<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="js/jquery-2.1.4.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
	$(function(){
		$("#recharge").click(function(e) {
            $.get("viprecharge",
			{
			  vipRechargeNum:$("#vipRechargeNum").val(),
			  amout:$("#amout").val()
			},
			function(responsetxt,status){
				if(status=="success")
					alert(responsetxt);
		    }
		);
      });
		
		$("#vipRechargeNum").blur(function(e) {
            if($("#vipRechargeNum").val()=="")
			{
				alert("请输入充值卡号！");
				$("#vipRechargeNum").focus();
			}
        });
		
		$("#amout").blur(function(e) {
            if($("#amout").val()<0)
			{
				alert("充值金额不正确！");
				$("#amout").focus();
			}
        });
		
		
		
		
	});
</script>
<title>会员充值</title>
</head>
	
<body>
<table>
    	<tr>
        	<td>请输入会员卡号：</td>
        	<td><input type="number" name="vipRechargeNum" id="vipRechargeNum" />
            </td>
        </tr>
        <tr>
        	<td>请输入充值金额：</td>
        	<td><input type="number" name="amout" id="amout" />
            </td>
        </tr>
        <tr>
        	<td colspan="2" align="center">
            	<button id="recharge">确认充值</button>
            </td>
        </tr>
    </table>
</body>
</html>