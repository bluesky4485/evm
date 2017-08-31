$(document).ready(function() {
	var bodyId="deviceItemManageBody";
	init();
	function init(){
		searchDeviceItem();
	}
	function searchDeviceItem(){
		var para = {};
		var options = $('#deviceItemTable').datagrid('getPager').data("pagination").options;  
		var pageNumber = options.pageNumber;
		var pageSize =options.pageSize;
		para["pageNum"] = pageNumber;		
		para["pageSize"] = pageSize;
		var orderNo= $.trim($("#oid").textbox("getValue"));
		if(orderNo!=""){
			para["orderNo"] = $.trim($("#oid").textbox("getValue"));
		}
		para["deviceTypeName"] = $.trim($("#deviceType").textbox("getValue"));
		var deviceTypeName=$.trim($('#deviceItemNamePara').searchbox('getValue'));
		if(deviceTypeName!=""){
			para["deviceTypeName"] = deviceTypeName;
		}
		BasePage.sendPostRequest(bodyId,'/deviceItemController.do?method=ajaxFindDeviceItemPage',para,function(data){
			if(data.messageType=="error"){
				BasePage.showInfoMessage(data.message);
			}else{
				if(data== null || data.bizData.total ==0)
				{
					$("#deviceItemTable").datagrid('loadData', []);
					BasePage.showInfoMessage("未查询到设备信息！");
				}else{
					$("#deviceItemTable").datagrid('loadData', data.bizData);
				}
			}
			
		});
	}
	var authObj = $('#deviceItemTable').datagrid('getPager');
    if (authObj) {
    	BasePage.pagerServerFilter(authObj,searchDeviceItem);
    }
    $("#doSearch").click(function(){
    	BasePage.initTablePager("deviceItemTable");
    	searchDeviceItem();
    });
//	修改
	$("#updDeviceItemBtn").click(function(){
		//相当于校验，跳页发form请求 
		var row = $("#deviceItemTable").datagrid('getSelected');
		if(row!=null){
			var url= BasePage.urlPre+"/deviceItemController.do?method=gotoUpdatePage";
			$("#updDeviceItemHref").attr("href",url+"&deviceItemId="+row["deviceItemId"]+"&orderNo="+row["orderNo"]);
		}else{
			BasePage.showInfoMessage("选择需要修改的设备!");
		}
	});
//	删除
	$("#delDeviceItemBtn").click(function(){
		var row = $("#deviceItemTable").datagrid('getSelected');
		if(row!=null){
			BasePage.confirmMessage("确认删除该设备？",function(flag){
				if(flag){
					var para={};
					var options = $('#deviceItemTable').datagrid('getPager').data("pagination").options;  
					var pageNumber = options.pageNumber;
					var pageSize =options.pageSize;
					para["pageNum"] = pageNumber;		
					para["pageSize"] = pageSize;
					para["deviceItemId"]=row["deviceItemId"];
					para["updDate"]=row["updDate"];
					BasePage.sendPostRequest(bodyId,'/deviceItemController.do?method=ajaxDeleteDeviceItemById',para, function(data) {
						if(data.messageType=="error"){
							BasePage.showInfoMessage(data.message);
						}else{
							BasePage.showInfoMessage("删除成功!");
							searchDeviceItem();
						}
					});
				}
			});
		}else{
			BasePage.showInfoMessage("选择需要删除的设备!");
		}
	});
	  $('#deviceItemNamePara').searchbox({
		    searcher:function(value,name){
		    	BasePage.initTablePager("deviceItemTable");
		    	searchDeviceItem();
		    },
		    prompt:'设备名称'
		});
});