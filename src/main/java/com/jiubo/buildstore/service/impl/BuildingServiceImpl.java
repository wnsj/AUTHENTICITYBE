package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.*;

import com.jiubo.buildstore.common.ImgTypeConstant;
import com.jiubo.buildstore.dao.*;
import com.jiubo.buildstore.service.BuildingAnalysisService;
import com.jiubo.buildstore.service.BuildingHorseTypeService;
import com.jiubo.buildstore.service.BuildingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jiubo.buildstore.common.ImgTypeConstant.*;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
@Service
public class BuildingServiceImpl extends ServiceImpl<BuildingDao, BuildingBean> implements BuildingService {


    @Autowired
    private BuildingDao buildingDao;

    @Autowired
    private BuildingAnalysisService buildingAnalysisService;

    @Autowired
    private BuildingHorseTypeService buildingHorseTypeService;

    @Autowired
    private SaleTypeDao saleTypeDao;

    @Autowired
    private CounselorCommentDao counselorCommentDao;

    @Autowired
    private ImgTypeDao imgTypeDao;

    @Autowired
    private BuildingImgDao buildingImgDao;

    @Autowired
    private BuildingTypeDao buildingTypeDao;

    @Autowired
    private CharacteristicDao characteristicDao;

    @Override

    public Page<BuildingBean> getAllBulidBypage(BuildingBean buildingBean) {
        Page<BuildingBean> page = new Page<>();
        page.setCurrent(StringUtils.isBlank(buildingBean.getCurrent()) ? 1L : Long.parseLong(buildingBean.getCurrent()));
        page.setSize(StringUtils.isBlank(buildingBean.getPageSize()) ? 10L : Long.parseLong(buildingBean.getPageSize()));

        // 楼盘数据
        List<BuildingBean> allBulidBypage = buildingDao.getAllBulidBypage(page, buildingBean);

        if (null != allBulidBypage && allBulidBypage.size() > 0) {

            // 获取所有类型
            List<BuildingTypeBean> buildTypeList = buildingTypeDao.getAllBuildingType();
            Map<Integer, List<BuildingTypeBean>> btMap = null;
            if (null != buildTypeList && buildTypeList.size() > 0) {
                btMap = buildTypeList.stream().collect(Collectors.groupingBy(BuildingTypeBean::getBtId));
            }
            //获取所有特色
            List<CharacteristicBean> charaList = characteristicDao.getAllChara();
            Map<Integer, List<CharacteristicBean>> charaMap = null;
            if (null != charaList && charaList.size() > 0) {
                charaMap = charaList.stream().collect(Collectors.groupingBy(CharacteristicBean::getChaId));
            }

            // 获取户型id集合
            List<Integer> bhtIdList = buildingBean.getBhtIdList();

            if (null != bhtIdList && bhtIdList.size() > 0) {
                BuildingAnalysisBean buildingAnalysisBean = new BuildingAnalysisBean();
                buildingAnalysisBean.setBhtIdList(bhtIdList);
                // 获取户型分析表中楼盘ID
                List<BuildingAnalysisBean> bidByBhtIdList = buildingAnalysisService.getBidByBhtIdList(buildingAnalysisBean);
                List<Integer> buildIdList = bidByBhtIdList.stream().map(BuildingAnalysisBean::getBuildId).collect(toList());
                buildingBean.setBuildIdList(buildIdList);
            }


            // 翻译楼盘户型
            // 获取户型分析数据
            List<BuildingHorseTypeBean> allHorseType = buildingHorseTypeService.getAllHorseType();

            // 户型map 通过户型分析中的户型id和楼盘id到户型表中翻译户型
            Map<Integer, List<BuildingHorseTypeBean>> integerListMap = allHorseType.stream().collect(Collectors.groupingBy(BuildingHorseTypeBean::getBhtId));

            // 获取楼盘id
            List<Integer> list = allBulidBypage.stream().map(BuildingBean::getBuildId).collect(toList());

            // 根据楼盘id抓取户型分析数据
            BuildingAnalysisBean buildingAnalysisBean = new BuildingAnalysisBean();
            buildingAnalysisBean.setBIdList(list);
            List<BuildingAnalysisBean> bidByBIdList = buildingAnalysisService.getBidByBIdList(buildingAnalysisBean);
            Map<Integer, List<BuildingAnalysisBean>> listMap = null;
            if (null != bidByBIdList && bidByBIdList.size() > 0) {
                listMap = bidByBIdList.stream().collect(Collectors.groupingBy(BuildingAnalysisBean::getBuildId));
            }

            // 翻译出售状态
            List<SaleTypeBean> allSaleType = saleTypeDao.getAllSaleType();
            Map<Integer, List<SaleTypeBean>> saleMap = null;
            if (null != allSaleType && allSaleType.size() > 0) {
                saleMap = allSaleType.stream().collect(Collectors.groupingBy(SaleTypeBean::getStId));
            }
            // 获取咨询师名字 联系方式
//            CounselorCommentBean commentBean = new CounselorCommentBean();
//            commentBean.setBIdList(list);
//            List<CounselorCommentBean> cidByBidList = counselorCommentDao.getCidByBidList(commentBean);

            // 获取头图
            BuildingImgBean buildingImgBean = new BuildingImgBean();
            buildingImgBean.setBIdList(list);
            buildingImgBean.setItId(6);
            List<BuildingImgBean> byBuildId = buildingImgDao.getHeadImgByBuildId(buildingImgBean);
            Map<Integer, List<BuildingImgBean>> headImgMap = null;
            if (null != byBuildId && byBuildId.size() > 0) {
                headImgMap = byBuildId.stream().collect(Collectors.groupingBy(BuildingImgBean::getBId));
            }

           // 遍历实体 翻译各个类型字段
            for (BuildingBean bean : allBulidBypage) {

                // 户型
                if (null != listMap) {
                    List<String> bhtNameList = new ArrayList<>();
                    List<BuildingAnalysisBean> buildingAnalysisBeans = listMap.get(bean.getBuildId());
                    // 拼接户型
                    if (null != buildingAnalysisBeans && buildingAnalysisBeans.size() > 0) {

                        for (BuildingAnalysisBean bean1 : buildingAnalysisBeans) {
                            List<BuildingHorseTypeBean> buildingHorseTypeBeans = integerListMap.get(bean1.getBhtId());
                            if (null != buildingHorseTypeBeans && buildingHorseTypeBeans.size() > 0) {
                                bhtNameList.add(buildingHorseTypeBeans.get(0).getBhtName());
                            }
                        }
                        List<String> strings = bhtNameList.stream().distinct().collect(toList());
                        // 户型名
                        bean.setCaName(StringUtils.join(strings, "、"));
                    }
                }



                // 开盘时间
                if (null != bean.getOpenDate()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    bean.setOpenDateTime(sdf.format(bean.getOpenDate()));
                }

                // 出售情况
                if (null != allSaleType && bean.getIsSale() != null) {
                    bean.setSaleLabel(saleMap.get(bean.getIsSale()).get(0).getStName());
                }

                // 类型
                if (null != btMap && bean.getBtId() != null) {
                    bean.setBtName(btMap.get(bean.getBtId()).get(0).getBtName());
                }

                // 特色
                if (null != charaMap && bean.getChaId() != null) {
                    bean.setChaName(charaMap.get(bean.getChaId()).get(0).getChaName());
                }
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
                if (null != headImgMap && null != bean.getBuildId()) {

                    List<BuildingImgBean> buildingImgBeans = headImgMap.get(bean.getBuildId());
                    if (null != buildingImgBeans && buildingImgBeans.size() > 0) {
                        bean.setImgName(buildingImgBeans.get(0).getImgName());
                        bean.setImgPath(buildingImgBeans.get(0).getImgPath());
                    }
                }
            }
        }
        return page.setRecords(allBulidBypage);
    }

