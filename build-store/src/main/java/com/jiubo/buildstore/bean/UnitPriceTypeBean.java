package com.jiubo.buildstore.bean;

import java.math.BigDecimal;
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
@TableName("unit_price_type")
public class UnitPriceTypeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "UP_ID", type = IdType.AUTO)
    private Integer upId;

    private BigDecimal begPrice;

    private BigDecimal endPrice;


}
