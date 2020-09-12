package com.jiubo.buildstore.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.EntrustRentBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.BusinessDistrictService;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author swd
 * @since 2020-09-11
 */
@RestController
@RequestMapping("/businessDistrictBean")
public class BusinessDistrictController {
	
	@Autowired
	private BusinessDistrictService businessDistrictService;
	
	@ApiOperation(value = "根据区域查询对应的商圈", notes = "根据区域查询对应的商圈")
	@GetMapping("/getBusinessDistrict")
	public JSONObject getBusinessDistrict(Integer ldId) {
		JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,businessDistrictService.getBusinessDistrict(ldId));
        return jsonObject;
	}
	
	@ApiOperation(value = "查询热门商圈", notes = "查询热门商圈")
	@GetMapping("/getHotBusinessDistrict")
	public JSONObject getHotBusinessDistrict() {
		JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,businessDistrictService.getHotBusinessDistrict());
        return jsonObject;
	}
	
}
