<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>    
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>分配专家</title>
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
</style>
<link rel="stylesheet" type="text/css" href="styles/miwei.css" media="all">
  <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
 <script src="http://cdn.bootcss.com/jquery/1.12.2/jquery.js"></script>
 <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
  <script language="javascript" type="text/javascript" src="<%=request.getContextPath() %>/My97DatePicker/WdatePicker.js"></script>
  

</head>
<body>

<table 			align="center">
			<tr>
				<th>
					项目名称
				</th>
				<th>
					联系电话
				</th>
				<th>
					邮箱
				</th>
				<th>
					专业
				</th>
				<th>
					项目成员
				</th>
				<th>
					立项承诺
				</th>
				<th>
					审核操作
				</th>
			</tr>
            <tbody id="projlist">
            	
            	</tbody>
			
			
		</table>
		<div id="expert" align="center">
		<table align="center">        
                            	<thead>
                            		<tr class="th-blue-l">
										<th class="js-ckAll"><input type="checkbox" name=""></th>
	                                    <th>专家ID</th>
	                                    <th>专家名字</th>
										
                                	</tr>
                            	</thead>
                            	<tbody id="expertShowTable">
                            	</tbody>                          
       </table>
       <input type="button" value="提交" onclick="assign()">
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

  function queryprojlist(){
	  var webRootPath = '<%=request.getContextPath()%>';	  
		var qaram = {
			// "pageNum" : (pageNum-1)*pageSize,
			// "pageSize" : pageSize,
			// "roleId" : roleIdShowGlob,
			"state":"1"
		};
		$.postJSON(webRootPath + "/queryprojlist", qaram, function(data) {
			var trs = "";
			if (data) {
					$.each(data, function(index, value) {
											trs += "<tr>"
												   +"<td id='label'>"+"<input id='input' type='text'   value='"+value.proj_id+"'>"+"</td>"
													+"<td >"+value.proj_name+"</td>"
													+"<td >"+value.phone+"</td>"
													+"<td >"+value.email+"</td>"
													+"<td >"+value.major+"</td>"
													+"<td >"+value.members+"</td>"
													+"<td >"+value.promise+"</td>"
													+"<td >"+"<a href='javascript:void(0)' id='pass' onclick=\"queryexperts('"+value.proj_id+"')\">分配专家</a>"+"</td>"
												+"</tr>";
										});
				
				$("#projlist").html(trs);
				
			}
		});
		
	}
  queryprojlist();
  $("#expert").hide();
  function queryexperts(projid){
	  $("#expert").show();
	  var webRootPath = '<%=request.getContextPath()%>';	  
	  var proj_id=$("#input").val();
		var qaram = {
			// "pageNum" : (pageNum-1)*pageSize,
			// "pageSize" : pageSize,
			// "roleId" : roleIdShowGlob,
			"proj_id":"1",
			"roleid":"2",
		};
		$.postJSON(webRootPath + "/queryuserlist", qaram, function(data) {
			
				var trs = "";
				if (data) {
						$.each(data, function(index, value) {
												trs += "<tr>"
														+"<td class='js-ck'><input type='checkbox' name='messagePush' value='"+value.user_id+"'  data-pid= '"+projid+"' ></td>"
														+"<td >"+value.user_id+"</td>"
														+"<td >"+value.user_name+"</td>"
														
													+"</tr>";
											});
						$("#expertShowTable").html(trs);
			}else{
				alert("操作失败！");
			}
		});	
	}
  
  $("#expert").on("click",".th-blue-l .js-ckAll input[type=checkbox]",function(){
		if($(this).prop("checked")==true){
			$("#expertShowTable .js-ck input[type=checkbox]").prop("checked",true);
		}else{
			$("#expertShowTable .js-ck input[type=checkbox]").prop("checked",false);
		}
	})
	function assign(){
	  var webRootPath = '<%=request.getContextPath()%>';	
	  var ruleIds=new Array();
	  var checkMshPsh=$("input[name='messagePush']:checked");
	 // var ruleIds = {};
	var i=0;
	  var proj_id=$(checkMshPsh).data("pid");
	  console.log(proj_id);
		$("#expertShowTable .js-ck input[name='messagePush']:checked").each(function(){
			/*ruleIds[i] = {};
			ruleIds[i]['userid'] =$(this).val();
			i++;*/
			ruleIds.push($(this).val());
		});
		console.log(ruleIds);
		var qaram = {
				"proj_id" : proj_id,
			"userids": ruleIds
			
			};
		console.log( JSON.stringify(qaram));
		
		$.postJSON(webRootPath + "/assignexperts", qaram, function(data) {
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