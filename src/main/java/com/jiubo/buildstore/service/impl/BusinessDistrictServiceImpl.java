package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.BuildingImgBean;
import com.jiubo.buildstore.bean.BusinessDistrictBean;
import com.jiubo.buildstore.bean.LocationDistinguishBean;
import com.jiubo.buildstore.bean.RoomMainBean;
import com.jiubo.buildstore.common.ImgPathConstant;
import com.jiubo.buildstore.common.ImgTypeConstant;
import com.jiubo.buildstore.dao.BuildingImgDao;
import com.jiubo.buildstore.dao.BusinessDistrictDao;
import com.jiubo.buildstore.dao.LocationDistinguishDao;
import com.jiubo.buildstore.service.BusinessDistrictService;
import com.jiubo.buildstore.util.DateUtils;
import com.jiubo.buildstore.util.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author swd
 * @since 2020-09-11
 */
@Service
public class BusinessDistrictServiceImpl extends ServiceImpl<BusinessDistrictDao, BusinessDistrictBean>
		implements BusinessDistrictService {
	
	@Value("${buildStoreDir}")
    private String buildStoreDir;
	
	@Autowired
	private BusinessDistrictDao businessDistrictDao;

	@Autowired
	private BuildingImgDao buildingImgDao;
	
	@Autowired
	private LocationDistinguishDao locationDistinguishDao;

	@Override
	public List<BusinessDistrictBean> getBusinessDistrict(Integer ldId) {
			QueryWrapper<BusinessDistrictBean> queryWrapper = new QueryWrapper<BusinessDistrictBean>();
			queryWrapper.select("*");
			if(ldId != null) {
				queryWrapper.eq("ld_id", ldId);
			}
			List<BusinessDistrictBean> list = businessDistrictDao.selectList(queryWrapper);
			return list;
	}

	@Override
	public List<BusinessDistrictBean> getHotBusinessDistrict() {
		QueryWrapper<BusinessDistrictBean> queryWrapper = new QueryWrapper<BusinessDistrictBean>();
		queryWrapper.select("*");
		queryWrapper.eq("is_hot", 2);
		List<BusinessDistrictBean> list = businessDistrictDao.selectList(queryWrapper);
		return list;
	}

	@Override
	public Integer addBusinessDistrict(BusinessDistrictBean bean, MultipartFile file) throws IOException {
		bean.setCreateDate(new Date());
		bean.setModifyTime(new Date());
		businessDistrictDao.insert(bean);
		if(file != null) {
			Map<String, String> map = FileUtil.uploadFile(file, ImgPathConstant.BU_PATH,bean.getId(),2);
			if(!map.isEmpty()) {
				BuildingImgBean imgBean = new BuildingImgBean();
				imgBean.setImgName(map.get("name"));
				imgBean.setCreateDate(new Date());
				imgBean.setItId(ImgTypeConstant.HEAD_PICTURE);
				imgBean.setImgPath(map.get("path"));
				imgBean.setInfoId(bean.getId());
				imgBean.setType(ImgTypeConstant.BUSSINESS);
				buildingImgDao.insert(imgBean);
				bean.setBuPath(map.get("path"));
				businessDistrictDao.updateById(bean);
			}
		}
		
		return 1;
	}

	@Override
	public Integer updateBusinessDistrict(BusinessDistrictBean bean, MultipartFile file) throws IOException {
		if (null != file) {
			FileUtil.delFile(bean.getBuPath());
			Map<String, String> map = FileUtil.uploadFile(file,ImgPathConstant.BU_PATH,bean.getId(),2);
			if(!map.isEmpty()) {
				bean.setBuPath(map.get("path"));
				BuildingImgBean imgBean = new BuildingImgBean();
				imgBean.setImgName(map.get("name"));
				imgBean.setCreateDate(new Date());
				imgBean.setItId(ImgTypeConstant.HEAD_PICTURE);
				imgBean.setImgPath(map.get("path"));
				imgBean.setInfoId(bean.getId());
				imgBean.setType(ImgTypeConstant.BUSSINESS);
				buildingImgDao.insert(imgBean);
			}

		}
		bean.setModifyTime(new Date());
		return businessDistrictDao.updateById(bean);
	}

	@Override
	public PageInfo<BusinessDistrictBean> getBusinessDistrictPage(BusinessDistrictBean bean) {
		Integer pageNum = StringUtils.isBlank(bean.getCurrent()) ? 1 : Integer.valueOf(bean.getCurrent());
		Integer pageSize = StringUtils.isBlank(bean.getPageSize()) ? 10 : Integer.valueOf(bean.getPageSize());
		PageHelper.startPage(pageNum,pageSize);
		QueryWrapper<BusinessDistrictBean> qw = new QueryWrapper<BusinessDistrictBean>();
		qw.select("*");
		if(bean.getLdId() != null) {
			qw.eq("ld_id", bean.getLdId());
		}
		List<BusinessDistrictBean> list = businessDistrictDao.selectList(qw);
		for (int i = 0; i < list.size(); i++) {
			BusinessDistrictBean districtBean = list.get(i);
			String formatDate = DateUtils.formatDate(districtBean.getCreateDate(), "yyyy-MM-dd");
			districtBean.setCreateTime(formatDate);
			if(districtBean.getIsHot() == 2) {
				districtBean.setIsHotName("热门");
			}else {
				districtBean.setIsHotName("不热门");
			}
			if (!StringUtils.isBlank(districtBean.getBuPath())) {
				districtBean.setBuPath(ImgPathConstant.INTERFACE_PATH.concat(buildStoreDir).concat(districtBean.getBuPath()));
			}
			LocationDistinguishBean distinguishBean = locationDistinguishDao.selectById(districtBean.getLdId());
			districtBean.setLdName(distinguishBean.getLdName());
		}
		PageInfo<BusinessDistrictBean> page = new PageInfo<BusinessDistrictBean>(list);
		return page;
	}

}
