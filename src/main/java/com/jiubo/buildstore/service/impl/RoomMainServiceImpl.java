package com.jiubo.buildstore.service.impl;
import com.jiubo.buildstore.bean.AreaBean;
import com.jiubo.buildstore.bean.BuildingImgBean;
import com.jiubo.buildstore.bean.CounselorBean;
import com.jiubo.buildstore.bean.RoomMainBean;
import com.jiubo.buildstore.bean.RoomReceive;
import com.jiubo.buildstore.bean.TotlePriceTypeBean;
import com.jiubo.buildstore.bean.UnitPriceTypeBean;
import com.jiubo.buildstore.dao.AreaDao;
import com.jiubo.buildstore.dao.BuildingImgDao;
import com.jiubo.buildstore.dao.CounselorDao;
import com.jiubo.buildstore.dao.RoomMainDao;
import com.jiubo.buildstore.dao.TotlePriceTypeDao;
import com.jiubo.buildstore.dao.UnitPriceTypeDao;
import com.jiubo.buildstore.bean.*;
import com.jiubo.buildstore.dao.*;
import com.jiubo.buildstore.service.RoomMainService;
import com.jiubo.buildstore.util.CollectionsUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 房源主表 服务实现类
 * </p>
 *
 * @author swd
 * @since 2020-09-12
 */
@Service
public class RoomMainServiceImpl extends ServiceImpl<RoomMainDao, RoomMainBean> implements RoomMainService {
	
	@Autowired
	private RoomMainDao roomMainDao;
	
	@Autowired
	private AreaDao areaDao;

	@Autowired
	private UnitPriceTypeDao unitPriceTypeDao;
	
	@Autowired
	private TotlePriceTypeDao totlePriceTypeDao;
	
	@Autowired
	private CounselorDao counselorDao;
	
	@Autowired
	private BuildingImgDao buildingImgDao;
	

	@Autowired
	private OfficeDao officeDao;

	@Autowired
	private OpenRoomDao openRoomDao;
	
	@Autowired
	private RoomDao roomDao;
	
	@Autowired
	private StoreRoomDao storeRoomDao;
	
	private ShareRoomDao shareRoomDao;
	@Override
	public PageInfo<RoomMainBean> getRoomByConditions(RoomReceive receive) {
		Integer pageNum = StringUtils.isBlank(receive.getCurrent()) ? 1 : Integer.valueOf(receive.getCurrent());
		Integer pageSize = StringUtils.isBlank(receive.getPageSize()) ? 10 : Integer.valueOf(receive.getPageSize());
		setCondition(receive);

		// 房源数据
		PageHelper.startPage(pageNum,pageSize);
		List<RoomMainBean> allRoomBypage = roomMainDao.getAllRoomBypage(receive);

		if (!CollectionsUtils.isEmpty(allRoomBypage)) {

			// 遍历实体 翻译各个类型字段
//			for (RoomMainBean bean : allRoomBypage) {
//
//				// 是否是热销标签
//				if (bean.getIsHot() != null) {
//					bean.setIsHot(2);
//				} else {
//					bean.setIsHot(3);
//				}
//
//			}
		}
		PageInfo<RoomMainBean> page = new PageInfo<RoomMainBean>(allRoomBypage);
		return page;
	}

	@Override
	public RMChildSharedBean getSharedById(Integer id) {
		RMChildSharedBean roomMainBean = roomMainDao.getSharedById(id);
		if (null != roomMainBean) {
			//办公室信息
			QueryWrapper<OfficeBean> qwP = new QueryWrapper<OfficeBean>();
			qwP.select("*");
			qwP.eq("room_id", roomMainBean.getId());
			List<OfficeBean> officeBeanList = officeDao.selectList(qwP);
			// 网点信息
			ShareRoomBean shareRoomBean = shareRoomDao.getShareRoomByRoomId(roomMainBean.getId());
			if (null != shareRoomBean) {
				String chaList = shareRoomBean.getChaList();
				if (StringUtils.isNotBlank(chaList)) {
					String[] split = chaList.split("\\|");
					ArrayList< String> arrayList = new ArrayList<String>(split.length);
					Collections.addAll(arrayList, split);
					shareRoomBean.setChList(arrayList);
				}
			}
			// 图片路径
			QueryWrapper<BuildingImgBean> qw = new QueryWrapper<BuildingImgBean>();
			qw.select("*");
			qw.eq("IT_ID", 2);
			qw.eq("TYPE", 2);
			qw.eq("INFO_ID", roomMainBean.getId());
			List<BuildingImgBean> pictureList = buildingImgDao.selectList(qw);
			if (!CollectionsUtils.isEmpty(pictureList)) {
				List<String> list = pictureList.stream().map(BuildingImgBean::getImgPath).collect(Collectors.toList());
				roomMainBean.setPictureList(list);
			}
			roomMainBean.setOfficeBeanList(officeBeanList);
			// 网点信息
			roomMainBean.setShareRoomBean(shareRoomBean);
			return roomMainBean;
		}
		return null;
	}


