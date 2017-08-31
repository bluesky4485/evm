<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"	src="<%=request.getContextPath()%>/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.showLoading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BasePage.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/Util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/logout.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/dataManage.js"></script>
<title>数据管理</title>
</head>
<body id="dbManageBody" class="easyui-layout"  > 
     <input type="hidden" id="context" value="<%=request.getContextPath()%>"/>
	 <div data-options="region:'north', border:true" style="height: 52px;">
	        <table id="top" style="width: 100%; height: 50px; border: #F3F3F3; background-color: #FCFCFC">
				<tr style="padding-top: 5px">
					<td align="center"
						style="widht: 100px; border-right: #EEEEEE 1px solid;">您有<span
						class="span-red" id="newMorder" style="color:red"> 0 </span> 新报修未处理
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
	 </div>
	
	<div data-options="region:'center',split:false" >
	    <div class="easyui-layout" style="height:100%">
	       <div data-options="region:'north',split:false" style="height:65px">
	           <div style="padding:5px;">
					 <a id="bakBtn" style="width:180px;height:50px;" href="javascript:void(0)" class="easyui-linkbutton c5"   ><span style="font-size:18px;font-weight: 20px">一键备份</span></a>
			 	     <a id="revertBtn" style="width:180px;height:50px"  href="javascript:void(0)" class="easyui-linkbutton c1"><span style="font-size:18px">一键还原</span></a>
			    </div>
	       </div>
	       <div data-options="region:'center',split:false">
	          <table class="easyui-datagrid" style="width:auto;height:auto"  id="fileDg" 
					data-options="singleSelect:true,collapsible:false,fit:true,pagination:true,rownumbers:true,pageSize:12,pageList:[12,30,50],checkbox:true,loadFilter : BasePage.pagerFilter">
					<thead>
						<tr>
						    <th data-options="field:'ck',checkbox:true,width:50"></th>
						    <th data-options="field:'fileName',width:200,align:'center'">文件名称</th>
							<th data-options="field:'fileSize',width:200,align:'center'">文件大小</th>
							<th data-options="field:'modefiedTime',width:200,align:'center'">创建时间</th>
							<th data-options="field:'downLoad',width:60,align:'center',formatter:function(value,row,index){return  dataManage.formatImage(value,row);}">下载</th>
						</tr>
					</thead>
				   </table>
	       </div>
	    </div>
	   
	     
	</div>
</body>
</html>
