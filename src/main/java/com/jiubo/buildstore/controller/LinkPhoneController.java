package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.Exception.MessageException;
import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.bean.LinkPhoneBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.LinkPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
@RestController
@RequestMapping("/linkPhoneBean")
public class LinkPhoneController {

    @Autowired
    private LinkPhoneService linkPhoneService;


    @PostMapping("/getPhone")
    public JSONObject getPhone(@RequestBody LinkPhoneBean linkPhoneBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,linkPhoneService.getPhone(linkPhoneBean));
        return jsonObject;
    }


    @PostMapping("/addLinkPhone")
    public JSONObject addLinkPhone(@RequestBody LinkPhoneBean linkPhoneBean){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
//        LinkPhoneBean linkPhoneBean = JSONObject.parseObject(param, LinkPhoneBean.class);
        linkPhoneService.addLinkPhone(linkPhoneBean);
        return jsonObject;
    }

    @PostMapping("/patchLinkById")
    public JSONObject patchLinkById(@RequestBody LinkPhoneBean linkPhoneBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
//        LinkPhoneBean linkPhoneBean = JSONObject.parseObject(param, LinkPhoneBean.class);
        linkPhoneService.patchLinkById(linkPhoneBean);
        return jsonObject;
    }

    @PostMapping("/patchFormById")
    public JSONObject patchFormById(@RequestBody LinkPhoneBean linkPhoneBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
//        LinkPhoneBean linkPhoneBean = JSONObject.parseObject(param, LinkPhoneBean.class);
        linkPhoneService.patchFormById(linkPhoneBean);
        return jsonObject;
    }
}
