<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/template/commons/total.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${staticPath}/static/css/generalForm.css" />
<title>角色管理</title>
</head>
<body>
<div class="htmleaf-container">
    <header class="htmleaf-header">
    </header>
    <div class="htmleaf-content">
        <canvas class="snow-canvas" speed="1" interaction="false" size="2" count="80" opacity="0.00001" start-color="rgba(253,252,251,1)" end-color="rgba(251,252,253,0.3)" wind-power="0" image="false" width="1366" height="667"></canvas>
        <canvas class="snow-canvas" speed="3" interaction="true" size="6" count="30" start-color="rgba(253,252,251,1)" end-color="rgba(251,252,253,0.3)" opacity="0.00001" wind-power="2" image="false" width="1366" height="667"></canvas>
        <canvas class="snow-canvas" speed="3" interaction="true" size="12" count="20" wind-power="-5" image="${staticPath}/static/snowing/img/snow.png" width="1366" height="667"></canvas>
    </div>

</div>
<script>
    $(function(){
        $(".snow-canvas").snow();
    });
</script>
</body>
</html>