package com.jiubo.buildstore.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildReceive;
import com.jiubo.buildstore.bean.BuildReturn;
import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.bean.BuildingImgBean;
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
    public JSONObject getAllBulidBypages(@RequestBody BuildReceive buildingBean) {
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
    public JSONObject getAllBuildByPage(@RequestBody BuildReceive buildingBean) {
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

        BuildReceive buildingBean = JSONObject.parseObject(addParam, BuildReceive.class);

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

        BuildReceive buildingBean = JSONObject.parseObject(addParam, BuildReceive.class);
        buildingService.patchById(buildingBean, effectImg, enPlanImg, buildRealImg, matchingRealImg,headImg,regionImg,video);

        return jsonObject;
    }


    /**
     * 获取所有楼盘的名字及id（楼盘下拉）
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
    public JSONObject getBuildByBuildId(BuildReceive buildingBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, buildingService.getBuildByBuildId(buildingBean));

        return jsonObject;
    }

    /**(文章页热门)
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
     * 获取热门楼盘（条件筛选页）
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


    /**
     * 条件筛选页模糊查询
     * @param buildingBean
     * @return
     */
    @PostMapping("/getBuildLikePage")
    public JSONObject getBuildLikePage(@RequestBody BuildReceive buildingBean){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        jsonObject.put(Constant.Result.RETDATA, buildingService.getBuildLikePage(buildingBean));

        return jsonObject;
    }

    /**
     * 后台管理 修改楼盘||评论中的图片删除（点击x）
     * @param buildingImgBean
     * @return
     */
    @PostMapping("/deleteImgFile")
    public JSONObject deleteImgFile(@RequestBody BuildingImgBean buildingImgBean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.Result.RETCODE, Constant.Result.SUCCESS);
        jsonObject.put(Constant.Result.RETMSG, Constant.Result.SUCCESS_MSG);
        buildingService.deleteImgFile(buildingImgBean);

        return jsonObject;
    }


}
