<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/template/commons/total.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<%@ include file="/template/commons/basejs.jsp"%>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">

  <!-- Navbar -->
  <nav class="main-header navbar navbar-expand navbar-dark navbar-navy">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="index3.html" class="nav-link">Home</a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="#" class="nav-link">Contact</a>
      </li>
    </ul>

    <!-- SEARCH FORM -->
    <form class="form-inline ml-3">
      <div class="input-group input-group-sm">
        <input class="form-control form-control-navbar" type="search" placeholder="Search" aria-label="Search">
        <div class="input-group-append">
          <button class="btn btn-navbar" type="submit">
            <i class="fas fa-search"></i>
          </button>
        </div>
      </div>
    </form>

    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">
      <!-- Messages Dropdown Menu -->
      
      <li class="nav-item dropdown user user-menu">
		<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">
		<img src="${staticPath}/static/AdminLTE-3.0.5/dist/img/Tohsaka Rin.jpg" class="user-image" alt="User Image">
              <span class="hidden-xs">当前登录：
              <shiro:principal property='userName'></shiro:principal>
              </span>
              <i class="right fas fa-angle-down"></i>
        </a>
        <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="${staticPath}/static/AdminLTE-3.0.5/dist/img/Tohsaka Rin.jpg" class="img-circle" alt="User Image">

                <p>
                  Tohsaka Rin - Web Developer
                  <small>Member since Nov. 2012</small>
                </p>
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <button class="btn btn-danger btn-flat">修改密码</button>
                </div>
                <div class="pull-right">
                  <button class="btn btn-default btn-flat" onclick="logout();">退出</button>
                </div>
              </li>
            </ul>
		</li>
      
      <li class="nav-item dropdown">
        <a class="nav-link" data-toggle="dropdown" href="#">
          <i class="far fa-comments"></i>
          <span class="badge badge-danger navbar-badge">3</span>
        </a>
        <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
          <a href="#" class="dropdown-item">
            <!-- Message Start -->
            <div class="media">
              <img src="${staticPath}/static/AdminLTE-3.0.5/dist/img/Tohsaka Rin.jpg" alt="User Avatar" class="img-size-50 mr-3 img-circle">
              <div class="media-body">
                <h3 class="dropdown-item-title">
                  Brad Diesel
                  <span class="float-right text-sm text-danger"><i class="fas fa-star"></i></span>
                </h3>
                <p class="text-sm">Call me whenever you can...</p>
                <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>
              </div>
            </div>
            <!-- Message End -->
          </a>
          <div class="dropdown-divider"></div>
          <a href="#" class="dropdown-item">
            <!-- Message Start -->
            <div class="media">
              <img src="${staticPath}/static/AdminLTE-3.0.5/dist/img/Tohsaka Rin.jpg" alt="User Avatar" class="img-size-50 img-circle mr-3">
              <div class="media-body">
                <h3 class="dropdown-item-title">
                  John Pierce
                  <span class="float-right text-sm text-muted"><i class="fas fa-star"></i></span>
                </h3>
                <p class="text-sm">I got your message bro</p>
                <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>
              </div>
            </div>
            <!-- Message End -->
          </a>
          <div class="dropdown-divider"></div>
          <a href="#" class="dropdown-item">
            <!-- Message Start -->
            <div class="media">
              <img src="${staticPath}/static/AdminLTE-3.0.5/dist/img/Tohsaka Rin.jpg" alt="User Avatar" class="img-size-50 img-circle mr-3">
              <div class="media-body">
                <h3 class="dropdown-item-title">
                  Nora Silvester
                  <span class="float-right text-sm text-warning"><i class="fas fa-star"></i></span>
                </h3>
                <p class="text-sm">The subject goes here</p>
                <p class="text-sm text-muted"><i class="far fa-clock mr-1"></i> 4 Hours Ago</p>
              </div>
            </div>
            <!-- Message End -->
          </a>
          <div class="dropdown-divider"></div>
          <a href="#" class="dropdown-item dropdown-footer">See All Messages</a>
        </div>
      </li>
      <!-- Notifications Dropdown Menu -->
      <li class="nav-item dropdown">
        <a class="nav-link" data-toggle="dropdown" href="#">
          <i class="far fa-bell"></i>
          <span class="badge badge-warning navbar-badge">15</span>
        </a>
        <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
          <span class="dropdown-item dropdown-header">15 Notifications</span>
          <div class="dropdown-divider"></div>
          <a href="#" class="dropdown-item">
            <i class="fas fa-envelope mr-2"></i> 4 new messages
            <span class="float-right text-muted text-sm">3 mins</span>
          </a>
          <div class="dropdown-divider"></div>
          <a href="#" class="dropdown-item">
            <i class="fas fa-users mr-2"></i> 8 friend requests
            <span class="float-right text-muted text-sm">12 hours</span>
          </a>
          <div class="dropdown-divider"></div>
          <a href="#" class="dropdown-item">
            <i class="fas fa-file mr-2"></i> 3 new reports
            <span class="float-right text-muted text-sm">2 days</span>
          </a>
          <div class="dropdown-divider"></div>
          <a href="#" class="dropdown-item dropdown-footer">See All Notifications</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link" data-widget="control-sidebar" data-slide="true" href="#" role="button">
          <i class="fas fa-th-large"></i>
        </a>
      </li>
    </ul>
  </nav>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="${ctx}/admin/index" class="brand-link">
      <img src="${staticPath}/static/AdminLTE-3.0.5/dist/img/Tohsaka Rin.jpg" alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
           style="opacity: .8">
      <span class="brand-text font-weight-light">GisardAdminLTE 3.0.5</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image">
          <img src="${staticPath}/static/AdminLTE-3.0.5/dist/img/Tohsaka Rin.jpg" class="img-circle elevation-2" alt="User Image">
        </div>
        <div class="info">
          <a href="#" class="d-block">Tohsaka Rin</a>
          <a href="#"><i class="fa fa-circle text-success"></i>在线</a>
        </div>
      </div>
      
      <!-- search form (Optional) -->
		<form action="#" class="form-inline ml-3">
			<div class="input-group">
				<input type="text" oninput="searchMenu(this.value)" class="form-control menuSearchInput" style="background-color:#374850;" placeholder="菜单搜索">
				<span class="input-group-btn">
					<button type="button" class="btn btn-dark">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div>
		</form>

      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column sidebar-menu" data-widget="treeview" role="menu" data-accordion="false" id="in-tree">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
          
        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
		<h1 style="display: none;" class="in-page-title">主页</h1>
	</section>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content container-fluid">
      <!--------------------------
      | Your Page Content Here |
      -------------------------->
    </section>
    <iframe id="content-iframe" src="" frameborder="0" height="100%" style="padding: 15px; width: 1px; min-width: 100%; width: 100%; display: none;"></iframe>
	<!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
  <div class="pull-right hidden-xs">
      <b>Version</b> 1.1.0
  </div>
  <strong>Copyright &copy; 2018-2020 <a href="https://adminlte.io">GisardAdminLTE3.0.5</a>.</strong> All rights
  reserved.
  </footer>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<!-- 上滑至顶端 -->
