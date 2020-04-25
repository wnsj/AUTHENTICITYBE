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
 * @author syl
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

    public int addBuilding(@Param("buildingBean") BuildingBean buildingBean);

    public void patchById(@Param("buildingBean") BuildingBean buildingBean);

    public BuildingBean getAllByHtName(@Param("buildingBean") BuildingBean buildingBean);

    public List<BuildingBean> getAllBuild();

    /**
     * 推荐楼盘（根据人气热搜排序）
     * @return
     */
    public List<BuildingBean> getRecommend();

    /**
     * 优选楼盘
     * @return
     */
    public List<BuildingBean> getOptimization();

    /**
     * 品质楼盘（根据热销值排序）
     * @return
     */
    public List<BuildingBean> getQuality();

    public BuildingBean getBuildById(@Param("buildingBean") BuildingBean buildingBean);
}
