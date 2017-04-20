$(document).ready(function() {
	var bodyId="orderManageBody";
	BizPageSt.BindQueryOrderResult(bodyId,"todayNoDoOrderCnt","weekNoDoOrderCnt","monthNoDoOrderCnt","allOrderCnt");
	//项目编号 
	ProjectUtil.BindProjectList(bodyId,"projectid",{},function(data){
            $("#projectName").textbox("setValue",data["projectName"]);
	});
	//订单总数值改变事件
	$("#orderCnt").numberbox({onChange: function(newValue, oldValue){
		var orderPre=$("#orderPre").val();
		if(newValue==0||newValue==""){
			$("#orderBegin").textbox("setValue","");
			$("#orderEnd").textbox("setValue","");
		}
		else{
			$("#orderBegin").textbox("setValue",(orderPre+"1"));
			$("#orderEnd").textbox("setValue",(orderPre+newValue));
		}
		
	}});

	//批量创建订单
	$("#batch_Create_EVM").click(function(){
		var orderPre=$("#orderPre").val();
		var projectid=$("#projectid").combobox("getValue");
		var orderCnt=$("#orderCnt").numberbox("getValue");
		if(!maxOrderCntValdiate(orderCnt)){
			   BasePage.showInfoMessage("批量生成订单数不可超过20!");
			   return;
		}
		var para={};
		para["batchOrderPre"]=orderPre;
		para["projectId"]=projectid;
		para["batchOrderCnt"]=orderCnt;
		BasePage.sendPostRequest(bodyId,'/orderController.do?method=ajaxBatchInsertOrder',para,function(data){
			if(data.messageType=="error"){
				BasePage.showInfoMessage(data.message);
				return;
			}else{
				alert("创建成功");	
		        $("#batchAdd_window").window("close");
				searchOrder();
				BizPageSt.BindQueryOrderResult(bodyId,"todayNoDoOrderCnt","weekNoDoOrderCnt","monthNoDoOrderCnt","allOrderCnt");
			}
		});
	});
	//今日未处理的订单
	$("#todayNoDoOrderCntDiv").click(function(){
		var queryStat=$.trim($('#queryStat').val());
		$('#queryStat').val("1");
		BasePage.initTablePager("orderDg");
		searchOrder();
	});
	//7日内未处理的订单
	$("#weekNoDoOrderCntDiv").click(function(){
		var queryStat=$.trim($('#queryStat').val());
		$('#queryStat').val("2");
		BasePage.initTablePager("orderDg");
		searchOrder();
	});
	//本月未处理的订单
	$("#monthNoDoOrderCntDiv").click(function(){
		var queryStat=$.trim($('#queryStat').val());
		$('#queryStat').val("3");
		BasePage.initTablePager("orderDg");
		searchOrder();
	});
	//所有未处理完的订单
	$("#allOrderCntDiv").click(function(){
		$('#queryStat').val("4");
		BasePage.initTablePager("orderDg");
		searchOrder();
	});
	init();
	function init(){
		searchOrder();
	} 
	
	function searchOrder(){
		var para = {};
		var options = $('#orderDg').datagrid('getPager').data("pagination").options;  
		var pageNumber = options.pageNumber;
		var pageSize =options.pageSize;
		para["pageNum"] = pageNumber;		
		para["pageSize"] = pageSize;
		var orderNoPara=$.trim($('#orderNoPara').searchbox("getValue"));
		if(orderNoPara!=""){
			para["orderNo"] = orderNoPara;
		}
		var queryStat=$.trim($('#queryStat').val());
		para["queryStat"] = queryStat;
		BasePage.sendPostRequest(bodyId,'/orderController.do?method=ajaxFindOrderPage',para,function(data){	
			if(data.messageType=="error"){
				BasePage.showInfoMessage(data.message);
				return;
			}else{
				if(data== null || data.bizData.total ==0){
					BasePage.showInfoMessage("未查询到订单信息！");
					$("#orderDg").datagrid('loadData', []);
				}else{
					$("#orderDg").datagrid('loadData', data.bizData);
				}
			}
		});
	}
	var orderPaging = $('#orderDg').datagrid('getPager');
    if (orderPaging) {
    	BasePage.pagerServerFilter(orderPaging,searchOrder);
    }
    //	删除
	$("#delOrder").click(function(){
		var row = $("#orderDg").datagrid('getSelected');
		if(row!=null){
			BasePage.confirmMessage("确认删除该订单？",function(flag){
				if(flag){
					var para={};
					var options = $('#orderDg').datagrid('getPager').data("pagination").options;  
					var pageNumber = options.pageNumber;
					var pageSize =options.pageSize;
					para["pageNum"] = pageNumber;		
					para["pageSize"] = pageSize;
					para["orderId"]=row["orderId"];
					para["updDate"]=row["updDate"];
					BasePage.sendPostRequest(bodyId,'/orderController.do?method=ajaxDeleteOrder',para, function(data) {
						if(data.messageType=="error"){
							BasePage.showInfoMessage(data.message);
							return;
						}else{
							BasePage.showInfoMessage("删除成功!");
							searchOrder();
						}
					});
				}
			});
		}else{
			BasePage.showInfoMessage("选择需要删除的订单!");
		}
	});
//	修改
	$("#updOrder").click(function(){
		//相当于校验，跳页发form请求 
		var row = $("#orderDg").datagrid('getSelected');
		if(row!=null){
			var url= BasePage.urlPre+"/orderController.do?method=gotoUpdatePage"; 
			$("#updOrderHref").attr("href",url+"&orderId="+row["orderId"]);
		}else{
			BasePage.showInfoMessage("选择需要修改的订单!");
		}
	});
	//批量增加
	$("#batchAddOrder").click(function(){
		//batchAdd_window
		BasePage.sendPostRequest(bodyId,'/orderController.do?method=ajaxGetOrderNo',{}, function(data) {
			$("#orderPre").val(data.bizData)
			$("#orderBegin").textbox("setValue","");
			$("#orderEnd").textbox("setValue","");
			$("#orderCnt").numberbox("setValue","");
			$("#batchAdd_window").window("open");
		});
		
	});
    $('#orderNoPara').searchbox({
	    searcher:function(value,name){
	    	BasePage.initTablePager("orderDg");
	    	$('#queryStat').val("");
	    	searchOrder();
	    },
	    prompt:'订单编号'
	});
  //打印
	$("#batch_print_EVM").click(function(){
	   $("#batch_print_hidden").empty();
	   var orderPre=$("#orderPre").val();
	   var cnt=$("#orderCnt").numberbox("getValue");
	   if(!maxOrderCntValdiate(cnt)){
		   BasePage.showInfoMessage("批量生成订单数不可超过20!");
		   return;
	   }
	   for(var i=1;i<=cnt;i++){
		   var orderNo=orderPre+i;
		   $("#batch_print_hidden").append("<div style='width:70mm;height:50mm' id='"+orderNo+"'></div>")
		   createEvm(orderNo ,orderNo,150,150);
		   
	   }
	   $("#batch_print_hidden").jqprint({debug: true,operaSupport: true});
     });
	 function maxOrderCntValdiate(_cnt){
		 var cnt=0;
		 cnt=parseInt(_cnt);
		 var max=20;
		 var min=1
		 if(cnt==undefined||cnt<min||cnt>max){
			 return false;
		 } 
		 return true;
	 }
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
 