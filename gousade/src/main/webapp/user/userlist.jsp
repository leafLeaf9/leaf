<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>  
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
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
<link rel="stylesheet" type="text/css" href="../js/jquery-easyui-1.7.0/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../js/jquery-easyui-1.7.0/themes/icon.css">
	<script type="text/javascript" src="../js/jquery-easyui-1.7.0/jquery.min.js"></script>
	<script type="text/javascript" src="../js/jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="../js/jquery.color.js"></script>
	<script type="text/javascript" src="../js/jsUtil.js"></script>
</head>
<body>
<div class="con">
            	<!-- 操作 -->
                <div class="bg-nav">
                    <span onclick="readyinsertuser()">
                        <img src="<%=request.getContextPath()%>/img/leading-in.png"/>
                       	 新增
                    </span>
                    <span  onclick="readyupdateuser()">
                        <img src="<%=request.getContextPath()%>/img/insert.png" />
                       	 编辑
                    </span>
                    <span onclick="readydeluser()">
                        <img src="<%=request.getContextPath()%>/img/delete.png" />
                       	 删除
                    </span>
<!--                     <span onclick="readydeluser()"> -->
<%--                         <img src="<%=request.getContextPath()%>/img/fenpei.jpg" /> --%>
<!--                        	 分配角色 -->
<!--                     </span> -->
                </div>
</div>
<table 	id="user"		align="center">
			<tr  class="th-blue-l">
				<th  class="js-ckAll"><input type="checkbox" name=""></th>
				<th>
					用户id
				</th>
				<th>
					密码
				</th>
				<th>
					用户名称
				</th>
				<th>
					创建时间
				</th>
				<th>
					更新时间
				</th>
				<th>
					用户状态
				</th>
				<th>
					用户角色
				</th>
				<th>
					备注
				</th>
			</tr>
            <tbody id="projlist">
            	
            	</tbody>
			
			
		</table>
<table id="easyuiuser" title="用户列表" class="easyui-datagrid" fitColumns="true" pagination="true"
    url="../queryuserlist" toolbar="#tb" rownumbers="true">
    <thead>
        <tr>
            <th field="cb" checkbox="true"  align="center"></th>
            <th field="tid" width="20" align="center" hidden="true"></th>    
            <th field="user_id" width="100" align="center">用户名</th> 
            <th field="password" width="100" align="center">密码</th> 
            <th field="user_name" width="100" align="center">用户昵称</th> 
            <th field="created" width="100" align="center">创建时间</th> 
            <th field="updated" width="100" align="center">更新时间</th> 
            <th field="state" width="100" align="center">状态</th> 
            <th field="role_id" width="100" align="center">角色id</th>
            <th field="remark" width="100" align="center">备注</th>  
        </tr>
    </thead>
</table> 
		<div id="insert" align="center">
		<table cellspacing="0" cellpadding="10" class="tfm" id="profilelist">
 
<tr >
<th >用户id:</th>
<td >
<input type="text" name="user_id" id="user_id" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >密码:</th>
<td >
<input type="text" name="password" id="password" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >用户名称:</th>
<td >
<input type="text" name="user_name" id="user_name" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >备注:</th>
<td >
<input type="text" name="remark" id="remark" class="px" value="" tabindex="1" /></td>
</tr>

</tr>
<tr >
<th >角色id:</th>
<td >
<input type="text"  id="role_id" class="px" value="" tabindex="1" /></td>
</tr>
</table>
<input type="button" value="提交" onclick="insertuser()">
		</div>
		<div id="update" align="center">
		<table cellspacing="0" cellpadding="10" class="tfm" id="profilelist">
 
<tr >
<th >用户id:</th>
<td >
<input type="text" readonly="readonly" id="user_id1" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >密码:</th>
<td >
<input type="text"  id="password1"  /></td>
</tr>
<tr >
<th >用户名称:</th>
<td >
<input type="text"  id="user_name1" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >备注:</th>
<td >
<input type="text"  id="remark1" class="px" value="" tabindex="1" /></td>
</tr>

</tr>
<tr >
<th >角色id:</th>
<td >
<input type="text"  id="role_id1" class="px" value="" tabindex="1" /></td>
</tr>

