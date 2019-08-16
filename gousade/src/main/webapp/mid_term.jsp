<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>中期检查规则设置</title>

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

<form id="form2" action="setMidRule" method="post">
<input type="hidden" id="message" value="${message}" >
<table cellspacing="0" cellpadding="10" class="tfm" id="profilelist">
<tr >
<th >项目类型:</th>
<td >
<select name="level">
	<option value=1>校类一级</option>
	<option value=2>校类二级</option>
</select>
</tr> 
<tr >
<th >规则内容:</th>
<td >
<textarea name="text" id="content" class="pt" rows="5" cols="40" tabindex="1"></textarea><div class="rq mtn" id="showerror_bio"></div><p class="d"></p></td>
</tr>  

<tr >
<th >开始时间</th>
<td >
<input type="text" name="beginTime" id="startdate" onClick="WdatePicker()"   /></td>
</tr>

<tr >
<th >结束时间</th>
<td >
<input type="text" name="endTime" id="stopdate" onClick="WdatePicker()"   /></td>
</tr>
</table>

<input type="submit" value="提交"">
</form>
</body>
</html>