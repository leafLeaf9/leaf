<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/template/commons/total.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>aliyunPlayer01</title>
<link rel="stylesheet" href="https://g.alicdn.com/de/prismplayer/2.8.2/skins/default/aliplayer-min.css" />
<script charset="utf-8" type="text/javascript" src="https://g.alicdn.com/de/prismplayer/2.8.2/aliplayer-min.js"></script>
</head>
<body>
<div class="prism-player" id="J_prismPlayer"></div>
<script>
    var player = new Aliplayer({
    id: 'J_prismPlayer',
    width: '100%',
    autoplay: false,
    cover: 'http://liveroom-img.oss-cn-qingdao.aliyuncs.com/logo.png',
   	vid : '80573683a09e48b49755e205f450d13f',
    playauth : 'ddd',
    encryptType:1, //当播放私有加密流时需要设置。
    },function(player){
        console.log('播放器创建好了。')
   });
</script>
</body>
</html>