$(document).ready(function(){
	var bodyId="updDeviceItemBody";
	 var dg = $("#deviceItemPropertyTable").datagrid({
	        data: []
	    });
		dg.datagrid('enableCellEditing').datagrid('gotoCell', {
	        index: 0,
	        field: 'deviceItemPropertyValue'
	    });
	init();
	function init() {
		var deviceItemId=$("#deviceItemId").val();
		var para={};
		para["deviceItemId"]=deviceItemId;
		BasePage.sendPostRequest(bodyId,'/deviceItemController.do?method=ajaxFindDeviceItemById',para,function(data){	
			if(data== null || data.bizData!=null){
				init_callback(data.bizData);
			}else{
				BasePage.showInfoMessage("获得更新数据异常");
			}
		});
	}
	function init_callback(updObj){
		var deviceItemId=updObj.deviceItemId;
		$("#deviceItemID").textbox("setValue",deviceItemId);
		var deviceItemName=updObj.deviceTypeName+updObj.deviceItemId;;
		$("#deviceItemName").textbox("setValue",deviceItemName);
		var deviceTypeName=updObj.deviceTypeName;
		$("#deviceType").textbox("setValue",deviceTypeName);
		var propertyList=updObj.deviceItemPropertyList;
		$("#deviceItemPropertyTable").datagrid('loadData', propertyList);
		$("#hidden_updDate").val(updObj["updDate"]);
		var deviceItemUid=updObj.deviceItemUid;
		$("#deviceItemUid").textbox("setValue",deviceItemUid);
	}
	$("#saveBtn").click(function(){
		var rows=$("#deviceItemPropertyTable").datagrid("getRows");
		var deviceItemId=$("#deviceItemId").val();
		var para={};
		var deviceItemUid=$.trim($("#deviceItemUid").textbox("getValue"));
	    if(deviceItemUid.length!=0&&deviceItemUid.length>100){
	    	  BasePage.showInfoMessage("设备标识长度不合法！");
	    	  return ;
	     }
	    para["deviceItemUid"]=deviceItemUid;
		para["deviceItemId"]=deviceItemId;
		para["updDate"]=$("#hidden_updDate").val();
		para["deviceItemPropertyStr"]=JSON.stringify(rows);
		BasePage.sendPostRequest(bodyId,'/deviceItemController.do?method=ajaxInsertDeviceItemProperty',para,function(data){	
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