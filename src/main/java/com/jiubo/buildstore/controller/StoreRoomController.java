package com.jiubo.buildstore.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.RoomBean;
import com.jiubo.buildstore.bean.StoreRoomBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.StoreRoomService;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 商铺房源 前端控制器
 * </p>
 *
 * @author swd
 * @since 2020-09-12
 */
@RestController
@RequestMapping("/storeRoomBean")
public class StoreRoomController {
	
	@Autowired
	private StoreRoomService storeRoomService;
	
	@ApiOperation(value = "添加商铺房源", notes = "添加商铺房源")
	@PostMapping("/addStoreRoom")
	public JSONObject addStoreRoom(@RequestBody String param, MultipartFile[] picture
			, MultipartFile[] video, MultipartFile headPicture) throws IOException {
		System.out.println("param");
		JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        StoreRoomBean bean = JSONObject.parseObject(param, StoreRoomBean.class);
        jsonObject.put(Constant.Result.RETDATA,storeRoomService.addStoreRoom(bean,picture,video,headPicture));
        return jsonObject;
	}
	
	@ApiOperation(value = "更新商铺房源", notes = "更新商铺房源")
	@PostMapping("/updateStoreRoom")
	public JSONObject updateStoreRoom(@RequestBody String param, MultipartFile[] picture
			, MultipartFile[] video, MultipartFile headPicture) throws IOException {
		System.out.println("param");
		JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        StoreRoomBean bean = JSONObject.parseObject(param, StoreRoomBean.class);
        jsonObject.put(Constant.Result.RETDATA,storeRoomService.updateStoreRoom(bean,picture,video,headPicture));
        return jsonObject;
	}
	
}