</table>
<input type="button" value="提交" onclick="updateuser()">
		</div>
		
		 

		<script>
  
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
	  $("#user").on("click",".th-blue-l .js-ckAll input[type=checkbox]",function(){
			if($(this).prop("checked")==true){
				$("#user .js-ck input[type=checkbox]").prop("checked",true);
			}else{
				$("#user .js-ck input[type=checkbox]").prop("checked",false);
			}
		})

  function queryprojlist(){
	  var webRootPath = '<%=request.getContextPath()%>';	  
		var qaram = {
			// "pageNum" : (pageNum-1)*pageSize,
			// "pageSize" : pageSize,
			// "roleId" : roleIdShowGlob,
			
			
		};
		$.postJSON(webRootPath + "/queryuserlist", qaram, function(data) {
			var trs = "";
			if (data) {
					$.each(data, function(index, value) {
											trs += "<tr>"
												+"<td class='js-ck'><input type='checkbox' name='messagePush' value='"+value.user_id+"' data-pw= '"+value.password+"' data-name= '"+value.user_name+"' data-remark= '"+value.remark+"' data-roleid= '"+value.role_id+"' ></td>"
												  
													+"<td >"+value.user_id+"</td>"
													+"<td >"+value.password+"</td>"
													+"<td >"+value.user_name+"</td>"
													+"<td >"+value.created+"</td>"
													+"<td >"+value.updated+"</td>"
													+"<td >"+value.state+"</td>"
													+"<td >"+value.role_id+"</td>"
													+"<td >"+value.remark+"</td>"
												+"</tr>";
										});
				
				$("#projlist").html(trs);
				
			}
		});
		
	}
//  queryprojlist();
alert("进入页面");
  $("#insert").hide();
  $("#update").hide();
  function readyinsertuser(){
	  $("#update").hide();
	  $("#insert").show();
  }
  function readyupdateuser(){
	  var checkMshPsh=$("input[name='messagePush']:checked");
		console.log($("input[name='messagePush']:checked")[0])
		if($(checkMshPsh).length!=1){
			alert("提示！,请选择且仅选择一条记录");
		}else{
			
			$("#insert").hide();
			$("#update").show();
			$("#user_id1").val($(checkMshPsh).val());
			$("#password1").val($(checkMshPsh).data("pw"));
			$("#user_name1").val($(checkMshPsh).data("name"));
			$("#remark1").val($(checkMshPsh).data("remark"));
			$("#role_id1").val($(checkMshPsh).data("roleid"));
			
			
			
		}
	  
  }
	function readydeluser() { 
		
		if(confirm("是否删除选定内容？")) {
			deluser();
		}else{
			
		}


		} 
  function insertuser(){
	  var webRootPath = '<%=request.getContextPath()%>';	
		var qaram = {
				"user_id" :  $("#user_id").val(),
				"password" :  $("#password").val(),
				"user_name" :  $("#user_name").val(),
				"remark" :  $("#remark").val(),
				"role_id":$("#role_id").val()
			
			};
		console.log( JSON.stringify(qaram));
		
		$.postJSON(webRootPath + "/insertuser", qaram, function(data) {
              if (data) {
				
				alert(data.result);
				  queryprojlist();
				
			}else{
				alert("操作失败！");
			}
		});
  }
  function updateuser(){
	  var webRootPath = '<%=request.getContextPath()%>';	
		var qaram = {
				"user_id" :  $("#user_id1").val(),
				"password" :  $("#password1").val(),
				"user_name" :  $("#user_name1").val(),
				"remark" :  $("#remark1").val(),
				"role_id":$("#role_id1").val()
			
			};
		console.log( JSON.stringify(qaram));
		
		$.postJSON(webRootPath + "/updateuser", qaram, function(data) {
              if (data) {
				
				alert(data.result);
				  queryprojlist();
				
			}else{
				alert("操作失败！");
			}
		});
  }
  
  function deluser(){
	  var webRootPath = '<%=request.getContextPath()%>';	
	  var ruleIds=new Array();
	 // var ruleIds = {};
	var i=0;
	  var proj_id=$("#input").val();
		$("#user .js-ck input[name='messagePush']:checked").each(function(){
			/*ruleIds[i] = {};
			ruleIds[i]['userid'] =$(this).val();
			i++;*/
			ruleIds.push($(this).val());
		});
		console.log(ruleIds);
		var qaram = {
				
			"userids": ruleIds
			
			};
		console.log( JSON.stringify(qaram));
		
		$.postJSON(webRootPath + "/delusers", qaram, function(data) {
              if (data) {
				
				alert(data.result);
				  queryprojlist();
				
			}else{
				alert("操作失败！");
			}
		});
  }
  
  </script>
</body>
</html>