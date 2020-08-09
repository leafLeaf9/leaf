<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/template/commons/total.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<%@ include file="/template/commons/basejs.jsp"%>
<script src="${staticPath}/static/snowing/js/snow-plugin.js"></script>
</head>
<body class="hold-transition sidebar-mini skin-yellow">
<div class="wrapper">
	<header class="main-header">
		<!-- Logo -->
		<a href="${ctx}/admin/index" class="logo">
		<span class="logo-mini"><b>A</b>LT</span>
		<span class="logo-lg"><b>Gisard</b>LTE</span>
		</a>
		
		<!-- Header Navbar: style can be found in header.less -->
		<nav class="navbar navbar-static-top" role="navigation" style="padding:0px 0px 0px 0px;height:50px;">
		<a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
		<span class="sr-only">Toggle navigation</span>
		</a>
		<!-- Navbar Right Menu -->
		<div class="navbar-custom-menu">
		<ul class="nav navbar-nav">
		<!-- <li class="dropdown messages-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <i class="fa fa-envelope-o"></i>
              <span class="label label-success">4</span>
            </a>
        </li> -->
          
        <!-- User Account Menu -->
		<li class="dropdown user user-menu">
		<a href="#" class="dropdown-toggle" data-toggle="dropdown">
		<img src="${staticPath}/static/AdminLTE-2.4.18/dist/img/Tohsaka Rin.jpg" class="user-image" alt="User Image">
              <span class="hidden-xs">当前登录：
              <shiro:principal property='userName'></shiro:principal>
              </span>
        </a>
        <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="${staticPath}/static/AdminLTE-2.4.18/dist/img/Tohsaka Rin.jpg" class="img-circle" alt="User Image">

                <p>
                  Alexander Pierce - Web Developer
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
		
		<!-- Control Sidebar Toggle Button -->
		<!-- <li>
			<a href="#" data-toggle="control-sidebar">
				<i class="fa fa-gears"></i>
			</a>
		</li> -->
		</ul>
		</div>
		</nav>
	</header>
	
	<!-- Left side column. contains the logo and sidebar -->
	<aside class="main-sidebar">
		<section class="sidebar">
			<div class="user-panel">
					<div class="pull-left image">
						<img src="${staticPath}/static/AdminLTE-2.4.18/dist/img/Tohsaka Rin.jpg" class="img-circle" alt="User Image">
					</div>
					<div class="pull-left info">
						<p>
							<shiro:principal property='userName'></shiro:principal>
						</p>
						<!-- Status -->
						<a href="#">
							<i class="fa fa-circle text-success"></i>在线</a>
					</div>
			</div>
			
			<!-- search form (Optional) -->
			<form action="#" class="sidebar-form">
				<div class="input-group">
					<input type="text" oninput="searchMenu(this.value)" class="form-control" placeholder="菜单搜索">
					<span class="input-group-btn">
						<button type="button" class="btn">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
			</form>
			
			<!-- Sidebar Menu -->
			<ul class="sidebar-menu" data-widget="tree" id="in-tree">
			</ul>
			<!-- /.sidebar-menu -->
		</section>
	</aside>
	
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1 style="display: none;" class="in-page-title">主页</h1>
		</section>
		
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
    <strong>Copyright &copy; 2018-2020 <a href="https://adminlte.io">GisardLTE</a>.</strong> All rights
    reserved.
  	</footer>
</div>
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
	console.log('Navigated to the index');
	var currentSkin;
	var storage = window.localStorage;
	$('body').addClass(storage.currentSkin || 'skin-yellow');
	$(".container-fluid").load("${ctx}/admin/user/userManage");
	$.ajax({
		type: "post",
		url: '${ctx}/admin/resource/tree',
		//async : false,
		dataType: 'json',
		contentType: 'application/json;charset=utf-8', // 设置请求头信息  
		success: function (result) {
			var str = "<li class='header'> 菜单导航</li>" +
				"<li class='treeview openNewContent active' data-url='/admin/main' data-resourcetype='1'><a href='#'><i class='fa fa-home'></i><span class='tree-name'>Index</span><span class='pull-right-container'></span></a></li>";
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

</script>
</body>
</html>