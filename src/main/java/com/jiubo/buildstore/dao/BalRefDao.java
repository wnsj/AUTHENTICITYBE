package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.BalRefBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author syl
 * @since 2020-04-27
 */
public interface BalRefDao extends BaseMapper<BalRefBean> {

    public List<BalRefBean> getRefByBaIdList(@Param("balRefBean") BalRefBean balRefBean);

    public void insertBatch(@Param("balRefBean") BalRefBean balRefBean);

    public void deleteRefByBaId(@Param("balRefBean") BalRefBean balRefBean);
}
