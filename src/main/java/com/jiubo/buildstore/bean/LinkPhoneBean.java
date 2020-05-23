package com.jiubo.buildstore.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * @author syl
 * @since 2020-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("link_phone")
public class LinkPhoneBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LP_ID", type = IdType.AUTO)
    private Integer lpId;

    private String lpName;

    private Integer buildId;

    @TableField(exist = false)
    private String build;
    /**
     * 1:详情--参考单价
            2:详情--最近开盘
            3:免费专车看房
            4:楼盘优惠
            5:户型分析--了解户型
            6:楼盘动态--订阅楼盘信息
            7:专家点评--咨询
            8:免费专车看房
            
     */
    private Integer moId;

    @TableField(exist = false)
    private String moLabel;

    private String phone;

    private Date createDate;

    @TableField(exist = false)
    private String createTime;
    /**
     * 前台传入的日期
     */
    private Date writeTime;

    @TableField(exist = false)
    private String writeDate;

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
     * 回拨电话标识（1：已回拨；2：未回拨）
     */
    private Integer remarks;

    @TableField(exist = false)
    private String remarksLabel;

    /**
     * 备注
     */
    private String form;

    /**
     * 回访销售
     */
    private String returnSale;
}
