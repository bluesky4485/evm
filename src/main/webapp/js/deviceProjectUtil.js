var DeviceUtil=DeviceUtil||{};
(function(){
	//交换设备
	DeviceUtil.AddSelectDevice=function(soureDgId,descDgId){
		var rows = $("#"+soureDgId).datagrid('getSelections');
		if(rows==null){
			BasePage.showInfoMessage("选择设备!");
			return;
		}
		for(var i=0;i<rows.length;i++){
			var rowIndex=$("#"+soureDgId).datagrid('getRowIndex',rows[i]);
			$("#"+soureDgId).datagrid("deleteRow",rowIndex);
			$("#"+descDgId).datagrid("appendRow",rows[i]);
		}
	};
	//加载设备信息
	DeviceUtil.LoadDeviceData=function(gridId,callback){
		var para={};
		para["classType"]=$("#deviceWindowIndex").val();
		BasePage.sendPostRequest(gridId,'/deviceController.do?method=ajaxFindDeviceByType',para,function(data){
			$("#"+gridId).datagrid('loadData', data.bizData);
			callback;
		});
		
	};
	//取得需要添加的设备
	DeviceUtil.GetDeviceData=function(gridId){
		var rows=$("#"+gridId).datagrid("getRows");
		if(rows==null){
			BasePage.showInfoMessage("选择需要添加的设备!");
			return;
		}
		var resData=[];
		for(var i=0;i<rows.length;i++){
			var row=rows[i];
			var cnt=$("#OPT_"+i).val();
			if(cnt==0){
				BasePage.showInfoMessage("添加的设备【"+row["deviceName"]+"】为0!");
				return;
			}else{
				var rowData={};
				rowData["deviceId"]=row["deviceId"];
				rowData["classType"]=row["classType"];
				rowData["deviceName"]=row["deviceName"];
				rowData["deviceCnt"]=cnt;
				resData.push(rowData);
			}
		}
		return resData;
	};
	DeviceUtil.CaculateDeviceCnt=function(addDevice,gridData){
		if(gridData.length==0){
				for(var i=0;addDevice!=null&&i<addDevice.length;i++){
				  gridData.push(addDevice[i]);
				}
		}else{
			 for(var i=0;addDevice!=null&&i<addDevice.length;i++){
				var flag=false;
				for(var j=0;j<gridData.length;j++){
					if(addDevice[i].deviceName==gridData[j].deviceName){
						flag=true;
						break;
					}
				}
				if(!flag){
					gridData.push(addDevice[i]); 
				}else{
					gridData[j].deviceCnt=parseInt(gridData[j].deviceCnt)+parseInt(addDevice[i].deviceCnt);
				}
		     }
		}
		return gridData;
	};
})();

//格式化 +/-操作框
function doOptFormat(value,row,index){
	var add="<img style='padding-top:5px' src='/evm/images/addCheck.jpg' onClick='javascript:addOne("+index+")'/>";
	var Opt="<input id='"+"OPT_"+index+"' type='text' style='width:15px;height:15px' value='0'/>";
	var substract="<img  style='padding-top:5px;z-index:20000' src='/evm/images/subtractCheck.jpg' onClick='javascript:substractOne("+index+")'/>";
	return substract+Opt+add;
}
//+1
function addOne(index){
	var sum=$("#OPT_"+index).val();
	$("#OPT_"+index).val(parseInt(sum)+1);    
}
//减法-1
function substractOne(index){
	var sum=$("#OPT_"+index).val();
	sum=parseInt(sum);
	if(sum>0){
		$("#OPT_"+index).val(sum-1); 
	}
}
//设备window打开，记录那种类型的设备 index 1=设备2线材3辅助材料
function deviceOpen(index) {
	$("#deviceWindowIndex").val(index);
	$("#device_window").dialog("open");
}
//删除设备
function deviceDelete(index){
	var deviceWindowIndex=$("#deviceWindowIndex").val();
	var tableID="deviceDg";
	if(deviceWindowIndex==1){
		tableID="deviceDg";
	}else if(deviceWindowIndex==2){
		tableID="lineDeviceDg";
	}else if(deviceWindowIndex==3){
		tableID="helpDeviceDg";
	}
	var rows = $("#"+tableID).datagrid('getSelections');
	for(var i=0;i<rows.length;i++){
		var rowIndex=$("#"+tableID).datagrid('getRowIndex',rows[i]);
		$("#"+tableID).datagrid("deleteRow",rowIndex);
	}
};
//设备查询 
function doSearchDevice(value) {
	 
}