    @Override
    public void addBuilding(BuildingBean buildingBean, MultipartFile[] effectImg, MultipartFile[] enPlanImg,
                            MultipartFile[] buildRealImg,
                            MultipartFile[] matchingRealImg,
                            MultipartFile[] headImg, MultipartFile[] video) throws Exception {


        BuildingBean byHtName = buildingDao.getAllByHtName(buildingBean);

        if (null == byHtName) {
            // 若不存在 创建
            buildingDao.addBuilding(buildingBean);

        } else {
            // 存在 则更新
            int id = byHtName.getBuildId();
            buildingBean.setBuildId(id);
            buildingDao.patchById(buildingBean);
        }


        List<ImgTypeBean> imgTypeList = imgTypeDao.getAllImgType();
//        System.out.println("effectImg：" + enPlanImg);
        buildingBean.setBuildId(buildingBean.getBuildId());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String ss = sdf.format(date);
        if (null != imgTypeList && imgTypeList.size() > 0) {
            Map<String, List<ImgTypeBean>> listMap = imgTypeList.stream().collect(Collectors.groupingBy(ImgTypeBean::getItName));

            this.saveFile(buildingBean, effectImg, "effectImg", listMap.get(ImgTypeConstant.effectImg).get(0).getItId());
            this.saveFile(buildingBean, enPlanImg, "enPlanImg", listMap.get(ImgTypeConstant.enPlanImg).get(0).getItId());
            this.saveFile(buildingBean, buildRealImg, "buildRealImg", listMap.get(ImgTypeConstant.buildRealImg).get(0).getItId());
            this.saveFile(buildingBean, matchingRealImg, "matchingRealImg", listMap.get(ImgTypeConstant.matchingRealImg).get(0).getItId());
            this.saveFile(buildingBean, headImg, "headImg", listMap.get(ImgTypeConstant.headImg).get(0).getItId());
            this.saveFile(buildingBean, video, "video", listMap.get(ImgTypeConstant.video).get(0).getItId());
        }
    }

