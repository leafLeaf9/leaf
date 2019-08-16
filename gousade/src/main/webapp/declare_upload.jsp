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
   <form action="/toDeclare_upload" enctype="multipart/form-data" method="POST" >
      <input type="hidden" id="message" value="${message}" >
      <tr >
	  <th >项目ID:</th>
	  <td >
	  <input type="text" name="projId"/><div class="rq mtn" id="showerror_realname"></div><p class="d"></p></td>
	  </tr>
      			   <input type="file" name="myfile"/><br>
                   <input type="submit" value="上传"/>
   </form>
</body>
</html>