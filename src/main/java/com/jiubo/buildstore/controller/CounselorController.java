package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.bean.CounselorBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.CounselorService;
import org.apache.ibatis.annotations.Param;
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
@RequestMapping("/counselorBean")
public class CounselorController {

    @Autowired
    private CounselorService counselorService;

    @GetMapping("/getAllCouselor")
    public JSONObject getAllCouselor() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,counselorService.getAllCouselor());
        return jsonObject;
    }

    @PostMapping("/getAllCouselorByPage")
    public JSONObject getAllCouselorByPage(@RequestBody CounselorBean counselorBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,counselorService.getAllCouselorByPage(counselorBean));
        return jsonObject;
    }

    @PostMapping("/insertCou")
    public JSONObject insertCou(String param, @RequestParam("picture") MultipartFile[] picture) throws Exception{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        CounselorBean counselorBean = JSONObject.parseObject(param, CounselorBean.class);
        counselorService.insertCou(counselorBean,picture);
        return jsonObject;
    }


    @PostMapping("/patchCou")
    public JSONObject patchCou(String param, @RequestParam("picture") MultipartFile[] picture) throws Exception{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        CounselorBean counselorBean = JSONObject.parseObject(param, CounselorBean.class);
        counselorService.patchCou(counselorBean,picture);
        return jsonObject;
    }
}