<a id="scrollUp" href="#" style="position: fixed; z-index: 2147483647; display: none;">
	<i class="fa  fa-angle-double-up"></i>
</a>
<div class="basic-shield"></div>
	<div class="baisc-log-window">
		<img src="${staticPath}/static/image/loading.gif" alt="加载中...">
</div>

<script>
$(function(){
	$(".container-fluid").load("${ctx}/admin/user/userManage");
	$.ajax({
		type: "post",
		url: '${ctx}/admin/resource/tree',
		//async : false,
		dataType: 'json',
		contentType: 'application/json;charset=utf-8', // 设置请求头信息  
		success: function (result) {
	// 		var str ="<li class='nav-item openNewContent active' data-url='/admin/main' data-resourcetype='1'><a href='#'><i class='fa fa-home'></i><span class='tree-name'>Index</span><span class='pull-right-container'></span></a></li>";
			var str="";
			var setting = {
				data: {
					simpleData: {
						enable: true,
						idKey:'id',
		                pIdKey:'pid',
		                rootPId: null
					}
				}
			};
			for (var i = 0; i < result.length; i++) {
				result[i].open = true
			}
			$.fn.zTree.init($("#in-tree"), setting, result);
			$("#in-tree").prepend(str);
		}
	});
});

$(".sidebar-menu").on("click", '.openNewContent', function () {
	// 清除所有菜单 active 状态
	$(".openNewContent").each(function () {
		$(this).removeClass("active");
	});
	var resourceType = $(this).attr("data-resourceType");
	$(this).addClass("active")
	var url = $(this).attr("data-url");
	// 添加选中菜单 active 状态
	if (resourceType == '1') {
		$(".container-fluid").css('display', 'block');
		$("#content-iframe").css('display', 'none');
		url = url;
		$(".in-page-title").text($(this).text());
		$(".container-fluid").load(url);
	} else if (resourceType == '2') {
		window.open(url);
	} else if (resourceType == '3') {
		var userNameStr = Base64.encode("<shiro:principal property='userId'></shiro:principal>");
		var newurl = url.replace('%username%', userNameStr);
		window.open(newurl);
	} else if (resourceType == '4') {
		var userNameStr = "<shiro:principal property='userId'></shiro:principal>";
		var newurl = url.replace('%username%', userNameStr);
		window.open(newurl);
	} else if (resourceType == '5') {
		// 隐藏 section 内容区， 展示 iframe 内容区
		$(".in-page-title").text($(this).text());
		$(".container-fluid").css('display', 'none');
		$("#content-iframe").attr('src', $(this).attr('data-url'));
		$("#content-iframe").css('display', 'block');
	} else if (resourceType == '7') {
		var userNameStr = "<shiro:principal property='userId'></shiro:principal>";
		var newurl = url.replace('%username%', userNameStr);
		$(".in-page-title").text($(this).text());
		$(".container-fluid").css('display', 'none');
		$("#content-iframe").attr('src', newurl);
		$("#content-iframe").css('display', 'block');
	}
});

