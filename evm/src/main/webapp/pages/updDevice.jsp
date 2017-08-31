<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"%>
<%@ page isELIgnored="false" %> 
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/updDevice.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/logout.js"></script> 
</head>
<body id="updDeviceBody"class="easyui-layout"  > 
	 <div data-options="region:'north', border:true" style="height: 150px;">
	        <table id="top" style="width: 100%; height: 50px; border: #F3F3F3; background-color: #FCFCFC">
				<tr style="padding-top: 5px">
					<td align="center"
						style="widht: 100px; border-right: #EEEEEE 1px solid;">您有 <span
						class="span-red" id="newMorder" style="color:red">0 </span> 新报修未处理
					</td>
					<td><input class="easyui-searchbox"    id="roleNamePara"
						style="background-color: #BABABA; width: 500px; height: 35px; padding-left: 20px" />
					</td>
					<td align="right" style="width: 250px; border-left: #EEEEEE 1px solid;">
					 当前用户：<%=session.getAttribute("LOGIN_NAME") %>
					       <a onclick="signout()"id="sysLogoutBtn"href="#" class="easyui-linkbutton c5" style="width:80px">退出</a>
				</td>
				</tr>
		 </table>
			 <div id="toolbar" class="toolbar">
			 <!-- 返回 -->
		      <div id="goHist">
			          <a href="#" >
	                      <img onclick="javascript:history.back();"  src="<%=request.getContextPath()%>/images/web_button_back.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_back_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_back.png'"/>
	                 </a>
		      </div>
				<!-- 修改-->
			   <div  id="updDeviceBtn">
					 <a id="updCustomHref" href="#">
					    <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_tj.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_tj_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_tj.png'"/>
					 </a>  
				</div>
				<div  id="desc" style="float:left;;background-color:#060192;color:white;font-size:18px;width:180px;height:20px;padding-left:30px;padding-top:10px">修改设备信息</div>
			 </div>
			 <div id="hidden_div">
			         <input type="hidden" id="deviceId"  name="deviceId" value="<c:out value='${updObj.deviceId}'/>"/>
			         <input type="hidden"  id="hidden_updDate" name="updDate"   type="hidden"  />
			 </div>
		<div style="padding-left:20px">
			<table>
				<tr>
					<td style="width: 300px">设备类型名称<input disabled="disabled" style="width: 120; height: 35px"
						id="deviceName" type="text" class="easyui-textbox" /></td>
					<td>设备分类<input style="width: 120; height: 35px"
						id="deviceType" type="text" class="easyui-combobox" data-options=" panelHeight:'auto',valueField:'id',textField:'text'"/></td>
				</tr>
			</table>
		</div>
	 </div>
	
	<div data-options="region:'center',split:false"  >
		<div style="height:100%">
			<table class="easyui-datagrid" style="width:auto;height:auto"  id="propertyTable" 
			data-options="singleSelect:true,collapsible:false,fit:true,pagination:false,toolbar:toolbar,rownumbers:true,pageSize:12,pageList:[12,20,50],checkbox:true,loadFilter:BasePage.pagerFilter">
			<thead>
				<tr>
				    <th data-options="field:'ck',checkbox:true,width:50"></th>
				    <th data-options="field:'propertyName',width:280,align:'center'">属性名称</th>
					<!-- <th data-options="field:'propertyValue',width:280,align:'center'">属性内容</th> -->
				</tr>
			</thead>
		   </table>
	   </div>
	</div>
		<div id="property_window" title="增加故障类型" class="easyui-window"
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
		style="width: 300px; height: 180px;">
		<table style="margin: 5px 0 0 10px">
			<tr style="height: 35px">
				<td>属性名称:</td>
				<td><input style="width: 100;height:35px" id="add_PropertyName" type="text"
					class="easyui-textbox" /></td>
			</tr>
			<!-- <tr>
				<td>属性内容:</td>
				<td><input style="width: 100;height:35px" id="add_PropertyValue" type="text"
					class="easyui-textbox" /></td>
			</tr> -->
			<tr>
				<td colspan="2">
					<div style="float: right; margin: 10px 20px 0 0">
						<a id="add_property_window_OK" href="javascript:void(0)"
							class="easyui-linkbutton" style="width: 100px; height: 30px;">确定</a> <a
							id="add_property_window_cancel" href="javascript:void(0)"
							class="easyui-linkbutton" style="width: 100px; height: 30px">取消</a>
					</div>
				</td>
			</tr>
		</table>
	</div> 
	<form id="retrnManageForm"action="<%=request.getContextPath()%>/deviceController.do?method=returnManagePage"  id="isform" method="post">
	
	</form>
</body>
</html>
