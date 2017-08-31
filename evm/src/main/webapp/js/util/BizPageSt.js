var BizPageSt={}||BizPageSt;
(function(){
	var projectSturl="/pageDatastController.do?method=ajaxProjectPageST";
	BizPageSt.BindQueryProjectStResult=function(bodyId,ctrl1,ctrl2,ctrl3,ctrl4,callBack){
		BasePage.sendPostRequest(bodyId,projectSturl,{},function(data){
			if(ctrl1!=undefined){
				$('#'+ctrl1).text(data.bizData.todayStopWorkCnt);
			}
			if(ctrl2!=undefined){
				$('#'+ctrl2).text(data.bizData.mothSingProjectCnt);
			}
			if(ctrl3!=undefined){
				$('#'+ctrl3).text(data.bizData.weekStopWorkCnt);
			}
			if(ctrl4!=undefined){
				$('#'+ctrl4).text(data.bizData.allNonDealOrderCnt);
			}
		});
	};
	var mOrderSturl="/pageDatastController.do?method=ajaxAllMOrderST";
	BizPageSt.BindQueryAllMOrderStResult=function(bodyId,ctrl1,ctrl2,ctrl3,ctrl4,callBack){
		BasePage.sendPostRequest(bodyId,mOrderSturl,{},function(data){
			if(ctrl1!=undefined){
				$('#'+ctrl1).text(data.bizData.allNoDoMorderCnt);
			}
			if(ctrl2!=undefined){
				$('#'+ctrl2).text(data.bizData.noCallBackMorderCnt);
			}
			if(ctrl3!=undefined){
				$('#'+ctrl3).text(data.bizData.doingMorderCnt);
			}
			if(ctrl4!=undefined){
				$('#'+ctrl4).text(data.bizData.problemMorderCnt);
			}
		});
	};
	var cusSturl="/pageDatastController.do?method=ajaxCustomPageST";
	BizPageSt.BindQueryCustResult=function(bodyId,ctrl1,ctrl2,ctrl3,ctrl4,callBack){
		BasePage.sendPostRequest(bodyId,cusSturl,{},function(data){
			if(ctrl1!=undefined){
				$('#'+ctrl1).text(data.bizData.todayStopServiceCnt);
			}
			if(ctrl2!=undefined){
				$('#'+ctrl2).text(data.bizData.threeDayStopServiceCnt);
			}
			if(ctrl3!=undefined){
				$('#'+ctrl3).text(data.bizData.monthStopServiceCnt);
			}
			if(ctrl4!=undefined){
				$('#'+ctrl4).text(data.bizData.allCustomerCnt);
			}
		});
	};
	var orderSturl="/pageDatastController.do?method=ajaxOrderPageST";
	BizPageSt.BindQueryOrderResult=function(bodyId,ctrl1,ctrl2,ctrl3,ctrl4,callBack){
		BasePage.sendPostRequest(bodyId,orderSturl,{},function(data){
			if(ctrl1!=undefined){
				$('#'+ctrl1).text(data.bizData.todayNoDoOrderCnt);
			}
			if(ctrl2!=undefined){
				$('#'+ctrl2).text(data.bizData.weekNoDoOrderCnt);
			}
			if(ctrl3!=undefined){
				$('#'+ctrl3).text(data.bizData.monthNoDoOrderCnt);
			}
			if(ctrl4!=undefined){
				$('#'+ctrl4).text(data.bizData.allOrderCnt);
			}
		});
	};
})();

 