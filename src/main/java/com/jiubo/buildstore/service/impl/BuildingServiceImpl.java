package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.Exception.MessageException;
import com.jiubo.buildstore.bean.*;
import com.jiubo.buildstore.common.BuildConstant;
import com.jiubo.buildstore.common.ImgPathConstant;
import com.jiubo.buildstore.common.ImgTypeConstant;
import com.jiubo.buildstore.dao.*;
import com.jiubo.buildstore.service.BuildingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiubo.buildstore.util.CollectionsUtils;
import com.jiubo.buildstore.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
@Slf4j
@Service
public class BuildingServiceImpl extends ServiceImpl<BuildingDao, BuildingBean> implements BuildingService {


    @Autowired
    private BuildingDao buildingDao;

    @Autowired
    private CharacteristicDao characteristicDao;

    @Autowired
    private BuildingHorseTypeDao buildingHorseTypeDao;

    @Autowired
    private SaleTypeDao saleTypeDao;

    @Autowired
    private CharaRefDao charaRefDao;

    @Autowired
    private BhtRefDao bhtRefDao;

    @Autowired
    private ImgTypeDao imgTypeDao;

    @Autowired
    private BuildingImgDao buildingImgDao;

    @Autowired
    private BuildingTypeDao buildingTypeDao;

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private UnitPriceTypeDao unitPriceTypeDao;

    @Autowired
    private LocationDistinguishDao locationDistinguishDao;

    @Autowired
    private MetroBuildRefDao metroBuildRefDao;
    @Value("${buildStoreDir}")
    private String buildStoreDir;


    @Override
    /**
     * 条件筛选
     */
    public Page<BuildReturn> getAllBulidBypage(BuildReceive buildingBean) {
        Page<BuildReturn> page = new Page<>();
        page.setCurrent(StringUtils.isBlank(buildingBean.getCurrent()) ? 1L : Long.parseLong(buildingBean.getCurrent()));
        page.setSize(StringUtils.isBlank(buildingBean.getPageSize()) ? 10L : Long.parseLong(buildingBean.getPageSize()));
        if (setCondition(buildingBean, page)) return page;

        // 楼盘数据
        List<BuildReturn> allBulidBypage = buildingDao.getAllBulidBypage(page, buildingBean);

        if (!CollectionsUtils.isEmpty(allBulidBypage)) {
            // 获取楼盘id
            List<Integer> list = allBulidBypage.stream().map(BuildingBean::getBuildId).collect(toList());

            // 获取所有类型
            List<BuildingTypeBean> buildTypeList = buildingTypeDao.getAllBuildingType();
            Map<Integer, List<BuildingTypeBean>> btMap = null;
            if (!CollectionsUtils.isEmpty(buildTypeList)) {
                btMap = buildTypeList.stream().collect(Collectors.groupingBy(BuildingTypeBean::getBtId));
            }

            //获取所有特色
            List<CharaRefBean> chaRefByBidList = charaRefDao.getChaRefByBidList(new CharaRefBean().setBuildIdList(list));
            Map<Integer, List<CharaRefBean>> charaRefMap = null;
            if (!CollectionsUtils.isEmpty(chaRefByBidList)) {
                charaRefMap = chaRefByBidList.stream().collect(Collectors.groupingBy(CharaRefBean::getBuildId));
            }


            BuildingImgBean buildingImgBean = new BuildingImgBean();
            buildingImgBean.setBIdList(list);

            buildingImgBean.setItId(2);
            List<BuildingImgBean> video = buildingImgDao.getHeadImgByBuildId(buildingImgBean);
            Map<Integer, List<BuildingImgBean>> videoMap = null;
            if (!CollectionsUtils.isEmpty(video)) {
                videoMap = video.stream().collect(Collectors.groupingBy(BuildingImgBean::getBuildId));
            }

            buildingImgBean.setItId(1);
            List<BuildingImgBean> picture = buildingImgDao.getHeadImgByBuildId(buildingImgBean);
            Map<Integer, List<BuildingImgBean>> pictureMap = null;
            if (!CollectionsUtils.isEmpty(picture)) {
                pictureMap = picture.stream().collect(Collectors.groupingBy(BuildingImgBean::getBuildId));
            }

            // 遍历实体 翻译各个类型字段
            for (BuildReturn bean : allBulidBypage) {
                // 视频
                if (null != videoMap) {
                    List<BuildingImgBean> imgBeans = videoMap.get(bean.getBuildId());
                    if (!CollectionsUtils.isEmpty(imgBeans)) {
                        BuildingImgBean imgBean = imgBeans.get(0);
                        bean.setVideoPath(imgBean.getImgPath());
                    }

                }

                // 图片
                if (null != pictureMap) {
                    List<BuildingImgBean> beanList = pictureMap.get(bean.getBuildId());
                    if (!CollectionsUtils.isEmpty(beanList)) {
                        List<String> list1 = beanList.stream().map(BuildingImgBean::getImgPath).collect(toList());
                        bean.setPicturePath(list1);
                    }
                }

                // 类型
                if (null != btMap && bean.getBuildType() != null) {
                    List<BuildingTypeBean> list1 = btMap.get(bean.getBuildType());
                    if (!CollectionsUtils.isEmpty(list1)) {
                        bean.setBtName(list1.get(0).getBtName());
                    }
                }

                // 特色
                if (null != charaRefMap) {
                    List<CharaRefBean> charaRefBeanList = charaRefMap.get(bean.getBuildId());
                    if (!CollectionsUtils.isEmpty(charaRefBeanList)) {
                        List<String> charaRefList = charaRefBeanList.stream().map(CharaRefBean::getHouseName).collect(toList());
                        // 特色标签集合
                        bean.setCharaNameList(charaRefList);
                        // 特色id集合
                        bean.setChaIdList(charaRefBeanList.stream().map(CharaRefBean::getHouseId).collect(toList()));
                    }
                }

            }
        }
        return page.setRecords(allBulidBypage);
    }


