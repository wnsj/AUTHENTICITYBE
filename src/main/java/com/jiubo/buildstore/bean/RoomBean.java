package com.jiubo.buildstore.bean;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
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
@TableName("room")
public class RoomBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 房源名字
     */
    private String room;

    /**
     * 房源编号
     */
    private Integer roomCode;

    /**
     * 房源概况
     */
    private Integer surveyId;

    /**
     * 楼盘id
     */
    private Integer buildId;

    /**
     * 是否可注册
     */
    private Integer isRegister;

    /**
     * 免租时间
     */
    private String rentFreeTime;

    /**
     * 最早可租
     */
    private String earliestRent;

    /**
     * 最短租期
     */
    private String minTenancy;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 看房时间
     */
    private String watchHouseTime;

    /**
     * 所在楼层
     */
    private String floor;

    /**
     * 建筑面积
     */
    private BigDecimal buildArea;

    /**
     * 最小工位数
     */
    private Integer minStationNum;

    /**
     * 最大工位个数
     */
    private Integer maxStationNum;

    /**
     * 详细地址
     */
    private String detailedAddress;

    /**
     * 是否推荐
     */
    private Integer isRecommend;

    /**
     * 是否热门
     */
    private Integer isHot;

    /**
     * 是否在租
     */
    private Integer isRent;

    /**
     * 商圈id
     */
    private Integer businessId;

    /**
     * 户型介绍
     */
    private String horseLabel;

    /**
     * 装修描述
     */
    private String renovationLabel;

    /**
     * 楼盘介绍
     */
    private String buildLabel;

    /**
     * 交通出行
     */
    private String traffic;

    /**
     * 周边配套
     */
    private String surround;

    /**
     * 路途
     */
    private String way;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 修改时间
     */
    private LocalDateTime modifyDate;


}
