package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.Exception.MessageException;
import com.jiubo.buildstore.bean.ArticleBean;
import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.ArticleService;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
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

    @PostMapping("/getArticleByPage")
    public JSONObject getArticleByPage(@RequestBody ArticleBean articleBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, articleService.getArticleByPage(articleBean));
        return jsonObject;
    }



    //上传文件接口
    @ResponseBody
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public JSONObject uploadFile(@RequestParam("file") MultipartFile[] file) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, articleService.uploadFile(file));
        return jsonObject;
    }



    @PostMapping("/insertArticle")
    public JSONObject insertArticle(String param){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        ArticleBean articleBean = JSONObject.parseObject(param, ArticleBean.class);
        articleService.insertArticle(articleBean);
        return jsonObject;
    }

    @PostMapping("/updateArticle")
    public JSONObject updateArticle(String param) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        ArticleBean articleBean = JSONObject.parseObject(param, ArticleBean.class);
        articleService.updateArticle(articleBean);
        return jsonObject;
    }
}