    public Page<BuildReturn> getAllBulidByCondition(BuildReceive buildingBean) {
        Page<BuildReturn> page = new Page<>();
        page.setCurrent(StringUtils.isBlank(buildingBean.getCurrent()) ? 1L : Long.parseLong(buildingBean.getCurrent()));
        page.setSize(StringUtils.isBlank(buildingBean.getPageSize()) ? 10L : Long.parseLong(buildingBean.getPageSize()));
        if (setCondition(buildingBean, page)) return page;

        // 楼盘数据
        List<BuildReturn> allBulidBypage = buildingDao.getAllBulidBypage(page, buildingBean);

        if (!CollectionsUtils.isEmpty(allBulidBypage)) {
            // 获取楼盘id
            List<Integer> list = allBulidBypage.stream().map(BuildReturn::getBuildId).collect(toList());


            // 获取所有类型
            List<BuildingTypeBean> buildTypeList = buildingTypeDao.getAllBuildingType();
            Map<Integer, List<BuildingTypeBean>> btMap = null;
            if (!CollectionsUtils.isEmpty(buildTypeList)) {
                btMap = buildTypeList.stream().collect(Collectors.groupingBy(BuildingTypeBean::getBtId));
            }

            //获取所有特色
            List<CharaRefBean> chaRefByBidList = charaRefDao.getChaRefByBidList(new CharaRefBean().setBuildIdList(list));
            Map<Integer, List<CharaRefBean>> charaRefMap = null;
            if (!CollectionsUtils.isEmpty(chaRefByBidList)) {
                charaRefMap = chaRefByBidList.stream().collect(Collectors.groupingBy(CharaRefBean::getBuildId));
            }

            // 翻译楼盘户型
            List<BhtRefBean> bhtRefBeanList = bhtRefDao.getAllBhtRefByBIds(new BhtRefBean().setBuildIdList(list));
            Map<Integer, List<BhtRefBean>> bhtRefMap = null;
            if (!CollectionsUtils.isEmpty(bhtRefBeanList)) {
                bhtRefMap = bhtRefBeanList.stream().collect(Collectors.groupingBy(BhtRefBean::getBuildId));
            }

            // 翻译出售状态
            List<SaleTypeBean> allSaleType = saleTypeDao.getAllSaleType();
            Map<Integer, List<SaleTypeBean>> saleMap = null;
            if (!CollectionsUtils.isEmpty(allSaleType)) {
                saleMap = allSaleType.stream().collect(Collectors.groupingBy(SaleTypeBean::getStId));
            }

            // 获取头图
            BuildingImgBean buildingImgBean = new BuildingImgBean();
            buildingImgBean.setBIdList(list);
            List<BuildingImgBean> byBuildId = buildingImgDao.getHeadImgByBuildId(buildingImgBean);
            Map<Integer, List<BuildingImgBean>> imgMap = null;
            if (!CollectionsUtils.isEmpty(byBuildId)) {
                imgMap = byBuildId.stream().collect(Collectors.groupingBy(BuildingImgBean::getBuildId));
            }
            // 获取地铁
            List<MetroBuildRefBean> allMBRefByBIds = metroBuildRefDao.getAllMBRefByBIds(new MetroBuildRefBean().setBuildIdList(list));
            Map<Integer, List<MetroBuildRefBean>> mBRefMap = null;
            if (!CollectionsUtils.isEmpty(allMBRefByBIds)) {
                mBRefMap = allMBRefByBIds.stream().collect(Collectors.groupingBy(MetroBuildRefBean::getBuildId));
            }

            // 遍历实体 翻译各个类型字段
            for (BuildReturn bean : allBulidBypage) {

                // 类型
                if (null != btMap && bean.getBuildType() != null) {
                    bean.setBtName(btMap.get(bean.getBuildType()).get(0).getBtName());
                }

                // 特色
                if (null != charaRefMap) {

                    List<CharaRefBean> charaRefBeanList = charaRefMap.get(bean.getBuildId());
                    if (null != charaRefBeanList && charaRefBeanList.size() > 0) {
                        List<String> charaRefList = charaRefBeanList.stream().map(CharaRefBean::getHouseName).collect(toList());
                        bean.setCharaNameList(charaRefList);
                        bean.setChaIdList(charaRefBeanList.stream().map(CharaRefBean::getHouseId).collect(toList()));
                        if (!CollectionsUtils.isEmpty(charaRefList)) {
                            bean.setChaName(StringUtils.join(charaRefList, "、"));
                        }
                    }
                }


                // 图片名字 路径
                if (null != imgMap && null != bean.getBuildId()) {
                    List<BuildingImgBean> buildingImgBeans = imgMap.get(bean.getBuildId());
                    if (!CollectionsUtils.isEmpty(buildingImgBeans)) {
                        Map<Integer, List<BuildingImgBean>> map = buildingImgBeans.stream().collect(Collectors.groupingBy(BuildingImgBean::getItId));

                    }
                }
            }
        }
        return page.setRecords(allBulidBypage);
    }


    private boolean setCondition(BuildReceive buildingBean, Page<BuildReturn> page) {
        // 获取面积集合
        List<Integer> areaIdList = buildingBean.getAreaIdList();
        if (null != areaIdList && areaIdList.size() > 0) {
            List<AreaBean> areaByIdList = areaDao.getAreaByIdList(new AreaBean().setIdList(areaIdList));
            List<Map<String, Object>> areaList = new ArrayList<>();
            for (AreaBean areaBean : areaByIdList) {
                Map<String, Object> map = new HashMap<>();
                map.put("minArea", areaBean.getBegArea());
                map.put("maxArea", areaBean.getEndArea());
                areaList.add(map);
            }
            buildingBean.setAreaList(areaList);
        }

        // 获取均价集合
        List<Integer> unitPriceIdList = buildingBean.getUnitPriceIdList();
        if (!CollectionsUtils.isEmpty(unitPriceIdList)) {
            List<UnitPriceTypeBean> priceByIdList = unitPriceTypeDao.getUnitPriceByIdList(new UnitPriceTypeBean().setIdList(unitPriceIdList));
            List<Map<String, Object>> unitPriceList = new ArrayList<>();
            for (UnitPriceTypeBean unitPriceTypeBean : priceByIdList) {
                Map<String, Object> map = new HashMap<>();
                if (null == unitPriceTypeBean.getBegPrice()) {
                    map.put("minUnitPrice", null);
                } else {
                    map.put("minUnitPrice", unitPriceTypeBean.getBegPrice().multiply(new BigDecimal(10000)));
                }

                if (null == unitPriceTypeBean.getEndPrice()) {
                    map.put("maxUnitPrice", null);
                } else {
                    map.put("maxUnitPrice", unitPriceTypeBean.getEndPrice().multiply(new BigDecimal(10000)));
                }
                unitPriceList.add(map);
            }
            buildingBean.setUnitPriceList(unitPriceList);
        }
        return false;
    }


