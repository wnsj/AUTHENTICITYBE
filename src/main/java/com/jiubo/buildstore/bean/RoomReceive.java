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
	private List<Integer> buildIdList;
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
	 * 商圈idlist
	 */
	private List<Integer> bdIdList;

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
	 * 类型ID集合
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
	 * 搜索查询房源
	 */

	private String nameLike;
	
	/**
	 * 排序依据
	 */

	private String pageOrder;
	
	/**
	 * 面积排序
	 */
	private Integer areaOrder;
	
	/**
	 * 单价排序
	 */
	private Integer unitPriceOrder;
	
	/**
	 * 总价排序
	 */
	private Integer totalPriceOrder;
	
	/**
	 * 房源特色id集合
	 */
//	private List<Integer> chaIdList;

//	private List<Map<String, Object>> openTimeList;

	private List<Map<String, Object>> areaList;

	private List<Map<String, Object>> totalPriceList;

	private List<Map<String, Object>> unitPriceList;

	private List<Integer> regionIdList;
	
	/**
	 * 是否查询热门 2热门
	 */
	private Integer isHot;
	
	/**
	 * 是否在首页用于共享办公
	 */
	private Integer isHome;
	
	/**
	 * 商铺类别id
	 */
	private Integer stId;
	
	/**
	 * 商铺业态
	 */
	private String caId;
	
	/**
	 * 商铺业态
	 */
	private List<Integer> caIdList;

	/**
	 * 房源类型1,写字楼，2，共享，3商铺
	 */
	private Integer type;

}
