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
 * 
 * </p>
 *
 * @author dx
 * @since 2020-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("building_dynamic")
public class BuildingDynamicBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "BD_ID", type = IdType.AUTO)
    private Integer bdId;

    private String bdName;

    private String bdContent;

    private LocalDateTime createDate;


    private String proCer;

    private LocalDateTime cerDate;


}
