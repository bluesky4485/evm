$(document).ready(function() {
	var bodyId="roleManageBody";
	var roleDgId="roleDg";
	var pageQueryUrl="/roleController.do?method=ajaxFindRolePage";
	var deleteUrl="/roleController.do?method=ajaxDeleteRole";
	var updUrl="/roleController.do?method=ajaxUpdateRole";
	var addUrl="/roleController.do?method=ajaxInsertRole";
	var gotoUpdate="/roleController.do?method=gotoUpdatePage";
	init();
	function init(){
		
		searchRolePage();
	}
	function searchRolePage(){
		var para = {};
		var options = $('#'+roleDgId).datagrid('getPager').data("pagination").options;  
		var pageNumber = options.pageNumber;
		var pageSize =options.pageSize;
		para["pageNum"] = pageNumber;		
		para["pageSize"] = pageSize;
		var roleName=$.trim($("#roleNamePara").searchbox("getValue"));
		if(roleName!=""){
			para["roleName"] = roleName;
		}
		BasePage.sendPostRequest(bodyId,pageQueryUrl,para,function(data){
			if(data.messageType=="error"){
				BasePage.showInfoMessage(data.message);
				return;
			}else{
				if(data== null || data.bizData.total ==0){
					BasePage.showInfoMessage(BasePage.noSearchData);
				}else{
					$("#"+roleDgId).datagrid('loadData', data.bizData);
				}
			}
			
		});
	}
	//服务端分页控件
	var roleDgPaging = $('#'+roleDgId).datagrid('getPager');
    if (roleDgPaging) {
    	BasePage.pagerServerFilter(roleDgPaging,searchRolePage);
    }
//	删除
	$("#delRole").click(function(){
		var row = $("#"+roleDgId).datagrid('getSelected');
		if(row!=null){
			BasePage.confirmMessage("确认删除该角色？",function(flag){
				if(flag){
					var para={};
					var options = $('#'+roleDgId).datagrid('getPager').data("pagination").options;  
					var pageNumber = options.pageNumber;
					var pageSize =options.pageSize;
					para["pageNum"] = pageNumber;		
					para["pageSize"] = pageSize;
					para["roleId"] = row["roleId"];
					para["updDate"]=row["updDate"];
					BasePage.sendPostRequest(bodyId,deleteUrl,para, function(data) {
						if(data.messageType=="error"){
							BasePage.showInfoMessage(data.message);
							return;
						}else{
							alert("删除成功!");
							searchRolePage();
						}
					});
				}
			});
		}else{
			BasePage.showInfoMessage("选择需要删除的角色!");
		}
	});
//	修改
	$("#updRole").click(function(){
		//相当于校验，跳页发form请求 
		var row = $("#"+roleDgId).datagrid('getSelected');
		if(row!=null){
			var url= BasePage.urlPre+gotoUpdate;
			$("#updRoleHref").attr("href",url+"&roleId="+row["roleId"]);
		}else{
			BasePage.showInfoMessage("选择需要修改的角色!");
		}
	});
	$('#roleNamePara').searchbox({
	    searcher:function(value,name){
	    	BasePage.initTablePager("roleDg");
	    	searchRolePage();
	    },
	    prompt:'角色名称'
	});
});