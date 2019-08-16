<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<base href="<%=basePath%>">
		<title></title>
		<style type="text/css">
			html,body{
				height: 100%;overflow: hidden;
			}
			body{
				background: url(img/bg1.jpg);
			}
			.wrap{
				position: absolute;
				left: 50%;
				top: 50%;
				width: 300px;
				height: 440px;
				border: 5px solid gray;
				transform: translate(-50%,-50%);
				text-align: center;
				background-color: white;
			}
			.wrap>form{
				text-align: right;
				margin-right: 30px;
			}
			.wrap>form>input[type=submit]{
				width: 90%;
				height: 40px;
				color: white;
				background-color: deepskyblue;
			}
			.wrap>img{
				width: 85%;
			}
		</style>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
 <script src="http://cdn.bootcss.com/jquery/1.12.2/jquery.js"></script>
 <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
	</head>
	<body>
		<div class="wrap">
			<img src="img/sign.jpg"/>
			<form action="##" method="post">
				用&nbsp;户&nbsp;名:&nbsp;<input type="text" id="user_id"/> <br /><br />
				姓&nbsp;&nbsp;&nbsp;名:&nbsp;<input type="text" id="user_name"/> <br /><br />
				
				密&nbsp;&nbsp;&nbsp;码:&nbsp;<input type="password" id="password" /> <br /><br />
				确认密码:&nbsp;<input type="password" id="confirm_password" /> <br /><br />
								
		<input type="button" style="float:left; width: 120.96px; height: 24.4px;" value="注册" onclick="regist()"/>
	    <input name="Submit2" style=" width: 120.96px; height: 24.4px;" type="reset" style="font-family:'微软雅黑'" value="重置" />
			</form>
			
		</div>
	<script>
	var rpflag=false;
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
		  $('#confirm_password').blur(function(){
				var p=$('#password').val();
				var rp=$('#confirm_password').val();
				if(p!=rp){
				alert("两次密码输入不一致！");
					rpflag=false;
					return;
				}
				rpflag=true;
			}	
		);
		  function regist(){
			  var webRootPath = '<%=request.getContextPath()%>';
			  if(rpflag){
				  var qaram = {										
							"user_id" :  $("#user_id").val(),
							"password" :  $("#password").val(),
							"user_name" :  $("#user_name").val(),
							"confirm_password" :  $("#confirm_password").val(),						
					};
				  $.postJSON(webRootPath + "/regist", qaram, function(data) {
		              if (data) {
						
						alert(data.result);
						window.location.href='login.jsp';
						
					}else{
						alert("操作失败！");
					}
				});
			  }else{
				  alert("两次密码输入不一致！");
			  } 
				
		  }
	
	</script>	
	</body>
</html>
