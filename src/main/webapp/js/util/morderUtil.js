//运维公用工具
var MorderUtil={}||MorderUtil;
(function(){
	// 提交验证，更新和新增页面共用??未完，需要补充
	MorderUtil.submitValidate=function(){
		var result=true;
		var checkArray={};
		// 订单编号
		var orderId=$("#orderId").combobox("getValue");
		// 运维订单编号
		var morderNo=$("#maintainNo").textbox("getValue");
		// 运维经理ID
		var morderPmId=$("#morderPmId").val();
		// 派修时间
		var maintainDate=$("#maintainDate").datebox("getValue");
		// 故障描述
		var faultDesc=$("#faultDesc").textbox("getValue");
		checkArray["faultDesc"]=faultDesc;
		// 预约时间
		var appointmentDate=$("#appointmentDate").datebox("getValue");
		// 计划完成时间
		var planEndDate=$("#planEndDate").datebox("getValue");
		// 维修人数
		var mworkerCnt=$("#maintainWorkerCnt").numberbox("getValue");
		// 维修开始日期
		var maintainBeginDate=$("#maintainBeginDate").datebox("getValue");
		// 维修状态
		var maintainStatus=$("#maintainStatus").combobox("getValue");
		
		// 维修进度
		var maintainSpeed=$("#maintainSpeed").numberbox("getValue");
		// 故障类别
		var faultType=$("#faultType").combobox("getValue");
		// 维修负责人ID
		var maintainPmId=$("#maintainPmId").val();
		// 验收状态
		var acceptStatus=$("#acceptStatus").combobox("getValue");
		// 验收回访人
		var acceptecallMan=$.trim($("#accepteCallMan").textbox("getValue"));
		// 验收回访时间
		var callbackDate=$("#callBackDate").datebox("getValue");
		// 解决方案
		var solution=$("#solution").textbox("getValue");
		// 维修结果描述
		var maintainResultdesc=$.trim($("#maintainResultDesc").textbox("getValue"));
		// 备注
		var maintainRemark=$.trim($("#maintainRemark").textbox("getValue"));
		// 用户评分
		var userScore=$("#userScore").numberbox("getValue");
		// 用户评价
		var userProposal=$.trim($("#userProposal").textbox("getValue"));
        if(mworkerCnt!=""){
        	var _mworkerCnt=parseInt(mworkerCnt);
        	if(_mworkerCnt>10000){
        		BasePage.showInfoMessage("维修人数最多10000人！");
    			return false;
        	}
        }
		if(faultDesc!=""&&faultDesc.length>200){
			BasePage.showInfoMessage("请检查故障描述输入长度！");
			return false;
		}
		if(accepteCallMan!=""&&accepteCallMan.length>200){
			BasePage.showInfoMessage("请检查验收回访人输入长度！");
			return false;
		}
		if(solution!=""&&solution.length>200){
			BasePage.showInfoMessage("请检查解决方案输入长度！");
			return false;
		}
		if(maintainResultDesc!=""&&maintainResultDesc.length>200){
			BasePage.showInfoMessage("请检查维修结果描述输入长度！");
			return false;
		}
		if(maintainRemark!=""&&maintainRemark.length>200){
			BasePage.showInfoMessage("请检查备注输入长度！");
			return false;
		}
		if(userScore!=""&&userScore.length>200){
			BasePage.showInfoMessage("请检查用户评分输入长度！");
			return false;
		}
		if(userProposal!=""&&userProposal.length>200){
			BasePage.showInfoMessage("请检查用户评价输入长度！");
			return false;
		}
		return true;
	};
 
})();

	
		
	


