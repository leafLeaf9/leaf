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
<form class="form-inline person-search-form search-form" id="user-search-form" method="post">
	<div class="input-group">
	  <div class="input-group-prepend">
	    <span class="input-group-text">用户名:</span>
	  </div>
	  <input type="text" class="form-control input-sm" name="userName" placeholder="userName">
	</div>
	<button type="submit" class="btn btn-primary btn-sm">查询</button>
	<button type="button" class="btn btn-warning btn-sm" onclick="userClearSearch()">清空</button>
	<button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#user-insert-modal">新增</button>
</form>
</div>
</div>

<table id="user-datagrid" class="table table-bordered table-hover table-striped">
    <thead>
        <tr>
            <th>用户名</th>
            <th>姓名</th>
            <th>联系方式</th>
            <th>最后登录时间</th>
            <th>创建时间</th>
        </tr>
    </thead>
    <tbody>
    </tbody>
</table>

<div class="modal fade " id="user-insert-modal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
			<h5 class="modal-title su-modal-title">编辑信息</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="user-insert-form">
					<div class="input-group mb-3">
						<label class="col-sm-3 control-label">登录名：</label>
						<div class="col-sm-9">
							<input class="form-control" name="id" type="text" style="display:none;">
							<input type="text" class="form-control input-sm" placeholder="" name="userId">
						</div>
					</div>
					<div class="input-group mb-3">
						<label class="col-sm-3 control-label">姓名：</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" placeholder="" name="userName">
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
							<input type="phonenumber" class="form-control" placeholder="" name="mobile">
						</div>
					</div>
					<div class="input-group mb-3">
						<label class="col-sm-3 control-label">角色：</label>
						<div class="col-sm-9">
						<select class="form-control select2" multiple="multiple" data-placeholder="" name="roleIds"
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

<script>
$(function() {
    $.user_datagrid=$('#user-datagrid').DataTable({
    	ajax: {
			url: "${ctx}/selectUserList",
			type: "POST",
			data: {
				"searchMap": function () {
					return JSON.stringify($('#user-search-form').serializeObject());
				}
			}
		},
		serverSide: true,
		pagingType: "full_numbers",
		columns: [
			{
				data: 'userId',
			},
			{
				data: 'userName',
			},
			{
				data: 'phonenumber',
			},
			{
				data: 'lastLoginTime',
			},
			{
				data: 'created',
			},
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

$('#user-insert-form').submit(function(e){
	var userInsertForm = new FormData($("#user-insert-form")[0]);
	$.ajax({
        url: "${ctx}/userEdit",
        type: "POST",
        data: userInsertForm,
        cache : false,
		processData : false,// 告诉jQuery不要去处理发送的数据
		contentType : false,// 告诉jQuery不要去设置Content-Type请求头
        dataType: 'json',
        success: function (result) {
        	$.user_datagrid.draw(false);
        	$('#user-insert-modal').modal('hide');
        	layer.msg(result.msg, {
				icon : 1,
				time : 1000,
			});
        },
        error: function () {
        	layer.msg('ajax error', {
				icon : 2,
				time : 1000,
			});
        }
	});
	return false;
});

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