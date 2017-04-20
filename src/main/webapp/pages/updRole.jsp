<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/gray/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.showLoading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BasePage.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BaseMap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/Util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jQuery.md5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/updRole.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/logout.js"></script> 
</head>
<body id="addRoleBody" class="easyui-layout">
	<div data-options="region:'north', border:true" style="height: 225px;">
		<table id="top" style="width: 100%; height: 50px; border: #F3F3F3; background-color: #FCFCFC">
			<tr style="padding-top: 5px">
				<td align="center"
					style="widht: 100px; border-right: #EEEEEE 1px solid;">您有 <span
					class="span-red" id="newMorder" style="color:red">0 </span> 新报修未处理
				</td>
				<td><input class="easyui-searchbox" disabled="disabled" 
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
		      <div  id="goHist">
			          <a href="#" >
	                      <img onclick="javascript:history.back();"  src="<%=request.getContextPath()%>/images/web_button_back.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_back_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_back.png'"/>
	                 </a>
		      </div>
			<div id="saveRoleBtn">
				<img onclick=""
					src="<%=request.getContextPath()%>/images/web_button_tj.png"
					onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_tj_on.png'"
					onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_tj.png'" />
			</div>
			<div id="desc"
				style="float: left; background-color: #060192; color: white; font-size: 18px; width: 180px; height: 20px; padding-left: 30px; padding-top: 10px">添加角色</div>
		</div>
		<div id="hidden_div">
			 <input type="hidden"  id="hidden_updDate"     />
			 <input type="hidden"  id="roleId"     value="<c:out value='${updObj.roleId}'/>"/>
		</div>
		<div style="padding-left: 5px; padding-top: 10px">
			<table class="input_table" style="width: 100%">
				<tr>
					<td>角色名称<span class="redStar">*</span></td>
					<td><input id="roleName" type="text" class="easyui-textbox" disabled="disabled" 
						style="width: 100%" /></td>
				</tr>
				<tr style="height: 70px">
					<td>角色备注</td>
					<td><input id="roleRemark" type="text"
						style="width: 100%; height: 100%" class="easyui-textbox"
						multiline="true" /></td>
				</tr>
			</table>
		</div>
	</div>
	<div data-options="region:'center', border: true">
		<table id="funcDg" class="easyui-datagrid"
			style="width: auto; height: auto"
			data-options="autoRowHeight:true,singleSelect:false,collapsible:false,fit:true,pagination:false,rownumbers:true,pageSize:12,pageList:[12,20,50],checkbox:true">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true,width:50"></th>
					<th data-options="field:'funcId',width:100,align:'center'">权限编码</th>
					<th data-options="field:'funcName',width:300,align:'left'">权限名称</th>
					<th data-options="field:'funcRemark',width:300,align:'left'">备注</th>
				</tr>
			</thead>
		</table>
	</div>

	<form id="returnManageForm"
		action="<%=request.getContextPath()%>/roleController.do?method=returnManagePage"
		id="isform" method="post"></form>
</body>
</html>
