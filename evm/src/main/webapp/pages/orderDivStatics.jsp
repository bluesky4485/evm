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
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/icon.css">
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
			项目名称:<input id="projectid" class="easyui-combobox"  data-options=" panelHeight:'120',valueField:'projectId',textField:'projectName'" style="width:200px;height:32px"/>
		</div>
	</div>
</body>
</html>
