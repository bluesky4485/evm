
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
<script type="text/javascript" src="./jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
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
<script type="text/javascript" src="./js/main.js"></script>
<script type="text/javascript" src="./bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="./allcss/metisMenu.min.js"></script>
<script type="text/javascript" src="./allcss/sb-admin-2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BasePage.js"></script>
<script type="text/javascript"  >
function signout(){
	$("#logOutForm").submit();
}
</script>
</head>
<body class="easyui-layout">
   <!-- style="width: 215px;" -->
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
			<a class="navbar-brand" href="">二维码设备管理系统</a>
		</div>
		<!-- /.navbar-header -->
		<div class="navbar-collapse" id="navbar-collapse-1"></div>
		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse" id="sidebar_1">
				<ul class="nav in" id="side-menu">
					<li><a href="pages/index.jsp" class="active" target="center_iframe"><img src="./images/web_icon_01.png" class="nav_img">首页</a></li>
					<li><a href="pages/customMange.jsp" target="center_iframe"><img src="./images/web_icon_02.png" class="nav_img">客户管理</a></li>
					<li><a href="pages/projectManage.jsp" target="center_iframe"><img src="./images/web_icon_03.png" class="nav_img">项目管理</a></li>
					<li><a href="pages/orderManage.jsp" target="center_iframe"><img src="./images/web_icon_04.png" class="nav_img">订单管理</a></li>
					<li><a href="pages/maintainOrderManage.jsp"	target="center_iframe"><img src="./images/web_icon_05.png" class="nav_img">运维管理</a></li>
					<li>
					    <a href="#"><img src="./images/web_icon_06.png" class="nav_img" />统计分析<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="pages/projectStatics.jsp" target="center_iframe">项目信息统计</a></li>
							<li><a href="pages/orderStatics.jsp" target="center_iframe">订单信息统计</a></li>
							<li><a href="pages/morderStatics.jsp" target="center_iframe">维修信息统计</a></li>
							<li><a href="pages/deviceItemStatics.jsp" target="center_iframe">设备信息统计</a></li>
						</ul> 
						<!-- /.nav-second-level -->
					</li>
					<li>
					     <a href="#"><img src="./images/mis_icon_05.png" class="nav_img" />数据维护<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="./pages/faultTypeMange.jsp"  target="center_iframe">故障类型表</a></li>
							<li><a href="./pages/deviceMange.jsp"  target="center_iframe">设备属性表</a></li>
							<li><a href="./pages/deviceMangeNew.jsp"  target="center_iframe">设备信息管理</a></li>
							<li><a href="./pages/orderPrint.jsp"  target="center_iframe">订单打印</a></li>
						</ul> 
					 
					</li>
				</ul>
			</div>
			 
		</div>
		  </nav>
	</div>

	<div data-options="region:'center'">
		 
		<iframe id="center_iframe" name="center_iframe" height='600px'
			width='1024px' src='pages/index.jsp' width="100%" frameborder='0'
			marginheight='0' marginwidth='0' scrolling='no'> </iframe>
          <form id="logOutForm" action="<%=request.getContextPath()%>/loginController.do?method=signOut" method="post">
		         <input name='userName' type="hidden" value='${LOGIN_INFO.uname}' />
		</form>
	</div>

</body>
</html>