    @Override
    public BuildingBean addBuilding(BuildReceive buildingBean, MultipartFile[] effectImg, MultipartFile[] enPlanImg,
                            MultipartFile[] buildRealImg,
                            MultipartFile[] matchingRealImg,
                            MultipartFile[] headImg,
                            MultipartFile[] regionImg,
                            MultipartFile[] video) throws Exception {


        BuildReturn byHtName = buildingDao.getAllByHtName(buildingBean);

        // 判断联系方式是否为空
        if (StringUtils.isBlank(buildingBean.getTel())) {
            buildingBean.setTel(BuildConstant.MODIFY_TEL);
        }


        if (null == byHtName) {

            buildingBean.setCreateTime(new Date());
            // 若不存在 创建
            buildingDao.insert(buildingBean);

            // 绑定楼盘和户型关系
            bindBhtRef(buildingBean);

            // 绑定楼盘特色关系
            bindCharaRef(buildingBean);

            // 绑定楼盘 地铁之间的关系
            bindMBRef(buildingBean);

        } else {

            // 存在 则更新
            int id = byHtName.getBuildId();
            buildingBean.setBuildId(id);
            buildingDao.updateById(buildingBean);

            // 更新楼盘户型关系
            List<Integer> bhtIdList = buildingBean.getBhtIdList();
            if (!CollectionsUtils.isEmpty(bhtIdList)) {
                bhtRefDao.deleteBhtRefByBid(id);
                bindBhtRef(buildingBean);
            }
            // 更新楼盘特色关系
            List<Integer> chaIdList = buildingBean.getChaIdList();
            if (!CollectionsUtils.isEmpty(chaIdList)) {
                charaRefDao.deleteCharaRefByBid(id);
                bindCharaRef(buildingBean);
            }

            //更新楼盘地铁关系
            List<Integer> metroIdList = buildingBean.getMetroIdList();
            if (!CollectionsUtils.isEmpty(metroIdList)) {
                metroBuildRefDao.deleteMBRefByBid(id);
                bindMBRef(buildingBean);
            }
        }


        List<ImgTypeBean> imgTypeList = imgTypeDao.getAllImgType();
//        System.out.println("effectImg：" + enPlanImg);
        buildingBean.setBuildId(buildingBean.getBuildId());

        if (!CollectionsUtils.isEmpty(imgTypeList)) {
            Map<String, List<ImgTypeBean>> listMap = imgTypeList.stream().collect(Collectors.groupingBy(ImgTypeBean::getItName));
            this.saveFile(buildingBean, effectImg, "effectImg", listMap.get(ImgTypeConstant.effectImg).get(0).getItId());
            this.saveFile(buildingBean, enPlanImg, "enPlanImg", listMap.get(ImgTypeConstant.enPlanImg).get(0).getItId());
            this.saveFile(buildingBean, buildRealImg, "buildRealImg", listMap.get(ImgTypeConstant.buildRealImg).get(0).getItId());
            this.saveFile(buildingBean, matchingRealImg, "matchingRealImg", listMap.get(ImgTypeConstant.matchingRealImg).get(0).getItId());
            this.saveFile(buildingBean, regionImg, "regionImg", listMap.get(ImgTypeConstant.regionImg).get(0).getItId());
            this.saveFile(buildingBean, headImg, "headImg", listMap.get(ImgTypeConstant.headImg).get(0).getItId());
            this.saveFile(buildingBean, video, "video", listMap.get(ImgTypeConstant.video).get(0).getItId());
        }

        return buildingBean;
    }

    private void bindMBRef(BuildReceive buildingBean) {
        List<Integer> metroIdList = buildingBean.getMetroIdList();
        if (!CollectionsUtils.isEmpty(metroIdList)) {
            List<MetroBuildRefBean> metroBuildRefBeanList = new ArrayList<>();
            for (Integer metroId : metroIdList) {
                MetroBuildRefBean metroBuildRefBean = new MetroBuildRefBean();
                metroBuildRefBean.setMetroId(metroId);
                metroBuildRefBean.setBuildId(buildingBean.getBuildId());
                metroBuildRefBeanList.add(metroBuildRefBean);
            }
            metroBuildRefDao.insertMBRefBatch(metroBuildRefBeanList);
        }
    }

    private void bindCharaRef(BuildReceive buildingBean) {
        List<Integer> chaIdList = buildingBean.getChaIdList();
        if (!CollectionsUtils.isEmpty(chaIdList)) {
            List<CharacteristicBean> characteristicBeanList = characteristicDao.selectBatchIds(chaIdList);
            List<CharaRefBean> charaRefBeanList = new ArrayList<>();
            if (!CollectionsUtils.isEmpty(characteristicBeanList)) {
                for (CharacteristicBean characteristicBean : characteristicBeanList) {
                    CharaRefBean charaRefBean = new CharaRefBean();
                    charaRefBean.setBuildId(buildingBean.getBuildId());
                    charaRefBean.setHouseId(characteristicBean.getChaId());
                    charaRefBean.setHouseName(characteristicBean.getChaName());
                    charaRefBeanList.add(charaRefBean);
                }
                charaRefDao.insertChaRefBatch(charaRefBeanList);
            }

        }
    }

    private void bindBhtRef(BuildReceive buildingBean) {
        List<Integer> bhtIdList = buildingBean.getBhtIdList();

        if (!CollectionsUtils.isEmpty(bhtIdList)) {
            List<BuildingHorseTypeBean> horseTypeBeans = buildingHorseTypeDao.selectBatchIds(bhtIdList);
            List<BhtRefBean> bhtRefBeans = new ArrayList<>();
            if (!CollectionsUtils.isEmpty(horseTypeBeans)) {
                for (BuildingHorseTypeBean horseTypeBean : horseTypeBeans) {
                    BhtRefBean bhtRefBean = new BhtRefBean();
                    bhtRefBean.setBuildId(buildingBean.getBuildId());
                    bhtRefBean.setBhtId(horseTypeBean.getBhtId());
                    bhtRefBean.setBhtName(horseTypeBean.getBhtName());
                    bhtRefBeans.add(bhtRefBean);
                }
                bhtRefDao.insertBhtRefBatch(bhtRefBeans);
            }
        }
    }

