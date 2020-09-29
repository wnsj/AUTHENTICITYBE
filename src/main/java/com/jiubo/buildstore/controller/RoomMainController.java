package com.jiubo.buildstore.controller;


import com.jiubo.buildstore.Exception.MessageException;
import com.jiubo.buildstore.bean.RoomMainBean;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.RoomReceive;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.common.ImgTypeConstant;
import com.jiubo.buildstore.service.RoomMainService;
import com.jiubo.buildstore.service.RoomService;

import io.swagger.annotations.ApiOperation;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
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

    @ApiOperation(value = "多条件查询房源后台管理", notes = "多条件查询房源后台管理")
    @PostMapping("/getRoomByConditionsBe")
    public JSONObject getRoomByConditionsBe(@RequestBody RoomReceive receive) throws MessageException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, roomMainService.getRoomByConditionsBe(receive));
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
    public JSONObject addRoomMain(@RequestBody String param) throws MessageException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        RoomMainBean bean = JSONObject.parseObject(param, RoomMainBean.class);
        jsonObject.put(Constant.Result.RETDATA, roomMainService.addRoomMain(bean));
        return jsonObject;
    }

    @ApiOperation(value = "修改房源主表", notes = "修改房源主表")
    @PostMapping("/updateRoomMain")
    public JSONObject updateRoomMain(@RequestBody String param) throws MessageException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        RoomMainBean bean = JSONObject.parseObject(param, RoomMainBean.class);
        jsonObject.put(Constant.Result.RETDATA, roomMainService.updateRoomMain(bean));
        return jsonObject;
    }

    @ApiOperation(value = "根据id删除数据", notes = "根据id删除数据")
    @PostMapping("/deleteRoomByPk")
    public JSONObject deleteRoomByPk(@RequestBody String param) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        RoomMainBean bean = JSONObject.parseObject(param, RoomMainBean.class);
        roomMainService.deleteRoomByPk(bean);
        return jsonObject;
    }


    @ApiOperation(value = "房源上下架", notes = "房源上下架")
    @PostMapping("/offOrOnTheShelf")
    public JSONObject offOrOnTheShelf(@RequestBody String param) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        RoomMainBean bean = JSONObject.parseObject(param, RoomMainBean.class);
        roomMainService.offOrOnTheShelf(bean);
        return jsonObject;
    }
    
    @ApiOperation(value = "查询是否添加详情", notes = "查询是否添加详情")
    @PostMapping("/findHaveDetail")
    public JSONObject findHaveDetail(@RequestBody RoomMainBean bean) throws MessageException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, roomMainService.findHaveDetail(bean));
        return jsonObject;
    }
    
}
