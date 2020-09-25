<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/template/commons/total.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户管理新</title>
</head>
<body>
<div class="row">
<div class="col-md-12">
<div class="nav-tabs-custom">
	<ul class="nav nav-tabs">
	    <li class="nav-item"><a class="nav-link active" href="#tab_1" data-toggle="tab" aria-expanded="false">用户管理</a></li>
	    <li class="nav-item"><a class="nav-link" href="#custom-tabs-one-profile" data-toggle="tab" aria-expanded="false">
	    Profile</a></li>
	    <li class="nav-item"><a class="nav-link" href="#tab_3" data-toggle="tab" aria-expanded="false">Tab 3</a></li>
    </ul>
	<div class="tab-content">
	<div class="tab-pane active" id="tab_1">
	<form class="form-inline search-form" id="user-search-form" method="post">
		<div class="input-group">
		  <div class="input-group-prepend">
		    <span class="input-group-text">用户名:</span>
		  </div>
		  <input type="text" class="form-control input-sm" name="userName" placeholder="userName">
		</div>
		<button type="submit" class="btn btn-primary btn-sm">查询</button>
		<button type="button" class="btn btn-warning btn-sm" onclick="userClearSearch()">清空</button>
		<button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#user-insert-modal">新增</button>
		<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#file-upload-modal">上传文件测试</button>
		<button type="button" class="btn btn-dark btn-sm" onclick="fileDownload()">下载文件测试</button>
		<button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#excel-upload-modal">上传excel</button>
	</form>

	<table id="user-datagrid" class="table table-bordered table-hover table-striped">
	    <thead>
	        <tr>
	            <th>用户名</th>
	            <th>姓名</th>
	            <th>联系方式</th>
	            <th>最后登录时间</th>
	            <th>创建时间</th>
	            <th>操作</th>
	        </tr>
	    </thead>
	    <tbody>
	    </tbody>
	</table>
	</div>
	<div class="tab-pane fade" id="custom-tabs-one-profile" role="tabpanel" aria-labelledby="custom-tabs-one-profile-tab">
    Mauris tincidunt mi at erat gravida, eget tristique urna bibendum. Mauris pharetra purus ut ligula tempor,
    vulputate metus facilisis.
    </div>
    <div class="tab-pane" id="tab_3">
	D:/AdminLTE-3.0.5/pages/UI/navbar.html
    </div>
	</div>
</div>
</div>
</div>

<div class="modal fade " id="user-insert-modal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
			<h5 class="modal-title su-modal-title">编辑信息</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<form class="form-horizontal" id="user-insert-form">
			<div class="modal-body">
				
					<div class="input-group mb-3">
						<label class="col-sm-3 control-label">登录名：</label>
						<div class="col-sm-9">
							<input class="form-control" name="id" type="text" style="display:none;">
							<input type="text" class="form-control input-sm" placeholder="" name="userId" required="required">
						</div>
					</div>
					<div class="input-group mb-3">
						<label class="col-sm-3 control-label">姓名：</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" placeholder="" name="userName" required="required">
						</div>
					</div>
					<div class="input-group mb-3">
						<label class="col-sm-3 control-label">密码：</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" placeholder="密码不修改请留空！" name="password">
						</div>
					</div>
					<div class="input-group mb-3">
						<label class="col-sm-3 control-label">手机号：</label>
						<div class="col-sm-9">
							<input type="number" class="form-control" placeholder="" name="phoneNumber" required="required">
						</div>
					</div>
					<div class="input-group mb-3">
						<label class="col-sm-3 control-label">角色：</label>
						<div class="col-sm-9">
						<select class="form-control select2" multiple="multiple" data-placeholder="" name="roleIds" required="required"
						style="width: 100%">
						</select>
						</div>
					</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-light pull-left" data-dismiss="modal">关闭</button>
				<button type="submit" class="btn btn-primary">保存</button>
			</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<div class="modal fade " id="file-upload-modal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
			<h5 class="modal-title su-modal-title">上传文件</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<form class="form-horizontal" id="file-upload-form">
			<div class="modal-body">
				
					<div class="input-group mb-3">
						<label class="col-sm-3 control-label">文件：</label>
						<div class="col-sm-9">
							<input type="file" name="attachments" class="form-control input-sm"
							multiple="multiple" required="required">
						</div>
					</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-light pull-left" data-dismiss="modal">关闭</button>
				<button type="submit" class="btn btn-primary">保存</button>
			</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<div class="modal fade " id="excel-upload-modal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
			<h5 class="modal-title su-modal-title">上传文件</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<form class="form-horizontal" id="excel-upload-form">
			<div class="modal-body">
				
					<div class="input-group mb-3">
						<label class="col-sm-3 control-label">文件：</label>
						<div class="col-sm-9">
							<input type="file" name="file" class="form-control input-sm"
							required="required">
						</div>
					</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-light pull-left" data-dismiss="modal">关闭</button>
				<button type="submit" class="btn btn-primary">保存</button>
			</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<script>
