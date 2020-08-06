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
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- AdminLTE必须 -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<style>
</style>
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
<!-- <script src="./static/echarts/echarts-gl.min.js"></script> -->
<link rel="shortcut icon" href="/favicon.ico" />
<link rel="bookmark" href="/favicon.ico" type="image/x-icon"　/>
<!-- Bootstrap 4.5.0 如果你使用的是我们编译过的 JavaScript，不要忘记在它之前引入 jQuery 和 Popper.js jQuery在前,Popper在中间,Bootstrap最后 -->
<script src="./static/popper.js-1.6.1/popper.min.js"></script>
<link rel="stylesheet" href="./static/bootstrap-4.5.0-dist/css/bootstrap.min.css">
<script src="./static/bootstrap-4.5.0-dist/js/bootstrap.min.js"></script>
<!-- font-awesome-4.7.0 -->
<link rel="stylesheet" href="${staticPath}/static/font-awesome-4.7.0/css/font-awesome.min.css">
<!-- AdminLTE -->
<link rel="stylesheet" href="${staticPath}/static/AdminLTE-3.0.5/dist/css/AdminLTE.min.css">
<%-- <link rel="stylesheet" href="${staticPath }/static/AdminLTE-3.0.5/dist/css/skins/_all-skins.min.css"> --%>
<!-- DataTables -->
<link rel="stylesheet" href="./static/DataTables-1.10.21/media/css/jquery.dataTables.css">
<script src="./static/DataTables-1.10.21/media/js/jquery.dataTables.js"></script>
<!-- <link rel="stylesheet" href="./static/DataTables-1.10.21/media/css/dataTables.bootstrap4.min.css"> -->
<!-- Select2 -->
<link rel="stylesheet" href="./static/select2-4.0.13/dist/css/select2.min.css">
<script src="./static/select2-4.0.13/dist/js/select2.min.js"></script>
<script src="./static/select2-4.0.13/dist/js/select2tree.js"></script>
<!-- datetimepicker -->
<link rel="stylesheet" href="./static/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css">
<script type="text/javascript" src="./static/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="./static/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<!-- layer -->
<script type="text/javascript" src="${staticPath}/static/layer/layer.js"></script>
<!-- customized basic css -->
<link rel="stylesheet" href="${staticPath}/static/css/customized-basic.css">
</head>
<body>
<%--ctxPath--%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--静态文件目录 --%>
<c:set var="staticPath" value="${ctx}" />
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
//配置DataTables默认参数
$.extend(true, $.fn.dataTable.defaults, {
    "language": {
    	"url": "./static/DataTables-1.10.21/assets/Chinese.txt",
    	"pagingType": "full_numbers",
    	"autoWidth": true,
    },
    "columnDefs": [ {
        "targets": '_all',//将所有列的空值变为''空字符串,防止报Requested unknown parameter 'xxx',please see http://datatables.net/tn/4错误
        "defaultContent": '',
      } ]
});
</script>
</body>
</html>