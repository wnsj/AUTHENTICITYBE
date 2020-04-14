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
@TableName("horse_type")
public class HorseTypeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "HT_ID", type = IdType.AUTO)
    private Integer htId;

    /**
     *户型分析类型名
     */
    private String caName;


}
