<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结题项目评审</title>
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
<form action="/endReview" method="GET">
	<table align="center">
		<tr>
			<td>
				<input type="hidden" id="message" value="${message}" >
				<input type="hidden" id="message" value="${projId}" name="projId" >
			</td>
		</tr>
		<tr>
			<td>项目结题验收材料:&nbsp;
				<a href="${url}" target="blank" id="download">点击下载</a></td>
		</tr>
		<tr></tr>
		<tr>
			<td>
				审批结果：
				<select name="result" size=1>
					<option value="1">审核通过</option>
					<option value="0">驳回整改</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<h3 align="center">审批结果说明</h3>
		    	<textarea name="explain" rows="10" cols="50">驳回整改时请在此处说明原因...</textarea>
		    </td>
		</tr>
		<tr>
			<td align="center"><input type="submit" value="提交"></td>
		</tr>
	</table>
</form>

</body>
</html>