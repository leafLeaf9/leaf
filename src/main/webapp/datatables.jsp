<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/template/commons/total.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>datatables测试页面</title>
</head>
<body>
<button type="button" class="btn btn-success btn-sm" >新增</button>
<select class="form-control" id="id_select2_demo1"></select>
<table id="table_id_example" class="table table-bordered table-hover nowrap">
    <thead>
        <tr>
            <th>Column 1</th>
            <th>Column 2</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Row 1 Data 1</td>
            <td>Row 1 Data 2</td>
        </tr>
        <tr>
            <td>Row 2 Data 1</td>
            <td>Row 2 Data 2</td>
        </tr>
    </tbody>
</table>
<script>
$(function() {
    $('#table_id_example').DataTable({
    });
	$('#id_select2_demo1').select2();
});
</script>
</body>
</html>