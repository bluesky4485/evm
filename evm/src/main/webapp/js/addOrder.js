$(document).ready(function() {
	var bodyId="addOrderBody";
	
	BizPageSt.BindQueryOrderResult(bodyId,"todayNoDoOrderCnt","weekNoDoOrderCnt","monthNoDoOrderCnt","allOrderCnt");
	orderCompleteFile.loadCtrl();
	orderworkFile.loadCtrl();
	//绑定项目类型
	BasePage.BindProjectTypeControl("projectType");
	//项目经理性别
	BasePage.BindSexControl("pmSex");
	//客户类型
	BasePage.BindUserTypeControl("utype");
	//项目编号 
	ProjectUtil.BindProjectList(bodyId,"projectid",{},function(data){
            $("#projectName").textbox("setValue",data["projectName"]);
	});
	//施工内检结果 
	BasePage.bindWorkCheckResult("checkResult");
	//施工类型
	BasePage.BindWorkTypeControl("workType");
	//施工状态 workStatus
	BasePage.BindWorkStatusControl("workStatus");
	// 增加设备、删除设备的变色
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
	//打开搜索后清空搜索条件
	$("#user_window").window({onBeforeOpen:function(){
		$('#userSearch').searchbox("setValue","");
	}});
	//打开地图
	QmMap.init("mapContainer");
	
	//打开地图
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
	        position :new AMap.LngLat(lng,lat),
	        offset : new AMap.Pixel(-12,-12),
	        map : QmMap.Map
	     });
		QmMap.Map.panTo([lng,lat]);
		$("#map_window").window("open");
	});
	$("#workCusMap").click(function(){
		QmMap.Map.clearMap();
		AMap.plugin('AMap.Geocoder',function(){
	        var geocoder = new AMap.Geocoder({
	            city: "0431"//城市，默认：“全国”
	        });
	        var marker = new AMap.Marker({
	            map:QmMap.Map,
	            bubble:true,
	            position:[125.3245,43.886841]
	        })
	        QmMap.Map.on('click',function(e){
	            marker.setPosition(e.lnglat);
	            $("#lat").textbox("setValue",e.lnglat.lat);
	            $("#lng").textbox("setValue",e.lnglat.lng);
	            geocoder.getAddress(e.lnglat,function(status,result){
	              if(status=='complete'){
	            	  $("#workAddress").textbox("setValue", result.regeocode.formattedAddress);
	              }
	            })
	        })
	    });
		$("#map_window").window("open");
	});
	//创建日期
	var crrentDate = BasePage.getNowFormatDate();
	$("#currentDate").html(crrentDate);
	//客户window 取消
	$("#customer_window_cancel").click(function(){
		$("#customer_window").dialog("close");
	});
	//客户window 确定
	$("#customer_window_OK").click(function() {
		var updObj = $("#customerDg").datagrid('getSelected');
		if(updObj!=null){
			// 客户姓名
			$("#cname").searchbox("setValue", updObj["cname"]);
			// 客户编号
			$("#cNo").textbox("setValue", updObj["cno"]);
			// 客户地址
			$("#caddress").textbox("setValue", updObj["caddress"]);
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
	//用户 window 取消
	$("#user_window_cancel").click(function(){
		$("#user_window").dialog("close");
	});
	//用户 window 确定
	$("#user_window_OK").click(function() {
		var updObj = $("#userDg").datagrid('getSelected');
		var openType=$("#openType").val();
		if(updObj!=null){
			  if(openType=="1"){
				// 施工负责人
				$("#workManager").searchbox("setValue", updObj["ufullName"]);
				// 施工负责人电话1
				$("#workManageTel1").textbox("setValue", updObj["utel1"]);
				// 施工负责人电话2
				$("#workManageTel2").textbox("setValue", updObj["utel2"]);
				$("#workPmId").val(updObj["uid"]);
			}else if(openType=="2"){
				// 库联系人
				$("#outStoreContacter").searchbox("setValue", updObj["ufullName"]);
				// 出库联系人电话
				$("#outStoreContacterTel").textbox("setValue", updObj["utel1"]);
				$("#storePmId").val(updObj["uid"]);
			}else if(openType=="3"){
				// 内检人员
				$("#checkPerson").searchbox("setValue", updObj["ufullName"]);
				$("#checkerId").val(updObj["uid"]);
			}
		} 
		$("#user_window").dialog("close");
	});
	//项目编号选择事件
	$("#projectid").combobox({onChange: function(newValue, oldValue){
		var para = {};
		para["projectId"]=newValue;
		BasePage.sendPostRequest(bodyId,'/projectController.do?method=ajaxFindProjectById',para,function(data){	
			if(data== null || data.bizData!=null){
				queryProject_Callback(data.bizData);
			}else{
				BasePage.showInfoMessage("获得更新数据异常");
			}
		});
		
	}});
	//选择项目后，回调
	function queryProject_Callback(updObj){
		// 项目名称
		$("#projectName").textbox("setValue", updObj["projectName"]);
		if(updObj.pmUser!=null){
			//项目经理ID
			$("#pmId").val(updObj["pmId"]);
			//项目经理
			$("#pmName").textbox("setValue", updObj.pmUser.ufullName);
			//项目经理电话1
			$("#pmTel1").textbox("setValue", updObj.pmUser.utel1);
			//项目经理电话2
			$("#pmTel2").textbox("setValue", updObj.pmUser.utel2);
			//项目经理性别
			$("#pmSex").combobox("setValue",updObj.pmUser.usex);
		}
		if(updObj.custom!=null){
			//客户编号
			$("#cusId").textbox("setValue",updObj.custom["cno"]);
			// 客户姓名
			$("#cname").searchbox("setValue", updObj.custom["cname"]);
			// 客户地址
			$("#caddress").textbox("setValue", updObj.custom["caddress"]);
			$("#cusAddrLat").val(updObj.custom["lat"]);
			$("#cusAddrLng").val(updObj.custom["lng"]);
			// 客户类型
			$("#utype").combobox("setValue", updObj.custom["ctype"]);
			// 客户电话1
			$("#ctel1").textbox("setValue", updObj.custom["ctel1"]);
			// 客户电话2
			$("#ctel2").textbox("setValue", updObj.custom["ctel2"]);
			// 所属分局
			$("#substation").textbox("setValue", updObj.custom["substation"]);
			// 所属派出所
			$("#policestation").textbox("setValue", updObj.custom["policestation"]);
		}
		//缴费类型
		$("#payType").textbox("setValue", updObj["payType"]);
		//缴费金额
		$("#contractSum").textbox("setValue", updObj["contractSum"]);
		//年服务费
		$("#pYearSum").textbox("setValue", updObj["yearPay"]);
		//缴费日期
		$("#payDate").datebox("setValue", updObj["payDate"]);
		//服务截止日期
		$("#serviceEndDate").datebox("setValue", updObj["serviceEndDate"]);
		//入网日期
		$("#joinDate").datebox("setValue", updObj["joinDate"]);
		//停机日期
		$("#stopDate").datebox("setValue", updObj["stopDate"]);
		//行业类别
		$("#projectType").combobox("setValue", updObj["projectType"]);
		//备注
		$("#projectRemark").textbox("setValue", updObj["projectRemark"]);
	};
	//设备window 打开初始化
	$("#device_window").window({"onBeforeOpen":function(){
//		DeviceUtil.LoadDeviceData("unAddDeviceDg");
		var projectId=$("#projectid").combobox("getValue");
		if(projectId==undefined||projectId==null||projectId==""){
			BasePage.showInfoMessage("请选择项目！");
			return false ;
		}
	}});
	//设备window 打开初始化
	$("#device_window").window({"onOpen":function(){
//		DeviceUtil.LoadDeviceData("unAddDeviceDg");
		var projectId=$("#projectid").combobox("getValue");
		if(projectId==undefined||projectId==null||projectId==""){
			BasePage.showInfoMessage("请选择项目！");
			return false ;
		}
		orderDiviceUtil.LoadDeviceData4Add("unAddDeviceDg");
		$("#addDeviceDg").datagrid('loadData', []);
	}});
	// 设备window确定
	$("#device_window_OK").click(function() {
		//选择需要添加的表
		var deviceWindowIndex=$("#deviceWindowIndex").val();
		var tableID="deviceDg";
		if(deviceWindowIndex==1){
			tableID="deviceDg";
		}else if(deviceWindowIndex==2){
			tableID="lineDeviceDg";
		}else if(deviceWindowIndex==3){
			tableID="helpDeviceDg";
		}
		var addDevice=orderDiviceUtil.GetDeviceData("addDeviceDg");
		var gridData=$("#"+tableID).datagrid("getRows");
		var result=orderDiviceUtil.CaculateDeviceCnt(addDevice,gridData);
		$("#"+tableID).datagrid("loadData",result);
		$("#device_window").dialog("close");
	});
	// 设备window取消
	$("#device_window_cancel").click(function() {
		$("#device_window").dialog("close");
	});
    //设备移添
	$("#deviceAddBtn").click(function(){
		orderDiviceUtil.AddSelectDevice("unAddDeviceDg","addDeviceDg");
	});
	//设备移除
	$("#deviceDelBtn").click(function(){
		orderDiviceUtil.AddSelectDevice("addDeviceDg","unAddDeviceDg");
	});
	//提交验证
	$("#saveOrderBtn").click(function(){
		saveOrder();
	});
	function saveOrder(){
		var projectId=$("#projectid").combobox("getValue");
		if(projectId==undefined||projectId==null||projectId==""){
			BasePage.showInfoMessage("请选择项目！");
			return ;
		}
		var orderNo=$("#orderNo").textbox("getValue");
		if(orderNo==""){
			BasePage.showInfoMessage("订单编号不能空，请重进页面生成订单编号！");
			return;
		}
		if(!OrderUtil.submitValidate()){
			return;
		}
		var para={};
		para["orderNo"]=$("#orderNo").textbox("getValue");
		para["projectId"]=$("#projectid").combobox("getValue");
		para["workPmId"]=$("#projectid").val("getValue");
		//汇聚箱号
		para["convergeBoxNo"]=$("#convergeBoxNo").textbox("getValue");
		//施工负责人
		para["workPmId"]=$("#workPmId").val();
		//施工人数
		var workCnt=$("#workers").numberbox("getValue");
		if(workCnt==""){
			workCnt=0;
		}
		para["workCnt"]=workCnt;
		//施工地点
		para["workAddress"]=$("#workAddress").textbox("getValue");
		para["lng"]=$("#lng").textbox("getValue");
		para["lat"]=$("#lat").textbox("getValue");
		//施工单位
		para["workCompany"]=$("#workCompany").textbox("getValue");
		//单位资质情况
		para["workCompanyQualified"]=$("#workCompanyQualified").textbox("getValue");
		//施工类型
		para["workType"]=$("#workType").combobox("getValue");
		//施工天数
		var workDays=$("#workDays").numberbox("getValue");
		if(workDays==""){
			workDays=0;
		}
		para["workDays"]=workDays;
		//计划施工时间
		para["planBeginDate"]=$("#workBeginDate").datebox("getValue");
		//计划完工时间
		para["planEndDate"]=$("#workPlanEndDate").datebox("getValue");
		//出库联系人
		para["storePmId"]=$("#storePmId").val();
		//计划出库时间
		para["planOutstoreDate"]=$("#planOutStoreDate").datebox("getValue");
		//施工状态
		para["workStatus"]=$("#workStatus").combobox("getValue");
		//实际施工时间
		para["workBeginDate"]=$("#realWorkBeginDate").datebox("getValue");
		//实际施工完成时间
		para["workEndDate"]=$("#realWorkEndDate").datebox("getValue");
		//施工进度
		var workProgress=$("#workProgress").numberbox("getValue");
		if(workProgress==""){
			workProgress=0;
		}
		para["workProgress"]=workProgress;
		//最后一次施工更新时间
		para["lastWorkupdDate"]=$("#lastWorkUpdDate").datebox("getValue");
		//内检人员
		para["checkerId"]=$("#checkerId").val();
		//实际施工时间
		para["workBeginDate"]=$("#realWorkBeginDate").datebox("getValue");
		//施工内检结果
		para["checkResult"]=$("#checkResult").combobox("getValue");
		//-----------------------------------------------------------------
		//内检时间
		para["checkDate"]=$("#checkDate").datebox("getValue");
		//用户评分
		var userRating=$("#userRating").numberbox("getValue");
		if(userRating==""){
			userRating=0;
		}
		para["userRating"]=userRating;
		//用户评价
		para["userAppraise"]=$("#userAppraise").textbox("getValue");
		//备注
		para["workRemark"]=$("#workRemark").textbox("getValue");
		//device
		var rows=$("#deviceDg").datagrid("getRows");
		rows=rows.concat($("#lineDeviceDg").datagrid("getRows"));
		rows=rows.concat($("#helpDeviceDg").datagrid("getRows"));
		for(var i=0;i<rows.length;i++){
			if(parseInt(rows[i]["planCnt"])>parseInt(rows[i]["deviceCnt"])){
				BasePage.showInfoMessage("设备【"+rows[i]["deviceName"]+"】"+"中可领设备数量不可大于设备总数！");
				return;
			}
		}
		para["deviceStr"]=JSON.stringify(rows);
		var paths=getFilePath();
		para["fileStrs"]=paths;
		BasePage.sendPostRequest(bodyId,'/orderController.do?method=ajaxInsertOrder',para,function(data){
			if(data.messageType=="error"){
				BasePage.showInfoMessage(data.message);
				return;
			}else{
				alert("保存成功！");
				$("#gotoManageForm").submit();
			}
		});
	};
	function getFilePath(){
		var Str="";
		$("#worDocDiv input[type=hidden]").each(function(index,el){
			Str+=el.value+"@"
		});
		$("#completeDocDiv input[type=hidden]").each(function(index,el){
			Str+=el.value+"@"
	    });
		return Str;
	}
	 
	////搜索施工负责人
	$('#workManager').searchbox({
	    searcher:function(value,name){
	    	$("#openType").val("1");
	    	$("#user_window").window("open");
	    	searchUserList();
	    },
	    prompt:'系统用户'
	});
	//搜索出库联系人
	$('#outStoreContacter').searchbox({
	    searcher:function(value,name){
	    	$("#openType").val("2");
	    	$("#user_window").window("open");
	    	searchUserList();
	    },
	    prompt:'系统用户'
	}); 
	//搜索内检人员
	$('#checkPerson').searchbox({
	    searcher:function(value,name){
	    	$("#openType").val("3");
	    	$("#user_window").window("open");
	    	searchUserList();
	    },
	    prompt:'系统用户'
	});
	//搜索用户
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
		BasePage.sendPostRequest('user_window','/userController.do?method=ajaxFindUserList',para,function(data){	
			if(data== null || data.bizData.total ==0){
				BasePage.showInfoMessage(BasePage.noSearchData);
				$("#userDg").datagrid('loadData', []);
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
    createEvm("qrcode",$("#orderNo").textbox("getValue"),150,150);
    //生成evm
    $("#goHist").click(function(){
		$("#gotoManageForm").submit();
	});
    //打印
	$("#printOrderBtn").click(function(){  
		//window.open('', '_self');
        $("#qrcode").jqprint({});
       //	$("#qrcode").printArea();
     });
});
 
 
 
 
   