    @Override
    public BuildingBean patchById(BuildReceive buildingBean, MultipartFile[] effectImg, MultipartFile[] enPlanImg,
                          MultipartFile[] buildRealImg, MultipartFile[] matchingRealImg, MultipartFile[] headImg, MultipartFile[] regionImg, MultipartFile[] video) throws Exception {
        // 如果联系方式为空
        if (StringUtils.isBlank(buildingBean.getTel())) {
            buildingBean.setTel(BuildConstant.MODIFY_TEL);
        }

        // 更新楼盘数据
        buildingDao.updateById(buildingBean);

        // 更新楼盘户型关系
        List<Integer> bhtIdList = buildingBean.getBhtIdList();
        if (!CollectionsUtils.isEmpty(bhtIdList)) {
            bhtRefDao.deleteBhtRefByBid(buildingBean.getBuildId());
            bindBhtRef(buildingBean);
        }
        // 更新楼盘特色关系
        List<Integer> chaIdList = buildingBean.getChaIdList();
        if (!CollectionsUtils.isEmpty(chaIdList)) {
            charaRefDao.deleteCharaRefByBid(buildingBean.getBuildId());
            bindCharaRef(buildingBean);
        }

        //更新楼盘地铁关系
        List<Integer> metroIdList = buildingBean.getMetroIdList();
        if (!CollectionsUtils.isEmpty(metroIdList)) {
            metroBuildRefDao.deleteMBRefByBid(buildingBean.getBuildId());
            bindMBRef(buildingBean);
        }

        // 获取图片类型
        List<ImgTypeBean> imgTypeList = imgTypeDao.getAllImgType();
        // 更新图片
        updatePicture(buildingBean, imgTypeList, effectImg, enPlanImg, buildRealImg, matchingRealImg, headImg, regionImg, video);

        return buildingBean;
    }

    @Override
    public List<BuildingBean> getAllBuild() {
        return buildingDao.getAllBuild();
    }


    /**
     * 首页查询
     *
     * @param
     * @return
     */
    @Override
    public BuildMainBean getAllByBuildName() {


        BuildMainBean buildMainBean = new BuildMainBean();

        // 推荐楼盘
        List<BuildReturn> recommendList = buildingDao.getRecommend();
        //头图
        getHeadImg(recommendList, 1);
        buildMainBean.setCommendList(recommendList);
        // 品质楼盘
        List<BuildReturn> qualityList = buildingDao.getQuality();
        //头图
        getHeadImg(qualityList, 2);
        buildMainBean.setQualityList(qualityList);
        // 优选楼盘
        List<BuildReturn> optimizationList = buildingDao.getOptimization();
        if (!CollectionsUtils.isEmpty(optimizationList)) {
            // 热销
            List<BuildReturn> beans = optimizationList.stream().sorted(Comparator.comparing(BuildingBean::getSellWell).reversed()).limit(4).collect(toList());
            //头图
            getHeadImg(beans, 3);
            // 热搜
            List<BuildReturn> beans1 = optimizationList.stream().sorted(Comparator.comparing(BuildingBean::getHotSearch).reversed()).limit(4).collect(toList());
            //头图
            getHeadImg(beans1, 3);
            // 人气
            List<BuildReturn> beans2 = optimizationList.stream().sorted(Comparator.comparing(BuildingBean::getPopularity).reversed()).limit(4).collect(toList());
            //头图
            getHeadImg(beans2, 3);

            buildMainBean.setNewSellWellList(beans);
            buildMainBean.setNewHotSearchList(beans1);
            buildMainBean.setNewPopularityList(beans2);
        }
        return buildMainBean;
    }

    /**
     * 楼盘详情
     *
     * @param buildingBean
     * @return
     */
    @Override
    public BuildReturn getBuildByBuildId(BuildReceive buildingBean) {
        BuildReturn build = buildingDao.getBuildById(buildingBean);

        if (null != build) {
            List<BuildingTypeBean> allBuildingType = buildingTypeDao.getAllBuildingType();
            if (!CollectionsUtils.isEmpty(allBuildingType)) {
                Map<Integer, List<BuildingTypeBean>> map1 = allBuildingType.stream().collect(Collectors.groupingBy(BuildingTypeBean::getBtId));
                List<BuildingTypeBean> buildingTypeBeans = map1.get(build.getBuildType());
                if (!CollectionsUtils.isEmpty(buildingTypeBeans)) {
                    build.setBtName(buildingTypeBeans.get(0).getBtName());
                }

            }
            // 获取所有位置
            Map<Integer, List<LocationDistinguishBean>> listMap = getLdMap();

            List<BuildingImgBean> imgByBuildId = buildingImgDao.getAllImgByBuildId(new BuildingImgBean().setBuildId(build.getBuildId()));
            if (!CollectionsUtils.isEmpty(imgByBuildId)) {
                Map<Integer, List<BuildingImgBean>> map = imgByBuildId.stream().collect(Collectors.groupingBy(BuildingImgBean::getItId));
                // 视频
                List<BuildingImgBean> imgBeans5 = map.get(7);
                if (!CollectionsUtils.isEmpty(imgBeans5)) {
                    build.setVideoPath(ImgPathConstant.INTERFACE_PATH.concat(imgBeans5.get(0).getImgPath()));
                }

            }

            if (null != listMap && build.getLdId() != null) {
                List<LocationDistinguishBean> beans = listMap.get(build.getLdId());
                if (!CollectionsUtils.isEmpty(beans)) {
                    build.setLdName(beans.get(0).getLdName());
                }
            }

            // 户型
            List<BhtRefBean> bhtRefBeans = bhtRefDao.getAllBhtRefByBuildId(build.getBuildId());
            if (!CollectionsUtils.isEmpty(bhtRefBeans)) {
                Map<Integer, List<BhtRefBean>> bhtRefMap = bhtRefBeans.stream().collect(Collectors.groupingBy(BhtRefBean::getBhtId));
                List<HouseTypeNum> houseTypeNumList = new ArrayList<>();
                for (Integer bhtId : bhtRefMap.keySet()) {
                    HouseTypeNum houseTypeNum = new HouseTypeNum();
                    List<BhtRefBean> beans = bhtRefMap.get(bhtId);
                    houseTypeNum.setHouseName(beans.get(0).getBhtName());
                    houseTypeNum.setHouseId(beans.get(0).getBhtId());
                    houseTypeNum.setHouseNum(beans.size());
                    houseTypeNumList.add(houseTypeNum);
                }
            }
            // 特色标签
            List<CharaRefBean> charaByBuildId = charaRefDao.getCharaByBuildId(build.getBuildId());
            if (!CollectionsUtils.isEmpty(charaByBuildId)) {
                List<String> collect = charaByBuildId.stream().map(CharaRefBean::getHouseName).collect(toList());
                build.setCharaNameList(collect);
            }


        }
        return build;
    }

