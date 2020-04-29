package com.jiubo.buildstore.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@TableName("counselor")
public class CounselorBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "COU_ID", type = IdType.AUTO)
    private Integer couId;

    private String couName;

    private String introduce;

    private String graduate;

    private Integer ccId;

    private String tel;

    private String charaName;

    private String couLabel;

    private List<Integer> labelList;

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

    private String pictureName;

    private String picturePath;
}
