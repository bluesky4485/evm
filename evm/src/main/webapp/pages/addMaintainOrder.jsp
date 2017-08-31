<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"%>
<%@ page import="org.evm.biz.order.common.OrderNoBuilder" %>
<jsp:useBean id="builder" class="org.evm.biz.order.common.OrderNoBuilder"></jsp:useBean>
<%@ page isELIgnored="false" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="./../jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript" src="./../jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="./../jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="./../jquery-easyui-1.5/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="./../jquery-easyui-1.5/themes/icon.css">
<!--自定义文件-->
<script src="http://webapi.amap.com/maps?v=1.3&key=e4a4a2fd139f9535810830feefddf38f&&plugin=AMap.OverView,AMap.ToolBar"></script>

<link rel="stylesheet" type="text/css" href="./../css/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.showLoading.js"></script>
<script type="text/javascript" src="./../js/common/BasePage.js"></script>
<script type="text/javascript" src="./../js/common/BaseMap.js"></script>
<script type="text/javascript" src="./../js/common/Util.js"></script>
<script type="text/javascript" src="./../js/util/faultTypeUtil.js"></script>
<script type="text/javascript" src="./../js/util/orderUtil.js"></script>
<script type="text/javascript" src="./../js/util/morderUtil.js"></script>
<script type="text/javascript" src="./../js/util/userUtil.js"></script>
<script type="text/javascript" src="./../js/addMaintainOrder.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/util/BizPageSt.js"></script>
 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/logout.js"></script>
