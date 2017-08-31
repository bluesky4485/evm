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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css"/>
<!--自定义文件-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.showLoading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BasePage.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/Util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/logManage.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/logout.js"></script> 
</head>
<body id="logManageBody" class="easyui-layout"  > 
	 <div data-options="region:'north', border:true" style="height: 147px;">
	        <table id="top" style="width: 100%; height: 50px; border: #F3F3F3; background-color: #FCFCFC">
				<tr style="padding-top: 5px">
					<td align="center"
						style="widht: 100px; border-right: #EEEEEE 1px solid;">您有 <span
						class="span-red" id="newMorder" style="color:red">0 </span> 新报修未处理
					</td>
					<td><input class="easyui-searchbox"   id="logNamePara"
						style="background-color: #BABABA; width: 500px; height: 35px; padding-left: 20px" />
					</td>
					<td align="right" style="width: 250px; border-left: #EEEEEE 1px solid;">
					 当前用户：<%=session.getAttribute("LOGIN_NAME") %>
					       <a onclick="signout()"id="sysLogoutBtn"href="#" class="easyui-linkbutton c5" style="width:80px">退出</a>
				</td>
				</tr>
		     </table>
			 <div id="toolbar" class="toolbar">
				<div  id="desc" style="float:left;;background-color:#060192;color:white;font-size:18px;width:180px;height:20px;padding-left:30px;padding-top:10px">日志管理</div>
			 </div>
			 <div style="padding: 5px 0px 5px 20px">
	          <span>开始时间</span> <input style="height:35px" id="beginDate" type="text" class="easyui-datebox" data-options="editable:false" />
	          <span>结束时间</span> <input style="height:35px" id="endDate" type="text" class="easyui-datebox" data-options="editable:false" />
		      <a id="doSearchLogBtn" style="height:35px;width:100px" href="#" class="easyui-linkbutton"   >确定</a> 
		    </div>
	 </div>
	
	<div data-options="region:'center',split:false"  >
	    
		<div style="height:100%">
			
			<table id="logDg" class="easyui-datagrid" style="width:auto;height:auto"  
			data-options="autoRowHeight:true,singleSelect:true,collapsible:false,fit:true,pagination:true,rownumbers:true,pageSize:12,pageList:[12,20,50],checkbox:true">
			<thead>
				<tr>
				    <th data-options="field:'ck',checkbox:true,width:50"></th>
					<th data-options="field:'insDate',width:150,align:'center'">操作时间 </th>
					<th data-options="field:'logUserName',width:150,align:'left'">操作人员</th>
					<th data-options="field:'logContent',width:250,align:'left'">操作内容</th>
				</tr>
			</thead>
		   </table>
	   </div>
	</div>
</body>
</html>
