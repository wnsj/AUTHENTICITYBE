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
@TableName("building_type")
public class BuildingTypeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "BT_ID", type = IdType.AUTO)
    private Integer btId;
    
    /**
     * 类型名字
     */
    private String btName;
   
    /**
     * 1,写字楼，2，共享，3商铺
     */
    private Integer type;
}
