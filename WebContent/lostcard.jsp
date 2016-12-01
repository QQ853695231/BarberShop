<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员卡挂失</title>
<script src="js/jquery-2.1.4.js"></script>
<script type="text/javascript">
	$(function(){
		$("#lost").attr("disabled","disabled");
		$("#lostname,#phone").change(function(e)
		 	{
            	if($("#lostname").val()!="" && $("#phone").val().length==11)
					$("#lost").removeAttr("disabled");
				else
					$("#lost").attr("disabled","disabled");
       	    }
		  );
		$("#lost").click(function(e) {
           $.get(   "lostcard",
		   			{
					  lostname:$("#lostname").val(),
					  lostphone:$("#phone").val()
					},
					function(response,status)
					{
			   				alert(response);
			   		}
			  	); 
        });	
	});
</script>
<style type="text/css">
 .out{
	 	width:500px;
		height:500px;
		margin:auto;
		border:1px solid #C3F;
	 }
  .tips{
	  	font-size:20px;
		font-weight:400;
		font-family:Arial, Helvetica, sans-serif;
		margin-left:37px;
		color:#F00;
	  }
  .indiv{
	  	width:250px;
		height:250px;
	  	margin:auto;
		color:#F00;
	  }
   .lostbtn{
	   	font-size:17px;
		font-weight:bold;
		margin-left:2px;
		width:210px;
		height:30px;
	   }
</style>
</head>
	
<body>
	<div class="out">
    	<p class="tips">
    		<span>请输入办理会员时的姓名与手机号，以便挂失！</span>
    	</p>
        <div class="indiv">
        	<p>
        		<span>姓&nbsp;&nbsp;名：<input type="text" id="lostname" />*</span>
        	</p>
        	<p>
        		<span>手机号：<input type="number" id="phone" />*</span>
        	</p>
       	     <p>
        	<button type="button" id="lost" class="lostbtn">挂失并重新生成会员号</button>
             </p>
        </div>
    	
        
    </div>
</body>
</html>