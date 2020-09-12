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
@TableName("building_dynamic")
public class BuildingDynamicBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "BD_ID", type = IdType.AUTO)
    private Integer bdId;

    private String bdName;

    private String bdContent;

    private Date createDate;
    @TableField(exist = false)
    private String createTime;


    @TableField(exist = false)
    private Date startDate;
    @TableField(exist = false)
    private Date endDate;
    /**
     * 类型
     */
    private Integer buildId;

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
     * 排序依据
     */
    @TableField(exist = false)
    private String pageOrder;

    /**
     * 图片路径
     */
    private String bdPath;
    /**
     * 动态数目
     */
    @TableField(exist = false)
    private Integer dyCount;
}
