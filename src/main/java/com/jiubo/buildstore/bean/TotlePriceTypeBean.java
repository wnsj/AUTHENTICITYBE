package com.jiubo.buildstore.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
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
@TableName("totle_price_type")
public class TotlePriceTypeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "TP_ID", type = IdType.AUTO)
    private Integer tpId;

    private BigDecimal begPrice;

    private BigDecimal endPrice;

    @TableField(exist = false)
    private List<Integer> idList;
    
    /**
     * 1,写字楼，2，共享，3商铺
     */
    private Integer type;
}
