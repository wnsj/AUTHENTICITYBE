package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.Exception.MessageException;
import com.jiubo.buildstore.bean.BuildReceive;
import com.jiubo.buildstore.bean.OfficeBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.OfficeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author swd
 * @since 2020-09-13
 */
@RestController
@RequestMapping("/officeBean")
public class OfficeController {

    @Autowired
    private OfficeService officeService;
    @PostMapping("/getOfficeByPk")
    public JSONObject getOfficeByPk(@RequestBody OfficeBean officeBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,officeService.getOfficeByPk(officeBean.getId()));
        return jsonObject;
    }

    @PostMapping("/addOffice")
    public JSONObject addOffice(String addParam,
                                @RequestParam(value = "headImg",required = false) MultipartFile headImg,
                                @RequestParam(value = "picture",required = false) MultipartFile[] picture,
                                @RequestParam(value = "video",required = false) MultipartFile video) throws IOException, MessageException {
        if (StringUtils.isBlank(addParam)) throw new MessageException("接收参数失败！");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        OfficeBean officeBean = JSONObject.parseObject(addParam, OfficeBean.class);
        officeService.addOffice(officeBean,headImg,picture,video);
        return jsonObject;
    }
    
    @PostMapping("/getAllOffice")
    public JSONObject getAllOffice(@RequestBody OfficeBean officeBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,officeService.getAllOffice(officeBean));
        return jsonObject;
    }
    @PostMapping("/patchOffice")
    public JSONObject patchOffice(String addParam,
                                @RequestParam(value = "headImg",required = false) MultipartFile headImg,
                                @RequestParam(value = "picture",required = false) MultipartFile[] picture,
                                @RequestParam(value = "video",required = false) MultipartFile video) throws IOException, MessageException {
        if (StringUtils.isBlank(addParam)) throw new MessageException("接收参数失败！");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        OfficeBean officeBean = JSONObject.parseObject(addParam, OfficeBean.class);
        officeService.patchOffice(officeBean,headImg,picture,video);
        return jsonObject;
    }
}
