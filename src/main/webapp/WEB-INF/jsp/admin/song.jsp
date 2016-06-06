<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>歌曲管理</title>
<link rel="stylesheet" type="text/css" href="static/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="static/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="static/uploadify/uploadify.css" />
<link rel="stylesheet" type="text/css" href="kindeditor/themes/simple/simple.css" />
<style type="text/css">
	.f1{
        width:200px;
    }
</style>
</head>
<body>
	<table id="songlist"></table>
	<div id="win" iconCls="icon-save" title="歌曲信息">
        <form id="songform" action="admin/addSong" data-options="novalidate:true" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td colspan="4">
                        <div id="songQueue"></div>
                    </td>
                </tr>
                <tr>
                    <td>歌曲文件:</td>
                    <td>
                        <div>
                            <input type="hidden" id="songPath" name="songPath" />
                            <input type="hidden" id="songFlag" name="songFlag" />
                            <input id="song_upload" name="song_upload" type="file" multiple="multiple">
                        </div>
                    </td>
                    <td>
                        <a href="javascript:void(0)" class="easyui-linkbutton" id="btnUpload"
                                onclick="javascript: $('#song_upload').uploadify('upload', '*')">上传</a>
                        <a href="javascript:void(0)" class="easyui-linkbutton" id="btnCancelUpload"
                            onclick="javascript:clearSongUplaoder()">取消</a>
                    </td>
                </tr>
                <tr>
                    <td>文件名称:</td>
                    <td>
                        <input id="songName" class="f1 easyui-textbox" data-options="required:true" name="songName"></input>
                    </td>
                    <td>时长:</td>
                    <td>
                        <input id="trackLength" name="trackLength" class="f1 easyui-textbox"></input>
                    </td>
                </tr>
                <tr>
                    <td>歌手:</td>
                    <td>
                        <input class="f1 easyui-textbox" id="singer" name="singerName">
                    </td>
                    <td>专辑:</td>
                    <td>
                        <input class="f1 easyui-textbox" id="album" name="albumName">
                    </td>
                </tr>
                <tr>
                    <td>简介:</td>
                    <td colspan="3">
                        <input name="briefIntroduction" data-options="multiline:true" style="height:60px;width:490px;" class="easyui-textbox"></input>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                       <div id="fileQueue"></div> 
                    </td>
                </tr>
                <tr>
                    <td>上传图片:</td>
                    <td>
                        <div>
                            <input type="hidden" id="picture" name="picture" />
                            <input id="file_upload" name="file_upload" type="file" multiple="multiple">
                        </div>
                    </td>
                    <td>
                        <a href="javascript:void(0)" class="easyui-linkbutton" id="btnUpload"
                                onclick="javascript: $('#file_upload').uploadify('upload', '*')">上传</a>
                        <a href="javascript:void(0)" class="easyui-linkbutton" id="btnCancelUpload"
                            onclick="javascript:clearUplaoder()">取消</a>
                    </td>
                </tr>
                <tr>
                    <td>歌曲图片:</td>
                    <td><img id="img" class="f1" height="100" src=""/></td>
                </tr>
                <tr>
                    <td>歌词:</td>
                </tr>
                <tr>
                    <td colspan="4">
                        <textarea name="lyric" id="lyric" style="width:600px;height:230px;visibility:hidden;"></textarea>
                    </td>
                </tr>
            </table>
        </form>
	    <div id="addFooter" style="text-align:center;padding:5px;">
	    	<a href="javascript:void(0)" icon="icon-ok" class="easyui-linkbutton" onclick="submitForm()">保存</a>
	    	<a href="javascript:void(0)" icon="icon-cancel" class="easyui-linkbutton" onclick="closeWin()">取消</a>
	    </div>
	</div>
	<div id="updateWin" iconCls="icon-edit" title="修改歌曲信息">
        <form id="updateform" action="admin/updateSong" data-options="novalidate:true" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>文件名称:</td>
                    <td>
                        <input type="hidden" name="id">
                        <input type="hidden" name="songPath" />
                        <input type="hidden" name="songFlag" />
                        <input id="updateName" class="f1 easyui-textbox" data-options="required:true,editable:false" name="songName"></input>
                    </td>
                    <td>时长:</td>
                    <td>
                        <input id="updateTrackLength" data-options="editable:false" name="trackLength" class="f1 easyui-textbox"></input>
                    </td>
                </tr>
                <tr>
                    <td>歌手:</td>
                    <td>
                        <input class="f1 easyui-textbox" data-options="required:true,editable:false" id="updateSinger" name="singerName">
                    </td>
                    <td>专辑:</td>
                    <td>
                        <input class="f1 easyui-textbox" data-options="required:true,editable:false" id="updateAlbum" name="albumName">
                    </td>
                </tr>
                <tr>
                    <td>简介:</td>
                    <td colspan="3">
                        <input name="briefIntroduction" data-options="multiline:true" style="height:60px;width:490px;" class="easyui-textbox"></input>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                       <div id="updateQueue"></div> 
                    </td>
                </tr>
                <tr>
                    <td>上传图片:</td>
                    <td>
						<div>
                            <input type="hidden" id="updatePicture" name="picture" />
                            <input id="update_upload" name="update_upload" type="file" multiple="multiple">
                        </div>
					</td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="btnUpload"
                                onclick="javascript: $('#update_upload').uploadify('upload', '*')">上传</a>
                        <a href="javascript:void(0)" class="easyui-linkbutton" id="btnCancelUpload"
                            onclick="javascript:clearUpdateUplaoder()">取消</a>
					</td>
                </tr>
                <tr>
                    <td>歌曲图片:</td>
                    <td><img id="updateImg" class="f1" height="100" src=""/></td>
                </tr>
                <tr>
                    <td>歌词:</td>
                </tr>
                <tr>
                    <td colspan="4">
                        <textarea name="lyric" id="updateLyric" style="width:600px;height:230px;visibility:hidden;"></textarea>
                    </td>
                </tr>
            </table>
        </form>
        <div id="updateFooter" style="text-align:center;padding:5px">
            <a href="javascript:void(0)" icon="icon-ok" class="easyui-linkbutton" onclick="submitUpdateForm()">保存</a>
            <a href="javascript:void(0)" icon="icon-cancel" class="easyui-linkbutton" onclick="closeUpdateWin()">取消</a>
        </div>
    </div>
    <script type="text/javascript" src="static/js/jquery.min.js"></script>
    <script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="static/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="static/uploadify/swfobject.js"></script>
    <script type="text/javascript" src="static/uploadify/jquery.uploadify.min.js"></script>
    <script type="text/javascript" src="source/song.js"></script>
    <script type="text/javascript" src="js/uploadfile.js"></script>
    <script type="text/javascript" src="kindeditor/kindeditor-min.js"></script>
    <script type="text/javascript" src="kindeditor/lang/zh_CN.js"></script>
    <script type="text/javascript">
    KindEditor.ready(function(K) {
    	var editor = K.create('#lyric', {
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
    	var editor1 = K.create('#updateLyric', {
            resizeType : 0,
            minWidth:450,
            allowPreviewEmoticons : false,
            allowImageUpload : false,
            afterBlur:function() {
            	editor1.sync();
            },
            items : [
                'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                'insertunorderedlist']
        });
    });
    </script>
</body>
</html>