package cn.cesgroup.cesweb.service.system.service.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.cesgroup.cesweb.api.system.entity.SysFile;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.oss.config.properties.OssProperties;
import cn.cesgroup.cesweb.common.oss.service.OssTemplate;
import cn.cesgroup.cesweb.common.security.util.SecurityUtils;
import cn.cesgroup.cesweb.service.system.mapper.SysFileMapper;
import cn.cesgroup.cesweb.service.system.service.SysFileService;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 文件管理 服务实现类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:37:21 PM
 * @version 1.0.2020
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements SysFileService {

	private final OssProperties ossProperties;

	private final OssTemplate minioTemplate;

	/**
	 * 上传文件
	 * @param file
	 */
	@Override
	public Response<Map<String, String>> uploadFile(MultipartFile file) {
		String fileName = IdUtil.simpleUUID() + StrUtil.DOT + FileUtil.extName(file.getOriginalFilename());
		Map<String, String> resultMap = new HashMap<>(4);
		resultMap.put("bucketName", ossProperties.getBucketName());
		resultMap.put("fileName", fileName);
		resultMap.put("url", String.format("/system/sysfile/%s/%s", ossProperties.getBucketName(), fileName));

		try {
			minioTemplate.putObject(ossProperties.getBucketName(), fileName, file.getInputStream());
			// 文件管理数据记录,收集管理追踪文件
			fileLog(file, fileName);
		}
		catch (Exception e) {
			log.error("上传失败", e);
			return Response.failed(e.getLocalizedMessage());
		}
		return Response.ok(resultMap);
	}

	/**
	 * 读取文件
	 * @param bucket
	 * @param fileName
	 * @param response
	 */
	@Override
	public void getFile(String bucket, String fileName, HttpServletResponse response) {
		try (InputStream inputStream = minioTemplate.getObject(bucket, fileName)) {
			response.setContentType("application/octet-stream; charset=UTF-8");
			IoUtil.copy(inputStream, response.getOutputStream());
		}
		catch (Exception e) {
			log.error("文件读取异常: {}", e.getLocalizedMessage());
		}
	}

	/**
	 * 删除文件
	 * @param id
	 */
	@Override
	@SneakyThrows
	@Transactional(rollbackFor = Exception.class)
	public Boolean deleteFile(String id) {
		SysFile file = this.getById(id);
		minioTemplate.removeObject(ossProperties.getBucketName(), file.getFileName());
		return this.removeById(id);
	}

	/**
	 * 文件管理数据记录,收集管理追踪文件
	 * @param file 上传文件格式
	 * @param fileName 文件名
	 */
	private void fileLog(MultipartFile file, String fileName) {
		SysFile sysFile = new SysFile();
		// 原文件名
		String original = file.getOriginalFilename();
		sysFile.setFileName(fileName);
		sysFile.setOriginal(original);
		sysFile.setFileSize(file.getSize());
		sysFile.setType(FileUtil.extName(original));
		sysFile.setBucketName(ossProperties.getBucketName());
		sysFile.setCreateUser(SecurityUtils.getUser().getUsername());
		this.save(sysFile);
	}

}
