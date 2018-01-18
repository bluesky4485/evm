<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/spring.tld" prefix="spring"%>
<%@ page isELIgnored="false" %> 
<jsp:useBean id="builder" class="org.evm.biz.order.common.OrderNoBuilder"></jsp:useBean>
 <%@ page import="org.evm.biz.user.entity.UserVO" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/gray/easyui.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/icon.css">
<!--自定义文件-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">

<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.showLoading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BasePage.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BaseMap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.jqprint-0.3.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/Util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/addOrder.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/projectUtil.js"></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/deviceProjectUtil.js"></script> --%>
<script src="http://webapi.amap.com/maps?v=1.3&key=e4a4a2fd139f9535810830feefddf38f&&plugin=AMap.OverView,AMap.ToolBar"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jscript/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jscript/uploadify/upload_order_file.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jscript/uploadify/upload_order_completefile.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jscript/qrcode.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/orderDeviceUtil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/orderUtil.js"></script>
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/util/BizPageSt.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/logout.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.jqprint-0.3.js"></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.PrintArea.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery-migrate-1.2.1.min.js"></script>
</head>
<body id="addOrderBody" class="easyui-layout">
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
		      <!-- 返回 -->
		      <div  id="goHist">
			          <a href="#"  title="返回">
	                      <img onclick="javascript:history.back();"  src="<%=request.getContextPath()%>/images/web_button_back.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_back_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_back.png'"/>
	                 </a>
		      </div>
			<!--  保存新增 -->
			<div id="saveOrderBtn">
				<a href="#" title="保存"> 
				 <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_tj.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_tj_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_tj.png'"/>
				</a>
			</div>
			<div id="printOrderBtn">
			  <a href="#"> 
				 <img  onclick=""  src="<%=request.getContextPath()%>/images/web_button_print.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_print_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_print.png'"/>
				</a>
			</div>
			<div  id="desc" style="float:left;;background-color:#060192;color:white;font-size:18px;width:180px;height:20px;padding-left:30px;padding-top:10px">创建订单</div>
		</div>
		<div id="hidden_div">
			         <input type="hidden" id="workPmId" name="workPmId" value="0" /><!--  施工负责人ID-->
			         <input type="hidden" id="storePmId" name="storePmId"value="0"  /><!--出库联系人 -->
			         <input type="hidden" id="checkerId" name="checkerId"  value="0" /><!--内检人员 -->
			         <!-- <input type="hidden" id="lat" name="lat"  value="0" /> 
			         <input type="hidden" id="lng" name="lng"  value="0" />  -->
		</div>
		<div id="projectEdit" style="background-color: #f3f3f3">
			<table >
			  <tr>
			     <td style="padding-left:20px">项目编号<span class="redStar">*</span></td>
			     <td><input id="projectid" class="easyui-combobox"  data-options=" panelHeight:'120',valueField:'projectId',textField:'projectNo'" style="width:200px;height:42px"/></td>
			     <td>项目名称<span class="redStar">*</span></td>
			     <td><input id="projectName" class="easyui-textbox"  disabled="disabled"   style="width:200px;height:42px"/></td>
			     <td><div style="float:left;padding-left:30px">录入员： <%=session.getAttribute("LOGIN_NAME") %> </div>
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
                           <td>订单编号</td>
                           <td colspan="3"><input id="orderNo"  disabled="disabled"   value="<%=builder.BuildOrderNo()%>"  class="easyui-textbox"  style=" width:100%;background-color: #BABABA"/></td>
                           <td>项目经理</td>
                           <td><input id="pmName"  type="text" disabled="disabled"   class="easyui-searchbox"  data-options="prompt:'搜索用户信息'" /></td>
                         </tr>
                         <tr>
                           <td rowspan="3">订单建码</td>
                           <td  rowspan="3"> <div style="background-color: red;width:100px"id="qrcode" ></div></td>
                            <td>客户编号</td>
                           <td><input id="cusId"   type="text"  disabled="disabled"  class="easyui-textbox"   /></td> 
                           <td>项目经理电话1</td>
                           <td><input id="pmTel1"  disabled="disabled"  type="text" class="easyui-textbox" /></td>
                           
                           </tr>
                         <tr>
                           <td>客户名称</td>
                            <td><input id="cname"  disabled="disabled"  type="text" class="easyui-textbox"  /></td>
                           <td>项目经理电话2</td>
                           <td><input id="pmTel2"  disabled="disabled"  type="text" class="easyui-textbox" /></td>
                         </tr>
                         
                         <tr>
                           <td>客户类型 </td>
                           <td><input id="utype"  disabled="disabled"  type="text" class="easyui-combobox"  data-options=" panelHeight:'auto',valueField:'tvalue',textField:'tname'"/></td>
                           <td>项目经理性别</td>
                           <td><input id="pmSex"  disabled="disabled" type="text" class="easyui-combobox"  data-options=" panelHeight:'auto',valueField:'id',textField:'text'"/></td>
                          </tr>
                         <tr>
                           <td>客户电话1</td>
                           <td><input  id="ctel1"  disabled="disabled"  type="text"  class="easyui-textbox" /></td>
                           <td>客户电话2</td>
                            <td><input id="ctel2" disabled="disabled"  type="text"  class="easyui-textbox" /></td>
                           <td>项目经理<br/>身份证号</td>
                           <td><input id="pmidCard"  disabled="disabled"  type="text" class="easyui-textbox"  /></td>
                        </tr> 
                         <tr>
                           <td>客户地址</td>
                           <td colspan="3">
	                           <input  id="caddress"  disabled="disabled"  type="text" class="easyui-textbox" style="width:300px" />
	                           <a id="addCusMap" href="#" class="easyui-linkbutton">地图</a>
	                           <input id="cusAddrLat" type="hidden" value="0"></input>
                               <input id="cusAddrLng" type="hidden" value="0"></input> 
                           </td>
                          <td rowspan="6">备注</td>
                           <td rowspan="6"><input id="projectRemark"  disabled="disabled"  multiline="true"  type="text" class="easyui-textbox"  style="width: 100%; height: 240px"/></td>
                         </tr> 
                         <tr>
                           <td>所属分局</td>
                           <td><input  id="substation"  disabled="disabled"  type="text" class="easyui-textbox" /></td>
                           <td>所属派出所</td>
                           <td><input  id="policestation"  disabled="disabled"  type="text" class="easyui-textbox" /></td>
                         </tr>
                         <tr>
                           <td>缴费类型</td>
                           <td><input  id="payType"  disabled="disabled"  type="text"   class="easyui-textbox" /></td>
                           <td>合同金额</td>
                           <td><input  id="contractSum" disabled="disabled"  type="text" class="easyui-textbox" /></td>
                         </tr>
                          <tr>
                           <td>年服务费</td>
                           <td><input  id="pYearSum"  disabled="disabled"  type="text" class="easyui-textbox" /></td>
                           <td>缴费日期</td>
                           <td><input  id="payDate" disabled="disabled"  type="text" class="easyui-datebox" /></td>
                         </tr> 
                         <tr>
                           <td>服务截止日期</td>
                           <td><input  id="serviceEndDate"  disabled="disabled"  type="text" class="easyui-datebox" data-options="editable:false" /></td>
                           <td>入网日期</td>
                           <td><input  id="joinDate"  disabled="disabled"  type="text" class="easyui-datebox" data-options="editable:false" /></td>
                         </tr>
                         <tr>
                           <td>停机日期</td>
                           <td><input  id="stopDate"  disabled="disabled"  type="text" class="easyui-datebox" data-options="editable:false" /></td>
                           <td>项目分类</td>
                           <td><input  id="projectType"  disabled="disabled"  type="text" class="easyui-combobox"  data-options=" panelHeight:'auto',valueField:'id',textField:'text',"/></td>
                         </tr>
		          </table>
              </div>
              <div title="施工信息" style="padding:10px">
                  <% UserVO currenUser=(UserVO)session.getAttribute("LOGIN_INFO");%>
                 <table class="input_table">
                          <tr>
                           <td>汇聚箱编号</td>
                           <td colspan="3"><input id="convergeBoxNo"  style=" width:100%;"  type="text" class="easyui-textbox" /></td>
                           <td rowspan="2"style="width:100px">
                               <div>施工图纸</div>
                               <div>
                                    <a id="archivesCemeteryFile" href="javascript:void(0)" class="easyui-linkbutton"  style="width:80px;height: 30px;" >上传图纸</a>
                               </div>
                             </td>
                           <td rowspan="9">
                                  <input type="hidden" id="worKDocIDs"  value="" />
                                 <div id=worDocDiv></div>
                            </td>
                         </tr>
                         <tr>
                           <td>施工负责人</td>
                           <td><input id="workManager"  type="text" class="easyui-searchbox"    style="width:100%" data-options="editable:false"
                                   <% if(currenUser!=null&&!currenUser.hasDispatchWorkPm()){%>
                                	   disabled="disabled" 
                                     <%    }%>
                                   />
                           </td>
                           <td>施工人数</td>
                           <td><input id="workers" type="text" class="easyui-numberbox" data-options="min:0,max:10000" value="0" 
                                   <% if(currenUser!=null&&!currenUser.hasDispatchWorkPm()){%>
                                	   disabled="disabled" 
                                     <%    }%>
                             />
                            </td>
                           
                         </tr>
                        
                         <tr>
                           <td>施工负责人<br/>电话1</td>
                           <td><input id="workManageTel1" disabled="disabled"  type="text" class="easyui-textbox" /></td>
                           <td>施工负责人<br/>电话2</td>
                           <td><input id="workManageTel2"  disabled="disabled" type="text" class="easyui-textbox" /></td>
                            <td  rowspan="7"> 
                         </tr>
                         <tr>
                           <td>施工地点</td>
                           <td colspan="3"><input id="workAddress"type="text" class="easyui-textbox" style="width: 300px"/><a id="workCusMap" href="#" class="easyui-linkbutton">地图</a></td>
                                                  
                         </tr>
                         <tr>
                           <td>经度</td>
                           <td><input id="lng"    type="text" class="easyui-textbox" value="0" /></td>
                           <td>纬度</td>
                           <td><input id="lat"    type="text" class="easyui-textbox" value="0" /></td>
                         </tr>
                         <tr>
                           <td>施工单位</td>
                           <td><input id="workCompany"type="text" class="easyui-textbox" /></td>
                           <td>单位资质情况</td>
                           <td><input id="workCompanyQualified" type="text" class="easyui-textbox" /></td>
                         </tr>
                         <tr>
                           <td>施工类型</td>
                           <td><input id="workType"  type="text"  class="easyui-combobox"  data-options=" panelHeight:'auto',valueField:'id',textField:'text',editable:false"/></td>
                           <td>施工天数</td>
                           <td><input id="workDays"type="text" class="easyui-numberbox" data-options="min:0,max:10000" value="0"/></td>
                         </tr>
                         <tr>
                           <td>计划施工时间</td>
                           <td><input id="workBeginDate" type="text" class="easyui-datebox"    data-options="editable:false" /></td>
                           <td>计划完工时间</td>
                           <td><input id="workPlanEndDate"type="text" class="easyui-datebox" data-options="editable:false" /></td>
                         </tr>
                         <tr>
                           <td>出库联系人</td>
                           <td><input id="outStoreContacter"  type="text"  class="easyui-searchbox"   style="width:100%"  data-options="editable:false"/> </td>
                           <td>计划出库时间</td>
                           <td><input id="planOutStoreDate"type="text" class="easyui-datebox" data-options="editable:false" /></td>
                           </tr>
                         <tr>
                           <td>出库联系人电话</td>
                           <td><input id="outStoreContacterTel"  disabled="disabled"  type="text"  class="easyui-textbox"  /></td>
                           <td>施工状态</td>
                           <td><input id="workStatus"  type="text"  class="easyui-combobox"  data-options=" panelHeight:'auto',valueField:'id',textField:'text',editable:false"
                                    <% if(currenUser!=null&&!currenUser.hasDispatchWorkPm()){%>
                                	   disabled="disabled" 
                                     <%    }%>
                                    />
                           </td>
                           <td rowspan="2">
                               <div>竣工资料</div>
	                           <div>
	                             <input type="button" id="archivesOrderFile" name="archivesOrderFile" class="button-z" value="上传" />
	                           </div>
                           </td>
                           <td rowspan="7" style="width:300px">
                                  <div style="padding-top:1px" id=completeDocDiv></div>
                           </td>
                        
                         </tr> 
                         <tr>
                            <td>实际施工时间</td>
                           <td><input id="realWorkBeginDate" type="text" class="easyui-datebox" data-options="editable:false" /></td>
                           <td>实际施工<br/>完成时间</td>
                           <td><input id="realWorkEndDate"  type="text" class="easyui-datebox" data-options="editable:false" /></td>
                         </tr>
                         <tr>
                          <td>施工进度</td>
                           <td><input id="workProgress"  value="0" type="text" data-options="min:0,max:100" class="easyui-numberbox"  /></td>
                            <td>最后一次<br/>施工更新时间</td>
                          <td><input id="lastWorkUpdDate"  type="text" class="easyui-datebox"    data-options="editable:false"
                                   <% if(currenUser!=null&&!currenUser.hasDispatchWorkPm()){%>
                                	   disabled="disabled" 
                                     <%    }%>
                                  />
                          </td>
                          
                          <td rowspan="5"></td>
                         </tr>
                         <tr>
                           <td>内检人员</td>
                            <td><input id="checkPerson"  type="text"  class="easyui-searchbox"  style="width:100%"   data-options="editable:false"/></td>
                            <td>施工内检结果</td>
                           <td><input id="checkResult"      type="text"  class="easyui-combobox"  data-options=" panelHeight:'auto',valueField:'id',textField:'text',editable:false"
                                   <% if(currenUser!=null&&!currenUser.hasDispatchCheckResult()){%>
                                	   disabled="disabled" 
                                     <%    }%>
                                   />
                           </td>
                         </tr>
                         <tr>
                          <td>内检时间</td>
                           <td ><input id="checkDate"  type="text" class="easyui-datebox" data-options="editable:false" /></td>
                             <td>用户评分</td>
                           <td><input id="userRating"  type="text"  class="easyui-numberbox" data-options="min:0,max:100" value="0"/></td>
                         </tr>
                         <tr>
                         
                           <td>用户评价</td>
                           <td colspan="3"><input id="userAppraise"  type="text" class="easyui-textbox" multiline="true"  style="width: 400px;height: 50px"/></td>
                         </tr>
                         <tr>
                           <td>备注</td>
                           <td colspan="3"><input type="text" id="workRemark" class="easyui-textbox"  multiline="true"  style="width: 400px;height: 50px"/></td>
                         </tr>        
		          </table>
              </div>
              <div title="设备相关信息"  >
                  <input id="deviceWindowIndex" type="hidden" value="0"/>
                  <div class="easyui-accordion" data-options="multiple:true" style="height:400px;width:100%">
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
							                  <th data-options="field:'deviceCnt',width:100">设备总数</th>
							                <th data-options="field:'planCnt',width:100">可领设备数量</th>
							                <th data-options="field:'storeCnt',width:100">已安装设备数量</th>
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
							                  <th data-options="field:'deviceCnt',width:100">设备总数</th>
							                <th data-options="field:'planCnt',width:100">可领设备数量</th>
							                <th data-options="field:'storeCnt',width:100">已安装设备数量</th>
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
				                        ddeviceDelete(3);
				                    }
				                }]">
				                <table id="helpDeviceDg" style="height:300px"  class="easyui-datagrid" data-options="singleSelect:false,collapsible:false,fit:true,fitColumns:true">
				                  <thead>
						              <tr>
							                <th data-options="field:'deviceName',width:300">设备名称</th>
							                  <th data-options="field:'deviceCnt',width:100">设备总数</th>
							                <th data-options="field:'planCnt',width:100">可领设备数量</th>
							                <th data-options="field:'storeCnt',width:100">已安装设备数量</th>
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
				           <input   class="easyui-searchbox" data-options="prompt:'搜索设备项'" style="height:30px;width:100%;background-color: #BABABA"/>
				         </div>
				        <div data-options="region:'center',split:false,border:true,collapsible:false">
					        <table  id="addDeviceDg"  title="未选设备" class="easyui-datagrid" data-options="singleSelect:false,collapsible:false,fit:true">
					            <thead>
					              <tr>
					                    <th data-options="field:'ck',checkbox:true,width:50"></th>
						                <th data-options="field:'deviceName',width:80">设备名称</th>
						              <!--   <th data-options="field:'classType',width:80,formatter:function(value,row,index){return BasePage.DeviceTypeformater(value);}">设备类型</th> -->
						                 <th data-options="field:'opt',width:80,formatter:function(value,row,index){return doOptFormat(value,row,index);}">设备总数</th>
						                 <th data-options="field:'planCnt',width:80,formatter:function(value,row,index){return doPlanFormat(value,row,index);}">可领设备数量</th>
						                <!--  <th data-options="field:'storeCnt',width:80,formatter:function(value,row,index){return doWorkFormat(value,row,index);}">已安装数量</th> -->
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
					           <input  id="deviceSearch" class="easyui-searchbox" data-options="prompt:'搜索设备项'" style="height:30px;width:100%;background-color: #BABABA"/>
					         </div>
					        <div data-options="region:'center',split:false,border:true,collapsible:false">
						        <table id="unAddDeviceDg" title="已选设备"  class="easyui-datagrid" data-options="singleSelect:false,collapsible:false,fit:true" >
						            <thead>
						              <tr>
						                    <th data-options="field:'ck',checkbox:true,width:50"></th>
							                <th data-options="field:'deviceName',width:130">设备名称</th>
							                <th data-options="field:'classType',width:130,formatter:function(value,row,index){return BasePage.DeviceTypeformater(value);}">设备类型</th>
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
				    <input id="userSearch"   class="easyui-searchbox"  style="height:30px;width:100%;background-color: #BABABA"/>
		       </div>
		        <div data-options="region:'center',split:false,border:true,collapsible:false">
			        <table id="userDg"class="easyui-datagrid" style="width:auto;height:auto"   
						data-options="singleSelect:true,collapsible:false,fit:true,pagination:true,rownumbers:true,pageSize:12,pageList:[12,20,50],checkbox:true">
						<thead>
							<tr>
							       <th data-options="field:'ck',checkbox:true,width:50"></th>
							    <th data-options="field:'uid',width:60,align:'center'">用户编号</th>
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
	<form id="gotoManageForm" action="<%=request.getContextPath()%>/orderController.do?method=returnManagePage"  id="isform" method="post">
		</form>
</body>
</html>
