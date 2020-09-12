package com.jiubo.buildstore.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.RoomReceive;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.RoomMainService;
import com.jiubo.buildstore.service.RoomService;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 房源主表 前端控制器
 * </p>
 *
 * @author swd
 * @since 2020-09-12
 */
@RestController
@RequestMapping("/roomMainBean")
public class RoomMainController {
	
	@Autowired
	private RoomMainService roomMainService;
	
	@ApiOperation(value = "多条件查询房源", notes = "多条件查询房源")
	@GetMapping("/getRoomByConditions")
	public JSONObject getHotBusinessDistrict(RoomReceive receive) {
		JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,roomMainService.getRoomByConditions(receive));
        return jsonObject;
	}
	
}
