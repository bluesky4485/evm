package org.evm.core.consts;

public enum EventType {
	queryEvent {//查询
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "1";
		}
	},
	insertEvent {//插入
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "2";
		}
	},
	updateEvent {//更新
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "3";
		}
	},
	deleteEvent {//删除
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "4";
		}
	},
	exportEvent {//导出
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "5";
		}
	},
	loginEvent {//登录系统
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "6";
		}
	},
	logoutEvent {//退出系统
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "7";
		}
	},
	sendCmdEvent {//指令下发
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "8";
		}
	},
}
