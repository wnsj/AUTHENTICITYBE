package com.jiubo.buildstore.bean;

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
 * 商铺房源
 * </p>
 *
 * @author swd
 * @since 2020-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("store_room")
public class StoreRoomBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 共享房源详情ID
     */
    @TableId(value = "store_id", type = IdType.AUTO)
    private Integer storeId;

    /**
     * 网点介绍
     */
    private String produce;

    /**
     * 特点
     */
    private String chaList;

    /**
     * 短标签
     */
    private String label;

    /**
     * 层高
     */
    private Integer highLevel;

    /**
     * 是否 可餐饮
     */
    private Integer isEat;

    /**
     * 是否 空置中
     */
    private Integer isUse;

    /**
     * 最短租期
     */
    private String minLeaseTerm;

    /**
     * 业态
     */
    private String suitableStore;

    /**
     * 商铺介绍
     */
    private String storeProduce;

    /**
     * 面积信息
     */
    private String area;

    /**
     * 价格优势
     */
    private String priceAdvantage;

    /**
     * 物业信息
     */
    private String propertyInfo;

    /**
     * 地铁信息
     */
    private String stationInfo;

    /**
     * 公交信息
     */
    private String transitInfo;

    /**
     * 周边小区
     */
    private String surroundingCommunity;

    /**
     * 写字楼信息
     */
    private String buildingInfo;

    /**
     * 银行信息
     */
    private String bankInfo;

    /**
     * 学校信息
     */
    private String schoolInfo;

    /**
     * 医院信息
     */
    private String hospitalInfo;

    /**
     * 商场信息
     */
    private String storeInfo;

    /**
     * 房源id
     */
    private Integer roomId;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;


}
