<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/template/commons/total.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" href="css/skin_/nav.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/global.js"></script>
<title>菜单和资源页面iframe</title>
</head>
<% String name = (String)session.getAttribute("u"); %>
<body  onload="listAdminMenu()">



<div id="container">
<div id="bd">
    	<div class="sidebar">
        	<div class="sidebar-bg"></div>
            <i class="sidebar-hide"></i>
            <h2 ><span style="font-weight:bold;font-size:18px;color:white">
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;菜单</span></h2>
           
          <ul class="nav" id="menu-ul"> 
          
          </ul>
           
           
           <div class="tree-list outwindow">
            	<div class="tree ztree"></div>
            </div>
        </div>
        <div class="main">
        	<div class="title">
                <i class="sidebar-show"></i>
                <ul class="tab ue-clear">
                   
                </ul>
                <i class="tab-more"></i>
                <i class="tab-close"></i>
            </div>
            <div class="content">
            </div>
        </div>
    </div> 	
</div>
<div class="more-bab-list">
	<ul></ul>
    <div class="opt-panel-ml"></div>
    <div class="opt-panel-mr"></div>
    <div class="opt-panel-bc"></div>
    <div class="opt-panel-br"></div>
    <div class="opt-panel-bl"></div>
</div>
<script>
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
function listAdminMenu(){
	var webRootPath = '<%=request.getContextPath()%>';
	 
		var qaram = {
			// "pageNum" : (pageNum-1)*pageSize,
			// "pageSize" : pageSize,
			// "roleId" : roleIdShowGlob,
			"user_id":'<%=name%>'
		};
		$.postJSON(webRootPath + "/listAdminMenu", qaram, function(data) {
			if (data) {
				
				   console.log(data);
				 
			            //for循环初始化菜单
			            var str = '';
			            var dataid=1;
			            $.each(data, function (i, item) {
			                str += '<li class="nav-li current">';
			                str +='<a href="javascript:;" class="ue-clear">';
			                str += '<i class="nav-ivon"></i>';
			                str += '<span class="nav-text">' + item.name + '</span>'; 
			                
// 			                str += '<a href="' + item.url + '">';
// 			                str += '<i class="' + item.state + '"></i>';
// 			                str += '<span>' + item.name + '</span>';
// 			                if (item.childMenu.length != 0) {
// 			                    str += '<span class="pull-right-container">';
// 			                    str += '    <i class="fa fa-angle-left pull-right"></i>';
// 			                    str += '</span>';
// 			                }
			                str += '</a>';
			                str += '<ul class="subnav">';
			                if (item.childMenu.length != 0) {
			                    $.each(item.childMenu, function (j, item2) {
			                    	 str += '<li class="subnav-li" href="' + item2.url + '" data-id="' + dataid + '">';
			                    	 str += '<a href="javascript:;" class="ue-clear"><i class="subnav-icon"></i>';
			                    	 str += '<span class="subnav-text">' + item2.name + '</span>'; 
			                    	 str += '</a></li>';
			                    	 dataid++;
// 			                        str += '<li><a href="' + item2.url + '">';
// 			                        str += item2.name;
// 			                        str += '</a></li>';
			                    })
			                }
			                str += '</ul></li>';
			            });
			            $("#menu-ul").append(str);
			        
				
			}else{
				alert("操作失败！");
			}
		});
	
}

</script>
</body>
<script type="text/javascript" src="js/nav.js"></script>
<script type="text/javascript" src="js/Menu.js"></script>
<script type="text/javascript" src="js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
	var menu = new Menu({
		defaultSelect: $('.nav').find('li[data-id="1"]')	
	});


	
	$('.sidebar h2').click(function(e) {
        $('.tree-list').toggleClass('outwindow');
		$('.nav').toggleClass('outwindow');
    });
	
	$(document).click(function(e) {
		if(!$(e.target).is('.tab-more')){
			 $('.tab-more').removeClass('active');
			 $('.more-bab-list').hide();
		}
    });
</script>
</html>
