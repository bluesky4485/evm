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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/icon.css"/>

<!--自定义文件-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.showLoading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BasePage.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/Util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/maintainOrderManage.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/logout.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/util/BizPageSt.js"></script> 
</head>
<body id="morderManageBody" class="easyui-layout"  > 
	 <div data-options="region:'north', border:true" style="height: 171px;">
	          <table id="top" style="width: 100%; height: 50px; border: #F3F3F3; background-color: #FCFCFC">
				<tr style="padding-top: 5px">
					<td align="center"
						style="widht: 100px; border-right: #EEEEEE 1px solid;">您有 <span
						class="span-red" id="newMorder" style="color:red">0 </span> 新报修未处理
					</td>
					<td><input class="easyui-searchbox"   id="morderNoPara"
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
				  <td class="redTd_class"  id="allNoDoMorderCntDiv" >
					 <div  class="td_img_div_class">
						 <img src="<%=request.getContextPath()%>/images/web_icon_09.png" />
					 </div>
					 <div> 
						  <div  class="td_digit_div_class">
							  <span style="font-size:30px;" id="allNoDoMorderCnt">0</span>
						 </div>
						  <div class="td_char_div_class">
							 <span style="font-size:8px;">未处理的运维订单</span>
						  </div>
					  </div>
				  </td>
				  <td  class="yellowTd_class"  id="noCallBackMorderCntDiv"> 
					   <div  class="td_img_div_class">
						 <img src="<%=request.getContextPath()%>/images/web_icon_10.png" />
					 </div>
					 <div> 
						  <div  class="td_digit_div_class">
							  <span style="font-size:30px;" id="noCallBackMorderCnt">0</span>
						 </div>
						  <div class="td_char_div_class">
							 <span style="font-size:8px;" >未回访的运维订单</span>
						  </div>
					  </div>
				  </td>
				  <td  class="blueTd_class"  id="doingMorderCntDiv" >
					   <div  class="td_img_div_class">
						 <img src="<%=request.getContextPath()%>/images/web_icon_11.png" />
					 </div>
					 <div> 
						  <div class="td_digit_div_class">
							  <span style="font-size:30px;" id="doingMorderCnt">0</span>
						 </div>
						  <div class="td_char_div_class">
							 <span style="font-size:8px;">运维中的运维订单</span>
						  </div>
					  </div>
				  </td>		   
				  <td  class="purpleTd_class" id="problemMorderCntDiv">
					 <div  class="td_img_div_class">
						 <img src="<%=request.getContextPath()%>/images/web_icon_12.png" />
					 </div>
					 <div> 
						  <div class="td_digit_div_class">
							  <span style="font-size:30px;" id="problemMorderCnt">0</span>
						 </div>
						  <div class="td_char_div_class">
							 <span style="font-size:8px;">全部运维订单</span>
						  </div>
					  </div>
				  </td>
				</tr> 	 
			 </table>
			 <div id="toolbar" class="toolbar">
			    <!-- 删除-->
			    <div  id="delMOrder">
			           <a id="deleteMOrderHref" href="#">
					   
					    <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_del.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_del_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_del.png'"/>
					   </a>
				</div>
				<!-- 新增-->
			    <div>
				     <a id="addMOrderHref" href="<%=request.getContextPath()%>/pages/addMaintainOrder.jsp">
				       
						    <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_add.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_add_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_add.png'"/>
					 </a>
				</div>
				<!-- 修改-->
			   <div  id="updMOrder">
					 <a id="updMOrderHref" href="#">
					        <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_edit.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_edit_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_edit.png'"/>
					 </a>  
				</div>
				<div  id="desc" style="float:left;;background-color:#060192;color:white;font-size:18px;width:180px;height:20px;padding-left:30px;padding-top:10px">运维订单管理</div>
			 </div>
			 <div id="hiden_div">
				 <input type="hidden"  id="queryStat"  value="" />
			</div>
	 </div>
	
	<div data-options="region:'center',split:false"  >
		<div style="height:100%">
			<table id="mOrderDg"  class="easyui-datagrid" style="width:auto;height:auto"  
			data-options="singleSelect:true,collapsible:false,fit:true,pagination:true,rownumbers:true,pageSize:12,pageList:[12,30,50],checkbox:true">
			<thead>
				<tr>
				    <th data-options="field:'ck',checkbox:true,width:50"></th>
				    <th data-options="field:'projectName',width:80,align:'center'">项目名称</th>
					<th data-options="field:'orderNo',width:150,align:'center'">订单编号</th>
					<th data-options="field:'morderNo',width:150,align:'center'">运维编号</th>
					<th data-options="field:'cusName',width:80,align:'center'">客户名称</th>
					<th data-options="field:'pmName',width:120,align:'center'">项目经理</th>
					<th data-options="field:'faultDesc',width:120,align:'center'">故障描述 </th>
					<th data-options="field:'maintainPmName',width:120,align:'center'">维修负责人</th>
					<th data-options="field:'maintainStatus',width:120,align:'center',formatter:function(value,row,index){return BasePage.WorkMaintainStatusformater(value);}">维修状态</th>
				<!-- 	<th data-options="field:'opts',width:120,align:'center',formatter:formatOper">操作</th> -->
				</tr>
			</thead>
		   </table>
	   </div>
	</div>
</body>
</html>