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
import com.jiubo.buildstore.util.CollectionsUtils;
import com.jiubo.buildstore.util.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

	@Value("${buildStoreDir}")
	private String buildStoreDir;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Integer addStoreRoom(StoreRoomBean bean, MultipartFile[] picture, MultipartFile[] video,
								MultipartFile[] headPicture) throws IOException {
		RoomMainBean mainBean = roomMainDao.selectById(bean.getRoomId());
		bean.setCreateDate(new Date());
		if (!CollectionsUtils.isEmpty(bean.getSuitable())) {
			bean.setSuitableStore(StringUtils.join(bean.getSuitable(),"|"));
		}
		if (!CollectionsUtils.isEmpty(bean.getProperInfo())) {
			bean.setPropertyInfo(StringUtils.join(bean.getProperInfo(),"|"));
		}
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
					imgBean.setType(ImgTypeConstant.STORE);
					list.add(imgBean);
				}

			}
			if(video != null) {
				for (int i = 0; i < video.length; i++) {
					MultipartFile file = video[i];
					Map<String, String> map = FileUtil.uploadFile(file,ImgPathConstant.HOUSE_PATH,mainBean.getId(),ImgTypeConstant.VIDEO);
					BuildingImgBean imgBean = new BuildingImgBean();
//					imgBean.setImgName(map.get("name"));
					imgBean.setCreateDate(new Date());
					imgBean.setItId(ImgTypeConstant.VIDEO);
					imgBean.setImgPath(map.get("path"));
					imgBean.setInfoId(mainBean.getId());
					imgBean.setType(ImgTypeConstant.STORE);
					list.add(imgBean);
				}
			}
			if (!CollectionsUtils.isEmpty(list)) {
				buildingImgDao.insertList(list);
			}
			if(headPicture != null && headPicture.length > 0) {
				Map<String, String> map = FileUtil.uploadFile(headPicture[0], ImgPathConstant.HOUSE_PATH,mainBean.getId(),ImgTypeConstant.HEAD_PICTURE);
				BuildingImgBean imgBean = new BuildingImgBean();
				imgBean.setImgName(map.get("name"));
				imgBean.setCreateDate(new Date());
				imgBean.setItId(ImgTypeConstant.PICTURE);
				imgBean.setImgPath(map.get("path"));
				imgBean.setInfoId(mainBean.getId());
				imgBean.setType(ImgTypeConstant.STORE);
				mainBean.setRoomImg(imgBean.getImgPath());
				roomMainDao.updateById(mainBean);
			}
		}
		return 1;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Integer updateStoreRoom(StoreRoomBean bean, MultipartFile[] picture, MultipartFile[] video,
			MultipartFile[] headPicture) throws IOException {
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
					imgBean.setType(ImgTypeConstant.STORE);
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
					imgBean.setType(ImgTypeConstant.STORE);
					list.add(imgBean);
				}
				buildingImgDao.insertList(list);
				list.clear();
			}
			if(headPicture != null && headPicture.length > 0) {
				Map<String, String> map = FileUtil.uploadFile(headPicture[0], ImgPathConstant.HOUSE_PATH,mainBean.getId(),ImgTypeConstant.HEAD_PICTURE);
				QueryWrapper<BuildingImgBean> qw = new QueryWrapper<BuildingImgBean>();
				qw.select("*");
				qw.eq("IT_ID", ImgTypeConstant.HEAD_PICTURE);
				qw.eq("TYPE", ImgTypeConstant.STORE);
				qw.eq("INFO_ID", mainBean.getId());
				BuildingImgBean imgBean = buildingImgDao.selectOne(qw);
				if(imgBean != null) {
					imgBean.setImgName(map.get("name"));
					imgBean.setImgPath(map.get("path"));
					buildingImgDao.updateById(imgBean);
				} else {
					BuildingImgBean buildingImgBean = new BuildingImgBean();
					
					imgBean.setImgName(map.get("name"));
					imgBean.setCreateDate(new Date());
					imgBean.setItId(ImgTypeConstant.PICTURE);
					imgBean.setImgPath(map.get("path"));
					imgBean.setInfoId(mainBean.getId());
					imgBean.setType(ImgTypeConstant.STORE);
					buildingImgDao.insert(buildingImgBean);
				}

				mainBean.setRoomImg(imgBean.getImgPath());
				roomMainDao.updateById(mainBean);
			}
		}
		return storeRoomDao.updateById(bean);
	}

	@Override
	public StoreRoomBean getStoreByRoomId(Integer roomId) {
		QueryWrapper<StoreRoomBean> qw = new QueryWrapper<StoreRoomBean>();
		qw.select("*");
		qw.eq("room_id", roomId);
		StoreRoomBean bean = storeRoomDao.selectOne(qw);
		if (null != bean) {
			if (StringUtils.isNotBlank(bean.getPropertyInfo())){
				String[] split = bean.getPropertyInfo().split("\\|");
				List<Integer> idList = arrToList(split);
				bean.setProperInfo(idList);
			}
			if (StringUtils.isNotBlank(bean.getSuitableStore())) {
				String[] split = bean.getSuitableStore().split("\\|");
				List<Integer> idList = arrToList(split);
				bean.setSuitable(idList);
			}

			// 图片路径
			QueryWrapper<BuildingImgBean> que = new QueryWrapper<BuildingImgBean>();
			que.select("*");
			que.eq("TYPE", ImgTypeConstant.STORE);
			que.eq("INFO_ID", roomId);
			List<BuildingImgBean> pictureList = buildingImgDao.selectList(que);

			Map<Integer, List<BuildingImgBean>> picMap = null;
			if (!CollectionsUtils.isEmpty(pictureList)) {
				picMap = pictureList.stream().collect(Collectors.groupingBy(BuildingImgBean::getItId));
			}

			if (null != picMap) {
				List<BuildingImgBean> imgBeans = picMap.get(ImgTypeConstant.PICTURE);
				if (!CollectionsUtils.isEmpty(imgBeans)) {
					List<String> pathList = getPathList(pictureList);
					bean.setPicList(pathList);
				}
				List<BuildingImgBean> buildingImgBeans = picMap.get(ImgTypeConstant.HEAD_PICTURE);
				if (!CollectionsUtils.isEmpty(buildingImgBeans)) {
					List<String> pathList = getPathList(buildingImgBeans);
					bean.setHeadPath(pathList.get(0));
				}
			}

		}
		return bean;
	}

	/**
	 * 拼接图片路径（后台管理）
	 *
	 * @param imgBeans
	 * @return
	 */
	private List<String> getPathList(List<BuildingImgBean> imgBeans) {
		List<String> pathList = new ArrayList<>();
		for (BuildingImgBean buildingImgBean : imgBeans) {
			String path = ImgPathConstant.INTERFACE_PATH.concat(buildStoreDir).concat(buildingImgBean.getImgPath()).concat("&imgId=").concat(buildingImgBean.getImgId().toString());
			pathList.add(path);
		}
		return pathList;
	}

	private List<Integer> arrToList(String[] split) {
		ArrayList< String> arrayList = new ArrayList<String>(split.length);
		Collections.addAll(arrayList, split);
		List<Integer> idList = new ArrayList<>();
		for (String s : arrayList) {
			int anInt = Integer.parseInt(s);
			idList.add(anInt);
		}
		return idList;
	}

}
