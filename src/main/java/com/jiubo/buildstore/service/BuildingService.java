package com.jiubo.buildstore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.BuildMainBean;
import com.jiubo.buildstore.bean.BuildingBean;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
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
public interface BuildingService extends IService<BuildingBean> {
    public Page<BuildingBean> getAllBulidBypage(BuildingBean buildingBean);

    public void addBuilding(BuildingBean buildingBean,MultipartFile[] effectImg,
                            MultipartFile[] enPlanImg,
                            MultipartFile[] buildRealImg,
                            MultipartFile[] matchingRealImg,
                            MultipartFile[] headImg,MultipartFile[] video) throws Exception;

    public void patchById(BuildingBean buildingBean,MultipartFile[] effectImg,
                            MultipartFile[] enPlanImg,
                            MultipartFile[] buildRealImg,
                            MultipartFile[] matchingRealImg,
                            MultipartFile[] headImg,MultipartFile[] video) throws Exception;

    public List<BuildingBean> getAllBuild();


    public BuildMainBean getAllByBuildName();

    public BuildingBean getBuildByBuildId(BuildingBean buildingBean);

    public List<BuildingBean> getSellWell();

    public BuildMainBean getHot();

    /**
     * 推荐楼盘（根据人气热搜排序）
     * @return
     */
    public List<BuildingBean> getRecommend();
}
