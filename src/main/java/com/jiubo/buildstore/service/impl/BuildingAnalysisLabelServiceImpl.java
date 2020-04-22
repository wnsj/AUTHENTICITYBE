package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.BuildingAnalysisLabelBean;
import com.jiubo.buildstore.dao.BuildingAnalysisLabelDao;
import com.jiubo.buildstore.service.BuildingAnalysisLabelService;
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
public class BuildingAnalysisLabelServiceImpl extends ServiceImpl<BuildingAnalysisLabelDao, BuildingAnalysisLabelBean> implements BuildingAnalysisLabelService {


    @Autowired
    private BuildingAnalysisLabelDao buildingAnalysisLabelDao;
    @Override
    public List<BuildingAnalysisLabelBean> getALabel() {
        return buildingAnalysisLabelDao.getALabel();
    }
}
