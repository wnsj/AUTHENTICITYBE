package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.BusinessDistrictBean;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author swd
 * @since 2020-09-11
 */
public interface BusinessDistrictService extends IService<BusinessDistrictBean> {

	public List<BusinessDistrictBean> getBusinessDistrict(Integer ldId);

	public List<BusinessDistrictBean> getHotBusinessDistrict();

	public Integer addBusinessDistrict(BusinessDistrictBean bean, MultipartFile file) throws IOException;

	public Integer updateBusinessDistrict(BusinessDistrictBean bean, MultipartFile file) throws IOException;

}
