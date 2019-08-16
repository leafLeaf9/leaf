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
<meta charset="UTF-8">
<title>评论文本情感分析</title>
<style>
a{
 cursor:pointer;
 color:blue;
}
#label{
display:none;
}
table
  {
  border-collapse:collapse;
  }
  table, td, th
  {
  border:1px solid black;
  }


  input{
  cursor:pointer;
  }
  input:hover
{
background-color:skyblue;
}

 
</style>

  <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
 <script src="http://cdn.bootcss.com/jquery/1.12.2/jquery.js"></script>
 <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
  <script language="javascript" type="text/javascript" src="<%=request.getContextPath() %>/My97DatePicker/WdatePicker.js"></script>
  

</head>
<body>
<% String name = (String)session.getAttribute("u"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br/>
		<div id="expert" align="center">
		<table id="tab">
		
<tbody><tr id="tr1">
<th id="td1">评论文本:</th>
<td id="td2">
<textarea  id="comment" class="pt" rows="5" cols="40" tabindex="1" style="margin: 0px; width: 858px; height: 68px;"></textarea></td>

</tr>
<tr id="tr2">
<th id="td3">分词结果:</th>
<td id="td4">
<textarea  id="fenciresult" class="pt" rows="5" cols="40" tabindex="1" style="margin: 0px; width: 858px; height: 100px;"></textarea></td>
</tr>
<!-- <tr > -->
<!-- <th >情感词提取结果:</th> -->
<!-- <td > -->
<!-- <textarea  id="extractresult" class="pt" rows="5" cols="40" tabindex="1" style="margin: 0px; width: 1000px; height: 50px;"></textarea></td> -->
<!-- </tr> -->

<!-- <tr > -->
<!-- <th >公式转换结果:</th> -->
<!-- <td > -->
<!-- <textarea  id="formresult" class="pt" rows="5" cols="40" tabindex="1" style="margin: 0px; width: 1000px; height: 50px;"></textarea></td> -->
<!-- </tr> -->

<!-- <tr > -->
<!-- <th >情感计算结果:</th> -->
<!-- <td > -->
<!-- <textarea  id="calresult" class="pt" rows="5" cols="40" tabindex="1" style="margin: 0px; width: 1000px; height: 50px;"></textarea></td> -->
<!-- </tr> -->
</tbody></table>
<br/>
<table id="total"  border="0" cellpadding="0" cellspacing="0"><tr>
<td><table id="origin" >
<tr ><th>分句队列</th></tr>
<tbody id="originlist"></tbody>
</table></td>
<td><table id="word" >
<tr><th>情感词队列</th></tr>
<tbody id="wordlist"></tbody>
</table></td>
<td><table id="form" >
<tr><th>公式队列</th></tr>
<tbody id="formlist"></tbody>

</table></td>
<td><table id="cal" >
<tr><th>分句情感强度</th></tr>
<tbody id="callist"></tbody>
<!-- <tr><td id="res1"></td></tr> -->
</table></td>
</tr></table>
<table><br />
<!-- <tr> -->
<!-- <th>动态情感词强度</th> -->
<!-- <td id="dynamiclist"></td> -->
<!-- </tr> -->
</table>
<table id="res">
<tr>
<th>整条评论情感强度</th>
<td id="reslist"></td>
</tr>
</table>

       <input type="button" style=" width: 135.96px; height: 24.4px;" value="加载评论" onclick="loadcomment()">  
       <input type="button" style=" width: 135.96px; height: 24.4px;" value="消除空格" onclick="replaceblank()">  
       <input type="button" style=" width: 135.96px; height: 24.4px;" value="中文分词" onclick="Segment()">  
       <input type="button" style=" width: 135.96px; height: 24.4px;" value="提取情感词" onclick="extract()">        
       <input type="button" style=" width: 135.96px; height: 24.4px;" value="文本转换为公式" onclick="selectform()">  
       <input type="button" style=" width: 135.96px; height: 24.4px;" value="情感强度计算" onclick="calculate()">  
		</div>
		<script>
		var test=null;
		var fencilist=null;//分词并标注词性之后的list
		var extractlist=null;//提取情感词之后的list
		var formlist=null;//转换成公式之后的list
		var index=0;
		var resultvalue=0.0;
		
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
var user_id='<%=name %>';
$("#total").hide();$("#res").hide();$("#form").hide();$("#cal").hide();
// $("#origin").hide();$("#word").hide();$("#form").hide();$("#cal").hide();$("#res").hide();

function  valuetostar(value) {
	var star="";
	if(value>0.0&&value<=0.2) star="★";
	if(value>0.2&&value<=0.4) star="★★";
	if(value>0.4&&value<=0.6) star="★★★";
	if(value>0.6&&value<=0.8) star="★★★★";
	if(value>0.8&&value<=1.0) star="★★★★★";
	if(value>1.0) star="★★★★★★";
	if(value>-0.2&&value<=-0.0) star="☆";
	if(value>-0.4&&value<=-0.2) star="☆☆";
	if(value>-0.6&&value<=-0.4) star="☆☆☆";
	if(value>-0.8&&value<=-0.9) star="☆☆☆☆";
	if(value>-1.0&&value<=-0.8) star="☆☆☆☆☆";
	if(value<-1.0) star="☆☆☆☆☆☆";
	return star;
}

function  loadcomment(){
	  var webRootPath = '<%=request.getContextPath()%>';	
	  var qaram = {				
				"index": index,				
				};
	  $.postJSON(webRootPath + "/querycomments", qaram, function(data) {
          if (data) {
        	  $.each(data, function(index, value) {
        		  document.getElementById("comment").value=value.comment;
        		  $("#fenciresult").val("");
//         		  $("#extractresult").val("");
//         		  $("#formresult").val("");
//         		  $("#calresult").val("");
                  var trs="";
        		  $("#originlist").html(trs);
        		  $("#wordlist").html(trs);
        		  $("#formlist").html(trs);
        		  $("#callist").html(trs);
        		  $("#reslist").html(trs);
        		  $("#total").hide();$("#res").hide();$("#form").hide();$("#cal").hide();
				});
			
			
			
			index++;
			 
			
		}else{
			alert("操作失败！");
		}
	});
	  }
function  replaceblank(){
	var str=$("#comment").val();
	str=str.replace(/ /g, "，");
	 document.getElementById("comment").value=str;
}
  function  Segment(){
	  var webRootPath = '<%=request.getContextPath()%>';	
		
		var comment=$("#comment").val();
		
		 
			var qaram = {
					
				"comment": comment,
				
				};
			$.postJSON(webRootPath + "/segment", qaram, function(data) {
	              if (data) {
					
					//alert(data.result);
 					document.getElementById("fenciresult").value=data.result;
					
					//$("#comment").html(data.result);
					 fencilist=data.list;
					
					
				}else{
					alert("操作失败！");
				}
			});
  }
  
  function  extract(){
	  if(fencilist == "" || fencilist == undefined || fencilist == null){
		  alert("还未进行分词，无法提取情感词，请按照页面规则进行操作！"); 
		  return false;
	  }
	  var webRootPath = '<%=request.getContextPath()%>';
	  
	  $.postJSON(webRootPath + "/extract", fencilist, function(data) {
          if (data) {
        	  console.log(data.result);
//         	  document.getElementById("extractresult").value=data.result;        	  
        	  extractlist=data.list;
        	  resultvalue+=data.resultvalue;
        	  console.log(resultvalue);
//         	  $("#dynamiclist").html(resultvalue+valuetostar(resultvalue));
        	  var trs = "";
        	  var trs1 = "";
        	  $.each(data.result, function(index, value) {
					trs += "<tr>"					
							
							+"<td >"+value+"</td>"
							
						+"</tr>";
				});
        	  $.each(data.originresult, function(index, value) {
					trs1 += "<tr>"					
							
							+"<td >"+value+"</td>"
							
						+"</tr>";
				});
				 
				 $("#wordlist").html(trs);
				 $("#originlist").html(trs1);
				 $("#total").show();$("#origin").show();$("#word").show();
				 

		}else{
			alert("操作失败！");
		}
	});
  }
  
  function  selectform(){
	  if(extractlist == "" || extractlist == undefined || extractlist == null){
		  alert("还未进行情感词提取，无法进行公式转换，请按照页面规则进行操作！"); 
		  return false;
	  }
	  var webRootPath = '<%=request.getContextPath()%>';
	  
	  $.postJSON(webRootPath + "/selectform", extractlist, function(data) {
          if (data) {
        	  console.log(data.result);
//         	  document.getElementById("formresult").value=data.result;
        	  formlist=data.list; 
        	  var trs = "";        	 
        	  $.each(data.result, function(index, value) {
					trs += "<tr>"					
							
							+"<td >"+value+"</td>"
							
						+"</tr>";
				});
        	  $("#formlist").html(trs);
        	  $("#form").show();
		}else{
			alert("操作失败！");
		}
	});
  }
  
  function  calculate(){
	  if(formlist == "" || formlist == undefined || formlist == null){
		  alert("还未进行公式转换，无法进行情感强度计算，请按照页面规则进行操作！"); 
		  return false;
	  }
	  var webRootPath = '<%=request.getContextPath()%>';
	  var qaram = {
				
				"comment": $("#comment").val(),
				"formlist":formlist,
				};
	  $.postJSON(webRootPath + "/calculate", qaram, function(data) {
          if (data) {
        	  console.log(data.resultstr);
        	  resultvalue+=data.resultvalue;
        	  resultvalue=Math.round(resultvalue*10000)/10000;
        	  console.log(data.resultvalue);
        	  console.log(resultvalue);
//         	  document.getElementById("calresult").value=data.resultstr;
        	  var trs = "";
        	  var trs1 = "";
        	  $.each(data.result, function(index, value) {
					trs += "<tr>"					
							
							+"<td >"+value+"</td>"
							
						+"</tr>";
				});
				 
				 $("#callist").html(trs);		
				 $("#reslist").html(resultvalue+valuetostar(resultvalue)); 
				 $("#res1").html(resultvalue+valuetostar(resultvalue)); 
				 resultvalue=0.0; 
				 $("#cal").show();$("#res").show();
		}else{
			alert("操作失败！");
		}
	});
  }

  </script>
</body>
</html>