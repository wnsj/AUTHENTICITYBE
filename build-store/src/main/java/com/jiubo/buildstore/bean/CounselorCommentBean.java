package com.jiubo.buildstore.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@TableName("counselor_comment")
public class CounselorCommentBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "COUC_ID", type = IdType.AUTO)
    private Integer coucId;

    private String comContent;

    private Integer cId;

    private Integer bId;

    private LocalDateTime comDate;

    private Integer praiseNum;


}