</head>
<body id="addMorderBody" class="easyui-layout">
	<div data-options="region:'north', border:true" style="height: 217px;">
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
				  <td class="redTd_class">
					 <div  class="td_img_div_class">
						 <img src="./../images/web_icon_09.png" />
					 </div>
					 <div> 
						  <div class="td_digit_div_class">
							  <span style="font-size:30px;" id="aallNoDoMorderCnt">0</span>
						 </div>
						  <div class="td_char_div_class">
							 <span style="font-size:8px;" >未处理的运维订单</span>
						  </div>
					  </div>
				  </td>
				  <td  class="yellowTd_class"> 
					   <div  class="td_img_div_class">
						 <img src="./../images/web_icon_10.png" />
					 </div>
					 <div> 
						  <div class="td_digit_div_class">
							  <span style="font-size:30px;" id="anoCallBackMorderCnt">0</span>
						 </div>
						  <div class="td_char_div_class">
							 <span style="font-size:8px;">未回访的运维订单</span>
						  </div>
					  </div>
				  </td>
				  <td  class="blueTd_class">
					   <div  class="td_img_div_class">
						 <img src="./../images/web_icon_11.png" />
					 </div>
					 <div> 
						  <div class="td_digit_div_class">
							  <span style="font-size:30px;" id="adoingMorderCnt">0</span>
						 </div>
						  <div class="td_char_div_class">
							 <span style="font-size:8px;">运维中的运维订单</span>
						  </div>
					  </div>
				  </td>		   
				  <td  class="purpleTd_class">
					 <div  class="td_img_div_class">
						 <img src="./../images/web_icon_12.png" />
					 </div>
					 <div> 
						  <div class="td_digit_div_class">
							  <span style="font-size:30px;" id="aproblemMorderCnt">0</span>
						 </div>
						  <div class="td_char_div_class">
							 <span style="font-size:8px;">存在问题的运维订单</span>
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
			<!--  保存新增 -->
			<div id="saveMorderBtn">
				<a href="#"> 
				 <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_tj.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_tj_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_tj.png'"/>
				</a>
			</div>
			 
			<div  id="desc" style="float:left;;background-color:#060192;color:white;font-size:18px;width:180px;height:20px;padding-left:30px;padding-top:10px">创建运维订单</div>
		</div>
		<div id="hidden_div">
		             <input type="hidden" id="cusId" name="cusId"  value="0"/>
			         <input type="hidden" id="morderPmId" name="morderPmId"  value="0"/><!--  运维经理-->
			         <input type="hidden" id="maintainPmId" name="maintainPmId"  value="0" /><!-- //维修负责人 -->
			         <!-- 客户地址-->
		             <input type="hidden" id="lat" name="lat"  value="0" /><!--lat -->
			         <input type="hidden" id="lng" name="lng"  value="0" /><!--lng -->
		</div>
		<div id="orderEdit" style="background-color: #f3f3f3">
			<table >
			  <tr>
			     <td style="padding-left:10px">订单编号<span class="redStar">*</span></td>
			     <td><input id="orderId" class="easyui-combobox"  data-options=" panelHeight:'120px',valueField:'orderId',textField:'orderNo',editable:false" style="width:200px;height:42px"></td>
			     <td>运维编号<span class="redStar">*</span></td>
			     <td><input id="maintainNo" value="<%=builder.BuildMOrderNo()%>"   disabled="disabled"  class="easyui-textbox"  style="width:200px;height:42px"></td>
			     <td><div style="float:left;padding-left:30px">运维订单录入员：<%=session.getAttribute("LOGIN_NAME") %> </div>
			         <div style="float:left;padding-left:30px">创建日期：<span id="currentDate"></span></div></td>
			  </tr>
			</table>
		</div>
	</div>
	<div data-options="region:'center', border: true">
	   <div style="padding:10px">
		<table class="input_table">
			<tr>
				<td>客户姓名</td>
				<td><input  id="cName"  disabled="disabled"  type="text"  class="easyui-textbox" /></td>
				<td>派修时间</td>
				<td><input id ="maintainDate"  type="text" class="easyui-datebox"  data-options="editable:false" /></td>
				<td rowspan="3">解决方案</td>
				<td rowspan="3" style="width:200px"><input id ="solution"type="text" class="easyui-textbox" multiline="true"  style="width:100%;height: 120px" /></td>
			</tr>
			<tr>
				<td>客户电话1</td>
					<td><input id="ctel1"  disabled="disabled"  type="text" class="easyui-textbox" /></td>
				<td>故障描述</td>
				<td><input   id="faultDesc"  type="text"  multiline="true"  class="easyui-textbox" /></td>
			</tr>
			<tr>
				<td>客户电话2</td>
				<td><input id="ctel2"  disabled="disabled"  type="text" class="easyui-textbox" /></td>
				<td>预约时间</td>
				<td><input id="appointmentDate" type="text" class="easyui-datebox" data-options="editable:false" /></td>
			    
			</tr>
			<tr>
				<td>客户地址</td>
				<td><input  id="caddress"  disabled="disabled"  type="text" class="easyui-textbox" /><a id="addCusMap" href="#" class="easyui-linkbutton">地图</a></td>
				<td>计划完成时间</td>
				<td><input id="planEndDate" type="text" class="easyui-datebox" data-options="editable:false" /></td>
				<td>最后一次进度</br>更新时间</td>
				<td><input id="lastUpdDate" type="text" class="easyui-datetimebox" disabled="disabled"  style="width:100%;" /></td>
			    
			</tr>
			<tr>
				<td>用户类别</td>
				<td><input id="utype"  disabled="disabled"  type="text" class="easyui-combobox"  data-options=" panelHeight:'auto',valueField:'tvalue',textField:'tname'"/></td>
				<td>维修人数</td>
				<td><input id="maintainWorkerCnt"  type="text" class="easyui-numberbox" value="0" data-options="min:0,max:10000"  ></td>
			    <td rowspan="2" >维修结果描述</td>
				<td rowspan="2" ><input id="maintainResultDesc" type="text" class="easyui-textbox" multiline="true"  style="height:80px;width: 100%"/></td>
			</tr>
			<tr>
				<td>服务截止日期</td>
				<td><input  id="serviceEndDate"  disabled="disabled"  type="text" class="easyui-datebox" data-options="editable:false" /></td>
				<td>维修开始日期</td>
				<td><input id="maintainBeginDate" type="text" class="easyui-datebox" data-options="editable:false"  /></td>
				</tr>
			<tr>
				<td>运维经理</td>
				<td><input id="maintainName"   class="easyui-searchbox" data-options="editable:false"  /></td>
				<td>维修状态</td>
				<td><input  id="maintainStatus"   class="easyui-combobox" data-options=" panelHeight:'100',valueField:'id',textField:'text'" /></td>
				<td rowspan="3">备注</td>
				<td  rowspan="3"><input id="maintainRemark" type="text" class="easyui-textbox"  multiline="true" style="height:120px;width:100%"/></td>
		
			</tr>
			 <tr>
				<td>运维经理电话1</td>
				<td><input id="maintainTel1"   disabled="disabled"  type="text" class="easyui-textbox" /></td>
				<td>维修进度</td>
				<td><input id="maintainSpeed" type="text" class="easyui-numberbox" data-options="min:0,max:100"  value="0"/></td>
			</tr>
			<tr>
				<td>运维经理电话2</td>
				<td><input id="maintainTel2"   disabled="disabled"  type="text" class="easyui-textbox" /></td>
				<td>故障类别</td>
				<td><input id="faultType" type="text" class="easyui-combobox" data-options=" panelHeight:'100',valueField:'faultTypeId',textField:'faultTypeName'"/></td>
			</tr>
			<tr>
				<td>验收状态</td>
				<td><input  id="acceptStatus"     type="text" class="easyui-combobox" data-options=" panelHeight:'auto',valueField:'id',textField:'text'"/></td>
				<td>维修负责人</td>
				<td><input id="maintainPm"  type="text" class="easyui-searchbox"  data-options="editable:false"  /></td>
				<td>用户评分</td>
				<td><input id="userScore"  type="text" class="easyui-numberbox" value="0"  style="width:100%;" data-options="min:0,max:100"/></td>
			</tr>
			<tr>
				<td>验收回访人</td>
				<td><input id="accepteCallMan" type="text" class="easyui-textbox" /></td>
				<td>维修负责人电话1</td>
				<td><input id="maintainPmTel1" disabled="disabled" type="text" class="easyui-textbox" /></td>
				<td rowspan="2">用户评价</td>
				<td rowspan="2"><input id="userProposal" type="text" class="easyui-textbox" multiline="true"    style="height:80px;width:100%"/></td>
			</tr>
			<tr>
				<td>验收回访时间</td>
				<td><input id="callBackDate" type="text" class="easyui-datebox" data-options="editable:false"  /></td>
				<td>维修负责人电话2</td>
				<td><input id="maintainPmTel2" disabled="disabled" type="text" class="easyui-textbox" /></td>
			</tr>
		</table>
	   </div>
	</div>
	<div id="user_window" title="用户信息" class="easyui-window"
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
		style="width: 650px; height: 400px;">
		<input type="hidden" id="openType"/>
		    <div class="easyui-layout" style="width:100%;height:100%;">
		       <div data-options="region:'north',split:false,border:true,collapsible:false" style="height:32px">
				    <input id="userSearch"   class="easyui-searchbox" data-options="prompt:'搜索用户'" style="height:30px;width:100%;background-color: #BABABA"/>
		       </div>
		        <div data-options="region:'center',split:false,border:true,collapsible:false">
			        <table id="userDg"class="easyui-datagrid" style="width:auto;height:auto"   
						data-options="singleSelect:true,collapsible:false,fit:true,pagination:true,rownumbers:true,pageSize:12,pageList:[12,20,50],checkbox:true">
						<thead>
							<tr>
							    <th data-options="field:'ck',checkbox:true,width:50"></th>
							   <th data-options="field:'uid',width:60,align:'center',hidden:true">用户编号</th>
							    <th data-options="field:'uname',width:80,align:'center'">用户名称</th>
								<th data-options="field:'ufullName',width:80,align:'center'">用户姓名</th>
								<th data-options="field:'utel1',width:80,align:'center'">用户电话1</th>
								<th data-options="field:'utel2',width:80,align:'center'">用户电话2</th>
								<th data-options="field:'usex',width:60,align:'center',formatter:function(value,row,index){return BasePage.Sexformater(value);}">用户性别</th>
								<th data-options="field:'uidCard',width:120,align:'center'">用户身份证号</th>
								<th data-options="field:'roleName',width:70">角色类型</th>
							</tr>
						</thead>
					   </table>
		         </div>
		         <div data-options="region:'south',split:false,border:false,collapsible:false" style="height:50px;">
		              <div style="float:right;margin:10px 20px 0 0 ">
		                     <a id="user_window_OK" href="javascript:void(0)" class="easyui-linkbutton"  style="width:100px;height: 30px;" onclick="$('#dlg').dialog('open')">确定</a>
                      		<a id="user_window_cancel" href="javascript:void(0)" class="easyui-linkbutton"   style="width:100px;height: 30px"onclick="$('#dlg').dialog('close')">取消</a>
		               </div>
		        </div>
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
	<form id="retrnManageForm"action="<%=request.getContextPath()%>/morderController.do?method=returnManagePage"  id="isform" method="post">
	</form>
</body>
</html>
