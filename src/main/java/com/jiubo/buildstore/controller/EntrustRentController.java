package com.jiubo.buildstore.controller;


import org.springframework.web.bind.annotation.GetMapping;
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

	@ApiOperation(value = "查看委托出租", notes = "查看委托出租")
	@PostMapping("/getEnByPage")
	public JSONObject getEnByPage(@RequestBody EntrustRentBean entrustRentBean) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
		jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
		jsonObject.put(Constant.Result.RETDATA,entrustRentService.getEnByPage(entrustRentBean));
		return jsonObject;
	}
	
	@ApiOperation(value = "修改委托出租备注信息和是否联系", notes = "修改委托出租备注信息和是否联系")
	@GetMapping("/updateEntrustRent")
	public JSONObject updateEntrustRent(Integer enId, Integer isContact, String remark, String returnSale) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
		jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
		jsonObject.put(Constant.Result.RETDATA,entrustRentService.updateEntrustRent(enId,isContact,remark,returnSale));
		return jsonObject;
	}
}
