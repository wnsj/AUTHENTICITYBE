package com.jiubo.buildstore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.CounselorBean;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
public interface CounselorService extends IService<CounselorBean> {

    public List<CounselorBean> getAllCouselor();

    public Page<CounselorBean> getAllCouselorByPage(CounselorBean counselorBean);

    public void insertCou(CounselorBean counselorBean, MultipartFile[] picture)throws Exception;


    public void patchCou(CounselorBean counselorBean,MultipartFile[] picture)throws Exception;

    public List<CounselorBean> getAllCharaName();
}
