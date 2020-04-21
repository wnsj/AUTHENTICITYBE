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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

    @Override

    public Page<BuildingBean> getAllBulidBypage(BuildingBean buildingBean) {
        Page<BuildingBean> page = new Page<>();
        page.setCurrent(StringUtils.isBlank(buildingBean.getCurrent()) ? 1L : Long.parseLong(buildingBean.getCurrent()));
        page.setSize(StringUtils.isBlank(buildingBean.getPageSize()) ? 10L : Long.parseLong(buildingBean.getPageSize()));
        // 获取户型id集合
        List<Integer> bhtIdList = buildingBean.getBhtIdList();
        if (null != bhtIdList && bhtIdList.size() > 0) {
            BuildingAnalysisBean buildingAnalysisBean = new BuildingAnalysisBean();
            buildingAnalysisBean.setBhtIdList(bhtIdList);
            // 获取户型分析表中楼盘ID
            List<BuildingAnalysisBean> bidByBhtIdList = buildingAnalysisService.getBidByBhtIdList(buildingAnalysisBean);
            List<Integer> bIdList = bidByBhtIdList.stream().map(BuildingAnalysisBean::getBId).collect(toList());
            buildingBean.setBIdList(bIdList);
        }


        // 翻译楼盘户型
        // 获取户型分析数据
        List<BuildingHorseTypeBean> allHorseType = buildingHorseTypeService.getAllHorseType();

        // 户型map 通过户型分析中的户型id和楼盘id到户型表中翻译户型
        Map<Integer, List<BuildingHorseTypeBean>> integerListMap = allHorseType.stream().collect(Collectors.groupingBy(BuildingHorseTypeBean::getBhtId));

        List<BuildingBean> allBulidBypage = buildingDao.getAllBulidBypage(page, buildingBean);
        if (null != allBulidBypage && allBulidBypage.size() > 0) {
            // 获取楼盘id
            List<Integer> list = allBulidBypage.stream().map(BuildingBean::getBId).collect(toList());
            // 根据楼盘id抓取户型分析数据
            BuildingAnalysisBean buildingAnalysisBean = new BuildingAnalysisBean();
            buildingAnalysisBean.setBIdList(list);
            List<BuildingAnalysisBean> bidByBIdList = buildingAnalysisService.getBidByBIdList(buildingAnalysisBean);
            Map<Integer, List<BuildingAnalysisBean>> listMap = bidByBIdList.stream().collect(Collectors.groupingBy(BuildingAnalysisBean::getBId));
            // 翻译出售状态
            List<SaleTypeBean> allSaleType = saleTypeDao.getAllSaleType();

            CounselorCommentBean commentBean = new CounselorCommentBean();
            commentBean.setBIdList(list);
            List<CounselorCommentBean> cidByBidList = counselorCommentDao.getCidByBidList(commentBean);

            for (BuildingBean bean : allBulidBypage) {
                List<String> bhtNameList = new ArrayList<>();
                List<BuildingAnalysisBean> buildingAnalysisBeans = listMap.get(bean.getBId());
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

                if (null != bean.getOpenDate()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    bean.setOpenDateTime(sdf.format(bean.getOpenDate()));
                }

                if (null != allSaleType) {
                    Map<Integer, List<SaleTypeBean>> map = allSaleType.stream().collect(Collectors.groupingBy(SaleTypeBean::getStId));

                    bean.setSaleLabel(map.get(bean.getIsSale()).get(0).getStName());
                }

                if (null != cidByBidList) {
                    Map<Integer, List<CounselorCommentBean>> collect = cidByBidList.stream().collect(Collectors.groupingBy(CounselorCommentBean::getBId));
                    List<CounselorCommentBean> commentBeans = collect.get(bean.getBId());
                    if (null != commentBeans) {
                        List<String> cNameList = new ArrayList<>();
                        for (CounselorCommentBean counselorCommentBean : commentBeans) {
                            cNameList.add(counselorCommentBean.getCouName());
                        }
                        String join = StringUtils.join(cNameList, "、");
                        bean.setCouName(join);
                    }
                }
            }
        }
        return page.setRecords(allBulidBypage);
    }

    @Override
    public void addBuilding(BuildingBean buildingBean, MultipartFile[] effectImg, MultipartFile[] enPlanImg,
                            MultipartFile[] buildRealImg,
                            MultipartFile[] matchingRealImg) throws Exception {


        String buildName = buildingBean.getHtName();
        int id;
        if (!StringUtils.isBlank(buildName)) {
            // 若不存在 创建
            id = buildingDao.addBuilding(buildingBean);
        } else {
            // 存在 则更新
            BuildingBean byHtName = buildingDao.getAllByHtName(buildingBean);
            id = byHtName.getBId();
            buildingBean.setBId(id);
            buildingDao.patchById(buildingBean);
        }


        List<ImgTypeBean> imgTypeList = imgTypeDao.getAllImgType();
//        System.out.println("effectImg：" + enPlanImg);
        buildingBean.setBId(id);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String ss = sdf.format(date);
        if (null != imgTypeList && imgTypeList.size() > 0) {
            Map<String, List<ImgTypeBean>> listMap = imgTypeList.stream().collect(Collectors.groupingBy(ImgTypeBean::getItName));

            this.saveFile(buildingBean, effectImg, listMap.get(ImgTypeConstant.effectImg).get(0).getItId() + "_" + ss + "_", listMap.get(ImgTypeConstant.effectImg).get(0).getItId());
            this.saveFile(buildingBean, enPlanImg, listMap.get(ImgTypeConstant.enPlanImg).get(0).getItId() + "_" + ss + "_", listMap.get(ImgTypeConstant.enPlanImg).get(0).getItId());
            this.saveFile(buildingBean, buildRealImg, listMap.get(ImgTypeConstant.buildRealImg).get(0).getItId() + "_" + ss + "_", listMap.get(ImgTypeConstant.buildRealImg).get(0).getItId());
            this.saveFile(buildingBean, matchingRealImg, listMap.get(ImgTypeConstant.matchingRealImg).get(0).getItId() + "_" + ss + "_", listMap.get(ImgTypeConstant.matchingRealImg).get(0).getItId());
        }
    }

    @Override
    public void patchById(BuildingBean buildingBean, MultipartFile[] effectImg, MultipartFile[] enPlanImg, MultipartFile[] buildRealImg, MultipartFile[] matchingRealImg) throws Exception {
        // 更新楼盘数据
        buildingDao.patchById(buildingBean);
        // 获取图片类型
        List<ImgTypeBean> imgTypeList = imgTypeDao.getAllImgType();
        // 更新图片
        updatePicture(buildingBean, effectImg, imgTypeList, 1);
        updatePicture(buildingBean, enPlanImg, imgTypeList, 2);
        updatePicture(buildingBean, buildRealImg, imgTypeList, 3);
        updatePicture(buildingBean, matchingRealImg, imgTypeList, 4);
    }

    private void updatePicture(BuildingBean buildingBean, MultipartFile[] img, List<ImgTypeBean> imgTypeList, Integer type) throws Exception {
        if (null != img) {
            if (null != imgTypeList) {
                Map<String, List<ImgTypeBean>> listMap = imgTypeList.stream().collect(Collectors.groupingBy(ImgTypeBean::getItName));
                BuildingImgBean buildingImgBean = new BuildingImgBean();
                buildingImgBean.setBId(buildingBean.getBId());
                buildingImgBean.setItId(listMap.get(ImgTypeConstant.effectImg).get(0).getItId());

                // 删除图片 以及图片表中的数据
                List<BuildingImgBean> allByBid = buildingImgDao.getAllByBid(buildingImgBean);
                if (null != allByBid) {
                    for (BuildingImgBean bean : allByBid) {
                        delFile("D:\\" + bean.getImgName());
                    }
                }
                buildingImgDao.deleteByImgName(buildingImgBean);

                // 重新插入图片名 并存储图片
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
                String ss = sdf.format(date);
                if (type == 1) {
                    this.saveFile(buildingBean, img, listMap.get(ImgTypeConstant.effectImg).get(0).getItId() + "_" + ss + "_",
                            listMap.get(ImgTypeConstant.effectImg).get(0).getItId());
                } else if (type == 2) {
                    this.saveFile(buildingBean, img, listMap.get(ImgTypeConstant.enPlanImg).get(0).getItId() + "_" + ss + "_", listMap.get(ImgTypeConstant.enPlanImg).get(0).getItId());

                } else if (type == 3) {
                    this.saveFile(buildingBean, img, listMap.get(ImgTypeConstant.buildRealImg).get(0).getItId() + "_" + ss + "_", listMap.get(ImgTypeConstant.buildRealImg).get(0).getItId());

                } else {
                    this.saveFile(buildingBean, img, listMap.get(ImgTypeConstant.matchingRealImg).get(0).getItId() + "_" + ss + "_", listMap.get(ImgTypeConstant.matchingRealImg).get(0).getItId());
                }
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

            for (MultipartFile multipartFile : file) {
                BuildingImgBean buildingImgBean = new BuildingImgBean();
                //原文件名
                String fileName = multipartFile.getOriginalFilename();


                File directory = new File("");// 参数为空
                String path = directory.getCanonicalPath();
//                System.out.println("路径a：" + path);
                String imgName = buildingBean.getBId().toString().concat("_").concat(fileName);
                File dir = new File(path);
                if (!dir.exists()) dir.mkdirs();
                String buildStore = "D:\\";
                String name = type + imgName;

                path = buildStore.concat(name);

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
                buildingImgBean.setBId(buildingBean.getBId());
                buildingImgBean.setCreateDate(new Date());
                buildingImgBean.setItId(typeId);

                buildingImgDao.addImg(buildingImgBean);
            }
        }
    }
}