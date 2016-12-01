<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <meta charset="UTF-8">
    <script src="js/jquery-2.1.4.js"></script>
    <script type="text/javascript">
        function  delfun(e)
        {
        	$.get("delsalarybyid",{
        		salaryid:$(e).parent().siblings().eq(0).text()
        	},function(response){
        		alert(response);
        	});
        	
           $(e).parents("ul").hide();
        }
        $(function () {
            $("#query").click(function() {
                if($("#salarynum").val().length==0 && $("#date").val().length==0 && $("#name").val().length==0 )
                {alert("至少写一项");}
                else
                {
                	$("ul").remove(".datacss");
                    $.getJSON(
                            "conditionquery",
                            {
                                salarynum:$("#salarynum").val(),
                                date:$("#date").val(),
                                name:$("#name").val()
                            },
                            function(data){
                                $.each(data, function (index,item) {
                                    $("#bgdata").append(
                                            "<ul class='datacss'>" +
                                            "<li>" +item.id + "</li>" +
                                            "<li>" +item.employeenum + "</li>" +
                                            "<li>" +item.employeename+"</li>" +
                                            "<li>" +item.shouldbepay+"</li>" +
                                            "<li>" +item.actuallypay+"</li>"+
                                            "<li>" +item.note+"</li>"+
                                            "<li>" +item.paytime+"</li>"+
                                            "<li>"+"<button type='button' class='delbtn' onclick='delfun(this)'>"+"删除"+"</button></li>"+
                                            "</ul>"
                                    );
                                });
                            }
                    );
                }
            });
        });

    </script>
    <style type="text/css">
        .datacss{
            font-size: 24px;
        }
        .delbtn{
            font-size: 20px;
            height: 26px;
        }
        select{
            font-size: 26px;
        }
        input{
            width:240px;
            height:36px;
            border-radius:20px;
        }
        .condition{
            width:100%;
            height:86px;

            text-align:center;
            font-size:28px;
        }
        li{
            margin-top:0px;
            width: 186px;
            float: left;
            font-size: 22px;
            list-style: none;
            outline: 2px solid black;

        }
        li:hover{
            font-size: 36px;
        }
        ul{
            text-align: center;
            float: left;
            margin-top: 0px;
            font-weight:bold;
        }
        .title{
            padding-top: 10px;
            position: absolute;
            left:10%;
            width:90%;
            height:auto;
            float: left;

        }
        button{
            width: 120px;
            height:60px;
            border-radius: 20px;
            font-size: 28px;
        }
        button:hover{
            background-color: blue;
        }
        input{
            font-size: 30px;
        }
    </style>
    <title>发放管理</title>
</head>
<body>
<div class="condition">
    <p>
        <span>查询码：<input type="text" id="salarynum"/> </span>
            <span>日期：<input type="month" id="date"/>
            </span>
        <span>姓名：<input type="text" id="name"/> </span>
        <span><button type="button" id="query">查询</button> </span>
    </p>


</div>
<div class="title" id="bgdata" >
    <ul id="title">
		<li>
            序号
        </li>
        <li>
            员工手机号
        </li>

        <li>
            员工姓名
        </li>

        <li>
            应发工资
        </li>

        <li>
            实发工资
        </li>
        <li>
            备注
        </li>

        <li>
            发放时间
        </li>
        <li>
            操作
        </li>
    </ul>

</div>
</body>
</html>