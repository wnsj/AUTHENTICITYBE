package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.AreaBean;
import com.jiubo.buildstore.bean.BuildingImgBean;
import com.jiubo.buildstore.bean.BuildingTypeBean;
import com.jiubo.buildstore.bean.RoomBean;
import com.jiubo.buildstore.bean.RoomReceive;
import com.jiubo.buildstore.bean.TotlePriceTypeBean;
import com.jiubo.buildstore.bean.UnitPriceTypeBean;
import com.jiubo.buildstore.common.ImgPathConstant;
import com.jiubo.buildstore.common.ImgTypeConstant;
import com.jiubo.buildstore.dao.AreaDao;
import com.jiubo.buildstore.dao.BuildingImgDao;
import com.jiubo.buildstore.dao.BuildingTypeDao;
import com.jiubo.buildstore.dao.RoomDao;
import com.jiubo.buildstore.dao.TotlePriceTypeDao;
import com.jiubo.buildstore.dao.UnitPriceTypeDao;
import com.jiubo.buildstore.service.RoomService;
import com.jiubo.buildstore.util.CollectionsUtils;
import com.jiubo.buildstore.util.FileUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
public class RoomServiceImpl extends ServiceImpl<RoomDao, RoomBean> implements RoomService {
	
	@Value("${buildStoreDir}")
    private String buildStoreDir;
	
	@Autowired
	private RoomDao roomDao;
	
	@Autowired
	private BuildingImgDao buildingImgDao;


	@Override
	public Integer addRoom(RoomBean bean, MultipartFile[] picture, MultipartFile[] video) throws IOException {
		bean.setCreateDate(new Date());
		bean.setModifyDate(new Date());
		roomDao.insert(bean);
		for (int i = 0; i < picture.length; i++) {
			MultipartFile file = picture[i];
			Map<String, String> map = FileUtil.uploadFile(file,ImgPathConstant.HOUSE_PATH,bean.getId(),2);
			if(i == 0) {
				
			}
			BuildingImgBean imgBean = new BuildingImgBean();
			imgBean.setImgName(map.get("name"));
			imgBean.setCreateDate(new Date());
			imgBean.setItId(2);
			imgBean.setImgPath(map.get("path"));
			imgBean.setInfoId(bean.getId());
			imgBean.setType(2);
			buildingImgDao.insert(imgBean);
		}
		return null;
	}
}
