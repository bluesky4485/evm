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
    var strPath=window.document.location.pathname;
    var postPath=strPath.substring(1,strPath.substr(1).indexOf('/')+1);

    if (!!check.html5) {
		$("#archivesCemeteryFile").uploadifive({
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
		$("#archivesCemeteryFile").uploadify({
			'width' : 60,
			'height' :24,
			'queueSizeLimit':10,
			'buttonText':'点击上传',
			'button_image_url' : '/'+ postPath +'/images/right-11.jpg',
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
					//$("#filename").append(d.bizData[0].fileName+"</br>");
					//如果先存储，可以返回ID
//					document.all.fileId.value += d.bizData[0].id+",";
					var a=Math.floor(Math.random()*9999+1000);
					var u = '/'+ postPath +"/upload/file/"+ d.bizData[0].filePath;
					$("#worKDocIDs").attr("value",$("#worKDocIDs").val()+d.bizData[0].id);
					u="/"+postPath+"/orderController.do?method=download&filePath="+d.bizData[0].filePath+"&fileName="+d.bizData[0].fileName+"&fileSize="+d.bizData[0].fileSize;
					var content="<a  href='"+u+"' >"+d.bizData[0].fileName+"</a> ";
					$("#worDocDiv").append(content+"</br>");
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

