package com.jiubo.buildstore.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildReceive;
import com.jiubo.buildstore.bean.BuildReturn;
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
    public List<BuildReturn> getAllBulidBypage(Page page, @Param("buildingBean") BuildReceive buildingBean);

    public int addBuilding(@Param("buildingBean") BuildReceive buildingBean);

    public void patchById(@Param("buildingBean") BuildReceive buildingBean);

    public BuildReturn getAllByHtName(@Param("buildingBean") BuildReceive buildingBean);

    public List<BuildingBean> getAllBuild();

    /**
     * 推荐楼盘（根据人气热搜排序）
     * @return
     */
    public List<BuildReturn> getRecommend();

    /**
     * 优选楼盘
     * @return
     */
    public List<BuildReturn> getOptimization();

    /**
     * 品质楼盘（根据热销值排序）
     * @return
     */
    public List<BuildReturn> getQuality();

    public BuildReturn getBuildById(@Param("buildingBean") BuildReceive buildingBean);

    public List<BuildReturn> getSellWell();

    /**
     * 人气
     * @return
     */
    public List<BuildReturn> getPHotBuild();

    /**
     * 热销
     * @return
     */
    public List<BuildReturn> getSWBuild();

    /**
     * 热搜
     * @return
     */
    public List<BuildReturn> getHotSBuild();

    public List<BuildReturn> getBuildLikePage(Page page,@Param("buildingBean") BuildReceive buildingBean);
}
