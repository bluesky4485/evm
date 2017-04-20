var orderDiviceUtil={}||orderDiviceUtil;
(function(){
	//交换设备
	orderDiviceUtil.AddSelectDevice=function(soureDgId,descDgId){
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
	orderDiviceUtil.LoadDeviceData=function(gridId,callback){
		var para={};
		para["classType"]=$("#deviceWindowIndex").val();
	    var projectId=$("#projectid").val();
	    
	    para["projectNo"]=projectId;
		BasePage.sendPostRequest(gridId,'/deviceController.do?method=ajaxFindDeviceByType',para,function(data){
			
			$("#"+gridId).datagrid('loadData', data.bizData);
			if(data.bizData==undefined||data.bizData.length==0){
				BasePage.showInfoMessage("请查看项目是否配置设备!");
			}
			if(callback!=undefined){
				callback;
			}
		});
	};
	//加载设备信息
	orderDiviceUtil.LoadDeviceData4Add=function(gridId,callback){
		var para={};
		para["classType"]=$("#deviceWindowIndex").val();
	    var projectId=$("#projectid").combobox("getText");
	    para["projectNo"]=projectId;
		BasePage.sendPostRequest(gridId,'/deviceController.do?method=ajaxFindDeviceByType',para,function(data){
			$("#"+gridId).datagrid('loadData', data.bizData);
			if(data.bizData==undefined||data.bizData.length==0){
				BasePage.showInfoMessage("请查看项目是否配置设备!");
			}
			if(callback!=undefined){
				callback;
			}
		});
	};
	//取得需要添加的设备
	orderDiviceUtil.GetDeviceData=function(gridId){
		var rows=$("#"+gridId).datagrid("getRows");
		if(rows==null){
			BasePage.showInfoMessage("选择需要添加的设备!");
			return;
		}
		var resData=[];
		for(var i=0;i<rows.length;i++){
			var row=rows[i];
			var deviceCnt=$("#OPT_"+i).val();
			var planCnt=$("#planCnt_"+i).val();
			var storeCnt=0;//$("#storeCnt_"+i).val();
			if(deviceCnt==0){
				BasePage.showInfoMessage("添加的设备【"+row["deviceName"]+"】为0!");
				return;
			}else{
				var rowData={};
				rowData["deviceId"]=row["deviceId"];
				rowData["classType"]=row["classType"];
				rowData["deviceName"]=row["deviceName"];
				rowData["deviceCnt"]=deviceCnt;
				rowData["planCnt"]=planCnt;
				rowData["storeCnt"]=storeCnt;
				resData.push(rowData);
			}
		}
		return resData;
	};
	orderDiviceUtil.CaculateDeviceCnt=function(addDevice,gridData){
		if(gridData.length==0){
				for(var i=0;addDevice!=null&&i<addDevice.length;i++){
				  gridData.push(addDevice[i]);
				}
		}else{
			 for(var i=0;addDevice!=null&&i<addDevice.length;i++){
				var flag=false;
				for(var j=0;j<gridData.length;j++){
					if(addDevice[i].deviceId==gridData[j].deviceId){
						flag=true;
						break;
					}
				}
				if(!flag){
					gridData.push(addDevice[i]); 
				}else{
					gridData[j].deviceCnt=parseInt(gridData[j].deviceCnt)+parseInt(addDevice[i].deviceCnt);
					gridData[j].planCnt=parseInt(gridData[j].planCnt)+parseInt(addDevice[i].planCnt);
					gridData[j].storeCnt=parseInt(gridData[j].storeCnt)+parseInt(addDevice[i].storeCnt);
				}
		     }
		}
		return gridData;
	};

})();

//格式化 +/-操作框
//格式化 +/-操作框
function doOptFormat(value,row,index){
	var add="<img style='padding-top:5px' src='/evm/images/addCheck.jpg' onClick='javascript:addOne("+index+",1)'/>";
	var Opt="<input id='"+"OPT_"+index+"' type='text' style='width:15px;height:15px' value='0'/>";
	var substract="<img  style='padding-top:5px;z-index:20000' src='/evm/images/subtractCheck.jpg' onClick='javascript:substractOne("+index+")'/>";
	return substract+Opt+add;
}
//格式化 +/-操作框
 doPlanFormat=function(value,row,index){
	var add="<img style='padding-top:5px' src='/evm/images/addCheck.jpg' onClick='javascript:addOne("+index+",2)'/>";
	var Opt="<input id='"+"planCnt_"+index+"' type='text' style='width:15px;height:15px' value='0'/>";
	var substract="<img  style='padding-top:5px;z-index:20000' src='/evm/images/subtractCheck.jpg' onClick='javascript:substractOne("+index+",2)'/>";
	return substract+Opt+add;
}
//格式化 +/-操作框
doWorkFormat=function(value,row,index){
	var add="<img style='padding-top:5px' src='/evm/images/addCheck.jpg' onClick='javascript:addOne("+index+",3)'/>";
	var Opt="<input id='"+"storeCnt_"+index+"' type='text' style='width:15px;height:15px' value='0'/>";
	var substract="<img  style='padding-top:5px;z-index:20000' src='/evm/images/subtractCheck.jpg' onClick='javascript:substractOne("+index+",3)'/>";
	return substract+Opt+add;
}
//+1
function addOne(index,colName){
	var _colName="OPT";
	if(colName==1){
		_colName="OPT";
	}else if(colName==2){
		_colName="planCnt";
	}else if(colName==3){
		_colName="storeCnt";
	}
	var sum=$("#"+_colName+"_"+index).val();
	$("#"+_colName+"_"+index).val(parseInt(sum)+1);    
}
//减法-1
function substractOne(index,colName){
	var _colName="OPT";
	if(colName==1){
		_colName="OPT";
	}else if(colName==2){
		_colName="planCnt";
	}else if(colName==3){
		_colName="storeCnt";
	}
	var sum=$("#"+_colName+"_"+index).val();
	sum=parseInt(sum);
	if(sum>0){
		$("#"+_colName+"_"+index).val(sum-1); 
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
	alert('You input: ' + value);
	var deviceWindowIndex=$("#deviceWindowIndex").val();
	var tableID="deviceDg";
	if(deviceWindowIndex==1){
		tableID="deviceDg";
	}else if(deviceWindowIndex==2){
		tableID="lineDeviceDg";
	}else if(deviceWindowIndex==3){
		tableID="helpDeviceDg";
	}
	
}