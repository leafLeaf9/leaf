<%--标签 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" isELIgnored="false" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%--ctxPath--%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--静态文件目录 --%>
<c:set var="staticPath" value="${ctx}" />
<!-- showfilepath 文件展示路径-->
<c:set var="showfilepath"
	value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/upload/files/" />
<!-- showfilepath 图片展示路径-->
<c:set var="showimgpath"
	value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/upload/images/upload" />