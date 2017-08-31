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
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/icon.css"/>

<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<script	src="http://webapi.amap.com/maps?v=1.3&key=e4a4a2fd139f9535810830feefddf38f&&plugin=AMap.OverView,AMap.ToolBar"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/index.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/common/jquery.showLoading.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/common/BasePage.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/common/Util.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/common/BaseMap.js"></script>
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css" />
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/common/logout.js"></script>
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/css/style.css">
<link rel="stylesheet"	href="http://cache.amap.com/lbs/static/main.css?v=1.0" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/projectUtil.js"></script>
<style type="text/css">
.info_ul li {
	float: left;
	width: 110px;
	height: 25px;
	line-height: 30px;
	text-align: center;
	background-color: #fff;
	border: 1px #bbb solid;
	overflow: hidden;
	zoom: 1;
	list-style-type: none;
}

.info_ul li.fli {
	background-color: #ccc;
	/* 	color: red; */
	font-weight: bold;
}

.info_ul {
	overflow: hidden;
	zoom: 1;
	list-style-type: none;	
    padding-left: 0px;
    padding-top: 0px;
    height: 27px;
    margin-top: 0px;
    margin-bottom: 0px;
	
}

.tab_con {
	border: 1px #bbb solid;
	height: 125px;
}

.tab_con div {
	display: none;
	border: none;
}

.tab_con div.fdiv {
	display: block;
}

.container img {
	max-width: none;
}

#panel {
	position: absolute;
	background-color: #fff;
	border: solid 1px silver;
	box-shadow: 3px 4px 3px 0px silver;
	right: 10px;
	top: 10px;
	padding: 5px 10px;
	font-size: 12px;
	border-radius: 4px
}
</style>
</head>
<body id="bodyId" class="easyui-layout">
	<div data-options="region:'north', border:true" style="height: 55px;">
		<table id="top"
			style="width: 100%; height: 50px; border: #F3F3F3; background-color: #FCFCFC">
			<tr style="padding-top: 5px">
				<td align="center"
					style="widht: 100px; border-right: #EEEEEE 1px solid;">您有 <span
					class="span-red" id="newMorder" style="color: red">0 </span> 新报修未处理
				</td>
				<td><input class="easyui-searchbox" disabled="disabled"
					style="background-color: #BABABA; width: 500px; height: 35px; padding-left: 20px" />
				</td>
				<td align="right"
					style="width: 250px; border-left: #EEEEEE 1px solid;">当前用户：<%=session.getAttribute("LOGIN_NAME")%>
					<a onclick="signout()" id="sysLogoutBtn" href="#"
					class="easyui-linkbutton c5" style="width: 80px">退出</a>
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center', border: true"
		style="height: 400px;">
		
		<div id="qmMapContainer" style="width: 100%; height: 100%"></div>
		<div id="panel">
		<table>
		     <tr>
		     <td>
			     <a id="projectConfig"  class="easyui-linkbutton"  data-options="iconCls:'icon-ext-tools'" href="javascript:void(0)" title="项目配置"  >配置</a>
				   项目名称:<input id="projectid" class="easyui-combobox"  data-options=" panelHeight:'120',valueField:'projectId',textField:'projectName'" style="width:150px;height:32px"/>
				    订单编号:<input  id="orderNo"  name="orderNo"   type="text" class="easyui-textbox" style="width:150px;height:32px"/>
				    设备标识:<input  id="deviceItemPara"  name="deviceItemPara"   type="text" class="easyui-textbox" style="width:150px;height:32px"/>
			    <a id="doSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a>
		   </td>
		   </tr>
		   <tr>
		     <td>
		        <img style="width:20px;height:20px" src="<%=request.getContextPath()%>/images/order_success.png"/>已完成订单:<span id="successOrder">0</span>
		        <img style="width:20px;height:20px" src="<%=request.getContextPath()%>/images/order_working.png"/>安装中订单:<span id="workingOrder">0</span>
		        <img style="width:20px;height:20px" src="<%=request.getContextPath()%>/images/order_maintian.png"/>维修中订单:<span id="maintianOrder">0</span>
		     </td>
		   </tr>
		   </table>
		</div>
	</div>
	<div id="project_window" title="项目信息" class="easyui-window"
		data-options="		
			modal:false,
			draggable :true,
			resizable :false,
			closed :true,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			shadow : true,
			striped : true"
		style="width: 850px; height: 400px;">
	   <div class="easyui-layout" style="width:100%;height:100%;">
	           <div data-options="region:'north',split:false,border:true,collapsible:false" style="height:46px">
				  <table style="padding-top:4px">
		               <tr>
                           <td>项目名称</td>
                           <td colspan="3"><input id="projectName"  class="easyui-textbox"  style="height:30px;width:100px;"/></td>
                           <td>项目编号</td>
                           <td><input id="projectNo"  type="text"   class="easyui-textbox"  style="height:30px;width:100px;" /></td>
                           <td>项目经理</td>
                           <td><input id="pmName"  type="text"   class="easyui-textbox"  style="height:30px;width:100px;" /></td>
                           <td>签订时间</td>
                           <td><input id="signBeginDate"  type="text"   class="easyui-datebox"  style="height:30px;width:100px;" />-
                               <input id="signEndDate"  type="text"   class="easyui-datebox"  style="height:30px;width:100px;" /></td>
                           <td> <a id="doSearchProjectBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a></td>
                         </tr>
                   </table>
		       </div> 
		        <div data-options="region:'center',split:false,border:true,collapsible:false">
			        <table id="projectDg" class="easyui-datagrid" style="width:auto;height:auto"   
						data-options="singleSelect:false,collapsible:false,fit:true,pagination:false,rownumbers:true,pageSize:12,pageList:[12,30,50],checkbox:true">
						<thead>
							<tr>
							    <th data-options="field:'ck',checkbox:true,width:50"></th>
								<th data-options="field:'projectName',width:80,align:'center'">项目名称</th>
								<th data-options="field:'projectNo',width:80,align:'center'">项目编号 </th>
								<!-- <th data-options="field:'cusType',width:70,align:'center',formatter:function(value,row,index){return BasePage.UserTypeformater(value);}">客户类别</th> -->
								<th data-options="field:'buildType',width:80,align:'center',formatter:function(value,row,index){return BasePage.BuildTypeformater(value);}">建设方式</th>
								<th data-options="field:'cusName',width:70,align:'center'">客户联系人</th>
								<th data-options="field:'pmName',width:70,align:'center'">项目经理 </th>
								<th data-options="field:'cmName',width:70,align:'center'">客户经理</th>
								<th data-options="field:'workEndDate',width:100,align:'center'">工期截止日期</th>
								<th data-options="field:'signDate',width:80,align:'center'">签订时间</th>
								<th data-options="field:'setDefault',width:100,align:'center',formatter:function(value,row,index){return indexPage.formatOper(value,row,index);}">默认项目</th>
							</tr>
						</thead>
					 </table>
		         </div>
		         <div data-options="region:'south',split:false,border:false,collapsible:false" style="height:42px;">
		              <div style="float:right;margin:5px 20px 0 0 ">
		                     <a id="project_window_OK" href="javascript:void(0)" class="easyui-linkbutton"  style="width:100px;height: 30px;">设置</a>
                      		 <a id="project_window_cancel" href="javascript:void(0)" class="easyui-linkbutton"   style="width:100px;height: 30px">追加设置</a>
		               </div>
		        </div>
	   </div>
	</div>
</body>
</html>
