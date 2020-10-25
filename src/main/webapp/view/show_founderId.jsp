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
            height: 230px;
            margin: 0 auto;
            margin-top: 150px;
        }
        .SmallBox{
            margin: 0 auto;
            width:90%;
            height: 230px;
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
                <h2>用户账号</h2>
            </div>
            <hr>

            <div class="SmallBox2">
                <span>您的用户账号：${new_founderId}</span>
                <p style="color:red">请务必记住您的用户账号，登录时需要使用</p>
            </div>
            <hr>
            <div class="SmallBox3">
                <a href="view/login_founder.jsp">进入登录页面</button>
            </div>
        </div>
    </div>
</body>
</html>