function searchMenu(value){
	if (value != null && value.length != 0) {
		$(".treeview").hide();
		$(".tree-name:contains(" + value + ")").parents('.treeview').show();
		$(".tree-name:contains(" + value + ")").parents('.treeview-menu').css('display', 'block');
		$(".tree-name:contains(" + value + ")").closest('.treeview').find('.treeview').css('display', 'block');
	} else {
		$(".treeview").show();
	}
}

//pace进度条
$(document).ajaxStart(function () {
	Pace.restart();
});

function open_shield() {
	$('.basic-shield').css('display', 'block');
	$('.baisc-log-window').css('display', 'block');
}
// 关闭
function cancel_shield() {
	$('.basic-shield').css('display', 'none');
	$('.baisc-log-window').css('display', 'none');
}

$('.wrapper').scroll(function() {
	// 获取当前滚动条纵坐标的位置
	var ctop = $('.wrapper').scrollTop();
	console.log(ctop);
	// 判断滚动条距离是否大于导航条顶部距离
	if (ctop > 300) {
		$('#scrollUp').css('display', 'block');
		$('#scrollUp').on('click', function () {
			$('.content-wrapper').scrollTop(0);
		});
	} else {
		$('#scrollUp').css('display', 'none');
	}
});

//$('.content-wrapper').scroll()不生效，原因不明，用$(window).scroll()代替后可行
$(window).scroll(function (event) {
	var ctop = $(window).scrollTop();
// 	console.log(ctop);
	// 判断滚动条距离是否大于导航条顶部距离
	if (ctop > 300) {
		$('#scrollUp').css('display', 'block');
		$('#scrollUp').on('click', function () {
			$('.content-wrapper').scrollTop(0);
		});
	} else {
		$('#scrollUp').css('display', 'none');
	}
});

function logout(){
	layer.confirm('是否退出当前用户?', {icon: 3, title:'退出确认'}, function(index){
		$.post('${ctx}/admin/sysUser/logout', function (result) {
			if (result.status) {
				$('body').append(result.msg)
				window.location.href = '${ctx}';
			} else {
				window.location.href = '${ctx}';
			}
		}, 'json');
		layer.close(index);
	});
}

$(".menuSearchInput").focus(function(){
    $(this).css("background-color","#fff");
});
$(".menuSearchInput").focusout(function(){
    $(this).css("background-color","#374850");
});
</script>
</body>
</html>