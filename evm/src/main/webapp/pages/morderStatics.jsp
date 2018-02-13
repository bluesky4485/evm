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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<!--自定义文件-->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.showLoading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BasePage.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/Util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/logout.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/morderStatics.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/faultTypeUtil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/projectUtil.js"></script>
</head>
<body id="morderStaticsBody" class="easyui-layout"  > 
	 <div data-options="region:'north', border:true" style="height: 200px;">
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
			   <div  id="exportMorder">
			        <a href="#" title="导出">
			          <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_export.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_export_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_export.png'"/>
				    </a>
				</div>
				<div  id="desc" style="float:left;;background-color:#060192;color:white;font-size:18px;width:180px;height:20px;padding-left:30px;padding-top:10px">维修订单信息统计</div>
		</div>
		<div id="para_div" style ="padding-top:8px;padding-left:10px">
		 	<form id="exportForm" method="post">
		 	  <table>
		 	     <tr>
		 	        <td>项目名称</td>
		 	        <td><input id="projectid"  class="easyui-combobox"  data-options=" panelHeight:'120',valueField:'projectId',textField:'projectName'" style="width:120px;height:28px"/></td>
		 	        <td>维修状态</td>
		 	        <td><input  id="maintainStatus"  style="width:120px" class="easyui-combobox" data-options=" panelHeight:'100',valueField:'id',textField:'text'" style="width:120px;height:28px"/></td>
		 	        <td>派修时间</td>
		 	        <td><input id="maintainStartDate" type="text" style="width:120px" class="easyui-datebox" data-options="editable:false" />
		 	            <input id="maintainEndDate" type="text" style="width:120px"class="easyui-datebox" data-options="editable:false" />
		 	        </td>
		 	        </tr>
		 	     <tr>
		 	        <td>故障类别</td>
		 	        <td><input id="faultType" type="text" style="width:120px"class="easyui-combobox" data-options=" panelHeight:'100',valueField:'faultTypeId',textField:'faultTypeName'" style="width:120px;height:28px"/></td>
		 	        <td>验收状态</td>
		 	        <td><input  id="acceptStatus"   type="text" style="width:120px" class="easyui-combobox" data-options=" panelHeight:'auto',valueField:'id',textField:'text'"/></td>
		 	        <td>完成时间</td>
		 	        <td><input id="completeStartDate" type="text" style="width:120px"class="easyui-datebox" data-options="editable:false" />
		 	            <input id="completeEndDate" type="text" style="width:120px" class="easyui-datebox" data-options="editable:false" />
		 	        </td>
		 	       </tr>
		 	     <tr>
		 	        <td>维修负责人</td>
		 	        <td><input id="maintainPmName"   style="width:120px"  type="text" class="easyui-textbox" /></td>
		 	        <td>客户名称</td>
		 	        <td><input id="cusName"   style="width:120px"   type="text" class="easyui-textbox" /></td>
		 	        <td>维修时长</td>
		 	        <td>
		 	           <input id="opt" type="text" style="width:120px"class="easyui-combobox" data-options=" panelHeight:'auto',valueField:'id',textField:'text'"/>
		 	           <input id="maintianDuration"  style="width:120px"  class="easyui-numberbox" value="0" data-options="min:0,max:10000" />
		 	            <a id="doSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a>
		 	        </td>
		 	     </tr>
		 	     
		 	  </table>
		                
		                
		                
		                         
		               
		    </form>
		</div>
	 </div>
	<div data-options="region:'center',split:false"  >
		<div style="height:100%">
			<table id="orderStDg" class="easyui-datagrid" style="width:auto;height:auto"   
			data-options="singleSelect:true,remoteSort:false,multiSort:false,collapsible:false,fit:true,pagination:false,rownumbers:true,pageSize:12,pageList:[12,30,50],checkbox:true">
			<thead>
				<tr>
				    <th data-options="field:'ck',checkbox:true,width:50"></th>
				    <th data-options="field:'projectNo',width:100,align:'center' ,sortable:true">项目编号 </th>
					<th data-options="field:'projectName',width:100,align:'center',sortable:true">项目名称</th>
					<th data-options="field:'orderNo',width:100,align:'center',sortable:true">订单编号</th>
					<th data-options="field:'morderId',width:100,align:'center' ,hidden:true">维修订单ID</th>
					<th data-options="field:'morderNo',width:100,align:'center',sortable:true">维修订单编号</th>
					<th data-options="field:'morderPmId',width:100,align:'center' ,hidden:true">运维经理ID</th>
					<th data-options="field:'pmName',width:100,align:'center' ,sortable:true">运维经理 </th>
					<th data-options="field:'maintainDate',width:100,align:'center',sortable:true">派修时间</th>
					<th data-options="field:'faultDesc',width:100,align:'center' ,sortable:true"> 故障描述</th>
					<th data-options="field:'appointmentDate',width:100,align:'center',sortable:true">预约时间</th>
					<th data-options="field:'planEndDate',width:100,align:'center' ,sortable:true">计划完成时间</th>
					<th data-options="field:'mworkerCnt',width:100,align:'center' ,sortable:true">维修人数</th>
					<th data-options="field:'maintainBeginDate',width:100,align:'center' ,sortable:true">维修开始日期</th>
					<th data-options="field:'maintainStatus',width:100,align:'center' ,hidden:true">维修状态</th>
					<th data-options="field:'maintainStatusDesc',width:100,align:'center' ,sortable:true">维修状态</th>
					<th data-options="field:'maintainSpeed',width:100,align:'center' ,sortable:true">维修进度</th>
					<th data-options="field:'faultType',width:100,align:'center' ,hidden:true">故障类别</th>
					<th data-options="field:'faultTypeDesc',width:100,align:'center' ,sortable:true">故障类别</th>
					<th data-options="field:'maintainPmId',width:100,align:'center' ,hidden:true">维修负责人ID</th>
					<th data-options="field:'maintainPmName',width:100,align:'center' ,sortable:true">维修负责人</th>
					<th data-options="field:'acceptStatus',width:100,align:'center',hidden:true">验收状态</th>
					<th data-options="field:'acceptStatusDesc',width:100,align:'center',sortable:true">验收状态</th>
					<th data-options="field:'acceptecallMan',width:100,align:'center' ,sortable:true">验收回访人</th>
					<th data-options="field:'callbackDate',width:100,align:'center',sortable:true">验收回访时间</th>
					<th data-options="field:'solution',width:100,align:'center' ,sortable:true"> 解决方案</th>
					<th data-options="field:'maintainResultdesc',width:100,align:'center' ,sortable:true">维修结果描述</th>
					<th data-options="field:'maintainRemark',width:100,align:'center',sortable:true">备注</th>
					
					<!--  -->
					<th data-options="field:'userScore',width:100,align:'center',sortable:true"> 用户评分</th>
					<th data-options="field:'userProposal',width:100,align:'center' ,sortable:true">用户评价</th>
					<th data-options="field:'lastUpdDate',width:100,align:'center' ,sortable:true">最后一次进度更新时间</th>
					
					
					<th data-options="field:'insUser',width:100,align:'center',sortable:true">创建人</th>
					<th data-options="field:'insDate',width:100,align:'center'  ,sortable:true ">创建日期</th>
					<th data-options="field:'updUser',width:100,align:'center',sortable:true">修改人</th>
					<th data-options="field:'updDate',width:100,align:'center',sortable:true">修改日期</th>
				</tr>
			</thead>
		   </table>
	   </div>
	</div>
</body>
</html>
