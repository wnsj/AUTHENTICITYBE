package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.dao.AreaDao;
import com.jiubo.buildstore.dao.BuildingImgDao;
import com.jiubo.buildstore.dao.CounselorDao;
import com.jiubo.buildstore.dao.RoomMainDao;
import com.jiubo.buildstore.dao.TotlePriceTypeDao;
import com.jiubo.buildstore.dao.UnitPriceTypeDao;
import com.jiubo.buildstore.Exception.MessageException;
import com.jiubo.buildstore.bean.*;
import com.jiubo.buildstore.common.ImgTypeConstant;
import com.jiubo.buildstore.dao.*;
import com.jiubo.buildstore.service.RoomMainService;
import com.jiubo.buildstore.util.CollectionsUtils;
import com.jiubo.buildstore.util.DateUtils;
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
import org.springframework.transaction.annotation.Transactional;

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
	private RoomDao roomDao;

	@Autowired
	private LocationDistinguishDao locationDistinguishDao;

	@Autowired
	private BusinessDistrictDao businessDistrictDao;

	@Autowired
	private BuildingDao buildingDao;

	@Autowired
	private StoreRoomDao storeRoomDao;

	@Autowired
	private ShareRoomDao shareRoomDao;

	@Autowired
	private BaseServiceDao baseServiceDao;

	@Autowired
	private BuildingTypeDao buildingTypeDao;

	@Autowired
	private CommercialActivitieDao commercialActivitieDao;

	@Autowired
	private PropertyInfoDao propertyInfoDao;

	@Override
	public PageInfo<RoomMainBean> getRoomByConditions(RoomReceive receive) {
		Integer pageNum = StringUtils.isBlank(receive.getCurrent()) ? 1 : Integer.valueOf(receive.getCurrent());
		Integer pageSize = StringUtils.isBlank(receive.getPageSize()) ? 10 : Integer.valueOf(receive.getPageSize());
		setCondition(receive);

		// 如果是通过名字搜索的房源根据名字取查对应的区域商圈
		if (!StringUtils.isBlank(receive.getNameLike())) {
			QueryWrapper<LocationDistinguishBean> wrapperLd = new QueryWrapper<LocationDistinguishBean>();
			wrapperLd.select("*");
			wrapperLd.like("LD_NAME", receive.getNameLike());
			List<LocationDistinguishBean> ld = locationDistinguishDao.selectList(wrapperLd);
			List<Integer> ldList = ld.stream().map(LocationDistinguishBean::getLdId).collect(Collectors.toList());
			if (receive.getLdIdList() == null) {
				List<Integer> ldIdList = new ArrayList<Integer>();
				receive.setLdIdList(ldIdList);
			}
			receive.getLdIdList().addAll(ldList);
			if (receive.getLdIdList() == null || receive.getLdIdList().size() == 0) {
				QueryWrapper<BusinessDistrictBean> wrapperBd = new QueryWrapper<BusinessDistrictBean>();
				wrapperBd.select("*");
				wrapperBd.like("bu_name", receive.getNameLike());
				List<BusinessDistrictBean> bd = businessDistrictDao.selectList(wrapperBd);
				List<Integer> bdList = bd.stream().map(BusinessDistrictBean::getId).collect(Collectors.toList());
				if (receive.getBdIdList() == null) {
					List<Integer> bdIdList = new ArrayList<Integer>();
					receive.setBdIdList(bdIdList);
				}
				receive.getBdIdList().addAll(bdList);
				if (receive.getBdIdList() == null || receive.getBdIdList().size() == 0) {
					QueryWrapper<BuildingBean> wrapperb = new QueryWrapper<BuildingBean>();
					wrapperb.select("*");
					wrapperb.like("HT_NAME", receive.getNameLike());
					List<BuildingBean> b = buildingDao.selectList(wrapperb);
					List<Integer> bList = b.stream().map(BuildingBean::getBuildId).collect(Collectors.toList());
					if (receive.getBuildIdList() == null) {
						List<Integer> bIdList = new ArrayList<Integer>();
						receive.setBuildIdList(bIdList);
					}
					receive.getBuildIdList().addAll(bList);
					if (receive.getBuildIdList() == null || receive.getBuildIdList().size() == 0) {
						return null;
					}
				}
			}

		}

		// 如果商铺业态不为null
		if (!StringUtils.isBlank(receive.getCaId())) {
			String[] strs = receive.getCaId().split(",");
			for (int i = 0; i < strs.length; i++) {
				List<Integer> caIdList = new ArrayList<Integer>();
				receive.setCaIdList(caIdList);
				receive.getCaIdList().add(Integer.valueOf(strs[i]));
			}
		}

		// 房源数据
		PageHelper.startPage(pageNum, pageSize);
		List<RoomMainBean> allRoomBypage = roomMainDao.getAllRoomBypage(receive);

		if (!CollectionsUtils.isEmpty(allRoomBypage)) {

			// 遍历实体 翻译各个类型字段
			for (RoomMainBean bean : allRoomBypage) {
				
				if (bean.getRoomType() == 1) {
					QueryWrapper<BuildingImgBean> wrapper = new QueryWrapper<BuildingImgBean>();
					wrapper.select("*");
					wrapper.eq("IT_ID", ImgTypeConstant.VIDEO);
					wrapper.eq("TYPE", ImgTypeConstant.OFFICE_BUILD);
					wrapper.eq("INFO_ID", bean.getId());
					List<BuildingImgBean> imgBeans = buildingImgDao.selectList(wrapper);
					if (imgBeans != null) {
						bean.setIsVideo(2);
					} else {
						bean.setIsVideo(3);
					}
				}

				if (bean.getRoomType() == 3) {
					QueryWrapper<BuildingImgBean> wrapper = new QueryWrapper<BuildingImgBean>();
					wrapper.select("*");
					wrapper.eq("IT_ID", ImgTypeConstant.VIDEO);
					wrapper.eq("TYPE", ImgTypeConstant.STORE);
					wrapper.eq("INFO_ID", bean.getId());
					List<BuildingImgBean> imgBeans = buildingImgDao.selectList(wrapper);
					if (imgBeans != null) {
						bean.setIsVideo(2);
					} else {
						bean.setIsVideo(3);
					}
				}

				// 查询区域名称
				if (bean.getLdId() != null) {
					bean.setLdName(locationDistinguishDao.selectById(bean.getLdId()).getLdName());
				}
				// 查询楼盘名称
				if (bean.getBuildId() != null) {
					bean.setBuildName(buildingDao.selectById(bean.getBuildId()).getHtName());
				}
				// 查询商圈名称
				if (bean.getBusinessId() != null) {
					bean.setBussinessName(businessDistrictDao.selectById(bean.getBusinessId()).getBuName());
				}
				// 查询类型名称
				if (bean.getBtId() != null) {
					bean.setTypeName(buildingTypeDao.selectById(bean.getBtId()).getBtName());
				}
				// 时间
				if (bean.getCreateDate() != null) {
					bean.setCreateTime(DateUtils.formatDate(bean.getCreateDate(), "yyyy-MM-dd"));
				}
				// 将标签字符串打成集合
				if (bean.getLabelList() != null) {
					String[] strings = bean.getLabelList().split("\\|");
					ArrayList<String> arrayList = new ArrayList<String>(strings.length);
					Collections.addAll(arrayList, strings);
					bean.setLabels(arrayList);
				}
			}
		}
		PageInfo<RoomMainBean> page = new PageInfo<RoomMainBean>(allRoomBypage);
		return page;
	}

	@Override
	public PageInfo<RoomMainBean> getRoomByConditionsBe(RoomReceive receive) {
		Integer pageNum = StringUtils.isBlank(receive.getCurrent()) ? 1 : Integer.valueOf(receive.getCurrent());
		Integer pageSize = StringUtils.isBlank(receive.getPageSize()) ? 10 : Integer.valueOf(receive.getPageSize());
		setCondition(receive);

		// 如果是通过名字搜索的房源根据名字取查对应的区域商圈
		if (!StringUtils.isBlank(receive.getNameLike())) {
			QueryWrapper<LocationDistinguishBean> wrapperLd = new QueryWrapper<LocationDistinguishBean>();
			wrapperLd.select("*");
			wrapperLd.like("LD_NAME", receive.getNameLike());
			List<LocationDistinguishBean> ld = locationDistinguishDao.selectList(wrapperLd);
			List<Integer> ldList = ld.stream().map(LocationDistinguishBean::getLdId).collect(Collectors.toList());
			if (receive.getLdIdList() == null) {
				List<Integer> ldIdList = new ArrayList<Integer>();
				receive.setLdIdList(ldIdList);
			}
			receive.getLdIdList().addAll(ldList);
			if (receive.getLdIdList() == null || receive.getLdIdList().size() == 0) {
				QueryWrapper<BusinessDistrictBean> wrapperBd = new QueryWrapper<BusinessDistrictBean>();
				wrapperBd.select("*");
				wrapperBd.like("bu_name", receive.getNameLike());
				List<BusinessDistrictBean> bd = businessDistrictDao.selectList(wrapperBd);
				List<Integer> bdList = bd.stream().map(BusinessDistrictBean::getId).collect(Collectors.toList());
				if (receive.getBdIdList() == null) {
					List<Integer> bdIdList = new ArrayList<Integer>();
					receive.setBdIdList(bdIdList);
				}
				receive.getBdIdList().addAll(bdList);
				if (receive.getBdIdList() == null || receive.getBdIdList().size() == 0) {
					QueryWrapper<BuildingBean> wrapperb = new QueryWrapper<BuildingBean>();
					wrapperb.select("*");
					wrapperb.like("HT_NAME", receive.getNameLike());
					List<BuildingBean> b = buildingDao.selectList(wrapperb);
					List<Integer> bList = b.stream().map(BuildingBean::getBuildId).collect(Collectors.toList());
					if (receive.getBuildIdList() == null) {
						List<Integer> bIdList = new ArrayList<Integer>();
						receive.setBuildIdList(bIdList);
					}
					receive.getBuildIdList().addAll(bList);
					if(receive.getBuildIdList() == null || receive.getBuildIdList().size() == 0) {
						return null;
					}
				}
			}

		}

		// 如果商铺业态不为null
		if (!StringUtils.isBlank(receive.getCaId())) {
			String[] strs = receive.getCaId().split(",");
			for (int i = 0; i < strs.length; i++) {
				List<Integer> caIdList = new ArrayList<Integer>();
				receive.setCaIdList(caIdList);
				receive.getCaIdList().add(Integer.valueOf(strs[i]));
			}
		}

		// 房源数据
		PageHelper.startPage(pageNum, pageSize);
		List<RoomMainBean> allRoomBypage = roomMainDao.getAllRoomByPageBe(receive);

		if (!CollectionsUtils.isEmpty(allRoomBypage)) {

			// 遍历实体 翻译各个类型字段
			for (RoomMainBean bean : allRoomBypage) {
				
				QueryWrapper<ShareRoomBean> wrapperShare = new QueryWrapper<ShareRoomBean>();
				wrapperShare.select("*");
				wrapperShare.eq("room_id", bean.getId());
				List<ShareRoomBean> listShare = shareRoomDao.selectList(wrapperShare);
				if(!CollectionsUtils.isEmpty(listShare)) {
					ShareRoomBean shareRoomBean = listShare.get(0);
					if(!StringUtils.isBlank(shareRoomBean.getChaList())) {
						String[] strShare = shareRoomBean.getChaList().split("\\|");
						List<Integer> idShare = new ArrayList<Integer>();
						for (int i = 0; i < strShare.length; i++) {
							idShare.add(Integer.valueOf(strShare[i]));
						}
						bean.setChaList(idShare);
					}
				}
				
				if(bean.getRoomType() == 1) {
					QueryWrapper<BuildingImgBean> wrapper = new QueryWrapper<BuildingImgBean>();
					wrapper.select("*");
					wrapper.eq("IT_ID", ImgTypeConstant.VIDEO);
					wrapper.eq("TYPE", ImgTypeConstant.OFFICE_BUILD);
					wrapper.eq("INFO_ID", bean.getId());
					List<BuildingImgBean> imgBeans = buildingImgDao.selectList(wrapper);
					if(imgBeans != null ) {
						bean.setIsVideo(2);
					}else {
						bean.setIsVideo(3);
					}
				}

				if(bean.getRoomType() == 3) {
					QueryWrapper<BuildingImgBean> wrapper = new QueryWrapper<BuildingImgBean>();
					wrapper.select("*");
					wrapper.eq("IT_ID", ImgTypeConstant.VIDEO);
					wrapper.eq("TYPE", ImgTypeConstant.STORE);
					wrapper.eq("INFO_ID", bean.getId());
					List<BuildingImgBean> imgBeans = buildingImgDao.selectList(wrapper);
					if(imgBeans != null ) {
						bean.setIsVideo(2);
					}else {
						bean.setIsVideo(3);
					}
				}


				// 查询区域名称
				if (bean.getLdId() != null) {
					bean.setLdName(locationDistinguishDao.selectById(bean.getLdId()).getLdName());
				}
				// 查询楼盘名称
				if (bean.getBuildId() != null) {
					bean.setBuildName(buildingDao.selectById(bean.getBuildId()).getHtName());
				}
				// 查询商圈名称
				if (bean.getBusinessId() != null) {
					bean.setBussinessName(businessDistrictDao.selectById(bean.getBusinessId()).getBuName());
				}
				// 查询类型名称
				if (bean.getBtId() != null) {
					bean.setTypeName(buildingTypeDao.selectById(bean.getBtId()).getBtName());
				}
				// 时间
				if (bean.getCreateDate() != null) {
					bean.setCreateTime(DateUtils.formatDate(bean.getCreateDate(), "yyyy-MM-dd"));
				}
				// 将标签字符串打成集合
				if (bean.getLabelList() != null) {
					String[] strings = bean.getLabelList().split("\\|");
					ArrayList<String> arrayList = new ArrayList<String>(strings.length);
					Collections.addAll(arrayList, strings);
					bean.setLabels(arrayList);
				}
			}
		}
		PageInfo<RoomMainBean> page = new PageInfo<RoomMainBean>(allRoomBypage);
		return page;
	}

	@Override
	public RMChildSharedBean getSharedById(Integer id) {

		List<RMChildSharedBean> sharedByIdList = roomMainDao.getSharedById(id);
		BuildingBean buildingBean = buildingDao.selectById(id);
		if (!CollectionsUtils.isEmpty(sharedByIdList)) {
			RMChildSharedBean roomMainBean = sharedByIdList.get(0);
			if (null != buildingBean) {
				roomMainBean.setBuildName(buildingBean.getHtName());
			}

			// 办公室信息
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
					ArrayList<String> arrayList = new ArrayList<String>(split.length);
					Collections.addAll(arrayList, split);
					List<Integer> idList = new ArrayList<>();
					for (String s : arrayList) {
						int anInt = Integer.parseInt(s);
						idList.add(anInt);
					}
					List<BaseServiceBean> serviceBeans = baseServiceDao.selectBatchIds(idList);
//
//					shareRoomBean.setChList(arrayList);
					shareRoomBean.setBaseServiceBeans(serviceBeans);
				}
			}
			// 图片路径
			QueryWrapper<BuildingImgBean> qw = new QueryWrapper<BuildingImgBean>();
			qw.select("*");
			qw.ne("IT_ID", ImgTypeConstant.VIDEO);
			qw.eq("TYPE", ImgTypeConstant.BUILD);
			qw.eq("INFO_ID", id);
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
		// 查询区域名称
		if (mainBean.getLdId() != null) {
			mainBean.setLdName(locationDistinguishDao.selectById(mainBean.getLdId()).getLdName());
		}
		// 查询楼盘名称
		if (mainBean.getBuildId() != null) {
			mainBean.setBuildName(buildingDao.selectById(mainBean.getBuildId()).getHtName());
		}
		// 查询商圈名称
		if (mainBean.getBusinessId() != null) {
			mainBean.setBussinessName(businessDistrictDao.selectById(mainBean.getBusinessId()).getBuName());
		}
		// 查询类型名称
		if (mainBean.getBtId() != null) {
			mainBean.setTypeName(buildingTypeDao.selectById(mainBean.getBtId()).getBtName());
		}
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
		qwP.eq("IT_ID", ImgTypeConstant.PICTURE);
		qwP.eq("TYPE", ImgTypeConstant.OFFICE_BUILD);
		qwP.eq("INFO_ID", roomMainId);
		List<BuildingImgBean> pictureList = buildingImgDao.selectList(qwP);
		QueryWrapper<BuildingImgBean> qwV = new QueryWrapper<BuildingImgBean>();
		qwV.select("*");
		qwV.eq("IT_ID", ImgTypeConstant.VIDEO);
		qwV.eq("TYPE", ImgTypeConstant.OFFICE_BUILD);
		qwV.eq("INFO_ID", roomMainId);
		List<BuildingImgBean> videoList = buildingImgDao.selectList(qwV);
		QueryWrapper<BuildingImgBean> qwH = new QueryWrapper<BuildingImgBean>();
		qwH.select("*");
		qwH.eq("IT_ID", ImgTypeConstant.HEAD_PICTURE);
		qwH.eq("TYPE", ImgTypeConstant.OFFICE_BUILD);
		qwH.eq("INFO_ID", roomMainId);
		List<BuildingImgBean> headList = buildingImgDao.selectList(qwH);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("roomDetail", roomBean);
		result.put("roomMainDetail", mainBean);
		result.put("counselor", counselorBean);
		result.put("allCounselor", list);
		result.put("picture", pictureList);
		result.put("video", videoList);
		result.put("head", headList);
		result.put("ldName", locationDistinguishDao.selectById(mainBean.getLdId()).getLdName());
		result.put("buildName", buildingDao.selectById(mainBean.getBuildId()).getHtName());
		result.put("businessName", businessDistrictDao.selectById(mainBean.getBusinessId()).getBuName());
		return result;
	}

	@Override
	public Map<String, Object> getStoneDetail(Integer roomMainId) {
		RoomMainBean mainBean = roomMainDao.selectById(roomMainId);
		// 查询区域名称
		if (mainBean.getLdId() != null) {
			mainBean.setLdName(locationDistinguishDao.selectById(mainBean.getLdId()).getLdName());
		}
		// 查询楼盘名称
		if (mainBean.getBuildId() != null) {
			mainBean.setBuildName(buildingDao.selectById(mainBean.getBuildId()).getHtName());
		}
		// 查询商圈名称
		if (mainBean.getBusinessId() != null) {
			mainBean.setBussinessName(businessDistrictDao.selectById(mainBean.getBusinessId()).getBuName());
		}
		// 查询类型名称
		if (mainBean.getBtId() != null) {
			mainBean.setTypeName(buildingTypeDao.selectById(mainBean.getBtId()).getBtName());
		}
		QueryWrapper<StoreRoomBean> stRoom = new QueryWrapper<StoreRoomBean>();
		stRoom.select("*");
		stRoom.eq("room_id", roomMainId);
		StoreRoomBean storeRoomBean = storeRoomDao.selectOne(stRoom);
		String info = storeRoomBean.getPropertyInfo();
		List<PropertyInfoBean> infoBeans = null;
		if (StringUtils.isNotBlank(info)) {
			String[] split = info.split("\\|");
			List<Integer> list = arrToList(split);
			infoBeans = propertyInfoDao.selectBatchIds(list);

		}

		if (mainBean.getCaId() != null) {
			if (mainBean.getCaId() != null) {
				String[] s = mainBean.getCaId().split(",");
				StringBuilder builder = new StringBuilder();
				for (int i = 0; i < s.length; i++) {
					String result = commercialActivitieDao.selectById(Integer.valueOf(s[i])).getCacName();
					builder.append(result);
					builder.append("/");
				}
				builder.deleteCharAt(builder.length() - 1);
				mainBean.setComString(builder.toString());
				if (storeRoomBean != null) {
					storeRoomBean.setSuitableStore(builder.toString());
				}
			}

			CounselorBean counselorBean = counselorDao.selectById(mainBean.getCouId());
			QueryWrapper<CounselorBean> queryWrapper = new QueryWrapper<CounselorBean>();
			queryWrapper.select("*");
			List<CounselorBean> list = counselorDao.selectList(queryWrapper);

			QueryWrapper<BuildingImgBean> qwP = new QueryWrapper<BuildingImgBean>();
			qwP.select("*");
			qwP.eq("IT_ID", ImgTypeConstant.PICTURE);
			qwP.eq("TYPE", ImgTypeConstant.STORE);
			qwP.eq("INFO_ID", roomMainId);
			List<BuildingImgBean> pictureList = buildingImgDao.selectList(qwP);

			QueryWrapper<BuildingImgBean> qwV = new QueryWrapper<BuildingImgBean>();
			qwV.select("*");
			qwV.eq("IT_ID", ImgTypeConstant.VIDEO);
			qwV.eq("TYPE", ImgTypeConstant.STORE);
			qwV.eq("INFO_ID", roomMainId);
			List<BuildingImgBean> videoList = buildingImgDao.selectList(qwV);

			Map<String, Object> result = new HashMap<String, Object>();
			result.put("stRoomDetail", storeRoomBean);
			result.put("roomDetail", mainBean);
			result.put("counselor", counselorBean);
			result.put("allCounselor", list);
			result.put("picture", pictureList);
			result.put("video", videoList);
			result.put("proInfo", infoBeans);
			return result;
		}
		return null;
	}

	private List<Integer> arrToList(String[] split) {
		ArrayList<String> arrayList = new ArrayList<String>(split.length);
		Collections.addAll(arrayList, split);
		List<Integer> idList = new ArrayList<>();
		for (String s : arrayList) {
			int anInt = Integer.parseInt(s);
			idList.add(anInt);
		}
		return idList;
	}

	@Override
	public Integer addRoomMain(RoomMainBean bean) throws MessageException {
		
//		if (!StringUtils.isBlank(bean.getRoom())) {
//			QueryWrapper<RoomMainBean> queryWrapper = new QueryWrapper<RoomMainBean>();
//			queryWrapper.select("*");
//			queryWrapper.eq("room", bean.getRoom());
//			queryWrapper.notIn("flag", 3);
//			List<RoomMainBean> list = roomMainDao.selectList(queryWrapper);
//			if (!CollectionsUtils.isEmpty(list)) {
//				throw new MessageException("此房源名字存在");
//			}
//		}
		if (!StringUtils.isBlank(bean.getRoomCode())) {
			QueryWrapper<RoomMainBean> queryWrapper = new QueryWrapper<RoomMainBean>();
			queryWrapper.select("*");
			queryWrapper.eq("room_code", bean.getRoomCode());
			List<RoomMainBean> list = roomMainDao.selectList(queryWrapper);
			if (!CollectionsUtils.isEmpty(list)) {
				throw new MessageException("此房源编号存在");
			}
		}
		if (bean.getLdId() == null) {
			throw new MessageException("区域不能为空");
		}
		if (bean.getBuildId() == null) {
			throw new MessageException("楼盘不能为空");
		}
		if (bean.getBusinessId() == null) {
			throw new MessageException("商圈不能为空");
		}
		if (bean.getCouId() == null) {
			throw new MessageException("咨询师不能为空");
		}
		if (bean.getRoomType() == null) {
			throw new MessageException("房源类型不能为空");
		}
		QueryWrapper<RoomMainBean> qw = new QueryWrapper<RoomMainBean>();
		qw.select("*");
		qw.eq("build_id", bean.getBuildId());
		qw.eq("room_type", 2);
		List<RoomMainBean> list = roomMainDao.selectList(qw);
		BuildingBean bd = buildingDao.selectById(bean.getBuildId());
		if (bean.getRoomType() == 2 && !CollectionsUtils.isEmpty(list) && bd.getBuildType() == 2) {
			throw new MessageException("共享楼盘只可以填一个共享房源");
		}

		bean.setCreateDate(new Date());
		bean.setModifyDate(new Date());
		roomMainDao.insert(bean);
		ShareRoomBean shareRoomBean = new ShareRoomBean();
		shareRoomBean.setProduce(bean.getProduce());
		if(bean.getChaList() != null) {
			shareRoomBean.setChaList(StringUtils.join(bean.getChaList(), "|"));
		}
		shareRoomBean.setRoomId(bean.getId());
		shareRoomBean.setCreateDate(new Date());
		shareRoomDao.insert(shareRoomBean);
		BuildingBean buildingBean = buildingDao.selectById(bean.getBuildId());
		buildingBean.setIsRentNum(buildingBean.getIsRentNum() + 1);
		buildingDao.updateById(buildingBean);
		return bean.getId();
	}

	@Override
	public Integer updateRoomMain(RoomMainBean bean) throws MessageException {
		if (bean.getId() == null) {
			throw new MessageException("房源id不能为空");
		}
		if(bean.getChaList() != null) {
			QueryWrapper<ShareRoomBean> qw = new QueryWrapper<ShareRoomBean>();
			qw.select("*");
			qw.eq("room_id", bean.getId());
			List<ShareRoomBean> list = shareRoomDao.selectList(qw);
			if(!CollectionsUtils.isEmpty(list)) {
				ShareRoomBean shareRoomBean = list.get(0);
				shareRoomBean.setChaList(StringUtils.join(bean.getChaList(), "|"));
				shareRoomDao.updateById(shareRoomBean);
			}
		}
		bean.setModifyDate(new Date());
		return roomMainDao.updateById(bean);
	}

	@Override
	public List<RoomMainBean> getRoomOffice(RoomReceive receive) {
		QueryWrapper<RoomMainBean> qw = new QueryWrapper<RoomMainBean>();
		qw.select("id,room");
		// 1,写字楼，2，共享，3商铺
		qw.eq("room_type", 2);
		return roomMainDao.selectList(qw);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteRoomByPk(RoomMainBean bean) {
		int i = roomMainDao.deleteById(bean.getId());
		if (i>0) {
			if (bean.getRoomType() == 1) {
				QueryWrapper<RoomBean> qw = new QueryWrapper<RoomBean>();
				// 1,写字楼，2，共享，3商铺
				qw.eq("room_id", bean.getId());
				roomDao.delete(qw);
			} else if (bean.getRoomType() == 2) {
				QueryWrapper<OfficeBean> qw = new QueryWrapper<OfficeBean>();
				// 1,写字楼，2，共享，3商铺
				qw.eq("room_id", bean.getId());
				officeDao.delete(qw);
				QueryWrapper<ShareRoomBean> qws = new QueryWrapper<ShareRoomBean>();
				// 1,写字楼，2，共享，3商铺
				qws.eq("room_id", bean.getId());
				shareRoomDao.delete(qws);
			} else if (bean.getRoomType() == 3) {
				QueryWrapper<StoreRoomBean> qw = new QueryWrapper<StoreRoomBean>();
				// 1,写字楼，2，共享，3商铺
				qw.eq("room_id", bean.getId());
				storeRoomDao.delete(qw);
			}
		}
	}


	@Transactional(rollbackFor = Exception.class)
	@Override
	public void offOrOnTheShelf(RoomMainBean bean) {
		roomMainDao.patchRoomFlagById(bean);
		if (bean.getRoomType() == 2) {
			officeDao.patchOffFlagByRoomId(new OfficeBean().setRoomId(bean.getId()).setFlag(bean.getFlag()));
		}
	}

}
