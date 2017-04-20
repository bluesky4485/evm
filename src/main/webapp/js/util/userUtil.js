var UserUtil={}||UserUtil;
(function(){
	UserUtil.QueryUserList=function(formId,para,callback){
		var userArr = [];
		BasePage.sendPostRequest(formId,'/userController.do?method=ajaxFindUserList',para,function(data){	
			callback(data);
		});
	};
	UserUtil.SubmitValidate=function(){
		var uname = $.trim($("#uname").textbox("getValue"));
		var ufullName = $.trim($("#ufullName").textbox("getValue"));
		var pwd=$.trim($("#upass").passwordbox("getValue"));
		var rpwd=$.trim($("#rupass").passwordbox("getValue"));
		var ctel1 = $.trim($("#utel1").textbox("getValue"));
		var ctel2 = $.trim($("#utel2").textbox("getValue"));
		var csex = $("#usex").combobox("getValue");
		var idCard = $.trim($("#uidCard").textbox("getValue"));
		var uaddress = $.trim($("#uaddress").textbox("getValue"));
		var remark = $.trim($("#uremark").textbox("getValue"));
		var roles=$.trim($("#roleName").combobox("getValues"));
		//用户名校验
		if(uname.length==0){
			BasePage.showInfoMessage("用户名不可为空！");
			return false;
		}else if(!BasePage.ValidatePwd(uname,[1,20])){
			BasePage.showInfoMessage("用户名请输入1到20位的数字或英文！");
			return false;
		}else if(uname.length>21){
			BasePage.showInfoMessage("您输入的用户名长度不合法！");
			return false;
		}
		//密码校验
		if(pwd.length==0){
			BasePage.showInfoMessage("密码不可为空！");
			return false;
		}else if(!BasePage.ValidatePwd(pwd,[6,8])){
			BasePage.showInfoMessage("密码请输入6到8位的数字或英文！");
			return false;
		} 
		if(rpwd.length==0||pwd!=rpwd){
			BasePage.showInfoMessage("两次输入的密码不一致！");
			return false;
		}
		//ufullName校验
		if(ufullName.length==0){
			BasePage.showInfoMessage("用户中文名不可为空！");
			return false;
		}else  if(!BasePage.ValidateEngChineseNum(ufullName)){
			BasePage.showInfoMessage("用户中文名请输入数字英文中文！");
			return false;
		}else if(ufullName.length>20){
			BasePage.showInfoMessage("您输入的用户中文名长度不合法！");
			return false;
		}
		//tel1
		if(ctel1.length>0&&!BasePage.ValidateMobile(ctel1)){
	    	   BasePage.showInfoMessage("您输入的电话1（手机号）不合法！");
	    	   return false;
	    }
		//tel2
		if(ctel2.length>0&&!BasePage.ValidateMobile(ctel2)){
	    	   BasePage.showInfoMessage("您输入的电话2（手机号）不合法！");
	    	   return false;
	    }
		//idCard
		 if(idCard.length>0&&!BasePage.ValidateIdCard(idCard)){
	    	   BasePage.showInfoMessage("您输入真实的身份证号！");
	    	   return false;
	       }
		 //uaddress
		 if(uaddress.length>50){
	    	   BasePage.showInfoMessage("您输入的地址信息超长！");
	    	   return false;
	     }
		 //remark
		 if(remark.length>200){
	    	   BasePage.showInfoMessage("您输入的备注信息超长！");
	    	   return false;
	      }
		 //roles
		 if(roles.length<=0){
			  BasePage.showInfoMessage("请选择角色信息！");
	    	  return false;
		 }
		 return true;
	};
	UserUtil.BindRoleList=function(formId,callback){
		BasePage.sendPostRequest(formId,'/roleController.do?method=ajaxFindRoleAll',{},function(data){
			$("#roleName").combobox("loadData",data.bizData);
			if(callback!=undefined){
				callback;
			}
		});	
	};
})();