<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <script src="js/jquery-2.1.4.js"></script>
    <meta charset="UTF-8">
    <title>员工信息管理</title>
    <script type="text/javascript">
        $(function(){
        	
            $.getJSON("listallemployee",function(responsetxt){
            	$.each(responsetxt,function(i,item){
                    $("#allinfo").append(
                        "<tr class='detail'>"+
                        "<td>"+item.realname+"</td>"+
                        "<td>"+item.sex+"</td>"+
                        "<td>"+item.address+"</td>"+
                        "<td>"+item.tel+"</td>"+
                        "<td>"+item.password+"</td>"+
                        "<td>"+item.position+"</td>"+
                        "<td>"+item.salary+"</td>"+
             "<td>"+"<a href='editemployee?editnum="+item.tel+"'>"+"编辑"+"</a>"+
             "<a href='deleteemployee?delnum="+item.tel+"'>"+"删除"+"</a>"+"</td>"+
                        "</tr>"
                    );
                });
            });
             $(".head").find("td").addClass("title");
            
            
        });
    </script>
    <style type="text/css">
        .head{
            font-size: 30px;
            text-align: center;

        }
        .title{
            text-align: center;
            width:160px;
            font-weight: bold;
        }
        .detail{
        	color:black;
        	font-size:20px;
        	font-weight:bold;
        	text-align: center;
        }
        .btn{
        margin-left:2px;
    }
    </style>
</head>
<body>
	<a href="addemployee.jsp">添加员工</a>
    <table id="allinfo"  border="1" align="center">
        <thead class="head">
            <tr>
                <td>姓名</td>
                <td>性别</td>
                <td>地址</td>
                <td>手机号</td>
                <td>密码</td>
                <td>职位</td>
                <td>工资</td>
                <td>操作</td>
            </tr>
        </thead>


    </table>
    <!--<button type="submit" class="btn">批量删除</button>-->
</body>
</html>