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
     * 房源id
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
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date modifyDate;

    @TableField(exist = false)
    private List<String> picList;
}
