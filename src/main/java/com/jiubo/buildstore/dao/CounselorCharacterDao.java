package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.CounselorCharacterBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
public interface CounselorCharacterDao extends BaseMapper<CounselorCharacterBean> {

    public List<CounselorCharacterBean> getAllCouChara();
}
