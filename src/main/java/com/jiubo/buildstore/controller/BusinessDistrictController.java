package com.jiubo.buildstore.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.jiubo.buildstore.bean.BuildingImgBean;
import com.jiubo.buildstore.bean.BusinessDistrictBean;
import com.jiubo.buildstore.bean.EntrustRentBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.common.ImgPathConstant;
import com.jiubo.buildstore.service.BusinessDistrictService;
import com.jiubo.buildstore.util.FileUtil;

import io.swagger.annotations.ApiOperation;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author swd
 * @since 2020-09-11
 */
@RestController
@RequestMapping("/businessDistrictBean")
public class BusinessDistrictController {
	
	@Value("${buildStoreDir}")
    private String buildStoreDir;
	
	@Autowired
	private BusinessDistrictService businessDistrictService;
	
	@ApiOperation(value = "根据区域查询对应的商圈", notes = "根据区域查询对应的商圈")
	@PostMapping("/getBusinessDistrict")
	public JSONObject getBusinessDistrict(@RequestBody BusinessDistrictBean businessDistrictBean) {
		JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,businessDistrictService.getBusinessDistrict(businessDistrictBean.getLdId()));
        return jsonObject;
	}
	
	@ApiOperation(value = "查询热门商圈", notes = "查询热门商圈")
	@GetMapping("/getHotBusinessDistrict")
	public JSONObject getHotBusinessDistrict() {
		JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,businessDistrictService.getHotBusinessDistrict());
        return jsonObject;
	}
	
	@ApiOperation(value = "添加商圈", notes = "添加商圈")
	@PostMapping("/addBusinessDistrict")
	public JSONObject addBusinessDistrict(@RequestBody String param,@RequestParam("file") MultipartFile file) throws IOException {
		JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        BusinessDistrictBean bean = JSONObject.parseObject(param, BusinessDistrictBean.class);
        jsonObject.put(Constant.Result.RETDATA,businessDistrictService.addBusinessDistrict(bean,file));
        return jsonObject;
	}
	
	@ApiOperation(value = "修改商圈", notes = "修改商圈")
	@PostMapping("/updateBusinessDistrict")
	public JSONObject addBusinessDistrict(@RequestBody BusinessDistrictBean bean,@RequestParam("file") MultipartFile file) throws IOException {
		JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA,businessDistrictService.updateBusinessDistrict(bean,file));
        return jsonObject;
	}
	
}