    private Map<Integer, List<LocationDistinguishBean>> getLdMap() {
        List<LocationDistinguishBean> allDistinguishList = locationDistinguishDao.getAllDistinguish();
        Map<Integer, List<LocationDistinguishBean>> listMap = null;
        if (!CollectionsUtils.isEmpty(allDistinguishList)) {
            listMap = allDistinguishList.stream().collect(Collectors.groupingBy(LocationDistinguishBean::getLdId));
        }
        return listMap;
    }

    private List<String> getMobilePicPath(Map<Integer, List<BuildingImgBean>> map) {
        List<String> mobilePathList = new ArrayList<>();

        // 环境规划图
        List<BuildingImgBean> imgBeans1 = map.get(2);
        if (!CollectionsUtils.isEmpty(imgBeans1)) {
            List<BuildingImgBean> collect1 = imgBeans1.stream().limit(2).collect(toList());
            List<String> pathList = getPathList(collect1);
            mobilePathList.addAll(pathList);
        }
        // 楼盘实景
        List<BuildingImgBean> imgBeans2 = map.get(3);
        if (!CollectionsUtils.isEmpty(imgBeans2)) {
            List<BuildingImgBean> collect3 = imgBeans2.stream().limit(2).collect(toList());
            List<String> pathList = getPathList(collect3);
            mobilePathList.addAll(pathList);
        }
        // 配套实景
        List<BuildingImgBean> imgBeans3 = map.get(4);
        if (!CollectionsUtils.isEmpty(imgBeans3)) {
            List<BuildingImgBean> collect4 = imgBeans3.stream().limit(2).collect(toList());
            List<String> pathList = getPathList(collect4);
            mobilePathList.addAll(pathList);
        }

        // 效果图实体
        List<BuildingImgBean> imgBeans = map.get(1);
        if (!CollectionsUtils.isEmpty(imgBeans)) {
            List<BuildingImgBean> collect = imgBeans.stream().limit(2).collect(toList());
            List<String> pathList = getPathList(collect);
            mobilePathList.addAll(pathList);
        }
        return mobilePathList;
    }
    private List<String> getPathList(List<BuildingImgBean> imgBeans) {
        List<String> pathList = new ArrayList<>();
        for (BuildingImgBean buildingImgBean : imgBeans) {
            String path = ImgPathConstant.INTERFACE_PATH.concat(buildingImgBean.getImgPath()).concat("&imgId=").concat(buildingImgBean.getImgId().toString());
            pathList.add(path);
        }
        return pathList;
    }

    private List<String> getStrings(List<String> effectList) {
        List<String> pathList = new ArrayList<>();
        for (String e : effectList) {
            pathList.add(ImgPathConstant.INTERFACE_PATH.concat(e));
        }
        return pathList;
    }


    /**
     * 文章页 热门
     */
    @Override
    public List<BuildReturn> getSellWell() {

        List<BuildReturn> sellWell = buildingDao.getSellWell();
        getHeadImg(sellWell, 2);
        return sellWell;
    }

    /**
     * 条件筛选页 热门
     */
    @Override
    public BuildMainBean getHot() {
        BuildMainBean buildMainBean = new BuildMainBean();
        List<BuildReturn> hotSBuild = buildingDao.getHotSBuild();
        getHeadImg(hotSBuild, 2);
        buildMainBean.setNewHotSearchList(hotSBuild);
        List<BuildReturn> pHotBuild = buildingDao.getPHotBuild();
        getHeadImg(pHotBuild, 2);
        buildMainBean.setNewPopularityList(pHotBuild);
        List<BuildReturn> swBuild = buildingDao.getSWBuild();
        getHeadImg(swBuild, 2);
        buildMainBean.setNewSellWellList(swBuild);
        return buildMainBean;
    }

    @Override
    public List<BuildReturn> getRecommend() {
        List<BuildReturn> beanList = buildingDao.getRecommend();
        if (!CollectionsUtils.isEmpty(beanList)) {
            getHeadImg(beanList, 2);
        }
        return beanList;
    }

    @Override
    public Page<BuildReturn> getBuildLikePage(BuildReceive buildingBean) {
        Page<BuildReturn> page = new Page<>();
        page.setCurrent(StringUtils.isBlank(buildingBean.getCurrent()) ? 1L : Long.parseLong(buildingBean.getCurrent()));
        page.setSize(StringUtils.isBlank(buildingBean.getPageSize()) ? 10L : Long.parseLong(buildingBean.getPageSize()));


        List<BuildReturn> beanPageList = buildingDao.getBuildLikePage(page, buildingBean);
        if (!CollectionsUtils.isEmpty(beanPageList)) {
            // 获取楼盘id
            List<Integer> list = beanPageList.stream().map(BuildingBean::getBuildId).collect(toList());

            // 获取所有类型
            Map<Integer, List<BuildingTypeBean>> btMap = getBtMap();

            //获取所有特色
            Map<Integer, List<CharaRefBean>> charaRefMap = getChaMap(list);

            // 翻译楼盘户型
            Map<Integer, List<BhtRefBean>> bhtRefMap = getBhtMap(list);

            // 翻译出售状态
            Map<Integer, List<SaleTypeBean>> saleMap = getSaleMap();


            // 获取咨询师名字 联系方式
//            CounselorCommentBean commentBean = new CounselorCommentBean();
//            commentBean.setBIdList(list);
//            List<CounselorCommentBean> cidByBidList = counselorCommentDao.getCidByBidList(commentBean);

            // 获取头图
            BuildingImgBean buildingImgBean = new BuildingImgBean();
            buildingImgBean.setBIdList(list);
            buildingImgBean.setItId(6);

            Map<Integer, List<BuildingImgBean>> headImgMap = getHeadImgMap(buildingImgBean);

            buildingImgBean.setItId(7);
            Map<Integer, List<BuildingImgBean>> videoMap = getHeadImgMap(buildingImgBean);

            for (BuildReturn bean : beanPageList) {
                // 类型
                if (null != btMap && bean.getBuildType() != null) {
                    bean.setBtName(btMap.get(bean.getBuildType()).get(0).getBtName());
                }

                // 特色
                setChaName(charaRefMap, bean);

                // 获取咨询师名字 联系方式
//                if (null != cidByBidList) {
//                    Map<Integer, List<CounselorCommentBean>> collect = cidByBidList.stream().collect(Collectors.groupingBy(CounselorCommentBean::getBId));
//                    List<CounselorCommentBean> commentBeans = collect.get(bean.getBuildId());
//                    if (null != commentBeans) {
////                        List<CounselorBean> cNameList = new ArrayList<>();
////                        CounselorBean counselorBean = new CounselorBean();
////                        String join = StringUtils.join(cNameList, "、");
//                        bean.setCouName(commentBeans.get(0).getCouName());
//                        bean.setTel(commentBeans.get(0).getTel());
//                    }
//                }

                // 头图名字 路径
                setImgPath(headImgMap, bean);
                // 设置视频 路径
                setVideoPath(videoMap, bean);

            }
        }
        return page.setRecords(beanPageList);
    }

