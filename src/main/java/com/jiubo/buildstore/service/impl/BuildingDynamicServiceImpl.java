package com.jiubo.buildstore.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.Exception.MessageException;
import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.bean.BuildingDynamicBean;
import com.jiubo.buildstore.bean.BuildingImgBean;
import com.jiubo.buildstore.bean.MessageTypeBean;
import com.jiubo.buildstore.bean.RoomMainBean;
import com.jiubo.buildstore.common.ImgPathConstant;
import com.jiubo.buildstore.common.ImgTypeConstant;
import com.jiubo.buildstore.dao.BuildingDao;
import com.jiubo.buildstore.dao.BuildingDynamicDao;
import com.jiubo.buildstore.dao.MessageTypeDao;
import com.jiubo.buildstore.service.BuildingDynamicService;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
@Service
public class BuildingDynamicServiceImpl extends ServiceImpl<BuildingDynamicDao, BuildingDynamicBean>
		implements BuildingDynamicService {

	@Value("${buildStoreDir}")
	private String buildStoreDir;
	@Autowired
	private BuildingDynamicDao buildingDynamicDao;
	@Autowired
	private BuildingDao buildingDao;
	@Autowired
	private MessageTypeDao messageTypeDao;

	@Override
	public PageInfo<BuildingDynamicBean> getDynamicByBid(BuildingDynamicBean buildingDynamicBean) {

		Integer pageNum = StringUtils.isBlank(buildingDynamicBean.getCurrent()) ? 1
				: Integer.valueOf(buildingDynamicBean.getCurrent());
		Integer pageSize = StringUtils.isBlank(buildingDynamicBean.getPageSize()) ? 10
				: Integer.valueOf(buildingDynamicBean.getPageSize());
		PageHelper.startPage(pageNum, pageSize);
		List<BuildingDynamicBean> dynamicByBidList = buildingDynamicDao.getDynamicByBid(buildingDynamicBean);
		if (null != dynamicByBidList && dynamicByBidList.size() > 0) {
			for (BuildingDynamicBean dynamicBean : dynamicByBidList) {
				if (dynamicBean.getCreateDate() != null) {
					dynamicBean.setCreateTime(DateUtils.formatDate(dynamicBean.getCreateDate(), "yyyy-MM-dd"));
				}
				if (StringUtils.isNotBlank(dynamicBean.getBdPath())) {
					dynamicBean.setBdPath(
							ImgPathConstant.INTERFACE_PATH.concat(buildStoreDir).concat(dynamicBean.getBdPath()));
				}
				if (dynamicBean.getBuildId() != null) {
					MessageTypeBean bean = messageTypeDao.selectById(dynamicBean.getBuildId());
					if (null != bean) {
						dynamicBean.setTypeName(bean.getMtName());
					}
				}
			}
		}
		PageInfo<BuildingDynamicBean> page = new PageInfo<BuildingDynamicBean>(dynamicByBidList);
		return page;
	}

	@Override
	public BuildingDynamicBean getNewestDynamicByBid(BuildingDynamicBean buildingDynamicBean) {
		List<BuildingDynamicBean> dynamicByBidList = buildingDynamicDao.getDynamicByBid(buildingDynamicBean);
		BuildingDynamicBean bean = new BuildingDynamicBean();
		if (!CollectionsUtils.isEmpty(dynamicByBidList)) {
			List<BuildingDynamicBean> dynamicBeans = dynamicByBidList.stream()
					.sorted(Comparator.comparing(BuildingDynamicBean::getCreateDate).reversed()).limit(1)
					.collect(Collectors.toList());
			bean = dynamicBeans.get(0);
			bean.setDyCount(dynamicByBidList.size());
			for (BuildingDynamicBean dynamicBean : dynamicByBidList) {
				if (dynamicBean.getCreateDate() != null) {
					dynamicBean.setCreateTime(DateUtils.formatDate(dynamicBean.getCreateDate(), "yyyy-MM-dd"));
				}
			}
		} else {
			bean.setDyCount(0);
		}
		return bean;
	}

	@Override
	public Page<BuildingDynamicBean> getDynamicByPage(BuildingDynamicBean buildingDynamicBean) {
		Page<BuildingDynamicBean> page = new Page<>();
		page.setCurrent(StringUtils.isBlank(buildingDynamicBean.getCurrent()) ? 1L
				: Long.parseLong(buildingDynamicBean.getCurrent()));
		page.setSize(StringUtils.isBlank(buildingDynamicBean.getPageSize()) ? 10L
				: Long.parseLong(buildingDynamicBean.getPageSize()));
		List<BuildingDynamicBean> dynamicBeans = buildingDynamicDao.getDynamicByPage(page, buildingDynamicBean);
		if (null != dynamicBeans && dynamicBeans.size() > 0) {
			for (BuildingDynamicBean dynamicBean : dynamicBeans) {
				if (dynamicBean.getBuildId() != null) {
					Date date = dynamicBean.getCreateDate();
					if (null != date) {
						dynamicBean.setCreateTime(DateUtils.formatDate(date, "yyyy-MM-dd"));
					}
				}
			}
		}
		return page.setRecords(dynamicBeans);
	}

	@Override
	public Page<BuildingDynamicBean> getDynamicByPageBe(BuildingDynamicBean buildingDynamicBean) {
		Page<BuildingDynamicBean> page = new Page<>();
		page.setCurrent(StringUtils.isBlank(buildingDynamicBean.getCurrent()) ? 1L
				: Long.parseLong(buildingDynamicBean.getCurrent()));
		page.setSize(StringUtils.isBlank(buildingDynamicBean.getPageSize()) ? 10L
				: Long.parseLong(buildingDynamicBean.getPageSize()));
		List<BuildingDynamicBean> dynamicBeans = buildingDynamicDao.getDynamicByPage(page, buildingDynamicBean);
		if (null != dynamicBeans && dynamicBeans.size() > 0) {
			for (BuildingDynamicBean dynamicBean : dynamicBeans) {
				if (dynamicBean.getBuildId() != null) {
					Date date = dynamicBean.getCreateDate();
					if (null != date) {
						dynamicBean.setCreateTime(DateUtils.formatDate(date, "yyyy-MM-dd"));
					}
				}
				if (StringUtils.isNotBlank(dynamicBean.getBdPath())) {
					dynamicBean.setBdPath(ImgPathConstant.INTERFACE_PATH.concat(dynamicBean.getBdPath()));
				}
			}
		}
		return page.setRecords(dynamicBeans);
	}

	@Override
	public void patchDyById(BuildingDynamicBean buildingDynamicBean, MultipartFile[] file) throws IOException {
		if (file != null && file.length > 0) {
			Map<String, String> map = FileUtil.uploadFile(file[0], ImgPathConstant.BU_PATH,
					buildingDynamicBean.getBdId(), 2);
			if (!map.isEmpty()) {
				buildingDynamicBean.setBdPath(map.get("path"));
			}
		} else {
			buildingDynamicBean.setBdPath(null);
		}
		buildingDynamicDao.updateById(buildingDynamicBean);
	}

	@Override
	public void addDynamic(BuildingDynamicBean buildingDynamicBean, MultipartFile[] file) throws IOException {
		buildingDynamicBean.setCreateDate(new Date());
		buildingDynamicDao.insert(buildingDynamicBean);
		if (file != null && file.length > 0) {
			Map<String, String> map = FileUtil.uploadFile(file[0], ImgPathConstant.BU_PATH,
					buildingDynamicBean.getBdId(), 2);
			if (!map.isEmpty()) {
				buildingDynamicBean.setBdPath(map.get("path"));
				buildingDynamicDao.updateById(buildingDynamicBean);
			}
		}
	}

	@Override
	public Map<String, BuildingDynamicBean> getDynamicByDyId(Integer dynamicId) {
		Map<String, BuildingDynamicBean> result = new HashMap<String, BuildingDynamicBean>();
		// 记录此条咨询数据在list的下标
		Integer index = null;
		QueryWrapper<BuildingDynamicBean> queryWrapper = new QueryWrapper<BuildingDynamicBean>();
		queryWrapper.select("*");
		List<BuildingDynamicBean> list = buildingDynamicDao.selectList(queryWrapper);
		for (int i = 0; i < list.size(); i++) {
			BuildingDynamicBean bean = list.get(i);
			if (bean.getBdId() == dynamicId) {
				index = i;
				break;
			}
		}
		// 资讯类型
		QueryWrapper<MessageTypeBean> meWrapper = new QueryWrapper<MessageTypeBean>();
		meWrapper.select("*");
		List<MessageTypeBean> messageTypeBeans = messageTypeDao.selectList(meWrapper);
		Map<Integer, List<MessageTypeBean>> meMap = null;
		if (!CollectionsUtils.isEmpty(messageTypeBeans)) {
			meMap = messageTypeBeans.stream().collect(Collectors.groupingBy(MessageTypeBean::getMtId));
		}
		// now代表当前查询的这条咨询信息up上一条down下一条
		BuildingDynamicBean bd = buildingDynamicDao.selectById(dynamicId);
		addMtName(meMap, bd);

		result.put("now", bd);
		// 如果下标等于0说明为第一条数据没有上一条放null
		if (index == 0) {
			result.put("up", null);
		} else {
			bd = list.get(index - 1);
			bd.setCreateTime(DateUtils.formatDateTime(bd.getCreateDate()));
			result.put("up", bd);
			addMtName(meMap, bd);
		}
		// 如果下标等于集合大小减一说明为最后一条数据没有下一条放null
		if (index == list.size() - 1) {
			result.put("down", null);
		} else {
			bd = list.get(index + 1);
			bd.setCreateTime(DateUtils.formatDateTime(bd.getCreateDate()));
			result.put("down", bd);
			addMtName(meMap, bd);
		}
		return result;
	}

	private void addMtName(Map<Integer, List<MessageTypeBean>> meMap, BuildingDynamicBean bd) {
		if (null != meMap && null != bd) {
			bd.setCreateTime(DateUtils.formatDateTime(bd.getCreateDate()));
			List<MessageTypeBean> typeBeans = meMap.get(bd.getBuildId());
			if (!CollectionsUtils.isEmpty(typeBeans)) {
				bd.setMtName(typeBeans.get(0).getMtName());
			}
		}
	}

	@Override
	public List<BuildingDynamicBean> getNewestDy() {
		return buildingDynamicDao.getNewestDy();
	}

	@Override
	public JSONObject getDynamicByBuildId() {
		JSONObject jsonObject = new JSONObject();
		// 行业新闻
		List<BuildingDynamicBean> dynamicByBuildId = buildingDynamicDao.getDynamicByBuildId(1);
		// 找房攻略
		List<BuildingDynamicBean> dynamicByBuildId1 = buildingDynamicDao.getDynamicByBuildId(7);

		jsonObject.put("industry", dynamicByBuildId);
		jsonObject.put("strategy", dynamicByBuildId1);
		return jsonObject;
	}

	@Override
	public void deleteDynamicById(BuildingDynamicBean buildingDynamicBean) {
		buildingDynamicDao.deleteDynamicById(buildingDynamicBean.getBdId());
	}

	@Override
	public List<BuildingDynamicBean> getRecommendDy(BuildingDynamicBean buildingDynamicBean) {
		return buildingDynamicDao.getRecommendDy(buildingDynamicBean.getBuildId(),buildingDynamicBean.getBdId());
	}
}
