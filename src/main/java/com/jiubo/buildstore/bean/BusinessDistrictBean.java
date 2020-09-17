package com.jiubo.buildstore.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("business_district")
public class BusinessDistrictBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商圈名字
     */
    private String buName;

    /**
     * 图片路径
     */
    private String buPath;

    /**
     * 商圈描述
     */
    private String buLabel;

    /**
     * 区域id
     */
    private Integer ldId;
    
    /**
     * 是否热门
     */
    private Integer isHot;


    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date modifyTime;

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
     * 区域名称
     */
    @TableField(exist = false)
    private String ldName;
    
    /**
     * 是否热门
     */
    @TableField(exist = false)
    private String isHotName;
    
    /**
     *创建时间
     */
    @TableField(exist = false)
    private String createTime;
}
