<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"%>

<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/icon.css">

<!--自定义文件-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.showLoading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BasePage.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/Util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/projectManage.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/logout.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/BizPageSt.js"></script>
</head>
<body id="projectManageBody" class="easyui-layout"  > 
	 <div data-options="region:'north', border:true" style="height: 171px;">
	 <table id="top" style="width: 100%; height: 50px; border: #F3F3F3; background-color: #FCFCFC">
			<tr style="padding-top: 5px">
				<td align="center"
					style="widht: 100px; border-right: #EEEEEE 1px solid;">您有 <span
					class="span-red" id="newMorder" style="color:red">0 </span> 新报修未处理
				</td>
				<td><input class="easyui-searchbox"   id="projectNamePara"
					style="background-color: #BABABA; width: 500px; height: 35px; padding-left: 20px" />
				</td>
				<td align="right" style="width: 250px; border-left: #EEEEEE 1px solid;">
					 当前用户：<%=session.getAttribute("LOGIN_NAME") %>
					       <a onclick="signout()"id="sysLogoutBtn"href="#" class="easyui-linkbutton c5" style="width:80px">退出</a>
				   </td>
			</tr>
		</table>
			  <table style="width:100%">
				<tr>
				  <td class="redTd_class"  id="todayStopWorkCntDiv">
					 <div  class="td_img_div_class">
						 <img src="<%=request.getContextPath()%>/images/web_icon_13.png" />
					 </div>
					 <div> 
						  <div class="td_digit_div_class">
							  <span style="font-size:30px;" id="todayStopWorkCnt">0</span>
						 </div>
						  <div class="td_char_div_class">
							 <span style="font-size:8px;" id="todayStopWorkCnt">今日工期截止的项目</span>
						  </div>
					  </div>
				  </td>
				  <td  class="greenTd_class"  id="weekStopWorkCntDiv">
					   <div  class="td_img_div_class">
						 <img src="<%=request.getContextPath()%>/images/web_icon_15.png" />
					 </div>
					 <div> 
						  <div class="td_digit_div_class">
							  <span style="font-size:30px;" id="weekStopWorkCnt">0</span>
						 </div>
						  <div class="td_char_div_class">
							 <span style="font-size:8px;">7日内工期截止的项目</span>
						  </div>
					  </div>
				  </td>		
				  <td  class="yellowTd_class"  id="mothSingProjectCntDiv" > 
					   <div  class="td_img_div_class">
						 <img src="<%=request.getContextPath()%>/images/web_icon_14.png" />
					 </div>
					 <div> 
						  <div class="td_digit_div_class">
							  <span style="font-size:30px;"id="mothSingProjectCnt">0</span>
						 </div>
						  <div class="td_char_div_class">
							 <span style="font-size:8px;">本月内工期截止的项目</span>
						  </div>
					  </div>
				  </td>
				     
				  <td  class="blueTd_class"    id="allProjectCntDiv">
					 <div  class="td_img_div_class">
						 <img src="<%=request.getContextPath()%>/images/web_icon_12.png" />
					 </div>
					 <div> 
						  <div class="td_digit_div_class">
							  <span style="font-size:30px;" id="allProjectCnt">0</span>
						 </div>
						  <div class="td_char_div_class">
							 <span style="font-size:8px;">全部项目</span>
						  </div>
					  </div>
				  </td>
				</tr> 	 
			 </table> 
			 <div id="toolbar" class="toolbar">
			    <!-- 删除-->
			    <div  id="delProject">
					    <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_del.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_del_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_del.png'"/>
				</div>
				<!-- 新增-->
			    <div>
				     <a href="<%=request.getContextPath()%>/pages/addProject.jsp">
						    <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_add.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_add_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_add.png'"/> 
					 </a>
				</div>
				<!-- 修改-->
			   <div  id="updProject">
					 <a  id="updProjectHref"href="#">
					        <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_edit.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_edit_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_edit.png'"/>
					 </a>  
				</div>
				<div  id="desc" style="float:left;;background-color:#060192;color:white;font-size:18px;width:180px;height:20px;padding-left:30px;padding-top:10px">项目管理</div>
			 </div>
			  <div id="hiden_div">
				 <input type="hidden" id="queryStat"  value="" />
				 <input type="hidden" id="stopServiceDays"  value="0" /> 
			</div>
	 </div>
	
	<div data-options="region:'center',split:false"  >
	    
		<div style="height:100%">
			<table id="projectDg" class="easyui-datagrid" style="width:auto;height:auto"   
			data-options="singleSelect:true,collapsible:false,fit:true,pagination:true,rownumbers:true,pageSize:12,pageList:[12,30,50],checkbox:true">
			<thead>
				<tr>
				    <th data-options="field:'ck',checkbox:true,width:50"></th>
					<th data-options="field:'projectName',width:120,align:'center'">项目名称</th>
					<th data-options="field:'projectNo',width:120,align:'center'">项目编号 </th>
					<th data-options="field:'cusType',width:70,align:'center',formatter:function(value,row,index){return BasePage.UserTypeformater(value);}">客户类别</th>
					<th data-options="field:'buildType',width:80,align:'center',formatter:function(value,row,index){return BasePage.BuildTypeformater(value);}">建设方式</th>
					<th data-options="field:'cusName',width:100,align:'center'">客户联系人</th>
					<th data-options="field:'pmName',width:100,align:'center'">项目经理 </th>
					<th data-options="field:'cmName',width:100,align:'center'">客户经理</th>
					<th data-options="field:'workEndDate',width:100,align:'center'">工期截止日期</th>
					<th data-options="field:'signDate',width:80,align:'center'">签订时间</th>
					<th data-options="field:'projectRemark',width:120,align:'center'">备注</th>
				</tr>
			</thead>
		   </table>
	   </div>
	</div>
	<form id="gotoProjectManageForm" action="<%=request.getContextPath()%>/projectController.do?method=returnCustomManage"  id="isform" method="post">
		</form>
</body>
</html>
