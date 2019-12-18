<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/template/commons/total.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
</head>
<body>
<div id="cc" class="easyui-layout" style="width:600px;height:400px;">
    <div data-options="region:'north',title:'导航栏'" style="height:100px;">
    <div class="lc-title">This is the index jsp of the gousade project.</div>
    </div>       
    <div data-options="region:'west',title:'菜单',split:true" style="width:100px;">
    <ul id="layout_west_tree"></ul>
    </div>
    <div data-options="region:'center',title:'首页'" style="padding:5px;background:#eee;">
    <div id="index_tabs" class="easyui-tabs">
    <div title="Tab1" style="padding:20px;display:none;">
		tab1
    </div>
    <div title="Tab2" data-options="closable:true" style="overflow:auto;padding:20px;display:none;">
		tab2
    </div>
    <div title="Tab3" data-options="iconCls:'icon-reload',closable:true" style="padding:20px;display:none;">
		tab3
    </div>
    </div>
    </div>
    <div id="tabsMenu">
			<div data-options="iconCls:'fi-loop'" type="refresh"
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
// 			onContextMenu : function(e, title) {
// 				e.preventDefault();
// 				indexTabsMenu.menu('show', {
// 					eft : e.pageX,
// 					top : e.pageY
// 				}).data('tabTitle', title);
// 			},
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
				var curTabTitle = $(this).data(
						'tabTitle');
				var type = $(item.target).attr('type');
				if (type === 'refresh') {
					refreshTab();
					return;
				}
				if (type === 'close') {
					var t = index_tabs.tabs('getTab',
							curTabTitle);
					if (t.panel('options').closable) {
						index_tabs.tabs('close',
								curTabTitle);
					}
					return;
				}
				var allTabs = index_tabs.tabs('tabs');
				var closeTabsTitle = [];
				$.each(allTabs, function() {
					var opt = $(this).panel('options');
					if (opt.closable
							&& opt.title != curTabTitle
							&& type === 'closeOther') {
						closeTabsTitle.push(opt.title);
					} else if (opt.closable
							&& type === 'closeAll') {
						closeTabsTitle.push(opt.title);
					}
				});
				for (var i = 0; i < closeTabsTitle.length; i++) {
					index_tabs.tabs('close',
							closeTabsTitle[i]);
				}
			}
		});
})
</script>
</body>
</html>