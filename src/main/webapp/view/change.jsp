<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>change.jsp</title>
    <style type="text/css">
        body{
            background-color: royalblue;
        }
        .BigBox{
            background-color: white;
            border-radius: 10px;
            width: 500px;
            height: 320px;
            margin: 0 auto;
            margin-top: 150px;
        }
        .SmallBox{
            margin: 0 auto;
            width:90%;
            height: 320px;
        }
        .SmallBox1{
            margin: 0 auto;
            width:75%;
            text-align:center;
            padding-top:15px;
        }
        .SmallBox2{
            margin: 0 auto;
            width:100%;
            text-align:center;
            margin-top: 10px;
        }
    </style>
</head>
    <body>
        <div class="BigBox">
            <div class="SmallBox">
                <div class="SmallBox1">
                    <form action="/Maven_Webapp_Test/change?founderId=${founder.founderId}" method="post">
                        <table border="1">
                            <tr>
                                <th colspan="2">用户信息修改</th>
                            </tr>
                            <tr>
                                <td>您的账户Id：</td>
                                <td>${founder.founderId}</td>
                            </tr>
                            <tr>
                                <td>请修改你的用户名：</td>
                                <td><input type="text" name="change_founderName" value=${founder.founderName}></td>
                            </tr>
                            <tr>
                                <td>请修改你的密码：</td>
                                <td><input type="password" name="change_password1" value=${founder.password}></td>
                            </tr>
                            <tr>
                                <td>请确认你的密码：</td>
                                <td><input type="password" name="change_password2" value=${founder.password}></td>
                            </tr>
                            <tr>
                                <td>请修改你的性别：</td>
                                <td><input type="text" name="change_sex" value=${founder.sex}></td>
                            </tr>
                            <tr>
                                <td>请修改你的年龄：</td>
                                <td><input type="text" name="change_age" value=${founder.age}></td>
                            </tr>
                        </table>
                        <br>
                        <button type="submit">确定</button>
                    </form>
                </div>
                <hr>
                <div class="SmallBox2">
                    <a onclick="back()">返回个人信息页面</a>
                </div>
            </div>
        </div>

        <script type="text/javascript">
            function back(){
                window.history.go(-1);
           }
       </script>
    </body>
</html>
