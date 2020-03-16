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
<myshiro:hasAnyPermissions name="/admin/demandOrder/modulecheck,user:show">
<a href="#" class="easyui-linkbutton"><i class="fi-check icon-red"></i>授权1</a>
</myshiro:hasAnyPermissions>
<shiro:hasPermission name="user:show">
<a href="#" class="easyui-linkbutton"><i class="fi-check icon-red"></i>授权2</a>
</shiro:hasPermission>
<shiro:hasPermission name="/admin/demandOrder/modulecheck">
<a href="#" class="easyui-linkbutton"><i class="fi-check icon-red"></i>授权3</a>
</shiro:hasPermission>
</div>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false"  style="overflow: hidden;">
        <table id="AllResourceTree"></table>
    </div>
</div>
<!-- <table id="AllResourceTree"></table> -->
<div id="insertDialog" class="easyui-dialog" style="width:850px;height:300px;"
    data-options="resizable:true,modal:true,closed:true,onClose: function() {document.getElementById('EditResourceForm').reset();}">
<form id="EditResourceForm" method="post" class="contact-form">
<ul>
<li>
<div class="show-double">
<label >资源名称：</label>
<input name="id" type="hidden">
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
</li>
</ul>
<button type="submit" class="submit" id="doSubmitButton">确定</button>
<button type="button" class='submit' onClick="javascript:$('#insertDialog').dialog('close');return false;">取消</button>
</form>
</div>
<script>
var arr;
var temparr=[];
$(function(){
$('#AllResourceTree').treegrid({
	url:'${ctx}/selectResourceList',
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
    onLoadSuccess: function(row, data){
//     	此处使用function(row, data)是因为和datagrid有区别，详情见http://jquery-easyui.wikidot.com/forum/t-366777
    	arr=data;
//     	$('#AllResourceTree').treegrid('collapseAll');
    }
});
$('#EditResourceForm').form({
    url : '${ctx}/saveresource',
    onSubmit : function() {
    	progressLoad();
    },
    success : function(result) {
    	progressClose();
    	result = $.parseJSON(result);
    	$('#EditResourceForm')[0].reset();
    	$('#insertDialog').dialog('close');
    	$('#AllResourceTree').treegrid('reload');
    	$('#AllResourceTree').treegrid('unselectAll');
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
        value : '',
        onLoadSuccess: function(data){
        	$("#resourceEditPid").combotree('tree').tree("collapseAll");//折叠所有节点
        }
    });
}

function readyupdateresource(){
	var rows = $('#AllResourceTree').treegrid('getSelections');
	if(rows.length!=1){
		$.messager.alert('提示', '请选择且仅选择一条记录进行编辑!', 'info');
		return false;
	}else{
		$('#insertDialog').dialog('open').dialog("center").dialog("setTitle", "编辑资源");
		$("#EditResourceForm input[name=id]").val(rows[0].id);
		$("#EditResourceForm input[name=name]").val(rows[0].name);
		$("#EditResourceForm input[name=url]").val(rows[0].url);
		$("#EditResourceForm input[name=icon]").val(rows[0].icon);
		$("#EditResourceForm select[name=status]").val(rows[0].status);
		$("#EditResourceForm input[name=remarks]").val(rows[0].remarks);
		$('#resourceEditPid').combotree({
	        url : '${ctx}/selectAllTree',
	        parentField : 'pid',
	        lines : true,
	        panelHeight : 'auto',
	        value : typeof rows[0].pid == "undefined" ? '' : rows[0].pid,
	        onLoadSuccess: function(data){
	        	$("#resourceEditPid").combotree('tree').tree("collapseAll");//折叠所有节点
	        }
	    });
	}
}

function readydelresource(){
	var rows = $('#AllResourceTree').treegrid('getSelections');
	if(rows.length!=1){
		$.messager.alert('提示', '由于涉及到子节点的删除，所以请选择且仅选择一条记录进行删除!', 'info');
		return false;
	}else{
		$.messager.confirm('警告', '删除当前资源会连同子资源一起删除!您是否要删除当前资源？', function(r){
			if (r){
				progressLoad();
				console.log(arr);
				console.log(rows[0].children);
				let data = [];//let和var有区别
				temparr=[];
				temparr.push(rows[0].id);
				data=getchildNode(rows[0]);
				$.ajax({
					type:"post",
					   url:"${ctx}/deleteresource",
					   data:{"ids":data},
					   traditional: true,//传数组进后台需设置
					   dataType:"json",
					   success:function(data){
						   if(data.status){
							   $('#AllResourceTree').treegrid('reload');
							   $('#AllResourceTree').treegrid('unselectAll');
						 	   progressClose();
						       $.messager.alert('提示', data.msg, 'info');
						   }
					   }
					});
			}
		});
	}
}

//判断是否有子节点
function childNode(id){
	for(var i = 0; i < arr.length; i++){
		if(arr[i]._parentId == id){
			temparr.push(arr[i].id);
			childNode(arr[i].id);
		}
	}
	return temparr;
}

function getchildNode(row){//递归查找所有子节点
	if(typeof row.children != "undefined"){
		for(var i = 0; i < row.children.length; i++){
			temparr.push(row.children[i].id);
			getchildNode(row.children[i]);
		}
	}
	return temparr;
}

</script>
</body>
</html>