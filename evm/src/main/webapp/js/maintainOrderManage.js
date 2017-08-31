$(document).ready(function() {
	var bodyId='morderManageBody';
	//未处理的运维订单
	$("#allNoDoMorderCntDiv").click(function(){
		var queryStat=$.trim($('#queryStat').val());
		$('#queryStat').val("1");
		BasePage.initTablePager("mOrderDg");
		searchMorder(); 
	});
	//未回访的运维订单
	$("#noCallBackMorderCntDiv").click(function(){
		var queryStat=$.trim($('#queryStat').val());
		$('#queryStat').val("2");
		BasePage.initTablePager("mOrderDg");
		searchMorder();
	});
	//运维中的运维订单
	$("#doingMorderCntDiv").click(function(){
		var queryStat=$.trim($('#queryStat').val());
		$('#queryStat').val("3");
		BasePage.initTablePager("mOrderDg");
		searchMorder();
	});
	//全部的运维订单
	$("#problemMorderCntDiv").click(function(){
		$('#queryStat').val("4");
		BasePage.initTablePager("mOrderDg");
		searchMorder();
	});
	init();
	
	function init(){
		searchMorder();
	} 
	BizPageSt.BindQueryAllMOrderStResult(bodyId,"allNoDoMorderCnt","noCallBackMorderCnt","doingMorderCnt","problemMorderCnt");
	function searchMorder(){
		var para = {};
		var options = $('#mOrderDg').datagrid('getPager').data("pagination").options;  
		var pageNumber = options.pageNumber;
		var pageSize =options.pageSize;
		para["pageNum"] = pageNumber;		
		para["pageSize"] = pageSize;
		var morderNo= $('#morderNoPara').searchbox("getValue");
		if(morderNo!=""){
			para["morderNo"] = morderNo;
		}
		var queryStat=$.trim($('#queryStat').val());
		para["queryStat"] = queryStat;
		BasePage.sendPostRequest(bodyId,'/morderController.do?method=ajaxFindMorderPage',para,function(data){	
			if(data.messageType=="error"){
				BasePage.showInfoMessage(data.message);
				return;
			}
			if(data== null || data.bizData.total ==0){
				BasePage.showInfoMessage(BasePage.noSearchData);
				$("#mOrderDg").datagrid('loadData', []);
			}else{
				$("#mOrderDg").datagrid('loadData', data.bizData);
			}
		});
	}
	var morderPaging = $('#mOrderDg').datagrid('getPager');
    if (morderPaging) {
    	BasePage.pagerServerFilter(morderPaging,searchMorder);
    }
//	删除
	$("#delMOrder").click(function(){
		var row = $("#mOrderDg").datagrid('getSelected');
		if(row!=null){
			BasePage.confirmMessage("确认删除该维护订单？",function(flag){
				if(flag){
					var para = {};
					var options = $('#mOrderDg').datagrid('getPager').data("pagination").options;  
					var pageNumber = options.pageNumber;
					var pageSize =options.pageSize;
					para["pageNum"] = pageNumber;		
					para["pageSize"] = pageSize;
					para["morderId"]=row["morderId"];
					para["updDate"]=row["updDate"];
					BasePage.sendPostRequest(bodyId,'/morderController.do?method=ajaxDeleteMorder',para,function(data){	
						if(data.message=="sucess"){
							BasePage.showInfoMessage("删除成功!");
							searchMorder();
						}
					});
				}
			});
		}else{
			BasePage.showInfoMessage("选择需要删除的维护订单!");
		}
	});
//	修改
	$("#updMOrder").click(function(){
		//相当于校验，跳页发form请求 
		var row = $("#mOrderDg").datagrid('getSelected');
		if(row!=null){
			var url= BasePage.urlPre+"/morderController.do?method=gotoUpdatePage";
			$("#updMOrderHref").attr("href",url+"&morderId="+row["morderId"]);
		}else{
			BasePage.showInfoMessage("选择需要修改的维护订单!");
		}
	});
	 $('#morderNoPara').searchbox({
		    searcher:function(value,name){
		    	BasePage.initTablePager("mOrderDg");
		    	$('#queryStat').val("");
		    	searchMorder();
		    },
		    prompt:'运维订单编号'
		});
});
//操作列
function formatOper(val,row,index){  
	 var optStr="<div><a  href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconCls:'icon-add'\"   ></a>dd</div>"
     var img="<img src=\"./../images/web_button_14.png\" onclick=\"dispatcherClick()\" />"
     optStr=img+img+img;
	 optStr="<a href='javascript:void(0)' onclick=\"dispatcherClick()\" >指派</a> "
//	 optStr=optStr+"<a href='javascript:void(0)' onclick=\"editOrder()\" >编辑</a>"
    return optStr;  
} 
function dispatcherClick(val,row,index){
	alert("指派项目经理？");
}