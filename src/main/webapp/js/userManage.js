$(document).ready(function() {
	var bodyId="userManageBody";
	init();
	function init(){
		searchUser();
	} 
	function searchUser(){
		var para = {};
		var options = $('#userDg').datagrid('getPager').data("pagination").options;  
		var pageNumber = options.pageNumber;
		var pageSize =options.pageSize;
		para["pageNum"] = pageNumber;		
		para["pageSize"] = pageSize;
		var ufullName= $.trim($("#userNamePara").searchbox("getValue"));
		if(ufullName!=""){
			para["ufullName"] =ufullName;
		}
		BasePage.sendPostRequest(bodyId,'/userController.do?method=ajaxFindUserList',para,function(data){
			if(data.messageType=="error"){
				BasePage.showInfoMessage(data.message);
				return;
			}else{
				if(data== null || data.bizData.total ==0){
					BasePage.showInfoMessage(BasePage.noSearchData);
					$("#userDg").datagrid('loadData', []);
				}else{
					$("#userDg").datagrid('loadData', data.bizData);
				}
			}
		});
	}
	//服务端分页控件
	var userDgPaging = $('#userDg').datagrid('getPager');
    if (userDgPaging) {
    	BasePage.pagerServerFilter(userDgPaging,searchUser);
    }
    //	删除
	$("#delUser").click(function(){
		var row = $("#userDg").datagrid('getSelected');
		if(row!=null){
			BasePage.confirmMessage("确认删除该用户？",function(flag){
				if(flag){
					var para={};
					var options = $('#userDg').datagrid('getPager').data("pagination").options;  
					var pageNumber = options.pageNumber;
					var pageSize =options.pageSize;
					para["pageNum"] = pageNumber;		
					para["pageSize"] = pageSize;
					para["uid"] = row["uid"];
					para["updDate"]=row["updDate"];
					BasePage.sendPostRequest(bodyId,'/userController.do?method=ajaxDeleteUser',para, function(data) {
						if(data.messageType=="error"){
							BasePage.showInfoMessage(data.message);
							return;
						}else{
							alert("删除成功!");
							searchUser();
						}
					});
				}
			});
		}else{
			BasePage.showInfoMessage("选择需要删除的用户!");
		}
	});
//	修改
	$("#updUser").click(function(){
		//相当于校验，跳页发form请求 
		var row = $("#userDg").datagrid('getSelected');
		if(row!=null){
			var url= BasePage.urlPre+"/userController.do?method=gotoUpdatePage";//$("#updDeviceHref").attr("href");
			$("#updUserHref").attr("href",url+"&uid="+row["uid"]);
		}else{
			BasePage.showInfoMessage("选择需要修改的用户!");
		}
	});
	//批量增加
	$("#batchAddOrder").click(function(){
		//batchAdd_window
		$("#batchAdd_window").window("open");
	});
	$('#userNamePara').searchbox({
	    searcher:function(value,name){
	    	BasePage.initTablePager("userDg");
	    	searchUser();
	    },
	    prompt:'系统用户'
	});
});
