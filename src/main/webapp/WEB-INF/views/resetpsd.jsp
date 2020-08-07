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
<title>重置密码</title>
<style>
a{
 cursor:pointer;
 color:blue;
 display: block;
	text-align: center;
}

</style>
 <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
 <script src="http://cdn.bootcss.com/jquery/1.12.2/jquery.js"></script>
 <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
<% String name = (String)session.getAttribute("u"); %>
<form id="form1" >
 <table width="50%" border="0" align="center">
     <tr>
      <td width="40%">当前用户:</td>
      <!--当前登录用户名存放在session("loginUser")中-->
      <td width="60%" align="left"><%=name %></td>
    </tr>
    <tr>
      <td >当前密码:</td>
      <td><input name="currPsd" type="password" id="currPsd" /></td>
    </tr>
    <tr>
      <td>新的密码:</td>
      <td><input name="psd" type="password" id="psd" /></td>
    </tr>
    <tr>
      <td>密码确认:</td>
      <td><input name="repsd" type="password" class="STYLE2" id="repsd" /></td>
    </tr>
    <tr>
    <!--在</%%>中添加代码，显示changepsd.asp页面返回的错误信息-->
      <td colspan="2"><label id="errInfo" style="color:#F00; font-family: '微软雅黑';"><%%></label></td>
    </tr>
    <tr>
      <td colspan="2">
      <!--在下一行添加代码，当单击"修改"按钮后，调用函数，校验页面输入-->
            <input name="modify" type="button" style="font-family:'微软雅黑'" id="modify" value="修改" onmousedown="this.style.backgroundColor='#1ec5e5'" />
            <input name="Submit2" type="reset" style="font-family:'微软雅黑'" value="重置" />
   		</td>
    </tr>
  </table>
  </form>
<a onclick="show()" align="center">忘记密码？</a>
<div id="forget">
<table width="50%" border="0" align="center">
 <tr>
      <td >当前用户:</td>
      <!--当前登录用户名存放在session("loginUser")中-->
      <td  ><%=name %></td>
    </tr>
    <tr>
      <td >短信验证码：</td>
      <td><input name="code" type="text" id="code" />  <input type="button"  value="获取验证码" onclick="forget()"></td>
    </tr>

</table>
<div align="center">
<input type="button"  value="重置密码" onclick="clean()">
</div>

</div>
<script language="javascript">
var cflag=false;
var pflag=false;
	var rpflag=false;
	$(document).ready(function(){
		$('input#currPsd').blur(function(){
				
				if($('#currPsd').val()==""){
					//alert('请输入6-16个字符，不允许使用特殊字符!');
					$('#errInfo').html('当前密码不能为空');
					$('#errInfo').fadeIn("slow");
					$('#errInfo').fadeOut(1000);
					cflag=false;
					return;
				}
				
				cflag=true;
			}	
		);
		$('input#psd').blur(function(){
				var t=/^.{6,50}$/;
				var p=$('#psd').val();
				if(!t.test(p)){
					$('#errInfo').html('密码至少6位，不允许使用特殊字符!');
					$('#errInfo').fadeIn("slow");
					$('#errInfo').fadeOut(1000);
					pflag=false;
					return;
				}
				pflag=true;
			}	
		);	
		$('input#repsd').blur(function(){
				var p=$('#psd').val();
				var rp=$('#repsd').val();
				if(p!=rp){;
					$('#errInfo').html('两次密码输入不一致!');
					$('#errInfo').fadeIn("slow");
					$('#errInfo').fadeOut(1000);
					rpflag=false;
					return;
				}
				rpflag=true;
			}	
		);
		$("#modify").click(function(){
			if(cflag && pflag && rpflag)
				changepsd();
			else
			{
				$('#errInfo').html('校验未通过，请检查当前密码，新密码以及确认新密码!');
					$('#errInfo').fadeIn("slow");
					$('#errInfo').fadeOut(1000);
				}
			
			});
		
		$('#form1').keypress(function(event){
			if(event.keyCode==13){
				if(cflag && pflag && rpflag)
					changepsd();
				else
					{
						$('#errInfo').html('校验未通过，请检查当前密码，新密码以及确认新密码!');
							$('#errInfo').fadeIn("slow");
							$('#errInfo').fadeOut(3000);
						}
				}
			});
		
		});

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
		  
			
			function changepsd(){
			  var webRootPath = '<%=request.getContextPath()%>';	
				var qaram = {
						"user_id" :  "<%=name %>",
						"currPsd" :  $("#currPsd").val(),
						"password" :  $("#psd").val(),
						
					
					};
				console.log( JSON.stringify(qaram));
				
				$.postJSON(webRootPath + "/resetpsd", qaram, function(data) {
		              if (data) {
						
						alert(data.result);
						
						
					}else{
						alert("操作失败！");
					}
				});
		  }
			$("#forget").hide();
			function show(){
				$("#forget").show();
				
			}	
			function forget(){	
				 var webRootPath = '<%=request.getContextPath()%>';	  
				var qaram = {
						"user_id" :  "<%=name %>",	
						
					
					};
				console.log( JSON.stringify(qaram));
				
				$.postJSON(webRootPath + "/getcode", qaram, function(data) {
		              if (data) {
						
						alert("我们已经向您绑定的手机"+data.phonenumber.substr(0, 3) + '****' + data.phonenumber.substr(7)+"发送了短信，请注意查收。");
						
						
					}else{
						alert("操作失败！");
					}
				});
			}
			function clean(){
				 var webRootPath = '<%=request.getContextPath()%>';	  
					var qaram = {
						"user_id" :  "<%=name %>",	
						"code":	$("#code").val(),
						
						};
					console.log( JSON.stringify(qaram));
					
					$.postJSON(webRootPath + "/cleanpsd", qaram, function(data) {
			              if (data) {
							
			            		alert(data.result);
							
							
						}else{
							alert("操作失败！");
						}
					});
			}

</script>
</body>
</html>