<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/template/commons/total.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/generalForm.css" />
<title>资源管理</title>
</head>
<body>
<div class="con">
<span style="cursor: pointer;" onclick="readyinsertresource()"><i class="iconfont icon-Create_member">新增</i></span>
<span style="cursor: pointer;" onclick="readyupdateresource()"><i class="iconfont icon-bianji">编辑</i></span>
<span style="cursor: pointer;" onclick="readydelresource()"><i class="iconfont icon-shanchu">删除</i></span>
</div>
<table id="AllResourceTree"></table>
<div id="insertDialog" class="easyui-dialog" style="width:850px;height:300px;"
    data-options="resizable:true,modal:true,closed:true,onClose: function() {document.getElementById('EditResourceForm').reset();}">
<form id="EditResourceForm" method="post" class="contact-form">
<ul>
<li>
<div class="show-double">
<label >资源名称：</label>
<input name="name" type="text" required="required">
</div>
<div class="show-double">
<label >资源路径：</label>
<input name="url" type="text">
</div>
</li>

<li>
<div class="show-double">
<label >资源图标：</label>
<input name="icon" type="text">
</div>
<div class="show-double">
<label >资源状态：</label>
<select name="status" type="text" required="required">
<option value=""></option>
<option value="1">打开</option>
<option value="0">关闭</option>
</select>
</div>
</li>

<li>
<div class="show-double">
<label >上级资源：</label>
<select id="resourceEditPid" name="pid"></select>
<a class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#resourceEditPid').combotree('clear');" >清空</a>
</div>
<div class="show-double">
<label >备注：</label>
<input name="remarks" type="text">
</div>
<li>
</ul>
<button type="submit" class="submit" id="doSubmitButton">确定</button>
<button type="button" class='submit' onClick="javascript:$('#insertDialog').dialog('close');return false;">取消</button>
</form>
</div>
<script>
$(function(){
$('#AllResourceTree').treegrid({
	url:'${pageContext.request.contextPath}/selectResourceList',
	title:'资源管理',
    idField:'id',
    treeField:'name',
    parentField : 'pid',
    fitColumns: true,
    rownumbers: true,
    singleSelect:false,
    checkOnSelect:true,
    columns:[[
    	{
    		field: 'ck',
    		checkbox: true,
    	},
    	{
			title : '编号',
			field : 'id',
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
			field : 'status',
            title : '菜单状态',
            formatter : function(value, row, index) {
                if (value == '1') {
                    return '打开';
                } else {
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
		{
			field : 'updateTime',
            title : '更新时间',
		},
    ]],
    onLoadSuccess: function(data){
//     	$('#AllResourceTree').treegrid('collapseAll');
    }
});
$('#EditResourceForm').form({
    url : '${ctx}/insertresource',
    onSubmit : function() {
    },
    success : function(result) {
    	result = $.parseJSON(result);
    	$('#EditResourceForm')[0].reset();
    	$('#insertDialog').dialog('close');
    	$('#AllResourceTree').treegrid('reload');
    	$.messager.alert('提示', result.msg, 'info');
    }
});
})
function readyinsertresource(){
	$('#insertDialog').dialog('open').dialog("center").dialog("setTitle", "新增资源");
	$('#resourceEditPid').combotree({
        url : '${ctx}/selectAllTree',
        parentField : 'pid',
        lines : true,
        panelHeight : 'auto',
        onLoadSuccess: function(data){
        	$("#resourceEditPid").combotree('tree').tree("collapseAll");//折叠所有节点
        }
    });
}

</script>
</body>
</html>