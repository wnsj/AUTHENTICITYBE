package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiubo.buildstore.bean.BuildingImgBean;
import com.jiubo.buildstore.bean.OfficeBean;
import com.jiubo.buildstore.common.ImgPathConstant;
import com.jiubo.buildstore.common.ImgTypeConstant;
import com.jiubo.buildstore.dao.BuildingImgDao;
import com.jiubo.buildstore.dao.OfficeDao;
import com.jiubo.buildstore.service.OfficeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiubo.buildstore.util.CollectionsUtils;
import com.jiubo.buildstore.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author swd
 * @since 2020-09-13
 */
@Service
public class OfficeServiceImpl extends ServiceImpl<OfficeDao, OfficeBean> implements OfficeService {

    @Autowired
    private OfficeDao officeDao;
    @Autowired
    private BuildingImgDao buildingImgDao;
    @Override
    public OfficeBean getOfficeByPk(Integer id) {
        OfficeBean bean = officeDao.selectById(id);
        if (null != bean) {
            QueryWrapper<BuildingImgBean> qwP = new QueryWrapper<BuildingImgBean>();
            qwP.select("*");
            qwP.eq("IT_ID", 2);
            qwP.eq("TYPE", 4);
            qwP.eq("INFO_ID", bean.getId());
            List<BuildingImgBean> pictureList = buildingImgDao.selectList(qwP);
            if (!CollectionsUtils.isEmpty(pictureList)) {
                List<String> list = pictureList.stream().map(BuildingImgBean::getImgPath).collect(Collectors.toList());
                bean.setPicList(list);
            }
        }
        return bean;
    }

    @Override
    public void addOffice(OfficeBean officeBean,
                          MultipartFile headImg,
                          MultipartFile[] picture,
                          MultipartFile video) throws IOException {
        officeDao.insert(officeBean);
        List<BuildingImgBean> buildingImgBeans = new ArrayList<>();


        // 头图
        if (null != headImg) {
            Map<String, String> headMap = FileUtil.uploadFile(headImg, ImgPathConstant.OFFICE, officeBean.getId(), ImgTypeConstant.HEAD_PICTURE);
            BuildingImgBean imgBean = new BuildingImgBean();
            imgBean.setImgName(headMap.get("name"));
            imgBean.setCreateDate(new Date());
            imgBean.setItId(ImgTypeConstant.PICTURE);
            imgBean.setImgPath(headMap.get("path"));
            imgBean.setInfoId(officeBean.getId());
            imgBean.setType(ImgTypeConstant.OFFICE);
            buildingImgBeans.add(imgBean);
        }


        // 视频
        if (null != video) {
            Map<String, String> videoMap = FileUtil.uploadFile(video, ImgPathConstant.OFFICE, officeBean.getId(), ImgTypeConstant.VIDEO);
            BuildingImgBean videoBean = new BuildingImgBean();
            videoBean.setImgName(videoMap.get("name"));
            videoBean.setCreateDate(new Date());
            videoBean.setItId(ImgTypeConstant.VIDEO);
            videoBean.setImgPath(videoMap.get("path"));
            videoBean.setInfoId(officeBean.getId());
            videoBean.setType(ImgTypeConstant.OFFICE);
            buildingImgBeans.add(videoBean);
        }

        for (MultipartFile file : picture) {
            Map<String, String> picMap = FileUtil.uploadFile(file, ImgPathConstant.OFFICE, officeBean.getId(), ImgTypeConstant.PICTURE);
            BuildingImgBean picBean = new BuildingImgBean();
            picBean.setImgName(picMap.get("name"));
            picBean.setCreateDate(new Date());
            picBean.setItId(ImgTypeConstant.PICTURE);
            picBean.setImgPath(picMap.get("path"));
            picBean.setInfoId(officeBean.getId());
            picBean.setType(ImgTypeConstant.OFFICE);
            buildingImgBeans.add(picBean);
        }
        buildingImgDao.insertList(buildingImgBeans);
    }
}
