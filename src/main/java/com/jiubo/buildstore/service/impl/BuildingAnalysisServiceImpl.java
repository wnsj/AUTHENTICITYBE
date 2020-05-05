package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.*;

import com.jiubo.buildstore.common.ImgTypeConstant;
import com.jiubo.buildstore.dao.*;
import com.jiubo.buildstore.service.BuildingAnalysisService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiubo.buildstore.service.BuildingService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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
 * @author syl
 * @since 2020-04-10
 */
@Service
public class BuildingAnalysisServiceImpl extends ServiceImpl<BuildingAnalysisDao, BuildingAnalysisBean> implements BuildingAnalysisService {

    @Autowired
    private BuildingAnalysisDao buildingAnalysisDao;

    @Autowired
    private BuildingDao buildingDao;

    @Autowired
    private SaleTypeDao saleTypeDao;

    @Autowired
    private BuildingImgDao buildingImgDao;

    @Autowired
    private BalRefDao balRefDao;

    @Autowired
    private BuildingAnalysisLabelDao buildingAnalysisLabelDao;

    @Autowired
    private ImgTypeDao imgTypeDao;
    @Override
    public List<BuildingAnalysisBean> getBidByBhtIdList(BuildingAnalysisBean buildingAnalysisBean) {
        return buildingAnalysisDao.getBidByBhtIdList(buildingAnalysisBean);
    }

    @Override
    public List<BuildingAnalysisBean> getBidByBIdList(BuildingAnalysisBean buildingAnalysisBean) {
        return buildingAnalysisDao.getBidByBIdList(buildingAnalysisBean);
    }

