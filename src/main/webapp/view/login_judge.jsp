<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>评委登录界面</title>
        <link rel="stylesheet" href="../css/login_judge.css">
        <style type="text/css">
            body{
                background-color: royalblue;
            }
            .BigBox{
                background-color: white;
                border-radius: 10px;
                width: 400px;
                height: 280px;
                margin: 0 auto;
                margin-top: 150px;
            }
            .SmallBox{
                margin: 0 auto;
                width:90%;
                height: 280px;
            }
            .SmallBox1{
                margin: 0 auto;
                width: 150px;
                border: 1px solid white;

            }
            .SmallBox2{
                margin: 0 auto;
                width:80%;
            }
            .SmallBox3{
                margin: 0 auto;
                width:80%;
            }
        </style>
    </head>
    <body>
        <div class="BigBox">
            <div class="SmallBox">
                <div class="SmallBox1">
                    <h2>评委登录界面</h2>
                </div>
                <hr>

                <div class="SmallBox2">
                    <form action="/Maven_Webapp_Test/LogJudgeServlet" method="post">
                        <table>
                            <tr>
                                <td>评委账号：</td>
                                <td><input type="text" name="judgeAccountNum"></td>
                            </tr>
                            <tr>
                                <td>密码：</td>
                                <td><input type="password" name="judgePassword"></td>
                            </tr>
                            <tr>
                                <td>投票项目Id：</td>
                                <td><input type="text" name="projectId"></td>
                            </tr>
                        </table>
                        <button type="submit">登录</button>
                    </form>
                </div>
                <hr>
                <div class="SmallBox3">
                    <button onclick="window.location.href='choose.jsp'">返回</button>
                </div>
            </div>
        </div>
    </body>
</html>