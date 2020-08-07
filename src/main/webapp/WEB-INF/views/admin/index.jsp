<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/template/commons/total.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<%@ include file="/template/commons/basejs.jsp"%>
</head>
<body class="hold-transition sidebar-mini skin-blue">
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
		<a href="#" class="dropdown-toggle nowLogin" data-toggle="dropdown">
		<img src="${staticPath}/static/AdminLTE-2.4.2/dist/img/Tohsaka Rin.jpg" class="user-image" alt="User Image">
              <span class="hidden-xs">当前登录：
              <shiro:principal property='userName'></shiro:principal>
              </span>
        </a>
        <ul class="dropdown-menu nowLoginDropDown">
              <!-- User image -->
              <li class="user-header">
                <img src="${staticPath}/static/AdminLTE-2.4.2/dist/img/Tohsaka Rin.jpg" class="img-circle" alt="User Image">

                <p>
                  Alexander Pierce - Web Developer
                  <small>Member since Nov. 2012</small>
                </p>
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">修改密码</a>
                </div>
                <div class="pull-right">
                  <a href="#" class="btn btn-default btn-flat">退出</a>
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
						<img src="${staticPath}/static/AdminLTE-2.4.2/dist/img/Tohsaka Rin.jpg" class="img-circle" alt="User Image">
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
<%-- <a id="scrollUp" href="#" style="position: fixed; z-index: 2147483647; display: none;">
	<i class="fa fa-angle-up"></i>
</a>
<div class="basic-shield"></div>
	<div class="baisc-log-window">
		<img src="${staticPath}/static/image/loading.gif" alt="加载中...">
</div> --%>

<script>
$(function(){
	console.log('index');
	var currentSkin;
	var storage = window.localStorage;
	$('body').addClass(storage.currentSkin || 'skin-blue');
	$(".container-fluid").load("${ctx}/admin/user/userManage");
	$.ajax({
		type: "post",
		url: '${ctx}/admin/resource/tree',
		//async : false,
		dataType: 'json',
		contentType: 'application/json;charset=utf-8', // 设置请求头信息  
		success: function (result) {
			var str = "<li class='header'> 菜单导航</li>" +
				"<li class='treeview openNewContent active' data-url='/admin/main' data-resourcetype='1'><a href='#'><i class='fa fa-home'></i><span class='tree-name'>主页</span><span class='pull-right-container'></span></a></li>";
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
</script>
</body>
</html>