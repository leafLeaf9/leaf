<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/template/commons/total.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/generalForm.css" />
<title>角色管理</title>
</head>
<body>
<a href="javascript:void(0)" onclick="readyinsertrole()" class="easyui-linkbutton"><i class="iconfont icon-Create_member"></i>新增</a>
<a href="javascript:void(0)" onclick="readyeditrole()" class="easyui-linkbutton"><i class="iconfont icon-bianji"></i>编辑</a>
<a href="javascript:void(0)" onclick="readydelrole()" class="easyui-linkbutton"><i class="iconfont icon-shanchu"></i>删除</a>
<a href="javascript:void(0)" onclick="readyauthorize()" class="easyui-linkbutton"><i class="iconfont fi-check"></i>授权</a>
<a href="javascript:void(0)" onclick="generatePDF()" class="easyui-linkbutton"><i class="iconfont fi-check"></i>PDF生成方法测试</a>
<!-- <div class="easyui-layout" data-options="fit:true,border:false"> -->
<!--     <div data-options="region:'center',border:false"  style="overflow: hidden;"> -->
        <table id="role-datagrid"></table>
<!--     </div> -->
<!-- </div> -->
<div id="roletoolbar" >
	<span>角色名:</span>
	<input name="keyword" style="line-height:26px;border:1px solid #ccc">
	<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="doSearch()">查询</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="ClearSearch()">清空</a>
</div>
<div id="insertDialog" class="easyui-dialog" style="width:850px;height:200px;"
    data-options="resizable:true,modal:true,closed:true,onClose: function() {document.getElementById('EditRoleForm').reset();}">
<form id="EditRoleForm" method="post" class="contact-form" onsubmit="return false;"><!-- onsubmit用于防止表单提交页面跳转 -->
<ul>
<li>
<div class="show-double">
<label >角色名称：</label>
<input name="id" type="hidden">
<input name="name" type="text" required="required">
</div>
<div class="show-double">
<label >序号：</label>
<input name="seq" type="number">
</div>
</li>

<li>
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

<div id="AuthorizeDialog" class="easyui-dialog" style="width:500px;height:500px;"
    data-options="resizable:true,modal:true,closed:true">
<div id="roleAuthorizeLayout" class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'west'" title="资源授权" style="width: 300px; padding: 1px;">
        <div class="well well-small" >
            <form id="roleAuthorizeForm" method="post">
                <input name="roleId" type="hidden" readonly="readonly">
                <ul id="resourceTree"></ul>
                <input id="resourceIds" name="resourceIds" type="hidden" />
            </form>
        </div>
    </div>
    <div data-options="region:'center'" title="" style="overflow: hidden; padding: 10px;">
        <div>
            <button onclick="checkAll();"><i class="iconfont fi-battery-full"></i>全选</button>
            <br /> <br />
            <button onclick="checkInverse();"><i class="iconfont fi-minus"></i>反选</button>
            <br /> <br />
            <button onclick="uncheckAll();"><i class="iconfont fi-battery-empty"></i>取消</button>
            <br /> <br /> <br /> <br />
            <button id="roleAuthorizeSubmit" style="cursor: pointer;"><i class="iconfont fi-download"></i>保存</button>
        </div>      
    </div>
</div>
</div>
<script>
$(function(){
	$('#role-datagrid').datagrid({
		url:'${ctx}/selectRoleList',
		title:'角色管理',
		fitColumns: true,
		pagination: true,
	    rownumbers: true,
	    toolbar: '#roletoolbar',
	    columns:[[
	    	{
	    		field: 'ck',
	    		checkbox: true,
	    	},
	    	{
	    		title : '编号',
				field : 'id',
				hidden: true,
			},
			{
				field : 'name',
	            title : '角色名称',
	            width: 50,
			},			
			{
				field : 'seq',
	            title : '序号',
	            width: 50,
			},
			{
				field : 'remarks',
	            title : '备注',
	            width: 50,
			},
			{
				field : 'createTime',
	            title : '创建时间',
	            width: 50,
			},
			{
				field : 'updateTime',
	            title : '更新时间',
	            width: 50,
			},
	    ]],
	});
	
	$('#resourceTree').tree({
		url : '${ctx}/selectAllTree',
        parentField : 'pid',
        lines : true,
        checkbox : true,
        cascadeCheck : false,
        onLoadSuccess: function(node,data){
        	$("#resourceTree").tree("collapseAll");//折叠所有节点
        	$(".tree-icon,.tree-file").removeClass("tree-icon tree-file");//删除tree默认的文件夹和文件图标
            $(".tree-icon,.tree-folder").removeClass("tree-icon tree-folder tree-folder-open tree-folder-closed");
        }
	});	
})
function doSearch(){
	$('#role-datagrid').datagrid('load', {
        name: $('#roletoolbar input[name=keyword]').val(),
      });
}
function ClearSearch(){
	  $('#roletoolbar input').val('');
	  $('#role-datagrid').datagrid('reload',{});
}
function readyinsertrole(){
	$('#insertDialog').dialog('open').dialog("center").dialog("setTitle", "新增角色");
}

