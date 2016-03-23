function createImgPlupload(obj,url,showFile) {
	var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
		runtimes : 'html5,html4,flash',
		browse_button : obj,
		url : url,
		flash_swf_url : 'Moxie.swf',
		filters: {
		  mime_types : [ //只允许上传图片文件和rar压缩文件
		    { title : "图片文件", extensions : "jpg,png" }
		  ],
		  max_file_size : '100kb' //最大只能上传100kb的文件
		},
		prevent_duplicates : true, //不允许队列中存在重复文件
		resize: {width: 320, height: 240, quality: 90},
		multiple_queues: false
	});
	uploader.init(); //初始化

	//绑定文件添加进队列事件
	uploader.bind('FilesAdded',function(uploader,files){
		for(var i = 0, len = files.length; i<len; i++){
			var file_name = files[i].name; //文件名
			//构造html来更新UI
			var html = '<li id="file-' + files[i].id +'"><p class="file-name">' + file_name + '</p><p class="progress"></p></li>';
			$(html).appendTo('#'+showFile);
			previewImage(files[i],function(imgsrc){
                $('#file-'+files[i].id).append('<img src="'+ imgsrc +'" />');
            });
		}
	});

	//绑定文件上传进度事件
	uploader.bind('UploadProgress',function(uploader,file){
		$('#file-'+file.id+' .progress').css('width',file.percent + '%');//控制进度条
	});
}

function previewImage(file,callback){
    if(!file || !/image\//.test(file.type)) return;
    var preloader = new mOxie.Image();
    preloader.onload = function() {
        preloader.downsize( 300, 300 );
        var imgsrc = preloader.type=='image/jpeg' ? preloader.getAsDataURL('image/jpeg',80) : preloader.getAsDataURL(); //得到图片src,实质为一个base64编码的数据
        callback && callback(imgsrc);
        preloader.destroy();
        preloader = null;
    };
    preloader.load( file.getSource() );
}