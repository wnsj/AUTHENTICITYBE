package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.bean.RecruitBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author syl
 * @since 2020-04-25
 */
@RestController
@RequestMapping("/recruitBean")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    @PostMapping("/getEasyInfo")
    public JSONObject getEasyInfo(RecruitBean recruitBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);

//        RecruitBean recruitBean = new RecruitBean();
//        recruitBean.setPositionType(positionType);
//        recruitBean.setRecruitmentType(recruitmentType);

        jsonObject.put(Constant.Result.RETDATA, recruitService.getEasyInfo(recruitBean));
        return jsonObject;
    }

    @PostMapping("/getDetails")
    public JSONObject getDetails(RecruitBean recruitBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, recruitService.getDetails(recruitBean));
        return jsonObject;
    }

    @PostMapping("/addRecruit")
    public JSONObject addRecruit(@RequestBody RecruitBean recruitBean){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
//        RecruitBean recruitBean = JSONObject.parseObject(param, RecruitBean.class);
        recruitService.addRecruit(recruitBean);
        return jsonObject;
    }

    @PostMapping("/getRecruitByPage")
    public JSONObject getRecruitByPage(@RequestBody RecruitBean recruitBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
//        RecruitBean recruitBean = JSONObject.parseObject(param, RecruitBean.class);
        jsonObject.put(Constant.Result.RETDATA, recruitService.getRecruitByPage(recruitBean));
        return jsonObject;
    }

    @PostMapping("/patchRecruitById")
    public JSONObject patchRecruitById(@RequestBody RecruitBean recruitBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
//        RecruitBean recruitBean = JSONObject.parseObject(param, RecruitBean.class);
        recruitService.patchRecruitById(recruitBean);
        return jsonObject;
    }
}
