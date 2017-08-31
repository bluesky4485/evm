
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"%>
<%@ page isELIgnored="false"%>
<%@ page import="org.evm.biz.user.entity.UserVO"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>二维码设备管理系统</title>
<link rel="stylesheet" type="text/css"href="./jquery-easyui-1.5/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="./jquery-easyui-1.5/themes/icon.css">
<link rel="stylesheet" type="text/css" href="./jquery-easyui-1.5/demo/demo.css">
<script type="text/javascript" src="./jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript" src="./jquery-easyui-1.5/jquery.easyui.min.js"></script>
 
<link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- MetisMenu CSS -->
<link href="./allcss/metisMenu.min.css" rel="stylesheet"/>
<!-- Custom CSS -->
<link href="./allcss/sb-admin-2.css" rel="stylesheet"/>
<!-- User CSS -->
<link href="./allcss/user.css" rel="stylesheet"/>
 
<link href="./font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
 
  
<!-- 自定义的  -->
<link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/css/style.css"/>
<!-- 测试数据 -->
<script type="text/javascript" src="./js/main.js"></script>
<script type="text/javascript" src="./bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="./allcss/metisMenu.min.js"></script>
<script type="text/javascript" src="./allcss/sb-admin-2.js"></script>
<script type="text/javascript"  >
function signout(){
	$("#logOutForm").submit();
}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',split:true" style="width: 215px;">
	<input type="hidden" id="serverName" value="<%=request.getContextPath()%>"/>
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#sidebar_1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="">二维码MIS管理系统</a>
		</div>
		<!-- /.navbar-header -->
		<div class="navbar-collapse" id="navbar-collapse-1"></div>
		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse" id="sidebar_1">
				<ul class="nav in" id="side-menu">
					 
					<li><a href="pages/userManage.jsp" target="center_iframe"><img src="./images/mis_icon_01.png" class="nav_img">用户管理</a></li>
					<li><a href="pages/roleManage.jsp" target="center_iframe"><img src="./images/mis_icon_02.png" class="nav_img">角色管理</a></li>
					<li><a href="pages/logManage.jsp" target="center_iframe"><img src="./images/mis_icon_03.png" class="nav_img">日志管理</a></li>
					<li><a href="pages/funcManage.jsp" target="center_iframe"><img src="./images/mis_icon_04.png" class="nav_img">权限管理</a></li>
					<li><a href="pages/dataManage.jsp" target="center_iframe"><img src="./images/mis_icon_04.png" class="nav_img">数据管理</a></li>
				 
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>
	</div>

	<div data-options="region:'center'">
		<%-- <div class="navbar-collapse" id="navbar-collapse-1">
			<ul class="nav navbar-nav nav-top-1">
				<li><a href="#">您有<span class="span-red"> 1 </span>新报修未处理
				</a></li>
			</ul>

			<form class="navbar-form navbar-left" role="search">
				<div class="input-group">
					<div class="form-group has-feedback">
						<input type="text" class="form-control" name="search_con"
							id="search_con" placeholder="权限代码或权限名称"> <span
							class="glyphicon glyphicon-search form-control-feedback"></span>
					</div>
				</div>
			</form>

			<ul class="nav navbar-top-links navbar-right nav-top-2">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> 	<%
									UserVO login = (UserVO) session.getAttribute("LOGIN_INFO");
								%> <span id="UserName"> <%=login.getUfullName()%>
					</span><i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-messages" role="menu">
						<li><a href="#"><i class="fa fa-user fa-fw"></i> User
								Profile</a></li>
						<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
						</li>
						<li class="divider"></li>
						<li><a href="#"><i class="fa fa-sign-out fa-fw"></i>
								Logout</a></li>
					</ul> <!-- /.dropdown-user --></li>
				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-top-links -->
		</div> --%>
		<iframe id="center_iframe" name="center_iframe" height='600px'
			width='1024px' src='pages/userManage.jsp' width="100%" frameborder='0'
			marginheight='0' marginwidth='0' scrolling='no'> </iframe>
          <form id="logOutForm" action="<%=request.getContextPath()%>/loginController.do?method=signOut" method="post">
		         <input name='userName' type="hidden" value=''${LOGIN_INFO.uname}' />
		</form>
	</div>

</body>
</html>