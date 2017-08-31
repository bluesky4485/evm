BasePage.noSearchData="未查询到数据！";
BasePage.SexArray=[{id:0,text:'男',selected:true},{id:1,text:'女'}];
//绑定性别
BasePage.BindSexControl=function(ctrId){
	$('#'+ctrId).combobox('loadData',BasePage.SexArray);
};
BasePage.Sexformater=function(val){
	for(var i=0;i<BasePage.SexArray.length;i++){
		if(BasePage.SexArray[i].id==val){
			return BasePage.SexArray[i].text;
		}
	}
	return "";
};
BasePage.UserType=[{tvalue:"0",tname:"报警用户",selected:true},{tvalue:"1",tname:"天网用户"}];
//绑定用户类别
BasePage.BindUserTypeControl=function(ctrId){
	$('#'+ctrId).combobox('loadData',BasePage.UserType);
};
BasePage.UserTypeformater=function(val,row,index){
	for(var i=0;i<BasePage.UserType.length;i++){
		if(BasePage.UserType[i].tvalue==val){
			return BasePage.UserType[i].tname;
		}
	}
	return "";
}
//行业类别：Industry
BasePage.IndustryType=[{tvalue:"0",tname:"企业",selected:true},{tvalue:"1",tname:"金融"},{tvalue:"2",tname:"个人"},{tvalue:"3",tname:"公安"}]
BasePage.BindIndustryTypeControl=function(ctrId){
	$('#'+ctrId).combobox('loadData',BasePage.IndustryType);
};
BasePage.IndustryTypeformater=function(val){
	for(var i=0;i<BasePage.IndustryType.length;i++){
		if(BasePage.IndustryType[i].tvalue==val){
			return BasePage.IndustryType[i].tname;
		}
	}
	return "";
}
//建设方式
BasePage.BuildType=[{id:0,text:"自建"},{id:1,text:"外包"}];
BasePage.BindBuildTypeeControl=function(ctrId){
	$('#'+ctrId).combobox('loadData',BasePage.BuildType);
};
BasePage.BuildTypeformater=function(val){
	for(var i=0;i<BasePage.BuildType.length;i++){
		if(BasePage.BuildType[i].id==val){
			return BasePage.BuildType[i].text;
		}
	}
	return "";
};
BasePage.ProjectType=[{id:"0",text:"报警",selected:true},{id:"1",text:"天网"},{id:"2",text:"系统集成"}]
BasePage.BindProjectTypeControl=function(ctrId){
	$('#'+ctrId).combobox('loadData',BasePage.ProjectType);
};
BasePage.ProjectTypeformater=function(val){
	for(var i=0;i<BasePage.ProjectType.length;i++){
		if(BasePage.ProjectType[i].id==val){
			return BasePage.ProjectType[i].text;
		}
	}
	return "";
};
//订单状态
BasePage.OrderStatus=[{id:"0",text:"创建",selected:true},{id:"1",text:"预约"},{id:"2",text:"施工中"},{id:"3",text:"施工完成"},{id:"4",text:"验收完成"}];
BasePage.BindOrderStatusControl=function(ctrId){
	$('#'+ctrId).combobox('loadData',BasePage.OrderStatus);
};
BasePage.OrderStatusformater=function(val){
	for(var i=0;i<BasePage.OrderStatus.length;i++){
		if(BasePage.OrderStatus[i].id==val){
			return BasePage.OrderStatus[i].text;
		}
	}
	return "";
};
//施工内检结果
BasePage.WorkCheckResult=[{id:"0",text:"未内检",selected:true},{id:"1",text:"内检通过"},{id:"2",text:"内检不通过"}];
BasePage.bindWorkCheckResult=function(ctrId){
	$('#'+ctrId).combobox('loadData',BasePage.WorkCheckResult);
}
BasePage.WorkCheckResultformater=function(val){
	for(var i=0;i<BasePage.WorkCheckResult.length;i++){
		if(BasePage.WorkCheckResult[i].id==val){
			return BasePage.WorkCheckResult[i].text;
		}
	}
	return "";
};
//施工类型
BasePage.WorkType=[{id:"0",text:"报警项目",selected:true},{id:"1",text:"天网项目"}];
BasePage.BindWorkTypeControl=function(ctrId){
	$('#'+ctrId).combobox('loadData',BasePage.ProjectType);
};
BasePage.WorkTypeformater=function(val){
	for(var i=0;i<BasePage.WorkType.length;i++){
		if(BasePage.WorkType[i].id==val){
			return BasePage.WorkType[i].text;
		}
	}
	return "";
};
//施工状态
BasePage.WorkStatus=[{id:"0",text:"创建",selected:true},{id:"1",text:"预约"},{id:"2",text:"施工中"},{id:"3",text:"施工完成"},{id:"4",text:"验收完成"}];
BasePage.BindWorkStatusControl=function(ctrId){
	$('#'+ctrId).combobox('loadData',BasePage.WorkStatus);
};
BasePage.WorkStatusformater=function(val){
	for(var i=0;i<BasePage.WorkStatus.length;i++){
		if(BasePage.WorkStatus[i].id==val){
			return BasePage.WorkStatus[i].text;
		}
	}
	return "";
};
//验收状态accept
BasePage.AcceptStatus=[{id:"0",text:"未回访",selected:true},{id:"1",text:"已修好"},{id:"2",text:"未修好"}];
BasePage.BindAccepStatusControl=function(ctrId){
	$('#'+ctrId).combobox('loadData',BasePage.AcceptStatus);
};
BasePage.WorkAcceptStatusformater=function(val){
	for(var i=0;i<BasePage.AcceptStatus.length;i++){
		if(BasePage.AcceptStatus[i].id==val){
			return BasePage.AcceptStatus[i].text;
		}
	}
	return "";
};
//维修状态（
BasePage.MaintainStatus=[{id:"0",text:"创建",selected:true},{id:"1",text:"预约"},{id:"2",text:"维修中"},{id:"3",text:"维修完成"},{id:"4",text:"回访完成"}];
BasePage.BindMaintainStatusControl=function(ctrId,callback){
	$('#'+ctrId).combobox('loadData',BasePage.MaintainStatus);
	callback;
};
BasePage.WorkMaintainStatusformater=function(val){
	for(var i=0;i<BasePage.MaintainStatus.length;i++){
		if(BasePage.MaintainStatus[i].id==val){
			return BasePage.MaintainStatus[i].text;
		}
	}
	return "";
};
//月份
BasePage.MonthArray=[{id:"1",text:"一月份",selected:true},{id:"2",text:"二月份"},{id:"3",text:"三月份"},{id:"4",text:"四月份"},{id:"5",text:"五月份"}];
BasePage.BindMonthControl=function(ctrId){
	$('#'+ctrId).combobox('loadData',BasePage.MonthArray);
};
BasePage.Monthformater=function(val){
	for(var i=0;i<BasePage.MonthArray.length;i++){
		if(BasePage.MonthArray[i].id==val){
			return BasePage.MonthArray[i].text;
		}
	}
	return "";
};
//年
BasePage.BindYearControl=function(ctrId,yearIndex,beforeCnt,afterCnt){
	var arr=[];
	var year=yearIndex;
	for(var i =0; beforeCnt!=undefined&&i<beforeCnt;i++){
		var a={};
		a["id"]=year-beforeCnt+i;
		a["text"]=year-beforeCnt+i+"年";
		arr.push(a);
	}
	for(var i =0; afterCnt!=undefined&&i<afterCnt;i++){
		var a={};
		a["id"]=yearIndex+i;
		a["text"]=yearIndex+i+"年";
		if(i==0){
			a.selected=true;
		}
		arr.push(a);
	}
	$('#'+ctrId).combobox('loadData',arr);
};
//设备类型
BasePage.DeviceType=[{id:1,text:"前端设备",selected:true},{id:2,text:"后端设备"},{id:3,text:"辅助材料"}];
BasePage.AllDeviceType=[{id:"0",text:"全部",selected:true},{id:1,text:"设备"},{id:2,text:"线材"},{id:3,text:"辅助材料"}];
BasePage.BindDeviceTypeControl=function(ctrId){
	$('#'+ctrId).combobox('loadData',BasePage.DeviceType);
};
BasePage.DeviceTypeformater=function(val){
	for(var i=0;i<BasePage.DeviceType.length;i++){
		if(BasePage.DeviceType[i].id==val){
			return BasePage.DeviceType[i].text;
		}
	}
	return "";
};
//项目状态：（1维护期内，2维护期外，3终止）
BasePage.ProjectStat=[{id:1,text:"维护期内",selected:true},{id:2,text:"维护期外"},{id:3,text:"终止"}];
BasePage.BindProjectStatControl=function(ctrId){
	$('#'+ctrId).combobox('loadData',BasePage.ProjectStat);
};
//校验身份证号
BasePage.ValidateIdCard=function(card)  
{  
   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
   if(reg.test(card) === false)  
   {  
       return  false;  
   }
   return true;
};

