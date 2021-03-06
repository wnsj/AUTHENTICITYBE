package com.jiubo.buildstore.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildingDynamicBean;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
public interface BuildingDynamicService extends IService<BuildingDynamicBean> {
    public PageInfo<BuildingDynamicBean> getDynamicByBid(BuildingDynamicBean buildingDynamicBean);

    public BuildingDynamicBean getNewestDynamicByBid(BuildingDynamicBean buildingDynamicBean);

    public Page<BuildingDynamicBean> getDynamicByPage(BuildingDynamicBean buildingDynamicBean);
    public Page<BuildingDynamicBean> getDynamicByPageBe(BuildingDynamicBean buildingDynamicBean);

    public void patchDyById(BuildingDynamicBean buildingDynamicBean,MultipartFile[] file) throws IOException;

    public void addDynamic(BuildingDynamicBean buildingDynamicBean,MultipartFile[] file) throws IOException;
    
    public Map<String, BuildingDynamicBean> getDynamicByDyId(Integer dynamicId);

    List<BuildingDynamicBean> getNewestDy();

    JSONObject getDynamicByBuildId();

    void deleteDynamicById(BuildingDynamicBean buildingDynamicBean);

    List<BuildingDynamicBean> getRecommendDy(BuildingDynamicBean buildingDynamicBean);
}
