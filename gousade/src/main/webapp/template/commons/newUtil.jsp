<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript">


function addFile(){
	//$("#file").append("<div ><input class=\"easyui-filebox\" name=\"file1\" data-options=\"prompt:'选择文件...',buttonText:'&nbsp;选&nbsp;择&nbsp;',\" style=\"width:80%\"><input type='button' value='删除' onclick='deleteFile(this)'/></div>");
	$("#file").append("<div style='text-align:left'><input type='file' name='attachments' size='70'/><input type='button' value='删除' onclick='deleteFile(this)'/></div>");
}

function deleteFile(obj){
	$(obj).parent().remove();
}

function deleteExistFile(obj,_index){
	var file = $("#newsAttachmentsDto_"+_index);
	file.parent().css('display','none');
	file.parent().prev().css('display','none');
	document.getElementById("newsAttachmentsDto["+_index+"]_delFlag").value="1";
}

function downLoadFile(id){
	var downLoadForm = $("#frm")[0];
	downLoadForm.action = "${ctx}/admin/attachment1/download?id="+id;
	downLoadForm.submit();
}
function download(id){
	var url ="${ctx}/admin/attachment1/download?id="+id;
	window.open(url, "_blank");
}
</script>