package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.RoomBean;
import com.jiubo.buildstore.bean.StoreRoomBean;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商铺房源 服务类
 * </p>
 *
 * @author swd
 * @since 2020-09-12
 */
public interface StoreRoomService extends IService<StoreRoomBean> {

	public Integer addStoreRoom(StoreRoomBean bean, MultipartFile[] picture, MultipartFile[] video, MultipartFile headPicture[]) throws IOException;

	public Integer updateStoreRoom(StoreRoomBean bean, MultipartFile[] picture, MultipartFile[] video,
			MultipartFile headPicture) throws IOException;

}
