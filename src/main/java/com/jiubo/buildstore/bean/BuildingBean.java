package com.jiubo.buildstore.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.format.annotation.DateTimeFormat;

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

    private String saleLabel;
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

    private List<Integer> areaIdList;

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

    private Date createDate;

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
    private BigDecimal minUnitPrice;
    /**
     * 结束单价
     */
    private BigDecimal maxUnitPrice;

    /**
     *开始总价
     */
    private BigDecimal minTitlePrice;

    /**
     *结束总价
     */
    private BigDecimal maxTitlePrice;

    private List<Integer> idList;

    private List<Map<String,Object>> openTimeList;

    private List<Map<String,Object>> areaList;

    private List<Map<String,Object>> totalPriceList;

    private List<Map<String,Object>> unitPriceList;

    private Date openDate;

    private String couName;

    private String openDateTime;

    private Date proDate;

    private String startTime;
    private String endTime;
}
