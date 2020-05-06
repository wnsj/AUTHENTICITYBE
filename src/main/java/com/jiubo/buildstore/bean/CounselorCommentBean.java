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
@TableName("counselor_comment")
public class CounselorCommentBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "COUC_ID", type = IdType.AUTO)
    /**
     * 咨询师评论ID
     */
    private Integer coucId;

    /**
     * 评论内容
     */
    private String comContent;

    /**
     * 咨询师ID
     */
    private Integer couId;

    /**
     * 楼盘ID
     */
    private Integer buildId;

    private List<Integer> bIdList;
    /**
     * 评论时间
     */
    private Date comDate;

    private String comTime;
    /**
     * 点赞数
     */
    private Integer praiseNum;

    /**
     * 评论类型
     */
    private Integer coucType;

    /**
     * 联系方式
     */
    private String tel;

    /**
     * 咨询师名字
     */
    private String couName;

    /**
     * 简单介绍
     */
    private String introduce;

    /**
     * 毕业院校
     */
    private String graduate;

    /**
     * 特长id
     */
    private Integer ccId;

    /**
     * 咨询师实体
     */
    private CounselorBean counselorBean;
    /**
     * 特长描述
     */
    private String ccContent;

    /**
     * 咨询师标签集合
     */
    private List<String> clContentList;

    private String pictureName;

    private String picturePath;

    private List<String> imgPathList;
    private String htName;

    private String couTypeName;
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
}
