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
<title>菜单管理</title>
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
<!--                     <span onclick="readyinsertuser()"> -->
<%--                         <img src="<%=request.getContextPath()%>/img/leading-in.png"/> --%>
<!--                        	 新增 -->
<!--                     </span> -->
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
					菜单id
				</th>
				
				<th>
					菜单名称
				</th>
				<th>
					创建时间
				</th>
				<th>
					更新时间
				</th>
				<th>
					菜单状态
				</th>
				
				<th>
					跳转地址
				</th>
				<th>
					菜单类型
				</th>
				<th>
					父菜单id
				</th>
			</tr>
            <tbody id="projlist">
            	
            	</tbody>
			
			
		</table>
		
		<div id="update"  align="center">
		<table cellspacing="0" cellpadding="10" class="tfm" id="profilelist" align="center">
 
<tr >
<th >菜单id:</th>
<td >
<input type="text"  id="id" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >菜单状态:</th>
<td >
<input type="text"  id="state"  /></td>
</tr>
<tr >
<th >菜单名称:</th>
<td >
<input type="text"  id="name" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >跳转地址:</th>
<td >
<input type="text"  id="url" class="px" value="" tabindex="1" /></td>
</tr>

<tr >
<th >菜单类型:</th>
<td >
<input type="text"  id="type" class="px" value="" tabindex="1" /></td>
</tr>

<tr >
<th >父菜单id:</th>
<td >
<input type="text"  id="pid" class="px" value="" tabindex="1" /></td>
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
		$.postJSON(webRootPath + "/querymenulist", qaram, function(data) {
			var trs = "";
			if (data) {
					$.each(data, function(index, value) {
											trs += "<tr>"
												+"<td class='js-ck'><input type='checkbox' name='messagePush' value='"+value.id+"' data-state= '"+value.state+"' data-name= '"+value.name+"' data-url= '"+value.url+"' data-type= '"+value.type+"' data-pid= '"+value.pid+"' ></td>"
												  
													+"<td >"+value.id+"</td>"
													
													+"<td >"+value.name+"</td>"
													+"<td >"+value.created+"</td>"
													+"<td >"+value.updated+"</td>"
													+"<td >"+value.state+"</td>"
													
													+"<td >"+value.url+"</td>"
													+"<td >"+value.type+"</td>"
													+"<td >"+value.pid+"</td>"
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
	  $("#insert").show();
  }
  function readyupdateuser(){
	  var checkMshPsh=$("input[name='messagePush']:checked");
		console.log($("input[name='messagePush']:checked")[0])
		if($(checkMshPsh).length!=1){
			alert("提示！,请选择一条且仅选择一条记录");
		}else{
			$("#update").show();
			$("#id").val($(checkMshPsh).val());
			$("#state").val($(checkMshPsh).data("state"));
			$("#name").val($(checkMshPsh).data("name"));
			$("#url").val($(checkMshPsh).data("url"));
			$("#type").val($(checkMshPsh).data("type"));
			$("#pid").val($(checkMshPsh).data("pid"));
			
			
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
				"id" : $("#id").val(),
				"name" :  $("#name").val(),
				"state" :  $("#state").val(),
				"url" :  $("#url").val(),
				"type" :  $("#type").val(),
				"pid" :  $("#pid").val(),
			
			};
		console.log( JSON.stringify(qaram));
		
		$.postJSON(webRootPath + "/updatemenu", qaram, function(data) {
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
				
			"menuids": ruleIds
			
			};
		console.log( JSON.stringify(qaram));
		
		$.postJSON(webRootPath + "/delmenus", qaram, function(data) {
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