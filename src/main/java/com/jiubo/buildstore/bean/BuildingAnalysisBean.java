package com.jiubo.buildstore.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("building_analysis")
public class BuildingAnalysisBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "BA_ID", type = IdType.AUTO)
    private Integer baId;

    /**
     * 标签id集合
     */
    private List<Integer> balIdList;
    /**
     * 楼盘id
     */
    private Integer buildId;

    /**
     * 楼盘名称
     */
    private String htName;
    /**
     * 户型ID
     */
    private Integer bhtId;
    /**
     * 户型ID集合
     */
    private List<Integer> bhtIdList;
    /**
     * 户型名字
     */
    private String imgName;

    /**
     * 上传时间
     */
    private Date createDate;

    /**
     * 在售情况
     */
    private Integer isSale;

    private String saleLabel;
    /**
     * 总价
     */
    private BigDecimal totlePrice;


    /**
     * 分析内容
     */
    private String content;

    /**
     * 朝向
     * 1-东
     * 2-南
     * 3-西
     * 4-北
     */
    private Integer drection;

    private String drectionLabel;
    /**
     * 类型
     */
    private Integer htId;

    /**
     * 首付金
     */
    private BigDecimal downPayment;

    /**
     * 楼盘id集合
     */
    private List<Integer> bIdList;

    /**
     * 标签名
     */
    private String balContent;

    private List<String> balContentList;

    private String balContentLabel;
    /**
     * 户型分析类型名（例：平层）
     */
    private String caName;

    /**
     * 建筑面积
     */
    private Integer buildArea;

    /**
     * 居室
     */
    private String house;
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
     * 排序依据
     */
    @TableField(exist = false)
    private String pageOrder;

    private String horseImgName;

    private List<BuildingHorseTypeBean> buildingHorseTypeBeanList;
    private String floor;
}