    // 设置标签
    private void setChaName(Map<Integer, List<CharaRefBean>> charaRefMap, BuildReturn bean) {
        if (null != charaRefMap && null != bean.getBuildId()) {

            List<CharaRefBean> charaRefBeanList = charaRefMap.get(bean.getBuildId());
            if (null != charaRefBeanList && charaRefBeanList.size() > 0) {
                List<String> charaRefList = charaRefBeanList.stream().map(CharaRefBean::getHouseName).collect(toList());
                bean.setCharaNameList(charaRefList);
                bean.setChaIdList(charaRefBeanList.stream().map(CharaRefBean::getHouseId).collect(toList()));
            }
        }
    }

    // 设置视频路径
    private void setVideoPath(Map<Integer, List<BuildingImgBean>> videoMap, BuildReturn bean) {
        if (null != videoMap) {
            List<BuildingImgBean> imgBeans = videoMap.get(bean.getBuildId());
            if (null != imgBeans && imgBeans.size() > 0) {
                bean.setVideoPath(ImgPathConstant.INTERFACE_PATH.concat(imgBeans.get(0).getImgPath()));
            }
        }
    }

    // 设置头图路径
    private void setImgPath(Map<Integer, List<BuildingImgBean>> headImgMap, BuildReturn bean) {
        if (null != headImgMap && null != bean.getBuildId()) {

            List<BuildingImgBean> buildingImgBeans = headImgMap.get(bean.getBuildId());
            if (null != buildingImgBeans && buildingImgBeans.size() > 0) {
                bean.setImgPath(ImgPathConstant.INTERFACE_PATH.concat(buildingImgBeans.get(0).getImgPath()));
            }
        }
    }

    // 获取查询出的楼盘 所有头图
    private Map<Integer, List<BuildingImgBean>> getHeadImgMap(BuildingImgBean buildingImgBean) {
        List<BuildingImgBean> byBuildId = buildingImgDao.getHeadImgByBuildId(buildingImgBean);
        Map<Integer, List<BuildingImgBean>> headImgMap = null;
        if (null != byBuildId && byBuildId.size() > 0) {
            headImgMap = byBuildId.stream().collect(Collectors.groupingBy(BuildingImgBean::getBuildId));
        }
        return headImgMap;
    }

    // 获取所有出售情况
    private Map<Integer, List<SaleTypeBean>> getSaleMap() {
        List<SaleTypeBean> allSaleType = saleTypeDao.getAllSaleType();
        Map<Integer, List<SaleTypeBean>> saleMap = null;
        if (null != allSaleType && allSaleType.size() > 0) {
            saleMap = allSaleType.stream().collect(Collectors.groupingBy(SaleTypeBean::getStId));
        }
        return saleMap;
    }

    // 获取所有户型
    private Map<Integer, List<BhtRefBean>> getBhtMap(List<Integer> list) {
        List<BhtRefBean> bhtRefBeanList = bhtRefDao.getAllBhtRefByBIds(new BhtRefBean().setBuildIdList(list));
        Map<Integer, List<BhtRefBean>> bhtRefMap = null;
        if (null != bhtRefBeanList && bhtRefBeanList.size() > 0) {
            bhtRefMap = bhtRefBeanList.stream().collect(Collectors.groupingBy(BhtRefBean::getBuildId));
        }
        return bhtRefMap;
    }

    // 获取 查询出的楼盘 的标签
    private Map<Integer, List<CharaRefBean>> getChaMap(List<Integer> list) {
        List<CharaRefBean> chaRefByBidList = charaRefDao.getChaRefByBidList(new CharaRefBean().setBuildIdList(list));
        Map<Integer, List<CharaRefBean>> charaRefMap = null;
        if (null != chaRefByBidList && chaRefByBidList.size() > 0) {
            charaRefMap = chaRefByBidList.stream().collect(Collectors.groupingBy(CharaRefBean::getBuildId));
        }
        return charaRefMap;
    }

    // 获取所有类型
    private Map<Integer, List<BuildingTypeBean>> getBtMap() {
        List<BuildingTypeBean> buildTypeList = buildingTypeDao.getAllBuildingType();
        Map<Integer, List<BuildingTypeBean>> btMap = null;
        if (null != buildTypeList && buildTypeList.size() > 0) {
            btMap = buildTypeList.stream().collect(Collectors.groupingBy(BuildingTypeBean::getBtId));
        }
        return btMap;
    }

    private void getHeadImg(List<BuildReturn> beans, Integer type) {
        List<Integer> list = beans.stream().map(BuildingBean::getBuildId).collect(toList());
        // 获取头图
        BuildingImgBean buildingImgBean = new BuildingImgBean();
        buildingImgBean.setBIdList(list);
        buildingImgBean.setItId(6);
        List<BuildingImgBean> byBuildId = buildingImgDao.getHeadImgByBuildId(buildingImgBean);
        Map<Integer, List<BuildingImgBean>> listMap = null;
        if (!CollectionsUtils.isEmpty(byBuildId)) {
            listMap = byBuildId.stream().collect(Collectors.groupingBy(BuildingImgBean::getBuildId));
        }
        for (BuildReturn buildingBean1 : beans) {
            List<String> labelList = new ArrayList<>();
            if (null != listMap) {
                List<BuildingImgBean> imgBeans = listMap.get(buildingBean1.getBuildId());
                // 设置头图名字、路径
                if (!CollectionsUtils.isEmpty(imgBeans)) {
                    buildingBean1.setImgPath(ImgPathConstant.INTERFACE_PATH.concat(imgBeans.get(0).getImgPath()));
                }
            }

            // 人气 热搜 热销 判断
            if (type == 1) {
                if (buildingBean1.getHotSearch() != null) {
                    labelList.add("热搜");
                }
                if (buildingBean1.getPopularity() != null) {
                    labelList.add("人气");
                }
                if (buildingBean1.getHotSearch() > buildingBean1.getPopularity()) {
                    buildingBean1.setLabel("热搜");
                } else {
                    buildingBean1.setLabel("人气");
                }
                buildingBean1.setLabelList(labelList);
            }
        }
    }

