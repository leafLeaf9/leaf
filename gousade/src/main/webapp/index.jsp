<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/template/commons/total.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/css/indexUl.css" />
<title>首页</title>
<style>
#index_tabs{
background-image:url(https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3862456464,1470828142&fm=26&gp=0.jpg);
background-repeat:repeat;
z-index:999;
}
#SSR-publish{
z-index:-1;
}
</style>
</head>
<body>
<div id="index_layout">
    <div data-options="region:'north',border:false" style="overflow: hidden; background: #2c7acb">
    <div class="lc-title">This is the index jsp of the gousade project.</div>
    </div>       
    <div data-options="region:'west',title:'菜单',split:true" 
    style="width: 200px; overflow: hidden; overflow-y: auto; padding: 0px; font-size: 12px;">
    <ul id="layout_west_tree"></ul>
    </div>
    <div data-options="region:'center'" style="overflow: hidden;">
    <div id="index_tabs" class="easyui-tabs" >
    <div title="SSR-publish" id="SSR-publish" style="padding:20px;display:none;">			
		1the newest ssr link is :
		<div id="ssr"></div>
		<img src="http://img01.sogoucdn.com/app/a/100520146/92f5c23a2b538e7b1e410d1e841eab87"  alt="Misaka Mikoto" width="100%" height="100%"/>
	</div>
	<div title="Tab0" data-options="closable:true" style="padding:20px;display:none;">			
		Misaka Mikoto
		<img src="http://img01.sogoucdn.com/app/a/100520146/92f5c23a2b538e7b1e410d1e841eab87"  alt="Misaka Mikoto" />
	</div>
    <div title="Tab1" data-options="closable:true" style="padding:20px;display:none;">			
		tab1
		<img src="http://img02.sogoucdn.com/app/a/100520146/5887413b7ba8277e4c2bd786748cf9b7"  alt="小狐狸"/>
	</div>
    <div title="Tab2" data-options="closable:true" style="overflow:auto;padding:20px;display:none;">
		tab2
		<img src="http://img03.sogoucdn.com/app/a/100520146/deea25f4f33701892d50890ecbeb1c04"  alt="蓝色花香"/>
    </div>
    <div title="Tab3" data-options="iconCls:'fi-eye',closable:true" style="padding:20px;display:none;">
		tab3
		<img src="http://img04.sogoucdn.com/app/a/100520146/76c99d3c9a38d9b398162b8dc0d3eab4"  alt="爱蜜莉雅" />
		<img src="http://img04.sogoucdn.com/app/a/100520146/9859c0db785f04221a8799425bdba11e"  alt="橙黑色妹妹"/>
    </div>
    </div>
    </div>
    <div id="tabsMenu">
			<div data-options="iconCls:'icon-reload'" type="refresh"
				style="font-size: 12px;">刷新</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'fi-x'" type="close"
				style="font-size: 12px;">关闭</div>
			<div data-options="iconCls:''" type="closeOther">关闭其他</div>
			<div data-options="iconCls:''" type="closeAll">关闭所有</div>
	</div>
</div>
<script>
$(function(){
	$('#index_layout').layout({
		fit : true,
	});
	var index_tabs;
	var indexTabsMenu;
	index_tabs = $('#index_tabs').tabs(
		{
			fit : true,
			border : false,
			onContextMenu : function(e, title) {
				e.preventDefault();
				indexTabsMenu.menu('show', {
					eft : e.pageX,
					top : e.pageY
				}).data('tabTitle', title);
			},
			tools : [
					{
						iconCls : 'fi-home',
						handler : function() {
							index_tabs.tabs('select', 0);
						}
					},
					{
						iconCls : 'fi-loop',
						handler : function() {
							refreshTab();
						}
					},
					{
						iconCls : 'fi-x',
						handler : function() {
							var index = index_tabs.tabs('getTabIndex',index_tabs.tabs('getSelected'));
							var tab = index_tabs.tabs('getTab', index);
							if (tab.panel('options').closable) {
								index_tabs.tabs('close', index);
							}
						}
					} ]
		});
	// 选项卡菜单
	indexTabsMenu = $('#tabsMenu').menu(
		{
			onClick : function(item) {
				var curTabTitle = $(this).data('tabTitle');
				var type = $(item.target).attr('type');
				if (type === 'refresh') {
					refreshTab();
					return;
				}
				if (type === 'close') {
					var t = index_tabs.tabs('getTab',curTabTitle);
					if (t.panel('options').closable) {
						index_tabs.tabs('close',curTabTitle);
					}
					return;
				}
				var allTabs = index_tabs.tabs('tabs');
				var closeTabsTitle = [];
				$.each(allTabs, function() {
					var opt = $(this).panel('options');
					if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
						closeTabsTitle.push(opt.title);
					} else if (opt.closable && type === 'closeAll') {
						closeTabsTitle.push(opt.title);
					}
				});
				for (var i = 0; i < closeTabsTitle.length; i++) {
					index_tabs.tabs('close',closeTabsTitle[i]);
				}
			}
		});
	
	getssrlink();
})

function getssrlink(){
	var param={};
	$.ajax({
		url : "${ctx}/getssrlink",
		type : "POST",
		cache : false,
		contentType : "application/json;charset=utf-8",
		data:JSON.stringify(param),
		dataType : 'json',		
		success : function(result) {
			$('#ssr').html(result.link);
		},
		error : function() {
			$('#ssr').html("404 Not Found");
		}
	})
}

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
</script>
</body>
</html>