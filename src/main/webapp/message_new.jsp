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
<title>站内消息</title>

  <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
 <script src="http://cdn.bootcss.com/jquery/1.12.2/jquery.js"></script>
 <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
  <script language="javascript" type="text/javascript" src="<%=request.getContextPath() %>/My97DatePicker/WdatePicker.js">
  </script>
  <!--
<script>
function flush(){
    window.location.assign("message_show.jsp")
}
</script>
  -->
</head>

<body>
	<div class="eXtremeTable" >
	<table id="ec_table" class="tableRegion" width="98%" border="1">>
		<thead>
		<tr>
			<td class="tableHeader">发件人</td>
			<td class="tableHeader">详细信息</td>
			<td class="tableHeader">状态</td>
			<td class="tableHeader">时间</td>
		</tr>
		</thead>
		
		<tbody class="tableBody" >
		<c:forEach items="${messageList}" var="p" >
		<tr >

			<td>系统管理员</td>
			<td><a href="/showMessage?message=${p.message}&messId=${p.messId}" target="_parent"">点击查看详细信息</a></td>
			<td>
			<c:if test ="${p.state==0}"> 
				未读
			</c:if>
		   <c:if test ="${p.state==1}"> 
		               已读
			</c:if>
			</td>
			<td>${p.time}</td>
		</tr>
		</c:forEach>
		</tbody>
		
	</table>
	</div>
</body>
</html>