    /**
     * 楼盘详情 户型分析分页查询
     *
     * @param buildingAnalysisBean
     * @return
     */
    @Override
    public BuildingAnalysisPageBean getAllAnalysisByBid(BuildingAnalysisBean buildingAnalysisBean) {
        BuildingAnalysisPageBean buildingAnalysisPageBean = new BuildingAnalysisPageBean();
        List<BuildingAnalysisBean> buildAnalysisList;
        Page<BuildingAnalysisBean> page = new Page<>();
        if (null != buildingAnalysisBean && buildingAnalysisBean.getCode() == 1) {
            page.setCurrent(StringUtils.isBlank(buildingAnalysisBean.getCurrent()) ? 1L : Long.parseLong(buildingAnalysisBean.getCurrent()));
            page.setSize(StringUtils.isBlank(buildingAnalysisBean.getPageSize()) ? 3L : Long.parseLong(buildingAnalysisBean.getPageSize()));
            buildAnalysisList = buildingAnalysisDao.getAllAnalysisByBid(page, buildingAnalysisBean);
        } else {
            buildAnalysisList = buildingAnalysisDao.getAllAnalysisByBid(buildingAnalysisBean);
            page.setTotal(buildAnalysisList.size());
        }


        if (null != buildAnalysisList && buildAnalysisList.size() > 0) {
            // 楼盘
            List<BuildingBean> allBuild = buildingDao.getAllBuild();
            Map<Integer, List<BuildingBean>> buildMap = null;
            if (null != allBuild && allBuild.size() > 0) {
                buildMap = allBuild.stream().collect(Collectors.groupingBy(BuildingBean::getBuildId));
            }


            // 所有出售状态
            List<SaleTypeBean> allSaleType = saleTypeDao.getAllSaleType();
            Map<Integer, List<SaleTypeBean>> saleMap = null;
            if (null != allSaleType && allSaleType.size() > 0) {
                saleMap = allSaleType.stream().collect(Collectors.groupingBy(SaleTypeBean::getStId));
            }



            // 获取所有居室
            List<Integer> baIdList = buildAnalysisList.stream().map(BuildingAnalysisBean::getBaId).collect(Collectors.toList());
            // 获取标签关联
            List<BalRefBean> byBaIdList = balRefDao.getRefByBaIdList(new BalRefBean().setBaIdList(baIdList));
            // 获取标签
            List<BuildingAnalysisLabelBean> aLabelList = buildingAnalysisLabelDao.getALabel();
            Map<Integer, List<BuildingAnalysisLabelBean>> balMap = null;
            if (aLabelList != null && aLabelList.size() > 0) {
                balMap = aLabelList.stream().collect(Collectors.groupingBy(BuildingAnalysisLabelBean::getBalId));
            }
            Map<Integer, List<BalRefBean>> refMap = null;
            if (null != byBaIdList && byBaIdList.size() > 0) {
                refMap = byBaIdList.stream().collect(Collectors.groupingBy(BalRefBean::getBaId));
            }



            // 获取所有楼盘id
            List<Integer> list = buildAnalysisList.stream().map(BuildingAnalysisBean::getBuildId).collect(Collectors.toList());
            // 图片名称
            BuildingImgBean buildingImgBean = new BuildingImgBean();
            buildingImgBean.setBIdList(list);
            buildingImgBean.setItId(5);
            List<BuildingImgBean> imgList = buildingImgDao.getHeadImgByBuildId(buildingImgBean);
            Map<Integer, List<BuildingImgBean>> collect = null;
            if (null != imgList) {
                collect = imgList.stream().collect(Collectors.groupingBy(BuildingImgBean::getBaId));
            }


            List<BuildingHorseTypeBean> buildingHorseTypeBeanList = new ArrayList<>();

            Map<Integer, List<BuildingAnalysisBean>> map = buildAnalysisList.stream().collect(Collectors.groupingBy(BuildingAnalysisBean::getBhtId));
            for (Integer bhtId:map.keySet()) {
                List<BuildingAnalysisBean> analysisBeans = map.get(bhtId);
                if (null != analysisBeans && analysisBeans.size()>0){
                    BuildingHorseTypeBean buildingHorseTypeBean = new BuildingHorseTypeBean();
                    // 添加户型及户型id
                    buildingHorseTypeBean.setBhtId(analysisBeans.get(0).getBhtId());
                    buildingHorseTypeBean.setBhtName(analysisBeans.get(0).getImgName());
                    buildingHorseTypeBean.setBhtNum(analysisBeans.size());
                    buildingHorseTypeBeanList.add(buildingHorseTypeBean);
                }
            }

            for (BuildingAnalysisBean bean : buildAnalysisList) {



                // 添加标签
                if (null != refMap && null != balMap) {
                    List<BalRefBean> refBeans = refMap.get(bean.getBaId());
                    if (null != refBeans && refBeans.size()>0){
                        List<String> labelList = new ArrayList<>();
                        List<Integer> labelIdList = new ArrayList<>();
                        for (BalRefBean balRefBean : refBeans) {
                            labelList.add(balMap.get(balRefBean.getBalId()).get(0).getBalContent());
                            labelIdList.add(balRefBean.getBalId());
                        }
                        bean.setBalContentList(labelList);
                        bean.setBalIdList(labelIdList);
                        bean.setBalContentLabel(StringUtils.join(labelList,"、"));
                    }
                }


                // 楼盘名称
                if (null != buildMap && bean.getBuildId() != null) {
                    List<BuildingBean> beanList = buildMap.get(bean.getBuildId());
                    if (null != beanList) {
                        bean.setHtName(beanList.get(0).getHtName());
                    }
                }

                // 翻译出售状态
                if (null != saleMap && null != bean.getIsSale()) {
                    bean.setSaleLabel(saleMap.get(bean.getIsSale()).get(0).getStName());
                }

                // 翻译朝向
                if (bean.getDrection() == 1) {
                    bean.setDrectionLabel("东");
                } else if (bean.getDrection() == 2) {
                    bean.setDrectionLabel("南");
                } else if (bean.getDrection() == 3) {
                    bean.setDrectionLabel("西");
                } else if (bean.getDrection() == 4) {
                    bean.setDrectionLabel("北");
                }

                // 图片绑定实体
                if (null != collect) {
                    List<BuildingImgBean> buildingImgBeans = collect.get(bean.getBaId());
                    if (null != buildingImgBeans) {
                        bean.setHorseImgName(buildingImgBeans.get(0).getImgName());
                    }

                }
            }
            buildingAnalysisPageBean.setBuildingHorseTypeBeanList(buildingHorseTypeBeanList);
        }
        buildingAnalysisPageBean.setBuildingAnalysisBeans(page.setRecords(buildAnalysisList));

        return buildingAnalysisPageBean;
    }

