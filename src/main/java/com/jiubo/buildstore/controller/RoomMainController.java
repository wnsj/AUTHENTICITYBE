package com.jiubo.buildstore.controller;


import com.jiubo.buildstore.bean.RoomMainBean;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.RoomReceive;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.RoomMainService;
import com.jiubo.buildstore.service.RoomService;

import io.swagger.annotations.ApiOperation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
    @PostMapping("/getRoomByConditions")
    public JSONObject getHotBusinessDistrict(@RequestBody RoomReceive receive) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, roomMainService.getRoomByConditions(receive));
        return jsonObject;
    }
    
    @ApiOperation(value = "共享办公房源", notes = "共享办公房源")
    @PostMapping("/getRoomOffice")
    public JSONObject getRoomOffice(@RequestBody RoomReceive receive) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, roomMainService.getRoomOffice(receive));
        return jsonObject;
    }

    @ApiOperation(value = "查询房源详情", notes = "查询房源详情")
    @GetMapping("/getRoomDetails")
    public JSONObject getRoomDetails(Integer roomMainId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, roomMainService.getRoomDetails(roomMainId));
        return jsonObject;
    }


    @ApiOperation(value = "查询共享详情", notes = "查询共享详情")
    @PostMapping("/getSharedById")
    public JSONObject getSharedById(@RequestBody RoomMainBean roomMainBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, roomMainService.getSharedById(roomMainBean.getBuildId()));
        return jsonObject;
    }

    @ApiOperation(value = "查询商铺详情", notes = "查询商铺详情")
    @GetMapping("/getStoneDetail")
    public JSONObject getStoneDetail(Integer roomMainId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, roomMainService.getStoneDetail(roomMainId));
        return jsonObject;
    }

    @ApiOperation(value = "添加房源主表", notes = "添加房源主表")
    @PostMapping("/addRoomMain")
    public JSONObject addRoomMain(@RequestBody String param) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        RoomMainBean bean = JSONObject.parseObject(param, RoomMainBean.class);
        jsonObject.put(Constant.Result.RETDATA, roomMainService.addRoomMain(bean));
        return jsonObject;
    }

    @ApiOperation(value = "修改房源主表", notes = "修改房源主表")
    @PostMapping("/updateRoomMain")
    public JSONObject updateRoomMain(@RequestBody String param) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        RoomMainBean bean = JSONObject.parseObject(param, RoomMainBean.class);
        jsonObject.put(Constant.Result.RETDATA, roomMainService.updateRoomMain(bean));
        return jsonObject;
    }
    
}
