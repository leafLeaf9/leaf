<%--标签 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="shortcut icon" href="${staticPath }/static/sn/style/images/favicon.ico" />
<!-- DataTables -->
<link rel="stylesheet" href="${staticPath }/static/AdminLTE-2.4.2/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
<noscript>
	<p>本页面需要浏览器支持（启用）JavaScript!</p>	
</noscript>	
<!-- jQuery 3 -->
<script src="${staticPath }/static/AdminLTE-2.4.2/bower_components/jquery/dist/jquery.min.js"></script>
<%-- [扩展样式] --%>
<link rel="stylesheet" type="text/css" href="${staticPath }/static/css/zhbasic.css" />
<script defer="defer" src="${staticPath}/static/lac/json2.js"></script>
<script defer="defer" src="${staticPath}/static/lac/LAC-1.0.0.js"></script>
<script defer="defer" src="${staticPath}/static/lac/hashmap.js"></script>
<!-- [adminlte] -->
<link rel="stylesheet" type="text/css" href="${staticPath }/static/AdminLTE-2.4.2/bower_components/bootstrap/dist/css/bootstrap.min.css" />
<!-- Bootstrap 3.3.7 -->
<script defer="defer" src="${staticPath }/static/AdminLTE-2.4.2/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${staticPath }/static/AdminLTE-2.4.2/bower_components/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${staticPath }/static/AdminLTE-2.4.2/bower_components/Ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="${staticPath }/static/AdminLTE-2.4.2/dist/css/AdminLTE.min.css">
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<!-- <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic"> -->
<!-- DataTables -->
<script defer="defer" src="${staticPath }/static/AdminLTE-2.4.2/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script defer="defer" src="${staticPath }/static/AdminLTE-2.4.2/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- responsive -->
<%-- <link rel="stylesheet" href="${staticPath }/static/adminlte/plugins/datatables/extensions/Responsive/css/responsive.dataTables.min.css">
<script defer="defer" src="${staticPath }/static/adminlte/plugins/datatables/extensions/Responsive/js/dataTables.responsive.min.js"></script>
 --%><!-- FastClick -->
<script defer="defer" src="${staticPath }/static/AdminLTE-2.4.2/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script defer="defer" src="${staticPath }/static/AdminLTE-2.4.2/dist/js/adminlte.min.js"></script>
<link rel="stylesheet" href="${staticPath }/static/AdminLTE-2.4.2/dist/css/skins/_all-skins.min.css">
<!-- Select2 -->
<link rel="stylesheet" href="${staticPath }/static/AdminLTE-2.4.2/bower_components/select2/dist/css/select2.min.css">
<script defer="defer" src="${staticPath }/static/AdminLTE-2.4.2/bower_components/select2/dist/js/select2.full.min.js"></script>
<script defer="defer" src="${staticPath }/static/AdminLTE-2.4.2/bower_components/select2/dist/js/select2tree.js"></script>
<!-- PACE -->
<link rel="stylesheet" href="${staticPath }/static/AdminLTE-2.4.2/plugins/pace/pace.min.css">
<script defer="defer" src="${staticPath }/static/AdminLTE-2.4.2/bower_components/PACE/pace.min.js"></script>
<!-- ZTREE -->
<link rel="stylesheet" href="${staticPath }/static/ztree/zTreeStyle.css" type="text/css">
<script defer="defer" type="text/javascript" src="${staticPath }/static/ztree/jquery.ztree.core-3.5.js"></script>
<!-- layer -->
<script defer="defer" type="text/javascript" src="${staticPath }/static/layer/layer.js"></script>
<!-- treegrid -->
<link rel="stylesheet" href="${staticPath }/static/treegrid/bootstrap-table.css" type="text/css">
<script defer="defer" type="text/javascript" src="${staticPath }/static/treegrid/bootstrap-table.js"></script>
<script defer="defer" type="text/javascript" src="${staticPath }/static/treegrid/bootstraptable-treeview.js"></script>
<script defer="defer" type="text/javascript" src="${staticPath }/static/js/base64.min.js"></script>
<!-- dtree 授权树 -->
<link rel="stylesheet" href="${staticPath }/static/dtree/dtree.css" type="text/css">
<script defer="defer" type="text/javascript" src="${staticPath }/static/dtree/dtree.js"></script>
<!-- timepicker daterangepicker-->
<%-- <link rel="stylesheet" href="${staticPath }/static/AdminLTE-2.4.2/plugins/timepicker/bootstrap-timepicker.min.css" type="text/css">
<script defer="defer" type="text/javascript" src="${staticPath }/static/AdminLTE-2.4.2/plugins/timepicker/bootstrap-timepicker.min.js"></script> --%>
<%-- <script defer="defer" type="text/javascript" src="${staticPath }/static/AdminLTE-2.4.2/bower_components/moment/moment.js"></script> --%>
<%-- <link rel="stylesheet" href="${staticPath }/static/AdminLTE-2.4.2/bower_components/bootstrap-daterangepicker/daterangepicker.css" type="text/css"> --%>
<%-- <script defer="defer" type="text/javascript" src="${staticPath }/static/AdminLTE-2.4.2/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script> --%>
<!--bootstrapValidator 表单验证  -->
<link rel="stylesheet" href="${staticPath }/static/AdminLTE-2.4.2/bower_components/bootstrapvalidator-master/dist/css/bootstrapValidator.css" type="text/css">
<script defer="defer" type="text/javascript" src="${staticPath }/static/AdminLTE-2.4.2/bower_components/bootstrapvalidator-master/dist/js/bootstrapValidator.js"></script>

<!-- 序列化表单 -->
<script defer="defer" type="text/javascript" src="${staticPath }/static/js/jsUtil.js"></script>
<!-- jquery form 插件 -->
<script defer="defer" type="text/javascript" src="${staticPath }/static/js/jquery.form.min.js"></script>
<!-- icheck -->
<link rel="stylesheet" href="${staticPath }/static/AdminLTE-2.4.2/plugins/iCheck/square/blue.css" type="text/css">
<script defer="defer" type="text/javascript" src="${staticPath }/static/AdminLTE-2.4.2/plugins/iCheck/icheck.min.js"></script>

<!-- wysihtml 富文本编辑 --> 
<link rel="stylesheet" href="${staticPath }/static/summernote-master/dist/summernote.css" type="text/css">
<script defer="defer" type="text/javascript" src="${staticPath }/static/summernote-master/dist/summernote.min.js"></script>
<script defer="defer" type="text/javascript" src="${staticPath }/static/summernote-master/dist/lang/summernote-zh-CN.min.js"></script>
<!-- echarts  -->
<script defer="defer" src="${staticPath }/static/echarts/echarts.min.js"></script>
<!-- datetimepicker -->
<link rel="stylesheet" href="${staticPath }/static/AdminLTE-2.4.2/bower_components/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css">
<script defer="defer" type="text/javascript" src="${staticPath }/static/AdminLTE-2.4.2/bower_components/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.min.js"></script>
<script defer="defer" type="text/javascript" src="${staticPath }/static/AdminLTE-2.4.2/bower_components/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
