package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
@RestController
@RequestMapping("/buildingBean")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    /**
     * 条件筛选查询（分页）
     * @param buildingBean
     * @return
     */
    @PostMapping("/getAllBuildByPage")
    public JSONObject getAllBulidBypages(@RequestBody BuildingBean buildingBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,buildingService.getAllBulidBypage(buildingBean));
        return jsonObject;
    }
}
