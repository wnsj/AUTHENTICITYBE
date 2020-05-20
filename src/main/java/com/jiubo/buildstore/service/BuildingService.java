package com.jiubo.buildstore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiubo.buildstore.bean.*;
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
    public Page<BuildReturn> getAllBulidBypage(BuildReceive buildingBean);


    public Page<BuildReturn> getAllBulidByCondition(BuildReceive buildingBean);

    public void addBuilding(BuildReceive buildingBean,MultipartFile[] effectImg,
                            MultipartFile[] enPlanImg,
                            MultipartFile[] buildRealImg,
                            MultipartFile[] matchingRealImg,
                            MultipartFile[] headImg,
                            MultipartFile[] regionImg,
                            MultipartFile[] video) throws Exception;

    public void patchById(BuildReceive buildingBean,MultipartFile[] effectImg,
                            MultipartFile[] enPlanImg,
                            MultipartFile[] buildRealImg,
                            MultipartFile[] matchingRealImg,
                            MultipartFile[] headImg,
                          MultipartFile[] regionImg,
                          MultipartFile[] video) throws Exception;

    public List<BuildingBean> getAllBuild();


    public BuildMainBean getAllByBuildName();

    public BuildReturn getBuildByBuildId(BuildReceive buildingBean);

    public List<BuildReturn> getSellWell();

    public BuildMainBean getHot();

    /**
     * 推荐楼盘（根据人气热搜排序）
     * @return
     */
    public List<BuildReturn> getRecommend();


    /**
     * 模糊查询
     * @param buildingBean
     * @return
     */
    public Page<BuildReturn> getBuildLikePage(BuildReceive buildingBean);

    public void deleteImgFile(BuildingImgBean buildingImgBean);
}
