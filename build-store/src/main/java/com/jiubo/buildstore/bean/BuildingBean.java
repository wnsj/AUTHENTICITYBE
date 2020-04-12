package com.jiubo.buildstore.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

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
@TableName("building")
public class BuildingBean implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @TableId(value = "B_ID", type = IdType.AUTO)
    private Integer bId;

    private List<Integer> bIdList;
    /**
     * 楼盘名称
     */
    private String htName;

    /**
     * 是否在售(1在售；2已售)
     */
    private Integer isSale;

    /**
     * 是否在售集合(1在售；2已售)
     */
    private List<Integer> isSaleList;

    /**
     * 别名
     */
    private String alias;

    /**
     * 省份ID
     */
    private Integer proId;

    /**
     * 省份IDList
     */
    private List<Integer> proIdList;

    /**
     * 区域id
     */
    private Integer ldId;

    /**
     * 区域idList
     */
    private List<Integer> ldIdList;

    /**
     * 开发商id
     */
    private Integer devId;

    /**
     * 开发商id集合
     */
    private List<Integer> devIdList;
    /**
     * 最小面积
     */
    private Integer minArea;

    /**
     * 最大面积
     */
    private Integer maxArea;

    /**
     * 参考单价
     */
    private Integer unitPrice;

    /**
     * 参考总价
     */
    private Integer titlePrice;

    /**
     * 楼盘类型ID
     */
    private Integer btId;

    /**
     * 楼盘类型ID集合
     */
    private List<Integer> btIdList;
    /**
     * 楼盘地址
     */
    private String adress;

    /**
     * 楼盘户型ID
     */
    private Integer bhtId;

    /**
     * 楼盘户型ID集合
     */
    private List<Integer> bhtIdList;

    private LocalDateTime createDate;

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
    /**
     * 楼盘类型名
     */
    private String btName;
    /**
     * 户型类型名
     */
    private String caName;
    /**
     * 房源特色名
     */
    private String chaName;
    /**
     * 房源特色id
     */
    private Integer chaId;
    /**
     * 房源特色id集合
     */
    private List<Integer> chaIdList;
    /**
     * 开始单价
     */
    private Integer startUnitPrice;
    /**
     * 结束单价
     */
    private Integer endUnitPrice;

    /**
     *开始总价
     */
    private Integer startTitlePrice;

    /**
     *结束总价
     */
    private Integer endTitlePrice;

    private List<Integer> idList;

    private List<Map<String,Object>> openTimeList;

    private LocalDateTime openDate;

    private LocalDateTime proDate;
}
