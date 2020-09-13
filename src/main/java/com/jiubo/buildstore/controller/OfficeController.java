package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.OfficeBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author swd
 * @since 2020-09-13
 */
@RestController
@RequestMapping("/officeBean")
public class OfficeController {

    @Autowired
    private OfficeService officeService;
    @PostMapping("/getOfficeByPk")
    public JSONObject getOfficeByPk(@RequestBody OfficeBean officeBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,officeService.getOfficeByPk(officeBean.getId()));
        return jsonObject;
    }
}
