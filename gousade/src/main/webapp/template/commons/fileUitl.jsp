<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	function addFile() {
		//$("#file").append("<div ><input class=\"easyui-filebox\" name=\"file1\" data-options=\"prompt:'选择文件...',buttonText:'&nbsp;选&nbsp;择&nbsp;',\" style=\"width:80%\"><input type='button' value='删除' onclick='deleteFile(this)'/></div>");
		$("#file")
				.append(
						"<div style='text-align:left'><input type='file' name='attachments' size='70'/><input type='button' style='width: 50px; height: 25px; border: 1px solid #ccc; border-radius: 2px;' value='删除' onclick='deleteFile(this)'/></div>");
	}

	function deleteFile(obj) {
		$(obj).parent().remove();
	}

	function deleteExistFile(obj, _index) {
		var file = $("#articleAttachmentsDto_" + _index);
		file.parent().css('display', 'none');
		file.parent().prev().css('display', 'none');
		document.getElementById("articleAttachmentsDto[" + _index + "]_delFlag").value = "1";
	}

	function downLoadFile(id) {
		var downLoadForm = $("#frm")[0];
		downLoadForm.action = "${ctx}/admin/attachment/download?id=" + id;
		downLoadForm.submit();
	}

	/**
	 *文件下载
	 */
	function download(id) {
		if (null == $("#frmFileDown") || undefined == $("#frmFileDown").attr("id") || '' == $("#frmFileDown").attr("id")) {
			$("body").append('<iframe width="0" height="0" id="ifmFileDown" name="ifmFileDown"  style="display:none;"></iframe>');
			$("body").append('<form id="frmFileDown" target="ifmFileDown" method="post" style="display:none;"></form>');
		}
		var url = "${ctx}/admin/attachment/download?id=" + id;

		var downLoadForm = $("#frmFileDown")[0];
		downLoadForm.action = url;
		downLoadForm.submit();
	}

	/**
	 *模板下载
	 *
	 *fileType:模板类型
	 */
	function moduleDownFun(fileType) {
		if (null == $("#frmModuleDown") || undefined == $("#frmModuleDown").attr("id") || '' == $("#frmModuleDown").attr("id")) {
			$("body").append('<iframe width="0" height="0" id="moduleDown" name="moduleDown"  style="display:none;"></iframe>');
			$("body").append('<form id="frmModuleDown" target="moduleDown" method="post" style="display:none;"></form>');
		}

		var downLoadForm = $("#frmModuleDown")[0];
		var moduleUrl = "${ctx}/admin/examLibrary/modueldownload?moduleId=" + fileType;
		downLoadForm.action = moduleUrl;
		downLoadForm.submit();
	}
</script>