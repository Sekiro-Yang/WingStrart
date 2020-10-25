<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>activate_emailcode.jsp</title>
    <style type="text/css">
        body{
            background-color: royalblue;
        }
        .BigBox{
            background-color: white;
            border-radius: 10px;
            width: 420px;
            height: 240px;
            margin: 0 auto;
            margin-top: 150px;
        }
        .SmallBox{
            margin: 0 auto;
            width:90%;
            height: 240px;
        }
        .SmallBox1{
            margin: 0 auto;
            width:100%;
            text-align:center;
            padding-top:5px;
        }
        .SmallBox2{
            margin: 0 auto;
            width:75%;
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
                    <h3>用户信息修改界面</h3>
                </div>
                <hr>


                <div class="SmallBox2">
                    <form action="/Maven_Webapp_Test/ActivateEmailcodeServlet?founderId=${founder.founderId}&change_founderName=${change_founderName}&change_password=${change_password}&change_sex=${change_sex}&change_age=${change_age}" method="post">
                        <span>请输入邮箱激活码：</span>
                        <input type="text" name="code">
                        <input type="button" value="获取激活码" id="btn2">
                        <font color="red">${requestScope.emailCodeMess}</font>
                        <br><br>
                        <button type="submit">确定</button>
                    </form>
                </div>
                <hr>
                <div class="SmallBox3">
                    <a href="login_founder?founderId=${founder.founderId}">返回到个人信息页面</a>
                </div>
            </div>
        </div>
    </body>

    <script type="text/javascript">
       document.getElementById("btn2").onclick = function () {
            location.href="GetCodeServlet?founderId=${founder.founderId}&change_founderName=${change_founderName}&change_password=${change_password}&change_sex=${change_sex}&change_age=${change_age}"
       };
    </script>
</html>
