$(document).ready(function() {
			init();			
			function init(){
				searchDevice();
			} 
			function searchDevice(){
				var para = {};
				var options = $('#faultTypeTable').datagrid('getPager').data("pagination").options;  
				var pageNumber = options.pageNumber;
				var pageSize =options.pageSize;
				para["pageNum"] = pageNumber;		
				para["pageSize"] = pageSize;
				var faultTypeName= $.trim($("#faultTypeNamePara").searchbox("getValue"));
				if(faultTypeName!=""){
					para["faultTypeName"] =faultTypeName;
				}
				BasePage.sendPostRequest('dfManageBody','/faultTypeController.do?method=ajaxFindFaultType',para,function(data){
					if(data.messageType=="error"){
						BasePage.showInfoMessage(data.message);
					}else{
							if(data== null || data.bizData.total ==0){
								BasePage.showInfoMessage(BasePage.noSearchData);
							}else{
								$("#faultTypeTable").datagrid('loadData', data.bizData);
							}
					}
				});
			}
			// 新增故障类型
			$("#addFaultTypeBtn").click(function() {
				$("#add_falutType_window").window("open");
			});
			// 修改故障类型
			$("#updFaultTypeBtn").click(function() {
						var updObj = $("#faultTypeTable").datagrid(	'getSelected');
						if (updObj != null) {
							$("#upd_faultTypeNo").textbox("setValue",updObj["faultTypeNo"]);
							$("#upd_faultTypeName").textbox("setValue",updObj["faultTypeName"]);
							$("#upd_falutType_window").window("open");
						} else {
							BasePage.showInfoMessage("选择需要修改的故障类型！");
						}
					});
			// 删除故障类型
			$("#delFaultTypeBtn").click(function() {
				var updObj = $("#faultTypeTable").datagrid('getSelected');
				if (updObj != null) {
					$.messager.confirm("确认消息", "确定删除该故障类型？", function(result) {
						if (result) {
							// delte
							var para={};
							var updObj = $("#faultTypeTable").datagrid('getSelected');
							  if (updObj != null) {	
								  para["faultTypeId"] = updObj["faultTypeId"];
								  para["updDate"] = updObj["updDate"];
								  BasePage.sendPostRequest('dfManageBody','/faultTypeController.do?method=ajaxDeleteFaultType',para,function(data){	
									  if(data.messageType=="error"){
											BasePage.showInfoMessage(data.message);
										}else{
											searchDevice();
										}
									});
							  }
						}
					});
				} else {
					BasePage.showInfoMessage("选择需要删除的故障类型！");
				}
			});
            //新增故障类型
			$("#add_faultType_window_OK").click(function() {
				//TODO:
				if(FaultTypeUtil.submitValidate($("#add_faultTypeName"))){
				var faultTypeName=$.trim($("#add_faultTypeName").val());
				if(faultTypeName==""){
					BasePage.showInfoMessage("请输入故障名称！");
					return ;
				}else if(faultTypeName.length>20){
					BasePage.showInfoMessage("故障名称超长！");
					return ;
				} 
				var para={};
				para["faultTypeName"] =faultTypeName;
				BasePage.sendPostRequest('dfManageBody','/faultTypeController.do?method=ajaxInsertFaultType',para,function(data){	
					 if(data.messageType=="error"){
							BasePage.showInfoMessage(data.message);
						}else{
							searchDevice();
						}
					$("#add_falutType_window").window("close");
				});
			}
			});
			$("#add_faultType_window_cancel").click(function() {				 
				$("#add_falutType_window").window("close");
			});
			$("#upd_faultType_window_cancel").click(function() {
				$("#upd_falutType_window").window("close");
			});
		    //修改故障类型
			$("#upd_faultType_window_OK").click(function() {
				//TODO:校验？
				if(FaultTypeUtil.submitValidate($("#upd_faultTypeName"))){
				var updObj = $("#faultTypeTable").datagrid(	'getSelected');
				var faultTypeName=$.trim($("#upd_faultTypeName").val());
				if(faultTypeName==""){
					BasePage.showInfoMessage("请输入故障名称！");
					return ;
				}else if(faultTypeName.length>20){
					BasePage.showInfoMessage("故障名称超长！");
					return ;
				} 
				var para={};
				para["faultTypeName"] = faultTypeName;
				para["updDate"] = updObj["updDate"];
				para["faultTypeId"] = updObj["faultTypeId"];
				BasePage.sendPostRequest('dfManageBody','/faultTypeController.do?method=ajaxUpdateFaultType',para,function(data){					
					 if(data.messageType=="error"){
							BasePage.showInfoMessage(data.message);
						}else{
							searchDevice();
						}
					$("#upd_falutType_window").window("close");
				});
			}
		});
			  $('#faultTypeNamePara').searchbox({
				    searcher:function(value,name){
				    	BasePage.initTablePager("faultTypeTable");
				    	searchDevice();
				    },
				    prompt:'故障类型名称'
			});
});