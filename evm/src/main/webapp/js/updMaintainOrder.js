$(document).ready(function() {
	var bodyId="updMorerBody";
	init();
	//用户类别
	BasePage.BindUserTypeControl("utype");
	//验收状态
	BasePage.BindAccepStatusControl("acceptStatus");
	//维修状态
	BasePage.BindMaintainStatusControl("maintainStatus");
	//故障类别
	FaultTypeUtil.BindFaultTypeComoboxControl("updMorerBody","faultType");
	//创建日期
	BizPageSt.BindQueryAllMOrderStResult(bodyId,"uallNoDoMorderCnt","unoCallBackMorderCnt","udoingMorderCnt","uproblemMorderCnt");
	var crrentDate = BasePage.getNowFormatDate();
	$("#currentDate").html(crrentDate);
	//打开地图
	QmMap.init("mapContainer");
	$("#addCusMap").click(function(){
		var lng=$("#lng").val();
		var lat =$("#lat").val();
	    var _position=[125.3245,43.886841]
	    if((lng!=0||lng!="")&&(lat!=0||lat!="")){
	        	_position=[lng,lat];
        }
        var marker = new AMap.Marker({
            map:QmMap.Map,
            bubble:true,
            position: _position
        });
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
	function init(){
		var para = {};
		para["morderId"] = $("#morderId").val();
		BasePage.sendPostRequest(bodyId,'/morderController.do?method=ajaxFindMorderById',para,function(data){	
			if(data== null || data.bizData!=null){
				init_callback(data.bizData);
			}else{
				BasePage.showInfoMessage("获得更新数据异常");
			}
		});
	}
	//TODO:数据回绑
	function init_callback(maintainOrderObj){
		//初始订单信息
		$("#orderId").textbox("setValue", maintainOrderObj.order["orderNo"]);
		$("#maintainId").textbox("setValue", maintainOrderObj["morderId"]);
		$("#maintainNo").textbox("setValue", maintainOrderObj["morderNo"]);
		$("#hidden_updDate").val(maintainOrderObj["updDate"]);
		//加载上传信息
		morderFile.loadCtrl();
		//初始客户信息
		var cusUpdObj=maintainOrderObj.order.project.custom;
		if(cusUpdObj!=undefined){
			// 客户姓名
			$("#cName").textbox("setValue", cusUpdObj["cname"]);
			//客户电话1
			$("#ctel1").textbox("setValue", cusUpdObj["ctel1"]);
			//客户电话2
			$("#ctel2").textbox("setValue", cusUpdObj["ctel2"]);
			//客户地址
			$("#caddress").textbox("setValue", cusUpdObj["caddress"]);
			$("#lng").val(cusUpdObj["lng"]);
			$("#lat").val(cusUpdObj["lat"]);
			//用户类别
			$("#utype").combobox("setValue", cusUpdObj["ctype"]);
		}
		
		//服务截止日期
		$("#serviceEndDate").datebox("setValue", maintainOrderObj.order.project["serviceEndDate"]);
		//初始运维经理
		var mPmObj = maintainOrderObj.morderPmUser;
		if(mPmObj!=undefined){
			// 运维经理
			$("#maintainName").searchbox("setValue", mPmObj["ufullName"]);
			// 运维经理电话1
			$("#maintainTel1").textbox("setValue", mPmObj["utel1"]);
			// 运维经理电话2
			$("#maintainTel2").textbox("setValue", mPmObj["utel2"]);
		}
		//ID
		$("#maintainPmId").val(maintainOrderObj["maintainPmId"]);
		//初始维修负责人
		var builderPmObj = maintainOrderObj.maintainPmUser;
		if(builderPmObj!=undefined){
			// 维修负责人
			$("#maintainPm").searchbox("setValue", builderPmObj["ufullName"]);
			// 维修负责人电话1
			$("#maintainPmTel1").textbox("setValue", builderPmObj["utel1"]);
			// 维修负责人电话2
			$("#maintainPmTel2").textbox("setValue", builderPmObj["utel2"]); 
		}
		//ID
		$("#morderPmId").val(maintainOrderObj["morderPmId"]);
		//故障描述
		$("#faultDesc").textbox("setValue", maintainOrderObj["faultDesc"]); 
		//预约时间
		$("#appointmentDate").datebox("setValue", maintainOrderObj["appointmentDate"]); 
		//计划完成时间
		$("#planEndDate").datebox("setValue", maintainOrderObj["planEndDate"]); 
		//最后一次进度时间
		$("#lastUpdDate").datebox("setValue",maintainOrderObj["lastUpdDate"]); 
		//维修人数
		$("#maintainWorkerCnt").numberbox("setValue", maintainOrderObj["mworkerCnt"]); 
		//解决方案
		$("#solution").textbox("setValue", maintainOrderObj["solution"]); 
		//派修时间
		$("#maintainDate").datebox("setValue", maintainOrderObj["maintainDate"]); 
		//维修结果描述
		$("#maintainResultDesc").textbox("setValue", maintainOrderObj["maintainResultdesc"]); 
		//维修结果描述
		$("#serviceEndDate").textbox("setValue", maintainOrderObj["serviceEndDate"]); 
		//备注
		$("#maintainRemark").textbox("setValue", maintainOrderObj["maintainRemark"]); 
		//维修状态
		$("#maintainStatus").combobox("setValue", maintainOrderObj["maintainStatus"]); 
		//维修进度
		$("#maintainSpeed").numberbox("setValue", maintainOrderObj["maintainSpeed"]); 
		 
		//故障类别
		$("#faultType").combobox("setValue", maintainOrderObj["faultType"]);
		//验收状态
		$("#acceptStatus").combobox("setValue", maintainOrderObj["acceptStatus"]); 
		//用户评分
		$("#userScore").numberbox("setValue", maintainOrderObj["userScore"]); 
		//验收回访人
		$("#accepteCallMan").textbox("setValue", maintainOrderObj["acceptecallMan"]); 
		//验收回访时间
		$("#callBackDate").datebox("setValue", maintainOrderObj["callbackDate"]); 
		//验收回访时间
		$("#userProposal").textbox("setValue", maintainOrderObj["userProposal"]); 
		//维修开始日期
		$("#maintainBeginDate").datebox("setValue", maintainOrderObj["maintainBeginDate"]); 
		//最后一次进度更新时间
		$("#lastUpdDate").datetimebox("setValue", maintainOrderObj["updDate"]); 
		//FIle
		var files=maintainOrderObj.morderFileList;
		for(var i=0;i<files.length;i++){
			var file=files[i];
			if(file.bizType==1){
				var u=BasePage.urlPre+"/morderController.do?method=download&fileName="+file.fileName+"&filePath="+encodeURIComponent(file.filePath);
				var bizType=1;
				var submitPara=file.filePath+"&"+file.fileName+"&"+bizType;
				var content="<div><input type=\"hidden\" value='"+submitPara+"'/><a   href='"+u+"' >"+file.fileName+"</a><a onclick=javascript:deleteFileDiv(this)>删除</a></div> ";
				$("#worDocDiv").append(content);
			} 
		}
	};
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
		para["updDate"]=$("#hidden_updDate").val();
		//订单编号
		para["orderId"]=$("#orderId").combobox("getValue");
		//运维订单编号
		para["morderId"]=$("#morderId").val();
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
		para["mworkerCnt"]=$("#maintainWorkerCnt").numberbox("getValue");
		//维修开始日期
		para["maintainBeginDate"]=$("#maintainBeginDate").datebox("getValue");
		//维修状态
		para["maintainStatus"]=$("#maintainStatus").combobox("getValue");
		//维修进度
		para["maintainSpeed"]=$("#maintainSpeed").numberbox("getValue");
		//故障类别
		para["faultType"]=$("#faultType").combobox("getValue");
		//维修负责人ID
		para["maintainPmId"]=$("#maintainPmId").val();
		//验收状态
		para["acceptStatus"]=$("#acceptStatus").combobox("getValue");
		//验收回访人
		para["acceptecallMan"]=$("#accepteCallMan").textbox("getValue");
		//验收回访时间
		para["callbackDate"]=$("#callBackDate").datebox("getValue");
		// 解决方案
		para["solution"]=$("#solution").textbox("getValue");
		//维修结果描述
		para["maintainResultdesc"]=$("#maintainResultDesc").textbox("getValue");
		//备注
		para["maintainRemark"]=$("#maintainRemark").textbox("getValue");
		//用户评分
		para["userScore"]=$("#userScore").numberbox("getValue");
		//用户评价
		para["userProposal"]=$("#userProposal").textbox("getValue");
		var paths=getFilePath();
		para["fileStrs"]=paths;
		BasePage.sendPostRequest(bodyId,'/morderController.do?method=ajaxUpdateMorder',para,function(data){
			if(data.messageType=="error"){
				BasePage.showInfoMessage(data.message);
				return;
			}else{
				alert("保存成功！");
				$("#retrnManageForm").submit();
			}
		});
	};
	function getFilePath(){
		var Str="";
		$("#worDocDiv input[type=hidden]").each(function(index,el){
				Str+=el.value+"@"
		});
		return Str;
	}
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
		UserUtil.QueryUserList(bodyId,para,function(data){
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
