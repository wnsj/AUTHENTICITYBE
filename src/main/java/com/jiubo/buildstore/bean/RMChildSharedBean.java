package com.jiubo.buildstore.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RMChildSharedBean extends RoomMainBean {
    /**
     * 商圈名
     */

    private String buName;
    /**
     * 区域名
     */

    private String ldName;
    private ShareRoomBean shareRoomBean;
    private List<OfficeBean> officeBeanList;
}
