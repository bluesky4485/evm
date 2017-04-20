$(document).ready(function() {
		init();
		function init(){
			searchDevice();
		} 
		//查询
		function searchDevice(){
			var para = {};
			var options = $('#deviceTable').datagrid('getPager').data("pagination").options;  
			var pageNumber = options.pageNumber;
			var pageSize =options.pageSize;
			para["pageNum"] = pageNumber;		
			para["pageSize"] = pageSize;
			var deviceName=$.trim($('#deviceNamePara').searchbox('getValue'));
			if(deviceName!=""){
				para["deviceName"] = deviceName;
			}
			BasePage.sendPostRequest('deviceManageBody','/deviceController.do?method=ajaxFindDevicePage',para,function(data){
				if(data.messageType=="error"){
					BasePage.showInfoMessage(data.message);
				}else{
						if(data== null || data.bizData.total ==0){
							BasePage.showInfoMessage(BasePage.noSearchData);
						}else{
							$("#deviceTable").datagrid('loadData', data.bizData);
						}
				}
			});
		};
		var deviceTablePaging = $('#deviceTable').datagrid('getPager');
	    if (deviceTablePaging) {
	    	BasePage.pagerServerFilter(deviceTablePaging,searchDevice);
	    }
		$("#updDeviceBtn").click(function(){
			var row = $("#deviceTable").datagrid('getSelected');
			if(row!=null){
				var url= BasePage.urlPre+"/deviceController.do?method=gotoUpdatePage";//$("#updDeviceHref").attr("href");
				$("#updDeviceHref").attr("href",url+"&deviceId="+row["deviceId"]);
			}else{
				BasePage.showInfoMessage("选择需要修改的设备!");
			}
		});
		//删除
	$("#delDeviceBtn").click(function(){
		var row = $("#deviceTable").datagrid('getSelected');
		if(row!=null){
			BasePage.confirmMessage("确认删除设备？",function(flag){
				if(flag){
					var para={};
					var options = $('#deviceTable').datagrid('getPager').data("pagination").options;  
					var pageNumber = options.pageNumber;
					var pageSize =options.pageSize;
					para["pageNum"] = pageNumber;		
					para["pageSize"] = pageSize;
					para["deviceId"]=row["deviceId"];
					para["updDate"]=row["updDate"];
					BasePage.sendPostRequest('deviceManageBody','/deviceController.do?method=ajaxDeleteDevice',para, function(data) {
						if(data.messageType=="error"){
							BasePage.showInfoMessage(data.message);
						}else{
							searchDevice()
						}
					});
				}
			});
			}
	});
	  $('#deviceNamePara').searchbox({
		    searcher:function(value,name){
		    	BasePage.initTablePager("deviceTable");
		    	searchDevice();
		    },
		    prompt:'设备类型名称'
		});
});
function formatOper(){
	 var optStr="<div><a  href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-add'\"   ></a>dd</div>"
     var img="<img src=\"./../images/web_button_14.png\" onclick=\"dispatcherClick()\" />"
     optStr=img+img+img;
 
    return optStr;  
}