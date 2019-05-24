package com.wei.order.dao;

import com.wei.model.XcOrders;

import java.util.List;

/**
 * 订单 数据层
 * 
 * @author ruoyi
 * @date 2019-05-23
 */
public interface XcOrdersMapper 
{
	/**
     * 查询订单信息
     * 
     * @param orderNumber 订单ID
     * @return 订单信息
     */
	public XcOrders selectXcOrdersById(String orderNumber);
	
	/**
     * 查询订单列表
     * 
     * @param xcOrders 订单信息
     * @return 订单集合
     */
	public List<XcOrders> selectXcOrdersList(XcOrders xcOrders);
	
	/**
     * 新增订单
     * 
     * @param xcOrders 订单信息
     * @return 结果
     */
	public int insertXcOrders(XcOrders xcOrders);
	
	/**
     * 修改订单
     * 
     * @param xcOrders 订单信息
     * @return 结果
     */
	public int updateXcOrders(XcOrders xcOrders);
	
	/**
     * 删除订单
     * 
     * @param orderNumber 订单ID
     * @return 结果
     */
	public int deleteXcOrdersById(String orderNumber);
	
	/**
     * 批量删除订单
     * 
     * @param orderNumbers 需要删除的数据ID
     * @return 结果
     */
	public int deleteXcOrdersByIds(String[] orderNumbers);
	
}