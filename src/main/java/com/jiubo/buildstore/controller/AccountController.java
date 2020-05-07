package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.Exception.MessageException;
import com.jiubo.buildstore.bean.AccountBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.AccountService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mwl
 * @since 2020-02-10
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    //用户登录
    @PostMapping("/login")
    public JSONObject login(@RequestBody String params) throws Exception {
        if (StringUtils.isBlank(params)) throw new MessageException("参数接收失败!");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        AccountBean accountBean = JSONObject.parseObject(params, AccountBean.class);
        System.out.println("登录数据：" + params.toString());
        jsonObject.put(Constant.Result.RETDATA, accountService.login(accountBean));
        return jsonObject;
    }

//    //微信账号添加
//    @PostMapping("/addAccount")
//    public JSONObject addAccount(@RequestBody String params) throws Exception {
//        if (StringUtils.isBlank(params)) throw new MessageException("参数接收失败!");
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
//        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
//        System.out.println("测试：" + params.toString());
//        AccountBean accountBean = JSONObject.parseObject(params, AccountBean.class);
//        jsonObject.put(Constant.Result.RETDATA, accountService.addAccount(accountBean));
//        return jsonObject;
//    }
}
