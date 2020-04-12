package com.jiubo.buildstore.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("counselor")
public class CounselorBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "COU_ID", type = IdType.AUTO)
    private Integer couId;

    private String couName;

    private String introduce;

    private Integer clId;

    private String graduate;

    private Integer ccId;


}
