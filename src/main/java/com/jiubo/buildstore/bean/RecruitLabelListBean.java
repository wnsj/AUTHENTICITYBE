package com.jiubo.buildstore.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RecruitLabelListBean {
    private List<RecruitBean> hotList;
    private List<RecruitBean> longList;
}
