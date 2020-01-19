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
<title>中期待评审项目</title>

  <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
 <script src="http://cdn.bootcss.com/jquery/1.12.2/jquery.js"></script>
 <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
  <script language="javascript" type="text/javascript" src="<%=request.getContextPath() %>/My97DatePicker/WdatePicker.js">
  </script>
</head>
<body>
	<div class="eXtremeTable" >
	<table id="ec_table" class="tableRegion" width="98%" border="1">>
		<thead>
		<tr>
			<td class="tableHeader">项目名称</td>
			<td class="tableHeader">负责人ID</td>
			<td class="tableHeader">级别</td>
			<td class="tableHeader">电话</td>
			<td class="tableHeader">专业</td>
		</tr>
		</thead>
		
		<tbody class="tableBody" >
		<c:forEach items="${projList}" var="p" >
		<tr >
			<td><a href="/toMid_Review?projId=${p.projId}">${p.projName}</a></td>
			<td>${p.userId}</td>
			<td>${p.level}</td>
			<td>${p.phone}</td>
			<td>${p.major}</td>
		</tr>
		</c:forEach>
		</tbody>
		
	</table>
	</div>
</body>
</html>