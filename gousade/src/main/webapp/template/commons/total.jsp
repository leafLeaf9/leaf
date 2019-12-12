<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
a
{
 cursor:pointer;
 color:blue;
}
table
{
  border-collapse:collapse;
}
table, td, th
{
  border:1px solid black;
}
.bg-nav{
	overflow: hidden;
	margin: 25px 0 10px 15px;
	>span{
		float: left;
		margin-right: 15px;
		color: #6DB4F9;
		cursor: pointer;
		img{
			float: left;
			margin: 2px 8px  0 0;
		}
		&+span{
			border-left: 1px solid #DFDFDF;
			padding-left: 15px;
		}
	}
}
.bg-nav>span{float:left;margin-right:15px;color:#6DB4F9;cursor:pointer}
    .row {
      font-size: 18px;
      color: #333;
      margin-top: 20px;
      margin-bottom: 20px;
    }
    .row_description {
      vertical-align: top;
      margin-left: 14%;
      display: inline-block;
      width: 22%;
    }
    .row_data {
      display: inline-block;
      width: 55%;
    }
    .danger {
      color: #F56C6C;
    }
</style>
 <script src="http://cdn.bootcss.com/jquery/1.12.2/jquery.js"></script>
 <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="./js/jquery-easyui-1.7.0/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="./js/jquery-easyui-1.7.0/themes/icon.css">
	<script type="text/javascript" src="./js/jquery-easyui-1.7.0/jquery.min.js"></script>
	<script type="text/javascript" src="./js/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="./js/jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
<!-- 	<script type="text/javascript" src="./js/jquery.color.js"></script> -->
	<script type="text/javascript" src="./js/jsUtil.js"></script>
<link rel="stylesheet" href="./img/icon-font/iconfont.css">
</head>
<body>
<%--ctxPath--%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
            		
<script>
//扩展treegrid，使其支持平滑数据格式
$.fn.treegrid.defaults.loadFilter = function(data, parentId) {
    var opt = $(this).data().treegrid.options;
    var idFiled, textFiled, parentField;
    if (opt.parentField) {
        idFiled = opt.idFiled || 'id';
        textFiled = opt.textFiled || 'text';
        parentField = opt.parentField;
        var i, l, treeData = [], tmpMap = [];
        for (i = 0, l = data.length; i < l; i++) {
            tmpMap[data[i][idFiled]] = data[i];
        }
        for (i = 0, l = data.length; i < l; i++) {
            if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
                if (!tmpMap[data[i][parentField]]['children'])
                    tmpMap[data[i][parentField]]['children'] = [];
                data[i]['text'] = data[i][textFiled];
                tmpMap[data[i][parentField]]['children'].push(data[i]);
            } else {
                data[i]['text'] = data[i][textFiled];
                treeData.push(data[i]);
            }
        }
        return treeData;
    }
    return data;
};
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

</script>
<script  src = "canvas-nest.js-2.0.1/dist/newnest.js"> </script>
</body>
</html>