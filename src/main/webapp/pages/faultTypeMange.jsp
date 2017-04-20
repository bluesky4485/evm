<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"%>
<%@ page isELIgnored="false" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="./../jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript" src="./../jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="./../jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="./../jquery-easyui-1.5/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="./../jquery-easyui-1.5/themes/icon.css"/>

<!--自定义文件-->
<link rel="stylesheet" type="text/css" href="./../css/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.showLoading.js"></script>
<script type="text/javascript" src="./../js/common/BasePage.js"></script>
<script type="text/javascript" src="./../js/common/Util.js"></script>
<script type="text/javascript" src="./../js/util/faultTypeUtil.js"></script>
<script type="text/javascript" src="./../js/faultTypeMange.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/logout.js"></script> 
</head>
<body id="dfManageBody"  class="easyui-layout"  > 
	 <div data-options="region:'north', border:true" style="height: 101px;">
	      <table id="top" style="width: 100%; height: 50px; border: #F3F3F3; background-color: #FCFCFC">
				<tr style="padding-top: 5px">
					<td align="center"
						style="widht: 100px; border-right: #EEEEEE 1px solid;">您有 <span
						class="span-red" id="newMorder" style="color:red">0 </span> 新报修未处理
					</td>
					<td><input class="easyui-searchbox"   id="faultTypeNamePara"
						style="background-color: #BABABA; width: 500px; height: 35px; padding-left: 20px" />
					</td>
					<td align="right" style="width: 250px; border-left: #EEEEEE 1px solid;">
					 当前用户：<%=session.getAttribute("LOGIN_NAME") %>
					       <a onclick="signout()"id="sysLogoutBtn"href="#" class="easyui-linkbutton c5" style="width:80px">退出</a>
				</td>
				</tr>
		     </table>
			 <div id="toolbar" class="toolbar">
			    <!-- 删除-->
			    <div  id="delFaultTypeBtn">
					    <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_del.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_del_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_del.png'"/> 
				</div>
				<!-- 新增-->
			    <div id="addFaultTypeBtn">
				     <a id="addCustomHref" href="#">
				        <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_add.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_add_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_add.png'"/>
					 </a>
				</div>
				<!-- 修改-->
			   <div  id="updFaultTypeBtn">
					 <a id="updCustomHref" href="#">
					       <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_edit.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_edit_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_edit.png'"/>
					 </a>  
				</div>
				<div  id="desc" style="float:left;;background-color:#060192;color:white;font-size:18px;width:180px;height:20px;padding-left:30px;padding-top:10px">故障类型表</div>
			 </div>
	 </div>
	
	<div data-options="region:'center',split:false"  >
		<div style="height:100%">
			<table class="easyui-datagrid" style="width:auto;height:auto"  id="faultTypeTable" 
			data-options="singleSelect:true,collapsible:false,fit:true,pagination:true,rownumbers:true,pageSize:12,pageList:[12,20,50],checkbox:true,loadFilter:BasePage.pagerFilter">
			<thead>
				<tr>
				    <th data-options="field:'ck',checkbox:true,width:50"></th>		
				    <th data-options="field:'faultTypeId',width:280,align:'center'">故障类型编号</th>
					<th data-options="field:'faultTypeName',width:280,align:'center'">故障类型名称</th>
				</tr>
			</thead>
		   </table>
	   </div>
	</div>
		<div id="add_falutType_window" title="增加故障类型" class="easyui-window"
		data-options="		
			modal : true,
			draggable : true,
			resizable : false,
			closed : true,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			shadow : true,
			striped : true"
		style="width: 300px; height: 135px;">
		<table style="margin: 5px 0 0 10px">
			<!-- <tr style="height: 35px">
				<td>故障类型编号:</td>
				<td><input style="width: 100;height:35px" id="add_faultTypeNo" type="text"
					class="easyui-textbox" /></td>
			</tr> -->
			<tr>
				<td>故障类型名称:</td>
				<td><input style="width: 100;height:35px" id="add_faultTypeName" type="text"
					class="easyui-textbox"  /></td>
			</tr>
		 
			<tr>
				<td colspan="2">
					<div style="float: right; margin: 10px 20px 0 0">
						<a id="add_faultType_window_OK" href="javascript:void(0)"
							class="easyui-linkbutton" style="width: 100px; height: 30px;"
							onclick="$('#dlg').dialog('open')">确定</a> <a
							id="add_faultType_window_cancel" href="javascript:void(0)"
							class="easyui-linkbutton" style="width: 100px; height: 30px"
							onclick="$('#dlg').dialog('close')">取消</a>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div id="upd_falutType_window" title="修改故障类型" class="easyui-window"
		data-options="		
			modal : true,
			draggable : true,
			resizable : false,
			closed : true,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			shadow : true,
			striped : true"
		style="width: 300px; height: 135px;">
		<table style="margin: 5px 0 0 10px">
			<!-- <tr style="height: 35px">
				<td>故障类型编号:</td>
				<td><input style="width: 100;height:35px" id="upd_faultTypeNo" type="text" disabled="disabled"
					class="easyui-textbox" /></td>
			</tr> -->
			<tr>
				<td>故障类型名称:</td>
				<td><input style="width: 100;height:35px" id="upd_faultTypeName" type="text"
					class="easyui-textbox"  onkeypress="return   /[\w\u4e00-\u9fa5]/.test(String.fromCharCode(window.event.keyCode))"   
      onpaste="return   !/[^\w\u4e00-\u9fa5]/g.test(window.clipboardData.getData('Text'))"   
      ondragenter="return   false"/></td>
			</tr>
			
			<tr>
				<td colspan="2">
					<div style="float: right; margin: 10px 20px 0 0">
						<a id="upd_faultType_window_OK" href="javascript:void(0)"
							class="easyui-linkbutton" style="width: 100px; height: 30px;"
							onclick="$('#dlg').dialog('open')">确定</a> <a
							id="upd_faultType_window_cancel" href="javascript:void(0)"
							class="easyui-linkbutton" style="width: 100px; height: 30px"
							onclick="$('#dlg').dialog('close')">取消</a>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
