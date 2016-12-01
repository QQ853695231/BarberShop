
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link type="text/css" href="css/login.css" rel="stylesheet"/>
</head>
<script language="javascript">
	var validatecode;
	
	function mycheck()
 {
	   if(document.getElementById("username").value=="")
			   {  
					   alert("请输入用户名！"); 
					   return false;
			   }
	   if(document.getElementById("password").value=="")
		   {
		  		 alert("请输入密码！");
		   		return false;
		   }
	   if(document.getElementById("yzm").value=="")
		   {
		    alert("请输入验证码！");
		    return false;
		   }
	   if(!(document.getElementById("yzm").value == validatecode.trim()))
		   {
		   	alert("输入的验证码不正确！");
		   	return false;
		   }
	}
	function getValidateCode()
	{
		var xmlhr=getXMLHttpReq();
		xmlhr.open("get","user/getvalidatecode",true);
		xmlhr.send();
		xmlhr.onreadystatechange=function(){
			 if (xmlhr.readyState==4 && xmlhr.status==200)
			    {
				 validatecode=xmlhr.responseText;
				//alert("收到的验证码是："+validatecode);
			    }
		}
	}
	function getXMLHttpReq()
	{
		var xhr;
		if(window.XMLHttpRequest)
			{
				xhr=new XMLHttpRequest();
			}
		else
			{
			xhr=new ActiveXOjbect("Microwoft.XMLHTTP");
			}
		return xhr;
	}
	</script>
<body>
		<div class="frame">
			<form action="login" method="post"  name="form1" onSubmit="return mycheck()">
				<table >
				<tr>
				<% request.setCharacterEncoding("UTF-8");
						String info=	request.getParameter("info");
						if(info==null) info="";
				%>
				<%=info %>
				</tr>
				<tr>
					<td class="info" ><img src="images/user.png" width=20px height=20px/>&nbsp;用户名:</td>
					<td ><input type="text" name="userInfoFromBrowser.username" size=29  id="username"/></td>
				</tr>
				<tr>
					<td class="info"><img src="images/password.png" width=20px height=20px/>&nbsp;密 &nbsp;码:</td>
					<td><input type="password" name="userInfoFromBrowser.password" size=29  id="password"/></td>
					<td width=100><a href="">忘记密码</a></td>
				</tr> 
				<tr>
					<td class="info"><img src="images/pen.png" width=20px height=20px/>&nbsp;验证码:</td>
					<td><input type="text"  id="yzm" size="8" onfocus="getValidateCode()" />
					<img src="image" id="image" /><a href="javascript:;" onclick="document.getElementById('image').src='image?'+Math.random()">换一张</a>
					</td>
				</tr> 
				<tr class="role">
					<td class="info"><input type="radio" checked="checked" name="userInfoFromBrowser.power" value="管理员" class="in_role" />管理员</td>
					<td class="info"><input type="radio" name="userInfoFromBrowser.power" value="收银员" class="in_role"/>收银员</td>
				</tr>
				<tr>
					<td><input type="submit" value="登录" class="btn" /></td>
					<td><input type="button" value="重置" class="btn" onclick="document.form1.reset();"/></td>
  				</tr>
				</table>
			</form>
		</div>
	</body>
</html>