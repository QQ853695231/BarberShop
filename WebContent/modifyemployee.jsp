<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery-2.1.4.js"></script>
<script type="text/javascript">

		$(function(){
		
		$.getJSON("willbeedit"
                    ,function(responsetxt){
                            $("#modifyname").val(responsetxt.realname);
                            $("#modifypassword").val(responsetxt.password);
                            $("#modifyusername").val(responsetxt.username);
                            $("#modifyposition").val(response.position);
                    });
		
		
		
		});


	 
</script>
<style type="text/css">
        .toolbar{
            width: 410px;
            height: 50px;
            margin: auto auto;
        }
        .add{
            width:600px;
            height: 400px;
            margin: auto auto;
            border:1px solid #FF0000;
        }
        .span{
            font-size: 25px;
            font-weight: bold;
        }
        .inneradd
        {
            width:300px;
            height:300px;
            margin-left:180px;
            font-size:25px;
        }
      
       .btn{
       
       		width:100px;
       		height:30px;
       		border-radius:10px;
       }
       .btn:hover{
       		background:blue;
       }
    </style>
</head>
<body>
<div class="inneradd">
        <p>
            <span>姓&nbsp;&nbsp;名：<input type="text" id="modifyname"/> 
            </span>
        </p>
        <p>
            <span>性&nbsp;&nbsp;别：<input type="text" id="modifysex"  > </span>
        </p>
        <p>
            <span>地&nbsp;&nbsp;址：<input type="text" id="modifyaddress"> 
            </span>
        </p>
        <p>
            <span>电&nbsp;&nbsp;话：<input type="text" id="modifytel" maxlength="11" > </span>
        </p>
        <p> <span>工&nbsp;&nbsp;资: <input type="text" id="modifysalary"> 
        </span>
        </p>
        <p>
                <span>职&nbsp;&nbsp;位：<select id="modifyposition">
                    <option value="理发师">理发师</option>
                    <option value="收银员">收银员</option>
                    <option value="管理员">管理员</option>
                </select>
            </span>
        </p>
        <div>
        <p> <span>用户名：<input type="text" id="modifyusername"> 
            </span>
        </p>
        <p> <span>密&nbsp;&nbsp;码：<input type="text" id="modifypassword"> 
            </span>
        </p>
        </div>
        <p>
            <button type="button" id="modifybtn" class="btn">确认修改</button>
        </p>
    </div>
    </body>
</html>