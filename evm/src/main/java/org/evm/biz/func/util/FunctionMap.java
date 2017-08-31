package org.evm.biz.func.util;

/**
 * 权限表映射
 * 
 * @author admin
 *
 */
public class FunctionMap {
	public final static String NO_FUNC = "-1";
	// WEB
	/**
	 *  WEB业务端-登录
	 */
	public final static String WEB_CONNECT = "10001";
	/**
	 * WEB业务端-查询(所有查询)
	 */
	public final static String WEB_QUERY = "10002";
	/**
	 * WEB业务端-增、删、改(一般)
	 */
	public final static String WEB_ADD = "10003";
	/**
	 * WEB业务端-增、删、改(一般)
	 */
	public final static String WEB_DELETE = "10003";
	/**
	 * WEB业务端-增、删、改(一般)
	 */
	public final static String WEB_UPDATE = "10003";
	/**
	 * WEB业务端-指定施工负责人(项目经理
	 */
	public final static String WEB_ORDER_WORK = "10004";
	/**
	 * WEB业务端-施工内检结果(项目经理) 
	 */
	public final static String WEB_ORDER_CHECK = "10005";
	// MIS
	/**
	 * MIS端-登录 
	 */
	public final static String MIS_CONNECT = "20001";
	/**
	 * MIS端-查询(所有查询)
	 */
	public final static String MIS_QUERY = "20002";
	/**
	 * MIS端-增、删、改
	 */
	public final static String MIS_ADD = "20003";
	/**
	 * MIS端-增、删、改
	 */
	public final static String MIS_DELETE = "20003";
	/**
	 * MIS端-增、删、改
	 */
	public final static String MIS_UPDATE = "20003";
	// 用户APP
	/**
	 *    用户APP端-登录 
	 */
	public final static String APP_LOGOIN_USR = "30001";
	/**
	 * 用户APP端-修改密码
	 */
	public final static String APP_UPDPWD = "30002";
	/**
	 * 用户APP端-查询
	 */
	public final static String APP_QUERY = "30003";
	/**
	 * 用户APP端-修改某一工程订单的评价信息
	 */
	public final static String APP_UPD_ORDER_USERAPPRISE = "30004";
	/**
	 * 用户APP端-修改某一工程订单的维修评价信息
	 */
	public final static String APP_UPD_MORDER_USERAPPRISE = "30005";
	// 工程APP
	/**
	 * 工程APP端-登录
	 */
	public final static String APP_LOGOIN_PROJECT = "40001";
	/**
	 * 工程APP端-订单修改(包括对设备的修改)
	 */
	public final static String APP_UPD_PROJECT = "40002";
	/**
	 * 用户APP端-查询（所有查询）
	 */
	public final static String APP_QUERY_PROJECT = "40003";
	// 维修APPAPP
	/**
	 * 维修APP端-登录
	 */
	public final static String APP_LOGOIN_MORDER = "50001";
	/**
	 * 维修APP端-维修订单修改
	 */
	public final static String APP_UPD_MORDER = "50002";
	/**
	 * 用户APP端-维修订单查询（所有查询）
	 */
	public final static String APP_QUERY_MORDER = "50003";
}
