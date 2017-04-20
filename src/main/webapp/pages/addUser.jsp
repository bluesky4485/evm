<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"	src="<%=request.getContextPath()%>/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/icon.css">
 
<script src="http://webapi.amap.com/maps?v=1.3&key=e4a4a2fd139f9535810830feefddf38f&&plugin=AMap.OverView,AMap.ToolBar"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">

<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.showLoading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BasePage.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BaseMap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/Util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/addUser.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jQuery.md5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/userUtil.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/logout.js"></script>
</head>
<body id="addUserBody" class="easyui-layout">
	<div data-options="region:'north', border:true" style="height: 102px;">
	    <table id="top" style="width: 100%; height: 50px; border: #F3F3F3; background-color: #FCFCFC">
				<tr style="padding-top: 5px">
					<td align="center"
						style="widht: 100px; border-right: #EEEEEE 1px solid;">您有 <span
						class="span-red" id="newMorder" style="color:red">0 </span> 新报修未处理
					</td>
					<td><input class="easyui-searchbox"    disabled="disabled" 
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
			<div id="saveUser">
				<a id="saveUserHref" href="#">
				      <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_tj.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_tj_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_tj.png'"/>
				</a>
			</div>
           <div  id="desc" style="float:left;;background-color:#060192;color:white;font-size:18px;width:180px;height:20px;padding-left:30px;padding-top:10px">添加用户</div>
		</div>
		<div id="hiden_div">
			<input type="hidden" id="lat" name="lat"  value="0" /><!--lat -->
			 <input type="hidden" id="lng" name="lng"  value="0" /><!--lng -->
		</div>
	</div>
	<div data-options="region:'center', border: true">
	  <div style="padding:10px">
		<table class="input_table" >
			<tr>
				<td>用户名<span class="redStar">*</span></td>
				<td><input  id="uname"  name="uname"   type="text" class="easyui-textbox" /></td>
				<td>用户性别</td>
				<td><input id="usex"   name="usex"  type="text"   class="easyui-combobox"  data-options=" panelHeight:'auto',valueField:'id',textField:'text',"/></td>
			</tr>
			<tr>
				<td>密码 <span class="redStar">*</span></td>
				<td><input id ="upass" type="text"    name="upass"  class="easyui-passwordbox"  value=""  /></td>
				<td>用户电话1</td>				
				<td><input type="text" id="utel1"  name="utel1"  class="easyui-textbox" /></td>
			</tr>
			<tr>
				<td>重复密码 <span class="redStar">*</span></td>
				<td><input id ="rupass" type="text"    name="rupass"  class="easyui-passwordbox" value=""   /></td>				
				<td>用户电话2</td>
				<td><input  id="utel2" name="utel2"  type="text" class="easyui-textbox" /></td>
			</tr>
			<tr>
				<td>用户姓名<span class="redStar">*</span></td>
				<td><input  id="ufullName" name="ufullName"  type="text" class="easyui-textbox" /></td>
				<td>用户角色<span class="redStar">*</span></td>
				<td><input  id="roleName" name="roleName"  type="text" class="easyui-combobox" data-options=" panelHeight:'120',valueField:'roleId',textField:'roleName',multiple:true"/></td>
			</tr>
			<tr>
				<td>用户身份证号</td>
				<td colspan='3' ><input id ="uidCard" name="uidCard"  type="text" class="easyui-textbox" /></td>
			</tr>
			<tr>
				<td >用户地址</td>
				<td colspan='3' ><input  id="uaddress" name="uaddress"  type="text" class="easyui-textbox"  style="width: 100%; height: 80px"/> <a id="addCusMap" href="#" class="easyui-linkbutton">地图</a></td>
			</tr>
			<tr>
				<td >备注</td>
				<td colspan='3' ><input  id="uremark" name="uremake"  type="text" class="easyui-textbox"  style="width: 100%; height: 80px" /></td>
			</tr>
		</table>
	  </div>
	</div>
	<div id="map_window" title="地理位置" class="easyui-window"
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
		style="width: 500px; height: 500px;">
		<div   id="mapContainer"style="width: 100%; height: 100%"></div>
	</div>
	<form id="retrnManageForm"action="<%=request.getContextPath()%>/userController.do?method=returnManagePage"  id="isform" method="post">
	
	</form>
</body>
</html>
