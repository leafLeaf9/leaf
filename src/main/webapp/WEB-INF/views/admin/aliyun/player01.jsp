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
    //支持播放地址播放,此播放优先级最高
    source : 'https://outin-8f9a83f902bb11eb912300163e1c9256.oss-cn-shanghai.aliyuncs.com/sv/12433228-17516301216/12433228-17516301216.mp4?Expires=1603181607&OSSAccessKeyId=LTAI8bKSZ6dKjf44&Signature=%2FD8Ys9zzeolmjFHDeYWoRw7xhXc%3D',
    //播放方式二：点播用户推荐
   /*  vid : '1e067a2831b641db90d570b6480fbc40',
    playauth : 'ddd',
    cover: 'http://liveroom-img.oss-cn-qingdao.aliyuncs.com/logo.png',
    encryptType:1, //当播放私有加密流时需要设置。
    //播放方式四：使用STS方式播放
    vid : '1e067a2831b641db90d570b6480fbc40',
    accessKeyId: 'dd',
    securityToken: 'dd',
    accessKeySecret: 'dd',
     region:'cn-shanghai',//eu-central-1,ap-southeast-1 */
    },function(player){
        console.log('播放器创建好了。')
   });
</script>
</body>
</html>