    @Override
    public void patchById(BuildingBean buildingBean, MultipartFile[] effectImg, MultipartFile[] enPlanImg,
                          MultipartFile[] buildRealImg, MultipartFile[] matchingRealImg, MultipartFile[] headImg, MultipartFile[] video) throws Exception {
        // 更新楼盘数据
        buildingDao.patchById(buildingBean);
        // 获取图片类型
        List<ImgTypeBean> imgTypeList = imgTypeDao.getAllImgType();
        // 更新图片
        updatePicture(buildingBean, imgTypeList, effectImg, enPlanImg, buildRealImg, matchingRealImg, headImg, video);

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
        List<BuildingBean> recommendList = buildingDao.getRecommend();
        //头图
        getHeadImg(recommendList);
        buildMainBean.setCommendList(recommendList);
        // 品质楼盘
        List<BuildingBean> qualityList = buildingDao.getQuality();
        //头图
        getHeadImg(qualityList);
        buildMainBean.setQualityList(qualityList);
        // 优选楼盘
        List<BuildingBean> optimizationList = buildingDao.getOptimization();
        if (null != optimizationList) {
            // 热销
            List<BuildingBean> beans = optimizationList.stream().sorted(Comparator.comparing(BuildingBean::getSellWell).reversed()).limit(3).collect(toList());
            //头图
            getHeadImg(beans);
            // 热搜
            List<BuildingBean> beans1 = optimizationList.stream().sorted(Comparator.comparing(BuildingBean::getHotSearch).reversed()).limit(3).collect(toList());
            //头图
            getHeadImg(beans1);
            // 人气
            List<BuildingBean> beans2 = optimizationList.stream().sorted(Comparator.comparing(BuildingBean::getPopularity).reversed()).limit(3).collect(toList());
            //头图
            getHeadImg(beans2);

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
    public BuildingBean getBuildByBuildId(BuildingBean buildingBean) {
        BuildingBean build = buildingDao.getBuildById(buildingBean);

        List<BuildingImgBean> imgByBuildId = buildingImgDao.getAllImgByBuildId(new BuildingImgBean().setBId(build.getBuildId()));
        if (null != imgByBuildId) {
            Map<Integer, List<BuildingImgBean>> map = imgByBuildId.stream().collect(Collectors.groupingBy(BuildingImgBean::getItId));
            // 效果图实体
            List<BuildingImgBean> imgBeans = map.get(1);
            if (null != imgBeans) {
                build.setEffectList(imgBeans.stream().map(BuildingImgBean::getImgName).collect(toList()));
            }
            // 环境规划图
            List<BuildingImgBean> imgBeans1 = map.get(2);
            if (null != imgBeans1) {
                build.setEnPlanList(imgBeans1.stream().map(BuildingImgBean::getImgName).collect(toList()));
            }
            // 楼盘实景
            List<BuildingImgBean> imgBeans2 = map.get(3);
            if (null != imgBeans2) {
                build.setBuildReaList(imgBeans2.stream().map(BuildingImgBean::getImgName).collect(toList()));
            }
            // 配套实景
            List<BuildingImgBean> imgBeans3 = map.get(4);
            if (null != imgBeans3) {
                build.setMatchingRealList(imgBeans3.stream().map(BuildingImgBean::getImgName).collect(toList()));
            }
            // 头图
            List<BuildingImgBean> imgBeans4 = map.get(6);
            if (null != imgBeans4) {
                build.setImgName(imgBeans4.get(0).getImgName());
            }
            // 视频
            List<BuildingImgBean> imgBeans5 = map.get(7);
            if (null != imgBeans5) {
                build.setImgName(imgBeans5.get(0).getImgName());
            }
        }
        return build;
    }

    private void getHeadImg(List<BuildingBean> beans) {
        List<Integer> list = beans.stream().map(BuildingBean::getBuildId).collect(toList());
        // 获取头图
        BuildingImgBean buildingImgBean = new BuildingImgBean();
        buildingImgBean.setBIdList(list);
        buildingImgBean.setItId(6);
        List<BuildingImgBean> byBuildId = buildingImgDao.getHeadImgByBuildId(buildingImgBean);
        if (null != byBuildId) {
            Map<Integer, List<BuildingImgBean>> listMap = byBuildId.stream().collect(Collectors.groupingBy(BuildingImgBean::getBId));
            for (BuildingBean buildingBean1 : beans) {
                List<BuildingImgBean> imgBeans = listMap.get(buildingBean1.getBuildId());
                if (null != imgBeans) {
                    buildingBean1.setImgName(imgBeans.get(0).getImgName());
                    buildingBean1.setImgPath(imgBeans.get(0).getImgPath());
                }
                BigDecimal total = buildingBean1.getMinUnitPrice().add(buildingBean1.getMaxUnitPrice());
                BigDecimal average = total.divide(new BigDecimal(2),2,BigDecimal.ROUND_HALF_UP);
                buildingBean1.setAveragePrice(average);
            }
        }
    }

    private void updatePicture(BuildingBean buildingBean, List<ImgTypeBean> imgTypeList, MultipartFile[] effectImg, MultipartFile[] enPlanImg,
                               MultipartFile[] buildRealImg, MultipartFile[] matchingRealImg, MultipartFile[] headImg, MultipartFile[] video) throws Exception {

        Map<String, List<ImgTypeBean>> listMap = imgTypeList.stream().collect(Collectors.groupingBy(ImgTypeBean::getItName));
        BuildingImgBean buildingImgBean = new BuildingImgBean();
        buildingImgBean.setBId(buildingBean.getBuildId());

        if (null != effectImg && effectImg.length > 0) {
            deleteImg(buildingImgBean);
            buildingImgBean.setItId(listMap.get(ImgTypeConstant.effectImg).get(0).getItId());
            buildingImgDao.deleteByImgName(buildingImgBean);
            this.saveFile(buildingBean, effectImg, "effectImg", listMap.get(ImgTypeConstant.effectImg).get(0).getItId());
        }

        if (null != enPlanImg && enPlanImg.length > 0) {
            buildingImgBean.setItId(listMap.get(ImgTypeConstant.enPlanImg).get(0).getItId());
            buildingImgDao.deleteByImgName(buildingImgBean);
            this.saveFile(buildingBean, enPlanImg, "enPlanImg", listMap.get(ImgTypeConstant.enPlanImg).get(0).getItId());
        }

        if (null != buildRealImg && buildRealImg.length > 0) {
            buildingImgBean.setItId(listMap.get(ImgTypeConstant.buildRealImg).get(0).getItId());
            buildingImgDao.deleteByImgName(buildingImgBean);
            this.saveFile(buildingBean, buildRealImg, "buildRealImg", listMap.get(ImgTypeConstant.buildRealImg).get(0).getItId());
        }

        if (null != matchingRealImg && matchingRealImg.length > 0) {
            buildingImgBean.setItId(listMap.get(ImgTypeConstant.matchingRealImg).get(0).getItId());
            buildingImgDao.deleteByImgName(buildingImgBean);
            this.saveFile(buildingBean, matchingRealImg, "matchingRealImg", listMap.get(ImgTypeConstant.matchingRealImg).get(0).getItId());
        }

        if (null != headImg && headImg.length > 0) {
            buildingImgBean.setItId(listMap.get(ImgTypeConstant.headImg).get(0).getItId());
            buildingImgDao.deleteByImgName(buildingImgBean);
            this.saveFile(buildingBean, headImg, "headImg", listMap.get(ImgTypeConstant.headImg).get(0).getItId());
        }

        if (null != video && video.length > 0) {
            buildingImgBean.setItId(listMap.get(ImgTypeConstant.video).get(0).getItId());
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
                String imgName = buildingBean.getBuildId().toString().concat(fileName);
                File dir = new File("D:\\" + buildingBean.getHtName() + "\\" + type);
//                System.out.println("dir:" + dir.getPath());
                if (!dir.exists() && !dir.isDirectory()) dir.mkdirs();

                String name = type + i + "_" + imgName;
//                System.out.println("name:" + name);

                i++;
                String path = dir.getPath() + "\\" + name;
                System.out.println("path:" + path);

//                System.out.println("路径：" + path);
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
                buildingImgBean.setBId(buildingBean.getBuildId());
                buildingImgBean.setCreateDate(new Date());
                buildingImgBean.setItId(typeId);
                buildingImgBean.setImgPath(path);
                buildingImgDao.addImg(buildingImgBean);
            }
        }
    }


}