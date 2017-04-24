var BasePage = BasePage || {};
(function() {
	BasePage.urlPre= window.top.document.getElementById('serverName')!=null?window.top.document.getElementById('serverName').value:"" ;
	// 页面流转
	BasePage.redirect = function(url) {
		window.top.document.getElementById("center_iframe").src = url;
	};
	BasePage.showErrorMessage = function(content) {
		$.messager.alert("错误消息", content,'error');
	};
	BasePage.showInfoMessage = function(content) {
		$.messager.alert("提示消息", content,'info');
	};
	BasePage.showWarningMessage = function(content) {
		$.messager.alert("警告消息", content,'warning');
	};
	BasePage.confirmMessage = function(content,callback) {
		$.messager.confirm("确认消息", content, callback );
	};
	// 发送post请求 
	BasePage.sendPostRequest = function(_divId, _url, _para, _callback,_async) {
		if (undefined == BasePage.urlPre)
		{
			BasePage.urlPre ="/evm";// window.top.document.getElementById('qmUrlPre').value;
		}
		if(undefined ==_async||null==_async)
		{
			_async=true;	
		}
		var _url = BasePage.urlPre + _url;
		BasePage.openMask('#'+_divId);
		$.ajax({
		type : 'post',
		dataType : "json",
		async : _async,
		url : _url,
		data : _para,
		success : function(data) {
			BasePage.closeMask('#' + _divId);
			if(_callback!=undefined)
				  _callback(data);
		},
		error : function(x, e) {
			BasePage.closeMask('#' + _divId);
			if(""!=x.responseText&&x.responseText.length>0){
				if(x.responseText.length>50)
		       	  BasePage.showErrorMessage("错误内容："+x.responseText.substring(0,30)+"...");
			}
		}
		});
	};
	// 发送post请求-无遮罩
	BasePage.sendPostRequestNoMask = function(_url, _para, _callback,_async) {
		if (undefined == BasePage.urlPre) {
			BasePage.urlPre = window.top.document.getElementById('qmUrlPre').value;
		}
		if(undefined ==_async||null==_async)
		{
			_async=true;	
		}
		var _url = BasePage.urlPre + _url;
		$.ajax({
		type : 'post',
		dataType : "json",
		async : _async,
		url : _url,
		data : _para,
		success : function(data) {
			if(_callback!=undefined)
			  _callback(data);
		},
		error : function(x, e) {
			if(""!=x.responseText&&x.responseText.length>0){
				if(x.responseText.length>50)
		       	  BasePage.showErrorMessage("错误内容："+x.responseText.substring(0,30)+"...");
			}
		}
		});
	};
	// 关闭遮罩
	BasePage.closeMask = function(_id) {
		$(_id).hideLoading();
	};
	// 打开遮罩
	BasePage.openMask = function(_id) {
		$(_id).showLoading({
			'addClass' : 'loading-indicator'
		});
	};
	// 初始化pageNumber
	BasePage.initTablePager = function(tableId){
		$('#'+tableId).datagrid('getPager').pagination('refresh',{	
			pageNumber: 1
		});
		var opts = $('#'+tableId).datagrid('options');
		opts.pageNumber = 1;
	};
	//表格客户端分页
	BasePage.pagerFilter=function(data) {
		if (typeof data.length == 'number' && typeof data.splice == 'function') {
			data = {
			total : data.length,
			rows : data
			};
		}
		var dg = $(this);
		var opts = dg.datagrid('options');
		var pager = dg.datagrid('getPager');
		pager.pagination({
		onSelectPage : function(pageNum, pageSize) {
			opts.pageNumber = pageNum;
			opts.pageSize = pageSize;
			pager.pagination('refresh', {
			pageNumber : pageNum,
			pageSize : pageSize
			});
			dg.datagrid('loadData', data);
		},
		beforePageText : '第', // 页数文本框前显示的汉字
		afterPageText : '页 / 共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录  共 {total} 条记录'
		});
		if (!data.originalRows) {
			data.originalRows = (data.rows);
		}
		var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);
		var end = start + parseInt(opts.pageSize);
		data.rows = (data.originalRows.slice(start, end));
		return data;
	};
	//服务端分页
	BasePage.pagerServerFilter = function(_gridObj,_callBack){
		 $(_gridObj).pagination({
	            onSelectPage: function (pageNumber, pageSize) {
	            	_callBack(pageNumber,pageSize);
	            },
	            onBeforeRefresh: function (pageNumber, pageSize) {
	            	_callBack(pageNumber,pageSize);
	            },
	            onRefresh: function () {
	                $(this).pagination('loaded');
	            },
	            beforePageText: '第', //页数文本框前显示的汉字  
	            afterPageText: '页 / 共 {pages} 页',
	            displayMsg: '当前显示 {from} - {to} 条记录  共 {total} 条记录'
				 
	        });
	};
	//验证单ip
	BasePage.validateIP = function(value){
		var ipaddr = /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
		if(!ipaddr.test(value)){
			BasePage.showInfoMessage("IP地址格式不正确.");
			return false;
		}
		return true;
	};
	//验证ip多值
	BasePage.validateIPs = function(value){
		var ipaddr = /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
		var ipPools;
		if(value.indexOf(';')!=-1){
			ipPools = value.split(";");
			for(var i=0;i<ipPools.length;i++){				
				if(ipPools[i].indexOf('-')!=-1){
					if(!validateIpScope(ipPools[i],ipaddr)){
						return false;
					}
				}
				else{
					if(!ipaddr.test(ipPools[i])){
						BasePage.showInfoMessage("IP地址格式不正确.");
						return false;
					}
				}
			}
			return true;
		}
		if(value.indexOf('-')!=-1){
			if(!validateIpScope(value,ipaddr)){
				return false;
			}
			return true;
		}
		if(!ipaddr.test(value)){
			BasePage.showInfoMessage("IP地址格式不正确.");
			return false;
		}
	    return true;
	};
	//验证连续ip段
	validateIpScope=function(ips,ipaddr){
		if(ips.indexOf('-')!=-1){
			ipPools = ips.split("-");
			var startIp;
			var startIpNum;
			var startIpPrefix;
			var endIp;
			var endIpNum;
			var endIpPrefix;
			if(ipPools.length == 2){
				startIp = ipPools[0];
				endIp = ipPools[1];
				if(!(ipaddr.test(startIp)&&ipaddr.test(endIp))){
					BasePage.showInfoMessage("IP地址格式不正确.");
					return false;
				}						
				startIpNum = startIp.substr(startIp.lastIndexOf(".")+1);
				startIpPrefix = startIp.substr(0,startIp.lastIndexOf("."));						
				endIpNum = endIp.substr(endIp.lastIndexOf(".")+1);
				endIpPrefix = endIp.substr(0,endIp.lastIndexOf("."));
				if(endIpPrefix==startIpPrefix){
					if(endIpNum<startIpNum){
						return false;
					}
				}
				else{
					BasePage.showInfoMessage("非连续IP段.");
					return false;
				}
			}
		}
		else if(!ipaddr.test(ips)){
			BasePage.showInfoMessage("IP地址格式不正确.");
			return false;
		}
		return true;
	};
	//验证表单域_
	$.extend($.fn.validatebox.defaults.rules, {
        inputContentEn: {
	        validator: function(value, param){
	        return /^[a-zA-Z0-9\_]*$/g.test(value);
	        },
	        message: '名称非法，请输入数字、字母、下划线'
        },        
        mobileOrTelephone:{//联系电话(手机/电话)
			validator:function(value){
			var cmccMobile = /^(((1[0-9]{1}[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
			var tel = /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-?)?[1-9]\d{6,7}(\-\d{1,4})?$/;
			// /^\d{3,4}?\d{7,8}$/;
			return tel.test(value) || (value.length == 11 && cmccMobile.test(value));
			},
			message:'请正确填写您的联系电话'
		},
		ip : {// IP地址 
	       validator : function(value) { 
				var ipaddr = /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
				var ipPools;
				if(value.indexOf(';')!=-1){
					ipPools = value.split(";");
					for(var i=0;i<ipPools.length;i++){
						if(ipPools[i].indexOf('-')!=-1){
							ipPools = ipPools[i].split("-");
							var startIp;
							var startIpNum;
							var startIpPrefix;
							var endIp;
							var endIpNum;
							var endIpPrefix;
							if(ipPools.length == 2){
								startIp = ipPools[0];
								endIp = ipPools[1];
								if(!(ipaddr.test(startIp)&&ipaddr.test(endIp))){
									return false;
								}						
								startIpNum = startIp.substr(startIp.lastIndexOf(".")+1);
								startIpPrefix = startIp.substr(0,startIp.lastIndexOf("."));						
								endIpNum = endIp.substr(endIp.lastIndexOf(".")+1);
								endIpPrefix = endIp.substr(0,endIp.lastIndexOf("."));
								if(endIpPrefix==startIpPrefix){
									if(endIpNum<startIpNum){
										return false;
									}
								}
								else{
									return false;
								}
							}
						}
						else if(!ipaddr.test(ipPools[i])){
							return false;
						}
					}
					return true;
				}		   		
	    	    return ipaddr.test(value);
	       }, 
	       message : 'IP地址格式不正确' 
		},
	   chinese : {// 中文 
	       validator : function(value) { 
	           return /^[Α-￥]+$/g.test(value); 
	       }, 
	       message : '请输入中文' 
	   }, 
	   english : {// 英文 
	       validator : function(value) { 
	           return /^[A-Za-z]+$/g.test(value); 
	       }, 
	       message : '请输入英文' 
	   }, 
	   englishOrNumber : {// 数字或英文
	       validator : function(value,param) { 
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
	       }, 
	       message : '请输入介于{0}和{1}之间数字或英文' 
	   },
	   englishAndNumber : {// 数字和英文
	       validator : function(value,param) { 
	    	   var length = value.length;
	    	   for (var i = 0; i < value.length; i++) {
				   if (value.charCodeAt(i) > 127) {
					   length++;
				   }
			   }
	    	   if(param)
	    		   return /([a-zA-Z])+/g.test(value) && /([0-9])+/g.test(value) && (length >= param[0] && length <= param[1]); 
	    	   else
	    		   return /([a-zA-Z])+/g.test(value) &&/([0-9])+/g.test(value);
	       }, 
	       message : '请输入介于{0}和{1}之间数字和英文' 
	   },
	   number:{//全数字
		   validator:function(value,param){
			   var length = value.length;
			   for (var i = 0; i < value.length; i++) {
				   if (value.charCodeAt(i) > 127) {
					   length++;
				   }
			   }
			   return /^[0-9]+$/g.test(value) && (length >= param[0] && length <= param[1]);
			   
		   },
		   message:"输入内容长度必须介于{0}和{1}之间的数字"
	   },
	   //数字  
	   Number: {
		   validator: function (value) {  
			   var reg =/^[0-9]*$/;  
			   return reg.test(value);  
	       },  
	       message: '请输入数字'  
	   },
	   chineseEnglishNumber : {// 中文数字或英文
	       validator : function(value) { 
	           return /^[Α-￥a-zA-Z0-9]+$/g.test(value); 
	       }, 
	       message : '请输入中文数字或英文' 
	   },
	   account : {// 账号 
		    validator : function(value) { 
		        return /^[a-zA-Z][a-zA-Z0-9_]{2,15}$/g.test(value); 
		    }, 
		    message : '账号不合法（字母开头，允许3-16字节，允许字母数字下划线）' 
		},
		username : {// 用户名，可以是中文或英文
            validator : function(value) {
                return /^[u0391-uFFE5]+$/i.test(value)|/^w+[ws]+w+$/i.test(value);
            },
            message : '用户名不合法'
	    },
	    carNo:{
	        validator : function(value){
	            return /^[u4E00-u9FA5][da-zA-Z]{6}$/.test(value);
	        },
	        message : '车牌号码不合法（例：吉J12345）'
	    },
	    carenergin:{
	        validator : function(value){
	            return /^[a-zA-Z0-9]{16}$/.test(value);
	        },
	        message : '发动机型号不合法(例：FG6H012345654584)'
	    },
	    email:{
	        validator : function(value){
	        return /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})$/.test(value);
		    },
		    message : '电子邮件账号不合法(例：abc@126.com)'  
	    },
	    comparePwd:{
	    	 validator: function(value,param){
	    		 return value == $(param[0]).val();
	    		 },
	    		 message: '密码不一致.'
	    },
	    length:{
	    	validator:function(value,param){
	    		var len=$.trim(value).length;
	    		return len>=param[0]&&len<=param[1];
	    	},
	    	message:"输入内容长度必须介于{0}和{1}之间."
	    	}
        });
	

	/**
	 * form提交
	 *
	 * @param {string} _formId 提交的form控件ID
	 * @param {string} _url 提交form的Action，eg:/Monitor/UpLoad/SYS/UserBusiness/ImportPrivilageVehicle?QM_UserName=admin
	 * @param {Function} _onSumnit form提交时的方法，用于数据验证等
	 * @param {Function} _successCallback，成功时的回调函数
	 * @param {string} _submitType，ajax/form
	 */
	BasePage.FormSubmit = function (_formId, _url, _onSumnit, _successCallback,_submitType) {
		if (undefined == BasePage.urlPre)
		{
			BasePage.urlPre = window.top.document.getElementById('qmUrlPre').value;
		}
		var _a = BasePage.urlPre + _url;

		if(_submitType == 'form'){
			$('#' + _formId).attr('action',_a);
			$('#' + _formId).attr('method','post');
			$('#' + _formId)[0].submit();
		}
		else{
		    $('#' + _formId).form('submit', {
		        url: _a,
		        onSubmit: function (param) {
		            if (_onSumnit) {
		                if (!_onSumnit(param)) return false;
		            }
		        },
		        success: function (data) {
		        	if(_successCallback ){
		        		_successCallback(data);
		        	}
		        }
		    });
		}
	};
	/**
	 * 获得登录用户行政区码
	 * @returns
	 */
	BasePage.getCurrentUserArea =function(){
		var top_user= JSON.parse(parent.document.getElementById("top_loginUserInfo").value);
		return top_user.userArea;
	};
	/**
	 * 获得登录用户ID
	 * @returns
	 */
	BasePage.getCurrentUserId =function(){
		var top_user= JSON.parse(parent.document.getElementById("top_loginUserInfo").value);
		return  top_user.userId;
	};
	/**
	 * 获取登录用户名称
	 * @returns
	 */
	BasePage.getCurrentUserName =function(){
		var top_user= JSON.parse(parent.document.getElementById("top_loginUserInfo").value);
		return  top_user.userName;
	};
	/**
	 * 获取登录用户名称
	 * @returns
	 */
	BasePage.getCurrentUserLevel =function(){
		var top_user= JSON.parse(parent.document.getElementById("top_loginUserInfo").value);
		return  top_user.userLevel;
	};
	BasePage.getNowFormatDate=function() {
	    var date = new Date();
	    var seperator1 = "-";
	    var seperator2 = ":";
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
	    return currentdate;
	}
	/** 
	* 扩展表格列对齐属性： 
	*      自定义一个列字段属性： 
	*      headalign ：原始align属性针对数据有效, headalign针对列名有效
	*      
	*/  
	$.extend($.fn.datagrid.defaults,{  
	    onLoadSuccess : function() {  
	        var target = $(this);  
	        var opts = $.data(this, "datagrid").options;  
	        var panel = $(this).datagrid("getPanel");  
	        //获取列
	        var fields=$(this).datagrid('getColumnFields',false);
	        //datagrid头部 table 的第一个tr 的td们，即columns的集合
	        var headerTds =panel.find(".datagrid-view2 .datagrid-header .datagrid-header-inner table tr:first-child").children();
	        //重新设置列表头的对齐方式
	        headerTds.each(function (i, obj) {
	            var col = target.datagrid('getColumnOption',fields[i]);
	            if (!col.hidden && !col.checkbox)
	            {
	                var headalign=col.headalign||col.align||'left';
	                $("div:first-child", obj).css("text-align", headalign);
	            }
	        })
	    }  
	});
	//
	BasePage.setCookie=function(name,value){ 
        var Days = 30; 
        var exp = new Date(); 
        exp.setTime(exp.getTime() + Days*24*60*60*1000); 
        document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString(); 
    };
    //读取cookies 
    BasePage.getCookie=function(name) { 
        var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
        if(arr=document.cookie.match(reg))
            return unescape(arr[2]); 
        else 
            return null; 
    };
  //删除cookies 
    BasePage.delCookie=function(name){ 
        var exp = new Date(); 
        exp.setTime(exp.getTime() - 1); 
        var cval=BasePage.getCookie(name); 
        if(cval!=null) 
            document.cookie= name + "="+cval+";expires="+exp.toGMTString(); 
    };
})();


