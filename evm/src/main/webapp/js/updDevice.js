$(document).ready(function() {
	BasePage.BindDeviceTypeControl("deviceType");
	
	$("#add_property_window_OK").click(function(){
		var pName=$("#add_PropertyName").textbox("getValue");
		if(pName.length>50){
			BasePage.showInfoMessage("属性名超长长度！");
			return ;
		}
		var para={};
		para["propertyName"] = pName;
		para["propertyId"] = "";
		$('#propertyTable').datagrid('appendRow',para);
		$("#property_window").window("close");
	});
    $("#add_property_window_cancel").click(function(){
	   $("#property_window").window("close");
	});
    init();
    function init(){
    	var deviceId=$("#deviceId").val();
		var para={};
		para["deviceId"]=deviceId;
		BasePage.sendPostRequest('updDeviceBody','/deviceController.do?method=ajaxGetDeviceById',para,function(data){	
			if(data!= null){
				var updObj=data.bizData;
				$("#hidden_updDate").val(updObj["updDate"]);
				$("#deviceName").textbox('setValue',updObj["deviceName"]);
		    	$("#deviceType").combobox('setValue',updObj["classType"]);
		    	$('#propertyTable').datagrid('loadData',updObj.properties);
			}else{
				BasePage.showInfoMessage("获得更新数据异常");
			}
		});
    	
    }
    //保存
    $("#updDeviceBtn").click(function(){
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
    	if(propertyTableData.length>0){
    		para["allPropertys"]= JSON.stringify(propertyTableData);
    	}
    	para["deviceId"]= $("#deviceId").val();
    	para["deviceName"]= deviceName;
    	para["classType"]= deviceType;
    	para["updDate"]=$("#hidden_updDate").val();
    	BasePage.sendPostRequest('updDeviceBody','/deviceController.do?method=ajaxUpdateDevice',para,function(data){	
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
	var para={};
	para["propertyId"]=row["propertyId"];
	BasePage.sendPostRequest('updDeviceBody','/deviceController.do?method=ajaxDeleteDevicePropertyByID',para,function(data){	
		if(data.bizData==1){
			BasePage.showInfoMessage("保存成功！");
			$("#propertyTable").datagrid('deleteRow',rowIndex);
		}else{
			BasePage.showInfoMessage(data.message);
		}
	});
	
	 
}