package org.evm.biz.func.util;

public enum FuncDesc {
	/**
	 * WEB业务端-指定施工负责人(项目经理)
	 */
	dispatchWorkPm {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "10004";
		}
	},
	/**
	 * WEB业务端-施工内检结果(项目经理)
	 * 
	 */
	dispatchCheckResult {
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "10005";
		}
	}
}
