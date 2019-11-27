<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/template/commons/total.jsp"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>角色管理</title>
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
 <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
 <script src="http://cdn.bootcss.com/jquery/1.12.2/jquery.js"></script>
 <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
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
                </div>
<table 	id="user"		align="center">
			<tr class="th-blue-l">
				<th class="js-ckAll"><input type="checkbox" name=""></th>
				<th>
					角色id
				</th>
				
				<th>
					角色名称
				</th>
				<th>
					创建时间
				</th>
				<th>
					更新时间
				</th>
				<th>
					角色状态
				</th>
				
				<th>
					备注
				</th>
			</tr>
            <tbody id="projlist">
            	
            	</tbody>
			
			
		</table>
		<div id="insert" align="center">
		<table cellspacing="0" cellpadding="10" class="tfm" id="profilelist">
 
<tr >
<th >角色id:</th>
<td >
<input type="text" name="role_id" id="role_id" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >角色状态:</th>
<td >
<input type="text" name="state" id="state" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >角色名称:</th>
<td >
<input type="text" name="role_name" id="role_name" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >备注:</th>
<td >
<input type="text" name="remark" id="remark" class="px" value="" tabindex="1" /></td>
</tr>

</table>
<input type="button" value="提交" onclick="insertuser()">
		</div>
		<div id="update" align="center">
		<table cellspacing="0" cellpadding="10" class="tfm" id="profilelist">
 
<tr >
<th >角色id:</th>
<td >
<input type="text"  id="user_id1" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >角色状态:</th>
<td >
<input type="text"  id="password1"  /></td>
</tr>
<tr >
<th >角色名称:</th>
<td >
<input type="text"  id="user_name1" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >备注:</th>
<td >
<input type="text"  id="remark1" class="px" value="" tabindex="1" /></td>
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
		$.postJSON(webRootPath + "/testrolelist", qaram, function(data) {
			var trs = "";
			if (data) {
					$.each(data, function(index, value) {
											trs += "<tr>"
												+"<td class='js-ck'><input type='checkbox' name='messagePush' value='"+value.roleId+"' data-state= '"+value.state+"' data-name= '"+value.roleName+"' data-remark= '"+value.remark+"' ></td>"
												  
													+"<td >"+value.roleId+"</td>"
													
													+"<td >"+value.roleName+"</td>"
													+"<td >"+value.created+"</td>"
													+"<td >"+value.updated+"</td>"
													+"<td >"+value.state+"</td>"													
													+"<td >"+value.remark+"</td>"
												+"</tr>";
										});
				
				$("#projlist").html(trs);
				
			}
		});
		
	}
  queryprojlist();
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
			$("#password1").val($(checkMshPsh).data("state"));
			$("#user_name1").val($(checkMshPsh).data("name"));
			$("#remark1").val($(checkMshPsh).data("remark"));
			
			
			
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
				"role_id" :  $("#role_id").val(),
				"state" :  $("#state").val(),
				"role_name" :  $("#role_name").val(),
				"remark" :  $("#remark").val()
			
			};
		console.log( JSON.stringify(qaram));
		
		$.postJSON(webRootPath + "/insertrole", qaram, function(data) {
              if (data) {
				
				alert(data.result);
				
				
			}else{
				alert("操作失败！");
			}
		});
  }
  function updateuser(){
	  var webRootPath = '<%=request.getContextPath()%>';	
		var qaram = {
				"role_id" :  $("#user_id1").val(),
				"state" :  $("#password1").val(),
				"role_name" :  $("#user_name1").val(),
				"remark" :  $("#remark1").val()
			
			};
		console.log( JSON.stringify(qaram));
		
		$.postJSON(webRootPath + "/updaterole", qaram, function(data) {
              if (data) {
				
				alert(data.result);
				
				
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
				
			"roleids": ruleIds
			
			};
		console.log( JSON.stringify(qaram));
		
		$.postJSON(webRootPath + "/delroles", qaram, function(data) {
              if (data) {
				
				alert(data.result);
				
				
			}else{
				alert("操作失败！");
			}
		});
  }
  
  </script>
</body>
</html>