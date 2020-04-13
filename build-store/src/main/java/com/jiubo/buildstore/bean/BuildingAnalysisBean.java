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

    /**
     * 楼盘id
     */
    private Integer bId;

    /**
     * 户型ID
     */
    private Integer bhtId;
    /**
     * 户型ID集合
     */
    private List<Integer> bhtIdList;
    /**
     * 户型名字
     */
    private String imgName;

    /**
     * 上传时间
     */
    private LocalDateTime createDate;

    /**
     * 在售情况
     */
    private Integer isSale;

    /**
     * 总价
     */
    private Integer totlePrice;

    /**
     * 标签id
     */
    private Integer balId;

    /**
     * 分析内容
     */
    private String content;

    /**
     * 朝向
     * 1-东
     * 2-南
     * 3-西
     * 4-北
     */
    private Integer drection;

    /**
     * 类型
     */
    private Integer htId;

    /**
     * 首付金
     */
    private Integer downPayment;

    /**
     * 楼盘id集合
     */
    private List<Integer> bIdList;

    /**
     * 标签名
     */
    private String balContent;

    /**
     * 户型分析类型名（例：平层）
     */
    private String caName;

    /**
     * 建筑面积
     */
    private Integer buildArea;
    /**
     * 页码
     */
    @TableField(exist = false)
    private String pageNum;

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
}
