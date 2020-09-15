package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.AreaBean;
import com.jiubo.buildstore.bean.BuildingTypeBean;
import com.jiubo.buildstore.bean.RoomBean;
import com.jiubo.buildstore.bean.RoomReceive;
import com.jiubo.buildstore.bean.TotlePriceTypeBean;
import com.jiubo.buildstore.bean.UnitPriceTypeBean;
import com.jiubo.buildstore.dao.AreaDao;
import com.jiubo.buildstore.dao.BuildingTypeDao;
import com.jiubo.buildstore.dao.RoomDao;
import com.jiubo.buildstore.dao.TotlePriceTypeDao;
import com.jiubo.buildstore.dao.UnitPriceTypeDao;
import com.jiubo.buildstore.service.RoomService;
import com.jiubo.buildstore.util.CollectionsUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * 服务实现类
 * </p>
 *
 * @author swd
 * @since 2020-09-11
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomDao, RoomBean> implements RoomService {

	@Autowired
	private RoomDao roomDao;

	@Autowired
	private AreaDao areaDao;

	@Autowired
	private BuildingTypeDao buildingTypeDao;

	@Autowired
	private UnitPriceTypeDao unitPriceTypeDao;
	
	@Autowired
	private TotlePriceTypeDao totlePriceTypeDao;

	@Override
	public PageInfo<RoomBean> getRoomByConditions(RoomReceive receive) {
		Integer pageNum = StringUtils.isBlank(receive.getCurrent()) ? 1 : Integer.valueOf(receive.getCurrent());
		Integer pageSize = StringUtils.isBlank(receive.getPageSize()) ? 10 : Integer.valueOf(receive.getPageSize());
		setCondition(receive);

		// 房源数据
		PageHelper.startPage(pageNum,pageSize);
		List<RoomBean> allRoomBypage = roomDao.getAllRoomBypage(receive);

		if (!CollectionsUtils.isEmpty(allRoomBypage)) {
			// 获取房源id
//			List<Integer> list = allRoomBypage.stream().map(RoomBean::getId).collect(toList());

			// 获取所有类型
			List<BuildingTypeBean> buildTypeList = buildingTypeDao.getAllBuildingType();
			Map<Integer, List<BuildingTypeBean>> btMap = null;
			if (!CollectionsUtils.isEmpty(buildTypeList)) {
				btMap = buildTypeList.stream().collect(Collectors.groupingBy(BuildingTypeBean::getBtId));
			}

			// 遍历实体 翻译各个类型字段
			for (RoomBean bean : allRoomBypage) {

				// 是否是热销标签
//				if (bean.getIsHot() != null) {
//					bean.setIsHot(2);
//				} else {
//					bean.setIsHot(3);
//				}

			}
		}
		PageInfo<RoomBean> page = new PageInfo<RoomBean>(allRoomBypage);
		return page;
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
