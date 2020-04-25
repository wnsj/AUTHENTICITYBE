package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.RecruitBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dx
 * @since 2020-04-25
 */
@RestController
@RequestMapping("/recruitBean")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    @PostMapping("/getEasyInfo")
    public JSONObject getEasyInfo(@RequestBody RecruitBean recruitBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, recruitService.getEasyInfo(recruitBean));
        return jsonObject;
    }

    @PostMapping("/getDetails")
    public JSONObject getDetails(@RequestBody RecruitBean recruitBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, recruitService.getDetails(recruitBean));
        return jsonObject;
    }
}
