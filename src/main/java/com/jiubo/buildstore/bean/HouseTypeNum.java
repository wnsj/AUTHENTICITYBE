package com.jiubo.buildstore.bean;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class HouseTypeNum {
    /**
     * 居室名字
     */
    private String houseName;
    /**
     * 居室数量
     */
    private Integer houseNum;
}
