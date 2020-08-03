<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/template/commons/total.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>datatables测试页面</title>
</head>
<body>
<button type="button" class="btn btn-danger btn-sm">新增</button>
<button type="button" class="btn btn-primary">Primary</button>
<button type="button" class="btn btn-secondary">Secondary</button>
<button type="button" class="btn btn-success">Success</button>
<button type="button" class="btn btn-danger">Danger</button>
<button type="button" class="btn btn-warning">Warning</button>
<button type="button" class="btn btn-info">Info</button>
<button type="button" class="btn btn-light">Light</button>
<button type="button" class="btn btn-dark">Dark</button>
<button type="button" class="btn btn-link">Link</button>
<br/>
<button type="button" class="btn btn-outline-primary">Primary</button>
<button type="button" class="btn btn-outline-secondary">Secondary</button>
<button type="button" class="btn btn-outline-success">Success</button>
<button type="button" class="btn btn-outline-danger">Danger</button>
<button type="button" class="btn btn-outline-warning">Warning</button>
<button type="button" class="btn btn-outline-info">Info</button>
<button type="button" class="btn btn-outline-light">Light</button>
<button type="button" class="btn btn-outline-dark">Dark</button><i class="iconfont fi-check"></i>

<br/>
<div class="spinner-border text-primary" role="status">
  <span class="sr-only">Loading...</span>
</div>
<div class="spinner-border text-secondary" role="status">
  <span class="sr-only">L...</span>
</div>
<div class="spinner-border text-success" role="status">
  <span class="sr-only">Loading...</span>
</div>
<div class="spinner-border text-danger" role="status">
  <span class="sr-only">Loading...</span>
</div>
<div class="spinner-border text-warning" role="status">
  <span class="sr-only">Loading...</span>
</div>
<div class="spinner-border text-info" role="status">
  <span class="sr-only">Loading...</span>
</div>
<div class="spinner-border text-light" role="status">
  <span class="sr-only">Loading...</span>
</div>
<div class="spinner-border text-dark" role="status">
  <span class="sr-only">Loading...</span>
</div>
<br/>
<div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1">用户名</span>
  </div>
  <input type="text" class="form-control col-sm-3" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1">
</div>
<br/>
<div class="form-group">
<label>提交时间:</label>
<input name="startTime" type="datetime-local" class="form-control input-sm col-sm-3">-
<input name="endTime" type="text" class="form-control input-sm dayDatetimepicker col-sm-3">
</div>
<br/>
<select class="form-control col-sm-3" id="id_select2_demo1" multiple="multiple"></select>
<table id="table_id_example" class="table table-bordered table-hover table-striped"><!-- https://v4.bootcss.com/docs/content/tables/ -->
    <thead>
        <tr>
            <th>Column 1</th>
            <th>Column 2</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Row 1 Data 1Row 1 Data 1Row 1 Data 1Row 1 Data 1Row 1 Data 1Row 1 Data 1Row 1 Data 1Row 1 Data 1Row 1 Data 1</td>
            <td>Row 1 Data 2Row 1 Data 1Row 1 Data 1Row 1 Data 1Row 1 Data 1Row 1 Data 1Row 1 Data 1Row 1 Data 1Row 1 Data 1Row 1 Data 1</td>
        </tr>
        <tr>
            <td>Row 2 Data 1</td>
            <td>Row 2 Data 2</td>
        </tr>
    </tbody>
</table>

<div class="row">
<div class="col-md-12">
<form class="form-inline person-search-form search-form" id="user-search-form" method="post">
	<div class="form-group">
		<label>方案内容：</label> <input type="text"
			class="form-control input-sm" name="planContent">
	</div>
	<div class="input-group">
	  <div class="input-group-prepend">
	    <span class="input-group-text">用户名:</span>
	  </div>
	  <input type="text" class="form-control input-sm" name="userName" placeholder="userName">
	</div>
	<button type="submit" class="btn btn-primary btn-sm">查询</button>
	<button type="button" class="btn btn-warning btn-sm"
		onclick="clearSearchByplanlist()">清空</button>
	<button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#updateplanmodal"
			onclick="openinsertplan()">新增</button>
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
<script>
$(function() {
    $('#table_id_example').DataTable({
    });
    
    $('#user-datagrid').DataTable({
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
    $('#user-datagrid').removeClass( 'display' );
    for(var i=0;i<10;i++){
    	$('#id_select2_demo1').append(
			"<option value='" + "测试st"+ i + "'>" + "测试st"+ i +
			"</option>");
    }
	$('#id_select2_demo1').select2();
});
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