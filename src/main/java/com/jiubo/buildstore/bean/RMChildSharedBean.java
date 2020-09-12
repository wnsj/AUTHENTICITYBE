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
    @TableField(exist = false)
    private String buName;
    /**
     * 区域名
     */
    @TableField(exist = false)
    private String ldName;
    private List<AloneRoomBean> aloneRoomBeanList;
    private List<OpenRoomBean> openRoomBeanList;
}
