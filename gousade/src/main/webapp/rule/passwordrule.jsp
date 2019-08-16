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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>密码规则页面</title>
</head>
<body>
默认密码规则如下。
1.默认数字密码：10位数字
2.默认字母+数字密码：两类首字母小写缩写+10位数字
3.默认字母+数字+特殊字符密码：第二类字母小写缩写+6位数字+.
</body>
</html>