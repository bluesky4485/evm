<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/icon.css">
<!--自定义文件-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.showLoading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BasePage.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/Util.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/logout.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/projectStatics.js"></script>
</head>
<body id="projectStaticsBody" class="easyui-layout"  > 
	 <div data-options="region:'north', border:true" style="height: 140px;">
	 <table id="top" style="width: 100%; height: 50px; border: #F3F3F3; background-color: #FCFCFC">
			<tr style="padding-top: 5px">
				<td align="center"
					style="widht: 100px; border-right: #EEEEEE 1px solid;">您有 <span
					class="span-red" id="newMorder" style="color:red">0 </span> 新报修未处理
				</td>
				<td><input class="easyui-searchbox"   id="projectNamePara" disabled="disabled" 
					style="background-color: #BABABA; width: 500px; height: 35px; padding-left: 20px" />
				</td>
				<td align="right" style="width: 250px; border-left: #EEEEEE 1px solid;">
					 当前用户：<%=session.getAttribute("LOGIN_NAME") %>
					       <a onclick="signout()"id="sysLogoutBtn"href="#" class="easyui-linkbutton c5" style="width:80px">退出</a>
				   </td>
			</tr>
		</table>
	    <div id="toolbar" class="toolbar">
				<!-- 导出-->
			   <div  id="exportProject">
			           <a href="#" title="导出">
					    <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_export.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_export_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_export.png'"/>
				       </a>
				</div>
				<div  id="desc" style="float:left;;background-color:#060192;color:white;font-size:18px;width:180px;height:20px;padding-left:30px;padding-top:10px">项目信息统计</div>
		</div>
		<div id="para_div" style ="padding-top:8px;padding-left:10px">
		<form id="exportForm" method="post">
		       <input id="projectSearch"class="easyui-searchbox" data-options="prompt:'Please Input Value',menu:'#seachMenu'" style="width:300px;height:30px">
		        <div id="seachMenu" style="height:100px">
				        <div data-options="name:'PROJECT_NAME',iconCls:'icon-search'">项目名称</div>
				        <div data-options="name:'PROJECT_NO'">项目编号</div>
				        <div data-options="name:'CUS_NAME'">客户联系人</div>
				        <div data-options="name:'CM_NAME'">客户经理</div>
				        <div data-options="name:'PM_NAME'">项目经理</div>
				        <div data-options="name:'PROJECT_TYPE_DESC'">项目类别</div>
				        <div data-options="name:'BUILD_TYPE_DESC'">建设方式</div>
				        <div data-options="name:'PAY_TYPE'">缴费类型</div>
				 </div>
				 </form>
		</div>
	 </div>
	<div data-options="region:'center',split:false"  >
		<div style="height:100%">
			<table id="projectStDg" class="easyui-datagrid" style="width:auto;height:auto"   
			data-options="singleSelect:true,remoteSort:false,multiSort:false,collapsible:false,fit:true,pagination:false,rownumbers:true,pageSize:12,pageList:[12,30,50],checkbox:true">
			<thead>
				<tr>
				    <th data-options="field:'ck',checkbox:true,width:50"></th>
				    <th data-options="field:'projectId',width:100,align:'center',hidden:true">项目ID</th>
					<th data-options="field:'projectName',width:100,align:'center',sortable:true">项目名称</th>
					<th data-options="field:'projectNo',width:100,align:'center',sortable:true">项目编号 </th>
					<th data-options="field:'cusId',width:100,align:'center',hidden:true">客户联系人ID</th>
					<th data-options="field:'cusName',width:100,align:'center',sortable:true">客户联系人</th>
					<th data-options="field:'cmId',width:100,align:'center',hidden:true">客户经理ID</th>
					<th data-options="field:'cmName',width:100,align:'center',sortable:true">客户经理</th>
					<th data-options="field:'pmName',width:100,align:'center',sortable:true">项目经理 </th>
					<th data-options="field:'pmId',width:100,align:'center',hidden:true">项目经理ID</th>
					<th data-options="field:'projectTypeDesc',width:100,align:'center',sortable:true">项目类别</th>
					<th data-options="field:'projectType',width:100,align:'center',hidden:true">项目类别ID</th>
					<th data-options="field:'contractId',width:100,align:'center',sortable:true">合同编号</th>
					<th data-options="field:'contractSum',width:100,align:'center',sortable:true">合同金额</th>
					<th data-options="field:'signDate',width:100,align:'center',sortable:true">签订日期</th>
					<th data-options="field:'payType',width:100,align:'center',sortable:true">缴费类型</th>
					<th data-options="field:'workEndDate',width:100,align:'center',sortable:true">工期截止日期</th>
					<th data-options="field:'workStartDate',width:100,align:'center',sortable:true">开工日期</th>
					<th data-options="field:'yearPay',width:100,align:'center',sortable:true">年服务费</th>
					<th data-options="field:'serviceEndDate',width:100,align:'center',sortable:true">服务截止日期</th>
					<th data-options="field:'payDate',width:100,align:'center',sortable:true">缴费日期</th>
					<th data-options="field:'buildTypeDesc',width:100,align:'center',sortable:true">建设方式</th>
					<th data-options="field:'buildType',width:100,align:'center',hidden:true">建设方式ID</th>
					<th data-options="field:'joinDate',width:100,align:'center',sortable:true">入网日期</th>
					<th data-options="field:'stopDate',width:100,align:'center',sortable:true">停机日期</th>
					<th data-options="field:'projectRemark',width:100,align:'center',sortable:true">备注</th>
					<th data-options="field:'insUser',width:100,align:'center',sortable:true">创建人</th>
					<th data-options="field:'insDate',width:100,align:'center',sortable:true  ">创建日期</th>
					<th data-options="field:'updUser',width:100,align:'center',sortable:true">修改人</th>
					<th data-options="field:'updDate',width:100,align:'center',sortable:true">修改日期</th>
				</tr>
			</thead>
		   </table>
	   </div>
	</div>
</body>
</html>
