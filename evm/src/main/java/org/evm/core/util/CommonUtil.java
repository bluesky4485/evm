package org.evm.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommonUtil {
	/**
	 * 返回startPageNum,endPageNum
	 * 
	 * @param pageSize
	 * @param PageNum
	 * @return
	 */
	public static int[] calculatePage(int pageSize, int pageNum) {
		if (pageNum <= 0)
			pageNum = 1;
		if (pageSize <= 0)
			pageSize = 12;
		int startPageNum = (pageNum - 1) * (pageSize);
		int endPageNum = startPageNum + pageSize;
		int pageScope[] = { startPageNum, endPageNum };
		return pageScope;
	}
	/**
	 * 对字符串进行MD5加密
	 * @param originString
	 * @return
	 */
	public static String encodeByMD5(String originString) {
		if (originString != null) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				byte[] results = md.digest(originString.getBytes());
				// 将得到的字节数组变成字符串返回
				String resultString = converBytesToHexStr(results);
				String pass = resultString.toUpperCase();
				return pass;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * byte数组转化为可读的十六进制字符串
	 * 
	 * @param protoBytes
	 * @return
	 */
	public static String converBytesToHexStr(byte[] protoBytes) {
		StringBuffer buf = new StringBuffer();
		if ((protoBytes == null) || (protoBytes.length <= 0)) {
			return "";
		}
		for (int i = 0; i < protoBytes.length; i++) {
			int v = protoBytes[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				buf.append(0);
			}
			buf.append(hv);
		}
		return buf.toString().toUpperCase();
	}

	/**
	 * 十六进制字符串，实现右侧补零，用于总长度一定，有效数据位数不固定的场合，右侧补零
	 * 
	 * @param hexStr
	 * @param leng
	 *            指定返回字符串长度为：leng*2
	 * @return 右补位后的十六进制字符串
	 * @throws NoSuchAlgorithmException
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(CommonUtil.encodeByMD5("123456"));
		MessageDigest md = MessageDigest.getInstance("MD5");
		// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
		byte[] results = md.digest("123456".getBytes());
		System.out.println(converBytesToHexStr(results).length());
	}
}