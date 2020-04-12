package com.jiubo.buildstore.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @author syl
 * @since 2020-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("building_analysis")
public class BuildingAnalysisBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "BA_ID", type = IdType.AUTO)
    private Integer baId;

    private Integer bId;

    private Integer bhtId;
    private List<Integer> bhtIdList;
    private String imgName;

    private LocalDateTime createDate;

    private Integer isSale;

    private Integer totlePrice;

    private Integer balId;

    private String content;

    /**
     * 1-东
            2-南
            3-西
            4-北
     */
    private Integer drection;

    private Integer htId;

    private Integer downPayment;

    private List<Integer> bIdList;
}
