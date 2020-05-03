package com.jiubo.buildstore.bean;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CouBean {
    private Page<CounselorCommentBean> couPage;
    private List<CouTypeBean> couTypeBeanList;
}
