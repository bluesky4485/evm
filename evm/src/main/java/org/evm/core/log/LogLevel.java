package org.evm.core.log;

import org.apache.log4j.Level;


/**
 * 日志
*/
public class LogLevel extends Level {
    /**
     * 自定义日志级别
     * @param level 
     * @param name 
     * @param sysLogLevel 
     */
    public LogLevel(int level, String name, int sysLogLevel) {
        super(level, name, sysLogLevel);
    }
}
