$(document).ready(function() {
	//绑定统计参数
	BizPageSt.BindQueryProjectStResult('projectManageBody',"todayStopWorkCnt","mothSingProjectCnt","weekStopWorkCnt","allProjectCnt");
//	删除
	$("#delProject").click(function(){
		var row = $("#projectDg").datagrid('getSelected');
		if(row!=null){
			BasePage.confirmMessage("确认删除该项目？",function(flag){
				if(flag){
					var row = $("#projectDg").datagrid('getSelected');
					var para = {};
					var options = $('#projectDg').datagrid('getPager').data("pagination").options;  
					var pageNumber = options.pageNumber;
					var pageSize =options.pageSize;
					para["pageNum"] = pageNumber;		
					para["pageSize"] = pageSize;
					para["projectId"]=row["projectId"];
					para["updDate"]=row["updDate"];
					BasePage.sendPostRequest('projectManageBody','/projectController.do?method=ajaxDeleteProject',para,function(data){	
						if(data.messageType=="error"){
							BasePage.showInfoMessage(data.message);
							return;
						}else{
							BasePage.showInfoMessage("删除成功!");
							searchProjectList();
						}
					});
				}
			});
		}else{
			BasePage.showInfoMessage("选择需要删除的项目!");
		}
	});
//	修改
	$("#updProject").click(function(){
		//相当于校验，跳页发form请求 
		var row = $("#projectDg").datagrid('getSelected');
		if(row!=null){
			var url= BasePage.urlPre+"/projectController.do?method=gotoUpdatePage";
			$("#updProjectHref").attr("href",url+"&projectId="+row["projectId"]);
		}else{
			BasePage.showInfoMessage("选择需要修改的项目!");
		}
	});
	//今日工期截止的项目
	$("#todayStopWorkCntDiv").click(function(){
		var queryStat=$.trim($('#queryStat').val());
		$('#queryStat').val("1");
		BasePage.initTablePager("projectDg");
		searchProjectList();
	});
	//最近一周工期截止的项目
	$("#weekStopWorkCntDiv").click(function(){
		var queryStat=$.trim($('#queryStat').val());
		$('#queryStat').val("2");
		BasePage.initTablePager("projectDg");
		searchProjectList();
	});
	//最近一个月签订的项目
	$("#mothSingProjectCntDiv").click(function(){
		var queryStat=$.trim($('#queryStat').val());
		$('#queryStat').val("3");
		BasePage.initTablePager("projectDg");
		searchProjectList();
	});
	//全部项目
	$("#allProjectCntDiv").click(function(){
	    $('#queryStat').val("4");
	    BasePage.initTablePager("projectDg");
		searchProjectList();
	});
	init();
	function init(){
		searchProjectList();
	}
	/*查询项目*/
	function searchProjectList(){
		var para = {};
		var options = $('#projectDg').datagrid('getPager').data("pagination").options;  
		var pageNumber = options.pageNumber;
		var pageSize =options.pageSize;
		para["pageNum"] = pageNumber;		
		para["pageSize"] = pageSize;
		var projectName=$.trim($("#projectNamePara").searchbox("getValue"));
		if(projectName!=""){
			para["projectName"] = projectName;
		}
		var queryStat=$.trim($('#queryStat').val());
		var stopServiceDays=$.trim($('#stopServiceDays').val());
		para["queryStat"] = queryStat;
		para["stopServiceDays"] = stopServiceDays;
		BasePage.sendPostRequest('projectManageBody','/projectController.do?method=ajaxFindProjectPage',para,function(data){	
			if(data.messageType=="error"){
				BasePage.showInfoMessage(data.message);
				return;
			}else{
				if(data== null || data.bizData.total ==0){
					BasePage.showInfoMessage("未查询到项目信息！");
					$("#projectDg").datagrid('loadData', []);
				}else{
					$("#projectDg").datagrid('loadData', data.bizData);
				}
			}
		});
	}
	 //项目表分页
    var projectDgPage = $('#projectDg').datagrid('getPager');
    if (projectDgPage) {
    	BasePage.pagerServerFilter(projectDgPage,searchProjectList);
    }
    $('#projectNamePara').searchbox({
	    searcher:function(value,name){
	    	BasePage.initTablePager("projectDg");
	    	$('#queryStat').val("");
	    	searchProjectList();
	    },
	    prompt:'项目名称'
	});
});