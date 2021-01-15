package cn.cesgroup.cesweb.service.system.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.cesgroup.cesweb.api.system.entity.SysFile;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.log.annotation.SysLog;
import cn.cesgroup.cesweb.common.security.annotation.Inner;
import cn.cesgroup.cesweb.service.system.service.SysFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 文件管理
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 4:09:59 PM
 * @version 1.0.2020
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sysfile")
@Api(value = "file", tags = "文件管理")
public class SysFileController {

	private final SysFileService sysFileService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param sysFile 文件管理
	 * @return
	 */
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@GetMapping("/page")
	public Response<Page<SysFile>> getSysFilePage(Page<SysFile> page, SysFile sysFile) {
		return Response.ok(sysFileService.page(page, Wrappers.query(sysFile)));
	}

	/**
	 * 通过id删除文件管理
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id删除文件管理", notes = "通过id删除文件管理")
	@SysLog("删除文件管理")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_file_del')")
	public Response<Boolean> removeById(@PathVariable String id) {
		return Response.ok(sysFileService.deleteFile(id));
	}

	/**
	 * 上传文件 文件名采用uuid,避免原始文件名中带"-"符号导致下载的时候解析出现异常
	 * @param file 资源
	 * @return R(/ admin / bucketName / filename)
	 */
	@PostMapping("/upload")
	public Response<Map<String,String>> upload(@RequestParam("file") MultipartFile file) {
		return sysFileService.uploadFile(file);
	}

	/**
	 * 获取文件
	 * @param bucket 桶名称
	 * @param fileName 文件空间/名称
	 * @param response
	 * @return
	 */
	@Inner(false)
	@GetMapping("/{bucket}/{fileName}")
	public void file(@PathVariable String bucket, @PathVariable String fileName, HttpServletResponse response) {
		sysFileService.getFile(bucket, fileName, response);
	}

}