$(function() {
    $.user_datagrid=$('#user-datagrid').DataTable({
    	ajax: {
			url: "${ctx}/admin/sysUser/selectUserList",
			type: "POST",
			data: {
				"searchMap": function () {
					return JSON.stringify($('#user-search-form').serializeObject());
				}
			}
		},
		ordering: false,
// 		order: [[ 0, 'desc' ]],
		columns: [
			{
				data: 'userId',
			},
			{
				data: 'userName',
			},
			{
				data: 'phoneNumber',
			},
			{
				data: 'lastlogintime',
			},
			{
				data: 'createTime',
			},
			{
				data: '',
				render: function (data, type, row, meta) {
					return '<button type="button" class="btn btn-warning" onclick=sysUserEdit(' +
						JSON.stringify(row.id) +
						')><i class="fa fa-edit"></i> 编辑</button>' +
						'<button type="button" class="btn btn-danger" onclick=sysUserDelete(' +
						JSON.stringify(row.id) +
						')><i class="fa fa-close"></i> 删除</button></td>'
				}
			}
		],
    });
    getRoles();
});

$("#user-search-form").submit(
	function(e) {
		$.user_datagrid.ajax.reload(null,false);
	return false;
});

function userClearSearch(){
	$('#user-search-form')[0].reset();
	$.user_datagrid.ajax.reload(null,false);
}

function sysUserEdit(id){
	$("#user-insert-form input[name=id]").val(id);
	$.ajax({
        url: "${ctx}/admin/sysUser/selectByPrimaryKey",
        type: "POST",
        data: {id:id},
        dataType: 'json',
        success: function (result) {
        	if(!result){
        		layer.open({
       				content : "查询失败",
       				shadeClose : true,
       			});
        	}else{
        		$('#user-insert-form input[name=id]').val(result.id);
            	$('#user-insert-form input[name=userId]').val(result.userId);
            	$('#user-insert-form input[name=userName]').val(result.userName);
            	$('#user-insert-form input[name=phoneNumber]').val(result.phoneNumber);
            	if(result.roleIds!=null){
            		$('#user-insert-form select[name=roleIds]').val(result.roleIds.split(",")).select2();
            	}
            	$('#user-insert-modal').modal('show');
        	}
        },
        error: function () {
        	layer.open({
   				content : 'ajax error',
   				shadeClose : true,
   			});
        },
	});
}

$('#user-insert-form').submit(function(e){
	console.log(1);
	var userInsertForm = new FormData($("#user-insert-form")[0]);
	$.ajax({
        url: "${ctx}/admin/sysUser/userEdit",
        type: "POST",
        data: userInsertForm,
        cache : false,
		processData : false,// 告诉jQuery不要去处理发送的数据
		contentType : false,// 告诉jQuery不要去设置Content-Type请求头
        dataType: 'json',
        success: function (result) {
        	$.user_datagrid.draw(false);
        	$('#user-insert-modal').modal('hide');
        	layer.msg(result.message, {
				icon : 1,
				time : 1000,
			});
        },
        error: function () {
        	layer.msg('ajax error', {
				icon : 2,
				time : 1000,
			});
        },
	});
	return false;
});

