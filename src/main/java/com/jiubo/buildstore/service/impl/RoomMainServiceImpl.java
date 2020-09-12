package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.*;
import com.jiubo.buildstore.dao.*;
import com.jiubo.buildstore.service.RoomMainService;
import com.jiubo.buildstore.util.CollectionsUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private AloneRoomDao aloneRoomDao;

	@Autowired
	private OpenRoomDao openRoomDao;
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
			List<AloneRoomBean> aloneRoomBeans = aloneRoomDao.getAloneRoomByRoomId(roomMainBean.getId());
			List<OpenRoomBean> openRoomBeans = openRoomDao.getOpenRoomByRoomId(roomMainBean.getId());
			roomMainBean.setAloneRoomBeanList(aloneRoomBeans);
			roomMainBean.setOpenRoomBeanList(openRoomBeans);
			return roomMainBean;
		}
		return roomMainBean;
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
}
