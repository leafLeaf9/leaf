<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/template/commons/total.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/skin_/main.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.dialog.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/global.js"></script>
<title> personal project</title>
<style>
#nowtime{color:white;
position:absolute;
right:20%;
top:20px;
}
#container .user-name{
position:absolute;
top:10px;
left:87%;
}
#exitBtn{
	position: absolute;
	top: 10px;
	right: 1%;
}

</style>
</head>
<% String name = (String)session.getAttribute("u"); %>
<body onload="ShowTime()">

<div id="container">
	<div id="hd">
    	<div class="hd-top">
    	<div id="nowtime"></div>
            <h1 class="logo"></h1>
            <div class="setting ue-clear">
            	<div class="user-info">
                	<a href="/findMessageList?userId=<%=name%>" target="_blank" class="user-avatar"><span><i class="info-num">2</i></span></a>
                	<span class="user-name" >您好，<%=(String)session.getAttribute("user_name")%></span>
            	</div>
                <ul class="setting-main ue-clear">
                    <li id="exitBtn"><a href="javascript:;" class="close-btn exit"></a></li>
                </ul>
            </div>
        </div>
    </div>
    <div id="bd">
        <iframe width="100%" height="100%" id="mainIframe" src="nav.jsp" frameborder="0"></iframe>
    </div>
</div>

<div class="exitDialog">
	<div class="content">
    	<div class="ui-dialog-icon"></div>
        <div class="ui-dialog-text">
        	<p class="dialog-content">你确定要退出系统？</p>
            <p class="tips">如果是请点击“确定”，否则点“取消”</p>
            
            <div class="buttons">
                <input type="button" class="button long2 ok" value="确定" />
                <input type="button" class="button long2 normal" value="取消" />
            </div>
        </div>
    </div>
</div>


</body>
<script type="text/javascript" src="js/core.js"></script>
<script type="text/javascript" src="js/jquery.dialog.js"></script>
<script>
function ShowTime(){
	getnowtime();
	setInterval(function(){
		getnowtime();
	},1000);     //setInterval(fn,i) 定时器，每隔i秒执行fn
}
function getnowtime(){
  var time=new Date();
  var year=time.getFullYear();  //获取年份
  var month=time.getMonth()+1;  //获取月份
  var day=time.getDate();   //获取日期
  var hour=checkTime(time.getHours());   //获取时
  var minite=checkTime(time.getMinutes());  //获取分
  var second=checkTime(time.getSeconds());  //获取秒
  /****当时、分、秒、小于10时，则添加0****/
  function checkTime(i){
	  if(i<10){
			return "0"+i;  
		  }
	  return i;
  }
  document.getElementById("nowtime").innerHTML="当前时间："+year+"年"+month+"月"+day+"日  "+hour+":"+minite+":"+second;
}
$("#bd").height($(window).height()-$("#hd").height());
$(window).resize(function(e) {
    $("#bd").height($(window).height()-$("#hd").height());

});

$('.exitDialog').Dialog({
	title:'提示信息',
	autoOpen: false,
	width:400,
	height:200
});

$('.exit').click(function(){
	$('.exitDialog').Dialog('open');
});

$('.exitDialog input[type=button]').click(function(e) {
    $('.exitDialog').Dialog('close');
	
	if($(this).hasClass('ok')){
// 		window.location.href = "login.html"	;
		$.post('${ctx}/logout', function (result) {
			if (result.status) {
				//progressClose(); 添加一个加载动画，
				$('body').append(result.msg)
				window.location.href ='${ctx}';
				//isOpen=false;
			} else {
				window.location.href ='${ctx}';
			}
		}, 'json');
	}
});

$("#in-logout").on("click", function () {
	$.post('${ctx}/logout', function (result) {
		if (result.status) {
			//progressClose(); 添加一个加载动画，
			$('body').append(result.msg)
			window.location.href ='${ctx}';
			//isOpen=false;
		} else {
			window.location.href ='${ctx}';
		}
	}, 'json');
})

$(function(){
	
	var totalWidth = 0, current = 1;
	
	$.each($('.nav').find('li'), function(){
		totalWidth += $(this).outerWidth();
	});
	
	$('.nav').width(totalWidth);
	
	function currentLeft(){
		return -(current - 1) * 93;	
	}
	
	$('.nav-btn a').click(function(e) {
		var tempWidth = totalWidth - ( Math.abs($('.nav').css('left').split('p')[0]) + $('.nav-wrap').width() );
        if($(this).hasClass('nav-prev-btn')){
			if( parseInt($('.nav').css('left').split('p')[0])  < 0){
				current--;
				Math.abs($('.nav').css('left').split('p')[0]) > 93 ? $('.nav').animate({'left': currentLeft()}, 200) : $('.nav').animate({'left': 0}, 200);
			}
		}else{

			if(tempWidth  > 0)	{
				
			   	current++;
				tempWidth > 93 ? $('.nav').animate({'left': currentLeft()}, 200) : $('.nav').animate({'left': $('.nav').css('left').split('p')[0]-tempWidth}, 200);
			}
		}
    });
	
	$.each($('.skin-opt li'),function(index, element){
		if((index + 1) % 3 == 0){
			$(this).addClass('third');	
		}
		$(this).css('background',$(this).attr('attr-color'));
	});
	
	$('.setting-skin').click(function(e) {
        $('.skin-opt').show();
    });
	
	$('.skin-opt').click(function(e) {
        if($(e.target).is('li')){
			alert($(e.target).attr('attr-color'));	
		}
    });
	
	$('.hd-top .user-info .more-info').click(function(e) {
       $(this).toggleClass('active'); 
	   $('.user-opt').toggle();
    });
	
	$('.logo-icon').click(function(e) {
         $(this).toggleClass('active'); 
	     $('.system-switch').toggle();
    });
	
	hideElement($('.user-opt'), $('.more-info'), function(current, target){

		$('.more-info').removeClass('active'); 
	});
	
	hideElement($('.skin-opt'), $('.switch-bar'));
	
	hideElement($('.system-switch'), $('.logo-icon'), function(current, target){

		$('.logo-icon').removeClass('active'); 
	});
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
</script>
</html>
