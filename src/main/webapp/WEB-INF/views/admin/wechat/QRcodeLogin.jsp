<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/template/commons/total.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>QR code login</title>
    <%@ include file="/template/commons/basejs.jsp" %>
    <script src="${staticPath}/static/wechat/wxLogin.js"></script>
</head>
<body>
<div id="login_container"></div>
</body>
<script>
    $(function () {
        var obj = new WxLogin({
            self_redirect: false,
            id: "login_container",
            appid: "wx42f7dad2fb0b66c2",
            scope: "snsapi_login",
            redirect_uri: "http%3A%2F%2Fwww.yunshanggaosu.com%3A8088%2FGaoGuanTongServer%2Fcloud%2Findex.html",
            state: (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1),
            style: "",
            href: ""
        });
    });
</script>
</html>
