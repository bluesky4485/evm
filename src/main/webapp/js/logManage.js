$(document).ready(function() {
	var bodyId="logManageBody";
	var logDg="logDg";
	var pageQueryUrl="/logController.do?method=ajaxFindLogPage";
 
	init();
	function init(){
		$("#"+logDg).datagrid('loadData',[]);
		searchLogPage();
	}
	function searchLogPage(){
		var para = {};
		var options = $('#'+logDg).datagrid('getPager').data("pagination").options;  
		var pageNumber = options.pageNumber;
		var pageSize =options.pageSize;
		para["pageNum"] = pageNumber;		
		para["pageSize"] = pageSize;
		var beginDate=$("#beginDate").datebox("getValue");
		var endDate  =$("#endDate").datebox("getValue");
		para["beginDate"]=beginDate;
		para["endDate"]=endDate;
		if(beginDate!=""&&endDate!=""&&endDate<beginDate){
			BasePage.showInfoMessage("时间范围输入有误，请重新输入！");
    		return ;
    	}
		var logContent= $.trim($("#logNamePara").searchbox("getValue"));
		if(logContent!=""){
			para["logContent"] =logContent;
		}
		BasePage.sendPostRequest(bodyId,pageQueryUrl,para,function(data){
			if(data.messageType=="error"){
				BasePage.showInfoMessage(data.message);
				return;
			}else{
				if(data== null || data.bizData.total ==0){
					BasePage.showInfoMessage(BasePage.noSearchData);
					$("#"+logDg).datagrid('loadData', []);
				}else{
					$("#"+logDg).datagrid('loadData', data.bizData);
				}	
			}
		});
	}
	//服务端分页控件
	var logDgPaging = $('#'+logDg).datagrid('getPager');
    if (logDgPaging) {
    	BasePage.pagerServerFilter(logDgPaging,searchLogPage);
    }
    $('#logNamePara').searchbox({
	    searcher:function(value,name){
	    	BasePage.initTablePager(logDg);
	    	searchLogPage();
	    },
	    prompt:'日志内容'
	});
    $("#doSearchLogBtn").click(function(){
    	searchLogPage();
    });
});