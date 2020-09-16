package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.BuildingImgBean;
import com.jiubo.buildstore.bean.RoomBean;
import com.jiubo.buildstore.bean.RoomMainBean;
import com.jiubo.buildstore.bean.StoreRoomBean;
import com.jiubo.buildstore.common.ImgPathConstant;
import com.jiubo.buildstore.common.ImgTypeConstant;
import com.jiubo.buildstore.dao.BuildingImgDao;
import com.jiubo.buildstore.dao.RoomMainDao;
import com.jiubo.buildstore.dao.StoreRoomDao;
import com.jiubo.buildstore.service.StoreRoomService;
import com.jiubo.buildstore.util.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 商铺房源 服务实现类
 * </p>
 *
 * @author swd
 * @since 2020-09-12
 */
@Service
public class StoreRoomServiceImpl extends ServiceImpl<StoreRoomDao, StoreRoomBean> implements StoreRoomService {
	
	@Autowired
	private StoreRoomDao storeRoomDao;
	
	@Autowired
	private BuildingImgDao buildingImgDao;
	
	@Autowired
	private RoomMainDao roomMainDao;
	
	@Override
	public Integer addStoreRoom(StoreRoomBean bean, MultipartFile[] picture, MultipartFile[] video,
			MultipartFile headPicture) throws IOException {
		RoomMainBean mainBean = roomMainDao.selectById(bean.getRoomId());
		bean.setCreateDate(new Date());
		storeRoomDao.insert(bean);
		if(bean.getStoreId() != null) {
			List<BuildingImgBean> list = new ArrayList<BuildingImgBean>();
			if(picture != null) {
				for (int i = 0; i < picture.length; i++) {
					MultipartFile file = picture[i];
					Map<String, String> map = FileUtil.uploadFile(file,ImgPathConstant.HOUSE_PATH,mainBean.getId(),ImgTypeConstant.PICTURE);
					BuildingImgBean imgBean = new BuildingImgBean();
					imgBean.setImgName(map.get("name"));
					imgBean.setCreateDate(new Date());
					imgBean.setItId(ImgTypeConstant.PICTURE);
					imgBean.setImgPath(map.get("path"));
					imgBean.setInfoId(mainBean.getId());
					imgBean.setType(ImgTypeConstant.HOUSE);
					list.add(imgBean);
				}
				buildingImgDao.insertList(list);
				list.clear();
			}
			if(video != null) {
				for (int i = 0; i < video.length; i++) {
					MultipartFile file = video[i];
					Map<String, String> map = FileUtil.uploadFile(file,ImgPathConstant.HOUSE_PATH,mainBean.getId(),ImgTypeConstant.VIDEO);
					BuildingImgBean imgBean = new BuildingImgBean();
					imgBean.setImgName(map.get("name"));
					imgBean.setCreateDate(new Date());
					imgBean.setItId(ImgTypeConstant.VIDEO);
					imgBean.setImgPath(map.get("path"));
					imgBean.setInfoId(mainBean.getId());
					imgBean.setType(ImgTypeConstant.HOUSE);
					list.add(imgBean);
				}
				buildingImgDao.insertList(list);
				list.clear();
			}
			if(headPicture != null) {
				Map<String, String> map = FileUtil.uploadFile(headPicture, ImgPathConstant.HOUSE_PATH,mainBean.getId(),ImgTypeConstant.HEAD_PICTURE);
				BuildingImgBean imgBean = new BuildingImgBean();
				imgBean.setImgName(map.get("name"));
				imgBean.setCreateDate(new Date());
				imgBean.setItId(ImgTypeConstant.PICTURE);
				imgBean.setImgPath(map.get("path"));
				imgBean.setInfoId(mainBean.getId());
				imgBean.setType(ImgTypeConstant.HOUSE);
				mainBean.setRoomImg(imgBean.getImgPath());
				roomMainDao.updateById(mainBean);
			}
		}
		return 1;
	}

	@Override
	public Integer updateStoreRoom(StoreRoomBean bean, MultipartFile[] picture, MultipartFile[] video,
			MultipartFile headPicture) throws IOException {
		RoomMainBean mainBean = roomMainDao.selectById(bean.getRoomId());
		if(bean.getStoreId() != null) {
			List<BuildingImgBean> list = new ArrayList<BuildingImgBean>();
			if(picture.length > 0) {
				for (int i = 0; i < picture.length; i++) {
					MultipartFile file = picture[i];
					Map<String, String> map = FileUtil.uploadFile(file,ImgPathConstant.HOUSE_PATH,mainBean.getId(),ImgTypeConstant.PICTURE);
					BuildingImgBean imgBean = new BuildingImgBean();
					imgBean.setImgName(map.get("name"));
					imgBean.setCreateDate(new Date());
					imgBean.setItId(ImgTypeConstant.PICTURE);
					imgBean.setImgPath(map.get("path"));
					imgBean.setInfoId(mainBean.getId());
					imgBean.setType(ImgTypeConstant.HOUSE);
					list.add(imgBean);
				}
				buildingImgDao.insertList(list);
				list.clear();
			}
			if(video.length > 0) {
				for (int i = 0; i < video.length; i++) {
					MultipartFile file = video[i];
					Map<String, String> map = FileUtil.uploadFile(file,ImgPathConstant.HOUSE_PATH,mainBean.getId(),ImgTypeConstant.VIDEO);
					BuildingImgBean imgBean = new BuildingImgBean();
					imgBean.setImgName(map.get("name"));
					imgBean.setCreateDate(new Date());
					imgBean.setItId(ImgTypeConstant.VIDEO);
					imgBean.setImgPath(map.get("path"));
					imgBean.setInfoId(mainBean.getId());
					imgBean.setType(ImgTypeConstant.HOUSE);
					list.add(imgBean);
				}
				buildingImgDao.insertList(list);
				list.clear();
			}
			if(headPicture != null) {
				Map<String, String> map = FileUtil.uploadFile(headPicture, ImgPathConstant.HOUSE_PATH,mainBean.getId(),ImgTypeConstant.HEAD_PICTURE);
				QueryWrapper<BuildingImgBean> qw = new QueryWrapper<BuildingImgBean>();
				qw.select("*");
				qw.eq("IT_ID", ImgTypeConstant.PICTURE);
				qw.eq("TYPE", ImgTypeConstant.HOUSE);
				qw.eq("INFO_ID", mainBean.getId());
				BuildingImgBean imgBean = buildingImgDao.selectOne(qw);
				if(imgBean != null) {
					imgBean.setImgName(map.get("name"));
					imgBean.setImgPath(map.get("path"));
					buildingImgDao.updateById(imgBean);
				}else {
					BuildingImgBean buildingImgBean = new BuildingImgBean();
					
					imgBean.setImgName(map.get("name"));
					imgBean.setCreateDate(new Date());
					imgBean.setItId(ImgTypeConstant.PICTURE);
					imgBean.setImgPath(map.get("path"));
					imgBean.setInfoId(mainBean.getId());
					imgBean.setType(ImgTypeConstant.HOUSE);
					buildingImgDao.insert(buildingImgBean);
				}
				mainBean.setRoomImg(imgBean.getImgPath());
				roomMainDao.updateById(mainBean);
			}
		}
		return storeRoomDao.updateById(bean);
	}

}
