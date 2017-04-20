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
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/gray/easyui.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/icon.css"/>
<!--自定义文件-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
<script src="http://webapi.amap.com/maps?v=1.3&key=e4a4a2fd139f9535810830feefddf38f&&plugin=AMap.OverView,AMap.ToolBar"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.showLoading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BasePage.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BaseMap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/Util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/addCustomer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/customUtil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/BizPageSt.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/logout.js"></script> 
</head>
<body id="addProjectBody" class="easyui-layout">
	<div data-options="region:'north', border:true" style="height: 172px;">
	   <table id="top" style="width: 100%; height: 50px; border: #F3F3F3; background-color: #FCFCFC">
				<tr style="padding-top: 5px">
					<td align="center"
						style="widht: 100px; border-right: #EEEEEE 1px solid;">您有 <span
						class="span-red" id="newMorder" style="color:red">0 </span> 新报修未处理
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
		    <div id="goHist">
		        <a href="#" >
                      <img onclick="javascript:history.back();"  src="<%=request.getContextPath()%>/images/web_button_back.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_back_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_back.png'"/>
                 </a>
			 </div>
			<div id="saveCustom">
				<a id="saveCustomHref" href="#"> 
				 <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_tj.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_tj_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_tj.png'"/>
				</a>
			</div>
			 
             <div  id="desc" style="float:left;;background-color:#060192;color:white;font-size:18px;width:180px;height:20px;padding-left:30px;padding-top:10px">添加客户</div>
		</div>
		<div id="hiden_div">
			<input type="hidden" id="lat" name="lat"  value="0" /><!--lat -->
			 <input type="hidden" id="lng" name="lng"  value="0" /><!--lng -->
		</div>
	</div>
	<div data-options="region:'center', border: true">
		<div style="padding:10px">
		<table class="input_table"  >
			<tr>
				<td>客户姓名<span class="redStar">*</span></td>
				<td><input  id="cname"  name="cname"   type="text" class="easyui-textbox" /></td>
				<td>客户编号 </td>
				<td><input id ="cno" type="text"  disabled="disabled"  name="cno"  class="easyui-textbox" value=""  data-options="readonly:true"  /></td>
				<td>客户电话1<span class="redStar">*</span></td>
				<td><input type="text" id="ctel1"  name="ctel1"  class="easyui-textbox" /></td>
				
			</tr>
			<tr>
				<td>客户电话2<span class="redStar">*</span></td>
				<td><input  id="ctel2" name="ctel2"  type="text" class="easyui-textbox" /></td>
				<td>客户性别</td>
				<td><input id="csex"   name="csex"  type="text"   class="easyui-combobox"  data-options="panelHeight:'auto',valueField:'id',textField:'text',editable:false"/></td>
				<td>客户身份证号</td>
				<td><input id ="cidCard" name="cidCard"  type="text" class="easyui-textbox" /></td>
				 
			</tr>
			<tr>
				<td>客户地址</td>
				<td colspan="5">
				     <input id="caddress"   style="width:400px"name="caddress"  type="text" class="easyui-textbox" />
                     <a id="addCusMap" href="#" class="easyui-linkbutton">地图</a>
               </td>
			</tr>
			<tr>
				<td>所属分局</td>
				<td><input id="substation"  name="substation"   type="text" class="easyui-textbox" /></td>
				<td>所属派出所</td>
				<td><input id="policestation" name="policestation"   type="text" class="easyui-textbox" /></td>
				<td>用户类别</td>
				<td><input id="ctype"   name="ctype"   type="text"  class="easyui-combobox"  data-options=" panelHeight:'auto',valueField:'tvalue',textField:'tname',editable:false"/></td>
			</tr>
			<tr>
				<td>包含项目</td>
				<td colspan="5"><input id="projects" name="projects"   disabled="disabled"  name="projects"   type="text" multiline="true"  class="easyui-textbox" data-options="readonly:true"  style="width: 100%; height: 80px"/></td>
			</tr>
			<tr>
				<td>备注</td>
				<td colspan="5"><input id="remark" name="remark" class="easyui-textbox" multiline="true" style="width: 100%; height: 100px"></td>
			</tr>
		</table>
		</div>
	</div>
	<div id="map_window" title="地理位置" class="easyui-window"
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
		<div   id="mapContainer"style="width: 100%; height: 100%"></div>
	</div>
	<form id="retrnManageForm"action="<%=request.getContextPath()%>/customController.do?method=returnManagePage"  id="isform" method="post">
	
	</form>
</body>
</html>
