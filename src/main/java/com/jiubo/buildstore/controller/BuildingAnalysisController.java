package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.BuildingAnalysisBean;
import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.BuildingAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
@RestController
@RequestMapping("/buildingAnalysisBean")
public class BuildingAnalysisController {


    @Autowired
    private BuildingAnalysisService buildingAnalysisService;

    @PostMapping("/getAllAnalysisByBid")
    public JSONObject getAllAnalysisByBid(@RequestBody BuildingAnalysisBean buildingAnalysisBean){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,buildingAnalysisService.getAllAnalysisByBid(buildingAnalysisBean));
        return jsonObject;
    }

    @PostMapping("/insertByBid")
    public JSONObject insertByBid(String param, @RequestParam("horseTypeImg") MultipartFile[] horseTypeImg) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        BuildingAnalysisBean buildingAnalysisBean = JSONObject.parseObject(param, BuildingAnalysisBean.class);
        buildingAnalysisBean.setCreateDate(new Date());
        buildingAnalysisService.insertByBid(buildingAnalysisBean,horseTypeImg);
        return jsonObject;
    }

    @PostMapping("/patchBuildAnalysisById")
    public JSONObject patchBuildAnalysisById(String param, @RequestParam("horseTypeImg") MultipartFile[] horseTypeImg) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        BuildingAnalysisBean buildingAnalysisBean = JSONObject.parseObject(param, BuildingAnalysisBean.class);
        buildingAnalysisBean.setCreateDate(new Date());
        buildingAnalysisService.patchBuildAnalysisById(buildingAnalysisBean,horseTypeImg);
        return jsonObject;
    }


}
