$(document).ready(function() {
	var bodyId="addMorderBody";
	//用户类别
	BasePage.BindUserTypeControl("utype");
	//验收状态
	BasePage.BindAccepStatusControl("acceptStatus");
	//维修状态
	BasePage.BindMaintainStatusControl("maintainStatus");
	//绑定订单
	OrderUtil.BindOrderIdComoboxControl("addMorderBody","orderId");
	//故障类别
	FaultTypeUtil.BindFaultTypeComoboxControl("addMorderBody","faultType");
	BizPageSt.BindQueryAllMOrderStResult(bodyId,"aallNoDoMorderCnt","anoCallBackMorderCnt","adoingMorderCnt","aproblemMorderCnt");
	//
	//创建日期
	var crrentDate = BasePage.getNowFormatDate();
	$("#currentDate").html(crrentDate);
	//打开地图
	QmMap.init("mapContainer");
	$("#addCusMap").click(function(){
		QmMap.search($("#caddress").textbox("getValue"));
		$("#map_window").window("open");
	});
	//订单编号选择事件
	$("#orderId").combobox({onChange: function(newValue, oldValue){
		var para = {};
		para["orderId"] =newValue;
		BasePage.sendPostRequest(bodyId,'/orderController.do?method=ajaxFindOrderById',para,function(data){	
			if(data== null || data.bizData!=null){
				// 客户姓名
				$("#cName").textbox("setValue", data.bizData.project.custom["cname"]);
				//客户电话1
				$("#ctel1").textbox("setValue", data.bizData.project.custom["ctel1"]);
				//客户电话2
				$("#ctel2").textbox("setValue", data.bizData.project.custom["ctel2"]);
				//客户地址
				$("#caddress").textbox("setValue", data.bizData.project.custom["caddress"]);
				$("#lng").val(data.bizData.project.custom["lng"]);
				$("#lat").val(data.bizData.project.custom["lat"]);
				//用户类别
				$("#utype").combobox("setValue", data.bizData.project.custom["utype"]);
				//服务截止日期
				$("#serviceEndDate").datebox("setValue",data.bizData.project["serviceEndDate"]);
			}
		});
	}});
	//用户window 取消
	$("#user_window_cancel").click(function(){
		$("#user_window").dialog("close");
	});
	//用户 window 确定
	$("#user_window_OK").click(function() {
		var updObj = $("#userDg").datagrid('getSelected');
		var bindType=$("#openType").val();
		if(updObj!=null){
			if(bindType=="0"){
				// 运维经理
				$("#maintainName").searchbox("setValue", updObj["ufullName"]);
				// 运维经理电话1
				$("#maintainTel1").textbox("setValue", updObj["utel1"]);
				// 运维经理电话2
				$("#maintainTel2").textbox("setValue", updObj["utel2"]);
				//运维经理ID
				$("#morderPmId").val(updObj["uid"]);
			}else{
				// 维修负责人
				$("#maintainPm").searchbox("setValue", updObj["ufullName"]);
				// 维修负责人电话1
				$("#maintainPmTel1").textbox("setValue", updObj["utel1"]);
				// 维修负责人电话2
				$("#maintainPmTel2").textbox("setValue", updObj["utel2"]); 
				// 维修负责人ID
				$("#maintainPmId").val(updObj["uid"]);
			}
		}
		$("#user_window").dialog("close");
	});
	//保存按钮
	$("#saveMorderBtn").click(function(){
		saveMorder();
	});
	//TODO:验证，未完，需要补充
	function saveValidate(){
		return MorderUtil.submitValidate();
	};
	//保存request
	function saveMorder(){
		if(!saveValidate()){
			return;
		}
		var para={};
		//订单编号
		var _orderId=$("#orderId").combobox("getValue");
		para["orderId"]=_orderId;
		if(_orderId==""){
			BasePage.showInfoMessage("请选择订单编号！");
			return;
		}
		//运维订单编号
		para["morderNo"]=$("#maintainNo").textbox("getValue");
		//运维经理ID
		para["morderPmId"]=$("#morderPmId").val();
		//派修时间
		para["maintainDate"]=$("#maintainDate").datebox("getValue");
		//故障描述
		para["faultDesc"]=$("#faultDesc").textbox("getValue");
		//预约时间
		para["appointmentDate"]=$("#appointmentDate").datebox("getValue");
		//计划完成时间
		para["planEndDate"]=$("#planEndDate").datebox("getValue");
		//维修人数
		var _maintainWorkerCnt=$("#maintainWorkerCnt").numberbox("getValue");
		if(_maintainWorkerCnt==""){
			_maintainWorkerCnt=0;
		}
		para["mworkerCnt"]=_maintainWorkerCnt;
		//维修开始日期
		para["maintainBeginDate"]=$("#maintainBeginDate").datebox("getValue");
		//维修状态
		para["maintainStatus"]=$("#maintainStatus").combobox("getValue");
		//维修进度
		var _maintainSpeed=$("#maintainSpeed").numberbox("getValue");
		if(_maintainSpeed==""){
			_maintainSpeed=0;
		}
		para["maintainSpeed"]=_maintainSpeed;
		//故障类别
		para["faultType"]=$("#faultType").combobox("getValue");
		//维修负责人ID
		para["maintainPmId"]=$("#maintainPmId").val();
		//验收状态
		para["acceptStatus"]=$("#acceptStatus").combobox("getValue");
		//验收回访人
		para["acceptecallMan"]=$.trim($("#accepteCallMan").textbox("getValue"));
		//验收回访时间
		para["callbackDate"]=$("#callBackDate").datebox("getValue");
		// 解决方案
		para["solution"]=$.trim($("#solution").textbox("getValue"));
		//维修结果描述
		para["maintainResultdesc"]=$.trim($("#maintainResultDesc").textbox("getValue"));
		//备注
		para["maintainRemark"]=$.trim($("#maintainRemark").textbox("getValue"));
		//用户评分
		var _userScore=$("#userScore").numberbox("getValue");
		if(_userScore==""){
			_userScore=0;
		}
		para["userScore"]=_userScore;
		//用户评价
		para["userProposal"]=$.trim($("#userProposal").textbox("getValue"));
		//用户评价
		para["lastUpdDate"]=$("#lastUpdDate").datebox("getValue");
		BasePage.sendPostRequest('addMorderBody','/morderController.do?method=ajaxInsertMorder',para,function(data){
			if(data.messageType=="error"){
				BasePage.showInfoMessage(data.message);
				return;
			}else{
				alert("保存成功！");
				$("#retrnManageForm").submit();
			}
		});
	};
	//运维经理
	$('#maintainName').searchbox({
	    searcher:function(value,name){
	    	$("#openType").val("0");
	    	$('#userSearch').searchbox("setValue","");
	    	$("#user_window").window("open");
	    	searchUserList();
	    },
	    prompt:'用户姓名'
	});
	//维修负责人
	$('#maintainPm').searchbox({
	    searcher:function(value,name){
	    	$("#openType").val("1");
	    	$('#userSearch').searchbox("setValue","");
	    	$("#user_window").window("open");
	    	searchUserList();
	    },
	    prompt:'用户姓名'
	});
	$('#userSearch').searchbox({
	    searcher:function(value,name){
	    	searchUserList();
	    },
	    prompt:'用户姓名'
	});
	function searchUserList(){
		var para = {};
		var options = $('#userDg').datagrid('getPager').data("pagination").options;  
		var pageNumber = options.pageNumber;
		var pageSize =options.pageSize;
		para["pageNum"] = pageNumber;		
		para["pageSize"] = pageSize;
		var uname=$.trim($('#userSearch').searchbox("getValue"));
		if(uname!=""){
			para["ufullName"]=uname;	
		}
		UserUtil.QueryUserList("addMorderBody",para,function(data){
			if(data== null || data.bizData.total ==0){
				BasePage.showInfoMessage(BasePage.noSearchData);
			}else{
				$("#userDg").datagrid('loadData', data.bizData);
			}
		});
	};
	  //用户表分页
    var userDgPage = $('#userDg').datagrid('getPager');
    if (userDgPage) {
    	BasePage.pagerServerFilter(userDgPage,searchUserList);
    }
    $("#goHist").click(function(){
		$("#retrnManageForm").submit();
	});
});
 
 