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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
a
{
 cursor:pointer;
 color:blue;
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
.row {
  font-size: 18px;
  color: #333;
  margin-top: 20px;
  margin-bottom: 20px;
}
.row_description {
  vertical-align: top;
  margin-left: 14%;
  display: inline-block;
  width: 22%;
}
.row_data {
  display: inline-block;
  width: 55%;
}
.danger {
  color: #F56C6C;
}
</style>
<!-- <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script> -->
<script src="./js/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="./js/jquery-easyui-1.7.0/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="./js/jquery-easyui-1.7.0/themes/icon.css">
<link rel="stylesheet" href="./img/icon-font/iconfont.css"><!-- 阿里巴巴矢量图标 -->
<link rel="stylesheet" href="./foundation-icons/foundation-icons.css"><!-- foundation图标 -->
<script src="./js/jquery-easyui-1.7.0/jquery.min.js"></script>
<script src="./js/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
<script src="./js/jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
<!-- <script type="text/javascript" src="./js/jquery.color.js"></script> -->
<script src="./js/jsUtil/jsUtil.js"></script>
<script src="./js/jsUtil/extraJs.js"></script>
<!-- <script src ="canvas-nest.js-2.0.1/dist/newnest.js"></script> -->
<script src="./static/echarts/echarts.min.js"></script>
<script src="./static/echarts/echarts-gl.min.js"></script>
</head>
<body>
<%--ctxPath--%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
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
</script>
</body>
</html>