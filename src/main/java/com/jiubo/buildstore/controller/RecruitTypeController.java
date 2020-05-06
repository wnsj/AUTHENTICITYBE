package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.RecruitTypeBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.RecruitTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author syl
 * @since 2020-04-25
 */
@RestController
@RequestMapping("/recruitTypeBean")
public class RecruitTypeController {


    @Autowired
    private RecruitTypeService recruitTypeService;

    @PostMapping("/getAllRecruitType")
    public JSONObject getAllRecruitType() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, recruitTypeService.getAllRecruitType());
        return jsonObject;
    }

    @PostMapping("/addRecruitType")
    public JSONObject addRecruitType(@RequestBody RecruitTypeBean recruitTypeBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        recruitTypeService.addRecruitType(recruitTypeBean);
        return jsonObject;
    }
}
