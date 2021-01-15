package cn.cesgroup.cesweb.service.system.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.cesgroup.cesweb.api.system.entity.SysFile;
import cn.cesgroup.cesweb.common.core.util.Response;

/**
 * 文件管理
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 1:38:16 PM
 * @version 1.0.2020
 */
public interface SysFileService extends IService<SysFile> {

	/**
	 * 上传文件
	 * @param file
	 */
	Response<Map<String, String>> uploadFile(MultipartFile file);

	/**
	 * 读取文件
	 * @param bucket 桶名称
	 * @param fileName 文件名称
	 * @param response 输出流
	 */
	void getFile(String bucket, String fileName, HttpServletResponse response);

	/**
	 * 删除文件
	 * @param id
	 */
	Boolean deleteFile(String id);

}
