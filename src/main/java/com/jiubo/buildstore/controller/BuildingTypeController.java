package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.BuildingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/buildingTypeBean")
public class BuildingTypeController {

    @Autowired
    private BuildingTypeService buildingTypeService;

    /**
     * 楼盘类型复选
     * @return
     */
    @GetMapping("/getAllBuildingType")
    public JSONObject getAllBuildingType(Integer type) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,buildingTypeService.getAllBuildingType(type));
        return jsonObject;
    }
}
