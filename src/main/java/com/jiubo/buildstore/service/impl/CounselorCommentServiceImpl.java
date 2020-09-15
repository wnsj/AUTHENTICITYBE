package com.jiubo.buildstore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.Exception.MessageException;
import com.jiubo.buildstore.bean.*;

import com.jiubo.buildstore.common.ImgPathConstant;
import com.jiubo.buildstore.dao.*;
import com.jiubo.buildstore.service.CounselorCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiubo.buildstore.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.MarshalException;
import java.util.*;
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

    @Value("${buildStoreDir}")
    private String buildStoreDir;

//    @Override

    /**
     * 前端查询评论
     */
//    public CouBean getCounselorByBid(CounselorCommentBean counselorCommentBean) {
//        CouBean couBean = new CouBean();
////        Page<CounselorCommentBean> page = new Page<>();
////        page.setCurrent(StringUtils.isBlank(counselorCommentBean.getCurrent()) ? 1L : Long.parseLong(counselorCommentBean.getCurrent()));
////        page.setSize(StringUtils.isBlank(counselorCommentBean.getPageSize()) ? 10L : Long.parseLong(counselorCommentBean.getPageSize()));
//
//
//        // 咨询师评论数据
//        List<CounselorCommentBean> counselorByBid = counselorCommentDao.getCounselorByBid(counselorCommentBean);
//
//        if (null != counselorByBid && counselorByBid.size() > 0) {
//
//            // 获取图片
//            List<Integer> coucIdList = counselorByBid.stream().map(CounselorCommentBean::getCoucId).collect(Collectors.toList());
//            List<BuildingImgBean> imgByBuildId = buildingImgDao.getHeadImgByBuildId(new BuildingImgBean().setCoucIdList(coucIdList));
//            Map<Integer, List<BuildingImgBean>> couMap = null;
//            if (null != imgByBuildId && imgByBuildId.size() > 0) {
//                couMap = imgByBuildId.stream().collect(Collectors.groupingBy(BuildingImgBean::getCoucId));
//            }
//
//            Map<Integer, List<CounselorCommentBean>> map = counselorByBid.stream().collect(Collectors.groupingBy(CounselorCommentBean::getCoucType));
//
//            List<CouTypeBean> allCouTypeList = couTypeDao.getAllCouType();
//            Map<Integer, List<CouTypeBean>> couTypeMap = null;
//            if (null != allCouTypeList && allCouTypeList.size() > 0) {
//                couTypeMap = allCouTypeList.stream().collect(Collectors.groupingBy(CouTypeBean::getId));
//            }
//            List<CouTypeBean> couTypeBeanList = new ArrayList<>();
//            for (Integer couType : map.keySet()) {
//                List<CounselorCommentBean> commentBeans = map.get(couType);
//                if (null != couTypeMap) {
//                    CouTypeBean typeBean = couTypeMap.get(couType).get(0);
//                    typeBean.setCouTypeNum(commentBeans.size());
//                    couTypeBeanList.add(typeBean);
//                }
//            }
//            couBean.setCouTypeBeanList(couTypeBeanList);
//            // 咨询师id集合
//            List<Integer> collect = counselorByBid.stream().map(CounselorCommentBean::getCouId).collect(Collectors.toList());
//
//            // 通过咨询师id集合查找其标签
//            List<CouRefBean> couRefList = couRefDao.getRefByCouIdList(new CouRefBean().setCouIdList(collect));
//            Map<Integer, List<CouRefBean>> refMap = null;
//            if (null != couRefList && couRefList.size() > 0) {
//                refMap = couRefList.stream().collect(Collectors.groupingBy(CouRefBean::getCouId));
//            }
//
//            // 获取所有咨询师
//            List<CounselorBean> beanList = counselorDao.getAllCouselor();
//            Map<Integer, List<CounselorBean>> listMap = null;
//            if (null != beanList && beanList.size() > 0) {
//                listMap = beanList.stream().collect(Collectors.groupingBy(CounselorBean::getCouId));
//            }
//
//            //将标签数据放进咨询师评论分页数据中
//            for (CounselorCommentBean commentBean : counselorByBid) {
//                if (null != refMap && null != commentBean.getCouId()) {
//                    List<CouRefBean> counselorLabelBeans = refMap.get(commentBean.getCouId());
//                    if (null != counselorLabelBeans) {
//                        List<String> collect1 = counselorLabelBeans.stream().map(CouRefBean::getCouLabel).collect(Collectors.toList());
//                        commentBean.setClContentList(collect1);
//                    }
//                }
//
//                if (null != commentBean.getComDate()) {
//                    String date = DateUtils.formatDate(commentBean.getComDate(), "yyyy年MM月dd日");
//                    commentBean.setComTime(date);
//                }
//
//                // 咨询师头像
//                if (null != listMap && commentBean.getCouId() != null) {
//                    List<CounselorBean> beanList1 = listMap.get(commentBean.getCouId());
//                    log.debug("CouId{}" + commentBean.getCouId());
//                    if (null != beanList1 && beanList1.size() > 0) {
//                        CounselorBean bean = beanList1.get(0);
//                        if (null != bean) {
//                            if (!StringUtils.isBlank(bean.getPicturePath())) {
//                                if (!"1".equals(bean.getFlag())) {
//                                    bean.setFlag("1");
//                                    bean.setPicturePath(ImgPathConstant.INTERFACE_PATH.concat(bean.getPicturePath()));
//                                }
//                            }
//                            commentBean.setCounselorBean(bean);
//                        }
//                    }
//                }
//
//                // 图片路径
//                if (null != couMap) {
//                    List<BuildingImgBean> buildingImgBeans = couMap.get(commentBean.getCoucId());
//                    if (null != buildingImgBeans && buildingImgBeans.size() > 0) {
//                        List<String> collect1 = buildingImgBeans.stream().map(BuildingImgBean::getImgPath).collect(Collectors.toList());
//                        List<String> pathList = new ArrayList<>();
//                        for (String e : collect1) {
//                            pathList.add(ImgPathConstant.INTERFACE_PATH.concat(e));
//                        }
//                        commentBean.setImgPathList(pathList);
//                    }
//                }
//            }
//        }
//
//        couBean.setCouPage(counselorByBid);
//        couBean.setCouNum(counselorByBid.size());
//        return couBean;
//    }
//
//    @Override
//    public CounselorCommentBean updateNumById(CounselorCommentBean counselorCommentBean) throws MessageException {
//        if (counselorCommentDao.updateNumById(counselorCommentBean) <= 0) throw new MessageException("没有要更新的信息");
//        CounselorCommentBean counselorCommentBean1 = new CounselorCommentBean();
//        counselorCommentBean1.setCoucId(counselorCommentBean.getCoucId());
//        return counselorCommentDao.queryCounselorComment(counselorCommentBean1);
//    }
//
//    @Override
//    public Page<CounselorCommentBean> getCounselorByPage(CounselorCommentBean counselorCommentBean) {
//        Page<CounselorCommentBean> page = new Page<>();
//        page.setCurrent(StringUtils.isBlank(counselorCommentBean.getCurrent()) ? 1L : Long.parseLong(counselorCommentBean.getCurrent()));
//        page.setSize(StringUtils.isBlank(counselorCommentBean.getPageSize()) ? 10L : Long.parseLong(counselorCommentBean.getPageSize()));
//        List<CounselorCommentBean> comByPageList = counselorCommentDao.getComByPage(page, counselorCommentBean);
//        if (null != comByPageList && comByPageList.size() > 0) {
//
//            // 获取图片
//            List<Integer> coucIdList = comByPageList.stream().map(CounselorCommentBean::getCoucId).collect(Collectors.toList());
//            List<BuildingImgBean> imgByBuildId = buildingImgDao.getHeadImgByBuildId(new BuildingImgBean().setCoucIdList(coucIdList));
//            Map<Integer, List<BuildingImgBean>> couMap = null;
//            if (null != imgByBuildId && imgByBuildId.size() > 0) {
//                couMap = imgByBuildId.stream().collect(Collectors.groupingBy(BuildingImgBean::getCoucId));
//            }
//            // 获取楼盘名
//            List<BuildingBean> allBuildList = buildingDao.getAllBuild();
//            Map<Integer, List<BuildingBean>> map = null;
//            if (null != allBuildList && allBuildList.size() > 0) {
//                map = allBuildList.stream().collect(Collectors.groupingBy(BuildingBean::getBuildId));
//            }
//            // 获取咨询师名
//            List<CounselorBean> allCouselorList = counselorDao.getAllCouselor();
//            Map<Integer, List<CounselorBean>> listMap = null;
//            if (null != allCouselorList && allCouselorList.size() > 0) {
//                listMap = allCouselorList.stream().collect(Collectors.groupingBy(CounselorBean::getCouId));
//            }
//            // 获取类型
//            List<CouTypeBean> typeBeans = couTypeDao.getAllCouType();
//            Map<Integer, List<CouTypeBean>> collect = null;
//            if (null != typeBeans && typeBeans.size() > 0) {
//                collect = typeBeans.stream().collect(Collectors.groupingBy(CouTypeBean::getId));
//            }
//            for (CounselorCommentBean commentBean : comByPageList) {
//                // 翻译楼盘名
//                if (null != map) {
//                    if (null != commentBean.getBuildId()) {
//                        List<BuildingBean> buildingBeanList = map.get(commentBean.getBuildId());
//                        if (null != buildingBeanList) {
//                            commentBean.setHtName(buildingBeanList.get(0).getHtName());
//                        }
//                    }
//                }
//                //翻译咨询师名字
//                if (null != listMap && commentBean.getCouId() != null) {
//                    List<CounselorBean> counselorBeans = listMap.get(commentBean.getCouId());
//                    if (null != counselorBeans) {
//                        commentBean.setCouName(counselorBeans.get(0).getCouName());
//                    }
//
//                }
//                // 翻译类型
//                if (null != collect && commentBean.getCoucType() != null) {
//                    List<CouTypeBean> typeBeans1 = collect.get(commentBean.getCoucType());
//                    commentBean.setCouTypeName(typeBeans1.get(0).getCouTypeName());
//                }
//
//                // 发布时间
//                if (commentBean.getComDate() != null) {
//                    String formatDate = DateUtils.formatDate(commentBean.getComDate(), "yyyy-MM-dd");
//                    commentBean.setComTime(formatDate);
//                }
//
//// 图片路径
//                if (null != couMap) {
//                    List<BuildingImgBean> buildingImgBeans = couMap.get(commentBean.getCoucId());
//                    if (null != buildingImgBeans && buildingImgBeans.size() > 0) {
////                        List<String> collect1 = buildingImgBeans.stream().map(BuildingImgBean::getImgPath).collect(Collectors.toList());
////                        List<String> pathList = new ArrayList<>();
////                        for (String e : collect1) {
////                            pathList.add(ImgPathConstant.INTERFACE_PATH.concat(e));
////                        }
//                        List<String> pathList = new ArrayList<>();
//                        for (BuildingImgBean imgBean : buildingImgBeans) {
//                            String path = ImgPathConstant.INTERFACE_PATH.concat(imgBean.getImgPath()).concat("&imgId=").concat(imgBean.getImgId().toString());
//                            pathList.add(path);
//                        }
//                        commentBean.setImgPathList(pathList);
//                    }
//                }
//            }
//        }
//        return page.setRecords(comByPageList);
//    }
//
//    @Override
//    public void updateComById(CounselorCommentBean counselorCommentBean, MultipartFile[] file) throws Exception {
//        BuildingImgBean buildingImgBean = new BuildingImgBean();
//        buildingImgBean.setItId(8);
//        buildingImgBean.setCoucId(counselorCommentBean.getCoucId());
//        List<BuildingImgBean> allByBid = buildingImgDao.getAllByBid(buildingImgBean);
//        if (null != file && file.length>0) {
//            if (null != allByBid && allByBid.size()>0) {
//                for (BuildingImgBean imgBean : allByBid) {
//                    buildingImgDao.deleteByImgName(imgBean);
//                    this.delFile(imgBean.getImgPath());
//                }
//            }
//        }
//
//        counselorCommentDao.updateComById(counselorCommentBean);
//        this.saveFile(counselorCommentBean, file);
//    }
//
//    @Override
//    public void addCom(CounselorCommentBean counselorCommentBean, MultipartFile[] picImg) throws Exception {
//        counselorCommentBean.setComDate(new Date());
//        int id = counselorCommentDao.addCom(counselorCommentBean);
//        this.saveFile(counselorCommentBean, picImg);
//    }
//
//    public static void delFile(String filePathAndName) {
//        try {
//            String filePath = filePathAndName;
//            java.io.File myDelFile = new java.io.File(filePath);
//            myDelFile.delete();
//        } catch (Exception e) {
//            System.out.println("删除文件操作出错");
//            e.printStackTrace();
//        }
//    }


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
//                String imgName = commentBean.getCoucId().toString().concat(fileName);
                File dir = new File(buildStoreDir + ImgPathConstant.COMMENT + commentBean.getCoucId());
//                System.out.println("dir:" + dir.getPath());
                if (!dir.exists() && !dir.isDirectory()) dir.mkdirs();

                String name = UUID.randomUUID().toString().replace("-", "").concat(fileName);
//                System.out.println("name:" + name);

                i++;
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
                }

                buildingImgBean.setImgName(name);
//                buildingImgBean.setBuildId(commentBean.getBuildId());
                buildingImgBean.setCreateDate(new Date());
                buildingImgBean.setImgPath(path);
                buildingImgBean.setItId(8);
                buildingImgBean.setInfoId(commentBean.getCoucId());
                buildingImgDao.addImg(buildingImgBean);
            }
        }
    }

}
