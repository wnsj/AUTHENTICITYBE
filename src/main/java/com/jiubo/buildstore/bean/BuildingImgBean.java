package com.jiubo.buildstore.bean;

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
     * 楼盘id
     */
    private Integer bId;

    /**
     * 图片路径
     */
    private String imgPath;

    /**
     * 楼盘id集合
     */
    private List<Integer> bIdList;

    /**
     * 评论id 集合
     */
    private List<Integer> coucIdList;
    /**
     * 户型分析id
     */
    private Integer baId;

     /**
     * 咨询师评论ID
     */
    private Integer coucId;
}
