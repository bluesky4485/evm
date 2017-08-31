$(document).ready(function() {
	var url="/funcController.do?method=ajaxFindServiceFuncPage";
  
	$("#funcDg").datagrid({
		onDblClickRow : function(index, row){
			var functionId=row["funcId"];
			if(parseInt(functionId)>30000){
				$("#serviceDg").datagrid('loadData', []);
				$("#func_serviceId_window").window("open");
				searchServic(index, row);
			}
		}
    });
	function searchServic(index, row){
		var para = {};
		para["functionId"]=row["funcId"];
		BasePage.sendPostRequest("func_serviceId_window",url ,para,function(data){	
			if(data.messageType=="error"){
				BasePage.showInfoMessage(data.message);
				return;
			}else{
				if(data== null || data.bizData.length ==0){
					BasePage.showInfoMessage("未查询到相关服务的信息！");
					$("#serviceDg").datagrid('loadData', []);
				}else{
					$("#serviceDg").datagrid('loadData', data.bizData);
				}
			}
		});
	};
});