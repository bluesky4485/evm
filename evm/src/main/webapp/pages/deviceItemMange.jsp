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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/icon.css"/>

<!--自定义文件-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.showLoading.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/BasePage.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/Util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/deviceItemMange.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/logout.js"></script>
</head>
<body id="deviceItemManageBody" class="easyui-layout"  > 
	 <div data-options="region:'north', border:true" style="height: 160px;">
	          <table id="top" style="width: 100%; height: 50px; border: #F3F3F3; background-color: #FCFCFC">
				<tr style="padding-top: 5px">
					<td align="center"
						style="widht: 100px; border-right: #EEEEEE 1px solid;">您有 <span
						class="span-red" id="newMorder" style="color:red">0 </span> 新报修未处理
					</td>
					<td><input class="easyui-searchbox"   id="deviceItemNamePara"
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
			    <div  id="delDeviceItemBtn">
					    <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_del.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_del_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_del.png'"/>
				</div>
				<!-- 删除-->
			    <div  id="addDeviceItemBtn">
				    <a id="addDeviceItemHref" href="<%=request.getContextPath()%>/pages/addDeviceItem.jsp">
						    <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_add.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_add_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_add.png'"/>  
					</a>
				</div>
				<!-- 修改-->
			   <div  id="updDeviceItemBtn">
					 <a id="updDeviceItemHref" href="#">
					     					        <img onclick=""  src="<%=request.getContextPath()%>/images/web_button_edit.png"  onMouseOver="this.src='<%=request.getContextPath()%>/images/web_button_edit_on.png'" onMouseOut="this.src='<%=request.getContextPath()%>/images/web_button_edit.png'"/>
					 </a>  
				</div>
				<div  id="desc" style="float:left;;background-color:#060192;color:white;font-size:18px;width:180px;height:20px;padding-left:30px;padding-top:10px">设备信息管理</div>
			 </div>
		      <div  id="para_div"  style ="padding-top:10px">
		          <table  >
		             <tr>
                           <td>订单编号</td>
                           <td colspan="3"><input id="oid"  class="easyui-textbox"  style="height:30px;width:150px;"/></td>
                           <td>设备类型名称</td>
                           <td><input id="deviceType"  type="text"   class="easyui-textbox"  style="height:30px;width:150px;" /></td>
                           <td> <a id="doSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a></td>
                         </tr>
                       </table>
		      </div>
	</div>
	
	<div data-options="region:'center',split:false"  >
		<div style="height:100%">
			<table class="easyui-datagrid" style="width:auto;height:auto"  id="deviceItemTable" 
			data-options="singleSelect:true,collapsible:false,fit:true,pagination:true,rownumbers:true,pageSize:12,pageList:[12,20,50],checkbox:true">
			<thead>
				<tr>
				    <th data-options="field:'ck',checkbox:true,width:50"></th>
				    <th data-options="field:'orderNo',width:200,align:'center'">订单编号</th>
				    <th data-options="field:'deviceItemId',width:100,align:'center'">设备ID</th>
				    <th data-options="field:'deviceItemName',width:200,align:'center',formatter:function(value,row,index){return row['deviceTypeName']+row['deviceItemId'];}">设备名称</th>
				    <th data-options="field:'deviceTypeName',width:200,align:'center'">设备类型名称</th>
					<th data-options="field:'deviceTypeClassType',width:180,align:'left',formatter:function(value,row,index){return BasePage.DeviceTypeformater(value);}">设备分类</th>
					<th data-options="field:'deviceItemUid',width:100,align:'center'">设备标识</th>
				</tr>
			</thead>
		   </table>
	   </div>
	</div>
		 
</body>
</html>
