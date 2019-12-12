<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/template/commons/total.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源管理</title>
</head>
<body>
<table id="AllResourceTree"></table>
<script>
$(function(){
$('#AllResourceTree').treegrid({
	url:'${pageContext.request.contextPath}/selectResourceList',
    idField:'id',
    treeField:'name',
    parentField : 'pid',
    fitColumns: true,
    rownumbers: true,
    columns:[[
		{
			field : 'id',
			title : '编号',           
		},
		{
			field : 'name',
            title : '资源名称',
		},
		{
			field : 'url',
            title : '资源路径',
		},
		{
			field : 'state',
            title : '菜单状态',
            formatter : function(value, row, index) {
                if (value == 'open') {
                	console.log(value);
                    return '打开';
                } else {
                	console.log(value);
                    return '关闭';
                }
            },
		},
		{
			field : 'pid',
            title : '上级资源ID',
		},
		{
			field : 'remarks',
            title : '备注',
		},
		{
			field : 'createTime',
            title : '创建时间',
		},
    ]],
});
})
</script>
</body>
</html>