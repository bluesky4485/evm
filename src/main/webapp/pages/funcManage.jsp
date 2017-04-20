<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"	src="<%=request.getContextPath()%>/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/icon.css"/>
<!--自定义文件-->
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/css/style.css"/>

<script type="text/javascript"	src="<%=request.getContextPath()%>/js/common/jquery.showLoading.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/common/BasePage.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/common/Util.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/funcManage.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/funcServiceRel.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/logout.js"></script> 
</head>
<body id="funcManageBody" class="easyui-layout">
	<div data-options="region:'north', border:true" style="height: 100px;">
	      <table id="top" style="width: 100%; height: 50px; border: #F3F3F3; background-color: #FCFCFC">
				<tr style="padding-top: 5px">
					<td align="center"
						style="widht: 100px; border-right: #EEEEEE 1px solid;">您有 <span
						class="span-red" id="newMorder" style="color:red">0 </span> 新报修未处理
					</td>
					<td><input class="easyui-searchbox"   id="funcNamePara"
						style="background-color: #BABABA; width: 500px; height: 35px; padding-left: 20px" />
					</td>
					<td align="right" style="width: 250px; border-left: #EEEEEE 1px solid;">
					 当前用户：<%=session.getAttribute("LOGIN_NAME") %>
					       <a onclick="signout()"id="sysLogoutBtn"href="#" class="easyui-linkbutton c5" style="width:80px">退出</a>
				</td>
				</tr>
		     </table>
		<div id="toolbar" class="toolbar">
			<div id="desc"
				style="float: left;; background-color: #060192; color: white; font-size: 18px; width: 180px; height: 20px; padding-left: 30px; padding-top: 10px">权限管理</div>
		</div>
	</div>

	<div data-options="region:'center',split:false">

		<div style="height: 100%">
			<table id="funcDg" class="easyui-datagrid"
				style="width: auto; height: auto"
				data-options="autoRowHeight:true,singleSelect:true,collapsible:false,fit:true,pagination:true,rownumbers:true,pageSize:20,pageList:[20,20,50],checkbox:true">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true,width:50"></th>
						<th data-options="field:'funcId',width:100,align:'center'">权限编码</th>
						<th data-options="field:'funcName',width:250,align:'left'">权限名称</th>
						<th data-options="field:'funcRemark',width:100,align:'left'">备注</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
<div id="func_serviceId_window" title="服务列表" class="easyui-window"
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
		<div class="easyui-layout" data-options="fit:true">
           
            <div data-options="region:'center'"  >
                 <table id="serviceDg" class="easyui-datagrid"
						style="width: auto; height: auto"
						data-options="autoRowHeight:true,singleSelect:true,collapsible:false,fit:true,pagination:false,rownumbers:true,pageSize:20,pageList:[20,20,50],checkbox:true">
						<thead>
							<tr>
								<th data-options="field:'ck',checkbox:true,width:50"></th>
								<th data-options="field:'serviceId',width:100,align:'center'">服务编号</th>
								<th data-options="field:'serviceDesc',width:250,align:'left'">服务描述</th>
							</tr>
						</thead>
					</table>
            </div>
            <div data-options="region:'south',border:false" style="text-align:right;padding:5px 5px 5px 0;">
               <!--  <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:alert('ok')" style="width:80px">确定</a> -->
                <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:$('#func_serviceId_window').window('close');" style="width:80px">关闭</a>
            </div>
        </div>
	</div>
</html>
