package cn.cesgroup.cesweb.monitor.trans.api.controller;

import cn.cesgroup.cesweb.monitor.trans.api.service.ApiAdminService;
import cn.cesgroup.cesweb.monitor.trans.api.service.ApiModelService;
import cn.cesgroup.cesweb.monitor.trans.compensate.model.TxModel;
import cn.cesgroup.cesweb.monitor.trans.model.ModelInfo;
import cn.cesgroup.cesweb.monitor.trans.model.ModelName;
import cn.cesgroup.cesweb.monitor.trans.model.TxState;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author LCN on 2017/7/1.
 * @author LCN
 * @author chanlong
 */
@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

	private final ApiAdminService apiAdminService;

	private final ApiModelService apiModelService;

	@RequestMapping(value = "/onlines", method = RequestMethod.GET)
	public List<ModelInfo> onlines() {
		return apiModelService.onlines();
	}

	@RequestMapping(value = "/avueOnlines", method = RequestMethod.GET)
	public List<ModelInfo> avueOnlines() {
		return apiModelService.onlines();
	}

	@RequestMapping(value = "/setting", method = RequestMethod.GET)
	public TxState setting() {
		return apiAdminService.getState();
	}

	@RequestMapping(value = "/avueSetting", method = RequestMethod.GET)
	public List<Map<String, Object>> avueSetting() {
		return apiAdminService.getMapState();
	}

	@RequestMapping(value = "/json", method = RequestMethod.GET)
	public String json() {
		return apiAdminService.loadNotifyJson();
	}

	@RequestMapping(value = "/modelList", method = RequestMethod.GET)
	public List<ModelName> modelList() {
		return apiAdminService.modelList();
	}

	@RequestMapping(value = "/modelTimes", method = RequestMethod.GET)
	public List<String> modelTimes(@RequestParam("model") String model) {
		return apiAdminService.modelTimes(model);
	}

	@RequestMapping(value = "/modelInfos", method = RequestMethod.GET)
	public List<TxModel> modelInfos(@RequestParam("path") String path) {
		return apiAdminService.modelInfos(path);
	}

	@SneakyThrows
	@RequestMapping(value = "/compensate", method = RequestMethod.GET)
	public boolean compensate(@RequestParam("path") String path) {
		return apiAdminService.compensate(path);
	}

	@SneakyThrows
	@RequestMapping(value = "/delCompensate", method = RequestMethod.GET)
	public boolean delCompensate(@RequestParam("path") String path) {
		return apiAdminService.delCompensate(path);
	}

	@SneakyThrows
	@RequestMapping(value = "/hasCompensate", method = RequestMethod.GET)
	public boolean hasCompensate() {
		return apiAdminService.hasCompensate();
	}

}
