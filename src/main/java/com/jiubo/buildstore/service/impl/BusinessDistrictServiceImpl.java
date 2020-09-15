package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.BuildingImgBean;
import com.jiubo.buildstore.bean.BusinessDistrictBean;
import com.jiubo.buildstore.common.ImgPathConstant;
import com.jiubo.buildstore.common.ImgTypeConstant;
import com.jiubo.buildstore.dao.BuildingImgDao;
import com.jiubo.buildstore.dao.BusinessDistrictDao;
import com.jiubo.buildstore.service.BusinessDistrictService;
import com.jiubo.buildstore.util.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

	@Override
	public List<BusinessDistrictBean> getBusinessDistrict(Integer ldId) {
		if (ldId != null) {
			QueryWrapper<BusinessDistrictBean> queryWrapper = new QueryWrapper<BusinessDistrictBean>();
			queryWrapper.select("*");
			queryWrapper.eq("ld_id", ldId);
			List<BusinessDistrictBean> list = businessDistrictDao.selectList(queryWrapper);
			return list;
		}
		return null;
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
			Map<String, String> map = FileUtil.uploadFile(file,buildStoreDir, ImgPathConstant.BU_PATH,bean.getId(),ImgTypeConstant.HEAD_PICTURE);
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
		FileUtil.delFile(bean.getBuPath());
		Map<String, String> map = FileUtil.uploadFile(file,buildStoreDir,ImgPathConstant.BU_PATH,bean.getId(),2);
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
		bean.setModifyTime(new Date());
		return businessDistrictDao.updateById(bean);
	}

}
