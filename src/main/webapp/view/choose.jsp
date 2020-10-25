<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>choose.jsp</title>
        <style type="text/css">
            body{
                background-color: royalblue;

            }
            .BigBox{
                background-color: white;
                border-radius: 10px;
                width: 300px;
                height: 125px;
                margin: 0 auto;
                margin-top: 250px;
            }
            .SmallBox{
                margin: 0 auto;
                width:90%;
                height: 125px;
            }
            .SmallBox1{
                margin:0 auto;

                text-align:center;
                padding-top:5px;
            }
            .SmallBox2{
                width:50%;
                margin:0 auto;
            }
        </style>
    </head>

    <body>
        <div class="BigBox">
            <div class="SmallBox1">
                <h2>请选择你的登录身份：</h2>
            </div>
            <div class="SmallBox2">
                <button onclick="window.location.href = 'login_founder.jsp'">用户登录</button>
                <button onclick="window.location.href = 'login_judge.jsp'">评委登录</button>
            </div>
        </div>
    </body>
</html>

