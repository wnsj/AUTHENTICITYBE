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
@TableName("building_img")
public class BuildingImgBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "IMG_ID", type = IdType.AUTO)
    private Integer imgId;

    private String imgName;

    private LocalDateTime createDate;

    private Integer itId;

    private Integer bId;


}
