<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>二维码设备管理系统</title>
<link type="text/css" rel="stylesheet" href="./css/login.css" />
<script type="text/javascript" src="./jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript" src="./js/common/jQuery.md5.js"></script>
<script type="text/javascript" src="./js/login.js"></script>
<script type="text/javascript">
	function refresh(obj) {
		obj.src = "imageServlet?" + Math.random();
	}
</script>

</head>
<body>
	<form action="<%=request.getContextPath()%>/loginController.do?method=login" method="post">
		<div id="fs-login-background">
			<img class="fs-login-img" src="<%=request.getContextPath()%>/images/login/fslogin_bg.jpg" alt="" galleryimg="no" border="0">
		</div>
		<div id="fs-login">
			<div id="fs-login-scalebg">
				<img class="fs-login-scalebg-img" src="<%=request.getContextPath()%>/images/login/fslogin_bg.jpg" alt="" galleryimg="no" border="0">
			</div>
			<div id="fs-login-glass"></div>
			<div id="fs-login-content">
				<!-- 项目管理实验室进度管理平台 -->
				<div id="fs-login-title">二维码管理系统</div>
				<div class="fs-login-input fs-login-input-username">
					<input name="userName" tabindex="1" class="fs-login-username" type="text" value=""/>
				</div>
				<div class="fs-login-input fs-login-input-password">
					<input name="userPwd" tabindex="2" class="fs-login-password" type="password"  value=""/>
				</div>
				<div class="fs-login-input fs-login-input-checkcode">
					<!-- <table>
				    <tr>
				      <td> -->
					<input name='checkCode' class="fs-login-checkCode" tabindex="3" type="text" />
					<!-- </td>
				      <td  align='right'>-->
					<img title="点击更换" onclick="javascript:refresh(this);" src="imageServlet" style="vertical-align: middle">
					<!--</td>
				    </tr>
				</table> -->
				</div>
				<a tabindex="4" href="#" id="fs-login-btn" onclick="subLogin()"></a>
				<div class="fs-login-check">
					<span id="mess" class="red"> 
					<c:if test="${!empty param.loginInfo}">
							<c:if test="${param.loginInfo=='false'}">登录名或密码错误，请重新输入！</span>
					</dd>
					</c:if>
					<c:if test="${param.loginInfo=='checkCodeFalse'}">校验码输入有误，请重新输入！</span>
						</dd>
					</c:if>
					<c:if test="${param.loginInfo=='noConnFunc'}">当前用户无登录权限！</span>
						</dd>
					</c:if>
					</c:if>
					</span>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
