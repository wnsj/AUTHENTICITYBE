package com.jiubo.buildstore.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildingBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author dx
 * @since 2020-04-10
 */
public interface BuildingDao extends BaseMapper<BuildingBean> {

    /**
     * 分页查询楼盘信息
     *
     * @param buildingBean
     * @return
     */
    public List<BuildingBean> getAllBulidBypage(Page page, @Param("buildingBean") BuildingBean buildingBean);
/*
    @Param("proIdList") List<Integer> proIdList,
                                                @Param("isSaleList") List<Integer> isSaleList,
                                                @Param("ldIdList") List<Integer> ldIdList,
                                                @Param("devIdList") List<Integer> devIdList,
                                                @Param("btIdList") List<Integer> btIdList,
                                                @Param("idList") List<Integer> idList,
                                                @Param("chaIdList") List<Integer> chaIdList
 */
}
