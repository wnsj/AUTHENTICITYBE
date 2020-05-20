package com.jiubo.buildstore.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BuildMainBean {

    private List<BuildReturn> commendList;

    private List<BuildReturn> qualityList;

    private List<BuildReturn> newPopularityList;

    private List<BuildReturn> newHotSearchList;

    private List<BuildReturn> newSellWellList;

  /*  private List<BuildingBean> commendPopularityList;

    private List<BuildingBean> commendHotSearchList;

    private List<BuildingBean> commendSpecialOfferList;*/
}
