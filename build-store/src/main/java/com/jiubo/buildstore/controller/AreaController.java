package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.AreaBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.AreaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dx
 * @since 2020-04-10
 */
@RestController
@RequestMapping("/areaBean")
public class AreaController {
    @Autowired
    private AreaService areaService;

    @GetMapping("/getAllArea")
    public JSONObject getAllArea() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,areaService.getAllArea());
        return jsonObject;
    }
}
