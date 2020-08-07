<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="myshiro" uri="/WEB-INF/tld/auth.tld"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
</head>
<body>
<%--ctxPath--%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--静态文件目录 --%>
<c:set var="staticPath" value="${ctx}" />

</body>
</html>