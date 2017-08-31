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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/updDeviceItem.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jquery-easyui-1.5/themes/color.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/logout.js"></script> 
</head>
<body id="updDeviceItemBody" class="easyui-layout"  > 
	 <div data-options="region:'north', border:true" style="height:150px;">
	         <table id="top" style="width: 100%; height: 50px; border: #F3F3F3; background-color: #FCFCFC">
				<tr style="padding-top: 5px">
					<td align="center"
						style="widht: 100px; border-right: #EEEEEE 1px solid;">您有 <span
						class="span-red" id="newMorder" style="color:red">0 </span> 新报修未处理
					</td>
					<td><input class="easyui-searchbox"    id="roleNamePara"
						style="background-color: #BABABA; width: 500px; height: 35px; padding-left: 20px" />
					</td>
					<td align="right" style="width: 250px; border-left: #EEEEEE 1px solid;">
					 当前用户：<%=session.getAttribute("LOGIN_NAME") %>
					       <a onclick="signout()"id="sysLogoutBtn"href="#" class="easyui-linkbutton c5" style="width:80px">退出</a>
				</td>
				</tr>
		 </table>
			 <div id="toolbar" class="toolbar">
			 <!-- 返回 -->
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
			     <input type="hidden"  id="hidden_updDate" name="updDate"   type="hidden"  />
			 </div>
		     <div style="padding:10px 0 0 10px">
					<table  >
		             <tr>
                           <td>订单编号</td>
                           <td colspan="3"><input id="oid" value="<c:out value='${updObj.orderNo}'/>"     class="easyui-textbox"   disabled="disabled"  style="height:30px;width:150px;"/></td>
                           <td>设备类别</td>
                           <td><input id="deviceType"  type="text"   class="easyui-textbox"  disabled="disabled"   style="height:30px;width:150px;" /></td>
                           <td>设备编号</td>
                            <td><input id="deviceItemID"  type="text"   class="easyui-textbox"  disabled="disabled"   style="height:30px;width:150px;" /></td>
                           <td>设备名称</td>
                            <td><input id="deviceItemName"  type="text"   class="easyui-textbox"  disabled="disabled"  style="height:30px;width:150px;" /></td>
                             <td>设备标识</td>
                           <td><input id="deviceItemUid"  type="text"   class="easyui-textbox"   style="height:30px;width:150px;" /></td>
                         </tr>
                       </table>
		  </div>
	</div>
	
	<div data-options="region:'center',split:false"  >
		<div style="height:100%">
			<table class="easyui-datagrid" style="width:auto;height:auto"  id="deviceItemPropertyTable" 
			data-options="singleSelect:true,collapsible:false,fit:true,pagination:true,rownumbers:true,pageSize:12,pageList:[12,20,50],checkbox:true">
			<thead>
				<tr>
				    <th data-options="field:'ck',checkbox:true,width:50"></th>
				    <th data-options="field:'deviceTypePropertyName',width:200,align:'center'">属性名</th>
				    <th data-options="field:'deviceItemPropertyValue',width:200,align:'center',editor:'text'">属性值</th>
				</tr>
			</thead>
		   </table>
	   </div>
	</div>
	<form id="retrnManageForm"action="<%=request.getContextPath()%>/deviceItemController.do?method=returnManagePage"  id="isform" method="post">
	</form>	 
</body>
</html>
