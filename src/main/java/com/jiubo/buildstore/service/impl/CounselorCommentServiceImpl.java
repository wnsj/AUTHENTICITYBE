package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.*;

import com.jiubo.buildstore.dao.*;
import com.jiubo.buildstore.service.CounselorCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiubo.buildstore.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
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
public class CounselorCommentServiceImpl extends ServiceImpl<CounselorCommentDao, CounselorCommentBean> implements CounselorCommentService {

    @Autowired
    private CounselorCommentDao counselorCommentDao;

    @Autowired
    private CouTypeDao couTypeDao;

    @Autowired
    private CouRefDao couRefDao;

    @Autowired
    private BuildingDao buildingDao;

    @Autowired
    private CounselorDao counselorDao;

    @Autowired
    private BuildingImgDao buildingImgDao;

    @Override

    /**
     * 前端查询评论
     */
    public CouBean getCounselorByBid(CounselorCommentBean counselorCommentBean) {
        CouBean couBean = new CouBean();
//        Page<CounselorCommentBean> page = new Page<>();
//        page.setCurrent(StringUtils.isBlank(counselorCommentBean.getCurrent()) ? 1L : Long.parseLong(counselorCommentBean.getCurrent()));
//        page.setSize(StringUtils.isBlank(counselorCommentBean.getPageSize()) ? 10L : Long.parseLong(counselorCommentBean.getPageSize()));

        // 咨询师评论数据
        List<CounselorCommentBean> counselorByBid = counselorCommentDao.getCounselorByBid(counselorCommentBean);
        if (null != counselorByBid && counselorByBid.size() > 0) {

            Map<Integer, List<CounselorCommentBean>> map = counselorByBid.stream().collect(Collectors.groupingBy(CounselorCommentBean::getCoucType));

            List<CouTypeBean> allCouTypeList = couTypeDao.getAllCouType();
            Map<Integer, List<CouTypeBean>> couTypeMap = null;
            if (null != allCouTypeList && allCouTypeList.size() > 0) {
                couTypeMap = allCouTypeList.stream().collect(Collectors.groupingBy(CouTypeBean::getId));
            }
            List<CouTypeBean> couTypeBeanList = new ArrayList<>();
            for (Integer couType : map.keySet()) {
                List<CounselorCommentBean> commentBeans = map.get(couType);
                if (null != couTypeMap) {
                    CouTypeBean typeBean = couTypeMap.get(couType).get(0);
                    typeBean.setCouTypeNum(commentBeans.size());
                    couTypeBeanList.add(typeBean);
                }
            }
            couBean.setCouTypeBeanList(couTypeBeanList);
            // 咨询师id集合
            List<Integer> collect = counselorByBid.stream().map(CounselorCommentBean::getCId).collect(Collectors.toList());

            // 通过咨询师id集合查找其标签
            List<CouRefBean> couRefList = couRefDao.getRefByCouIdList(new CouRefBean().setCouIdList(collect));
            Map<Integer, List<CouRefBean>> refMap = null;
            if (null != couRefList && couRefList.size() > 0) {
                refMap = couRefList.stream().collect(Collectors.groupingBy(CouRefBean::getCouId));
            }

            //将标签数据放进咨询师评论分页数据中
            for (CounselorCommentBean commentBean : counselorByBid) {
                if (null != refMap) {
                    List<CouRefBean> counselorLabelBeans = refMap.get(commentBean.getCId());

                    List<String> collect1 = counselorLabelBeans.stream().map(CouRefBean::getCouLabel).collect(Collectors.toList());

                    commentBean.setClContentList(collect1);
                }
            }
        }

        couBean.setCouPage(counselorByBid);
        couBean.setCouNum(counselorByBid.size());
        return couBean;
    }

    @Override
    public void updateNumById(CounselorCommentBean counselorCommentBean) {
        counselorCommentDao.updateNumById(counselorCommentBean);
    }

