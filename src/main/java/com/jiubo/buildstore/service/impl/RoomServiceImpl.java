package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.AreaBean;
import com.jiubo.buildstore.bean.BhtRefBean;
import com.jiubo.buildstore.bean.BuildReceive;
import com.jiubo.buildstore.bean.BuildingImgBean;
import com.jiubo.buildstore.bean.BuildingTypeBean;
import com.jiubo.buildstore.bean.CharaRefBean;
import com.jiubo.buildstore.bean.MetroBuildRefBean;
import com.jiubo.buildstore.bean.RoomBean;
import com.jiubo.buildstore.bean.RoomReceive;
import com.jiubo.buildstore.bean.RoomReturn;
import com.jiubo.buildstore.bean.SaleTypeBean;
import com.jiubo.buildstore.bean.TotlePriceTypeBean;
import com.jiubo.buildstore.bean.UnitPriceTypeBean;
import com.jiubo.buildstore.common.ImgPathConstant;
import com.jiubo.buildstore.dao.AreaDao;
import com.jiubo.buildstore.dao.BuildingTypeDao;
import com.jiubo.buildstore.dao.RoomDao;
import com.jiubo.buildstore.dao.TotlePriceTypeDao;
import com.jiubo.buildstore.dao.UnitPriceTypeDao;
import com.jiubo.buildstore.service.RoomService;
import com.jiubo.buildstore.util.CollectionsUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
	public Page<RoomBean> getRoomByConditions(RoomReceive receive) {
		Page<RoomBean> page = new Page<>();
		page.setCurrent(StringUtils.isBlank(receive.getCurrent()) ? 1L : Long.parseLong(receive.getCurrent()));
		page.setSize(StringUtils.isBlank(receive.getPageSize()) ? 10L : Long.parseLong(receive.getPageSize()));
		setCondition(receive);

		// 房源数据
		List<RoomBean> allRoomBypage = roomDao.getAllRoomBypage(receive);

		if (!CollectionsUtils.isEmpty(allRoomBypage)) {
			// 获取房源id
			List<Integer> list = allRoomBypage.stream().map(RoomBean::getId).collect(toList());

			// 获取所有类型
			List<BuildingTypeBean> buildTypeList = buildingTypeDao.getAllBuildingType();
			Map<Integer, List<BuildingTypeBean>> btMap = null;
			if (!CollectionsUtils.isEmpty(buildTypeList)) {
				btMap = buildTypeList.stream().collect(Collectors.groupingBy(BuildingTypeBean::getBtId));
			}

			// 获取头图
//            BuildingImgBean buildingImgBean = new BuildingImgBean();
//            buildingImgBean.setBIdList(list);
//            List<BuildingImgBean> byBuildId = buildingImgDao.getHeadImgByBuildId(buildingImgBean);
//            Map<Integer, List<BuildingImgBean>> imgMap = null;
//            if (!CollectionsUtils.isEmpty(byBuildId)) {
//                imgMap = byBuildId.stream().collect(Collectors.groupingBy(BuildingImgBean::getBuildId));
//            }


			// 遍历实体 翻译各个类型字段
			for (RoomBean bean : allRoomBypage) {

				// 图片名字 路径
//                if (null != imgMap && null != bean.getBuildId()) {
//                    List<BuildingImgBean> buildingImgBeans = imgMap.get(bean.getBuildId());
//                    if (!CollectionsUtils.isEmpty(buildingImgBeans)) {
//                        Map<Integer, List<BuildingImgBean>> map = buildingImgBeans.stream().collect(Collectors.groupingBy(BuildingImgBean::getItId));
//                        // 轮播图
//                        getPicPath(bean, map);
//                        // 头图
//                        List<BuildingImgBean> imgBeans = map.get(6);
//                        if (!CollectionsUtils.isEmpty(imgBeans)) {
//                            bean.setImgName(imgBeans.get(0).getImgName());
//                            String imgPath = imgBeans.get(0).getImgPath();
//                            bean.setImgPath(ImgPathConstant.INTERFACE_PATH.concat(imgPath).concat("&imgId=").concat(imgBeans.get(0).getImgId().toString()));
//                        }
//
//                        // 视频
//                        List<BuildingImgBean> imgBeanList = map.get(7);
//                        if (!CollectionsUtils.isEmpty(imgBeanList)) {
//                            bean.setVideoName(imgBeanList.get(0).getImgName());
//                            bean.setVideoPath(ImgPathConstant.INTERFACE_PATH.concat(imgBeanList.get(0).getImgPath()).concat("&imgId=").concat(map.get(7).get(0).getImgId().toString()));
//                        }
//                    }
//                }

				// 是否是热销标签
				if (bean.getIsHot() != null) {
					bean.setIsHot(2);
				} else {
					bean.setIsHot(3);
				}

			}
		}
		return page.setRecords(allRoomBypage);
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