function readyeditrole(){
	var rows = $('#role-datagrid').datagrid('getSelections');
	if(rows.length!=1){
		$.messager.alert('提示', '请仅选择一条记录进行编辑!', 'info');
		return false;
	}else{
		$('#insertDialog').dialog('open').dialog("center").dialog("setTitle", "编辑角色");
		$("#EditRoleForm input[name=id]").val(rows[0].id);
		$("#EditRoleForm input[name=name]").val(rows[0].name);
		$("#EditRoleForm input[name=seq]").val(rows[0].seq);
		$("#EditRoleForm input[name=remarks]").val(rows[0].remarks);
	}
}

$('#EditRoleForm').submit(function(e){
	progressLoad();
	$.ajax({
    	type : "post",  
    	url : '${ctx}/saverole',
   		dataType : 'json',  
    	contentType : 'application/json;charset=utf-8', // 设置请求头信息  
    	data:JSON.stringify($('#EditRoleForm').serializeObject()),
    	success : function(result) {
    		progressClose();
    		$('#EditRoleForm')[0].reset();
        	$('#insertDialog').dialog('close');
    		$('#role-datagrid').datagrid('reload');
    		$.messager.alert('提示', result.msg, 'info');
    	}  
	});
// 	return false;//此处和表单处的 onsubmit="return false;" 只写一个即可阻止表单提交后页面跳转
})

function readydelrole(){
	var rows = $('#role-datagrid').datagrid('getSelections');
	if(rows.length<1){
		$.messager.alert('提示', '请至少一条记录进行删除。', 'error');
	}else{
		$.messager.confirm('警告', '是否确认删除这些角色？', function(r){
			if (r){
				progressLoad();
				var data=[];
				for(var i=0; i<rows.length; i++){
					data.push(rows[i].id);
				}
				$.ajax({
					type:"post",
				    url:"${ctx}/deleterole",
				    data:{"ids":data},
				    traditional: true,//传数组进后台需设置
				    dataType:"json",
				    success:function(data){
				    	if(data.status){
				    		progressClose();
				    		$('#role-datagrid').datagrid('reload');
				    		$.messager.alert('提示', data.msg, 'info');
				    	}
				    }
				});
			}
		});
	}
}

function readyauthorize(){
	var rows = $('#role-datagrid').datagrid('getSelections');
	if(rows.length!=1){
		$.messager.alert('提示', '请仅选择一个角色进行资源授权!', 'warning');
		return false;
	}else{
		$('#AuthorizeDialog').dialog('open').dialog("center").dialog("setTitle", "授权");
		progressLoad();
		$("#roleAuthorizeForm input[name=roleId]").val(rows[0].id);
		uncheckAll();
		$.ajax({
			type:"post",
		    url:"${ctx}/selectResourceIdListByRoleId",
		    contentType : 'application/json;charset=utf-8',
		    data:JSON.stringify(rows[0]),
		    dataType:"json",
		    success:function(result){
		    	if(result.status){
		    		progressClose();
		    		var tempArr = result.resultData;
		    		for (var i = 0; i < tempArr.length; i++) {
		    			if ($('#resourceTree').tree('find', tempArr[i])) {
		    				$('#resourceTree').tree('check', $('#resourceTree').tree('find', tempArr[i]).target);
                        }
		    		}
		    	}
		    }
		});
	}
}

$("#roleAuthorizeSubmit").on("click", function(){
	progressLoad();
	var roleId=$("#roleAuthorizeForm input[name=roleId]").val();
	var tempArr = [];
	var checknodes = $('#resourceTree').tree('getChecked');
	if (checknodes && checknodes.length > 0) {
        for ( var i = 0; i < checknodes.length; i++) {
        	tempArr.push(checknodes[i].id);
        }
    }
	$.ajax({
		type:"post",
	    url:"${ctx}/saveRoleAuthorize",
	    data:{"roleId":roleId,"resourceIds":tempArr},
	    traditional: true,//传数组进后台需设置
	    dataType:"json",
	    success:function(data){
	    	if(data.status){
	    		progressClose();
	    		$('#AuthorizeDialog').dialog('close');
	    		$('#role-datagrid').datagrid('reload');
	    		$.messager.alert('提示', data.msg, 'info');
	    	}
	    }
	});
})

function checkAll(){
	var nodes = $('#resourceTree').tree('getChecked', 'unchecked');
	if (nodes && nodes.length > 0) {
		for ( var i = 0; i < nodes.length; i++) {
			$('#resourceTree').tree('check', nodes[i].target);
		}
	}
}

function uncheckAll(){
	var nodes = $('#resourceTree').tree('getChecked');
	if (nodes && nodes.length > 0) {
		for ( var i = 0; i < nodes.length; i++) {
			$('#resourceTree').tree('uncheck', nodes[i].target);
		}
	}
}

function checkInverse(){
	var unchecknodes = $('#resourceTree').tree('getChecked', 'unchecked');
	var checknodes = $('#resourceTree').tree('getChecked');
	if (unchecknodes && unchecknodes.length > 0) {
        for ( var i = 0; i < unchecknodes.length; i++) {
        	$('#resourceTree').tree('check', unchecknodes[i].target);
        }
    }
	if (checknodes && checknodes.length > 0) {
        for ( var i = 0; i < checknodes.length; i++) {
        	$('#resourceTree').tree('uncheck', checknodes[i].target);
        }
    }
}
function generatePDF(){
	var myform = $("<form></form>");
    myform.attr('method', 'post');
    myform.attr('target', 'hiddenIframe');
    myform.attr('action', "${ctx}/generatePDFTest");
    myform.appendTo('body').submit();
    myform.remove();
}
</script>
</body>
</html>