package org.evm.biz.phoneapp.entity;

public class AppDataStVO {
	/**
	 * 施工中订单数
	 */
	private int workingnum;
	/**
	 * 工程订单总数
	 */
	private int workallnum;
	/**
	* 运维中订单数
	*/
	private int repairingnum;
	/**
	* 运维订单总数
	*/
	private int repairallnum;

	public int getWorkingnum() {
		return workingnum;
	}

	public void setWorkingnum(int workingnum) {
		this.workingnum = workingnum;
	}

	public int getWorkallnum() {
		return workallnum;
	}

	public void setWorkallnum(int workallnum) {
		this.workallnum = workallnum;
	}

	public int getRepairingnum() {
		return repairingnum;
	}

	public void setRepairingnum(int repairingnum) {
		this.repairingnum = repairingnum;
	}

	public int getRepairallnum() {
		return repairallnum;
	}

	public void setRepairallnum(int repairallnum) {
		this.repairallnum = repairallnum;
	}

}
