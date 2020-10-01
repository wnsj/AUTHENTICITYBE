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

public class BuildReturn extends BuildingBean {


    /**
     * 楼盘类型名
     */
    private String btName;

    /**
     * 房源特色名
     */
    private String chaName;


    /**
     * 商圈名
     */
    private String buName;


    /**
     * 视频路径
     */
    private String videoPath;

    private String videoName;
    /**
     * 图片路径
     */
    private List<String> picturePath;

    /**
     * 区域名
     */
    private String ldName;


    /**
     * 页码
     */

    private String current;

    /**
     * 每页尺寸
     */

    private String pageSize;

    /**
     * 咨询顾问
     */
    private CounselorBean counselorBean;

    /**
     * 共享办公类型户型个数
     */
    private Integer houseNum;

    /**
     * 共享办公可租房源数
     */
    private Integer shareIsRentNum;

    private List<String> charaNameList;

    private List<Integer> ChaIdList;

    private List<RoomMainBean> roomMainBeanList;

    List<OfficeBean> officeBeanList;

    /**
     * 类型集合
     */
    private List<Integer> buildTypeList;
    /**
     * 基础服务集合
     */
    private List<Integer> chaList;

    /**
     * 网点介绍
     */
    private String produce;
}