    // 更新图片
    private void updatePicture(BuildReceive buildingBean, List<ImgTypeBean> imgTypeList, MultipartFile[] effectImg, MultipartFile[] enPlanImg,
                               MultipartFile[] buildRealImg, MultipartFile[] matchingRealImg, MultipartFile[] headImg, MultipartFile[] regionImg, MultipartFile[] video) throws Exception {

        Map<String, List<ImgTypeBean>> listMap = imgTypeList.stream().collect(Collectors.groupingBy(ImgTypeBean::getItName));
        BuildingImgBean buildingImgBean = new BuildingImgBean();
        buildingImgBean.setBuildId(buildingBean.getBuildId());

        if (null != effectImg && effectImg.length > 0) {

//            buildingImgBean.setItId(listMap.get(ImgTypeConstant.effectImg).get(0).getItId());
//            deleteImg(buildingImgBean);
//            buildingImgDao.deleteByImgName(buildingImgBean);
            this.saveFile(buildingBean, effectImg, "effectImg", listMap.get(ImgTypeConstant.effectImg).get(0).getItId());
        }

        if (null != enPlanImg && enPlanImg.length > 0) {

//            buildingImgBean.setItId(listMap.get(ImgTypeConstant.enPlanImg).get(0).getItId());
//            deleteImg(buildingImgBean);
//            buildingImgDao.deleteByImgName(buildingImgBean);
            this.saveFile(buildingBean, enPlanImg, "enPlanImg", listMap.get(ImgTypeConstant.enPlanImg).get(0).getItId());
        }

        if (null != buildRealImg && buildRealImg.length > 0) {
//            buildingImgBean.setItId(listMap.get(ImgTypeConstant.buildRealImg).get(0).getItId());
//            deleteImg(buildingImgBean);
//            buildingImgDao.deleteByImgName(buildingImgBean);
            this.saveFile(buildingBean, buildRealImg, "buildRealImg", listMap.get(ImgTypeConstant.buildRealImg).get(0).getItId());
        }

        if (null != matchingRealImg && matchingRealImg.length > 0) {
//            buildingImgBean.setItId(listMap.get(ImgTypeConstant.matchingRealImg).get(0).getItId());
//            deleteImg(buildingImgBean);
//            buildingImgDao.deleteByImgName(buildingImgBean);
            this.saveFile(buildingBean, matchingRealImg, "matchingRealImg", listMap.get(ImgTypeConstant.matchingRealImg).get(0).getItId());
        }

        if (null != headImg && headImg.length > 0) {
            buildingImgBean.setItId(listMap.get(ImgTypeConstant.headImg).get(0).getItId());
            deleteImg(buildingImgBean);
            buildingImgDao.deleteByImgName(buildingImgBean);
            this.saveFile(buildingBean, headImg, "headImg", listMap.get(ImgTypeConstant.headImg).get(0).getItId());
        }

        if (null != regionImg && regionImg.length > 0) {
//            buildingImgBean.setItId(listMap.get(ImgTypeConstant.regionImg).get(0).getItId());
//            deleteImg(buildingImgBean);
//            buildingImgDao.deleteByImgName(buildingImgBean);
            this.saveFile(buildingBean, regionImg, "regionImg", listMap.get(ImgTypeConstant.regionImg).get(0).getItId());
        }
        if (null != video && video.length > 0) {
            buildingImgBean.setItId(listMap.get(ImgTypeConstant.video).get(0).getItId());
            deleteImg(buildingImgBean);
            buildingImgDao.deleteByImgName(buildingImgBean);
            this.saveFile(buildingBean, video, "video", listMap.get(ImgTypeConstant.video).get(0).getItId());
        }
    }

    private void deleteImg(BuildingImgBean buildingImgBean) {
        // 删除图片 以及图片表中的数据
        List<BuildingImgBean> allByBid = buildingImgDao.getAllByBid(buildingImgBean);
        if (null != allByBid) {
            for (BuildingImgBean bean : allByBid) {
                delFile(bean.getImgPath());
                System.out.println(bean.getImgPath());
            }
        }
    }


    public static void delFile(String filePathAndName) {
        try {
            String filePath = filePathAndName;
            java.io.File myDelFile = new java.io.File(filePath);
            myDelFile.delete();
        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            e.printStackTrace();
        }
    }


    //保存文件
    private void saveFile(BuildingBean buildingBean, MultipartFile[] file, String type, Integer typeId) throws Exception {
        if (file != null) {

            int i = 1;
            for (MultipartFile multipartFile : file) {
                BuildingImgBean buildingImgBean = new BuildingImgBean();
                //原文件名
                String fileName = multipartFile.getOriginalFilename();
                fileName = fileName.substring(fileName.lastIndexOf("."));

//                File directory = new File("");// 参数为空
//                String path = directory.getCanonicalPath();
//                System.out.println("路径a：" + path);
//                String imgName = buildingBean.getBuildId().toString().concat(fileName);
                File dir = new File(buildStoreDir + ImgPathConstant.BUILD_PATH + buildingBean.getBuildId() + "/" + type);
//                System.out.println("dir:" + dir.getPath());
                if (!dir.exists() && !dir.isDirectory()) dir.mkdirs();

                String name = UUID.randomUUID().toString().replace("-", "").concat(fileName);
//                System.out.println("name:" + name);

                i++;
                System.out.println("路径" + dir.getPath());
                String replace = dir.getPath().replace("\\", "/");
                String path = replace + "/" + name;

                System.out.println("path:" + path);

                //读写文件
                if (!multipartFile.isEmpty()) {
                    InputStream is = multipartFile.getInputStream();
                    int len = 0;
                    byte[] by = new byte[1024];
                    OutputStream os = new FileOutputStream(path);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    BufferedOutputStream bos = new BufferedOutputStream(os);
                    while ((len = bis.read(by)) != -1) {
                        bos.write(by, 0, len);
                        bos.flush();
                    }
                    if (null != bos)
                        bos.close();
                    if (null != bis)
                        bis.close();
                    if (null != os)
                        os.close();
                    if (null != is)
                        is.close();
                }

                buildingImgBean.setImgName(name);
                buildingImgBean.setBuildId(buildingBean.getBuildId());
                buildingImgBean.setCreateDate(new Date());
                buildingImgBean.setItId(typeId);
                buildingImgBean.setImgPath(path);
                buildingImgDao.addImg(buildingImgBean);
            }
        }
    }

