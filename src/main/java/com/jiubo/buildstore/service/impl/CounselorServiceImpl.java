package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.*;

import com.jiubo.buildstore.common.ImgPathConstant;
import com.jiubo.buildstore.common.ImgTypeConstant;
import com.jiubo.buildstore.dao.CouRefDao;
import com.jiubo.buildstore.dao.CounselorCharacterDao;
import com.jiubo.buildstore.dao.CounselorDao;
import com.jiubo.buildstore.dao.CounselorLabelDao;
import com.jiubo.buildstore.service.CounselorCharacterService;
import com.jiubo.buildstore.service.CounselorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class CounselorServiceImpl extends ServiceImpl<CounselorDao, CounselorBean> implements CounselorService {

    @Autowired
    private CounselorDao counselorDao;

    @Autowired
    private CounselorCharacterDao counselorCharacterDao;

    @Autowired
    private CouRefDao couRefDao;

    @Autowired
    private CounselorLabelDao counselorLabelDao;

    @Value("${buildStoreDir}")
    private String buildStoreDir;
    @Override
    public List<CounselorBean> getAllCouselor() {
        return counselorDao.getAllCouselor();
    }

    @Override
    public Page<CounselorBean> getAllCouselorByPage(CounselorBean counselorBean) {
        Page<CounselorBean> page = new Page<>();
        page.setCurrent(StringUtils.isBlank(counselorBean.getCurrent()) ? 1L : Long.parseLong(counselorBean.getCurrent()));
        page.setSize(StringUtils.isBlank(counselorBean.getPageSize()) ? 10L : Long.parseLong(counselorBean.getPageSize()));

        List<CounselorBean> counselorBeanList = counselorDao.getAllCouselorByPage(page, counselorBean);
        if (null != counselorBeanList && counselorBeanList.size() > 0) {
            //咨询师id集合
            List<Integer> list = counselorBeanList.stream().map(CounselorBean::getCouId).collect(Collectors.toList());
            // 咨询师特点
            List<CounselorCharacterBean> characterBeans = counselorCharacterDao.getAllCouChara();
            Map<Integer, List<CounselorCharacterBean>> charaMap = null;
            if (null != characterBeans && characterBeans.size() > 0) {
                charaMap = characterBeans.stream().collect(Collectors.groupingBy(CounselorCharacterBean::getCcId));
            }
            // 从咨询师标签关联表中获取标签
            List<CouRefBean> couRefBeans = couRefDao.getRefByCouIdList(new CouRefBean().setCouIdList(list));
            Map<Integer, List<CouRefBean>> couRefMap = null;
            if (null != couRefBeans && couRefBeans.size() > 0) {
                couRefMap = couRefBeans.stream().collect(Collectors.groupingBy(CouRefBean::getCouId));
            }

            for (CounselorBean counselorBean1 : counselorBeanList) {
                if (null != charaMap && null != counselorBean1.getCcId()) {
                    counselorBean1.setCharaName(charaMap.get(counselorBean1.getCcId()).get(0).getCcContent());
                }

                if (null != couRefMap) {
                    List<CouRefBean> beanList = couRefMap.get(counselorBean1.getCouId());
                    if (null != beanList && beanList.size() > 0) {
                        List<String> labelList = new ArrayList<>();
                        List<Integer> labelIdList = new ArrayList<>();
                        for (CouRefBean couRefBean : beanList) {
                            labelIdList.add(couRefBean.getCouLabelId());
                            labelList.add(couRefBean.getCouLabel());
                        }
                        String label = StringUtils.join(labelList, "、");
                        counselorBean1.setCouLabel(label);
                        counselorBean1.setLabelList(labelIdList);
                    }
                }
            }
        }
        return page.setRecords(counselorBeanList);
    }

    @Override
    public void insertCou(CounselorBean counselorBean, MultipartFile[] picture) throws Exception {

        counselorDao.insertCou(counselorBean);
        this.saveFile(counselorBean, picture);
        bindRef(counselorBean);
    }

    private void bindRef(CounselorBean counselorBean) {
        List<Integer> labelList = counselorBean.getLabelList();
        if (null != labelList && labelList.size() > 0){
            List<CouRefBean> couRefBeanList = new ArrayList<>();
            List<CounselorLabelBean> counselorLabelBeanList = counselorLabelDao.getAllByIdList(new CounselorLabelBean().setIdList(labelList));
            if (null != counselorLabelBeanList && counselorLabelBeanList.size() > 0) {
                for (CounselorLabelBean counselorLabelBean : counselorLabelBeanList) {
                    CouRefBean couRefBean = new CouRefBean();
                    couRefBean.setCouId(counselorBean.getCouId());
                    couRefBean.setCouLabelId(counselorLabelBean.getClId());
                    couRefBean.setCouLabel(counselorLabelBean.getClContent());
                    couRefBeanList.add(couRefBean);
                }
                couRefDao.insertCouRefBatch(couRefBeanList);
            }
        }
    }

    @Override
    public void patchCou(CounselorBean counselorBean, MultipartFile[] picture) throws Exception {
            couRefDao.deleteRefByCouId(counselorBean.getCouId());
            bindRef(counselorBean);
        if (null != picture && picture.length > 0) {
            CounselorBean cou = counselorDao.getCouById(counselorBean.getCouId());
            delFile(cou.getPicturePath());
            this.saveFile(counselorBean, picture);
        }
        counselorDao.patchCouById(counselorBean);
    }

    private void saveFile(CounselorBean counselorBean, MultipartFile[] file) throws Exception {
        if (file != null) {


            for (MultipartFile multipartFile : file) {
                //原文件名
                String fileName = multipartFile.getOriginalFilename();
                fileName = fileName.substring(fileName.lastIndexOf("."));

//                File directory = new File("");// 参数为空
//                String path = directory.getCanonicalPath();
//                System.out.println("路径a：" + path);

                File dir = new File(buildStoreDir + ImgPathConstant.COMMENT + counselorBean.getCouId());
//                System.out.println("dir:" + dir.getPath());
                if (!dir.exists() && !dir.isDirectory()) dir.mkdirs();

                String name = counselorBean.getCouId() + "_" + fileName;
//                System.out.println("name:" + name);
                String replace = dir.getPath().replace("\\", "/");
                String path = replace + "/" + name;
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
                    counselorBean.setPictureName(name);
                    counselorBean.setPicturePath(path);
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
}