    @Override
    public Page<CounselorCommentBean> getCounselorByPage(CounselorCommentBean counselorCommentBean) {
        Page<CounselorCommentBean> page = new Page<>();
        page.setCurrent(StringUtils.isBlank(counselorCommentBean.getCurrent()) ? 1L : Long.parseLong(counselorCommentBean.getCurrent()));
        page.setSize(StringUtils.isBlank(counselorCommentBean.getPageSize()) ? 10L : Long.parseLong(counselorCommentBean.getPageSize()));
        List<CounselorCommentBean> comByPageList = counselorCommentDao.getComByPage(page, counselorCommentBean);
        if (null != comByPageList && comByPageList.size() > 0) {
            // 获取楼盘名
            List<BuildingBean> allBuildList = buildingDao.getAllBuild();
            Map<Integer, List<BuildingBean>> map = null;
            if (null != allBuildList && allBuildList.size() > 0) {
                map = allBuildList.stream().collect(Collectors.groupingBy(BuildingBean::getBuildId));
            }
            // 获取咨询师名
            List<CounselorBean> allCouselorList = counselorDao.getAllCouselor();
            Map<Integer, List<CounselorBean>> listMap = null;
            if (null != allCouselorList && allCouselorList.size() > 0) {
                listMap = allCouselorList.stream().collect(Collectors.groupingBy(CounselorBean::getCouId));
            }
            // 获取类型
            List<CouTypeBean> typeBeans = couTypeDao.getAllCouType();
            Map<Integer, List<CouTypeBean>> collect = null;
            if (null != typeBeans && typeBeans.size() > 0) {
                collect = typeBeans.stream().collect(Collectors.groupingBy(CouTypeBean::getId));
            }
            for (CounselorCommentBean commentBean : comByPageList) {
                // 翻译楼盘名
                if (null != map) {
                    commentBean.setHtName(map.get(commentBean.getBuildId()).get(0).getHtName());
                }
                //翻译咨询师名字
                if (null != listMap) {
                    commentBean.setCouName(listMap.get(commentBean.getCId()).get(0).getCouName());
                }
                // 翻译类型
                if (null != collect) {
                    commentBean.setCouTypeName(collect.get(commentBean.getCoucType()).get(0).getCouTypeName());
                }

                if (commentBean.getComDate() != null) {
                    String formatDate = DateUtils.formatDate(commentBean.getComDate(), "yyyy-MM-dd");
                    commentBean.setComTime(formatDate);
                }
            }
        }
        return page.setRecords(comByPageList);
    }

    @Override
    public void updateComById(CounselorCommentBean counselorCommentBean, MultipartFile[] file) {
        BuildingImgBean buildingImgBean = new BuildingImgBean();
        buildingImgBean.setItId(8);
        buildingImgBean.setBaId(counselorCommentBean.getCoucId());
        List<BuildingImgBean> allByBid = buildingImgDao.getAllByBid(buildingImgBean);
        if (null != allByBid) {
            this.delFile(allByBid.get(0).getImgPath());
        }

        counselorCommentDao.updateComById(counselorCommentBean);
    }

    @Override
    public void addCom(CounselorCommentBean counselorCommentBean, MultipartFile[] picImg) throws Exception {
        counselorCommentBean.setComDate(new Date());
        int id = counselorCommentDao.addCom(counselorCommentBean);
        this.saveFile(counselorCommentBean, picImg);
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
    private void saveFile(CounselorCommentBean commentBean, MultipartFile[] file) throws Exception {
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
                String imgName = commentBean.getCoucId().toString().concat(fileName);
                File dir = new File("D:/" + commentBean.getCoucId() + "/" + "comment");
//                System.out.println("dir:" + dir.getPath());
                if (!dir.exists() && !dir.isDirectory()) dir.mkdirs();

                String name = i + "_" + imgName;
//                System.out.println("name:" + name);

                i++;
                String path = dir.getPath() + "/" + name;
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
                buildingImgBean.setBId(commentBean.getBuildId());
                buildingImgBean.setCreateDate(new Date());
                buildingImgBean.setImgPath(path);
                buildingImgBean.setItId(8);
                buildingImgBean.setBaId(commentBean.getCoucId());
                buildingImgDao.addImg(buildingImgBean);
            }
        }
    }

}
