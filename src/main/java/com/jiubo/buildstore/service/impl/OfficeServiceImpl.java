package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiubo.buildstore.bean.*;
import com.jiubo.buildstore.common.ImgPathConstant;
import com.jiubo.buildstore.common.ImgTypeConstant;
import com.jiubo.buildstore.dao.*;
import com.jiubo.buildstore.service.OfficeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiubo.buildstore.util.CollectionsUtils;
import com.jiubo.buildstore.util.DateUtils;
import com.jiubo.buildstore.util.FileUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author swd
 * @since 2020-09-13
 */
@Service
public class OfficeServiceImpl extends ServiceImpl<OfficeDao, OfficeBean> implements OfficeService {

	@Autowired
	private OfficeDao officeDao;
	@Autowired
	private BuildingImgDao buildingImgDao;
	@Autowired
	private RoomMainDao roomMainDao;
	@Autowired
	private CounselorDao counselorDao;
	@Autowired
	private BuildingDao buildingDao;

	@Autowired
	private BuildingTypeDao buildingTypeDao;

	@Value("${buildStoreDir}")
	private String buildStoreDir;
	@Override
	public OfficeBean getOfficeByPk(Integer id) {
		OfficeBean bean = officeDao.selectById(id);
		if (null != bean) {

			QueryWrapper<RoomMainBean> qw = new QueryWrapper<RoomMainBean>();
			qw.select("*");
			qw.eq("id", bean.getRoomId());
			List<RoomMainBean> roomMainBeans = roomMainDao.selectList(qw);

			if (!CollectionsUtils.isEmpty(roomMainBeans)) {
				bean.setRoomMainBean(roomMainBeans.get(0));
				if(roomMainBeans.get(0).getBuildId() != null) {
					BuildingBean buildingBean = buildingDao.selectById(roomMainBeans.get(0).getBuildId());
					if(buildingBean != null) {
						bean.setBuildName(buildingBean.getHtName());
						bean.setMinTotalPrice(buildingBean.getMinTitlePrice());
					}
				}
			}
			if(bean.getCouId() != null) {
				CounselorBean counselorBean = counselorDao.selectById(bean.getCouId());
				if(counselorBean != null) {
					bean.setCouBane(counselorBean);
				}
			}

			QueryWrapper<BuildingImgBean> qwP = new QueryWrapper<BuildingImgBean>();
			qwP.select("*");
			qwP.eq("IT_ID", 2);
			qwP.eq("TYPE", 4);
			qwP.eq("INFO_ID", bean.getId());
			List<BuildingImgBean> pictureList = buildingImgDao.selectList(qwP);
			if (!CollectionsUtils.isEmpty(pictureList)) {
				List<String> list = pictureList.stream().map(BuildingImgBean::getImgPath).collect(Collectors.toList());
				bean.setPicList(list);
			}
			BigDecimal decimal = new BigDecimal(bean.getStationNum().toString());
			bean.setUnitPrice(bean.getNowPrice().divide(decimal, 1, BigDecimal.ROUND_HALF_UP));
		}
		return bean;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void addOffice(OfficeBean officeBean, MultipartFile headImg, MultipartFile[] picture, MultipartFile video)
			throws IOException {
		officeDao.insert(officeBean);
		List<BuildingImgBean> buildingImgBeans = new ArrayList<>();

		// 头图
		if (null != headImg) {
			Map<String, String> headMap = FileUtil.uploadFile(headImg, ImgPathConstant.OFFICE, officeBean.getId(),
					ImgTypeConstant.HEAD_PICTURE);
			BuildingImgBean imgBean = new BuildingImgBean();
			imgBean.setImgName(headMap.get("name"));
			imgBean.setCreateDate(new Date());
			imgBean.setItId(ImgTypeConstant.HEAD_PICTURE);
			imgBean.setImgPath(headMap.get("path"));
			imgBean.setInfoId(officeBean.getId());
			imgBean.setType(ImgTypeConstant.OFFICE);
			buildingImgBeans.add(imgBean);
			officeDao.updateById(officeBean.setImgName(headMap.get("path")));
		}

		// 视频
		if (null != video) {
			addFile(officeBean, video, buildingImgBeans, ImgTypeConstant.VIDEO);
		}

		if (null != picture) {
			for (MultipartFile file : picture) {
				addFile(officeBean, file, buildingImgBeans, ImgTypeConstant.PICTURE);
			}
		}
		if (!CollectionsUtils.isEmpty(buildingImgBeans)) {
			buildingImgDao.insertList(buildingImgBeans);
		}

	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void patchOffice(OfficeBean officeBean, MultipartFile headImg, MultipartFile[] picture, MultipartFile video)
			throws IOException {

		List<BuildingImgBean> buildingImgBeans = new ArrayList<>();
		if (null != headImg) {
			deleteImgByCon(officeBean, ImgTypeConstant.HEAD_PICTURE, ImgTypeConstant.OFFICE);
			Map<String, String> headMap = FileUtil.uploadFile(headImg, ImgPathConstant.OFFICE, officeBean.getId(),
					ImgTypeConstant.HEAD_PICTURE);
			BuildingImgBean imgBean = new BuildingImgBean();
			imgBean.setImgName(headMap.get("name"));
			imgBean.setCreateDate(new Date());
			imgBean.setItId(ImgTypeConstant.HEAD_PICTURE);
			imgBean.setImgPath(headMap.get("path"));
			imgBean.setInfoId(officeBean.getId());
			imgBean.setType(ImgTypeConstant.OFFICE);
			buildingImgBeans.add(imgBean);
			officeBean.setImgName(headMap.get("path"));
		}
		officeDao.updateById(officeBean);
		// 视频
		if (null != video) {
			addFile(officeBean, video, buildingImgBeans, ImgTypeConstant.VIDEO);
		}

		if (null != picture) {
			for (MultipartFile file : picture) {
				addFile(officeBean, file, buildingImgBeans, ImgTypeConstant.PICTURE);
			}
		}
		buildingImgDao.insertList(buildingImgBeans);
	}

	public void deleteImgByCon(OfficeBean officeBean, Integer itId, Integer type) {
		QueryWrapper<BuildingImgBean> qwRoom = new QueryWrapper<BuildingImgBean>();
		qwRoom.select("*");
		qwRoom.eq("IT_ID", itId);
		qwRoom.eq("TYPE", type);
		qwRoom.eq("INFO_ID", officeBean.getId());
		buildingImgDao.delete(qwRoom);
	}

	private void addFile(OfficeBean officeBean, MultipartFile video, List<BuildingImgBean> buildingImgBeans,
			Integer video2) throws IOException {
		Map<String, String> videoMap = FileUtil.uploadFile(video, ImgPathConstant.OFFICE, officeBean.getId(), video2);
		BuildingImgBean videoBean = new BuildingImgBean();
		videoBean.setImgName(videoMap.get("name"));
		videoBean.setCreateDate(new Date());
		videoBean.setItId(video2);
		videoBean.setImgPath(videoMap.get("path"));
		videoBean.setInfoId(officeBean.getId());
		videoBean.setType(ImgTypeConstant.OFFICE);
		buildingImgBeans.add(videoBean);
	}

	@Override
	public PageInfo<OfficeBean> getAllOffice(OfficeBean officeBean) {
		Integer pageNum = StringUtils.isBlank(officeBean.getCurrent()) ? 1 : Integer.valueOf(officeBean.getCurrent());
		Integer pageSize = StringUtils.isBlank(officeBean.getPageSize()) ? 10
				: Integer.valueOf(officeBean.getPageSize());
		PageHelper.startPage(pageNum, pageSize);
		QueryWrapper<OfficeBean> queryWrapper = new QueryWrapper<OfficeBean>();
		queryWrapper.select("*");
		if (officeBean.getRoomId() != null) {
			queryWrapper.eq("room_id", officeBean.getRoomId());
		}
		if (officeBean.getOfficeType() != null) {
			queryWrapper.eq("office_type", officeBean.getOfficeType());
		}
		if (officeBean.getIsWall() != null) {
			queryWrapper.eq("is_wall", officeBean.getIsWall());
		}
		if (officeBean.getIsWindow() != null) {
			queryWrapper.eq("is_window", officeBean.getIsWindow());
		}
		queryWrapper.orderByDesc("create_date");
		List<OfficeBean> list = officeDao.selectList(queryWrapper);

		// 类型
		QueryWrapper<BuildingTypeBean> wrBt = new QueryWrapper<BuildingTypeBean>();
		wrBt.select("*");
		wrBt.eq("TYPE", 2);
		List<BuildingTypeBean> btList = buildingTypeDao.selectList(wrBt);
		Map<Integer, List<BuildingTypeBean>> btMap = null;
		if (!CollectionsUtils.isEmpty(btList)) {
			btMap = btList.stream().collect(Collectors.groupingBy(BuildingTypeBean::getBtId));
		}
		for (int i = 0; i < list.size(); i++) {
			officeBean = list.get(i);
			RoomMainBean mainBean = roomMainDao.selectById(officeBean.getRoomId());
			QueryWrapper<BuildingImgBean> wrapperV = new QueryWrapper<BuildingImgBean>();
			wrapperV.select("*");
			wrapperV.eq("IT_ID", ImgTypeConstant.VIDEO);
			wrapperV.eq("TYPE", ImgTypeConstant.OFFICE);
			wrapperV.eq("INFO_ID", officeBean.getId());
			List<BuildingImgBean> listV = buildingImgDao.selectList(wrapperV);
			if(listV != null && listV.size()>0) {
				officeBean.setVideoPath(ImgPathConstant.INTERFACE_PATH.concat(buildStoreDir).concat(listV.get(0).getImgPath()).concat("&imgId=").concat(listV.get(0).getImgId().toString()));
			}


			QueryWrapper<BuildingImgBean> wrapperPs = new QueryWrapper<BuildingImgBean>();
			wrapperPs.select("*");
			wrapperPs.eq("IT_ID", ImgTypeConstant.PICTURE);
			wrapperPs.eq("TYPE", ImgTypeConstant.OFFICE);
			wrapperPs.eq("INFO_ID", officeBean.getId());
			List<BuildingImgBean> listPs = buildingImgDao.selectList(wrapperPs);
			if(listPs != null && listPs.size()>0) {
				List<String> strings = getPathList(listPs);
				officeBean.setPictureList(strings);
			}

			QueryWrapper<BuildingImgBean> wrapperHead = new QueryWrapper<BuildingImgBean>();
			wrapperHead.select("*");
			wrapperHead.eq("IT_ID", ImgTypeConstant.HEAD_PICTURE);
			wrapperHead.eq("TYPE", ImgTypeConstant.OFFICE);
			wrapperHead.eq("INFO_ID", officeBean.getId());
			List<BuildingImgBean> listHead = buildingImgDao.selectList(wrapperPs);
			if(listHead != null && listHead.size()>0) {
				officeBean.setImgName(ImgPathConstant.INTERFACE_PATH.concat(buildStoreDir).concat(listHead.get(0).getImgPath()).concat("&imgId=").concat(listHead.get(0).getImgId().toString()));
			}

			officeBean.setRoomName(mainBean.getRoom());

			if (null != btMap) {
				List<BuildingTypeBean> typeBeans = btMap.get(officeBean.getOfficeType());
				if (!CollectionsUtils.isEmpty(typeBeans)) {
					officeBean.setOfficeTypeName(typeBeans.get(0).getBtName());
				}
			}

			if (officeBean.getIsWall() == 2) {
				officeBean.setIsWallName("是");
			}
			if (officeBean.getIsWall() == 3) {
				officeBean.setIsWallName("否");
			}
			if (officeBean.getIsWindow() == 2) {
				officeBean.setIsWindowName("是");
			}
			if (officeBean.getIsWindow() == 3) {
				officeBean.setIsWindowName("否");
			}
			if (officeBean.getCreateDate() != null) {
				officeBean.setCreateTime(DateUtils.formatDate(officeBean.getCreateDate(), "yyyy-MM-dd"));
			}
		}
		PageInfo<OfficeBean> pageInfo = new PageInfo<OfficeBean>(list);
		return pageInfo;
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
}
