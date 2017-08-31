package org.evm.core.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.net.SyslogAppender;



/**
*日志服务
*/
public class DataCenterLogger {
    /**
     * 自定义级别
     */
    private Level SERIOUS_LEVEL = new LogLevel(20002, "exchange", SyslogAppender.LOG_LOCAL0);

    /**登陆ID*/
    private static final String MDC_LOGIN_ID = "LOGIN_ID";

    /**IP地址*/
    private static final String MDC_IP = "IP";
    
    /**log 声明*/
    private Logger log = null;

    /**
     * 取得类名
     * @param clazz
     */
    public DataCenterLogger(Class clazz) {
        log = Logger.getLogger(clazz);
    }

    /**
     * IP地址
     * @param IP
     */
    public static void setIP(String IP) {
        MDC.put(MDC_IP, IP);
    }

    /**
     * 登陆ID
     * @param loginID
     */
    public static void setLoginID(String loginID) {
        MDC.put(MDC_LOGIN_ID, loginID);
    }

    /**
     * LogLevel接口中的所有自定义日志级别都继承自Level
     * @param debug
     */
    public void debug(Object debug) {
        if (log.isEnabledFor(SERIOUS_LEVEL)) {
            log.log(SERIOUS_LEVEL, debug);
        }
    }

    /**
     * 显示系统提示信息
     * @param info
     */
    public void info(Object info) {
    	if(log.isInfoEnabled()){
    		log.info(info);
    	}
    }

    /**
     * 用于显示错误信息
     * @param error
     */
    public void error(Object message) {
        log.error(message);
    }

    /**
     * 用于显示错误信息
     * @param error
     */
    public void error(Object message, Throwable throwable) {
        log.error(message, throwable);
    }
}