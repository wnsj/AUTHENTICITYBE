package com.jiubo.buildstore.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.Date;
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
@TableName("entrust_rent")
public class EntrustRentBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序列号
     */
    @ApiModelProperty(required = false, value = "序列号")
    @TableId(value = "EN_ID", type = IdType.AUTO)
    private Integer enId;

    /**
     * 手机号码
     */
    @ApiModelProperty(required = true, value = "手机号码")
    private String phone;

    /**
     * 座机号
     */
    @ApiModelProperty(required = true, value = "座机号")
    private String tel;

    /**
     * 城市id
     */
    @ApiModelProperty(required = true, value = "城市id")
    private Integer proId;

    /**
     * 区域id
     */
    @ApiModelProperty(required = true, value = " 区域id")
    private Integer ldId;

    /**
     * 商圈id
     */
    @ApiModelProperty(required = true, value = "商圈id")
    private Integer taId;

    /**
     * 写字楼名称
     */
    @ApiModelProperty(required = true, value = " 写字楼名称")
    private String obName;

    /**
     * 2已联系3未联系
     */
    @ApiModelProperty(required = true, value = "2已联系3未联系")
    private Integer isContact;

    /**
     * 备注
     */
    @ApiModelProperty(required = true, value = "备注")
    private String remark;

    /**
     * 创建时间
     */
    @ApiModelProperty(required = false, value = "创建时间")
    private Date createTime;
    /**
     * 商圈名
     */
    @TableField(exist = false)
    private String buName;
    /**
     * 区域名
     */
    @TableField(exist = false)
    private String ldName;


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
}
