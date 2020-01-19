<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
 <script src="http://cdn.bootcss.com/jquery/1.12.2/jquery.js"></script>
 <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
 <input type="button" style=" width: 135.96px; height: 24.4px;" value="粗粒度汇总" onclick="thickshow()">  
  <input type="button" style=" width: 135.96px; height: 24.4px;" value="细粒度汇总" onclick="thinshow()">
 <div id="thick" style="height:400px" >thick</div>
     <div id="thin" style="height:400px" >thin</div>
     <script>

console.log(9);
function  allhide() {
	$("#thick").hide();$("#thin").hide();
}
allhide();
function  thickshow() {
	$("#thick").show();$("#thin").hide();
}
function  thinshow() {
	$("#thick").hide();$("#thin").show();
}
 </script>
</body>
</html>