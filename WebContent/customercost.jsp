<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>顾客消费</title>
<script src="js/jquery-2.1.4.js"></script>
<script type="text/javascript">
	$(function(){
		$("#ensure").click(function(e) {
			if(parseInt($("#restmoney").val())<parseInt($("#total").val()))
			  {
				alert("可用余额不足,请充值！")
			  }
			else
				{
				$.get("vipusercost",{
					costdnum: $("#costnum").val(),
					consumername:$("#name").val(),
					costdnum:$("#costnum").val(),
					way:$("#way").val(),
					content:$("#content").val(),
					total:$("#total").val()
					
					},function(response,status)
					{
						if(status=="success")
							alert(response);
						else
							alert("存储失败！");
					});
				}
		 });
		
		$("#ensure2").click(function(){
			$.get("ordinarycost",
				{
				  way:$("#way2").val(),
				  content:$("#content2").val(),
				  total:$("#total2").val()
				},function(responsetxt,status){
					alert(responsetxt);
				});
			
		});
		$(".indiv").show();
		$(".cash").hide();
		$("#way").change(function(e) {
           $(".indiv").show();
		   $(".cash").hide(); 
        });
		
		$("#way2").change(function(e) {
            $(".indiv").hide();
			$(".cash").show();
        });
		
		$("#costnum").blur(function()
			{
				var number=$("#costnum").val();
				if(number.length==6 || number.length==11)
					{
					$.getJSON("consumer",
							{consumer_number:number},
							function(responsetxt){
									$("#level").val(responsetxt.card_level);
									$("#name").val(responsetxt.vip_name);
									$("#restmoney").val(responsetxt.card_balance);
								});
					}
					else
					{
						alert("号码输入不正确！");
						this.focus()
					}
				
			});
		
		});
	
</script>
<style type="text/css">
	.out{
			width:500px;
			height:400px;
			margin:50px auto;
						
		}
	.pspan{
			font-size:18px;
			padding-left:97px;
		}
	.indiv{
		 height:300px;
		 width:450px;
		 margin:auto;
		}
	.txtarea
		{
			float:right;
			margin-top:-228px;
			margin-right:8px;
			width:200px;
			height:200px;
		}
	.indiv .divspan{
			margin-top:25px;
			margin-bottom:25px;
			
		}
	.btn{
			margin-left:20px;
			width:121px;
			height:34px;
			border-radius:13px;
		}
	.cash{
		 height:300px;
		 width:450px;
		 margin:auto;
		
		}
	.content
		{
			margin-top:-13px;
			margin-left:8px;
			margin-right:8px;
			width:430px;
			height:200px;
		}
		
</style>
</head>
<body>
	<div class="out">
       <p class="pspan">
         <span>消费形式：</span>
         <span><input type="radio" name="way" value="card"  id="way" checked="checked"/>会员卡</span>
         <span><input type="radio"  name="way" value="money" id="way2"/>现金支付</span>
       </p>
       <div class="cash">
       		<p>
            	<textarea name="content" class="content" id="content2"></textarea>
            </p>
            <span>消费共计：<input type="number" id="total2"/> </span>
            <button type="button" id="ensure2" class="btn">确认消费</button>
       </div>
        <div class="indiv">
        	<p class="divspan">
           <span >会员卡号：<input type="text"  id="costnum"/></span>
           </p>
           <p class="divspan">
           <span >会员姓名：<input type="text" id="name"/></span>
           </p>
           <p class="divspan">
           <span>会员等级：<input type="text" id="level" disabled="disabled"/></span>
           </p>
           <p class="divspan">
           <span>可用余额：<input type="text" id="restmoney" disabled="disabled"/></span>
           </p>
           <p class="divspan">
           	<span>消费共计：<input type="number" id="total"/></span>
           </p> 
           <textarea name="content" class="txtarea" id="content"></textarea>
           <button type="button" id="ensure" class="btn">确认消费</button>
        </div>
        
    </div>
</body>
</html>