<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/template/commons/total.jsp"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="jquery,ui,easy,easyui,web">
	<meta name="description" content="easyui help you build your web page easily!">
<title>词表管理页面</title>
<style>
a{
 cursor:pointer;
 color:blue;
}
table
  {
  border-collapse:collapse;

  }
  table, td, th
  {
  border:1px solid black;
  }
.bg-nav{
	overflow: hidden;
	margin: 25px 0 10px 15px;
	>span{
		float: left;
		margin-right: 15px;
		color: #6DB4F9;
		cursor: pointer;
		img{
			float: left;
			margin: 2px 8px  0 0;
		}
		&+span{
			border-left: 1px solid #DFDFDF;
			padding-left: 15px;
		}
	}
}
.bg-nav>span{float:left;margin-right:15px;color:#6DB4F9;cursor:pointer}
    .row {
      font-size: 18px;
      color: #333;
      margin-top: 20px;
      margin-bottom: 20px;
    }
    .row_description {
      vertical-align: top;
      margin-left: 14%;
      display: inline-block;
      width: 22%;
    }
    .row_data {
      display: inline-block;
      width: 55%;
    }
    .danger {
      color: #F56C6C;
    }
</style>
 <script src="http://cdn.bootcss.com/jquery/1.12.2/jquery.js"></script>
 <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="./js/jquery-easyui-1.7.0/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="./js/jquery-easyui-1.7.0/themes/icon.css">
	<script type="text/javascript" src="./js/jquery-easyui-1.7.0/jquery.min.js"></script>
	<script type="text/javascript" src="./js/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="./js/jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
<!-- 	<script type="text/javascript" src="./js/jquery.color.js"></script> -->
	<script type="text/javascript" src="./js/jsUtil.js"></script>
<link rel="stylesheet" href="./img/icon-font/iconfont.css">
</head>
<body>

<div class="con">
            	<!-- 操作 -->
                <div class="bg-nav">
                    <span onclick="readyinsertuser()">
                        <i class="iconfont icon-Create_member"> 新增</i>

                    </span>
                    <span  onclick="readyupdateuser()">
                   <i class="iconfont icon-bianji"> 编辑</i>
                    </span>
                    <span onclick="readydeluser()">
                    <i class="iconfont icon-shanchu"> 删除</i>
                    </span>
                                                          
                </div>
</div>
<!-- <table id="user" title="词表" class="easyui-datagrid" fitColumns="true" pagination="true" -->
<%--     url="${pageContext.request.contextPath}/querywordslist" toolbar="#tb" rownumbers="true"> --%>
    <table id="user" title="词表" toolbar="#tb">
    <thead>
        <tr>
            <th field="cb" checkbox="true"  align="center"></th>
            <th field="tid" width="20" align="center" hidden="true"></th>    
            <th field="wordname" width="100" align="center">词名</th> 
            <th field="value" width="100" align="center">情感值</th> 
            <th field="wordtype" width="100" align="center">词性</th> 
            <th field="formtype" width="100" align="center">公式类型</th> 
            
        </tr>
    </thead>
</table>  
<div id="tb" style="padding:3px">
		<span>词名:</span>
		<input id="keywordname" style="line-height:26px;border:1px solid #ccc">		
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="doSearch()">查询</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="ClearSearch()">清空</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="exportwords()">导出</a>
</div>            		
		<div id="update"  align="center">
		<table cellspacing="0" cellpadding="10" class="tfm" id="profilelist" align="center">
 
<tr >
<th >词语名称:</th>
<td >
<input type="text" readonly="readonly" id="wordname1" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >情感值:</th>
<td >
<input type="text" id="value1" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >词语类型:</th>
<td >
<input type="text" id="wordtype1" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >公式类型:</th>
<td >
<input type="text" id="formtype1" class="px" value="" tabindex="1" /></td>
</tr>
</table>
<input type="button" value="提交" onclick="updateuser()">
		</div>
<!-- <input class="easyui-color">		 -->
<div id="insertDialog" class="easyui-dialog" style="width:400px;height:200px;"
    data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
<form id="insertform" method="post">
  <div class="row">
  <div class="row_description"><span class="danger">*</span> 词语名称:</div>
  <div class="row_data">
  <input name="wordname" type="text">
  </div>
  </div>
  
  <div class="row">
  <div class="row_description"><span class="danger">*</span> 情感值:</div>
  <div class="row_data">
  <input name="value" type="text">
  </div>
  </div>

  <div class="row">
  <div class="row_description"><span class="danger">*</span> 词语类型:</div>
  <div class="row_data">
  <input name="wordtype" type="text">
  </div>
  </div>

  <div class="row">
  <div class="row_description"><span class="danger">*</span> 公式类型:</div>
  <div class="row_data">
  <input name="formtype" type="text">
  </div>
  </div>
</form>
<div class="clearfix" style="padding: 0 200px; margin-bottom: 40px">
<a href="javascript:void(0);" class="easyui-linkbutton" style="width: 100px" onclick="forminsertuser()">确定</a>
<a href="javascript:void(0);" class="easyui-linkbutton" style="float: right; width: 100px" onclick="javascript:$('#insertDialog').dialog('close')">取消</a>
</div>
</div>
<div id="updateDialog" class="easyui-dialog" style="width:700px;height:300px;"
    data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,onClose:function(){$('updatewordsform')[0].reset()}">
<form id="updatewordsform" method="post">
  <div class="row">
  <div class="row_description"><span class="danger">*</span> 词语名称:</div>
  <div class="row_data">
  <input name="wordname" type="text">
  </div>
  </div>
  
  <div class="row">
  <div class="row_description"><span class="danger">*</span> 情感值:</div>
  <div class="row_data">
  <input name="value" type="text">
  </div>
  </div>

  <div class="row">
  <div class="row_description"><span class="danger">*</span> 词语类型:</div>
  <div class="row_data">
  <input name="wordtype" type="text">
  </div>
  </div>

  <div class="row">
  <div class="row_description"><span class="danger">*</span> 公式类型:</div>
  <div class="row_data">
  <input name="formtype" type="text">
  </div>
  </div>
</form>
<div class="clearfix" style="padding: 0 200px; margin-bottom: 40px">
<a href="javascript:void(0);" class="easyui-linkbutton" style="width: 100px" onclick="formupdatewords()">确定</a>
<a href="javascript:void(0);" class="easyui-linkbutton" style="float: right; width: 100px" onclick="javascript:$('#updateDialog').dialog('close')">取消</a>
</div>
</div>
<script>
$(function() {
$('#user').datagrid({
       url: '${pageContext.request.contextPath}/selectwordslist',
       pagination: true,
       rownumbers: true,
       fitColumns: true,
       pageNumber: 1,
       pageSize: 10,
       pageList: [10,20],
     });
$('#insertDialog').dialog({
    title: '新增词语',
    width: 700,
    height: 350,
    closed: true,
    cache: false,
    modal: true,
    onClose: function() {
       $('#insertform')[0].reset();  
      },
});
$('#insertDialog').dialog('close');
$('#updateDialog').dialog('close');
$("#update").hide();
});

  $.postJSON = function(url, data, callback) {
	      return jQuery.ajax( {
	          'type' : 'POST',
	          'url' : url,
	          'contentType' : 'application/json;charset=UTF-8',
	          'data' : JSON.stringify(data),
	          'dataType' : 'json',
	          'success' : callback
	      });
	  };

  function doSearch(){
	  $('#user').datagrid('load', {	        
	        wordname: $('#keywordname').val(),
	      });
  }
  
  function ClearSearch(){
	  $('#tb input').val('');
	  $('#user').datagrid('reload',{});
  }
  
  function readyinsertuser(){	  
	  $('#insertDialog').dialog('open').dialog("center");
  }
  function readyupdateuser(){
	  var ids = [];
	  var rows = $('#user').datagrid('getSelections');
	  if(rows.length!=1){
			alert("提示！,请选择一条且仅选择一条记录");
		}else{
			$('#updateDialog').dialog('open').dialog("center").dialog("setTitle", "编辑词语");
			$('#updatewordsform input[name=wordname]').val(rows[0].wordname);
			$('#updatewordsform input[name=value]').val(rows[0].value);
			$('#updatewordsform input[name=wordtype]').val(rows[0].wordtype);
			$('#updatewordsform input[name=formtype]').val(rows[0].formtype);	
		}
  }
	function readydeluser() { 
		 var rows = $('#user').datagrid('getSelections');
		  if(rows.length<1){
				alert("提示！请至少一条记录进行删除。");
			}else{
				if(confirm("是否删除选定内容？")) {
					deluser();
				}else{
					
				}
			}
		


		} 
  function forminsertuser(){
	  var webRootPath = '<%=request.getContextPath()%>';	
	  var qaram=$('#insertform').serializeObject();
	  $.postJSON(webRootPath + "/insertwords", qaram, function(data) {
          if (data) {			
        	$.messager.alert('提示','操作成功');
			$("#user").datagrid("reload");
            $("#user").datagrid("clearSelections");          
            $('#insertDialog').dialog('close');
		}else{
			$.messager.alert('提示','操作失败');
		}
	});
  }
 
  function formupdatewords(){
	  var param=$('#updatewordsform').serializeObject();
		$.postJSON(webRootPath + "/updatewords", param, function(data) {
              if (data) {				
            	$.messager.alert('提示','修改成功');
				$("#user").datagrid("reload");
                $("#user").datagrid("clearSelections");
                $('#updateDialog').dialog('close');
			}else{
				 $.messager.alert('操作失败');
			}
		});
  }
  
  function deluser(){
	  var webRootPath = '<%=request.getContextPath()%>';	
		  var ids = [];
		  var rows = $('#user').datagrid('getSelections');
		  for(var i=0; i<rows.length; i++){
				ids.push(rows[i].wordname);
			}
		var qaram = {
			"wordnames": ids,
			};
		$.postJSON(webRootPath + "/delwords", qaram, function(data) {
              if (data) {
            	$.messager.alert('提示',data.result);
				$("#user").datagrid("reload");
                $("#user").datagrid("clearSelections");				
			}else{
				$.messager.alert('提示','操作失败');
			}
		});
  }

function exportwords(){
	var myform = $("<form></form>");
	   myform.attr('method', 'post');
	   myform.attr('action', "/exopertwords");
	   myform.appendTo('body').submit();
}
  
</script>
</body>
</html>