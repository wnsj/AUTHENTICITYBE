package com.jiubo.buildstore.bean;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author swd
 * @since 2020-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("building")
public class BuildingBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "BUILD_ID", type = IdType.AUTO)
    private Integer buildId;

    /**
     * 楼盘名称
     */
    private String htName;

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
     * 最小工位数
     */
    private Integer minStationNum;

    /**
     * 最大工位数
     */
    private Integer maxStationNum;


    /**
     * 楼盘地址
     */
    private String adress;

    /**
     * 开发商id
     */
    private String devId;

    /**
     * 建筑面积
     */
    private BigDecimal floorage;

    /**
     * 物业公司
     */
    private String propertyCompany;

    /**
     * 项目介绍
     */
    private String projectIntroduction;

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
    private BigDecimal specialOffer;

    /**
     * 热销值
     */
    private Integer sellWell;

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
     * 联系方式
     */
    private String tel;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 排序权重
     */
    private Integer sort;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 客梯数
     */
    private Integer elevatorNum;

    /**
     * 标准层高
     */
    private BigDecimal floorHeight;

    /**
     * 入住企业
     */
    private String enterprises;

    /**
     * 咨询师id
     */
    private Integer couId;

    /**
     * 商圈id
     */
    private Integer businessId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 楼盘类型
     */
    private Integer buildType;

    /**
     * 头图路径
     */
    private String headPath;

    /**
     * 周边
     */
    private String periphery;

    /**
     * 可租房源数
     */
    private Integer isRentNum;
    
    /**
     * 区域名字
     */
    @TableField(exist = false)
    private String ldName;
    
    /**
     * 商圈名字
     */
    @TableField(exist = false)
    private String busName;
}