    @Override
    public void insertByBid(BuildingAnalysisBean buildingAnalysisBean, MultipartFile[] horseTypeImg) throws Exception {
        // 插入户型分析数据
        buildingAnalysisDao.insertByBid(buildingAnalysisBean);
        // 绑定户型分析和标签关系
        BalRefBean balRefBean = new BalRefBean();
        if (buildingAnalysisBean.getBalIdList() != null && buildingAnalysisBean.getBalIdList().size()>0){
            balRefBean.setBaId(buildingAnalysisBean.getBaId());
            balRefBean.setBalIdList(buildingAnalysisBean.getBalIdList());
            balRefDao.insertBatch(balRefBean);
        }

        // 保存文件
        this.saveFile(buildingAnalysisBean, horseTypeImg, "horseType", 5);
    }

    @Override
    public void patchBuildAnalysisById(BuildingAnalysisBean buildingAnalysisBean, MultipartFile[] horseTypeImg)  throws Exception {
        // 更新户型分析
        buildingAnalysisDao.patchBuildAnalysisById(buildingAnalysisBean);
        List<Integer> beanBalIdList = buildingAnalysisBean.getBalIdList();
        balRefDao.deleteRefByBaId(new BalRefBean().setBaId(buildingAnalysisBean.getBaId()));
        if (null != beanBalIdList && beanBalIdList.size() > 0) {
            BalRefBean balRefBean = new BalRefBean();
            balRefBean.setBaId(buildingAnalysisBean.getBaId());
            balRefBean.setBalIdList(beanBalIdList);
            balRefDao.insertBatch(balRefBean);
        }

        if (null != horseTypeImg && horseTypeImg.length>0) {
            // 更新图片
            BuildingImgBean buildingImgBean = new BuildingImgBean();
            buildingImgBean.setBId(buildingAnalysisBean.getBuildId());
            buildingImgBean.setBaId(buildingAnalysisBean.getBaId());
            deleteImg(buildingImgBean);

            // 获取图片类型
            List<ImgTypeBean> imgTypeList = imgTypeDao.getAllImgType();
            if (null != imgTypeList && imgTypeList.size() > 0) {
                Map<String, List<ImgTypeBean>> listMap = imgTypeList.stream().collect(Collectors.groupingBy(ImgTypeBean::getItName));
                buildingImgBean.setItId(listMap.get(ImgTypeConstant.horseType).get(0).getItId());
                buildingImgDao.deleteByImgName(buildingImgBean);
            }
            this.saveFile(buildingAnalysisBean,horseTypeImg,"horseType",5);
        }
    }

    //保存文件
    private void saveFile(BuildingAnalysisBean buildingBean, MultipartFile[] file, String type, Integer typeId) throws Exception {
        if (file != null) {

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
                    File dir = new File("D:\\" + buildingBean.getBuildId() + "\\" + type);
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

                    buildingImgBean.setImgPath(path);
                    buildingImgBean.setBaId(buildingBean.getBaId());
                    buildingImgBean.setImgName(name);
                    buildingImgBean.setBId(buildingBean.getBuildId());
                    buildingImgBean.setCreateDate(new Date());
                    buildingImgBean.setItId(typeId);

                    buildingImgDao.addImg(buildingImgBean);
                }
            }
        }
    }
    private void deleteImg(BuildingImgBean buildingImgBean) {
        // 删除图片 以及图片表中的数据
        List<BuildingImgBean> allByBid = buildingImgDao.getAllByBid(buildingImgBean);
        if (null != allByBid) {
            for (BuildingImgBean bean : allByBid) {
                BuildingServiceImpl.delFile(bean.getImgPath());
                System.out.println(bean.getImgPath());
            }
        }
    }
}

