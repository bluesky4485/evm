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

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css"/>
<!--自定义文件-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.showLoading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BasePage.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/Util.js"></script>
 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/logout.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/deviceMangeNew.js"></script>
<script src="http://webapi.amap.com/maps?v=1.3&key=e4a4a2fd139f9535810830feefddf38f&&plugin=AMap.OverView,AMap.ToolBar"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BaseMap.js"></script>
</head>
<body id="deviceManageBody" class="easyui-layout"  > 
	 <div data-options="region:'north', border:true" style="height: 101px;">
	          <table id="top" style="width: 100%; height: 50px; border: #F3F3F3; background-color: #FCFCFC">
				<tr style="padding-top: 5px">
					<td align="center"
						style="widht: 100px; border-right: #EEEEEE 1px solid;">您有 <span
						class="span-red" id="newMorder" style="color:red">0 </span> 新报修未处理
					</td>
					<td><input class="easyui-searchbox"   id="deviceNamePara"
						style="background-color: #BABABA; width: 500px; height: 35px; padding-left: 20px" />
					</td>
					<td align="right" style="width: 250px; border-left: #EEEEEE 1px solid;">
					 当前用户：<%=session.getAttribute("LOGIN_NAME") %>
					       <a onclick="signout()"id="sysLogoutBtn"href="#" class="easyui-linkbutton c5" style="width:80px">退出</a>
				    </td>
				</tr>
		     </table>
			 <div id="toolbar" class="toolbar">
			    <!-- 删除-->
			    <div  id="delDeviceBtn">
			       <a href="#" title="删除">
					    <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_del.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_del_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_del.png'"/>
				   </a>
				</div>
				<!-- 新增-->
			    <div id="addDeviceBtn">
			      <a href="#" title="新增">
						    <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_add.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_add_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_add.png'"/> 
				 </a>
				</div>
				<!-- 修改-->
			   <div  id="updDeviceItemBtn">
					 <a id="updDeviceHref" href="#" title="修改">
					     	 <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_edit.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_edit_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_edit.png'"/>
					 </a>  
				</div>
				<div  id="desc" style="float:left;;background-color:#060192;color:white;font-size:18px;width:180px;height:20px;padding-left:30px;padding-top:10px">设备类型管理</div>
			    
			 </div>
	</div>
	<div data-options="region:'west',split:false" style="width:50%" >
		<div class="easyui-layout" style="width:100%;height:100%" >
		   <div data-options="region:'north',split:false" style="height:50px" >
		    <table style="padding-top:5px">
		             <tr>
                           <td>项目名称</td>
                           <td colspan="3"><input id="pName"  class="easyui-textbox"  style="height:30px;width:150px;"/></td>
                           <td>订单编号</td>
                           <td><input id="oNo"  type="text"   class="easyui-textbox"  style="height:30px;width:150px;" /></td>
                           <td> <a id="doSearchOrderBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a></td>
                         </tr>
                       </table>
		   </div>
		   <div data-options="region:'center',split:false" title="订单数据">
		       <div style="height:100%">
			<table id="orderDg" class="easyui-datagrid" style="width:auto;height:auto"  
			data-options="autoRowHeight:true,singleSelect:true,collapsible:false,fit:true,pagination:true,rownumbers:true,pageSize:12,pageList:[12,30,50],checkbox:true">
			<thead>
				<tr>
				    <th data-options="field:'ck',checkbox:true,width:50"></th>
					<th data-options="field:'projectName',width:80,align:'center'">项目名称</th>
					<th data-options="field:'orderNo',width:100,align:'center'">订单编号 </th>
					<th data-options="field:'cusName',width:60,align:'center'">客户</th>
					<!-- <th data-options="field:'workAddress',width:270,align:'left'">施工地点</th> -->
					<th data-options="field:'pmName',width:70,align:'center'">项目经理 </th>
					<th data-options="field:'workPmName',width:70,align:'center'">施工负责人</th>
					<th data-options="field:'workStatus',width:80,formatter:function(value,row,index){return BasePage.WorkStatusformater(value);}">状态</th>
					<!-- <th data-options="field:'opt',width:180,align:'center',formatter:formatOper">操作</th> -->
				</tr>
			</thead>
		   </table>
	   </div>
		   </div>
		</div>
	
	</div>
	<div data-options="region:'center',split:false" style="width:50%" >
	    <div class="easyui-layout" style="width:100%;height:100%">
	   <div data-options="region:'north',split:false" style="height:50px" >
	             <table style="padding-top:5px">
		             <tr>
                           <td>设备类型名称</td>
                           <td><input id="deviceTypeName"  type="text"   class="easyui-textbox"  style="height:30px;width:150px;" /></td> 
                           <td>设备分类</td>
                           <td><input id="deviceTypeClassType"     style="height:30px;width:150px;" class="easyui-combobox" data-options=" panelHeight:'auto',valueField:'id',textField:'text'"/></td>
                           <td> <a id="doSearchDeviceItemBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a></td>
                         </tr>
                  </table>
	   </div>
	   <div data-options="region:'center',split:false" title="设备数据">
	        <div style="height:100%">
			<table class="easyui-datagrid" style="width:auto;height:auto"  id="deviceItemTable" 
			data-options="singleSelect:true,collapsible:false,fit:true,pagination:false,rownumbers:true,pageSize:12,pageList:[12,20,50],checkbox:true">
			<thead>
				<tr>
				    <th data-options="field:'ck',checkbox:true,width:50"></th>
				  <!--   <th data-options="field:'orderNo',width:200,align:'center'">订单编号</th> -->
				    <th data-options="field:'deviceItemId',width:50,align:'center'">设备ID</th>
				    <th data-options="field:'deviceItemName',width:100,align:'center',formatter:function(value,row,index){return row['deviceTypeName']+row['deviceItemId'];}">设备名称</th>
				    <th data-options="field:'deviceTypeName',width:100,align:'center'">设备类型名称</th>
					<th data-options="field:'deviceTypeClassType',width:100,align:'left',formatter:function(value,row,index){return BasePage.DeviceTypeformater(value);}">设备分类</th>
					<th data-options="field:'deviceItemUid',width:80,align:'center'">唯一标识</th>
				</tr>
			</thead>
		   </table>
	   </div>
	   
	   </div>
	  </div>
	</div>
	<div id="add_window" title="增加设备信息" class="easyui-window"
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
		style="width: 700px; height: 500px;">
		<div id="hidden">
		   <input type="hidden" value="" id="hidden_add_deviceTypeId"/>
		   <input type="hidden" value="" id="hidden_add_orderId"/>
		   <!-- <input type="hidden" value="" id="hidden_add_lat" value="0"/>
		   <input type="hidden" value="" id="hidden_add_lng" value="0"/> -->
		</div>
		 <div  class="easyui-layout" style="width:100%;height:100%">
		      <div data-options="region:'west',split:false" style="width:40%" >
		         <table style="padding-top:20px;padding-left:10px">
		              <tr style="height:40px">
                           <td>订单编号:</td>
                           <td colspan="3"><span id="add_orderNo"></span></td>
                       <tr>
                            <td>设备类别:</td>
                           <td><input id="add_deviceType"  type="text"   class="easyui-searchbox"   style="height:30px;width:150px;" /></td>
                       </tr>
                       <tr>
                           <td>唯一标识:</td>
                           <td><input id="add_deviceItemUid"  type="text"   class="easyui-textbox"   style="height:30px;width:150px;" /></td>
                         </tr>
                         <tr>
                           <td>设备地址:</td>
                           <td><input id="add_deviceItemAddr"  type="text"   class="easyui-textbox"  multiline="true"  style="height:60px;width:150px;" /><a id="addMap" href="#" class="easyui-linkbutton">地图</a></td>
                         </tr>
                         <tr>
                           <td>维度:</td>
                           <td><input id="hidden_add_lat"  type="text"   class="easyui-textbox"   style="height:30px;width:150px;" value="0" /></td>
	                     </tr>
	                     <tr>
	                       <td>维度:</td>
	                       <td><input id="hidden_add_lng"  type="text"   class="easyui-textbox"   style="height:30px;width:150px;" value="0" /></td>
	                     </tr>
                    </table>
		      </div>
		      <div data-options="region:'center',split:false" style="width:60%" >
		             <table class="easyui-datagrid" style="width:auto;height:auto"  id="deviceItemPropertyTable" 
							data-options="singleSelect:true,collapsible:false,fit:true,pagination:false,rownumbers:true,pageSize:12,pageList:[12,20,50],checkbox:true">
							 <thead>
									<tr>
									    <th data-options="field:'ck',checkbox:true,width:50"></th>
									    <th data-options="field:'devcieTypePropertyId',width:200,align:'center',hidden:true">属性名ID</th>
									    <th data-options="field:'deviceTypePropertyName',width:150,align:'center'">属性名</th>
									    <th data-options="field:'deviceItemPropertyValue',width:150,align:'center',editor:'text'">属性值</th>
									</tr>
								</thead>
						   </table>
		      </div>
		      <!--  -->
		        <div data-options="region:'south',split:false,border:false,collapsible:false" style="height:50px;">
		              <div style="float:right;margin:10px 20px 0 0 ">
		                     <a id="addItem_window_OK" href="javascript:void(0)" class="easyui-linkbutton"  style="width:100px;height: 30px;" onclick="$('#dlg').dialog('open')">确定</a>
                      		 <a id="addItem_window_cancel" href="javascript:void(0)" class="easyui-linkbutton"   style="width:100px;height: 30px"onclick="$('#dlg').dialog('close')">取消</a>
		               </div>
		        </div>
		       <!--  -->
		 </div>
	</div>
	<div id="update_window" title="修改增加设备" class="easyui-window"
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
		style="width: 700px; height: 500px;">
		<div id="hidden">
		   <input type="hidden" value="" id="hidden_updDate"/>
		   <!-- <input type="hidden" value="" id="hidden_upd_lat" value="0"/>
		   <input type="hidden" value="" id="hidden_upd_lng" value="0"/> -->
		</div>
		 <div  class="easyui-layout" style="width:100%;height:100%">
		      <div data-options="region:'west',split:false" style="width:40%" >
		         <table style="padding-top:20px;padding-left:10px">
		              <tr style="height:40px">
                           <td>设备编号:</td>
                           <td colspan="3"><span id="upd_deviceItemID"></span></td>
                       </tr>
                       <tr style="height:40px">
                           <td>设备名称:</td>
                           <td colspan="3"><span id="upd_deviceItemName"></span></td>
                       </tr>
		               <tr style="height:40px">
                           <td>订单编号:</td>
                           <td colspan="3"><span id="upd_orderNo"></span></td>
                       </tr>
                       <tr style="height:40px">
                           <td>设备类别:</td>
                           <td><span id="upd_deviceType"></span></td>
                       </tr>
                       <tr style="height:40px">
                           <td>唯一标识:</td>
                           <td><input id="upd_deviceItemUid"  type="text"   class="easyui-textbox"   style="height:30px;width:150px;" /></td>
                         </tr>
                         <tr style="height:40px">
                           <td>设备地址:</td>
                           <td><input id="upd_deviceItemAddr"  type="text"   class="easyui-textbox"  multiline="true" style="height:60px;width:150px;" /><a id="updMap" href="#" class="easyui-linkbutton">地图</a></td>
                         </tr>
                          <tr>
                           <td>维度:</td>
                           <td><input id="hidden_upd_lat"  type="text"   class="easyui-textbox"   style="height:30px;width:150px;" value="0" /></td>
	                     </tr>
	                     <tr>
	                       <td>维度:</td>
	                       <td><input id="hidden_upd_lng"  type="text"   class="easyui-textbox"   style="height:30px;width:150px;" value="0" /></td>
	                     </tr>
                    </table>
		      </div>
		      <div data-options="region:'center',split:false" style="width:60%" >
		             <table class="easyui-datagrid" style="width:auto;height:auto"  id="deviceItemPropertyTableUpd" 
							data-options="singleSelect:true,collapsible:false,fit:true,pagination:false,rownumbers:true,pageSize:12,pageList:[12,20,50],checkbox:true">
							 <thead>
									<tr>
									    <th data-options="field:'ck',checkbox:true,width:50"></th>
									    <th data-options="field:'devcieTypePropertyId',width:200,align:'center',hidden:true">属性名ID</th>
									    <th data-options="field:'deviceTypePropertyName',width:150,align:'center'">属性名</th>
									    <th data-options="field:'deviceItemPropertyValue',width:150,align:'center',editor:'text'">属性值</th>
									</tr>
								</thead>
						   </table>
		      </div>
		      <!--  -->
		        <div data-options="region:'south',split:false,border:false,collapsible:false" style="height:50px;">
		              <div style="float:right;margin:10px 20px 0 0 ">
		                     <a id="updItem_window_OK" href="javascript:void(0)" class="easyui-linkbutton"  style="width:100px;height: 30px;" onclick="$('#dlg').dialog('open')">确定</a>
                      		 <a id="updItem_window_cancel" href="javascript:void(0)" class="easyui-linkbutton"   style="width:100px;height: 30px"onclick="$('#dlg').dialog('close')">取消</a>
		               </div>
		        </div>
		       <!--  -->
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
		style="width: 420px; height: 400px;">
	      <div class="easyui-layout" style="width:100%;height:100%;">
	            <div data-options="region:'north', border:true" style="height: 36px;">
	                     <input id="para_deviceType"   class="easyui-searchbox"    style="height:30px;width:100%;"/>
	            </div>
	            <div data-options="region:'center',split:false"  >
	                	<table class="easyui-datagrid" style="width:auto;height:auto"  id="deviceTable" 
			data-options="singleSelect:true,collapsible:false,fit:true,pagination:false,rownumbers:true,pageSize:12,pageList:[12,20,50],checkbox:true">
			<thead>
				<tr>
				    <th data-options="field:'ck',checkbox:true,width:50"></th>
				    <th data-options="field:'deviceName',width:100,align:'center'">设备名称</th>
					<th data-options="field:'classType',width:100,align:'left',formatter:function(value,row,index){return BasePage.DeviceTypeformater(value);}">设备类型</th>
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
		<input id="openType" type="hidden" value="addMap"></input>
		<div   id="mapContainer"style="width: 100%; height: 100%"></div>
	</div>
</body>
</html>
