package com.jiubo.buildstore.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.MessageTypeService;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author swd
 * @since 2020-09-17
 */
@RestController
@RequestMapping("/messageTypeBean")
public class MessageTypeController {
	
	@Autowired
	private MessageTypeService messageTypeService;
	
	@GetMapping("/getAllMessageType")
	public JSONObject getAllMessageType() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,messageTypeService.getAllMessageType());
        return jsonObject;
	}
	
}
