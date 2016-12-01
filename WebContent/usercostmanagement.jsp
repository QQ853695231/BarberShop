<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>会员消费记录</title>
    <script src="js/jquery-2.1.4.js"></script>
    <script type="text/javascript">
        //生成从后台获取的json数组解析后的数据
        function generatedrow(responsetxt){
            $.each(responsetxt,function(i,item)
            {
            	//alert(item.number);
                $("#usercost").append(
                        "<tr class='detail'>"+
                        "<td>"+item.number+"</td>"+
                        "<td>"+item.name+"</td>"+
                        "<td>"+item.way+"</td>"+
                        "<td>"+item.cost+"</td>"+
                        "<td>"+item.content+"</td>"+
                        "<td>"+item.time+"</td>"+
                        "<td>"+"<button type='button' value='"+item.id+"' onclick='hiderow(this)'>"+"删除"+"</button>"+"</td>"+
                        "</tr>"
                );
            });

        }
        //清除当前表格当中的行
        function clearAlltr()
        {
            $("tr").remove(".detail");
        }
        //点击底部的页数按钮并生成数据
        function getnewdata(e){
                //alert($(e).val());
               clearAlltr();
            $.getJSON("getusercostdata",
                    {
                        page: $(e).val()
                    },function(firstpagedata){
                        generatedrow(firstpagedata);

                    }
            );

        }
        //生成的底部的按钮
        function generatedpagebtn(num){
            alert("需要生成的按钮个数"+num);
             var count=parseInt(num);
            for(var i=1;i<=count;i++)
            {
               $(".bottom").append(
                       "<span>"+
                       "<button type='button' onclick='getnewdata(this)' value='"+i+"'>"+i+"</button>"
                       +"</span>"
               );
            }
        }
            function getfirstpagedata(){
                $.getJSON("getusercostdata",
                        {
                            page:1
                        },function(firstpagedata){
                        	//alert(firstpagedata);
                            generatedrow(firstpagedata);
                        }
                );
            }
            function hiderow(e){
        			alert($(e).val());
        			$.get("deleusercostbyid",{
        				delcostid:$(e).val()
        			});
        			$(e).parents("tr").hide();
            }
            
        $(function(){
        		
            $("#costbtn").click(function(){
                //清除当前所有的行
                     clearAlltr();
                //清除所有已经生成的按钮
                    // $("div .bottom").html("");
  				$(".bottom").empty();
                $.get("queryUserCost",
                {
                    costdate:$("#costdate").val(),
                    querycostway:$("input[type='radio']:checked").val()

                },function(data)
                  {
                     generatedpagebtn(data);
                      getfirstpagedata();
                  }

                );

            });



        });


    </script>
</head>
<style>
    .thead{

        font-size: 20px;
    }
    .condition
    {
        margin-top: 0px;
        margin-left: 400px;
        width:800px;
        height:50px;
        border:1px solid #FF0000;
        font-size:21px;
        font-weight:bold;
    }

    .bottom{
    	position:absolute;
        margin-left:400px;
        top:400px;
        width:800px;
        height:30px;
        border:1px solid #FF0000;
    }
</style>
<body>
        <div class="condition">
             <span>
                请选择日期： <input type="date" id="costdate" style="font-size: 20px;"/>
            </span>
            <span>查询方式：</span>
            <span><input type="radio" name="queryway" value="year">按年份查询</input> </span>
            <span><input type="radio" name="queryway" value="month">按月份查询</input></span>

            <button id="costbtn" type="button">查询</button> </span>
        </div>
        <table id="usercost" align="center" border="1">
                <thead class="thead">
                    <tr>
                        <td>会员号</td>
                        <td>会员名</td>
                        <td>付款方式</td>
                        <td>支付金额</td>
                        <td>消费内容</td>
                        <td>消费时间</td>
                        <td>操作</td>
                    </tr>
                </thead>


        </table>
<div class="bottom">


</div>
</body>
</html>

</body>
</html>