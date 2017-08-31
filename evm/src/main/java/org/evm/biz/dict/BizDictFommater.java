package org.evm.biz.dict;

import java.util.HashMap;
import java.util.Map;

public class BizDictFommater {
	static Map<String, String> SexMap = new HashMap<String, String>();
	//
	static Map<String, String> UserTypeMap = new HashMap<String, String>();
	// 构建方式
	static Map<String, String> BuildTypeMap = new HashMap<String, String>();
	// 工程类别
	static Map<String, String> ProjectTypeMap = new HashMap<String, String>();
	// 订单状态
	static Map<String, String> OrderStatusMap = new HashMap<String, String>();
	// 施工内检结果
	static Map<String, String> WorkCheckResultMap = new HashMap<String, String>();
	// 施工类型
	static Map<String, String> WorkTypeMap = new HashMap<String, String>();
	// 施工状态
	static Map<String, String> WorkStatusMap = new HashMap<String, String>();
	// 验收状态accept
	static Map<String, String> AcceptStatusMap = new HashMap<String, String>();
	// 维修状态（
	static Map<String, String> MaintainStatusMap = new HashMap<String, String>();
	//设备类型
	static Map<String, String> DeviceTypeMap = new HashMap<String, String>();

 
	BizDictFommater() {
		SexMap.put("0", "男");
		SexMap.put("1", "女");

		UserTypeMap.put("0", "报警用户");
		UserTypeMap.put("1", "天网用户");

		BuildTypeMap.put("0", "自建");
		BuildTypeMap.put("1", "外包");

		ProjectTypeMap.put("0", "报警");
		ProjectTypeMap.put("1", "天网");
		ProjectTypeMap.put("2", "系统集成");

		WorkCheckResultMap.put("0", "未内检");
		WorkCheckResultMap.put("1", "内检通过");
		WorkCheckResultMap.put("2", "内检不通过");

		WorkTypeMap.put("0", "报警项目");
		WorkTypeMap.put("1", "天网项目");

		WorkStatusMap.put("0", "创建");
		WorkStatusMap.put("1", "预约");
		WorkStatusMap.put("2", "施工中");
		WorkStatusMap.put("3", "施工完成");
		WorkStatusMap.put("4", "验收完成");

		DeviceTypeMap.put("0", "设备");
		DeviceTypeMap.put("1", "线材");
		DeviceTypeMap.put("2", "辅助材料");
	}

	public static String Sexformater(String key) {
		return SexMap.get(key);
	}

	public static String UserTypeformater(String key) {
		return UserTypeMap.get(key);
	}

	public static String BuildTypeformater(String key) {
		return BuildTypeMap.get(key);
	}

	public static String ProjectTypeformater(String key) {
		return ProjectTypeMap.get(key);
	}

	public static String OrderStatusformater(String key) {
		return OrderStatusMap.get(key);
	}

	public static String WorkCheckResultformater(String key) {
		return WorkCheckResultMap.get(key);
	}

	public static String WorkTypeformater(String key) {
		return WorkTypeMap.get(key);
	}

	public static String WorkStatusformater(String key) {
		return WorkStatusMap.get(key);
	}

	public static String WorkAcceptStatusformater(String key) {
		return AcceptStatusMap.get(key);
	}

	public static String WorkMaintainStatusformater(String key) {
		return MaintainStatusMap.get(key);
	}

	public static String DeviceTypeformater(String key) {
		return DeviceTypeMap.get(key);
	}
}
