package com.jiubo.buildstore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildingAnalysisBean;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
public interface BuildingAnalysisService extends IService<BuildingAnalysisBean> {

    /**
     * 通过户型id获取楼盘id集合
     * @param buildingAnalysisBean
     * @return
     */
    public List<BuildingAnalysisBean> getBidByBhtIdList(BuildingAnalysisBean buildingAnalysisBean);

    /**
     * 通过楼盘id获取户型id 楼盘id集合
     * @param buildingAnalysisBean
     * @return
     */
    public List<BuildingAnalysisBean> getBidByBIdList(BuildingAnalysisBean buildingAnalysisBean);

    /**
     * 通过楼盘id获取户型分析
     * @param buildingAnalysisBean
     * @return
     */
    public Page<BuildingAnalysisBean> getAllAnalysisByBid(BuildingAnalysisBean buildingAnalysisBean);

    public void insertByBid(BuildingAnalysisBean buildingAnalysisBean, MultipartFile[] horseTypeImg) throws Exception;
}
