package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.LocationDistinguishBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.LocationDistinguishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/locationDistinguishBean")
public class LocationDistinguishController {
    @Autowired
    private LocationDistinguishService locationDistinguishService;


    /**
     * 区域位置复选
     * @return
     */
    @PostMapping("/getAllDistinguish")
    public JSONObject getAllDistinguish(@RequestBody LocationDistinguishBean locationDistinguishBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,locationDistinguishService.getAllDistinguish(locationDistinguishBean));
        return jsonObject;
    }
}
