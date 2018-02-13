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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/gray/easyui.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/icon.css"/>
<!--自定义文件-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.showLoading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BasePage.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BaseMap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/Util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/addProject.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/deviceUtil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/deviceProjectUtil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/projectUtil.js"></script>
<script src="http://webapi.amap.com/maps?v=1.3&key=e4a4a2fd139f9535810830feefddf38f&&plugin=AMap.OverView,AMap.ToolBar"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/BizPageSt.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/logout.js"></script>
</head>
<body id="addProjectBody" class="easyui-layout">
	<div data-options="region:'north', border:true" style="height: 218px;">
	  <table id="top" style="width: 100%; height: 50px; border: #F3F3F3; background-color: #FCFCFC">
			<tr style="padding-top: 5px">
				<td align="center"
					style="widht: 100px; border-right: #EEEEEE 1px solid;">您有 <span
					class="span-red" id="newMorder" id="newMorder" style="color:red">0 </span> 新报修未处理
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
		    <!-- 返回 -->
		      <div id="goHist">
			          <a href="#"  title="返回">
	                      <img onclick="javascript:history.back(-1);"  src="<%=request.getContextPath()%>/images/web_button_back.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_back_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_back.png'"/>
	                 </a>
		      </div>
			<!--  保存新增 -->
			<div id="saveProjectBtn">
				<a href="#" title="保存"> 
				       <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_tj.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_tj_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_tj.png'"/>
				</a>
			</div>
			<div  id="desc" style="float:left;;background-color:#060192;color:white;font-size:18px;width:180px;height:20px;padding-left:30px;padding-top:10px">创建项目</div>
		</div>
		<div id="hidden_div">
			         <input type="hidden" id="cusId" name="cusId" />
			         <input type="hidden" id="pmId" name="pmId" />
			         <input type="hidden" id="cmId" name="cmId" />
			</div>
		<div id="projectEdit" style="background-color: #f3f3f3">
			<table >
			  <tr>
			     <td style="padding-left:20px">项目编号<span class="redStar">*</span></td>
			     <td><input id="projectNo"  disabled="disabled"  class="easyui-textbox"  style="width:200px;height:42px"></td>
			     <td>项目名称<span class="redStar">*</span></td>
			     <td><input id="projectName"  class="easyui-textbox"  style="width:200px;height:42px"></td>
			     <td><div style="float:left;padding-left:30px">录入员：测试人员 </div>
			         <div style="float:left;padding-left:30px">创建日期：<span id="currentDate"></span></div></td>
			  </tr>
			</table>
		</div>
	</div>
	<div data-options="region:'center', border: true">
		  <div class="easyui-tabs" style="width:100%;height:100%">
		      <div title="客户信息" style="padding:10px">
		          <table class="input_table">
		           <tr>
                           <td>客户名称</td>
                           <td><input id="cname"  class="easyui-searchbox" data-options="prompt:'搜索客户信息',editable:false" style="width:100%;background-color: #BABABA"/></td>
                           <td>客户地址</td>
                           <td><input id="caddress" disabled="disabled"  type="text" class="easyui-textbox" />
                               <a id="addCusMap" href="#" class="easyui-linkbutton">地图</a>
                               <input id="cusAddrLat" type="hidden" value="0"></input>
                               <input id="cusAddrLng" type="hidden" value="0"></input>                           
                           </td>
                           <td>客户电话</td>
                           <td><input id="ctel1" disabled="disabled"  type="text" class="easyui-textbox" /></td>
                         </tr>
                         <tr>
                           <td>所属分局</td>
                           <td><input id="substation" disabled="disabled"  type="text" class="easyui-textbox" /></td>
                           <td>所属派出所</td>
                           <td><input id="policestation" disabled="disabled"  type="text" class="easyui-textbox" /></td>
                           <td>固定电话 </td>
                           <td><input id="ctel2" disabled="disabled"  type="text" class="easyui-textbox" /></td>
                         </tr>
                         <tr>
                           <td>客户经理</td>
                           <td><input id="cmName" type="text"  class="easyui-searchbox"  data-options="prompt:'搜索用户信息',editable:false" /></td>
                           <td>客户经理电话</td>
                           <td><input id="cmTel"  disabled="disabled"  type="text" class="easyui-textbox" /></td>
                           <td>客户经理性别</td>
                           <td><input id="cmSex" disabled="disabled" type="text" class="easyui-combobox" data-options=" panelHeight:'auto',valueField:'id',textField:'text',editable:false"/></td>
                         </tr>
                         
                         <tr>
                           <td>项目经理</td>
                           <td><input id="pmName" type="text"  class="easyui-searchbox"  data-options="prompt:'搜索用户信息',editable:false" /></td>
                            <td>项目经理电话</td>
                           <td><input id="pmTel"  disabled="disabled"  type="text" class="easyui-textbox" /></td>
                           <td>项目经理性别</td>
                           <td><input id="pmSex"  disabled="disabled"  type="text" class="easyui-combobox" data-options=" panelHeight:'auto',valueField:'id',textField:'text'"/></td>
                          </tr>
                         <tr>
                           <td>项目分类</td>
                           <td><input id="projectType" type="text" class="easyui-combobox"  data-options=" panelHeight:'auto',valueField:'id',textField:'text',editable:false"/></td>
                           <td>关联用户</td>
                           <td colspan="3"><input id="relUsers"    type="text"  style="width:300px"  class="easyui-searchbox"  data-options="prompt:'搜索用户信息',editable:false" /></td>
                         
                         </tr>
		          </table>
              </div>
              <div title="合同信息" style="padding:10px">
                 <table class="input_table">
                         <tr>
                           <td>合同编号<span class="redStar">*</span></td>
                           <td><input id="contractId"type="text" class="easyui-textbox"  style="height:100%"/></td>
                           <td>签订日期</td>
                           <td><input id="signDate" type="text" class="easyui-datebox" data-options="editable:false" /></td>
                           <td>合同金额</td>
                           <td><input id="contractSum" type="text" class="easyui-numberbox" precision="2" value="0.00"/></td>
                         </tr>
                        
                         <tr>
                           <td>缴费类型</td>
                           <td><input id="payType"type="text" class="easyui-textbox" /></td>
                           <td>工期截止日期</td>
                           <td><input id="pEndDate" type="text" class="easyui-datebox" data-options="editable:false"/></td>
                           <td>开工日期</td>
                           <td><input  id="pStartDate"type="text" class="easyui-datebox" data-options="editable:false"/></td>
                         </tr>
                         <tr>
                           <td>年服务费</td>
                           <td><input id="pYearSum"type="text" class="easyui-numberbox" precision="2" value="0.00" /></td>
                           <td>服务截止日期</td>
                           <td><input id="serviceEndDate" type="text" class="easyui-datebox" data-options="editable:false"/></td>
                           <td>缴费日期</td>
                           <td><input id="payDate"type="text" class="easyui-datebox" data-options="editable:false"/></td>
                         </tr>
                         <tr>
                           <td>建设方式</td>
                           <td><input type="text" id="buildType" class="easyui-combobox"  data-options=" panelHeight:'auto',valueField:'id',textField:'text',editable:false"/></td>
                           <td>入网日期</td>
                           <td><input id="joinDate"type="text" class="easyui-datebox" data-options="editable:false"/></td>
                           <td>停机日期</td>
                           <td><input id="stopDate"type="text" class="easyui-datebox" data-options="editable:false"/></td>
                         </tr>
                         <tr>
                           <td>项目状态</td>
                           <td colspan="5"><input type="text" id="proStat" class="easyui-combobox"  data-options=" panelHeight:'auto',valueField:'id',textField:'text',editable:false"/></td>
                         </tr>
                         <tr>
                           <td>备注</td>
                           <td colspan="5"><input id="contractRemarks"class="easyui-textbox" multiline="true"  value="" style="width: 100%; height: 120px"></td></td>
                         </tr>      
		          </table>
              </div>
              <div title="设备相关信息"  >
                  <input id="deviceWindowIndex" type="hidden" value="0"/>
                  <div class="easyui-accordion" data-options="multiple:false" style="height:400px;width:100%">
                        <div title="前端设备" data-options=" 
				                tools:[{
				                    iconCls:'icon_device_add',
				                    handler:function(){
				                        deviceOpen(1);
				                    }
				                },{
				                    iconCls:'icon_device_delete',
				                    handler:function(){
				                        deviceDelete(1);
				                    }
				                }]">
				            <table id="deviceDg" style="height:300px"  class="easyui-datagrid" data-options="singleSelect:false,collapsible:false,fit:true">
				                  <thead>
						              <tr>
							                <th data-options="field:'deviceName',width:300">设备名称</th>
							                <th data-options="field:'deviceCnt',width:100">设备数量</th>
							            </tr>
						         </thead>
				            </table>
                         </div>
                          <div title="后端设备"  data-options=" 
				                tools:[{
				                    iconCls:'icon_device_add',
				                    handler:function(){
				                        deviceOpen(2);
				                    }
				                },{
				                    iconCls:'icon_device_delete',
				                    handler:function(){
				                        deviceDelete(2);
				                    }
				                }]">
				            <table id="lineDeviceDg" style="height:300px"  class="easyui-datagrid" data-options="singleSelect:false,collapsible:false,fit:true">
				                  <thead>
						              <tr>
							                 <th data-options="field:'deviceName',width:300">设备名称</th>
							                <th data-options="field:'deviceCnt',width:100">设备数量</th>
							            </tr>
						         </thead>
				            </table>
                         </div>
                           <div title="辅助材料" data-options=" 
				                tools:[{
				                    iconCls:'icon_device_add',
				                    handler:function(){
				                        deviceOpen(3);
				                    }
				                },{
				                    iconCls:'icon_device_delete',
				                    handler:function(){
				                        deviceDelete(3);
				                    }
				                }]">
				                <table id="helpDeviceDg" style="height:300px"  class="easyui-datagrid" data-options="singleSelect:false,collapsible:false,fit:true,fitColumns:true">
				                  <thead>
						              <tr>
							                <th data-options="field:'deviceName',width:300">设备名称</th>
							                <th data-options="field:'deviceCnt',width:100">设备数量</th>
							            </tr>
						         </thead>
				            </table>
                          </div>
                  </div>
              </div>
		  </div>
	</div>
	<div id="device_window" title="设备管理" class="easyui-window"
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
		style="width: 850px; height: 500px;">
		 <div class="easyui-layout" style="width:830px;height:460px;">
		        <div data-options="region:'center',split:false,border:true,collapsible:false" title="未选设备"  >
			        <div class="easyui-layout" style="width:410px;height:100%;">
			            <div data-options="region:'north',split:false,border:true,collapsible:false" style="height:32px">
				           <input id="selectedDeviceSearch"  class="easyui-searchbox" data-options="prompt:'搜索设备项'" style="height:30px;width:100%;background-color: #BABABA"/>
				         </div>
				        <div data-options="region:'center',split:false,border:true,collapsible:false">
					        <table  id="addDeviceDg"  title="未选设备" class="easyui-datagrid" data-options="singleSelect:false,collapsible:false,fit:true">
					            <thead>
					              <tr>
					                    <th data-options="field:'ck',checkbox:true,width:50"></th>
						                <th data-options="field:'deviceName',width:130">设备名称</th>
						                <th data-options="field:'classType',width:180,align:'left',formatter:function(value,row,index){return BasePage.DeviceTypeformater(value);}">设备类型</th>
						                <th data-options="field:'opt',width:120,formatter:function(value,row,index){return doOptFormat(value,row,index);}">设备数量</th>
						            </tr>
						         </thead>
					        </table>
				         </div>
					        <div data-options="region:'south',split:false,border:true,collapsible:false" style="height:35px">
					              <div id="deviceDelBtn" style="float:right;background-color:#060192;width:100px;height:33px;">
										<div style ="font-size:10px;color:white;"  >
											<div style="float:left;padding:8px 0 0 20px"><img src="<%=request.getContextPath()%>/images/web_icon_20.png" /></div> 
											<div style="float:left;padding:10px 0 0 5px">移 除</div>
										</div> 
									</div>
					        </div>
				    </div>
		        </div>
		        <div data-options="region:'west',split:false,border:true,collapsible:false" title="已选设备" style="width:410px;">
			           <div class="easyui-layout" style="width:405px;height:100%;">
				            <div data-options="region:'north',split:false,border:true,collapsible:false" style="height:32px">
					           <input  id="unSelectedDeviceSearch" class="easyui-searchbox" data-options="prompt:'搜索设备项'" style="height:30px;width:100%;background-color: #BABABA"/>
					         </div>
					        <div data-options="region:'center',split:false,border:true,collapsible:false">
						        <table id="unAddDeviceDg" title="已选设备"  class="easyui-datagrid" data-options="singleSelect:false,collapsible:false,fit:true" >
						            <thead>
						              <tr>
						                    <th data-options="field:'ck',checkbox:true,width:50"></th>
							                <th data-options="field:'deviceName',width:130">设备名称</th>
							                <th data-options="field:'classType',width:180,align:'left',formatter:function(value,row,index){return BasePage.DeviceTypeformater(value);}">设备类型</th>
							            </tr>
							         </thead>
						        </table>
					         </div>
					        <div data-options="region:'south',split:false,border:true,collapsible:false" style="height:35px">
					               <div id="deviceAddBtn" style="float:right;background-color:#060192;width:100px;height:33px;">
										<div style ="font-size:10px;color:white;"  >
											<div style="float:left;padding:8px 0 0 20px"><img src="<%=request.getContextPath()%>/images/web_icon_19.png" /></div> 
											<div style="float:left;padding:10px 0 0 5px">添  加</div>
										</div> 
									</div>
					        </div>
				    </div>
		        </div>
                 <div data-options="region:'south',split:false,border:false,collapsible:false" style="height:50px;">
		              <div style="float:right;margin:10px 20px 0 0 ">
		                     <a id="device_window_OK" href="javascript:void(0)" class="easyui-linkbutton"  style="width:100px;height: 30px;" onclick="$('#dlg').dialog('open')">确定</a>
                      		<a id="device_window_cancel" href="javascript:void(0)" class="easyui-linkbutton"   style="width:100px;height: 30px"onclick="$('#dlg').dialog('close')">取消</a>
		               </div>
		        </div>
        </div>
	</div>
	<div id="customer_window" title="客户信息" class="easyui-window"
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
		style="width: 850px; height: 500px;">
		    <div class="easyui-layout" style="width:830px;height:460px;">
		       <div data-options="region:'north',split:false,border:true,collapsible:false" style="height:32px">
				    <input id="customSearch"   class="easyui-searchbox" data-options="prompt:'客户信息'" style="height:30px;width:100%;background-color: #BABABA"/>
		       </div>
		        <div data-options="region:'center',split:false,border:true,collapsible:false">
			        <table id="customerDg"class="easyui-datagrid" style="width:auto;height:auto"  
						data-options="singleSelect:true,collapsible:false,fit:true,pagination:true,rownumbers:true,pageSize:12,pageList:[12,20,50],checkbox:true">
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
								<th data-options="field:'caddress',width:120,align:'center'">客户地址 </th>
								<th data-options="field:'substation',width:120,align:'center'">所属分局</th>
								<th data-options="field:'policestation',width:120">所属派出所</th>
							</tr>
						</thead>
					   </table>
		         </div>
		         <div data-options="region:'south',split:false,border:false,collapsible:false" style="height:50px;">
		              <div style="float:right;margin:10px 20px 0 0 ">
		                     <a id="customer_window_OK" href="javascript:void(0)" class="easyui-linkbutton"  style="width:100px;height: 30px;" onclick="$('#dlg').dialog('open')">确定</a>
                      		<a id="customer_window_cancel" href="javascript:void(0)" class="easyui-linkbutton"   style="width:100px;height: 30px"onclick="$('#dlg').dialog('close')">取消</a>
		               </div>
		        </div>
		    </div>
	</div>
	<div id="user_window" title="用户信息" class="easyui-window"
		data-options="		
			modal:false,
			draggable :true,
			resizable :false,
			closed :true,
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
								<th data-options="field:'ufullName',width:80,align:'center'">用户姓名</th>
								<th data-options="field:'utel1',width:80,align:'center'">用户电话1</th>
								<th data-options="field:'utel2',width:80,align:'center'">用户电话2</th>
								<th data-options="field:'usex',width:60,align:'center',formatter:function(value,row,index){return BasePage.Sexformater(value);}">用户性别</th>
								<th data-options="field:'uidCard',width:120,align:'center'">用户身份证号</th>
								<th data-options="field:'roleName',width:90">角色类型</th>
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
	<div id="rel_user_window" title="用户信息" class="easyui-window"
		data-options="		
			modal:false,
			draggable :true,
			resizable :false,
			closed :true,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			shadow : true,
			striped : true"
		style="width: 650px; height: 400px;">
	   <input id="selecteIdUserId" type="hidden" value=""/>
	   <div class="easyui-layout" style="width:100%;height:100%;">
	           <div data-options="region:'north',split:false,border:true,collapsible:false" style="height:32px">
				    <input id="relUserSearch"   class="easyui-searchbox" data-options="prompt:'搜索用户'" style="height:30px;width:100%;background-color: #BABABA"/>
		       </div> 
		        <div data-options="region:'center',split:false,border:true,collapsible:false">
			        <table id="relUserDg"class="easyui-datagrid" style="width:auto;height:auto"  
						data-options="singleSelect:false,collapsible:false,fit:true,pagination:false,rownumbers:true,pageSize:12,pageList:[12,20,50],checkbox:true,loadFilter:BasePage.pagerFilter">
						<thead>
							<tr>
							    <th data-options="field:'ck',checkbox:true,width:50"></th>
							    <th data-options="field:'uid',width:60,align:'center',hidden:true">用户编号</th>
								<th data-options="field:'ufullName',width:80,align:'center'">用户姓名</th>
								<th data-options="field:'utel1',width:80,align:'center'">用户电话1</th>
								<th data-options="field:'utel2',width:80,align:'center'">用户电话2</th>
								<th data-options="field:'usex',width:60,align:'center',formatter:function(value,row,index){return BasePage.Sexformater(value);}">用户性别</th>
								<th data-options="field:'uidCard',width:120,align:'center'">用户身份证号</th>
								<th data-options="field:'roleName',width:90">角色类型</th>
							</tr>
						</thead>
					   </table>
		         </div>
		         <div data-options="region:'south',split:false,border:false,collapsible:false" style="height:50px;">
		              <div style="float:right;margin:10px 20px 0 0 ">
		                     <a id="reluser_window_OK" href="javascript:void(0)" class="easyui-linkbutton"  style="width:100px;height: 30px;" onclick="$('#dlg').dialog('open')">确定</a>
                      		 <a id="reluser_window_cancel" href="javascript:void(0)" class="easyui-linkbutton"   style="width:100px;height: 30px"onclick="$('#dlg').dialog('close')">取消</a>
		               </div>
		        </div>
		         
	   </div>
	</div>
	<form id="gotoProjectManageForm" action="<%=request.getContextPath()%>/projectController.do?method=returnCustomManage"  id="isform" method="post">
	</form>
</body>
</html>
