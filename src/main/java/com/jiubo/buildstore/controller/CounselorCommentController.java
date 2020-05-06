package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.bean.CounselorCommentBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.CounselorCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
@RestController
@RequestMapping("/counselorCommentBean")
public class CounselorCommentController {

    @Autowired
    private CounselorCommentService counselorCommentService;


    @PostMapping("/getCounselorByBid")
    public JSONObject getCounselorByBid(CounselorCommentBean counselorCommentBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        System.out.println();
        jsonObject.put(Constant.Result.RETDATA,counselorCommentService.getCounselorByBid(counselorCommentBean));
        return jsonObject;
    }
    @PostMapping("/getCounselorByPage")
    public JSONObject getCounselorByPage(@RequestBody CounselorCommentBean counselorCommentBean){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,counselorCommentService.getCounselorByPage(counselorCommentBean));
        return jsonObject;
    }

    /**
     * 点赞接口
     * @return
     */
    @PostMapping("/updateNumById")
    public JSONObject updateNumById(CounselorCommentBean counselorCommentBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        counselorCommentService.updateNumById(counselorCommentBean);
        return jsonObject;
    }


    /**
     * 修改评论
     * @param
     * @return
     */
    @PostMapping("/updateComById")
    public JSONObject updateComById(String param,@RequestParam("picImg") MultipartFile[] picImg) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        CounselorCommentBean counselorCommentBean = JSONObject.parseObject(param, CounselorCommentBean.class);
        counselorCommentService.updateComById(counselorCommentBean,picImg);
        return jsonObject;
    }

    @PostMapping("/addCom")
    public JSONObject addCom(String param, @RequestParam("picImg")MultipartFile[] picImg) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        CounselorCommentBean counselorCommentBean = JSONObject.parseObject(param, CounselorCommentBean.class);
        counselorCommentService.addCom(counselorCommentBean,picImg);
        return jsonObject;
    }
}
