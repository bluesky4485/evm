package org.evm.biz.custom.entity;

import java.math.BigDecimal;
import java.util.List;

import org.evm.core.entity.AbstractEntity;
import org.evm.biz.dict.BizDictFommater;
import org.evm.biz.project.entity.ProjectVO;

public class CustomVO extends AbstractEntity {
	/**
	 * 
	 * jerry.x 2016年10月10日
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 客户编号
	 */
	private long cno;
	/**
	 * 客户姓名
	 */
	private String cname;
	/**
	 * 客户电话1
	 */
	private String ctel1;
	/**
	 * 客户电话2
	 */
	private String ctel2;
	/**
	 * 性别
	 */
	private String csex;
	/**
	 * 客户身份证号
	 */
	private String cidCard;
	/**
	 * 用户类型
	 */
	private String ctype;
	/**
	 * 客户地址
	 */
	private String caddress;

	private BigDecimal lng;
	private BigDecimal lat;
	/**
	 * 所属派出所
	 */
	private String policestation;
	/**
	 * 所属分局
	 */
	private String substation;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 订单状态，是否删除
	 */
	private String cstatus;
	/**
	 * 关联的项目名
	 */
	private String projects;
	/**
	 * 查询状态
	 */
	private String queryStat;
	/**
	 * 停止服务的天数
	 */
	private int stopServiceDays;

	private List<ProjectVO> listProjects;

	public String getProjects() {
		StringBuilder sb = new StringBuilder();
		if (listProjects != null) {
			for (ProjectVO vo : listProjects) {
				sb.append(vo.getProjectName()).append(",");
			}
		}
		projects = sb.toString();
		if (projects.length() > 0)
			projects = projects.substring(0, projects.length() - 1);
		return projects;
	}

	public void setProjects(String projects) {
		this.projects = projects;
	}

	public long getCno() {
		return cno;
	}

	public void setCno(long cno) {
		this.cno = cno;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCtel1() {
		return ctel1;
	}

	public void setCtel1(String ctel1) {
		this.ctel1 = ctel1;
	}

	public String getCtel2() {
		return ctel2;
	}

	public void setCtel2(String ctel2) {
		this.ctel2 = ctel2;
	}

	public String getCsex() {
		return csex;
	}

	public void setCsex(String csex) {
		this.csex = csex;
	}

	public String getCidCard() {
		return cidCard;
	}

	public void setCidCard(String cidCard) {
		this.cidCard = cidCard;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getCaddress() {
		return caddress;
	}

	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}

	public String getPolicestation() {
		return policestation;
	}

	public void setPolicestation(String policestation) {
		this.policestation = policestation;
	}

	public String getSubstation() {
		return substation;
	}

	public void setSubstation(String substation) {
		this.substation = substation;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCstatus() {
		return cstatus;
	}

	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}

	public List<ProjectVO> getListProjects() {
		return listProjects;
	}

	public void setListProjects(List<ProjectVO> listProjects) {
		this.listProjects = listProjects;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public int getStopServiceDays() {
		return stopServiceDays;
	}

	public void setStopServiceDays(int stopServiceDays) {
		this.stopServiceDays = stopServiceDays;
	}

	public String getQueryStat() {
		return queryStat;
	}

	public void setQueryStat(String queryStat) {
		this.queryStat = queryStat;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		if (this.cno != 0)
			sb.append("客户编号:" + this.cno).append(";");
		if (this.cname != null)
			sb.append("客户姓名:" + this.cname).append(";");
		if (this.ctel1 != null)
			sb.append("客户电话1:" + this.ctel1).append(";");
		if (this.ctel2 != null)
			sb.append("客户电话2:" + this.ctel2).append(";");
		if (this.csex != null)
			sb.append("性别:" + BizDictFommater.Sexformater(this.csex)).append(";");
		if (this.cidCard != null)
			sb.append("客户身份证号:" + this.cidCard).append(";");
		if (this.ctype != null)
			sb.append("用户类型:" + BizDictFommater.UserTypeformater(this.ctype)).append(";");
		if (this.caddress != null)
			sb.append("客户地址:" + this.caddress).append(";");
		if (this.lng != null)
			sb.append("经度:" + this.lng).append(";");
		if (this.lat != null)
			sb.append("维度:" + this.lat).append(";");
		if (this.policestation != null)
			sb.append("所属派出所:" + this.policestation).append(";");
		if (this.substation != null)
			sb.append("所属分局:" + this.substation).append(";");
		if (this.remark != null)
			sb.append("备注:" + this.remark).append(";");
		// if (this.cstatus != null)
		// sb.append("删除标识:" + this.cstatus).append(";");
		// if (this.updUser != null)
		// sb.append("修改人ID:" + super.updUser).append(";");
		// if (this.insUser != null)
		// sb.append("创建人ID:" + super.insUser).append(";");
		return sb.toString();
	}
}
