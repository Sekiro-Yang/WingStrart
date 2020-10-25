<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register.jsp</title>
    <style type="text/css">
        body{
            background-color: royalblue;
        }
        .BigBox{
            background-color: white;
            border-radius: 10px;
            width: 500px;
            height: 300px;
            margin: 0 auto;
            margin-top: 150px;
        }
        .SmallBox{
            margin: 0 auto;
            width:90%;
            height: 300px;
        }
        .SmallBox1{
            margin: 0 auto;
            width:100%;
            text-align:center;
            padding-top:5px;
        }
        .SmallBox2{
            margin: 0 auto;
            width:70%;
        }
        .SmallBox3{
            margin: 0 auto;
            width:85%;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="BigBox">
        <div class="SmallBox">

            <div class="SmallBox1">
                <h2>用户注册界面</h2>
            </div>
            <hr>


            <div class="SmallBox2">
                <form action="/Maven_Webapp_Test/register?new_founderId=${new_founderId}" method="post">
                    <table>
                        <tr>
                            <td>请输入你的用户名：</td>
                            <td><input type="text" name="new_founderName"></td>
                        </tr>
                        <tr>
                            <td>请输入你的密码：</td>
                            <td><input type="text" name="new_password1"></td>
                        </tr>
                        <tr>
                            <td>请确认你的密码：</td>
                            <td><input type="text" name="new_password2"></td>
                        </tr>
                        <tr>
                            <td>请输入你的性别：</td>
                            <td><input type="text" name="new_sex"></td>
                        </tr>
                        <tr>
                            <td>请输入你的年龄：</td>
                            <td><input type="text" name="new_age"></td>
                        </tr>
                        <tr>
                            <td>请输入你的邮箱：</td>
                            <td><input type="text" name="new_email"></td>
                        </tr>
                    </table>
                    <button type="submit">确认</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>