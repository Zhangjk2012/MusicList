KindEditor.ready(function(K) {
	var editor = K.create('#content', {
        resizeType : 0,
        minWidth:450,
        allowPreviewEmoticons : false,
        allowImageUpload : false,
        afterBlur:function() {
        	editor.sync();
        },
        items : [
            'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
            'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
            'insertunorderedlist']
    });
});
$(function(){
	createImgUpload("file_upload", "fileQueue", function(file,data){
		$('#picture').val(data);
        $('#img').attr("src",data);
	});
});

function clearUplaoder() {
	clearUploadFile("file_upload");
	$('#picture').val("");
	$('#img').attr("src","");
}

function cancelInfo() {
	var id = $("#id").val();
	if (id !== "") {
    	$.ajax({
            url : 'admin/removecontactInfo',
            data : {"id":id},
            method:'post',
            dataType : 'json',
            success : function(r) {
                if (r.success) {
                    $.messager.show({msg : "取消成功。",title : '成功'});
                    $('#id').val('');
                    $('#telephone').textbox('clear');
                    $('#address').textbox('clear');
                } else {
                    $.messager.alert('错误',"取消失败。",'error');
                }
            }
        });
	}
}
function submitForm() {
    $('#infoform').form('submit', {
        onSubmit : function() {
            return $(this).form('enableValidation').form('validate');
        },
        success : function(data) {
        	var d = eval("("+data+")");
        	if (d.id) {
        		$("#id").val(d.id);
        	}
            $.messager.show({
                msg : "保存成功。",
                title : '成功'
            });
        }
    });
}