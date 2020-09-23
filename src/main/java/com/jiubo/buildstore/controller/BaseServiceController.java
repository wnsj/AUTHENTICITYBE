package com.jiubo.buildstore.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.Exception.MessageException;
import com.jiubo.buildstore.bean.RoomMainBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.BaseServiceService;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author swd
 * @since 2020-09-17
 */
@RestController
@RequestMapping("/baseServiceBean")
public class BaseServiceController {
	
	@Autowired
	private BaseServiceService baseServiceService;
	
	@PostMapping("/getAllBaseService")
    public JSONObject getAllBaseService() throws MessageException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, baseServiceService.getAllBaseService());
        return jsonObject;
    }

}
