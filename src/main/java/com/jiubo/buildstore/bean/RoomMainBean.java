package com.jiubo.buildstore.bean;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 房源主表
 * </p>
 *
 * @author swd
 * @since 2020-09-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("room_main")
public class RoomMainBean implements Serializable {

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
     * 房源编码
     */
    private String roomCode;

    /**
     * 区域ID
     */
    private Integer ldId;

    /**
     * 楼盘id
     */
    private Integer buildId;

    /**
     * 商圈id
     */
    private Integer businessId;

    /**
     * 看房时间
     */
    private String watchHouseTime;

    /**
     * 免租时间
     */
    private String rentFreeTime;

    /**
     * 是否可注册
     */
    private Integer isRegister;

    /**
     * 是否首页目前用于共享办公
     */
    private Integer isHome;

    /**
     * 是否热销
     */
    private Integer isHot;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 建筑面积
     */
    private BigDecimal buildArea;

    /**
     * 详细地址
     */
    private String detailedAddress;

    /**
     * 地铁地址
     */
    private String stationAddress;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 咨询师id
     */
    private Integer couId;

    /**
     * 头图路径
     */
    private String roomImg;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 类型id对应buildtype表
     */
    private Integer btId;

    /**
     * 装修情况
     */
    private String renovationCondition;

    /**
     * 距地铁
     */
    private String surround;

    /**
     * 标签List
     */
    private String labelList;

    /**
     * 1,写字楼，2，共享，3商铺
     */
    private Integer roomType;

    /**
     * 面积信息
     */
    private String areaInfo;
    
    /**
     * 商铺类别
     */
    private Integer stId;
    
    /**
     * 业态
     */
    private String caId;
    
    /**
     * 转让费
     */
    private BigDecimal transferFee;
    
    /**
     * 区域名称
     */
    @TableField(exist = false)
    private String ldName;
    
    /**
     * 楼盘名称
     */
    @TableField(exist = false)
    private String buildName;
    
    /**
     * 商圈名称
     */
    @TableField(exist = false)
    private String bussinessName;
    
    /**
     * 类型名称
     */
    @TableField(exist = false)
    private String typeName;

    /**
     * 标签集合
     */
    @TableField(exist = false)
    private List<String> labels;
    
    /**
     * 业态字符串
     */
    @TableField(exist = false)
    private String comString;
    
    /**
     * 创建时间翻译
     */
    @TableField(exist = false)
    private String createTime;
    
    /**
     *支付方式
     */
    private String payType;

    /**
     * 是否在租
     */
    private Integer isRent;
    
    /**
     * 网点介绍
     */
    private String produce;
    
    /**
     *特点list集合
     */
    private String chaList;
}
