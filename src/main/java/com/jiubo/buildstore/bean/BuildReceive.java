package com.jiubo.buildstore.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BuildReceive extends BuildingBean{
    private List<Integer> buildIdList;
    /**
     * 是否在售集合(1在售；2已售)
     */
    private List<Integer> isSaleList;
    /**
     * 省份IDList
     */
    private List<Integer> proIdList;
    /**
     * 区域idList
     */
    private List<Integer> ldIdList;

    /**
     * 开发商id集合
     */
    private List<Integer> devIdList;
    /**
     * 均价id集合
     */
    private List<Integer> unitPriceIdList;
    /**
     * 楼盘类型ID集合
     */
    private List<Integer> btIdList;
    /**
     * 面积id集合
     */
    private List<Integer> areaIdList;
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
     * 时间排序(1:升序；2降序)
     */
    @TableField(exist = false)
    private Integer dateSort;

    /**
     * 价格排序(1:升序；2降序)
     */
    @TableField(exist = false)
    private Integer priceSort;
    /**
     * 房源特色id集合
     */
    private List<Integer> chaIdList;

    private List<Map<String, Object>> openTimeList;

    private List<Map<String, Object>> areaList;

    private List<Map<String, Object>> totalPriceList;

    private List<Map<String, Object>> unitPriceList;

    private String startTime;
    private String endTime;
    private List<Integer> regionIdList;

    /**
     * 模糊查询字段
     */
    private String special;
}
