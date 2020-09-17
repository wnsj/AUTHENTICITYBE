package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.bean.BuildingDynamicBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.BuildingDynamicService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
@RestController
@Scope("prototype")
@RequestMapping("/buildingDynamicBean")
public class BuildingDynamicController {

    @Autowired
    private BuildingDynamicService buildingDynamicService;


    @PostMapping("/getDynamicByBid")
    public JSONObject getDynamicByBid(@RequestBody BuildingDynamicBean buildingDynamicBean) {
    	System.out.println("buildingDynamicBean"+buildingDynamicBean.toString());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,buildingDynamicService.getDynamicByBid(buildingDynamicBean));
        return jsonObject;
    }

    /**
     * 移动端详情动态
     * @param buildingDynamicBean
     * @return
     */
    @PostMapping("/getNewestDynamicByBid")
    public JSONObject getNewestDynamicByBid(BuildingDynamicBean buildingDynamicBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,buildingDynamicService.getNewestDynamicByBid(buildingDynamicBean));
        return jsonObject;
    }

    @PostMapping("/getDynamicByPage")
    public JSONObject getDynamicByPage(@RequestBody BuildingDynamicBean buildingDynamicBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,buildingDynamicService.getDynamicByPage(buildingDynamicBean));
        return jsonObject;
    }

    /**
     * 后台管理分页
     * @param buildingDynamicBean
     * @return
     */
    @PostMapping("/getDynamicByPageBe")
    public JSONObject getDynamicByPageBe(@RequestBody BuildingDynamicBean buildingDynamicBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,buildingDynamicService.getDynamicByPageBe(buildingDynamicBean));
        return jsonObject;
    }
    /**
     * 
     * 根据咨询id查询咨询详情及上一条和下一条
     * @param
     * @return
     */
    @GetMapping("/getDynamicByDyId")
    public JSONObject getDynamicByPage(Integer dynamicId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,buildingDynamicService.getDynamicByDyId(dynamicId));
        return jsonObject;
    }

    @PostMapping("/patchDyById")
    public JSONObject patchDyById(String param,@RequestParam(value = "picture", required = false) MultipartFile file) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        BuildingDynamicBean buildingDynamicBean = JSONObject.parseObject(param, BuildingDynamicBean.class);
        buildingDynamicService.patchDyById(buildingDynamicBean,file);
        return jsonObject;
    }

    @PostMapping("/addDynamic")
    public JSONObject addDynamic(String param,@RequestParam(value = "picture", required = false) MultipartFile file) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        BuildingDynamicBean buildingDynamicBean = JSONObject.parseObject(param, BuildingDynamicBean.class);
        buildingDynamicService.addDynamic(buildingDynamicBean,file);
        return jsonObject;
    }
    @PostMapping("/getNewestDy")
    public JSONObject getNewestDy() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,buildingDynamicService.getNewestDy());
        return jsonObject;
    }

    /**
     * 首页资讯
     * @return
     */
    @PostMapping("/getDynamicByBuildId")
    public JSONObject getDynamicByBuildId() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,buildingDynamicService.getDynamicByBuildId());
        return jsonObject;
    }
}
