package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.Exception.MessageException;
import com.jiubo.buildstore.bean.DevelogerBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.DevelogerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
@RestController
@RequestMapping("/develogerBean")
public class DevelogerController {

    @Autowired
    private DevelogerService develogerService;


    /**
     * 供应商复选
     * @return
     */
    @GetMapping("/getAllChara")
    public JSONObject getAllDev() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,develogerService.getAllDev());
        return jsonObject;
    }

    //开发商添加
    @GetMapping("/addDeveloger")
    public JSONObject addDeveloger(@RequestBody String param) throws MessageException {
        if (StringUtils.isBlank(param))throw new MessageException("上传的参数不能为空");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        DevelogerBean develogerBean = JSONObject.parseObject(param,DevelogerBean.class);
        develogerService.addDeveloger(develogerBean);
        return jsonObject;
    }

    //修改商添加
    @GetMapping("/updateDeveloger")
    public JSONObject updateDeveloger(@RequestBody String param) throws MessageException {
        if (StringUtils.isBlank(param))throw new MessageException("上传的参数不能为空");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        DevelogerBean develogerBean = JSONObject.parseObject(param,DevelogerBean.class);
        develogerService.updateDeveloger(develogerBean);
        return jsonObject;
    }

}
