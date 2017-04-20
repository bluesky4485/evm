$(document).ready(function() {	
	//绑定统计参数
	BizPageSt.BindQueryProjectStResult('updProjectBody',"todayStopWorkCnt","mothSingProjectCnt","weekStopWorkCnt","allProjectCnt");

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
	//处理掉按钮变色
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
    	QmMap.Map.panTo([lng,lat])
		$("#map_window").window("open");
	});
	var crrentDate = BasePage.getNowFormatDate();
	$("#currentDate").html(crrentDate);
	//客户window 取消
	$("#customer_window_cancel").click(function() {
		$("#customer_window").dialog("close");
	});
	//客户window 确定
	$("#customer_window_OK").click(function() {
		var updObj = $("#customerDg").datagrid('getSelected');
		if(updObj!=null){
			// 客户编号
			$("#cusId").val( updObj["cno"]);
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
		}else{
			BasePage.showInfoMessage("选择需要删除的客户!");
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
		var projectid=$.trim($("#projectid").textbox("getValue"));
		if(projectid.length==0){
			BasePage.showInfoMessage("项目编号必输！");
	    	return false;
		}
		if(projectid.length>20){
			BasePage.showInfoMessage("您输入的项目编号超长！");
	    	return false;
		}
		var projectName=$.trim($("#projectName").textbox("getValue"));
		if(projectid.length>20){
			BasePage.showInfoMessage("项目名称必输！”");
	    	return false;
		}
		if(projectid.length>20){
			BasePage.showInfoMessage("您输入的项目名称超长！”");
	    	return false;
		}
		var contractId=$.trim($("#contractId").textbox("getValue"));
		if(contractId!=projectid){
			BasePage.showInfoMessage("您输入的项目编号与合同号不一致！");
	    	return false;
		}
	}
	init();
	function init(){
		var para = {};
		para["projectId"] = $("#projectId").val();
		BasePage.sendPostRequest('updProjectBody','/projectController.do?method=ajaxFindProjectById',para,function(data){	
			if(data== null || data.bizData!=null){
				init_callback(data.bizData);
			}else{
				BasePage.showInfoMessage("获得更新数据异常");
			}
		});
	}
    function init_callback(updObj){
    	//TODO:测试用，应该查询得到
    	// 项目编号
		$("#projectNo").searchbox("setValue", updObj["projectNo"]);
		// 项目名称
		$("#projectName").textbox("setValue", updObj["projectName"]);
		//更新时间
		$("#hidden_updDate").val(updObj["updDate"]);
		if(updObj.custom!=null){
			//客户ID
			$("#cusId").val(updObj["cusId"]);
			// 客户姓名
			$("#cname").searchbox("setValue", updObj.custom["cname"]);
			// 客户地址
			$("#caddress").textbox("setValue", updObj.custom["caddress"]);
			$("#cusAddrLat").val(updObj.custom["lat"]);
			$("#cusAddrLng").val(updObj.custom["lng"]);
			// 客户电话1
			$("#ctel1").textbox("setValue", updObj.custom["ctel1"]);
			// 客户电话2
			$("#ctel2").textbox("setValue", updObj.custom["ctel2"]);
			// 所属分局
			$("#substation").textbox("setValue", updObj.custom["substation"]);
			// 所属派出所
			$("#policestation").textbox("setValue", updObj.custom["policestation"]);
		}
		if(updObj.pmUser!=null){
			//项目经理ID
			$("#pmId").val(updObj["pmId"]);
			//项目经理
			$("#pmName").textbox("setValue", updObj.pmUser.ufullName);
			//项目经理电话1
			$("#pmTel").textbox("setValue", updObj.pmUser.utel1);
			//项目经理性别
			$("#pmSex").combobox("setValue",updObj.pmUser.usex);
		}
		if(updObj.cmUser!=null){
			//客户经理ID
			$("#cmId").val(updObj["cmId"]);
			//客户经理
			$("#cmName").textbox("setValue", updObj.cmUser.ufullName);
			//客户经理电话
			$("#cmTel").textbox("setValue", updObj.cmUser.utel1);
			//客户经理性别
			$("#cmSex").combobox("setValue", updObj.cmUser.usex);
		}
		//项目分类
		$("#projectType").combobox("setValue", updObj["projectType"]);
		//--------------------------------------------------------------
		//合同编号
		$("#contractId").textbox("setValue", updObj["contractId"]);
		//签订日期
		$("#signDate").datebox("setValue", updObj["signDate"]);
		//合同金额
		$("#contractSum").numberbox("setValue", updObj["contractSum"]);
		//------------------------------------------------------------------
		//缴费类型
		$("#payType").textbox("setValue", updObj["payType"]);
		//工期截止日期
		$("#pEndDate").datebox("setValue", updObj["workEndDate"]);
		//开工日期
		$("#pStartDate").datebox("setValue", updObj["workStartDate"]);
		//------------------------------------------------------------------
		//年服务费
		$("#pYearSum").numberbox("setValue", updObj["yearPay"]);
		//服务截止日期
		$("#serviceEndDate").datebox("setValue", updObj["serviceEndDate"]);
		//缴费日期
		$("#payDate").datebox("setValue", updObj["payDate"]);
		//------------------------------------------------------------------
		//建设方式
		$("#buildType").combobox("setValue", updObj["buildType"]);
		//入网日期
		$("#joinDate").datebox("setValue", updObj["joinDate"]);
		//停机日期
		$("#stopDate").datebox("setValue", updObj["stopDate"]);
		//------------------------------------------------------------------
		//备注
		$("#contractRemarks").textbox("setValue", updObj["projectRemark"]);
		//备注
		$("#proStat").combobox("setValue", updObj["proStat"]);
		 if(updObj.relUserList!=null){
			 var relUserId="";
			 var relUserName="";
			 for(var i=0;i<updObj.relUserList.length;i++){
				 relUserId+=updObj.relUserList[i].userId+",";
				 relUserName+=updObj.relUserList[i].ufullName+",";
			 }
			 $("#selecteIdUserId").val(relUserId);
			 $("#relUsers").searchbox("setValue",relUserName);
		 }
		 
		//
		var deviceList=updObj["deviceList"];
		var deviceRows=[];
		var lineDeviceRows=[];
		var helpDeviceRows=[];
		for(var i=0;deviceList!=undefined &&i<deviceList.length;i++){
			var row=deviceList[i];
			if(row["deviceClassType"]==1){
				deviceRows.push(row);
			}else if(row["deviceClassType"]==2){
				lineDeviceRows.push(row);
			}else if(row["deviceClassType"]==3){
				helpDeviceRows.push(row);
			}
		}
		//带回设备信息
		$("#deviceDg").datagrid('loadData', deviceRows);
		$("#lineDeviceDg").datagrid('loadData', lineDeviceRows);
		$("#helpDeviceDg").datagrid('loadData', helpDeviceRows);
    }
    //保存
    $("#updSaveBtn").click(function(){
    	var para = {};
		para["projectId"] = $("#projectId").val();
		para["projectNo"] = $("#projectNo").val();
		para["projectName"] =  $("#projectName").val();
		para["updDate"] =  $("#hidden_updDate").val();
		
		para["cusId"] =  $("#cusId").val();
		para["cmId"] =  $("#cmId").val();
		para["pmId"] =  $("#pmId").val();
		para["projectType"] = $("#projectType").combobox("getValue");
		
		para["contractId"] =  $("#contractId").val();
		para["signDate"] = $("#signDate").datebox("getValue");
		var contractSum= $("#contractSum").numberbox("getValue");
		if(contractSum==""){
			contractSum=0;
		}
		para["contractSum"] =  contractSum;
		para["payType"] = $("#payType").textbox("getValue");
		para["workEndDate"] = $("#pEndDate").datebox("getValue");
		para["workStartDate"] = $("#pStartDate").datebox("getValue");
		var yearPay= $("#pYearSum").numberbox("getValue");
		if(yearPay==""){
			yearPay=0;
		}
		para["yearPay"] = yearPay;
		
		para["serviceEndDate"] = $("#serviceEndDate").datebox("getValue");
		para["payDate"] = $("#payDate").datebox("getValue");
		
		para["buildType"] = $("#buildType").combobox("getValue");
		para["joinDate"] = $("#joinDate").datebox("getValue");
		para["stopDate"] = $("#stopDate").datebox("getValue");
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
		BasePage.sendPostRequest('updProjectBody','/projectController.do?method=ajaxUpdateProject',para,function(data){
			if(data.messageType=="error"){
				BasePage.showInfoMessage(data.message);
				return;
			}else{
				alert("更新成功！");
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
	//项目经理查询
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
		if(cname!=""){
			para["ufullName"]=uname;	
		}
		$("#userDg").datagrid('loadData', []);
		BasePage.sendPostRequest('user_window','/userController.do?method=ajaxFindUserList',para,function(data){	
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
		BasePage.sendPostRequest('customer_window','/customController.do?method=ajaxFindCustom',para,function(data){	
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
    //--------------------
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
