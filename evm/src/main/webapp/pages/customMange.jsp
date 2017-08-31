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
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css"/>
<!--自定义文件-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.showLoading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BasePage.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/Util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/customManage.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/customUtil.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/logout.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/BizPageSt.js"></script>
</head>
<body id="customManageBody" class="easyui-layout"  > 
	 <div data-options="region:'north', border:true" style="height: 171px;">
	 
	        <table id="top" style="width: 100%; height: 50px; border: #F3F3F3; background-color: #FCFCFC">
				<tr style="padding-top: 5px">
					<td align="center"
						style="widht: 100px; border-right: #EEEEEE 1px solid;">您有<span
						class="span-red" id="newMorder" style="color:red"> 0 </span> 新报修未处理
					</td>
					<td><input class="easyui-searchbox"   id="cusNamePara"
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
				  <td class="redTd_class" id="todayStopServiceCntDiv">
					 <div  class="td_img_div_class">
						 <img src="<%=request.getContextPath()%>/images/web_icon_09.png" />
					 </div>
					 <div> 
						  <div   class="td_digit_div_class">
							  <span style="font-size:30px;" id="todayStopServiceCnt">0</span>
						 </div>
						  <div class="td_char_div_class">
							 <span style="font-size:8px;">今日停止服务的客户</span>
						  </div>
					  </div>
				  </td>
				  <td  class="yellowTd_class" id="threeDayStopServiceCntDiv" > 
					   <div  class="td_img_div_class">
						 <img src="<%=request.getContextPath()%>/images/web_icon_10.png" />
					 </div>
					 <div> 
						  <div class="td_digit_div_class">
							  <span style="font-size:30px;" id="threeDayStopServiceCnt">0</span>
						 </div>
						  <div class="td_char_div_class">
							 <span style="font-size:8px;">7日内停止服务的客户</span>
						  </div>
					  </div>
				  </td>
				  <td  class="blueTd_class"   id="fifthDayStopServiceCntDiv" >
					   <div  class="td_img_div_class">
						 <img src="<%=request.getContextPath()%>/images/web_icon_11.png" />
					 </div>
					 <div> 
						  <div  class="td_digit_div_class">
							  <span style="font-size:30px;" id="fifthDayStopServiceCnt">0</span>
						 </div>
						  <div class="td_char_div_class">
							 <span style="font-size:8px;">本月内停止服务的客户</span>
						  </div>
					  </div>
				  </td>		   
				  <td  class="purpleTd_class"  id="monthStopServiceCntDiv" >
					 <div  class="td_img_div_class">
						 <img src="<%=request.getContextPath()%>/images/web_icon_12.png" />
					 </div>
					 <div> 
						  <div class="td_digit_div_class">
							  <span style="font-size:30px;" id="monthStopServiceCnt">0</span>
						 </div>
						  <div class="td_char_div_class">
							 <span style="font-size:8px;">全部客户</span>
						  </div>
					  </div>
				  </td>
				</tr> 	 
			 </table>
			 <div id="toolbar" class="toolbar">
			    <!-- 删除-->
			    <div  id="delCustomer">
					     <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_del.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_del_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_del.png'"/>
				</div>
				<!-- 新增-->
			    <div>
				     <a id="addCustomHref" href="<%=request.getContextPath()%>/pages/addCustomer.jsp">
				       <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_add.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_add_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_add.png'"/>
					 </a>
				</div>
				<!-- 修改-->
			   <div  id="updCustomer">
					 <a id="updCustomHref" href="#">
					        <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_edit.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_edit_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_edit.png'"/>
					 </a>  
				</div>
				<div  id="desc" style="float:left;background-color:#060192;color:white;font-size:18px;width:180px;height:20px;padding-left:30px;padding-top:10px">客户管理</div>
			 </div>
			 <div id="hiden_div">
				 <input type="hidden" id="queryStat"  value="" />
				 <input type="hidden" id="stopServiceDays"  value="0" /> 
			</div>
	 </div>
	
	<div data-options="region:'center',split:false"  >
	    
		<div style="height:100%">
			<table class="easyui-datagrid" style="width:auto;height:auto"  id="customerTable" 
			data-options="singleSelect:true,collapsible:false,fit:true,pagination:true,rownumbers:true,pageSize:12,pageList:[12,30,50],checkbox:true">
			<thead>
				<tr>
				    <th data-options="field:'ck',checkbox:true,width:50"></th>
				    <th data-options="field:'cno',width:80,align:'center'">客户编号</th>
					<th data-options="field:'cname',width:80,align:'center'">客户姓名</th>
					<th data-options="field:'ctel1',width:80,align:'center'">客户电话1</th>
					<th data-options="field:'ctel2',width:80,align:'center'">客户电话2</th>
					<th data-options="field:'csex',width:80,align:'center',formatter:function(value,row,index){return BasePage.Sexformater(value);}">客户性别</th>
					<th data-options="field:'cidCard',width:120,align:'center'">客户身份证号</th>
					<th data-options="field:'ctype',width:80,formatter:function(value,row,index){return BasePage.UserTypeformater(value,row,index);}">用户类型</th>
					<th data-options="field:'caddress',width:120,align:'left'">客户地址 </th>
					<th data-options="field:'substation',width:120,align:'left'">所属分局</th>
					<th data-options="field:'policestation',width:120,align:'left'">所属派出所</th>
					<th data-options="field:'projects',width:120,align:'left'">包含项目</th>
				    <th data-options="field:'remark',width:120,align:'left'">备注</th>
				</tr>
			</thead>
		   </table>
	   </div>
	</div>
</body>
</html>
