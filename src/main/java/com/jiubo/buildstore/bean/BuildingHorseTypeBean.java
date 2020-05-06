package com.jiubo.buildstore.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;

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
@TableName("building_horse_type")
public class BuildingHorseTypeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "BHT_ID", type = IdType.AUTO)
    private Integer bhtId;

    private String bhtName;

    @TableField(exist = false)
    private Integer bhtNum;

    @TableField(exist = false)
    private Integer area;
}
