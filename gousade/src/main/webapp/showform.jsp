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
<title>公式展示页面</title>

  <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
 <script src="http://cdn.bootcss.com/jquery/1.12.2/jquery.js"></script>
 <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
  <script language="javascript" type="text/javascript" src="<%=request.getContextPath() %>/My97DatePicker/WdatePicker.js"></script>
  
<style>
h2,div {  
    text-align: center;  
}
</style>
</head>
<body>
<h2>本页面提供词性列表的下载和计算公式的展示。</h2>
<% String name = (String)session.getAttribute("u"); %>
<div ><a href="/xls/哈工大LTP词性含义列表.xlsx">点击下载词性表</a></div>
<p align="center">
公式展示：</p>
<div ><img src="/img/form.png"  /></div>

</body>
</html>