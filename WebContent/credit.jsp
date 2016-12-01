<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>积分管理</title>
<script src="js/jquery-2.1.4.js"></script>
<script type="text/javascript">
	$(function(){
		$("#creditbtn").attr("disabled","disabled");
		$("#creditbtn").click(function(){
			$.get("creditexchange",{
					creditnum:$("#cardnum").val(),
					exchangegoods:$("#exchange").val(),
					realcredit:$("#realcredit").val()
				},function(responsetxt){
						alert(responsetxt);
					}
				
			
			);
		});
	    $("#cardnum").blur(function(){
				$.getJSON("consumer",
							{consumer_number:$("#cardnum").val()},
							function(responsetxt)
								{
								  $("#availablecredit").val(responsetxt.card_score);
								});
			});
	    
			$("#realcredit").blur(function(){
					if($("#realcredit").val()> 0 && parseInt($("#availablecredit").val()) > parseInt($("#realcredit").val()) )
						{
							 $("#creditbtn").removeAttr("disabled").html("确认兑换");
						}
					else
						{
						 	 $("#creditbtn").attr("disabled","disabled").html("可用积分不足足或者有负值 ！");
						}
					
			});
				
		});
	
</script>
<style type="text/css">
	.out{
		height:500px;
		width:500px;
		border:1px solid #F00;
		margin:auto auto;
		}
	.title{
			font-size:30px;
			font-weight:bold;
			color:#F00;
			text-align:center;			
		}
	.btn{
		margin-left:83px;
		width:186px; 
		height:40px;
		 border-radius:14px;
		}
	.txtarea{
		margin-left:24px;
		width: 143px;
   		height: 98px;
		}
	
</style>
</head>

<body>
	<div class="out">
    	<p class="title">
        	<span>会员积分消费</span>
        </p>
        <div align="center">
        <p>
        	<span>消费会员卡号：<span><input type="text" id="cardnum" /></span></span>
        </p>
        <p>
        	<span>会员可用积分：<input type="text" id="availablecredit" /></span>
        </p>
        	<span class="exchange">兑换物品：</span>
        <span><textarea  id="exchange" class="txtarea" ></textarea></span>
        <p>
        	<span>物品价值积分：<input type="text" id="realcredit" /></span>
        </p>
        <p>
        	<span><button type="button" id="creditbtn" class="btn">确认消费</button></span>
        </p>
        </div>
    </div>
</body>
</html>