;if (!check)
var check={};
check.html5 = html5();
/**
 * 检测是否支持html5
 * @return {[type]} [description]
 */
 
 function html5() {
 	var rs = false;
	return rs;
 	//if (typeof(Worker) !== "undefined")
 	//	rs = true;
 	//return rs;
 }

 var orderworkFile={}||orderworkFile;
 (function(){
	 orderworkFile.loadCtrl=function(){
		 var strPath=window.document.location.pathname;
		    var postPath=strPath.substring(1,strPath.substr(1).indexOf('/')+1);
		   
		    if (!!check.html5) {
		    	 var orderNo=$("#orderNo").textbox("getValue"); 
				$("#archivesCemeteryFile").uploadifive({
					'formData': {'orderNo':orderNo},
					'fileObjName' : 'archivesCemeteryFile',
					'buttonText' : '选择',
					'buttonClass' : 'touxiang-images',
					'width' : 10,
					'height' : 10,
					'fileSizeLimit' : '2MB',
					'fileType' : 'image',
					'itemTemplate' : '<div class="uploadifive-queue-item" style="display: none;"></div>',
					// 'removeCompleted' : true,
					'formData' : {
					},
					'uploadScript' : base_url + 'upload/uploadheader',
					'onInit' : function() {
						$(".uploadifive-button").removeClass('uploadifive-button');
					},
					'onUploadComplete' : function(file, data) {
						var d = eval('('+data+')');
						if(d != ''){
							$("#worKDocIDs").val($("#worKDocIDs").val()+"?"+d.bizData[0].filePath);
							if(d.bizData.length>0){
								for(var i=0;i<d.bizData.length;i++){
									var para="&filePath="+d.bizData[i].filePath+"&fileName="+d.bizData[i].fileName+"&fileSize="+d.bizData[i].fileSize;
									$("#worKDocIDs").val($("#worKDocIDs").val()+"?"+para);
									var u="/"+postPath+"/orderController.do?method=download"+para;
									var content="<a  href='"+u+"' >"+d.bizData[i].fileName+"</a> ";
									$("#worDocDiv").append(content+"</br>");
								}
							}
						}
					},
					'onError' : function(errorType) {
						showDoctorError(errorType);
					}
				});
			} else {
				 var orderNo=$("#orderNo").textbox("getValue"); 
				$("#archivesCemeteryFile").uploadify({
					'formData': {'orderNo':orderNo},
					'width' : 60,
					'height' :24,
					'queueSizeLimit':10,
					'buttonText':'上传',
					'buttonClass' :'line-mid', 
					'fileSizeLimit' : '10MB',
					'fileTypeDesc' : '文件',
					'fileTypeExts' : '*.gif; *.jpg; *.png; *.jpeg;*.rar;*.doc',
					'itemTemplate' : '<div class="uploadifive-queue-item" style="display: none;"></div>',
					'swf' : '/'+ postPath +'/jscript/uploadify/uploadify.swf',
					'uploader' : '/'+ postPath +'/orderController.do?method=uploadFile',
					'onInit' : function() {
						//$(".uploadify-button").removeClass('uploadify-button');
					},
					'onUploadSuccess' : function(file, data, response) {
						var d = eval('('+data+')');
						if(d != ''){
							$("#worKDocIDs").val($("#worKDocIDs").val()+"&"+d.bizData[0].filePath);
							var u="/"+postPath+"/orderController.do?method=download&filePath="+encodeURIComponent(d.bizData[0].filePath)+"&fileName="+d.bizData[0].fileName+"&fileSize="+d.bizData[0].fileSize;
							var bizType=1;
							var submitPara=d.bizData[0].filePath+"&"+d.bizData[0].fileName+"&"+bizType;
							var content="<div><input type=\"hidden\" value='"+submitPara+"'/><a   href='"+u+"' >"+d.bizData[0].fileName+"</a><a onclick=javascript:deleteFileDiv(this)>删除</a></div> ";
							$("#worDocDiv").append(content);
						}
					},
					'onUploadError' : function(file, errorCode, errorMsg, errorString) {
						orderworkFile.showDoctorError(errorString);
					}
				});
			} 
	 };
	 orderworkFile.showDoctorError=function(errorType) {
    		var m;
    		if ('FILE_SIZE_LIMIT_EXCEEDED' === errorType) {
    			m = ("只能上传2M的图片");
    		} else if ('FORBIDDEN_FILE_TYPE' === errorType) {
    			m = ("图片格式限定在JPG, JPEG, GIF, PNG或BMP");
    		} else {
    			m = ('上传失败: ' + errorType);
    		}
    		alert(m);
    	};
 })();

 

function deleteFileDiv(a){
	a.parentNode.remove();
}