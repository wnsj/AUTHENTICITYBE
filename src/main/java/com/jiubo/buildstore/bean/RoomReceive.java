package com.jiubo.buildstore.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author swd
 * @since 2020-09-11
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoomReceive implements Serializable{
//	private List<Integer> buildIdList;
	/**
	 * 是否在售集合(1在售；2已售)
	 */
//	private List<Integer> isSaleList;
	/**
	 * 省份IDList
	 */
//	private List<Integer> proIdList;
	/**
	 * 区域idList
	 */
	private List<Integer> ldIdList;

	/**
	 * 开发商id集合
	 */
//	private List<Integer> devIdList;
	/**
	 * 单价id集合
	 */
	private List<Integer> unitPriceIdList;
	
	/**
	 * 总价id集合
	 */
	private List<Integer> totalPriceIdList;
	
	/**
	 * 楼盘类型ID集合
	 */
	private List<Integer> btIdList;
	/**
	 * 面积id集合
	 */
	private List<Integer> areaIdList;
	/**
	 * 楼盘户型ID集合
	 */
//	private List<Integer> bhtIdList;
	/**
	 * 页码
	 */

	private String current;

	/**
	 * 每页尺寸
	 */

	private String pageSize;
	/**
	 * 排序依据
	 */

	private String pageOrder;

	/**
	 * 时间排序(1:升序；2降序)
	 */

//	private Integer dateSort;

	/**
	 * 价格排序(1:升序；2降序)
	 */

//	private Integer priceSort;
	/**
	 * 房源特色id集合
	 */
//	private List<Integer> chaIdList;

//	private List<Map<String, Object>> openTimeList;

	private List<Map<String, Object>> areaList;

	private List<Map<String, Object>> totalPriceList;

	private List<Map<String, Object>> unitPriceList;

//	private String startTime;
//	private String endTime;
	private List<Integer> regionIdList;

	/**
	 * 模糊查询字段
	 */
//	private String special;

	/**
	 * 地铁ID集合
	 */
//	private List<Integer> metroIdList;
//
//	private Integer metroId;
	

}
