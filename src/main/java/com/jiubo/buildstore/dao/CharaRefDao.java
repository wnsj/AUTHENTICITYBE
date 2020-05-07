package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.CharaRefBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dx
 * @since 2020-04-30
 */
public interface CharaRefDao extends BaseMapper<CharaRefBean> {
 public void insertChaRefBatch(List<CharaRefBean> charaRefBeanList);

 public List<CharaRefBean> getChaRefByBidList(@Param("charaRefBean") CharaRefBean charaRefBean);

 public void deleteCharaRefByBid(@Param("buildId") Integer buildId);

 public List<CharaRefBean> getCharaByBuildId(@Param("buildId") Integer buildId);

 public List<CharaRefBean> getChaRefByChaIdList(@Param("charaRefBean") CharaRefBean charaRefBean);
}
