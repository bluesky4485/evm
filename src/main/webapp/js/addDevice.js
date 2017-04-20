$(document).ready(function() {
	BasePage.BindDeviceTypeControl("deviceType");
	$("#add_property_window_OK").click(function(){
		var pName=$.trim($("#add_PropertyName").textbox("getValue"));
		if(pName.length>50){
			BasePage.showInfoMessage("属性名超长长度！");
			return ;
		}
		var para={};
		para["propertyName"] = pName;
		$('#propertyTable').datagrid('appendRow',para);
		$("#property_window").window("close");
	});
    $("#add_property_window_cancel").click(function(){
	   $("#property_window").window("close");
	});
    $("#addDeviceBtn").click(function(){
    	var deviceName=$.trim($("#deviceName").textbox("getValue"));
    	if(deviceName==""){
    		BasePage.showInfoMessage("设备名称不可为空！");
    		return ;
    	}else if(deviceName.length>50){
    		BasePage.showInfoMessage("设备名称长度超长！");
    		return ;
    	}
    	var deviceType=$("#deviceType").combobox("getValue"); 
    	var propertyTableData=$("#propertyTable").datagrid("getRows");
    	var para={};
    	para["allPropertys"]= JSON.stringify(propertyTableData);
    	para["deviceName"]= deviceName;
    	para["classType"]= deviceType;
    	BasePage.sendPostRequest('addDeviceBody','/deviceController.do?method=ajaxInsertDevice',para,function(data){
    		if(data.messageType=="error"){
				BasePage.showInfoMessage(data.message);
			}else{
				alert("保存成功！");
				$("#retrnManageForm").submit();
			}
		});
    });
    $("#goHist").click(function(){
		$("#retrnManageForm").submit();
	});
});
var toolbar = [{
    text:'添加属性',
    iconCls:'icon-add',
    handler:function(){addProperty();}
},{
    text:'删除属性',
    iconCls:'icon-cut',
    handler:function(){deleteProperty();}
}];
function addProperty(){
	 $("#add_PropertyName").textbox("setValue","");
	 $("#property_window").window("open");
}
function deleteProperty(){
	var row = $("#propertyTable").datagrid('getSelected');
	var rowIndex=$("#propertyTable").datagrid('getRowIndex');
	$("#propertyTable").datagrid('deleteRow',rowIndex);
	 
}