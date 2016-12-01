<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script src="js/jquery-2.1.4.js"></script>
    <script type="text/javascript">
        function validate_required(field,alerttxt)
        {
            with (field)
            {
                if (value==null||value=="")
                {
                    alert(alerttxt);
                    field.focus();
                    return false;
                    }
                else {
                return true;
                }
            }
        };
        $(function(){
            $("#employeephone").blur(function () {
                if(validate_required(employeephone,"请输入手机号！") &&  $("#employeephone").val().length==11)
                {
                    $.getJSON("queryemployee",{
                        querynum:$("#employeephone").val()

                    },function(responsetxt){
                      
                            $("#employeename").val(responsetxt.realname);
                            $("#shouldbepay").val(responsetxt.salary);
                     		

                    });
                }
                else
                {
                    alert("号码有误！");
                }
            });
            $(".right input").addClass("input");
            $(".left p").addClass("bottom");
            $("#salarybtn").click(function () {
                var flag=validate_required(employeename,"员工不存在！") &&
                         validate_required(shouldbepay,"请输入应发工资！") &&
                         validate_required(actuallypay,"请输入实发工资！");
                if(flag){
                    $.get("givesalary",
                            {

                                salaryphone:$("#employeephone").val(),
                                salaryname:$("#employeename").val(),
                                shouldbepay:$("#shouldbepay").val(),
                                actuallypay:$("#actuallypay").val(),
                                note:$("#note").val()

                },
                            function (response) {
                                alert(response);

                    });
                }
            });
        });


    </script>
    <style type="text/css">
            .pub{
                width: 300px;
                height:500px;

                font-size: 40px;
            }
            .out{
                width: 700px;
                height:700px;
                border:1px solid #FF0000;
                margin: auto auto;
            }
            .left{
                float: left;
                text-align: right;
                margin-left: 95px;

            }
            .right{
                float:right;
            }
            .input{
                width: 260px;
                height: 40px;
                border-radius: 20px;
                font-size: 30px;
            }
            .salarybtn{
                    display: block;
                    width: 280px;
                    height:40px;
                    font-size: 30px;
                    letter-spacing: 15px;
                    font-weight:bold;
                    margin-left:40%;
                    border-radius: 20px;

            }
            .bottom{

                margin-bottom: 52px;
            }
    </style>
    <title>工资发放</title>
</head>
<body>
    <div class="out">
            <div class="left pub">
                <p>员工手机号：</p>
                <p>员工姓名：</p>
                <p>应发工资：</p>
                <p>实发工资：</p>
                <p>备    注：</p>
            </div>
            <div class="right pub" id="">
                <p><input type="text" id="employeephone" class="input"/>*</p>
                <p><input type="text" id="employeename" disabled="disabled"/></p>
                <p><input type="text" id="shouldbepay" disabled="disabled"/>*</p>
                <p><input type="text" id="actuallypay"/>*</p>
                <p><input type="text" id="note"/></p>
            </div>
            <button type="button" id="salarybtn" class="salarybtn">工资发放</button>
    </div>
</body>
</html>