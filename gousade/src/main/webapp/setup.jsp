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
<title>确认立项</title>
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
			"state":"3",
			
			
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
													+"<td >"+"<a href='javascript:void(0)' id='pass' onclick=\"setup('"+value.proj_id+"')\">立项</a>"+" | "+
													"<a href='javascript:void(0)' id='pass' onclick=\"notsetup('"+value.proj_id+"')\">不立项</a>"
													+"</td>"
												+"</tr>";
										});
				
				$("#projlist").html(trs);
				
			}
		});
		
	}
  queryprojlist();
  function setup(projid){
	 
		
		if(confirm("是否确定立项该项目？")) {
			 var webRootPath = '<%=request.getContextPath()%>';
			 var proj_id=projid;
			  var qaram = {
						"state":"4",
						"proj_id": proj_id,
					};
				$.postJSON(webRootPath + "/dopassbyid", qaram, function(data) {
		            if (data) {
						
						alert(data.result);
						
						
					}else{
						alert("操作失败！");
					}
				});
			}else{
				
			}
  }
  function notsetup(projid){
	  if(confirm("是否确定否决该项目？")) {
			 var webRootPath = '<%=request.getContextPath()%>';
			 var proj_id=projid;
			  var qaram = {
						"state":"-1",
						"proj_id": proj_id,
					};
				$.postJSON(webRootPath + "/dopassbyid", qaram, function(data) {
		            if (data) {
						
						alert(data.result);
						
						
					}else{
						alert("操作失败！");
					}
				});
			}else{
				
			}
  }
  </script>
</body>
</html>