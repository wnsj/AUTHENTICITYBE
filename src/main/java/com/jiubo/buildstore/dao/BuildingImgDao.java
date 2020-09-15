package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.BuildingImgBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
public interface BuildingImgDao extends BaseMapper<BuildingImgBean> {

    public void addImg(@Param("buildingImgBean") BuildingImgBean buildingImgBean);

    public void patchImgById(@Param("buildingImgBean") BuildingImgBean buildingImgBean);

    public List<BuildingImgBean> getAllByBid(@Param("buildingImgBean") BuildingImgBean buildingImgBean);

    public void deleteByImgName(@Param("buildingImgBean") BuildingImgBean buildingImgBean);

    public List<BuildingImgBean> getHeadImgByBuildId(@Param("buildingImgBean") BuildingImgBean buildingImgBean);

    public List<BuildingImgBean> getAllImgByBuildId(@Param("buildingImgBean") BuildingImgBean buildingImgBean);

    public void deleteImgById(@Param("buildingImgBean") BuildingImgBean buildingImgBean);
    public BuildingImgBean getImgById(@Param("buildingImgBean") BuildingImgBean buildingImgBean);

	public void insertList(@Param("list") List<BuildingImgBean> list);
}
