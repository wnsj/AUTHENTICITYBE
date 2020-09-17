package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.OfficeBean;
import com.jiubo.buildstore.bean.PropertyInfoBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.PropertyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.xml.bind.util.JAXBSource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author swd
 * @since 2020-09-17
 */
@RestController
@RequestMapping("/propertyInfoBean")
public class PropertyInfoController {

    @Autowired
    private PropertyInfoService propertyInfoService;

    @GetMapping("/getAllPInfo")
    public JSONObject getAllPInfo() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,propertyInfoService.getAllPInfo());
        return jsonObject;
    }
}
