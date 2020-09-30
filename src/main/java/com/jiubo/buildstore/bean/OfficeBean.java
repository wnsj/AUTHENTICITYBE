package com.jiubo.buildstore.bean;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @author swd
 * @since 2020-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("office")
public class OfficeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 楼盘id
     */
    private Integer roomId;

    /**
     * 头图
     */
    private String imgName;

    /**
     * 原价
     */
    private BigDecimal sorcePrice;

    /**
     * 现价
     */
    private BigDecimal nowPrice;

    /**
     * 工位数
     */
    private Integer stationNum;

    /**
     * 剩余工位数
     */
    private Integer surpluseNum;

    /**
     * 类型（1：开放工位；2：独立办公室）
     */
    private Integer officeType;

    /**
     * 面积
     */
    private BigDecimal area;

    /**
     * 是否靠墙(1:是;2:否)
     */
    private Integer isWall;

    /**
     * 是否带窗(1:是;2:否)
     */
    private Integer isWindow;
    
    /**
     * 是否带窗(1:是;2:否)
     */
    private Integer couId;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date modifyDate;
    

    /**
     * 当前页
     */
    @TableField(exist = false)
    private String current;
    
    /**
     * 此页数量
     */
    @TableField(exist = false)
    private String pageSize;

    @TableField(exist = false)
    private List<String> picList;

//    @TableField(exist = false)
//    private RoomMainBean roomMainBean;
    /**
     * 办公室类型
     */
    @TableField(exist = false)
    private String officeTypeName;
    
    /**
     * 是否靠墙翻译
     */
    @TableField(exist = false)
    private String isWallName;
    
    /**
     * 是否带窗翻译
     */
    @TableField(exist = false)
    private String isWindowName;
    
    /**
     * 咨询师实体
     */
    @TableField(exist = false)
    private CounselorBean couBane;
    
    /**
     * 楼盘内房屋最小总价
     */
    @TableField(exist = false)
    private BigDecimal minTotalPrice;
    
    /**
     * 楼盘名称
     */
    @TableField(exist = false)
    private String buildName;
    
    /**
     * 创建时间
     */
    @TableField(exist = false)
    private String createTime;
    
    /**
     * 工位每月
     */
    @TableField(exist = false)
    private BigDecimal unitPrice;
    
    /**
     * 房源名称
     */
    @TableField(exist = false)
    private String roomName;
    
    /**
     * 视频
     */
    @TableField(exist = false)
    private String videoPath;

    @TableField(exist = false)
    private String videoName;

    /**
     * 图片list
     */
    @TableField(exist = false)
    private List<String> pictureList;

    private String houseType;

    private Integer flag;
}
