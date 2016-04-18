function createImgUpload(obj,showObj,callback) {
	$('#'+obj).uploadify({
		'swf' : 'static/uploadify/uploadify.swf', //FLash文件路径
		'buttonText' : '浏  览', //按钮文本
		'uploader' : 'file/uploadImg', //处理文件上传Action
		'queueID' : showObj, //队列的ID
		folder : 'uploader',
		'queueSizeLimit' : 1, //队列最多可上传文件数量，默认为999
		'auto' : false, //选择文件后是否自动上传，默认为true
		'method' : "post",
		'multi' : false, //是否为多选，默认为true
		'removeCompleted' : true, //是否完成后移除序列，默认为true
		'fileSizeLimit' : '10MB', //单个文件大小，0为无限制，可接受KB,MB,GB等单位的字符串值
		'fileObjName' : 'file',
		'fileTypeDesc' : 'Image Files', //文件描述
		'fileTypeExts' : '*.jpg;*.png;*.bmp', //上传的文件后缀过滤器
		onUploadSuccess : function(file, data, response) { //一个文件上传成功后的响应事件处理
			callback(file, data);
		},
		onUploadStart : function(file) {
		}
	});
}

function createMp34Upload(obj,showObj,callback) {
	$('#'+obj).uploadify({
		'swf' : 'static/uploadify/uploadify.swf', //FLash文件路径
		'buttonText' : '浏  览', //按钮文本
		'uploader' : 'file/uploadSong', //处理文件上传Action
		'queueID' : showObj, //队列的ID
		folder : 'uploader',
		'queueSizeLimit' : 1, //队列最多可上传文件数量，默认为999
		'auto' : false, //选择文件后是否自动上传，默认为true
		'method' : "post",
		'multi' : false, //是否为多选，默认为true
		'removeCompleted' : true, //是否完成后移除序列，默认为true
		'fileSizeLimit' : '20MB', //单个文件大小，0为无限制，可接受KB,MB,GB等单位的字符串值
		fileObjName : 'file',
		'fileTypeDesc' : 'Mp3/MP4 Files', //文件描述
		'fileTypeExts' : '*.mp3;*.mp4', //上传的文件后缀过滤器
		onUploadSuccess : function(file, data, response) { //一个文件上传成功后的响应事件处理
			callback(file, data);
		},
		onUploadStart : function(file) {
		}
	});
}

function clearUploadFile(obj) {
	$('#'+obj).uploadify('cancel', '*');
}

