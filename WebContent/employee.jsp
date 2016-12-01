<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>员工管理</title>
    <script src="js/jquery-2.1.4.js"></script>
    <script type="text/javascript">
        $(function(){
        	$(".innerdelete ,.innermodify").hide();
        	$("#add").click(function(){
        		$(".inneradd").show();
        		$(".innerdelete ,.innermodify").hide();
        	});
        	
        	$("#modify").click(function(){
        		$(".innermodify").show();
        		$(".innerdelete ,.inneradd").hide();
        	});
        	
        	$("#delete").click(function(){
        		$(".innerdelete").show();
        		$(".inneradd ,.innermodify").hide();
        	});
            $("#modifybtn").click(function(){
                $.get("modifyemployee",{
                    modifyphone:$("#modifyphone").val(),
                    modifyname:$("#modifyname").val(),
                    modifypassword:$("#modifypassword").val(),
                    modifyusername:$("#modifyusername").val(),
                    modifyauthority:$("#modifyauthority").val()
                }, function (responsetxt) {
                    alert(responsetxt);
                    window.location.href="vipmanagement.html";
                });


            });


            $("#modifyphone").blur(function () {
                if( $("#modifyphone").val().length!=11)
                {
                    alert("请输入正确的手机号！");
                    $("#modifyphone").focus;
                }
                else{
                    $.getJSON("queryemployee",{
                    	querynum:$("#modifyphone").val()

                    },function(responsetxt){
                        $.each(responsetxt,function(i,item){
                            $("#modifyname").val(item.realname);
                            $("#modifypassword").val(item.password);
                            $("#modifyusername").val(item.username);
                            $("#modifyauthority").val(item.power);
                        })

                    });
                }


            });
            $("#addbtn").click(function(){
            	if( $("#employeename").val().length==0 || $("#phone").val().length!=11 || $("#username").val()==""
            			|| $("#password").val()=="" || $("#authority").val=="")
                {
                    alert("内容填写有误,请检查！");
                }
            	
            	else{
                $.get("addemployee",{
                            employeename:$("#employeename").val(),
                            phone:$("#phone").val(),
                            username:$("#username").val(),
                            password:$("#password").val(),
                            power:$("#authority").val()	},
                        function(responsetxt){
                            alert(responsetxt);
                            window.location.href="vipmanagement.html";
                        });
            	}
            });
            
            
            $("#querynum").blur(function(){
                $("#deletename").val("");
                if( $("#querynum").val().length!=11)
                {
                    alert("请输入正确的手机号！");
                    $("#querynum").focus;
                }
                else
                {
                    $.getJSON("queryemployee",{
                        querynum:$("#querynum").val()

                    },function(responsetxt){
                        $.each(responsetxt,function(i,item){
                            $("#deletename").val(item.realname);

                        })

                    });

                }
            });

            $("#delbtn").click(function(){

                if($("#deletename").val()=="")
                {
                    alert("号码不存在！")
                }
                else
                {
                    $.get("deleteemployee",{delnum:$("#queryphone").val()},function(responsetxt)
                    {
                        alert(responsetxt);
                        window.location.href="vipmanagement.html";
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
            width:400px;
            height:300px;
            margin-left:180px;
            font-size:30px;
        }
        .innerdelete{
            width:400px;
            height:300px;
            margin-left:180px;
            font-size:30px;
        }
        .innermodify{
            width:400px;
            height:300px;
            margin-left:180px;
            font-size:30px;
            
        }
       .btn{
       
       		width:100px;
       		height:50px;
       		border-radius:10px;
       }
       .btn:hover{
       		background:blue;
       }
    </style>
</head>
<body>
<div class="toolbar">
    <p class="span">
        <span><input type="radio"   name="tool" id="add" checked="checked"/>添加员工 </span>
        <span><input type="radio"   name="tool" id="modify"/>修改信息 </span>
        <span><input type="radio"   name="tool" id="delete"/>删除员工 </span>
    </p>
</div>
<div class="add">
    <div class="inneradd">
        <p>
            <span>员工姓名：<input type="text" id="employeename"/> </span>
        </p>
        <p>
            <span>手 机 号：<input type="text" id="phone" maxlength="11" > </span>
        </p>
        <p>
            <span>用 户 名：<input type="text" id="username"> </span>
        </p>
        <p>
            <span>登录密码：<input type="text" id="password"> </span>
        </p>
        <p>
                <span>权&nbsp;&nbsp;&nbsp;&nbsp;限：<select id="authority">
                    <option value="收银员">收银员</option>
                    <option value="管理员">管理员</option>
                </select>
            </span>
        </p>
        <p>
            <button type="button" id="addbtn" class="btn">确认添加</button>
        </p>
    </div>
    <div class="innerdelete">
        <span>员工手机号：<input type="text" id="querynum" maxlength="11"/></span>
        <br>
        <br>
        <span>员工姓名：&nbsp;</span><span><input type="text" id="deletename" disabled="disabled" /></span>
        <br>
        <br>
        <span><button type="button" id="delbtn" class="btn">确认删除</button> </span>
    </div>
    <div class="innermodify">
        <p>
            <span>手机号码：<input type="text" id="modifyphone" maxlength="11"/> </span>
        </p>
        <span>员工姓名:<input type="text" id="modifyname"/> </span>

        <p>
            <span>用 户 名：<input type="text" id="modifyusername"/> </span>
        </p>
        <p>
            <span>登录密码：<input type="text" id="modifypassword"/> </span>
        </p>
        <p>
                <span>权&nbsp;&nbsp;&nbsp;&nbsp;限：<select id="modifyauthority">
                    <option value="收银员">收银员</option>
                    <option value="管理员">管理员</option>
                </select>
            </span>
        </p>
        <p>
            <button type="button" id="modifybtn" class="btn" >确认修改</button>
        </p>
    </div>
</div>
</body>
</html>