package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.ArticleBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
 * @since 2020-04-25
 */
@RestController
@RequestMapping("/articleBean")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/getArticleByType")
    public JSONObject getArticleByType(@RequestBody ArticleBean articleBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, articleService.getArticleByType(articleBean));

        return jsonObject;
    }
}