$('#file-upload-form').submit(function(e){
	var fileUploadForm = new FormData($("#file-upload-form")[0]);
	$.ajax({
        url: "${ctx}/admin/sysUser/fileUpload",
        type: "POST",
        data: fileUploadForm,
        cache : false,
		processData : false,// 告诉jQuery不要去处理发送的数据
		contentType : false,// 告诉jQuery不要去设置Content-Type请求头
        dataType: 'json',
        success: function (result) {
        	$('#file-upload-modal').modal('hide');
       		layer.open({
   				content : result.message,
   				shadeClose : true,
   			});
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        	console.log(XMLHttpRequest);
        	layer.open({
   				content : XMLHttpRequest.responseJSON.message,
   				shadeClose : true,
   			});
        },
	});
	return false;
});

function fileDownload(){
	var myform = $("<form></form>");
	myform.attr('action', "${ctx}/admin/sysUser/fileDownload");
	myform.attr('method', 'post');
	myform.appendTo('body').submit();
}

$('#excel-upload-form').submit(function(e){
	open_shield();
	$('#excel-upload-modal').modal('hide');
	var fileUploadForm = new FormData($("#excel-upload-form")[0]);
	$.ajax({
        url: "${ctx}/easy-excel-data/upload",
        type: "POST",
        data: fileUploadForm,
        cache : false,
		processData : false,// 告诉jQuery不要去处理发送的数据
		contentType : false,// 告诉jQuery不要去设置Content-Type请求头
        dataType: 'json',
        success: function (result) {
        	cancel_shield();
       		layer.open({
   				content : result.message,
   				shadeClose : true,
   			});
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        	console.log(XMLHttpRequest);
        	cancel_shield();
        	layer.open({
   				content : XMLHttpRequest.responseJSON.message,
   				shadeClose : true,
   			});
        },
	});
	return false;
});

function sysUserDelete(id){
	layer.confirm('是否删除该用户?', {icon: 3, title:'删除用户确认'}, function(index){
		$.ajax({
	        url: "${ctx}/admin/sysUser/deleteUserByid",
	        type: "POST",
	        data: {id:id},
	        dataType: 'json',
	        success: function (result) {
	        	$.user_datagrid.draw(false);
	        	layer.msg(result.message, {
					icon : 1,
					time : 1000,
				});
	        },
	        error: function () {
	        	layer.msg('ajax error', {
					icon : 2,
					time : 1000,
				});
	        },
		});
		  layer.close(index);
	});
}

$('#user-insert-modal').on('hide.bs.modal', function () {
	 $('#user-insert-form')[0].reset();
	 $("#user-insert-form select[name=roleIds]").val("").select2();
});

function getRoles() {
	$.ajax({
		type: "post",
		url: "${ctx}/getRoles",
		success: function (data) {
			for (var i = 0; i < data.length; i++) {
				$("#user-insert-form select[name=roleIds]").append(
					"<option value='" + data[i].id + "'>" + data[i].name +
					"</option>");
			}
			$("#user-insert-form select[name=roleIds]").select2();
		},
		dataType: "json"
	});
}

$('.dayDatetimepicker').datetimepicker({
	language:"zh-CN",    //语言选择中文
	format:"yyyy-mm-dd hh:ii:ss",    //格式化日期
	timepicker:true,     //关闭时间选项
	todayButton:false,    //关闭选择今天按钮
	autoclose: 1,        //选择完日期后，弹出框自动关闭
	startView:2,         //打开弹出框时，显示到什么格式,3代表月
	minView: 0,          //能选择到的最小日期格式
// 	bootcssVer:3,//显示向左向右的箭头
});
</script>
</body>
</html>