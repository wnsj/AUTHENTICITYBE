package com.jiubo.buildstore.bean;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author dx
 * @since 2020-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("recruit")
public class RecruitBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 职位名称
     */
    private String position;

    /**
     * 职位类型
     */
    private Integer positionType;

    /**
     * 职位类型描述
     */
    private String positionTypeLabel;
    /**
     * 招收人数
     */
    private Integer recruitsNum;

    /**
     * 工作地点
     */
    private String workplace;

    /**
     * 最小薪酬
     */
    private BigDecimal minPay;

    /**
     * 最大薪酬
     */
    private BigDecimal maxPay;

    /**
     * 最小工作年限
     */
    private Integer minWorkingYears;

    /**
     * 最大工作年限
     */
    private Integer maxWorkingYears;

    /**
     * 学历
     */
    private String education;

    /**
     * 1:有五险一金；2：没有五险一金
     */
    private Long fiveRisksFund;
    /**
     * 1:有五险一金；2：没有五险一金 描述
     */
    private String fiveRisksFundLabel;
    /**
     * 1:包吃住；2：否
     */
    private Long foodShelter;

    /**
     * 1:包吃住；2：否 描述
     */
    private String foodShelterLabel;
    /**
     * 1：双休；2：没有双休
     */
    private Long weekend;

    /**
     * 1：双休；2：没有双休 描述
     */
    private String weekendLabel;
    /**
     * 岗位职责
     */
    private String responsibilities;

    /**
     * 岗位要求
     */
    private String requirements;

    /**
     * 招聘类型
     */
    private Long recruitmentType;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

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
