package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.RoomBean;
import com.jiubo.buildstore.bean.RoomReceive;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

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
			, MultipartFile[] video) throws IOException;

}
