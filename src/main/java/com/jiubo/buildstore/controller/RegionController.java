package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.RegionBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dx
 * @since 2020-05-01
 */
@RestController
@RequestMapping("/regionBean")
public class RegionController {




    @Autowired
    private RegionService regionService;

    @GetMapping("/getAllRegion")
    public JSONObject getAllRegion() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, regionService.getAllRegion());
        return jsonObject;
    }
}
