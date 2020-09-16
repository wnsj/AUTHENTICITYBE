package com.jiubo.buildstore.service;


import com.jiubo.buildstore.bean.RoomBean;


import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author swd
 * @since 2020-09-11
 */
public interface RoomService extends IService<RoomBean> {

	public Integer addRoom(RoomBean bean, MultipartFile[] picture
			, MultipartFile[] video, MultipartFile headPicture) throws IOException;

}
