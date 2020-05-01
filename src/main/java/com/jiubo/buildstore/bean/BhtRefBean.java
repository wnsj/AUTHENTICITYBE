package com.jiubo.buildstore.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author dx
 * @since 2020-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bht_ref")
public class BhtRefBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 户型类型id
     */
    private Integer bhtId;

    /**
     * 户型类型id集合
     */
    @TableField(exist = false)
    private List<Integer> bhtIdList;

    /**
     * 楼盘id
     */
    private Integer buildId;
    /**
     * 楼盘id集合
     */
    @TableField(exist = false)
    private List<Integer> buildIdList;

    /**
     * 户型名
     */
    private String bhtName;


}
