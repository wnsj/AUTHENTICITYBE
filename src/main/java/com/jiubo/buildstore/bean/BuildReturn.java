package com.jiubo.buildstore.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class BuildReturn extends BuildingBean{


    /**
     * 楼盘类型名
     */
    private String btName;

    /**
     * 房源特色名
     */
    private String chaName;

    private List<String> charaNameList;



    /**
     * 房源特色id集合
     */
    private List<Integer> chaIdList;

    /**
     * 头图路径
     */
    private String imgPath;


    /**
     * 视频路径
     */
    private String videoPath;

    /**
     * 图片路径
     */
    private List<String> picturePath;
    /**
     * 标签名（随意选的）
     */
    private String label;
    /**
     * 标签集合
     */
    private List<String> labelList;



    private String ldName;


    /**
     * 页码
     */

    private String current;

    /**
     * 每页尺寸
     */

    private String pageSize;


}
