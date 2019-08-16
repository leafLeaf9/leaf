<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>设置申报规则</title>

  <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
 <script src="http://cdn.bootcss.com/jquery/1.12.2/jquery.js"></script>
 <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
  <script language="javascript" type="text/javascript" src="<%=request.getContextPath() %>/My97DatePicker/WdatePicker.js"></script>
  <script type="text/javascript">
   function login(){
	   	   
	   var webRootPath = '<%=request.getContextPath()%>';	  
	   var res = $("#form2").serializeArray();	 
	   var level=$("#level").val();
	   var text=$("#text").val();
	   var startdate=$("#startdate").val();
	   var stopdate=$("#stopdate").val();
	   
	   var qaram = {
				
				"level":level,
				"text":text,
				"startdate":startdate,
				"stopdate":stopdate
				
			};
	 /*  $.each(res, function(i, field){
			$("#success").append(field.name + ":" + field.value + " ");
		}); */
	   
       $.ajax({
            async: false,
            type: "POST",
            url:webRootPath+"/setrule",
            contentType : 'application/json;charset=UTF-8',
            data:JSON.stringify(qaram),
           
            dataType:'json',
            success: function (data) {
            	
            	// $("#temp_div").html(data);
            	//console.log(data);
            	//var newdata=jQuery.parseJSON(data);
            	
            	
            	
                /*  var strtr = '';
                 
                      strtr = '<tr>';
                      strtr += '<td>'+data.user_id+'</td>';
                      strtr += '<td>'+data.user_name+'</td>';
                      strtr += '<td>'+data.birthyear+'年'+data.birthmonth+'月'+data.birthday+'日'+'</td>';
                      strtr += '<td>'+data.mydatepicker+'</td></tr>';
                  $('#rightList').append(strtr);*/
             alert(data.result);

            

              },
            error: function () {
            	alert("提交失败！");	
            }
        })
        
   }
    </script> 
</head>
<body>

  <form id="form2" action="##" method="post">
  <table cellspacing="0" cellpadding="10" class="tfm" id="profilelist">
  <tr >
<th >项目类型:</th>
<td >
<select name="level" id="level">
	<option value=1>校类一级</option>
	<option value=2>校类二级</option>
</select>
</tr> 

<tr >
<th >申报规则内容:</th>
<td >
<textarea name="text" id="text" class="pt" rows="5" cols="40" tabindex="1"></textarea><div class="rq mtn" id="showerror_bio"></div><p class="d"></p></td>

</tr>  

<tr >
<th >开始时间</th>
<td >
<input type="text" name="startdate" id="startdate" onClick="WdatePicker()"   /></td>
</tr>

<tr >
<th >结束时间</th>
<td >
<input type="text" name="stopdate" id="stopdate" onClick="WdatePicker()"   /></td>
</tr>
</table>

<input type="button" value="提交" onclick="login()">
</form>
</body>
</html>