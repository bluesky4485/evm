package org.evm.biz.dbmanager.web;

import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.evm.biz.dbmanager.entity.BakFileVO;
import org.evm.biz.sys.SystemConfig;
import org.evm.core.consts.MessageType;
import org.evm.core.web.AbstractMultiController;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DatabaseManagerController extends AbstractMultiController {
	private ComboPooledDataSource comboPooledDataSource;
	private DruidDataSource druidDataSource;

	public void setComboPooledDataSource(ComboPooledDataSource comboPooledDataSource) {
		this.comboPooledDataSource = comboPooledDataSource;
	}

	public void setDruidDataSource(DruidDataSource druidDataSource) {
		this.druidDataSource = druidDataSource;
	}

	private String getUserName() {
		return druidDataSource.getUsername();
	}
	private String getUserPwd() {
		return druidDataSource.getPassword();
	}
 

	/**
	 * 备份
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void ajaxDoMysqldump(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO:mysqldump -h127.0.0.1 -p3306 -uroot -p123 --lock-all-tables
		// evm>c:\test1.sql
		// mysqldump -uroot -pQmgps2008@123 --lock-all-tables
		// evm>/tmp/xh2mysqldumptest.sql
		// 操作系统
		String osname = System.getProperty("os.name");
		String sourceFolder = getFileServerPath();

		String hostIP = SystemConfig.dbIp;
		String port = SystemConfig.dbPort;
		String userName = this.comboPooledDataSource.getUser();
		String password = this.comboPooledDataSource.getPassword();
		String databaseName = SystemConfig.dbName;// this.comboPooledDataSource.getDataSourceName();
		String filePath = sourceFolder + "/" + databaseName + "_" + System.currentTimeMillis() + ".sql";
		String execStr = "";
		String msg = "";
		Runtime rt = Runtime.getRuntime();
		Process process = null;
		try {
			if (osname.startsWith("Linux")) {
				execStr = "mysqldump -h" + hostIP + " -p" + port + " -u" + userName + " -p" + password
						+ " --lock-all-tables " + databaseName + ">" + filePath;
				logger.debug(osname + "执行备份语句" + execStr);
				rt.exec(new String[] { "sh", "-c", execStr }).waitFor();

			} else {
				execStr = " cmd /c mysqldump -h" + hostIP + " -p" + port + " -u" + userName + " -p" + password
						+ " --lock-all-tables " + databaseName + ">\"" + filePath + "\"";
				logger.debug(osname + "执行备份语句" + execStr);
				rt.exec(execStr).waitFor();
			}
			logger.debug("备份完成；文件=" + filePath);
			msg = ("备份完成");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("mysql备份异常" + e);
			msg = "mysql备份异常" + e.getMessage();
		}
		super.ReturnAjaxMessage(response, msg, MessageType.info);
	}

	/**
	 * 还原
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void ajaxDoMysqlRestore(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// mysql -uroot -p123 evm < XXX.sql
		String msg = "";
		String osname = System.getProperty("os.name");
		BakFileVO whereCause = new BakFileVO();
		bindObject(request, whereCause);
		String userName = this.comboPooledDataSource.getUser();
		String password = this.comboPooledDataSource.getPassword();
		String databaseName = SystemConfig.dbName;// this.comboPooledDataSource.getDataSourceName();
		String execStr = "";
		try {
			String fPath = getFileServerPath() + "//" + whereCause.getFileName();
			Runtime rt = Runtime.getRuntime();
			if (osname.startsWith("Linux")) {
				execStr = "mysql -u" + userName + " -p" + password + "  " + databaseName + "<" + fPath;
				logger.debug(osname + "执行还原语句" + execStr);
				rt.exec(new String[] { "sh", "-c", execStr }).waitFor();
			} else {
				execStr = " cmd /c mysql -u" + userName + " -p" + password + "  " + databaseName + "<\"" + fPath + "\"";
				logger.debug(osname + "执行备份语句" + execStr);
				rt.exec(execStr).waitFor();
			}
			msg = "数据库还原成功";
		} catch (Exception e) {
			logger.error("回复失败", e);
			msg = "数据库还原失败";
		}
		super.ReturnAjaxMessage(response, msg, MessageType.info);
	}

	/**
	 * 查询备份文件
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void ajaxListBakFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sourceFolder = getFileServerPath();
		List<BakFileVO> fileList = new ArrayList<BakFileVO>();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		File rootfile = new File(sourceFolder);
		if (rootfile.isDirectory()) {
			File[] subFiles = rootfile.listFiles();
			for (File file : subFiles) {
				BakFileVO bakfile = new BakFileVO();
				String fileName = file.getName();
				Date dt = new Date(file.lastModified());
				String modefiedTime = sdf.format(dt);
				String fileSize = this.getFileSize(file) + "";
				bakfile.setFileName(fileName);
				bakfile.setFileSize(fileSize);
				bakfile.setModefiedTime(modefiedTime);
				fileList.add(bakfile);
			}
		}
		if (fileList.size() > 0) {
			Collections.sort(fileList, new Comparator<BakFileVO>() {
				public int compare(BakFileVO arg0, BakFileVO arg1) {
					SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
					Date d1 = new Date();
					try {
						d1 = sdf.parse(arg0.getModefiedTime());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						logger.error("异常", e1);
					}
					Date d2 = d1;
					try {
						d2 = sdf.parse(arg1.getModefiedTime());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						logger.error("异常", e);
					}
					return d2.compareTo(d1);
				}
			});
		}

		super.ReturnAjaxResult(response, fileList);
	}

	/**
	 * 得到文件大小
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	private long getFileSize(File file) throws Exception {
		FileChannel fc = null;
		if (file.exists() && file.isFile()) {
			FileInputStream fis = new FileInputStream(file);
			fc = fis.getChannel();
			return fc.size();
		} else {
			logger.info("file doesn't exist or is not a file");
		}
		return -1;
	}

	/**
	 * 
	 * @return
	 */
	public String getFileServerPath() {
		return getServletContext().getRealPath("/") + "dbbaks/";
	}

}
