package org.evm.biz.user.entity;

import java.math.BigDecimal;
import java.util.List;

import org.evm.biz.dict.BizDictFommater;
import org.evm.biz.func.entity.FuncVO;
import org.evm.biz.func.util.FuncDesc;
import org.evm.biz.order.entity.OrderVO;
import org.evm.biz.phoneapp.entity.AppDataStVO;
import org.evm.biz.role.entity.RoleVO;
import org.evm.core.entity.AbstractEntity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

/**
 * 用户表
 * 
 * @author jerry.x update 2016年10月10日 下午2:11:49
 */
public class UserVO extends AbstractEntity {
	/**
	 * 用户id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private long uid;
	/**
	 * 登录名
	 */
	private String uname;
	/**
	 * 密码
	 */
	private String upwd;
	/**
	 * 中文名
	 */
	private String ufullName;
	/**
	 * 性别
	 */
	private String usex;
	/**
	 * 电话1
	 */
	private String utel1;
	/**
	 * 电话2
	 */
	private String utel2;
	/**
	 * 身份证
	 */
	private String uidCard;
	/**
	 * 地址描述
	 */
	private String uaddress;
	/**
	 * 经纬度
	 */
	private BigDecimal lat;
	/**
	 * 经纬度
	 */
	private BigDecimal lng;
	/**
	 * 备注
	 */
	private String uremark;
	/**
	 * 删除状态标识
	 */
	private String ustatus;
	/**
	 * 密码是否有变更
	 */
	private String isChangePwd;
	// ------------------------------------------------
	private String allPropertyString;
	/**
	 * 用户拥有的角色
	 */
	private List<RoleVO> roleList;
	/**
	 * 角色串
	 */
	private String roleName;
	/**
	 * 角色ID列表
	 */
	private List<Long> roleIds;
	/**
	 * 用户权限
	 */
	private List<FuncVO> funcList;
	/**
	 * 手机APP 登录用到的统计信息
	 */
	private AppDataStVO appDataStVO;
	/**
	 * 手机APP 登录用到的订单信息
	 */
	private List<OrderVO> appOrderList;
	/**
	 * 手机APP需要的token
	 */
	private String token;
	/**
	 * APP 更新密码
	 */
	private String newPwd;

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public String getUfullName() {
		return ufullName;
	}

	public void setUfullName(String ufullName) {
		this.ufullName = ufullName;
	}

	public String getUsex() {
		return usex;
	}

	public void setUsex(String usex) {
		this.usex = usex;
	}

	public String getUtel1() {
		return utel1;
	}

	public void setUtel1(String utel1) {
		this.utel1 = utel1;
	}

	public String getUtel2() {
		return utel2;
	}

	public void setUtel2(String utel2) {
		this.utel2 = utel2;
	}

	public String getUidCard() {
		return uidCard;
	}

	public void setUidCard(String uidCard) {
		this.uidCard = uidCard;
	}

	public String getUaddress() {
		return uaddress;
	}

	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}

	public String getUremark() {
		return uremark;
	}

	public void setUremark(String uremark) {
		this.uremark = uremark;
	}

	public String getUstatus() {
		return ustatus;
	}

	public void setUstatus(String ustatus) {
		this.ustatus = ustatus;
	}

	public String getIsChangePwd() {
		return isChangePwd;
	}

	public void setIsChangePwd(String isChangePwd) {
		this.isChangePwd = isChangePwd;
	}

	public void caculateJsonString() {
		// TODO Auto-generated method stub
		String[] filterFields = new String[] { "userId", "userName", "userIp", "userArea", "userAreaText", "fullName",
				"companyName" };
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(this.getClass(), filterFields);
		allPropertyString = JSON.toJSONString(this, filter);
	}

	public String getAllPropertyString() {
		return allPropertyString;
	}

	public void setAllPropertyString(String allPropertyString) {
		this.allPropertyString = allPropertyString;
	}

	public List<RoleVO> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleVO> roleList) {
		this.roleList = roleList;
	}

	public String getRoleName() {
		// TODO:

		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public String getRoleListName() {
		String _roleName = "";
		for (int i = 0; roleList != null && i < roleList.size(); i++) {
			if (i != roleList.size() - 1) {
				_roleName += roleList.get(i).getRoleName() + ",";
			} else {
				_roleName += roleList.get(i).getRoleName();
			}
		}
		return _roleName;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public List<FuncVO> getFuncList() {
		return funcList;
	}

	public void setFuncList(List<FuncVO> funcList) {
		this.funcList = funcList;
	}

	/**
	 * 
	 * update by jerry.x 2016年11月25日
	 * 
	 * @return
	 *
	 */
	public boolean hasDispatchWorkPm() {
		boolean hasFunc = false;
		for (FuncVO func : funcList) {
			if (func.getFuncId().equals(FuncDesc.dispatchWorkPm.toString())) {
				hasFunc = true;
				break;
			}
		}
		return hasFunc;
	}

	/**
	 * 
	 * update by jerry.x 2016年11月25日
	 * 
	 * @return
	 *
	 */
	public boolean hasDispatchCheckResult() {
		boolean hasFunc = false;
		for (FuncVO func : funcList) {
			if (func.getFuncId().equals(FuncDesc.dispatchCheckResult.toString())) {
				hasFunc = true;
				break;
			}
		}
		return hasFunc;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		if (this.uid != 0)
			sb.append("用户id" + this.uid).append(";");
		if (this.uname != null)
			sb.append("登录名:" + this.uname).append(";");
		// sb.append("upwd:" + this.upwd).append(";");
		if (this.ufullName != null)
			sb.append("中文名:" + this.ufullName).append(";");
		if (this.usex != null)
			sb.append("性别:" + BizDictFommater.Sexformater(this.usex)).append(";");
		if (this.utel1 != null)
			sb.append("电话1:" + this.utel1).append(";");
		if (this.utel2 != null)
			sb.append("电话2:" + this.utel2).append(";");
		if (this.uidCard != null)
			sb.append("身份证:" + this.uidCard).append(";");
		if (this.uaddress != null)
			sb.append("地址描述:" + this.uaddress).append(";");
		if (this.lat != null)
			sb.append("维度:" + this.lat).append(";");
		if (this.lng != null)
			sb.append("经度:" + this.lng).append(";");
		if (this.uremark != null)
			sb.append("备注:" + this.uremark).append(";");
		// sb.append("ustatus:" + this.ustatus).append(";");
		// sb.append("updUser:" + super.updUser).append(";");
		// sb.append("updDate:" + super.updDate).append(";");
		// sb.append("insUser:" + super.insUser).append(";");
		// sb.append("insDate:" + super.updDate).append(";");
		return sb.toString();
	}

	public AppDataStVO getAppDataStVO() {
		return appDataStVO;
	}

	public void setAppDataStVO(AppDataStVO appDataStVO) {
		this.appDataStVO = appDataStVO;
	}

	public List<OrderVO> getAppOrderList() {
		return appOrderList;
	}

	public void setAppOrderList(List<OrderVO> appOrderList) {
		this.appOrderList = appOrderList;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

}
