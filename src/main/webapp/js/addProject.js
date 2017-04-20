$(document).ready(function() {
	var bodyId="addProjectBody";
	//绑定统计参数
	BizPageSt.BindQueryProjectStResult(bodyId,"todayStopWorkCnt","mothSingProjectCnt","weekStopWorkCnt","allProjectCnt");

	//客户经理性别
	BasePage.BindSexControl("cmSex");
	//项目经理性别
	BasePage.BindSexControl("pmSex");
	//绑定建设方式
	BasePage.BindBuildTypeeControl("buildType");
	//绑定项目类型
	BasePage.BindProjectTypeControl("projectType");
	//绑定项目状态
	BasePage.BindProjectStatControl("proStat");
	//增加设备、删除设备的变色
	$("#deviceDelBtn").hover(function(){
	    $("#deviceDelBtn").css("background-color","#1e18fe");
	},function(){
	    $("#deviceDelBtn").css("background-color","#060192");
	});
	$("#deviceAddBtn").hover(function(){
	    $("#deviceAddBtn").css("background-color","#1e18fe");
	},function(){
	    $("#deviceAddBtn").css("background-color","#060192");
	});
	//关联用户查询Window
	$("#rel_user_window").window({onBeforeOpen:function(){
		$('#relUserSearch').searchbox("setValue","");
	}});
	//用户查询Window
	$("#user_window").window({onBeforeOpen:function(){
		$('#userSearch').searchbox("setValue","");
	}});
	//客户window
	$("#customer_window").window({onBeforeOpen:function(){
		$('#customSearch').searchbox("setValue","");
	}});
	//合同ID变化，更新项目编号；
	$("#contractId").textbox({"onChange":function(newValue,oldValue){
		$("#projectNo").textbox("setValue",newValue);
	}});
	//创建日期
	var crrentDate = BasePage.getNowFormatDate();
	$("#currentDate").html(crrentDate);
	//打开地图
	QmMap.init("mapContainer");
	$("#addCusMap").click(function(){
		QmMap.Map.clearMap();
		var lat=$("#cusAddrLat").val();
        var lng=$("#cusAddrLng").val();
        if(lat==0||lat==undefined){
        	lat=43.886841;
        }
        if(lng==0||lng==undefined){
        	lng=125.3245;
        }
    	var marker = new AMap.Marker({
            map:QmMap.Map,
            bubble:true,
            position:[lng,lat]
        });
    	QmMap.Map.panTo([lng,lat]);
		$("#map_window").window("open");
	});
	//客户window 取消
	$("#customer_window_cancel").click(function() {
		$("#customer_window").dialog("close");
	});
	//客户window 确定
	//客户window 确定
	$("#customer_window_OK").click(function() {
		var updObj = $("#customerDg").datagrid('getSelected');
		if(updObj!=null){
			// 客户编号
			$("#cusId").val(updObj["cno"]);
			// 客户姓名
			$("#cname").searchbox("setValue", updObj["cname"]);
			// 客户地址
			$("#caddress").textbox("setValue", updObj["caddress"]);
			$("#cusAddrLat").val(updObj["lat"]);
			$("#cusAddrLng").val(updObj["lng"]);
			// 客户电话1
			$("#ctel1").textbox("setValue", updObj["ctel1"]);
			// 客户电话2
			$("#ctel2").textbox("setValue", updObj["ctel2"]);
			// 所属分局
			$("#substation").textbox("setValue", updObj["substation"]);
			// 所属派出所
			$("#policestation").textbox("setValue", updObj["policestation"]);
		} 
		$("#customer_window").dialog("close");
	});
	 
	//用户window 取消
	$("#user_window_cancel").click(function(){
		$("#user_window").dialog("close");
	});
	//用户 window 确定
	$("#user_window_OK").click(function() {
		var updObj = $("#userDg").datagrid('getSelected');
		var bindType=$("#openType").val();
		if(updObj!=null){
			if(bindType=="1"){
				// 项目经理
				$("#pmName").searchbox("setValue", updObj["ufullName"]);
				// 用户编号
				$("#pmId").val(updObj["uid"]);
				// 客户电话1
				$("#pmTel").textbox("setValue", updObj["utel1"]);
				// 项目经理性别
				$("#pmSex").combobox("setValue", updObj["usex"]);
			}else{
				$("#cmName").searchbox("setValue", updObj["ufullName"]);
				// 用户编号
				$("#cmId").val(updObj["uid"]);
				// 客户电话1
				$("#cmTel").textbox("setValue", updObj["utel1"]);
				// 项目经理性别
				$("#cmSex").combobox("setValue", updObj["usex"]);
			}
		}
		$("#user_window").dialog("close");
	});
	//提交验证
	function submitValidate(){
		var projectid=$.trim($("#projectNo").textbox("getValue"));
		if(projectid.length==0){
			BasePage.showInfoMessage("项目编号必输！");
	    	return false;
		}
		if(projectid.length>20){
			BasePage.showInfoMessage("您输入的项目编号超长！");
	    	return false;
		}
		var projectName=$.trim($("#projectName").textbox("getValue"));
		if(projectName.length==0){
			BasePage.showInfoMessage("项目名称必输！");
	    	return false;
		}
		if(projectName.length>20){
			BasePage.showInfoMessage("您输入的项目名称超长！");
	    	return false;
		}
		var contractId=$.trim($("#contractId").textbox("getValue"));
		if(contractId!=projectid){
			BasePage.showInfoMessage("您输入的项目编号与合同号不一致！");
	    	return false;
		}
		return true;
	}
	//保存项目信息
	$("#saveProjectBtn").click(function(){
		if(!submitValidate()){
			return;
		}
		var para = {};
		var projectNo=$("#projectNo").textbox("getValue");
		para["projectNo"] = projectNo
		para["projectName"] =  $("#projectName").textbox("getValue");
		para["cusId"] =  $("#cusId").val();
		para["cmId"] =  $("#cmId").val();
		para["pmId"] =  $("#pmId").val();
		para["projectType"] = $("#projectType").combobox("getValue");
		var contractId=$("#contractId").val();
		para["contractId"] =  $("#contractId").val();
		if(projectNo!=contractId){
			para["projectNo"] =contractId;
		}
		var signDate=$("#signDate").datebox("getValue")
		if(signDate!=""){
			para["signDate"] = $("#signDate").datebox("getValue");
		}
		var contractSum=$("#contractSum").numberbox("getValue");
		if(contractSum==""){
			contractSum=0;
		}
		para["contractSum"] = contractSum ;
		para["payType"] = $("#payType").textbox("getValue");
		var workEndDate= $("#pEndDate").datebox("getValue");
		if(workEndDate!=""){
			para["workEndDate"] = $("#pEndDate").datebox("getValue");
		}
		var workStartDate= $("#pStartDate").datebox("getValue");
		if(workStartDate!=""){
			para["workStartDate"] =workStartDate;
		}
		var yearPay=$("#pYearSum").numberbox("getValue");
		if(yearPay==""){
			yearPay=0;
		}
		para["yearPay"] = yearPay;
		var serviceEndDate=$("#serviceEndDate").datebox("getValue");
		if(serviceEndDate!=""){
			para["serviceEndDate"] =serviceEndDate;
		}
		var payDate= $("#payDate").datebox("getValue");
		if(payDate!=""){
			para["payDate"] = $("#payDate").datebox("getValue");
		}
		para["buildType"] = $("#buildType").combobox("getValue");
		var joinDate=  $("#joinDate").datebox("getValue");
		if(joinDate!=""){
			para["joinDate"] = joinDate;
		}
		var stopDate= $("#stopDate").datebox("getValue");
		if(stopDate!=""){
			para["stopDate"] = stopDate;
			
		}
		para["projectRemark"] = $("#contractRemarks").textbox("getValue");
		para["relUserStr"] = $("#selecteIdUserId").val();
		//项目状态
		para["proStat"]=$("#proStat").combobox("getValue");
		//TODO:
		var deviceRows=$("#deviceDg").datagrid("getRows");
		var lineDeviceRows=$("#lineDeviceDg").datagrid("getRows");
		var helpDeviceRows=$("#helpDeviceDg").datagrid("getRows");
		deviceRows=deviceRows.concat(lineDeviceRows);
		deviceRows=deviceRows.concat(helpDeviceRows);
		para["deviceStr"]=JSON.stringify(deviceRows);
		BasePage.sendPostRequest('addProjectBody','/projectController.do?method=ajaxInsertProject',para,function(data){	
			  if(data.messageType=="error"){
					BasePage.showInfoMessage(data.message);
					return;
				}else{
					alert("保存成功！");
					$("#gotoProjectManageForm").submit();
			}
		});
	});
	$('#cname').searchbox({
	    searcher:function(value,name){
	    	$("#customer_window").window("open");
	    	searchCustomList();
	    },
	    prompt:'客户名称'
	});
	//客户查询
	$('#customSearch').searchbox({
	    searcher:function(value,name){
	    	searchCustomList();
	    },
	    prompt:'客户名称'
	});
	//客户经理查询
	$('#cmName').searchbox({
	    searcher:function(value,name){
	    	$("#openType").val("0");
	    	$("#user_window").window("open");
	    	searchUserList();
	    },
	    prompt:'系统用户'
	});
	//项目经理查询
	$('#pmName').searchbox({
	    searcher:function(value,name){
	    	$("#openType").val("1");
	    	$("#user_window").window("open");
	    	searchUserList();
	    },
	    prompt:'系统用户'
	});

	$('#userSearch').searchbox({
	    searcher:function(value,name){
	    	searchUserList();
	    },
	    prompt:'系统用户'
	}); 
	searchUserList=function(){
		var para = {};
		var options = $('#userDg').datagrid('getPager').data("pagination").options;  
		var pageNumber = options.pageNumber;
		var pageSize =options.pageSize;
		para["pageNum"] = pageNumber;		
		para["pageSize"] = pageSize;
		var uname=$('#userSearch').searchbox("getValue");
		if(uname!=""){
			para["ufullName"]=uname;	
		}
		$("#userDg").datagrid('loadData', []);
		BasePage.sendPostRequest('userDg','/userController.do?method=ajaxFindUserList',para,function(data){	
			if(data== null || data.bizData.total ==0){
				BasePage.showInfoMessage(BasePage.noSearchData);
			}else{
				$("#userDg").datagrid('loadData', data.bizData);
			}
		});
	};
	searchCustomList = function(){
		var para = {};
		var options = $('#customerDg').datagrid('getPager').data("pagination").options;  
		var pageNumber = options.pageNumber;
		var pageSize =options.pageSize;
		para["pageNum"] = pageNumber;		
		para["pageSize"] = pageSize;
		var cname=$('#customSearch').searchbox("getValue");
		if(cname!=""){
			para["cname"]=cname;	
		}
		$("#customerDg").datagrid('loadData', []);
		BasePage.sendPostRequest('customerDg','/customController.do?method=ajaxFindCustom',para,function(data){	
			if(data== null || data.bizData.total ==0){
				BasePage.showInfoMessage(BasePage.noSearchData);
			}else{
				$("#customerDg").datagrid('loadData', data.bizData);
			}
		});
	}
	//客户表分页
	var customerDgPage = $('#customerDg').datagrid('getPager');
    if (customerDgPage) {
    	BasePage.pagerServerFilter(customerDgPage,searchCustomList);
    }
  //用户表分页
    var userDgPage = $('#userDg').datagrid('getPager');
    if (userDgPage) {
    	BasePage.pagerServerFilter(userDgPage,searchUserList);
    }
  //设备管理：
    
  //关联用户
  $("#rel_user_window").window("close"); 
  $('#relUsers').searchbox({
	    searcher:function(value,name){
	    	$("#rel_user_window").window("open");
	    	searchUserListNoPage();
	    },
	    prompt:'系统用户'
	});
  $('#relUserSearch').searchbox({
	    searcher:function(value,name){
	    	 searchUserListNoPage();
	    },
	    prompt:'系统用户'
	});
  searchUserListNoPage=function(){
		var para = {};
		var uname=$('#relUserSearch').searchbox("getValue");
		if(uname!=""){
			para["ufullName"]=uname;	
		}
		$("#relUserDg").datagrid('loadData', []);
		BasePage.sendPostRequest('relUserDg','/userController.do?method=ajaxFindUserList',para,function(data){	
			if(data== null || data.bizData.total ==0){
				BasePage.showInfoMessage(BasePage.noSearchData);
			}else{
				$("#relUserDg").datagrid('loadData', data.bizData);
			}
			relUserSelect();
		});
	};
	function relUserSelect(){
		var selectUserId=$("#selecteIdUserId").val();
		var arr=selectUserId.split(",")
		for(var i=0;i<arr.length;i++){
			var rows=$("#relUserDg").datagrid('getRows');
			for(var j=0;j<rows.length;j++){
				if(parseInt(arr[i])==rows[j].uid){
					var rowIndex=$("#relUserDg").datagrid('getRowIndex',rows[j]);
					$("#relUserDg").datagrid('selectRow',rowIndex);
				}
			}
			
		}
	}
	//用户window 取消
	$("#reluser_window_cancel").click(function(){
		$("#rel_user_window").dialog("close");
		
	});
	//用户window 确定
	$("#reluser_window_OK").click(function(){
		$("#rel_user_window").dialog("close");
		var selectUser="";
		var selectUserId="";
		var row = $("#relUserDg").datagrid('getSelections');
		for(var i=0;i<row.length;i++){
			selectUser=selectUser+row[i]["ufullName"]+",";
			selectUserId=selectUserId+row[i]["uid"]+",";
		}
		$("#relUsers").searchbox("setValue",selectUser);
		$("#selecteIdUserId").val(selectUserId);
	});
	$("#goHist").click(function(){
		$("#gotoProjectManageForm").submit();
	});
	//已选设备搜索
    $('#selectedDeviceSearch').searchbox({
  	    searcher:function(value,name){
  	    } 
  	});
    //未选设备搜索
    $('#unSelectedDeviceSearch').searchbox({
  	    searcher:function(value,name){
  	    } 
  	});
});
 