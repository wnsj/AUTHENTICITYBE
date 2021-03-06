package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.Exception.MessageException;
import com.jiubo.buildstore.bean.*;
import com.jiubo.buildstore.common.ImgPathConstant;
import com.jiubo.buildstore.common.ImgTypeConstant;
import com.jiubo.buildstore.dao.*;
import com.jiubo.buildstore.service.RoomService;
import com.jiubo.buildstore.util.CollectionsUtils;
import com.jiubo.buildstore.util.FileUtil;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

	@Autowired
	private RoomMainDao roomMainDao;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Integer addRoom(RoomBean bean, MultipartFile[] picture, MultipartFile[] video, MultipartFile headPicture) throws IOException, MessageException {
		RoomMainBean mainBean = roomMainDao.selectById(bean.getRoomId());
		if(bean.getRoomId() == null) {
			throw new MessageException("房源id不能为空");
		}
		bean.setCreateDate(new Date());
		bean.setModifyDate(new Date());
		roomDao.insert(bean);
		if(bean.getId() != null) {
			List<BuildingImgBean> list = new ArrayList<BuildingImgBean>();
			if(picture.length > 0) {
				//添加图片
				for (int i = 0; i < picture.length; i++) {
					MultipartFile file = picture[i];
					Map<String, String> map = FileUtil.uploadFile(file,ImgPathConstant.HOUSE_PATH,mainBean.getId(),ImgTypeConstant.PICTURE);
					BuildingImgBean imgBean = new BuildingImgBean();
					imgBean.setImgName(map.get("name"));
					imgBean.setCreateDate(new Date());
					imgBean.setItId(ImgTypeConstant.PICTURE);
					imgBean.setImgPath(map.get("path"));
					imgBean.setInfoId(mainBean.getId());
					imgBean.setType(ImgTypeConstant.OFFICE_BUILD);
					list.add(imgBean);
				}
				buildingImgDao.insertList(list);
				list.clear();
			}
			//添加视频
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
					imgBean.setType(ImgTypeConstant.OFFICE_BUILD);
					list.add(imgBean);
				}
				buildingImgDao.insertList(list);
				list.clear();
			}
			//头图
			if(headPicture != null) {
				Map<String, String> map = FileUtil.uploadFile(headPicture, ImgPathConstant.HOUSE_PATH,mainBean.getId(),ImgTypeConstant.HEAD_PICTURE);
				BuildingImgBean imgBean = new BuildingImgBean();
				imgBean.setImgName(map.get("name"));
				imgBean.setImgPath(map.get("path"));
				imgBean.setInfoId(mainBean.getId());
				imgBean.setType(ImgTypeConstant.OFFICE_BUILD);
				imgBean.setItId(ImgTypeConstant.HEAD_PICTURE);
				imgBean.setCreateDate(new Date());
				buildingImgDao.insert(imgBean);
				if(mainBean.getRoomImg() != null) {
					FileUtil.delFile(mainBean.getRoomImg());
				}
				mainBean.setRoomImg(imgBean.getImgPath());
				roomMainDao.updateById(mainBean);
			}
		}
		return 1;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Integer updateRoom(RoomBean bean, MultipartFile[] picture, MultipartFile[] video,
			MultipartFile headPicture) throws IOException, MessageException {
		if(bean.getId() == null) {
			throw new MessageException("写字楼id不能为空");
		}
		RoomMainBean mainBean = roomMainDao.selectById(bean.getRoomId());
		bean.setModifyDate(new Date());
		if(bean.getId() != null) {
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
					imgBean.setType(ImgTypeConstant.OFFICE_BUILD);
					list.add(imgBean);
				}
				if(!CollectionsUtils.isEmpty(list)) {
					buildingImgDao.insertList(list);
				}
				list.clear();
			}
			if(video.length > 0) {
				for (int i = 0; i < video.length; i++) {
					MultipartFile file = video[i];
					QueryWrapper<BuildingImgBean> qw = new QueryWrapper<BuildingImgBean>();
					qw.select("*");
					qw.eq("IT_ID", ImgTypeConstant.VIDEO);
					qw.eq("TYPE", ImgTypeConstant.OFFICE_BUILD);
					qw.eq("INFO_ID", mainBean.getId());
					BuildingImgBean imgBean = buildingImgDao.selectOne(qw);
					Map<String, String> map = FileUtil.uploadFile(file,ImgPathConstant.HOUSE_PATH,mainBean.getId(),ImgTypeConstant.VIDEO);
					
					if(imgBean != null) {
						FileUtil.delFile(buildStoreDir+imgBean.getImgPath());
						imgBean.setImgName(map.get("name"));
						imgBean.setImgPath(map.get("path"));
						buildingImgDao.updateById(imgBean);
					}else {
						BuildingImgBean buildingImgBean = new BuildingImgBean();
						buildingImgBean.setImgName(map.get("name"));
						buildingImgBean.setCreateDate(new Date());
						buildingImgBean.setItId(ImgTypeConstant.VIDEO);
						buildingImgBean.setImgPath(map.get("path"));
						buildingImgBean.setInfoId(mainBean.getId());
						buildingImgBean.setType(ImgTypeConstant.OFFICE_BUILD);
						list.add(buildingImgBean);
					}
				}
				if(!CollectionsUtils.isEmpty(list)) {
					buildingImgDao.insertList(list);
				}
				list.clear();
			}
			if(headPicture != null) {
				//TODO
				Map<String, String> map = FileUtil.uploadFile(headPicture, ImgPathConstant.HOUSE_PATH,mainBean.getId(),ImgTypeConstant.HEAD_PICTURE);
				QueryWrapper<BuildingImgBean> qw = new QueryWrapper<BuildingImgBean>();
				qw.select("*");
				qw.eq("IT_ID", ImgTypeConstant.HEAD_PICTURE);
				qw.eq("TYPE", ImgTypeConstant.OFFICE_BUILD);
				qw.eq("INFO_ID", mainBean.getId());
				BuildingImgBean imgBean = buildingImgDao.selectOne(qw);
				if(imgBean != null) {
					FileUtil.delFile(buildStoreDir+imgBean.getImgPath());
					imgBean.setImgName(map.get("name"));
					imgBean.setImgPath(map.get("path"));
					buildingImgDao.updateById(imgBean);
				}else {
					BuildingImgBean buildingImgBean = new BuildingImgBean();
					
					buildingImgBean.setImgName(map.get("name"));
					buildingImgBean.setCreateDate(new Date());
					buildingImgBean.setItId(ImgTypeConstant.HEAD_PICTURE);
					buildingImgBean.setImgPath(map.get("path"));
					buildingImgBean.setInfoId(mainBean.getId());
					buildingImgBean.setType(ImgTypeConstant.OFFICE_BUILD);
					buildingImgDao.insert(buildingImgBean);
				}
				mainBean.setRoomImg(map.get("path"));
				roomMainDao.updateById(mainBean);
			}
		}
		return roomDao.updateById(bean);
	}
}
