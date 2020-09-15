package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.OfficeBean;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author swd
 * @since 2020-09-13
 */
public interface OfficeService extends IService<OfficeBean> {
    OfficeBean getOfficeByPk(Integer id);
    void addOffice(OfficeBean officeBean, MultipartFile headImg,MultipartFile[] picture,MultipartFile video) throws IOException;
}
