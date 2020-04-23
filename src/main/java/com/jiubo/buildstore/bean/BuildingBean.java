package com.jiubo.buildstore.bean;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author dx
 * @since 2020-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("building")
public class BuildingBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "BUILD_ID", type = IdType.AUTO)
    private Integer buildId;

    private List<Integer> buildIdList;
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
     * 区域ID
     */
    private Integer ldId;

    /**
     * 区域idList
     */
    private List<Integer> ldIdList;

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
     * 最大单价
     */
    private BigDecimal maxUnitPrice;

    /**
     * 最小单价
     */
    private BigDecimal minUnitPrice;

    /**
     * 最大总价
     */
    private BigDecimal maxTitlePrice;

    /**
     * 最小总价
     */
    private BigDecimal minTitlePrice;

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
     * 排序标识(综合排序：0；单价排序：1；总价排序：2)
     */
    private Integer sortCode;

    /**
     * 楼盘类型名
     */
    private String btName;
    /**
     * 加推时间
     */
    private Date createDate;

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
     * 开发商id
     */
    private Integer devId;

    /**
     * 开盘时间
     */
    private Date openDate;

    /**
     * 预交房时间
     */
    private Date proDate;

    /**
     * 产权年限
     */
    private Integer ownershipYear;

    /**
     * 物业类型
     */
    private Integer propertyType;

    /**
     * 容积率
     */
    private BigDecimal plotRatio;

    /**
     * 绿化率
     */
    private BigDecimal greeningRate;

    /**
     * 售楼地址
     */
    private String saleAddress;

    /**
     * 房源特色id集合
     */
    private List<Integer> chaIdList;
    /**
     * 物业地址
     */
    private String propertyAddress;

    /**
     * 装修情况
     */
    private Integer decoration;

    /**
     * 占地面积
     */
    private BigDecimal coveredArea;

    /**
     * 建筑面积
     */
    private BigDecimal floorage;
    private List<Integer> idList;
    /**
     * 户数
     */
    private Integer households;

    /**
     * 建筑风格
     */
    private String architecturalStyle;

    /**
     * 开发周期
     */
    private Integer iteration;

    /**
     * 物业公司
     */
    private Integer propertyCompany;
    private List<Map<String,Object>> openTimeList;

    private List<Map<String,Object>> areaList;

    private List<Map<String,Object>> totalPriceList;

    private List<Map<String,Object>> unitPriceList;
    /**
     * 物业费
     */
    private BigDecimal propertyFee;

    /**
     * 停车位
     */
    private Integer parkingSpace;

    /**
     * 项目介绍
     */
    private String projectIntroduction;

    /**
     * 优惠(1:优惠；2：不优惠)
     */
    private Long discount;

    /**
     * 热销（1：热销；2：人气；3：热搜）
     */
    private Long sellWell;

    /**
     * (1:近期开盘;2:不是近期开盘)
     */
    private Long buildOpen;


    private String couName;

    private String openDateTime;


    private String startTime;
    private String endTime;

    /**
     *出售类型（1：推荐户型；2：品质楼盘；3：优选新房）
     */
    private Long salesType;

    /**
     * 修改时间
     */
    private Date modifyTime;
}
