<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>填写项目申请书</title>

  <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
 <script src="http://cdn.bootcss.com/jquery/1.12.2/jquery.js"></script>
 <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
  <script language="javascript" type="text/javascript" src="<%=request.getContextPath() %>/My97DatePicker/WdatePicker.js"></script>
  <script type="text/javascript">
   function login(){
	   	   
	   var webRootPath = '<%=request.getContextPath()%>';	  
	   var res = $("#form2").serializeArray();	
	   var proj_id=$("#proj_id").val();
	   var level=$("#level").val();
	   var proj_name=$("#proj_name").val();
	   var proj_user=$("#proj_user").val();
	   var phonenum=$("#phonenum").val();
	   var email=$("#email").val();
	   var major=$("#major").val();
	   var members=$("#members").val();
	   var promise=$("#promise").val();
	   var qaram = {
			   "proj_id":proj_id,
				"level":level,
				"proj_name":proj_name,
				"user_id":proj_user,
				"phone":phonenum,
				"email":email,
				"major":major,
				"members":members,
				"promise":promise
				
			};
	 /*  $.each(res, function(i, field){
			$("#success").append(field.name + ":" + field.value + " ");
		}); */
	   
       $.ajax({
            async: false,
            type: "POST",
            url:webRootPath+"/declare",
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
<% String name = (String)session.getAttribute("u"); %>
 <form id="form2" action="##" method="post">
  <table cellspacing="0" cellpadding="10" class="tfm" id="profilelist">
  <tr >
<th >项目id:</th>
<td >
<input type="text" name="proj_id" id="proj_id" class="px" value="" tabindex="1" /></td>
</tr>
  <tr >
<th >申报级别:</th>
<td >
<select name="level" id="level" class="ps" tabindex="1"><option value="0" selected="selected">请选择</option><option value="1">校级一类</option><option value="2">校级二类</option></select></td>
  </tr>
<tr >
<th >项目名称:</th>
<td >
<input type="text" name="proj_name" id="proj_name" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >项目负责人:</th>
<td >
<input type="text" name="proj_user" id="proj_user" class="px" value="<%=name %>" tabindex="1" /></td>
</tr>
<tr >
<th >手机号码:</th>
<td >
<input type="text" name="phonenum" id="phonenum" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >电子邮件:</th>
<td >
<input type="text" name="email" id="email" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >所属专业:</th>
<td >
<input type="text" name="major" id="major" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >项目成员(多个):</th>
<td >
<input type="text" name="members" id="members" class="px" value="" tabindex="1" /></td>
</tr>
<tr >
<th >立项承诺:</th>
<td >
<input type="text" name="promise" id="promise" class="px" value="" tabindex="1" /></td>
</tr>
</table>
<input type="button" value="提交" onclick="login()">

</form>
</body>
</html>