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
<title>消息内容</title>

  <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
 <script src="http://cdn.bootcss.com/jquery/1.12.2/jquery.js"></script>
 <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
  <script language="javascript" type="text/javascript" src="<%=request.getContextPath() %>/My97DatePicker/WdatePicker.js">
  </script>
</head>

<body>
<% String name = (String)session.getAttribute("u"); %>

<div align="center">
<textarea rows="30" cols="50" >
	${message}
</textarea></div>
<div align="center"><a href="/findMessageList?userId=<%=name%>">返回</a></div>

</body>
</html>