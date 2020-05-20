package com.jiubo.buildstore.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class BuildReturn extends BuildingBean{

    private String saleLabel;

    /**
     * 楼盘类型名
     */
    private String btName;
    /**
     * 户型类型名
     */
    private String caName;
    /**
     * 房源特色名
     */
    private String chaName;

    private List<String> charaNameList;

    private String proDateTime;

    /**
     * 房源特色id集合
     */
    private List<Integer> chaIdList;
    private Integer sellWellLabel;
    private String openDateTime;
    /**
     * 头图路径
     */
    private String imgPath;

    /**
     * 视频名
     */
    private String videoName;
    /**
     * 视频路径
     */
    private String videoPath;

    /**
     * 户型图
     */
    private List<String> effectList;
    private List<String> effectPathList;

    /**
     * 小区环境
     */
    private List<String> enPlanList;
    private List<String> enPlanPathList;

    /**
     * 实景图
     */
    private List<String> buildReaList;
    private List<String> buildReaPathList;


    /**
     * 全景看房
     */
    private List<String> matchingRealList;
    private List<String> matchingRealPathList;

    /**
     * 区位
     */
    private List<String> regionPathList;
    /**
     * 标签名（随意选的）
     */
    private String label;
    /**
     * 标签集合
     */
    private List<String> labelList;

    /**
     * 楼盘居室信息
     */
    private List<HouseTypeNum> htnNumList;

    private String ldName;

    /**
     * 头图名
     */
    private String imgName;

    /**
     * 均价
     */
    private BigDecimal averagePrice;

    /**
     * 页码
     */
    @TableField(exist = false)
    private String current;

    /**
     * 每页尺寸
     */
    @TableField(exist = false)
    private String pageSize;

    /**
     * 楼盘户型ID集合
     */
    private List<Integer> bhtIdList;
}