	private void setCondition(RoomReceive roomReceive) {
		// 获取面积集合
		List<Integer> areaIdList = roomReceive.getAreaIdList();
		List<Map<String, Object>> areaList = roomReceive.getAreaList();
		if (null != areaIdList && areaIdList.size() > 0) {
			List<AreaBean> areaByIdList = areaDao.getAreaByIdList(new AreaBean().setIdList(areaIdList));
			areaList = new ArrayList<>();
			for (AreaBean areaBean : areaByIdList) {
				Map<String, Object> map = new HashMap<>();
				map.put("minArea", areaBean.getBegArea());
				map.put("maxArea", areaBean.getEndArea());
				areaList.add(map);
			}
			roomReceive.setAreaList(areaList);
		}

		// 获取单价集合
		List<Integer> unitPriceIdList = roomReceive.getUnitPriceIdList();
		List<Map<String, Object>> unitPriceList = roomReceive.getUnitPriceList();
		if (!CollectionsUtils.isEmpty(unitPriceIdList)) {
			List<UnitPriceTypeBean> priceByIdList = unitPriceTypeDao
					.getUnitPriceByIdList(new UnitPriceTypeBean().setIdList(unitPriceIdList));
			unitPriceList = new ArrayList<>();
			for (UnitPriceTypeBean unitPriceTypeBean : priceByIdList) {
				Map<String, Object> map = new HashMap<>();
				map.put("minUnitPrice", unitPriceTypeBean.getBegPrice());
				map.put("maxUnitPrice", unitPriceTypeBean.getEndPrice());
				unitPriceList.add(map);
			}
			roomReceive.setUnitPriceList(unitPriceList);
		}
		
		// 获取总价集合
				List<Integer> totalPriceIdList = roomReceive.getTotalPriceIdList();
				List<Map<String, Object>> totalPriceList = roomReceive.getUnitPriceList();
				if (!CollectionsUtils.isEmpty(totalPriceIdList)) {
					List<TotlePriceTypeBean> priceByIdList = totlePriceTypeDao
							.getTotalPriceByIdList(new TotlePriceTypeBean().setIdList(totalPriceIdList));
					totalPriceList = new ArrayList<>();
					for (TotlePriceTypeBean totlePriceTypeBean : priceByIdList) {
						Map<String, Object> map = new HashMap<>();
						map.put("minUnitPrice", totlePriceTypeBean.getBegPrice());
						map.put("maxUnitPrice", totlePriceTypeBean.getEndPrice());
						totalPriceList.add(map);
					}
					roomReceive.setUnitPriceList(totalPriceList);
				}

	}



	@Override
	public Map<String, Object> getRoomDetails(Integer roomMainId) {
		RoomMainBean mainBean = roomMainDao.selectById(roomMainId);
		QueryWrapper<RoomBean> qwRoom = new QueryWrapper<RoomBean>();
		qwRoom.select("*");
		qwRoom.eq("room_id", roomMainId);
		RoomBean roomBean = roomDao.selectOne(qwRoom);
		CounselorBean counselorBean = counselorDao.selectById(mainBean.getCouId());
		QueryWrapper<CounselorBean> queryWrapper = new QueryWrapper<CounselorBean>();
		queryWrapper.select("*");
		List<CounselorBean> list = counselorDao.selectList(queryWrapper);
		QueryWrapper<BuildingImgBean> qwP = new QueryWrapper<BuildingImgBean>();
		qwP.select("*");
		qwP.eq("IT_ID", 2);
		qwP.eq("TYPE", 2);
		qwP.eq("INFO_ID", roomMainId);
		List<BuildingImgBean> pictureList = buildingImgDao.selectList(qwP);
		QueryWrapper<BuildingImgBean> qwV = new QueryWrapper<BuildingImgBean>();
		qwV.select("*");
		qwV.eq("IT_ID", 3);
		qwV.eq("TYPE", 2);
		qwV.eq("INFO_ID", roomMainId);
		List<BuildingImgBean> videoList = buildingImgDao.selectList(qwV);
		
		Map<String, Object> result = new  HashMap<String, Object>();
		result.put("roomDetail", roomBean);
		result.put("roomDetail", mainBean);
		result.put("counselor", counselorBean);
		result.put("allCounselor", list);
		result.put("picture", pictureList);
		result.put("video", videoList);
		return result;
	}

	@Override
	public Map<String, Object> getStoneDetail(Integer roomMainId) {
		RoomMainBean mainBean = roomMainDao.selectById(roomMainId);
		QueryWrapper<StoreRoomBean> stRoom = new QueryWrapper<StoreRoomBean>();
		stRoom.select("*");
		stRoom.eq("room_id", roomMainId);
		StoreRoomBean storeRoomBean = storeRoomDao.selectOne(stRoom);
		CounselorBean counselorBean = counselorDao.selectById(mainBean.getCouId());
		QueryWrapper<CounselorBean> queryWrapper = new QueryWrapper<CounselorBean>();
		queryWrapper.select("*");
		List<CounselorBean> list = counselorDao.selectList(queryWrapper);
		QueryWrapper<BuildingImgBean> qwP = new QueryWrapper<BuildingImgBean>();
		qwP.select("*");
		qwP.eq("IT_ID", 2);
		qwP.eq("TYPE", 2);
		qwP.eq("INFO_ID", roomMainId);
		List<BuildingImgBean> pictureList = buildingImgDao.selectList(qwP);
		QueryWrapper<BuildingImgBean> qwV = new QueryWrapper<BuildingImgBean>();
		qwV.select("*");
		qwV.eq("IT_ID", 3);
		qwV.eq("TYPE", 2);
		qwV.eq("INFO_ID", roomMainId);
		List<BuildingImgBean> videoList = buildingImgDao.selectList(qwV);
		
		Map<String, Object> result = new  HashMap<String, Object>();
		result.put("stRoomDetail", storeRoomBean);
		result.put("roomDetail", mainBean);
		result.put("counselor", counselorBean);
		result.put("allCounselor", list);
		result.put("picture", pictureList);
		result.put("video", videoList);
		return result;
	}
}
