
var CustomerUtil= CustomerUtil || {};
(function(){
	CustomerUtil.submitValidate=function(){
	       var cName=$.trim($("#cname").val());
	       if(cName.length==0){
	    	   BasePage.showInfoMessage("您还有未填全的项目，客户名称必输！");
	    	   return false;
	       }
	       if(cName.length>20){
	    	   BasePage.showInfoMessage("您输入的名称超长！");
	    	   return false;
	       }
	       var ctel1=$.trim($("#ctel1").val());
	       if(ctel1.length==0){
	    	   BasePage.showInfoMessage("您还有未填全的项目客户电话1（手机号）必输！");
	    	   return false;
	       }
	       if(!BasePage.ValidateMobile(ctel1)){
	    	   BasePage.showInfoMessage("您输入的客户电话1（手机号）不合法！");
	    	   return false;
	       }
	       var ctel2=$.trim($("#ctel2").val());
	       if(ctel2.length==0){
	    	   BasePage.showInfoMessage("您还有未填全的项目客户电话2必输！");
	    	   return false;
	       }
	       if(ctel2.length>16){
	    	   BasePage.showInfoMessage("您输入的电话号超长！");
	    	   return false;
	       }
	       if(!BasePage.ValidateMobile(ctel2)){
	    	   BasePage.showInfoMessage("您输入正确您输入的电话号！");
	    	   return false;
	       }
	       var idCard=$.trim($("#cidCard").val());
	       if(idCard.length>0&&!BasePage.ValidateIdCard(idCard)){
	    	   BasePage.showInfoMessage("您输入真实的身份证号！");
	    	   return false;
	       }
	      
	       var caddress=$.trim($("#caddress").val());
	       if(caddress.length>50){
	    	   BasePage.showInfoMessage("您输入的客户地址信息超长！");
	    	   return false;
	       }
	       var substation=$.trim($("#substation").val());
	       if(substation.length>50){
	    	   BasePage.showInfoMessage("您输入的所属分局信息超长！");
	    	   return false;
	       }
	       var policestation=$.trim($("#policestation").val());
	       if(policestation.length>50){
	    	   BasePage.showInfoMessage("您输入的所属派出所信息超长！");
	    	   return false;
	       }
	       var remark=$.trim($("#remark").val());
	       if(remark.length>200){
	    	   BasePage.showInfoMessage("您输入的备注信息超长！");
	    	   return false;
	       }
		   return true;
		};
})();