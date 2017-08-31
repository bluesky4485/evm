$(document).ready(function() {
	var bodyId="addProjectBody";
	// 设备window取消
	$("#device_window_cancel").click(function() {
		$("#device_window").dialog("close");
	});
	//设备window 打开初始化
	$("#device_window").window({"onOpen":function(){
		$("#addDeviceDg").datagrid('loadData', []);
		DeviceUtil.LoadDeviceData("unAddDeviceDg");
	}});
	// 设备window确定
	$("#device_window_OK").click(function() {
		//选择需要添加的表
		var deviceWindowIndex=$("#deviceWindowIndex").val();
		var tableID="deviceDg";
		if(deviceWindowIndex==1){
			tableID="deviceDg";
		}else if(deviceWindowIndex==2){
			tableID="lineDeviceDg";
		}else if(deviceWindowIndex==3){
			tableID="helpDeviceDg";
		}
		var addDevice=DeviceUtil.GetDeviceData("addDeviceDg");
		var gridData=$("#"+tableID).datagrid("getRows");
		var result=DeviceUtil.CaculateDeviceCnt(addDevice,gridData);
		$("#"+tableID).datagrid("loadData",result);
		$("#device_window").dialog("close");
	});
	// 设备window取消
	$("#device_window_cancel").click(function() {
		$("#device_window").dialog("close");
	});
    //设备移添
	$("#deviceAddBtn").click(function(){
		DeviceUtil.AddSelectDevice("unAddDeviceDg","addDeviceDg");
	});
	//设备移除
	$("#deviceDelBtn").click(function(){
		DeviceUtil.AddSelectDevice("addDeviceDg","unAddDeviceDg");
	});
});