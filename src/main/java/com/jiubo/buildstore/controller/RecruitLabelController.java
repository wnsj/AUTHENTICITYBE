package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.RecruitLabelBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.RecruitLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dx
 * @since 2020-04-25
 */
@Controller
@RequestMapping("/recruitLabelBean")
public class RecruitLabelController {

    @Autowired
    private RecruitLabelService recruitLabelService;

    @PostMapping("/getLabelByType")
    public JSONObject getLabelByType(@RequestBody RecruitLabelBean recruitLabelBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, recruitLabelService.getLabelByType(recruitLabelBean));
        return jsonObject;
    }
}
