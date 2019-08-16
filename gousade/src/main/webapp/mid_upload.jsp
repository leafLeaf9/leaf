<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>中期检查材料上传</title>

  <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
 <script src="http://cdn.bootcss.com/jquery/1.12.2/jquery.js"></script>
 <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
  <script language="javascript" type="text/javascript" src="<%=request.getContextPath() %>/My97DatePicker/WdatePicker.js"></script>
  <script defer="defer">
  	window.onload=function(){ 
  		var message = document.getElementById("message").value; 
  		if(message!=null && message != ""){
  	  		alert(message);
  	  	}
	} 
  </script>
</head>
<body>
	<div>
		
	</div>
   <form action="/midUpload" enctype="multipart/form-data" method="POST" >
	   <table align="center">
	      	<tr>
	      		<td>
	      			<input type="hidden" id="message" value="${message}" >
	      			<h3>校类一级项目规则说明</h3>
	      			<textarea rows="20" cols="30" readonly="readonly" >${rule1}</textarea>
	      		</td>
	      		<td>
	      			<h3>校类二级项目规则说明</h3>
	      			<textarea rows="20" cols="30" readonly="readonly" >${rule2}</textarea>
	      		</td>
	      	</tr>
	      	<br><br>
	     	<tr>
	     		<td>项目ID:&nbsp;&nbsp;<input type="text" name="projId"/></td>
	     	</tr>
	     	<tr>
	     		<td><input type="file" name="myfile"/>&nbsp;&nbsp;
	     			<input type="submit" value="上传"/></td>
	     	</tr>
	   </table>
   </form>
</body>
</html>