<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/template/commons/total.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%-- <base href="<%=basePath%>"> --%>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="jquery,ui,easy,easyui,web">
	<meta name="description" content="easyui help you build your web page easily!">
<title>用户管理</title>
<style>
a{
 cursor:pointer;
 color:blue;
}
#label{
display:none;
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
<table id="user" title="词表" toolbar="#UserToolbar">
    <thead>
        <tr>
           <th field="cb" checkbox="true"  align="center"></th>
            <th field="tid" width="20" align="center" hidden="true"></th>    
            <th field="user_id" width="100" align="center">用户名</th> 
            <th field="password" width="200" align="center">密码</th> 
            <th field="user_name" width="100" align="center">用户昵称</th> 
            <th field="created" width="100" align="center">创建时间</th> 
            <th field="updated" width="100" align="center">更新时间</th> 
            <th field="state" width="100" align="center">状态</th> 
            <th field="role_id" width="100" align="center">角色id</th>
            <th field="remark" width="100" align="center">备注</th>  
            
        </tr>
    </thead>
</table>
<div id="UserToolbar" style="padding:3px">
		<span>用户名:</span>
		<input name="userId" style="line-height:26px;border:1px solid #ccc">
		<span>姓名:</span>
		<input name="userName" style="line-height:26px;border:1px solid #ccc">
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="doSearch()">查询</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" onclick="ClearSearch()">清空</a>		
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
<div id="insertDialog" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
    data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <form id="insertform" method="post">
    <table cellspacing="0" cellpadding="10" class="tfm" id="inserttable">
<tr >
<th >词语名称:</th>
<td >
<input type="text" name="wordname" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >情感值:</th>
<td >
<input type="text" name="value" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >词语类型:</th>
<td >
<input type="text" name="wordtype" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >公式类型:</th>
<td >
<input type="text" name="formtype" class="px" value="" tabindex="1" /></td>
</tr>
</table>
</form>
<div class="clearfix" style="padding: 0 200px; margin-bottom: 40px">
<a href="javascript:void(0);" class="easyui-linkbutton" style="width: 100px" onclick="forminsertuser()">确定</a>
<a href="javascript:void(0);" class="easyui-linkbutton" style="float: right; width: 100px" onclick="javascript:$('#insertDialog').dialog('close')">取消</a>
</div>
</div>

<script>
$(function() {
	$('#user').datagrid({
        url: '${pageContext.request.contextPath}/queryuserlist',
        pagination: true,
        rownumbers: true,
        fitColumns: true,
        pageNumber: 1,
        pageSize: 10,
        pageList: [10,20],
      });
	$('#insertDialog').dialog('close');
	$("#insert").hide();
	$("#update").hide();
});
$('#insertDialog').dialog({
    title: '新增词语',
    width: 700,
    height: 350,
    closed: false,
    cache: false,
   // href: 'get_content.php',
    modal: true,
    onClose: function() {
       $('#insertform')[0].reset();  
       //$(this).dialog("destroy").remove();
      },
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
	      })
  }
  function readyinsertuser(){	  
	  $('#insertDialog').dialog('open');
  }
  function readyupdateuser(){
	  var ids = [];
	  var rows = $('#user').datagrid('getSelections');
	  if(rows.length!=1){
			alert("提示！,请选择一条且仅选择一条记录");
		}else{
			$("#update").show();
			$("#wordname1").val(rows[0].wordname);
			$("#value1").val(rows[0].value);
			$("#wordtype1").val(rows[0].wordtype);
			$("#formtype1").val(rows[0].formtype);	
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
  function insertuser(){
	  var webRootPath = '<%=request.getContextPath()%>';	
		var qaram = {
				"wordname" :  $("#wordname").val(),
				"value" :  $("#value").val(),
				"wordtype" :  $("#wordtype").val(),
				"formtype" :  $("#formtype").val()
			
			};
		console.log( JSON.stringify(qaram));
		
		$.postJSON(webRootPath + "/insertwords", qaram, function(data) {
              if (data) {
				
				alert(data.result);
				$("#user").datagrid("reload");
                $("#user").datagrid("clearSelections");
                $("#insert").hide();                
			}else{
				alert("操作失败！");
			}
		});
  }
  function forminsertuser(){
	  var webRootPath = '<%=request.getContextPath()%>';	
	  var qaram=$('#insertform').serializeObject();
	  $.postJSON(webRootPath + "/insertwords", qaram, function(data) {
          if (data) {			
			alert(data.result);
			$("#user").datagrid("reload");
            $("#user").datagrid("clearSelections");          
            $('#insertDialog').dialog('close');
		}else{
			alert("操作失败！");
		}
	});
  }
 
  function updateuser(){
	  var webRootPath = '<%=request.getContextPath()%>';	
		var qaram = {
				"wordname" : $("#wordname1").val(),
				"value" :  $("#value1").val(),
				"wordtype" :  $("#wordtype1").val(),
				"formtype" :  $("#formtype1").val(),							
			};
		console.log( JSON.stringify(qaram));
		
		$.postJSON(webRootPath + "/updatewords", qaram, function(data) {
              if (data) {
				
				alert(data.result);				
				$("#user").datagrid("reload");
                $("#user").datagrid("clearSelections");
                $("#update").hide();
			}else{
				alert("操作失败！");
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
				
			"wordnames": ids
			
			};
		console.log( JSON.stringify(qaram));
		
		$.postJSON(webRootPath + "/delwords", qaram, function(data) {
              if (data) {
				
				alert(data.result);
				$("#user").datagrid("reload");
                $("#user").datagrid("clearSelections");
				
			}else{
				alert("操作失败！");
			}
		});
  }
  
function exportwords(){
	var webRootPath = '<%=request.getContextPath()%>';
// 	var qaram = {
			
// 			};
	 var myform = $("<form></form>");
	   myform.attr('method', 'post');
	   myform.attr('action', "/exopertwords"); 
// 	   myform.append(qaram);  
	   myform.appendTo('body').submit();
	/* $.postJSON(webRootPath + "/exopertwords", qaram, function(data) {
        if (data) {
        	 url="./xls/词表导出.xlsx";
        	window.open(url); 
			
		}else{
			alert("操作失败！");
		}
	}); */
}
  
  </script>
<script  src = "canvas-nest.js-2.0.1/dist/newnest.js"> </script>
</body>
</html>