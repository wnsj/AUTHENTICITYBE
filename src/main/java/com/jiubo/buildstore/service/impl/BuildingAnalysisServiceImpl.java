package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildingAnalysisBean;

import com.jiubo.buildstore.bean.BuildingBean;
import com.jiubo.buildstore.bean.BuildingImgBean;
import com.jiubo.buildstore.bean.SaleTypeBean;
import com.jiubo.buildstore.dao.BuildingAnalysisDao;
import com.jiubo.buildstore.dao.BuildingDao;
import com.jiubo.buildstore.dao.BuildingImgDao;
import com.jiubo.buildstore.dao.SaleTypeDao;
import com.jiubo.buildstore.service.BuildingAnalysisService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiubo.buildstore.service.BuildingService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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
    public Page<BuildingAnalysisBean> getAllAnalysisByBid(BuildingAnalysisBean buildingAnalysisBean) {
        Page<BuildingAnalysisBean> page = new Page<>();
        page.setCurrent(StringUtils.isBlank(buildingAnalysisBean.getCurrent()) ? 1L : Long.parseLong(buildingAnalysisBean.getCurrent()));
        page.setSize(StringUtils.isBlank(buildingAnalysisBean.getPageSize()) ? 10L : Long.parseLong(buildingAnalysisBean.getPageSize()));
        List<BuildingBean> allBuild = buildingDao.getAllBuild();
        List<BuildingAnalysisBean> buildAnalysisList = buildingAnalysisDao.getAllAnalysisByBid(page, buildingAnalysisBean);

        // 所有出售状态
        List<SaleTypeBean> allSaleType = saleTypeDao.getAllSaleType();
        if (null != buildAnalysisList && buildAnalysisList.size() > 0) {

            // 获取所有楼盘id
            List<Integer> list = buildAnalysisList.stream().map(BuildingAnalysisBean::getBuildId).collect(Collectors.toList());

            // 图片名称
            List<BuildingImgBean> imgList = buildingImgDao.getHeadImgByBuildId(new BuildingImgBean().setBIdList(list));
            Map<Integer, List<BuildingImgBean>> collect = null;
            if (null != imgList) {
                collect = imgList.stream().collect(Collectors.groupingBy(BuildingImgBean::getBaId));
            }
            for (BuildingAnalysisBean bean : buildAnalysisList) {

                // 楼盘名称
                if (null != allBuild && allBuild.size() > 0) {
                    Map<Integer, List<BuildingBean>> buildMap = allBuild.stream().collect(Collectors.groupingBy(BuildingBean::getBuildId));
                    bean.setHtName(buildMap.get(bean.getBuildId()).get(0).getHtName());
                }

                // 翻译出售状态
                if (null != allSaleType) {
                    Map<Integer, List<SaleTypeBean>> saleMap = allSaleType.stream().collect(Collectors.groupingBy(SaleTypeBean::getStId));

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
                    bean.setHorseImgName(collect.get(bean.getBaId()).get(0).getImgName());
                }
            }
        }
        return page.setRecords(buildAnalysisList);
    }

    @Override
    public void insertByBid(BuildingAnalysisBean buildingAnalysisBean, MultipartFile[] horseTypeImg) throws Exception {
        buildingAnalysisDao.insertByBid(buildingAnalysisBean);

        this.saveFile(buildingAnalysisBean, horseTypeImg, "horseType", 5);
    }

    //保存文件
    private void saveFile(BuildingAnalysisBean buildingBean, MultipartFile[] file, String type, Integer typeId) throws Exception {
        if (file != null) {

            for (MultipartFile multipartFile : file) {
                BuildingImgBean buildingImgBean = new BuildingImgBean();
                //原文件名
                String fileName = multipartFile.getOriginalFilename();


                File directory = new File("");// 参数为空
                String path = directory.getCanonicalPath();
//                System.out.println("路径a：" + path);
                String imgName = buildingBean.getBuildId().toString().concat("_").concat(fileName);
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
                buildingImgBean.setBId(buildingBean.getBuildId());
                buildingImgBean.setCreateDate(new Date());
                buildingImgBean.setItId(typeId);

                buildingImgDao.addImg(buildingImgBean);
            }
        }
    }
}

