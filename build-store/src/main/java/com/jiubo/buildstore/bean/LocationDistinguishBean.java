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
 * @author syl
 * @since 2020-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("location_distinguish")
public class LocationDistinguishBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LD_ID", type = IdType.AUTO)
    private Integer ldId;

    private String ldName;

    private Integer proId;

    private Integer ltId;


}
