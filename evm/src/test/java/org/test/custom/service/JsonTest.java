package org.test.custom.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.evm.biz.user.entity.UserVO;
import org.evm.biz.webService.entity.WsEntity;

import com.alibaba.fastjson.JSON;

public class JsonTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float a = 1f;
		float b = 3f;
		float aa = a  / b;
		System.out.println(aa + "");
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		String rootDir = sdf.format(new Date());
		System.out.println(rootDir);
	}

	public static void main1(String[] args) {
		// TODO Auto-generated method stub

		WsEntity vo = new WsEntity();
		vo.setServiceId("100001");
		vo.setProjectID("1");
		vo.setSeq(System.currentTimeMillis() + "'");
		vo.setToken(System.currentTimeMillis() + "'");
		vo.setUid("1");
		UserVO user = new UserVO();
		vo.setPara("{\"uname\":admin,\"upwd\":sa1234,\"uid\":\"1\"}");
		UserVO paraEntity = JSON.parseObject("{uname:\"admin\",upwd:\"sa1234\"}", UserVO.class);
		System.out.println(JSON.toJSON(vo));
		// {"uid":"1","projectID":"1","serviceId":"100001","token":"1477920232346'","seq":"1477920232346'","para":"{\"uname\":\"admin\",\"upwd\":\"F647E02A69AB0E51780373F86F89A12A\",\"uid\":\"1\"}"}
		// vo.setSeq("123");
		// CustomVO a = new CustomVO();
		// a.setCidCard("123");
		// a.setCno("dfsdf");
		//
		// System.out.println(JSON.toJSON(vo));
		// String testStr =
		// "{\"seq\":\"123\",\"para\":{\"endPageNum\":0,\"pageSize\":0,\"cno\":\"dfsdf\",\"pageNum\":0,\"cidCard\":\"123\",\"startPageNum\":0}}";
		// vo = (WsEntity) JSON.parseObject(testStr, WsEntity.class);
		// System.out.println(vo.toString());

	}

}