BasePage.ValidateChinese=function(value){
	 return /^[Α-￥]+$/g.test(value);
};
BasePage.ValidateEnglish=function(value){
	return /^[A-Za-z]+$/g.test(value); 
};
BasePage.ValidateNumber=function (value) {  
	   var reg =/^[0-9]*$/;  
	   return reg.test(value);  
};
BasePage.ValidateTelephone=function (value) {  
	var tel = /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-?)?[1-9]\d{6,7}(\-\d{1,4})?$/;
	return tel.test(value)
};
BasePage.ValidateMobile=function (value) {  
	var cmccMobile = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/; 
	return value.length == 11 && cmccMobile.test(value) 
};
//中文英文数字下划线
BasePage.ValidateEngChineseNum=function(value){
	var engNum=/^[Α-￥a-zA-Z0-9]+$/g;
	return engNum.test(value);
}
//para[6,8] 输入长度6到8位，英文或数字
BasePage.ValidatePwd=function(value,param) { 
	   var length = value.length;
	   for (var i = 0; i < value.length; i++) {
		   if (value.charCodeAt(i) > 127) {
			   length++;
		   }
	   }
	   if(param)
		   return /^[a-zA-Z0-9]+$/g.test(value) && (length >= param[0] && length <= param[1]); 
	   else
		   return /^[a-zA-Z0-9]+$/g.test(value) ;
}
function createEvm(divId,content,_width,_height){
	var qrcode;
	var size=150;
	if(_height==undefined) _height=size;
	if(_width==undefined) _height=size;
	// 清除上一次的二维码
    if(qrcode){
        qrcode.clear();
    }
    // 创建二维码
    qrcode = new QRCode(divId, {
        width : _width,//设置宽高
        height : _height
    });
    qrcode.makeCode(content);
}