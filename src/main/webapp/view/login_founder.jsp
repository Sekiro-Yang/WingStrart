<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>login_founder.jsp</title>
        <style type="text/css">
            body{
                background-color: royalblue;
            }
            .BigBox{
                background-color: white;
                border-radius: 10px;
                width: 300px;
                height: 230px;
                margin: 0 auto;
                margin-top: 190px;
            }
            .SmallBox{
                margin: 0 auto;
                margin-top: 50px;
                width:90%;
                height: 230px;
            }
            .SmallBox1{
                margin: 0 auto;
                width: 150px;
                padding-top:5px;
            }
            .SmallBox2{
                margin: 0 auto;
                width:250px
            }
            .SmallBox3{
                margin: 0 auto;
                width:250px
            }
        </style>
    </head>
    <body>
        <div class="BigBox">
            <div class="SmallBox">
                <div class="SmallBox1">
                    <h2>用户登录界面</h2>
                </div>
                <hr>

                <div class="SmallBox2">
                    <form action="/Maven_Webapp_Test/login_founder" method="post">
                        <table>
                            <tr>
                                <td>账号：</td>
                                <td><input type="text" name="founderId"></td>
                            </tr>
                            <tr>
                                <td>密码：</td>
                                <td><input type="password" name="password"></td>
                            </tr>
                        </table>
                        <button type="submit">登录</button>
                    </form>
                </div>
                <hr>

                <div class="SmallBox3">
                    <button onclick="window.location.href='../register'">注册</button>
                    <button onclick="window.location.href='choose.jsp'">返回</button>
                </div>
            </div>
        </div>
    </body>
</html>