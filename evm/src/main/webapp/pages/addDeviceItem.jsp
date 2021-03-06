<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"%>
<%@ page isELIgnored="false" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/datagrid-cellediting.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/icon.css">

<!--自定义文件-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.showLoading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BasePage.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/Util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/addDeviceItem.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/logout.js"></script>
</head>
<body id="addDeviceItemBody" class="easyui-layout"  > 
	 <div data-options="region:'north', border:true" style="height: 150px;">
	         <table id="top" style="width: 100%; height: 50px; border: #F3F3F3; background-color: #FCFCFC">
			<tr style="padding-top: 5px">
				<td align="center"
					style="widht: 100px; border-right: #EEEEEE 1px solid;">您有 <span
					class="span-red" id="newMorder" style="color:red">0 </span> 新报修未处理
				</td>
				<td><input class="easyui-searchbox"     disabled="disabled" 
					style="background-color: #BABABA; width: 500px; height: 35px; padding-left: 20px" />
				</td>
				<td align="right" style="width: 250px; border-left: #EEEEEE 1px solid;">
					 当前用户：<%=session.getAttribute("LOGIN_NAME") %>
					       <a onclick="signout()"id="sysLogoutBtn"href="#" class="easyui-linkbutton c5" style="width:80px">退出</a>
				</td>
			</tr>
		</table>
			 <div id="toolbar" class="toolbar">
				    <div  id="goHist">
			          <a href="#" >
	                      <img onclick="javascript:history.back();"  src="<%=request.getContextPath()%>/images/web_button_back.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_back_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_back.png'"/>
	                 </a>
				 </div>
				<!-- 修改-->
				   <div  id="saveBtn">
						 <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_tj.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_tj_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_tj.png'"/>
					</div>
					<div  id="desc" style="float:left;;background-color:#060192;color:white;font-size:18px;width:180px;height:20px;padding-left:30px;padding-top:10px">设备信息管理</div>
			 </div>
			  <div id="hidden_div">
			     <input type="hidden" id="deviceItemId"  value="<c:out value='${updObj.deviceItemId}'/>"     />
			      <input type="hidden" id="orderid"  value=""     />
			       <input type="hidden" id="deviceTypeId"    value=""     />
			 </div>
		     <div style="padding:10px 0 0 10px">
					<table >
		             <tr>
                           <td>订单编号</td>
                           <td colspan="3"><input id="para_order_no"   class="easyui-searchbox"    style="height:30px;width:150px;"/></td>
                           <td>设备类别</td>
                           <td><input id="para_deviceType"  type="text"   class="easyui-searchbox"   style="height:30px;width:150px;" /></td>
                            <td>设备标识</td>
                           <td><input id="para_deviceItemUid"  type="text"   class="easyui-textbox"   style="height:30px;width:200px;" /></td>
                         </tr>
                    </table>
		  </div>
	</div>
	
	<div data-options="region:'center',split:false"  >
		<div style="height:100%">
			<table class="easyui-datagrid" style="width:auto;height:auto"  id="deviceItemPropertyTable" 
			data-options="singleSelect:true,collapsible:false,fit:true,pagination:false,rownumbers:true,pageSize:12,pageList:[12,20,50],checkbox:true">
			<thead>
				<tr>
				    <th data-options="field:'ck',checkbox:true,width:50"></th>
				    <th data-options="field:'devcieTypePropertyId',width:200,align:'center',hidden:true">属性名ID</th>
				    <th data-options="field:'deviceTypePropertyName',width:200,align:'center'">属性名</th>
				    <th data-options="field:'deviceItemPropertyValue',width:200,align:'center',editor:'text'">属性值</th>
				</tr>
			</thead>
		   </table>
	   </div>
	</div>
	<div class="easyui-window"  id="order_window" title="订单"
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
		style="width: 620px; height: 400px;">
	      <div class="easyui-layout" style="width:100%;height:100%;">
	            <div data-options="region:'north', border:true" style="height: 36px;">
	                     <input id="orderNo"   class="easyui-searchbox"    style="height:30px;width:100%;"/>
	            </div>
	            <div data-options="region:'center',split:false"  >
	                    <table id="orderDg" class="easyui-datagrid" style="width:auto;height:auto"  
								data-options="autoRowHeight:true,singleSelect:true,collapsible:false,fit:true,pagination:true,rownumbers:true,pageSize:10,pageList:[10,30,50],checkbox:true">
								<thead>
									<tr>
									    <th data-options="field:'ck',checkbox:true,width:50"></th>
										<th data-options="field:'projectName',width:100,align:'center'">项目名称</th>
										<th data-options="field:'orderNo',width:100,align:'center'">订单编号 </th>
										<th data-options="field:'cusName',width:100,align:'center'">客户</th>
										<th data-options="field:'pmName',width:80,align:'center'">项目经理 </th>
										<th data-options="field:'workPmName',width:100,align:'center'">施工负责人</th>
									</tr>
								</thead>
					   </table>
	            </div>
	             <div data-options="region:'south',split:false,border:false,collapsible:false" style="height:50px;">
		              <div style="float:right;margin:10px 20px 0 0 ">
		                     <a id="order_window_OK" href="javascript:void(0)" class="easyui-linkbutton"  style="width:100px;height: 30px;" onclick="$('#dlg').dialog('open')">确定</a>
                      		<a id="order_window_cancel" href="javascript:void(0)" class="easyui-linkbutton"   style="width:100px;height: 30px"onclick="$('#dlg').dialog('close')">取消</a>
		               </div>
		        </div>
	      </div>
	</div>
	<div class="easyui-window"  id="deviceType_window" title="设备类型"
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
		style="width: 620px; height: 400px;">
	      <div class="easyui-layout" style="width:100%;height:100%;">
	            <div data-options="region:'north', border:true" style="height: 36px;">
	                     <input id="deviceType"   class="easyui-searchbox"    style="height:30px;width:100%;"/>
	            </div>
	            <div data-options="region:'center',split:false"  >
	                	<table class="easyui-datagrid" style="width:auto;height:auto"  id="deviceTable" 
			data-options="singleSelect:true,collapsible:false,fit:true,pagination:true,rownumbers:true,pageSize:12,pageList:[12,20,50],checkbox:true">
			<thead>
				<tr>
				    <th data-options="field:'ck',checkbox:true,width:50"></th>
				    <th data-options="field:'deviceName',width:280,align:'center'">设备名称</th>
					<th data-options="field:'classType',width:180,align:'left',formatter:function(value,row,index){return BasePage.DeviceTypeformater(value);}">设备类型</th>
				</tr>
			</thead>
		   </table>
	            </div>
	             <div data-options="region:'south',split:false,border:false,collapsible:false" style="height:50px;">
		              <div style="float:right;margin:10px 20px 0 0 ">
		                     <a id="deviceType_window_OK" href="javascript:void(0)" class="easyui-linkbutton"  style="width:100px;height: 30px;" onclick="$('#dlg').dialog('open')">确定</a>
                      		<a id="deviceType_window_cancel" href="javascript:void(0)" class="easyui-linkbutton"   style="width:100px;height: 30px"onclick="$('#dlg').dialog('close')">取消</a>
		               </div>
		        </div>
	      </div>
	</div>
	<form id="retrnManageForm"action="<%=request.getContextPath()%>/deviceItemController.do?method=returnManagePage"  id="isform" method="post">
	</form>	 
</body>
</html>
