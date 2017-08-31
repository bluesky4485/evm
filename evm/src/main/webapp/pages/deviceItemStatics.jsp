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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/deviceItemStatics.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/faultTypeUtil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/projectUtil.js"></script>
</head>
<body id="diStaticsBody" class="easyui-layout"  > 
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
			   <div  id="exportMorder">
					                <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_export.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_export_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_export.png'"/>
		
				</div>
				<div  id="desc" style="float:left;;background-color:#060192;color:white;font-size:18px;width:180px;height:20px;padding-left:30px;padding-top:10px">设备信息统计</div>
		</div>
		<div id="para_div" style ="padding-top:8px;padding-left:10px">
		 	<form id="exportForm" method="post">
		         		项目名称:<input id="projectid" class="easyui-combobox"  data-options=" panelHeight:'120',valueField:'projectId',textField:'projectName'" style="width:120px;height:28px"/>
		                设备分类:<input  id="deviceTypeClassType"   class="easyui-combobox" data-options=" panelHeight:'100',valueField:'id',textField:'text'" style="width:120px;height:28px"/>
						设备类型:<input  id="deviceTypeName"   class="easyui-textbox"  style="width:120px;height:28px"/>
		                <a id="doSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a>
		    </form>
		</div>
	 </div>
	<div data-options="region:'center',split:false"  >
		<div style="height:100%">
			<table id="deviceItemStDg" class="easyui-datagrid" style="width:auto;height:auto"   
			data-options="singleSelect:true,remoteSort:false,multiSort:false,collapsible:false,fit:true,pagination:false,rownumbers:true,pageSize:12,pageList:[12,30,50],checkbox:true">
			<thead>
				<tr>
				    <th data-options="field:'ck',checkbox:true,width:50"></th>
				    <th data-options="field:'projectNo',width:100,align:'center' ,sortable:true">项目编号</th>
					<th data-options="field:'projectName',width:100,align:'center',sortable:true">项目名称</th>
					<th data-options="field:'orderNo',width:100,align:'center',sortable:true">订单编号</th>
					<th data-options="field:'deviceTypeClassType',width:100,align:'center',hidden:true">设备分类</th>
					<th data-options="field:'deviceTypeClassTypeDesc',width:100,align:'center',sortable:true">设备分类</th>
					<th data-options="field:'deviceTypeId',width:100,align:'center',hidden:true">设备类型</th>
					<th data-options="field:'deviceTypeName',width:100,align:'center',sortable:true">设备类型</th>
					<th data-options="field:'deviceItemUid',width:100,align:'center',sortable:true">设备标识</th>
					<th data-options="field:'deviceItemName',width:100,align:'center'">设备名称</th>	
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
