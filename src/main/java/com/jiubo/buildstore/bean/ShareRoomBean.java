package com.jiubo.buildstore.bean;

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
 * 共享房源
 * </p>
 *
 * @author swd
 * @since 2020-09-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("share_room")
public class ShareRoomBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 共享房源详情ID
     */
    @TableId(value = "share_id", type = IdType.AUTO)
    private Integer shareId;

    /**
     * 网点介绍
     */
    private String produce;

    /**
     * 特点
     */
    private String chaList;

    /**
     * 房源id
     */
    private Integer roomId;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;


}
