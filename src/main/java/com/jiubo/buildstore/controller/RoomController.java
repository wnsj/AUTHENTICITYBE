package com.jiubo.buildstore.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.Exception.MessageException;
import com.jiubo.buildstore.bean.RoomBean;
import com.jiubo.buildstore.bean.RoomMainBean;
import com.jiubo.buildstore.bean.RoomReceive;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.RoomService;

import io.swagger.annotations.ApiOperation;

import java.io.IOException;

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
@RequestMapping("/roomBean")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@ApiOperation(value = "添加写字楼房源", notes = "添加写字楼房源")
	@PostMapping("/addRoom")
	public JSONObject addRoom(String param, MultipartFile[] picture
			, MultipartFile[] video, MultipartFile headPicture) throws IOException, MessageException {
		System.out.println("param"+param);
		JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        RoomBean bean = JSONObject.parseObject(param, RoomBean.class);
        System.out.println("bean"+bean.toString());
        jsonObject.put(Constant.Result.RETDATA,roomService.addRoom(bean,picture,video,headPicture));
        return jsonObject;
	}
	
	@ApiOperation(value = "修改写字楼房源", notes = "修改写字楼房源")
	@PostMapping("/updateRoom")
	public JSONObject updateRoom(String param, MultipartFile[] picture
			, MultipartFile[] video, MultipartFile headPicture) throws IOException, MessageException {
		System.out.println("param"+param);
		JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        RoomBean bean = JSONObject.parseObject(param, RoomBean.class);
        System.out.println("bean"+bean.toString());
        jsonObject.put(Constant.Result.RETDATA,roomService.updateRoom(bean,picture,video,headPicture));
        return jsonObject;
	}
	
}
