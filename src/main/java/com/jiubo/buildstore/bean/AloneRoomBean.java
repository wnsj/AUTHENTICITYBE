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
 * 独立办公室
 * </p>
 *
 * @author swd
 * @since 2020-09-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("alone_room")
public class AloneRoomBean implements Serializable {

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
     * 单价
     */
    private BigDecimal sorcePrice;

    /**
     * 单间个数
     */
    private Integer nowPrice;

    /**
     * 剩余个数
     */
    private Integer surpluseNum;

    /**
     * 面积
     */
    private BigDecimal area;

    /**
     * 房源id
     */
    private Integer roomId;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;


}
