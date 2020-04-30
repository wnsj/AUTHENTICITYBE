package com.jiubo.buildstore.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author syl
 * @since 2020-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("article")
public class ArticleBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章类型
     */
    private Integer articleType;

    private String articleTypeLabel;
    /**
     * 文章内容
     */
    private String articleContent;

    /**
     * 文章标题
     */
    private String title;

    /**
     * (奖项、发展历程等时间)
     */
    private Date aboutTime;

    /**
     * 图片路径
     */
    private String imgPath;

    /**
     * 图片名字
     */
    private String imgName;

    /**
     * 创建时间
     */
    private Date createTime;

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
     * 修改时间
     */
    private LocalDateTime modifyTime;


}
