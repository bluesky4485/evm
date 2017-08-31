$(document).ready(function() {
	BizPageSt.BindQueryCustResult('customManageBody',"todayStopServiceCnt","threeDayStopServiceCnt","fifthDayStopServiceCnt","monthStopServiceCnt");
//	删除
	$("#delCustomer").click(function(){
		var row = $("#customerTable").datagrid('getSelected');
		if(row!=null){
			BasePage.confirmMessage("确认删除该客户？",function(flag){
				if(flag){
					var para={};
					var options = $('#customerTable').datagrid('getPager').data("pagination").options;  
					var pageNumber = options.pageNumber;
					var pageSize =options.pageSize;
					para["pageNum"] = pageNumber;		
					para["pageSize"] = pageSize;
					para["cno"]=row["cno"];
					para["updDate"]=row["updDate"];
					BasePage.sendPostRequest('customManageBody','/customController.do?method=ajaxDeleteCustom',para, function(data) {
						if(data.messageType=="error"){
							BasePage.showInfoMessage(data.message);
							return;
						}else{
							BasePage.showInfoMessage("删除成功!");
							searchList();
						}
					});
				}
			});
		}else{
			BasePage.showInfoMessage("选择需要删除的客户!");
		}
	});
//	修改
	$("#updCustomHref").click(function(){
		//相当于校验，跳页发form请求 
		var row = $("#customerTable").datagrid('getSelected');
		if(row!=null){
			var url= BasePage.urlPre+"/customController.do?method=gotoUpdatePage";
			$("#updCustomHref").attr("href",url+"&cno="+row["cno"]);
		}else{
			BasePage.showInfoMessage("选择需要修改的客户!");
		}
	});
	//0
	$("#todayStopServiceCntDiv").click(function(){
		var queryStat=$.trim($('#queryStat').val());
		$('#queryStat').val("1");
		BasePage.initTablePager("customerTable");
		searchList();
	});
	//3
	$("#threeDayStopServiceCntDiv").click(function(){
		var queryStat=$.trim($('#queryStat').val());
		$('#queryStat').val("2");
		BasePage.initTablePager("customerTable");
		searchList();
	});
	//本月
	$("#fifthDayStopServiceCntDiv").click(function(){
		var queryStat=$.trim($('#queryStat').val());
		$('#queryStat').val("3");
		BasePage.initTablePager("customerTable");
		searchList();
	});
	//全部
	$("#monthStopServiceCntDiv").click(function(){
		var queryStat=$.trim($('#queryStat').val());
		$('#queryStat').val("4");
		BasePage.initTablePager("customerTable");
		searchList();
	});
	
	searchList = function(){
		var para = {};
		var options = $('#customerTable').datagrid('getPager').data("pagination").options;  
		var pageNumber = options.pageNumber;
		var pageSize =options.pageSize;
		para["pageNum"] = pageNumber;		
		para["pageSize"] = pageSize;
		var cusName=$.trim($('#cusNamePara').searchbox('getValue'));
		if(cusName!=""){
			para["cname"] = cusName;
		}
		var queryStat=$.trim($('#queryStat').val());
		para["queryStat"] = queryStat;
		BasePage.sendPostRequest('customManageBody','/customController.do?method=ajaxFindCustom',para,function(data){
			if(data.messageType=="error"){
				BasePage.showInfoMessage(data.message);
				return;
			}else{
				if(data== null || data.bizData.total ==0){
					BasePage.showInfoMessage("未查询到客户信息！");
					$("#customerTable").datagrid('loadData', []);
				}else{
					$("#customerTable").datagrid('loadData', data.bizData);
				}
			}
		});
	}
	function init(){
		searchList();
	}
	init();
	var authObj = $('#customerTable').datagrid('getPager');
    if (authObj) {
    	BasePage.pagerServerFilter(authObj,searchList);
    }
    $('#cusNamePara').searchbox({
	    searcher:function(value,name){
	    	BasePage.initTablePager("customerTable");
	    	$('#queryStat').val("");
	    	searchList();
	    },
	    prompt:'客户姓名'
	});
});