package com.jiubo.buildstore.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.ArticleBean;
import com.jiubo.buildstore.bean.EntrustRentBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.EntrustRentService;

import io.swagger.annotations.Api;
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
@RequestMapping("/entrustRentBean")
public class EntrustRentController {
	
	@Autowired
	private EntrustRentService entrustRentService;
	
	@ApiOperation(value = "添加委托出租", notes = "添加委托出租")
	@PostMapping("/insertEntrustRent")
	public JSONObject insertEntrustRent(@RequestBody EntrustRentBean entrustRentBean) {
		JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        entrustRentService.insertEntrustRent(entrustRentBean);
        return jsonObject;
	}
}
