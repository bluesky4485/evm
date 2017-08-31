package org.evm.biz.order.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class OrderNoBuilder {
	private static Object locker = new Object();

	private static int sn = 0;
	private static int smn = 0;

	/**
	 * 生成订单好 update by jerry.x 2016年10月26日
	 * 
	 * @return
	 *
	 */
	public static String BuildOrderNo() {
		synchronized (locker) {
			if (sn == 99999)
				sn = 0;
			else
				sn++;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			return "P"+sdf.format(new Date()) + String.valueOf(sn);
		}
	}

	/**
	 * 生成运维订单号 update by jerry.x 2016年10月26日
	 * 
	 * @return
	 *
	 */
	public static String BuildMOrderNo() {
		synchronized (locker) {
			if (smn == 99999)
				smn = 0;
			else
				smn++;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			return "M"+sdf.format(new Date()) + String.valueOf(smn);
		}
	}

	public static String BuildOrderNoPre() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}

	public static void main(String[] args) {
		System.out.println(OrderNoBuilder.BuildOrderNo());
		System.out.println(UUID.randomUUID());
	}
}
