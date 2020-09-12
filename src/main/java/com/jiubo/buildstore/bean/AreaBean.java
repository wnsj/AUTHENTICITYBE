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
@TableName("area")
public class AreaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "AR_ID", type = IdType.AUTO)
    private Integer arId;

    private Integer begArea;

    private Integer endArea;
    @TableField(exist = false)
    private List<Integer> idList;
    
    /**
     * 1,写字楼，2，共享，3商铺
     */
    private Integer type;
}
