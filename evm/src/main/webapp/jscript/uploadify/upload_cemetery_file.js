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


jQuery(document).ready(function(){
	debugger;
     var strPath=window.document.location.pathname;
     var postPath=strPath.substring(1,strPath.substr(1).indexOf('/')+1);

     if (!!check.html5) {
		$("#cemeteryFile").uploadifive({
			'fileObjName' : 'cemeteryFile',
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
					var u = '/'+ postPath +"/upload/pic/"+ d[0].filePath +"?"+ Math.floor(Math.random()*9999+1000);
					$("#cemeteryFileId").attr("value",d[0].id);
					$("#cemeteryFilePic").attr("src",u);
				}
			},
			'onError' : function(errorType) {
				showDoctorError(errorType);
			}
		});
	} else {
		$("#cemeteryFile").uploadify({
			'width' : 60,
			'height' :24,
			'queueSizeLimit':5,
			'buttonText':'点击上传',
			'button_image_url' : '/'+ postPath +'/images/right-11.jpg',
			'fileSizeLimit' : '10MB',
			'fileTypeDesc' : '文件',
			'fileTypeExts' : '*.gif; *.jpg; *.png; *.jpeg;*.rar;*.doc',
			'itemTemplate' : '<div class="uploadifive-queue-item" style="display: none;"></div>',
			'swf' : '/'+ postPath +'/jscript/uploadify/uploadify.swf',
			'uploader' : '/'+ postPath +'/file/OrderController.do?method=uploadFile',
			'onInit' : function() {
				//$(".uploadify-button").removeClass('uploadify-button');
			},
			'onUploadSuccess' : function(file, data, response) {
				var d = eval('('+data+')');
				if(d != ''){
					debugger;
					$("#filename").append(d.bizData[0].fileName+"</br>");
					document.all.fileId.value += d.bizData[0].id+",";
				}
			},
			'onUploadError' : function(file, errorCode, errorMsg, errorString) {
				showDoctorError(errorString);
			}
		});
	}
});


function showDoctorError(errorType) {
	var m;
	if ('FILE_SIZE_LIMIT_EXCEEDED' === errorType) {
		m = ("只能上传2M的图片");
	} else if ('FORBIDDEN_FILE_TYPE' === errorType) {
		m = ("图片格式限定在JPG, JPEG, GIF, PNG或BMP");
	} else {
		m = ('上传失败: ' + errorType);
	}
	alert(m);
}

