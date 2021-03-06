<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>营业额管理</title>
    <script src="js/jquery-2.1.4.js"></script>
    <script type="text/javascript">
            $(function () {
                $("#querybtn").click(function () {
                    if($("#time").val()=="" || $("#time").val()==null)
                    {
                       alert("请选择查询日期！");
                    }
                    else
                    {
                      $.get("querytotalmoney",
                              {
                                  querytime:$("#time").val(),
                                  queryway:$("input[type='radio']:checked").val()
                              },
                              function (response) {
                          if(parseInt(response)==0)
                          {
                          	 alert("查询的日期没有营业额");
                          	 $("#showmoney").html(response);
                          }
                          else {
                              $("#showmoney").html(response);
                          }
                      });
                    }
                });
            });
        
    </script>
    <style type="text/css">
        .querybtn{
            width: 92px;
            height:50px;
            border-radius:9px;
            float: right;
            margin-right: 23px;
            margin-top:5px;
        }
        span{
            font-size: 20px;

        }
        .inputcss{
            width:200px;
            height: 40px;
            font-size: 25px;
            border-radius:8px;
        }
        .container{
            width:493px;
            height: 400px;
            border:1px solid #FF0000;
            margin: auto auto;
            font-size: 40px;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <span>请选择查询日期：</span><input type="date" class="inputcss" id="time"/>
        <button type="button" id="querybtn" class="querybtn">查询</button>
        <span>查询方式：<input type="radio" name="queryway" value="year"/>按年份查询 </span>
        <span><input type="radio" name="queryway" value="month"/>按月份查询</span>

        <span>营业额：</span><span><label id="showmoney" class="inputcss"> </label></span>

    </div>
</body>
</html>