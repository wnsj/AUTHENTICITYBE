package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
@Slf4j
@RestController
@RequestMapping("/buildingBean")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    /**
     * 条件筛选查询（分页）
     *
     * @param buildingBean
     * @return
     */
    @PostMapping("/getAllBuildByPage")
    public JSONObject getAllBulidBypages(@RequestBody BuildingBean buildingBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, buildingService.getAllBulidBypage(buildingBean));
        return jsonObject;
    }

    /**
     * 条件筛选查询（分页）
     *后台管理
     * @param buildingBean
     * @return
     */
    @PostMapping("/getAllBulidBypages")
    public JSONObject getAllBuildByPage(@RequestBody BuildingBean buildingBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, buildingService.getAllBulidByCondition(buildingBean));
        return jsonObject;
    }

    /**
     * 新增楼盘
     *
     * @param addParam
     * @param effectImg
     * @param enPlanImg
     * @param buildRealImg
     * @param matchingRealImg
     * @return
     * @throws Exception
     */
    @PostMapping("/addBuilding")
    public JSONObject addBuilding(String addParam, @RequestParam("effectImg") MultipartFile[] effectImg,
                                  @RequestParam("enPlanImg") MultipartFile[] enPlanImg,
                                  @RequestParam("buildRealImg") MultipartFile[] buildRealImg,
                                  @RequestParam("matchingRealImg") MultipartFile[] matchingRealImg,
                                  @RequestParam("headImg") MultipartFile[] headImg,
                                  @RequestParam("regionImg") MultipartFile[] regionImg,
                                  @RequestParam("video") MultipartFile[] video) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        BuildingBean buildingBean = null;
        try{
            buildingBean = JSONObject.parseObject(addParam, BuildingBean.class);
        }catch (Exception e) {
            log.error("转译失败Json{}",addParam);
            log.error("异常{}",e.toString());
        }

        buildingService.addBuilding(buildingBean, effectImg, enPlanImg, buildRealImg, matchingRealImg,headImg,regionImg,video);
        return jsonObject;
    }


    /**
     * 更新楼盘
     *
     * @param addParam
     * @param effectImg
     * @param enPlanImg
     * @param buildRealImg
     * @param matchingRealImg
     * @return
     * @throws Exception
     */
    @PostMapping("/patchById")
    public JSONObject patchById(String addParam, @RequestParam("effectImg") MultipartFile[] effectImg,
                                @RequestParam("enPlanImg") MultipartFile[] enPlanImg,
                                @RequestParam("buildRealImg") MultipartFile[] buildRealImg,
                                @RequestParam("matchingRealImg") MultipartFile[] matchingRealImg,
                                @RequestParam("headImg") MultipartFile[] headImg,
                                @RequestParam("regionImg") MultipartFile[] regionImg,
                                @RequestParam("video") MultipartFile[] video) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);

        BuildingBean buildingBean = JSONObject.parseObject(addParam, BuildingBean.class);
        buildingService.patchById(buildingBean, effectImg, enPlanImg, buildRealImg, matchingRealImg,headImg,regionImg,video);

        return jsonObject;
    }


    /**
     * 获取所有楼盘
     * @return
     */
    @GetMapping("/getAllBuild")
    public JSONObject getAllBuild() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, buildingService.getAllBuild());

        return jsonObject;
    }

    /**
     * 首页获取楼盘信息
     * @param
     * @return
     */
    @PostMapping("/getAllByBuildName")
    public JSONObject getAllByBuildName() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, buildingService.getAllByBuildName());

        return jsonObject;
    }

    /**
     * 详情页（楼盘信息）
     * @param buildingBean
     * @return
     */
    @PostMapping("/getBuildByBuildId")
    public JSONObject getBuildByBuildId(BuildingBean buildingBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, buildingService.getBuildByBuildId(buildingBean));

        return jsonObject;
    }

    /**
     * 获取热门
     * @return
     */
    @GetMapping("/getSellWell")
    public JSONObject getSellWell() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, buildingService.getSellWell());

        return jsonObject;
    }
    /**
     * 获取热门楼盘
     */
    @GetMapping("/getHot")
    public JSONObject getHot() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, buildingService.getHot());

        return jsonObject;
    }

    /**
     * 优质楼盘
     * @param
     * @return
     */
    @PostMapping("/getRecommend")
    public JSONObject getRecommend() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, buildingService.getRecommend());

        return jsonObject;
    }


    @PostMapping("/getBuildLikePage")
    public JSONObject getBuildLikePage(@RequestBody BuildingBean buildingBean){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, buildingService.getBuildLikePage(buildingBean));

        return jsonObject;
    }
}
