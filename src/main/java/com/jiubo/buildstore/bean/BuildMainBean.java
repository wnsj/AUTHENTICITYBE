package com.jiubo.buildstore.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BuildMainBean {

    private List<BuildingBean> commendList;

    private List<BuildingBean> qualityList;

    private List<BuildingBean> newPopularityList;

    private List<BuildingBean> newHotSearchList;

    private List<BuildingBean> newSellWellList;

  /*  private List<BuildingBean> commendPopularityList;

    private List<BuildingBean> commendHotSearchList;

    private List<BuildingBean> commendSpecialOfferList;*/
}
