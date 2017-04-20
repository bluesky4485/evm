<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"%>
<%@ page isELIgnored="false" %> 
<jsp:useBean id="builder" class="org.evm.biz.order.common.OrderNoBuilder"></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css"/>

<!--自定义文件-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">

<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.showLoading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BasePage.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/Util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/orderManage.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/projectUtil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/BizPageSt.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/logout.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jscript/qrcode.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.jqprint-0.3.js"></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.PrintArea.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery-migrate-1.2.1.min.js"></script>

</head>
<body id="orderManageBody" class="easyui-layout"  > 
	 <div data-options="region:'north', border:true" style="height: 172px;">
	  <table id="top" style="width: 100%; height: 50px; border: #F3F3F3; background-color: #FCFCFC">
				<tr style="padding-top: 5px">
					<td align="center"
						style="widht: 100px; border-right: #EEEEEE 1px solid;">您有 <span
						class="span-red" id="newMorder" style="color:red">0 </span> 新报修未处理
					</td>
					<td><input class="easyui-searchbox"   id="orderNoPara"
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
				  <td class="redTd_class"   id="todayNoDoOrderCntDiv" >
					 <div  class="td_img_div_class">
						 <img src="<%=request.getContextPath()%>/images/web_icon_13.png" />
					 </div>
					 <div> 
						  <div class="td_digit_div_class">
							  <span style="font-size:30px;" id="todayNoDoOrderCnt">0</span>
						 </div>
						  <div class="td_char_div_class">
							 <span style="font-size:8px;">今日未处理的订单</span>
						  </div>
					  </div>
				  </td>
				  <td  class="yellowTd_class"   id="weekNoDoOrderCntDiv" > 
					   <div  class="td_img_div_class">
						 <img src="<%=request.getContextPath()%>/images/web_icon_14.png" />
					 </div>
					 <div> 
						  <div class="td_digit_div_class">
							  <span style="font-size:30px;" id="weekNoDoOrderCnt">0</span>
						 </div>
						  <div class="td_char_div_class">
							 <span style="font-size:8px;">7日内未处理的订单</span>
						  </div>
					  </div>
				  </td>
				  <td  class="greenTd_class"  id="monthNoDoOrderCntDiv">
					   <div  class="td_img_div_class">
						 <img src="<%=request.getContextPath()%>/images/web_icon_15.png" />
					 </div>
					 <div> 
						  <div   class="td_digit_div_class">
							  <span style="font-size:30px;" id="monthNoDoOrderCnt">0</span>
						 </div>
						  <div class="td_char_div_class">
							 <span style="font-size:8px;">本月未处理的订单</span>
						  </div>
					  </div>
				  </td>		   
				  <td  class="blueTd_class"  id="allOrderCntDiv" >
					 <div  class="td_img_div_class">
						 <img src="<%=request.getContextPath()%>/images/web_icon_12.png" />
					 </div>
					 <div> 
						  <div class="td_digit_div_class">
							  <span style="font-size:30px;" id="allOrderCnt">0</span>
						 </div>
						  <div class="td_char_div_class">
							 <span style="font-size:8px;">全部订单</span>
						  </div>
					  </div>
				  </td>
				</tr> 	 
			 </table>  
			 <div id="toolbar" class="toolbar">
			    <!-- 删除-->
			    <div  id="delOrder">
					      <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_del.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_del_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_del.png'"/>
				</div>
				<!-- 新增-->
			    <div>
				     <a id="addOrderBtn" href="<%=request.getContextPath()%>/pages/addOrder.jsp">
						    <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_add.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_add_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_add.png'"/>
					 </a>
				</div>
				<!-- 修改-->
			   <div  id="updOrder"  >
					 <a  id="updOrderHref"href="#">
					       <img  onclick=""  src="<%=request.getContextPath()%>/images/web_button_edit.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_edit_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_edit.png'"/>
					 </a>  
				</div>
				<!-- 批量创建订单-->
			   <div  id="batchAddOrder" style="width:75px" >
					 <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_plzj.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_plzj_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_plzj.png'"/>
				</div>
				<div  id="desc" style="float:left;;background-color:#060192;color:white;font-size:18px;width:180px;height:20px;padding-left:30px;padding-top:10px">订单管理</div>
			 </div>
			 <div id="hiden_div">
				 <input type="hidden"  id="queryStat"  value="" />
			</div>
	 </div>
	<div data-options="region:'center',split:false"  >
		<div style="height:100%">
			<table id="orderDg" class="easyui-datagrid" style="width:auto;height:auto"  
			data-options="autoRowHeight:true,singleSelect:true,collapsible:false,fit:true,pagination:true,rownumbers:true,pageSize:12,pageList:[12,30,50],checkbox:true">
			<thead>
				<tr>
				    <th data-options="field:'ck',checkbox:true,width:50"></th>
					<th data-options="field:'projectName',width:100,align:'center'">项目名称</th>
					<th data-options="field:'orderNo',width:150,align:'center'">订单编号 </th>
					<th data-options="field:'cusName',width:80,align:'center'">客户</th>
					<th data-options="field:'workAddress',width:260,align:'left'">施工地点</th>
					<th data-options="field:'pmName',width:80,align:'center'">项目经理 </th>
					<th data-options="field:'workPmName',width:100,align:'center'">施工负责人</th>
					<th data-options="field:'workStatus',width:100,formatter:function(value,row,index){return BasePage.WorkStatusformater(value);}">状态</th>
					<th data-options="field:'insDate',width:120,align:'center'">创建时间</th>
					<!-- <th data-options="field:'opt',width:180,align:'center',formatter:formatOper">操作</th> -->
				</tr>
			</thead>
		   </table>
	   </div>
	</div>
	<div id="batchAdd_window" title="批量增加" class="easyui-window"
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
		style="width: 320px; height: 200px;">
		 <div class="easyui-layout" style="width:100%;height:100%;">
		        <div data-options="region:'center',split:false,border:true,collapsible:false">
					<table>
					   <tr style="height: 30px">
					          <td style="width:120px">项目编号</td>
					          <td style="width:140px"><input id="projectid" class="easyui-combobox"  data-options=" panelHeight:'120px',valueField:'projectId',textField:'projectNo'" ></td>
					   </tr>
					   <tr>
					          <td>订单个数</td>
					          <td>
					                   <input id="orderCnt"   type="text" class="easyui-numberbox"/>
					                   <input id="orderPre"   type="hidden"  value="" />
					          </td>
					   </tr>
					   <tr>
					         <td style="width:140px">订单编号开始</td>
					           <td><input id="orderBegin"  disabled="disabled"  type="text" class="easyui-textbox"      /></td>
					   </tr>
					   <tr>
					          <td>订单编号结束</td>
					          <td><input id="orderEnd"  disabled="disabled"   type="text" class="easyui-textbox"  /></td>
					   </tr>
					</table>
				</div>
				<div data-options="region:'south',split:false,border:false,collapsible:false" style="height:40px;">
		              <div style="margin:3px 5px 0 90px ">
		                      <a id="batch_Create_EVM" href="javascript:void(0)" class="easyui-linkbutton c8"  style="width:100px;height: 30px;"  >批量生成二维码</a>
		                      <a id="batch_print_EVM" href="javascript:void(0)" class="easyui-linkbutton c8"  style="width:100px;height: 30px;"  >批量打印二维码</a>
		              </div>
		        </div>
		</div>
	</div>
	<div id="batch_print_hidden">
	    <table>
	       <tr>
	          
	       </tr>
	    </table> 
	</div>
</body>
</html>
