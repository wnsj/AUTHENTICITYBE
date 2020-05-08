package com.jiubo.buildstore.service;

import com.jiubo.buildstore.Exception.MessageException;
import com.jiubo.buildstore.bean.DevelogerBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
public interface DevelogerService extends IService<DevelogerBean> {
    public List<DevelogerBean> getAllDev();

    //添加开发商
    public void addDeveloger(DevelogerBean recruitLabelBean) throws MessageException;

    //修改开发商
    public void updateDeveloger(DevelogerBean recruitLabelBean) throws MessageException;

}
