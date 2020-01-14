<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/template/commons/total.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/skin_/login.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.select.js"></script>
<title>shiro登录界面</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/loginShiroUser" method="post" >
<div id="container">
    <div id="bd">
    	<div id="main">
        	<div class="login-box">
                <div id="logo"></div>
                <h1></h1>
                <div class="input username" id="username">
                    <label for="userName">用户名1</label>
                    <span></span>
                    <input type="text" id="userName" name="userId" />
                </div>
                <div class="input psw" id="psw">
                    <label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                    <span></span>
                    <input type="password" id="password" name="password"/>
                </div>
                <div id="btn" class="loginButton">
                	<span style="color:red;font-weight:bold;margin-right:300px;font-size:20px;margin-left: 0px;">
                		<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>
               	    <input type="submit" id="Submit" class="button" value="登录"  />
                	<input type="button" class="button" value="注册" onclick="window.open('regist')"  />
                	</span></div>
            </div>
        </div>
        <div id="ft">CopyRight&nbsp;2018 - 2019&nbsp;&nbsp;&nbsp;&nbsp;personal project  -   - <a href="http://dnf.qq.com/" title="personal project" target="_blank">personal project</a> &nbsp;&nbsp;</div>
    </div>
</div>
</form>	

<!-- String username=request.getParameter("username"); -->
<!-- String password=request.getParameter("password"); -->
<!-- //连接数据库的方法或是调用后台的连接数据库的方法 -->
<!-- //查询，这里简写一下 -->



<!-- cn.edu.zstu.manage.pojo.User user = new cn.edu.zstu.manage.pojo.User(); -->
<!-- user.setUserId(username); -->
<!-- out.println(user.getUserId()); -->

<!-- session.setAttribute("userNow",user); -->

</body>
<script type="text/javascript">
	var height = $(window).height() > 445 ? $(window).height() : 445;
	$("#container").height(height);
	var bdheight = ($(window).height() - $('#bd').height()) / 2 - 20;
	$('#bd').css('padding-top', bdheight);
	$(window).resize(function(e) {
        var height = $(window).height() > 445 ? $(window).height() : 445;
		$("#container").height(height);
		var bdheight = ($(window).height() - $('#bd').height()) / 2 - 20;
		$('#bd').css('padding-top', bdheight);
    });
	$('select').select();
	var OriginTitile = document.title;
	var titleTime;
	document.addEventListener('visibilitychange', function() {
	    if (document.hidden) {
	    	document.title = '其实你点击不到我的。';
	        clearTimeout(titleTime);
	    } else {
	    	document.title = '食驚！';
	        titleTime = setTimeout(function() {
	            document.title = OriginTitile;
	        }, 1500);
	    }
	});
/* 	$('#Submit').click(function(e) {
		
        document.location.href = "/main";
    }); */
</script>

</html>
