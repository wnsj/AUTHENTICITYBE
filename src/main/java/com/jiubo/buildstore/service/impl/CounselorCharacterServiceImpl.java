package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.CounselorCharacterBean;

import com.jiubo.buildstore.dao.CounselorCharacterDao;
import com.jiubo.buildstore.service.CounselorCharacterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
@Service
public class CounselorCharacterServiceImpl extends ServiceImpl<CounselorCharacterDao, CounselorCharacterBean> implements CounselorCharacterService {

    @Autowired
    private CounselorCharacterDao counselorCharacterDao;
    @Override
    public List<CounselorCharacterBean> getAllCouChara() {
        return counselorCharacterDao.getAllCouChara();
    }
}
