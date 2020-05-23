package com.jiubo.buildstore.bean;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * <p>
 *
 * </p>
 *
 * @author syl
 * @since 2020-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("building")
public class BuildingBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "BUILD_ID", type = IdType.AUTO)
    public Integer buildId;
    /**
     * 楼盘名称
     */
    private String htName;

    /**
     * 是否在售(1在售；2已售)
     */
    private Integer isSale;



    /**
     * 别名
     */
    private String alias;

    /**
     * 省份ID
     */
    private Integer proId;


    /**
     * 区域ID
     */
    private Integer ldId;


    /**
     * 最小面积
     */
    private BigDecimal minArea;

    /**
     * 最大面积
     */
    private BigDecimal maxArea;

    /**
     * 最大单价
     */
    private BigDecimal maxUnitPrice;

    /**
     * 最小单价
     */
    private BigDecimal minUnitPrice;

    /**
     * 最大总价
     */
    private BigDecimal maxTitlePrice;

    /**
     * 最小总价
     */
    private BigDecimal minTitlePrice;

    /**
     * 楼盘类型ID
     */
    private Integer btId;

    /**
     * 楼盘地址
     */
    private String adress;

    /**
     * 加推时间
     */
    private Date createDate;

    /**
     * 房源特色id
     */
    private Integer chaId;

    /**
     * 开发商id
     */
    private String devId;

    /**
     * 开盘时间
     */
    private Date openDate;

    /**
     * 预交房时间
     */
    private Date proDate;

    /**
     * 产权年限
     */
    private Integer ownershipYear;

    /**
     * 物业类型
     */
    private String propertyType;

    /**
     * 容积率
     */
    private BigDecimal plotRatio;

    /**
     * 绿化率
     */
    private BigDecimal greeningRate;

    /**
     * 售楼地址
     */
    private String saleAddress;


    /**
     * 物业地址
     */
    private String propertyAddress;

    /**
     * 装修情况
     */
    private String decoration;

    /**
     * 占地面积
     */
    private BigDecimal coveredArea;

    /**
     * 建筑面积
     */
    private BigDecimal floorage;
    /**
     * 户数
     */
    private Integer households;

    /**
     * 建筑风格
     */
    private String architecturalStyle;

    /**
     * 开发周期
     */
    private Integer iteration;

    /**
     * 物业公司
     */
    private String propertyCompany;


    /**
     * 物业费
     */
    private BigDecimal propertyFee;

    /**
     * 停车位
     */
    private Integer parkingSpace;

    /**
     * 项目介绍
     */
    private String projectIntroduction;

    /**
     * 优惠(1:优惠；2：不优惠)
     */
    private Long discount;

    /**
     * 热销值
     */
    private Integer sellWell;

    /**
     * 人气值
     */
    private Integer popularity;
    /**
     * 热搜值
     */
    private Integer hotSearch;

    /**
     * 特价值
     */
    private Integer specialOffer;

    /**
     * (1:近期开盘;2:不是近期开盘)
     */
    private Long buildOpen;

    /**
     * 联系方式
     */
    private String tel;

    /**
     * 是否推荐户型（1：是；2：否）
     */
    private Long recommend;

    /**
     * 是否是优选楼盘（1：是；2：否）
     */
    private Long optimization;

    /**
     * 是否是品质楼盘（1：是；2：否）
     */
    private Long quality;

    /**
     * 纬度
     */
    private String latitude;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 楼盘特点描述
     */
    private String buildDescription;

    /**
     * 均价
     */
    private BigDecimal averagePrice;
    /**
     * 区域id
     */
    private Integer regionId;

    private Date updateTime;
}
