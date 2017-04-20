$(document).ready(function() {
	var bodyId="funcManageBody";
	var funcDg="funcDg";
	var pageQueryUrl="/funcController.do?method=ajaxFindFuncPage";
 
	init();
	function init(){
		$("#"+funcDg).datagrid('loadData',[]);
		searchFuncPage();
	}
	function searchFuncPage(){
		var para = {};
		var options = $('#'+funcDg).datagrid('getPager').data("pagination").options;  
		var pageNumber = options.pageNumber;
		var pageSize =options.pageSize;
		para["pageNum"] = pageNumber;		
		para["pageSize"] = pageSize;
		var funcName=$.trim($("#funcNamePara").searchbox("getValue"))
		if(funcName!=""){
			para["funcName"]=funcName;
		}
		BasePage.sendPostRequest(bodyId,pageQueryUrl,para,function(data){
			if(data.messageType=="error"){
				BasePage.showInfoMessage(data.message);
				return;
			}else{
				if(data== null || data.bizData.total ==0){
					BasePage.showInfoMessage(BasePage.noSearchData);
				}else{
					$("#"+funcDg).datagrid('loadData', data.bizData);
				}
			}
		});
	}
	//服务端分页控件
	var funcDgPaging = $('#'+funcDg).datagrid('getPager');
    if (funcDgPaging) {
    	BasePage.pagerServerFilter(funcDgPaging,searchFuncPage);
    }
    $('#funcNamePara').searchbox({
	    searcher:function(value,name){
	    	BasePage.initTablePager(funcDg);
	    	searchFuncPage();
	    },
	    prompt:'权限名称'
	});
});