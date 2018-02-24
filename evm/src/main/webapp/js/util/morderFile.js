$(function(){
	$fileName = $("#fileName");
	$fileID = $("#fileID");
	$pickfiles = $("#pickfiles");
	
	//前端上传组件plupload
	uploader = new plupload.Uploader({//上传插件定义
		browse_button : 'pickfiles',//选择文件的按钮
		container : 'container',//文件上传容器
		runtimes : 'html5,flash,silverlight,html4',//设置运行环境，会按设置的顺序，可以选择的值有html5,gears,flash,silverlight,browserplus,html4
		flash_swf_url :   '/js/plupload-2.0.0/js/Moxie.swf',// Flash环境路径设置
		silverlight_xap_url :   '/js/plupload-2.0.0/js/Moxie.xap',
		url : '/evmis/morderController.do?method=uploadExcelFile',//上传文件的controller
		max_file_size : '50mb',//100b, 10kb, 10mb, 1gb
		chunk_size : '50mb',//分块大小，小于这个大小的不分块
		unique_names : true,//生成唯一文件名
		multi_selection: false,  //禁止多选
		// 如果可能的话，压缩图片大小
		/*resize : {
			width : 320,
			height : 240,
			quality : 90
		},*/
		// 指定要浏览的文件类型
		filters : [ {
			title : 'Excel文件',
			extensions : 'xls,xlsx'
		} ]
	});
	uploader.bind('Init', function(up, params) {//初始化时
		//$fileName.val("当前运行环境: " + params.runtime);
		$fileName.val('');
		$fileID.val('');
	});
	uploader.bind('BeforeUpload', function(uploader, file) {//上传之前
		
	});
	uploader.bind('FilesAdded', function(up, files) {//选择文件后
		$.each(up.files, function (i, file) {
	        if (up.files.length == 0) {
	            return;
	        }
	        up.removeFile(file);
	    });
		$.each(files, function(i, file) {
			$fileName.val(file.name);
			$fileID.val(file.id);
//			$pickfiles.hide();
//			var span = $('<span id="span" onclick="uploader.removeFile(uploader.getFile($fileID.val()));$fileName.val(\'\');$(\'#span\').remove();$pickfiles.show();" style="cursor:pointer;" class="icon-cancel" title="删除">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>');
//			$("#show").append(span);
			//$('#filelist').append('<div id="' + file.id + '">' + file.name + '(' + plupload.formatSize(file.size) + ')<strong></strong>' + '<span onclick="uploader.removeFile(uploader.getFile($(this).parent().attr(\'id\')));$(this).parent().remove();" style="cursor:pointer;" class="ext-icon-cross" title="删除">&nbsp;&nbsp;&nbsp;&nbsp;</span></div>');
		});
		up.refresh();
	});
	uploader.bind('UploadProgress', function(up, file) {//上传进度改变
		/* var msg;
		if (file.percent == 100) {
			msg = '99';//因为某些大文件上传到服务器需要合并的过程，所以强制客户看到99%，等后台合并完成...
		} else {
			msg = file.percent;
		}
		$('#' + file.id + '>strong').html(msg + '%'); */

		/* parent.sy.progressBar({//显示文件上传滚动条
			title : '文件上传中...',
			value : msg
		}); */
		parent.$.messager.progress({
			text : '正在上传，请稍后....'
		});
	});
	uploader.bind('Error', function(up, err) {//出现错误
		//$('#filelist').append("<div>错误代码: " + err.code + ", 描述信息: " + err.message + (err.file ? ", 文件名称: " + err.file.name : "") + "</div>");
		$fileName.val("错误代码：" + err.code + "；错误信息:" + err.message);
		$fileID.val('');
		$pickfiles.hide();
		var span = $('<span id="span" onclick="$fileName.val(\'\');$(\'#span\').remove();$pickfiles.show();" style="cursor:pointer;" class="icon-cancel" title="删除">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>');
		$("#show").append(span);
		up.refresh();
	});
	uploader.bind('FileUploaded', function(up, file, info) {//上传完毕
		parent.$.messager.progress('close');
		//关闭外围dialog
		$("#fileLoad_window").window("close");
		$fileName.val('');
		$fileID.val('');
		up.removeFile(file);
		//window.parent.exportFinish(info.response);
		var data=JSON.parse(info.response)
		//TODO:导入数据预览
		$("#dataPreviewWindow").window("open");
		$("#dataPreViewDg").datagrid('loadData', data.bizData);
		
		
		//$.messager.alert('提示', '上传完毕！', 'info');
		//$('#' + file.id + '>strong').html("100%");
		/* var response = $.parseJSON(info.response);
		if (response.status) {
			$('#' + file.id + '>strong').html("100%");
			//console.info(response.fileUrl);
			//console.info(file.name);
			//$('#f1').append('<input type="hidden" name="fileUrl" value="'+response.fileUrl+'"/>');
			//$('#f1').append('<input type="hidden" name="fileName" value="'+file.name+'"/><br/>');
			//$(':input[name="data.photo"]').val(response.fileUrl);
		} */
	});
	uploader.bind('StateChanged', function(uploader) {
		/* if (uploader.files.length === (uploader.total.uploaded + uploader.total.failed)) {
			parent.$.messager.progress('close');
			$.messager.alert('提示', '上传完毕！', 'info');
		} */
	});
	uploader.init();
});

function upload() {
	var length = uploader.files.length;
	if(length == 0) {
		parent.$.messager.alert('提示', '请选择一个文件！', 'error');
	} else if (length == 1) {
		uploader.start();
	} else {
		$.messager.alert('提示','上传队列中存在'+ uploader.files.length+"个文件", 'error');
	}
}