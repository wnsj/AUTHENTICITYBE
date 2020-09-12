package com.jiubo.buildstore.bean;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 开放工位表
 * </p>
 *
 * @author swd
 * @since 2020-09-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("open_room")
public class OpenRoomBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 共享房源详情ID
     */
    @TableId(value = "or_id", type = IdType.AUTO)
    private Integer orId;

    /**
     * 头图
     */
    private String imgName;

    /**
     * 原价
     */
    private BigDecimal sorcePrice;

    /**
     * 现价
     */
    private BigDecimal nowPrice;

    /**
     * 工位数
     */
    private Integer stationNum;

    /**
     * 剩余工位数
     */
    private Integer surpluseNum;

    /**
     * 房源id
     */
    private Integer roomId;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;


}
