<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery-2.1.4.js"></script>
<script type="text/javascript">
	$(function(){
		 $("#addbtn").click(function(){
         	if( $("#realname").val().length==0 || $("#tel").val().length!=11 
         			 || $("#position").val=="")
             {
                 alert("内容填写有误,请检查！");
             }
         	
         	else{
             $.get("addemployee",{
            	 		realname:$("#realname").val(),
            	 		sex:$("#sex").val(),
            	 		 address:$("#address").val(),
                         password:$("#password").val(),
                         salary:$("#salary").val(),
                         position:$("#position").val(),
                         tel:$("#tel").val()
             },
                     function(responsetxt){
                         alert(responsetxt);
                         window.location.href="employeemanagement.html";
                     });
         	}
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
            <span>姓&nbsp;&nbsp;名：<input type="text" id="realname"/> 
            </span>
        </p>
        <p>
            <span>性&nbsp;&nbsp;别：<input type="text" id="sex"/> </span>
        </p>
        <p>
            <span>地&nbsp;&nbsp;址：<input type="text" id="address"/> 
            </span>
        </p>
        <p>
            <span>电&nbsp;&nbsp;话：<input type="text" id="tel"/> </span>
        </p>
        <p> <span>工&nbsp;&nbsp;资: <input type="text" id="salary"/> 
        </span>
        </p>
        <p>
                <span>职&nbsp;&nbsp;位：<select id="position">
                    <option value="理发师">理发师</option>
                    <option value="收银员">收银员</option>
                    <option value="管理员">管理员</option>
                </select>
            </span>
        </p>
        <div>
        
        <p> <span>密&nbsp;&nbsp;码：<input type="text" id="password"> 
            </span>
        </p>
        </div>
        <p>
            <button type="button" id="addbtn" class="btn">确认添加</button>
        </p>
    </div>
    </body>
</html>