package com.jiubo.buildstore.bean;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author swd
 * @since 2020-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("room")
public class RoomBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 房源名字
     */
    private String room;

    /**
     * 房源编号
     */
    private Integer roomCode;

    /**
     * roommain表的id
     */
    private Integer roomId;

    /**
     * 房源概况
     */
    private Integer surveyId;

    /**
     * 楼盘id
     */
    private Integer buildId;

    /**
     * 最早可租
     */
    private String earliestRent;

    /**
     * 最短租期
     */
    private String minTenancy;

    /**
     * 所在楼层
     */
    private String floor;

    /**
     * 建筑面积
     */
    private BigDecimal buildArea;

    /**
     * 最小工位数
     */
    private Integer minStationNum;

    /**
     * 最大工位个数
     */
    private Integer maxStationNum;

    /**
     * 是否推荐
     */
    private Integer isRecommend;

    /**
     * 户型介绍
     */
    private String horseLabel;

    /**
     * 交通出行
     */
    private String traffic;

    /**
     * 路途
     */
    private String way;

    /**
     * 装修描述
     */
    private String renovationLabel;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 楼盘介绍
     */
    private String buildIntroduce;
    
    /**
     * 使用率
     */
    private Integer usageRate;

    /**
     * 周边配套
     */
    private String rimMating;

    /**
     * 修改时间
     */
    private Date modifyDate;


}
