<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/template/commons/total.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>GisardAdminLTE 3.0.5 | Log in</title>
<%@ include file="/template/commons/basejs.jsp"%>
<script src="${staticPath}/static/snowing/js/snow-plugin.js"></script>
<script src="${staticPath}/static/snowing/slowsnow/snowy.js"></script>
<script src="${staticPath}/static/snowing/slowsnow/Snow.js"></script>
<style>
.login-page {
	background-repeat:no-repeat;
	background-size:100% 100%;
}
.snow-container{position:fixed;top:0;left:0;width:100%;height:100%;pointer-events:none;z-index:100001;}
</style>
</head>
<body class="hold-transition login-page">
<div class="htmleaf-content">
<div class="snow-container"></div>
</div>
<div class="login-box">
  <div class="login-logo">
    <a href="{ctx}/admin/index"><b>GisardAdminLTE 3.0.5</b></a>
  </div>
  <!-- /.login-logo -->
  <div class="card">
    <div class="card-body login-card-body">
      <p class="login-box-msg">Sign in to start your session</p>

      <form id="user-login-form" method="post">
        <div class="input-group mb-3">
          <input type="text" name="userId" class="form-control" placeholder="Email or userName" required="required">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fa fa-id-card"></span>
            </div>
          </div>
        </div>
        <div class="input-group mb-3">
          <input type="password" name="password" class="form-control" placeholder="Password" required="required">
          <div class="input-group-append">
            <div class="input-group-text">
              <span class="fa fa-lock"></span>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-8">
            <div class="icheck-primary">
              <input type="checkbox" name="rememberMe">
              <label for="remember">
                Remember Me
              </label>
            </div>
          </div>
          <!-- /.col -->
          <div class="col-4">
            <button type="submit" class="btn btn-primary btn-block">Sign In</button>
          </div>
          <!-- /.col -->
        </div>
      </form>

      <div class="social-auth-links text-center mb-3">
        <p>- OR -</p>
        <a href="#" class="btn btn-block btn-primary">
          <i class="fab fa-qq mr-2"></i> Sign in using QQ
        </a>
        <a href="#" class="btn btn-block btn-danger">
          <i class="fab fa-google-plus mr-2"></i> Sign in using Google+
        </a>
      </div>
      <!-- /.social-auth-links -->

      <p class="mb-1">
        <a href="#">I forgot my password</a>
      </p>
      <p class="mb-0">
        <a href="#" class="text-center">Register a new membership</a>
      </p>
    </div>
    <!-- /.login-card-body -->
  </div>
</div>
</body>
<script>
$(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' /* optional */
    });
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
});

$('#user-login-form').submit(function(e){
  console.log(1);
  var userInsertForm = new FormData($("#user-login-form")[0]);
  $.ajax({
    url: "${ctx}/admin/sysUser/loginShiroUser",
    type: "POST",
    data: userInsertForm,
    cache : false,
    processData : false,// 告诉jQuery不要去处理发送的数据
    contentType : false,// 告诉jQuery不要去设置Content-Type请求头
    dataType: 'json',
    success: function (result) {
      if(result.status){
        console.log(result.msg);
        window.location.href = result.msg;
      }else {
        layer.open({
          content: result.msg,
          shadeClose: true,
        });
      }

    },
    error: function () {
      layer.msg('ajax error', {
        icon : 2,
        time : 1000,
      });
    },
  });
  return false;
});
</script>
</html>
