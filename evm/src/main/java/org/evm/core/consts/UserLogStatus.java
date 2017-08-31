package org.evm.core.consts;

public enum UserLogStatus {
	login {// 查询
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "1";
		}
	},
	signOut {// 查询
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "2";
		}
	},
	releaseLock {// 查询
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "3";
		}
	},
}
