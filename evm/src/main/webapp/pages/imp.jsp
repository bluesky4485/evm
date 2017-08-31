<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>二维码设备管理系统</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/icon.css">
<script type="text/javascript">
$(document).ready(function() {
	$("#t1").click(function() {
		debugger;
		var form = document.getElementsByTagName("form");
		form[0].submit();
	});
});
	
</script>
</head>
<body>

	<form id="ajaxForm"
		action="<%=request.getContextPath()%>/fileController.do?method=uploadFile"
		method="post" enctype="multipart/form-data">
		<div class="easyui-panel" title="Upload File"
			style="width: 100%; max-width: 400px; padding: 30px 60px;">
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" label="Name:" name="importRule"
					value="{'A':'property1'}" labelPosition="top" style="width: 100%"/>
				<input type="file" name="file"/>
					
			</div>
		 
			<div>
				<input type="submit" id="t1" value="Upload" />
			</div>

		</div>
	</form>
</body>
</html>
