<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="js/jquery-2.1.4.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员办理</title>
<script type="text/javascript">
$(function() {
	$("#transact").click(function(){
		$.get("registervip",
		{
			newVipvip_number:$("#number").val(),
			newVipvip_name:$("#name").val(),
			newVipvip_phone:$("#phone").val(),
			newVipcard_balance:$("#balance").val(),
			newVipcard_level:$("#level").val()
		},function(response){
			alert(response);
		});
	});
  $("#btn").click(function(){
	  	$.get("getcardnum",function(num,status){
			if(status=="success")
				{ 
				   $("#number").val(num);
				}
			});
	  });
  $("#name").blur(function(){
	    if($("#name").val()=="")
			{
				alert("请输入会员姓名！");
				$("#name").focus();
			}
	  });
	$("#phone").blur(function() {
        if($("#phone").val()==""||$("#phone").val().length!=11)
			{
				alert("手机号有误，请重新输入！");
				$("#phone").focus();
			}
    });
});
</script>
</head>
<body>

	<table align="center">
    	<tr>
           <td>卡号：</td>
           <td><input type="text" name="newVipvip_number" id="number" /></td>
           <td><button type="button" id="btn">生成卡号</button></td>
        </tr>
    	<tr>
        	<td>会员姓名：</td>
            <td><input type="text" name="newVipvip_name" id="name"/><span>*</span></td>
        </tr>
        <tr>
        	<td>会员手机号：</td>
            <td><input type="text" name="newVipvip_phone" id="phone"/><span>*</span></td>
        </tr>
        <tr>
        	<td>充值金额：</td>
            <td><input type="text" name="newVipcard_balance" id="balance" /></td> 
        </tr>
        <tr>
        	<td>会员级别：</td>
            <td><select name="newVipcard_level" id="level">
            	<option value="金卡" >金卡</option>
                <option value="银卡" >银卡</option>
                <option value="铜卡">铜卡</option> 
               </select>
          </td>
          
        </tr>
        <tr><td><button type="button" id="transact">办理</button></td></tr>
    </table>
</body>
</html>