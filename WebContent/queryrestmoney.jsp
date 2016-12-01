<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="js/jquery-2.1.4.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>余额查询</title>
<script type="text/javascript">
	$(function(){
		$("#button").click(function(e) {
            var num=$("#number").val();
			$.get("queryrestmoney",
			{queryvipnum:num},function(responsetxt,status){
				if(status=="success" && responsetxt.length<=9)
				{
					$("#restmoney").val(responsetxt);
				}
				else
				{
					alert(responsetxt);	
					$("#restmoney").val(0);
				}
				});
        });
		
		
		});
	
</script>
</head>
<body>
<table>
	<tr>
   	  <td align="center">请输入要查询的会员卡号：</td>
        <td><input type="text" id="number"/></td>
    </tr>
    <tr>
    	<td>可用余额：</td>
        <td><input type="text" id="restmoney"/> </td>
    </tr>
    <tr>
    	<td colspan="2">
    	  <button type="button" id="button">查询余额</button>
  	  </td>
    </tr>
</table>
</body>
</html>