    @Override
    public List<Map<String, Object>> uploadFile(MultipartFile[] file) throws Exception {
        if (file == null || file.length <= 0) throw new MessageException("未接收到文件!");
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (MultipartFile multipartFile : file) {
            //原文件名
            String fileName = multipartFile.getOriginalFilename();
            String srcName = fileName;
            //生成文件名
            fileName = fileName.substring(fileName.lastIndexOf("."));

            String path = buildStoreDir + ImgPathConstant.MOBILE_INTRODUCE;
            File dir = new File(path);
            if (!dir.exists() && !dir.isDirectory()) dir.mkdirs();
            String replace = dir.getPath().replace("\\", "/");
            path = replace.concat("/").concat(UUID.randomUUID().toString().replace("-", "")).concat(fileName);
            System.out.println("路径" + path);
            Map<String, Object> map = new HashMap<>();
            map.put("srcName", srcName);
            map.put("fileUrl", ImgPathConstant.INTERFACE_PATH.concat(path));
            mapList.add(map);
            generateFile(multipartFile, path);
        }
        return mapList;
    }


    private void generateFile(MultipartFile multipartFile, String path) throws Exception {
        //读写文件
        if (!multipartFile.isEmpty()) {
            InputStream is = multipartFile.getInputStream();
            int len = 0;
            byte[] by = new byte[1024];
            OutputStream os = new FileOutputStream(path);
            BufferedInputStream bis = new BufferedInputStream(is);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            while ((len = bis.read(by)) != -1) {
                bos.write(by, 0, len);
                bos.flush();
            }
            if (bos != null)
                bos.close();
            if (bis != null)
                bis.close();
            if (os != null)
                os.close();
            if (is != null)
                is.close();
        }
    }

    public void deleteImgFile(BuildingImgBean buildingImgBean) {
        //删除文件
        BuildingImgBean img = buildingImgDao.getImgById(buildingImgBean);
        if (null != img) {
            delFile(img.getImgPath());
        }

        // 删除表中数据
        buildingImgDao.deleteImgById(buildingImgBean);
    }

    @Override
    public List<BuildReturn> getSelected() {
        List<BuildReturn> mobileList = buildingDao.getMobileList(new BuildingBean());
        if (!CollectionsUtils.isEmpty(mobileList)) {
            getHeadImg(mobileList,2);
        }
        return mobileList;
    }



    @Override
    public List<BuildReturn> getGuestLike() {
        List<BuildReturn> mobileList = buildingDao.getMobileList(new BuildingBean());
        if (!CollectionsUtils.isEmpty(mobileList)) {
            List<BuildReturn> buildReturns = mobileList.stream().sorted(Comparator.comparing(BuildingBean::getSort)).limit(4).collect(toList());
//            getHeadImg(buildReturns,2);
            // 楼盘ID集合
            List<Integer> list = buildReturns.stream().map(BuildingBean::getBuildId).collect(toList());
            // 获取头图
            BuildingImgBean buildingImgBean = new BuildingImgBean();
            buildingImgBean.setBIdList(list);
            buildingImgBean.setItId(6);
            Map<Integer, List<BuildingImgBean>> headImgMap = getHeadImgMap(buildingImgBean);

            // 获取所有类型
            Map<Integer, List<BuildingTypeBean>> btMap = getBtMap();

            // 获取所有区域
            List<LocationDistinguishBean> allDistinguish = locationDistinguishDao.getAllDistinguish();
            Map<Integer, List<LocationDistinguishBean>> ldMap = null;
            if (!CollectionsUtils.isEmpty(allDistinguish)) {
                ldMap = allDistinguish.stream().collect(Collectors.groupingBy(LocationDistinguishBean::getLdId));
            }
            for (BuildReturn buildReturn : buildReturns) {
                // 类型
                if (null != btMap && buildReturn.getBuildType() != null) {
                    buildReturn.setBtName(btMap.get(buildReturn.getBuildType()).get(0).getBtName());
                }

                // 头图名字 路径
                setImgPath(headImgMap, buildReturn);

                // 区域
                if (null != ldMap && buildReturn.getLdId() != null) {
                    buildReturn.setLdName(ldMap.get(buildReturn.getLdId()).get(0).getLdName());
                }
            }
            return buildReturns;
        }
        return null;
    }

    @Override
    public BuildReturn getDetails(BuildingBean buildingBean) {
        List<BuildReturn> mobileList = buildingDao.getMobileList(buildingBean);
        if (!CollectionsUtils.isEmpty(mobileList)) {
            BuildReturn buildReturn = mobileList.get(0);


            // 特色标签
            List<CharaRefBean> charaByBuildId = charaRefDao.getCharaByBuildId(buildReturn.getBuildId());
            if (!CollectionsUtils.isEmpty(charaByBuildId)) {
                List<String> collect = charaByBuildId.stream().map(CharaRefBean::getHouseName).collect(toList());
                buildReturn.setCharaNameList(collect);
            }

            List<Integer> buildIdList = new ArrayList<>();
            buildIdList.add(buildReturn.getBuildId());
            List<BuildingImgBean> byBuildId = buildingImgDao.getHeadImgByBuildId(new BuildingImgBean().setBIdList(buildIdList));

            if (!CollectionsUtils.isEmpty(byBuildId)) {
                Map<Integer, List<BuildingImgBean>> map = byBuildId.stream().collect(Collectors.groupingBy(BuildingImgBean::getItId));
                List<String> mobilePicPath = getMobilePicPath(map);
            }
            return buildReturn;
        }
        return null;
    }
}