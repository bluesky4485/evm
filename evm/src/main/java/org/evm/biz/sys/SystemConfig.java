package org.evm.biz.sys;

public class SystemConfig {
	/**
	 * 服务域
	 */
	public static String serverName;
	public static String loginPage;
	public static String mainPage;
	public static String systemId;
	public static String dbName;
	public static String dbIp;
	public static String dbPort;

	public void setServerName(String serverName) {
		SystemConfig.serverName = serverName;
	}

	public void setLoginPage(String loginPage) {
		SystemConfig.loginPage = loginPage;
	}

	public void setMainPage(String mainPage) {
		SystemConfig.mainPage = mainPage;
	}

	public void setSystemId(String systemId) {
		SystemConfig.systemId = systemId;
	}

	public void setDbName(String dbName) {
		SystemConfig.dbName = dbName;
	}

	public void setDbIp(String dbIp) {
		SystemConfig.dbIp = dbIp;
	}

	public void setDbPort(String dbPort) {
		SystemConfig.dbPort = dbPort;
	}

}
