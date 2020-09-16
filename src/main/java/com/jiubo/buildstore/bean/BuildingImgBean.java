package com.jiubo.buildstore.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("building_img")
public class BuildingImgBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "IMG_ID", type = IdType.AUTO)
    private Integer imgId;

    /**
     * 图片名称
     */
    private String imgName;

    /**
     * 上传时间
     */
    private Date createDate;

    /**
     * 图片类型id
     */
    private Integer itId;

    /**
     * 图片路径
     */
    private String imgPath;

    /**
     * 楼盘id集合
     */
    @TableField(exist = false)
    private List<Integer> bIdList;

    /**
     * 评论id 集合
     */
    @TableField(exist = false)
    private List<Integer> coucIdList;
    /**
     * 户型分析id
     */
    private Integer baId;
    
    /**
     * 用于区分房源和楼盘，房源2，楼盘3，咨询师4
     */
    private Integer type;

     /**
     * 房源或楼盘ID
     */
    private Integer infoId;

    @TableField(exist = false)
    private Integer buildId;
}
