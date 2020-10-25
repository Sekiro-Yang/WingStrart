<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>activate_code.jsp</title>
    <style type="text/css">
        body{
            background-color: royalblue;
        }
        .BigBox{
            background-color: white;
            border-radius: 10px;
            width: 420px;
            height: 260px;
            margin: 0 auto;
            margin-top: 150px;
        }
        .SmallBox{
            margin: 0 auto;
            width:90%;
            height: 260px;
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
                    <form action="/Maven_Webapp_Test/ActivateCodeServlet?founderId=${founderId}&change_founderName=${change_founderName}&change_password=${change_password}&change_sex=${change_sex}&change_age=${change_age}" method="post">
                        <%
                            // 获取浏览器发送过来的cookie, 获取用户信息
                            Cookie[] cookies = request.getCookies();
                            String username = "";
                            if (cookies != null) {
                                for (Cookie cookie : cookies) {
                                    if ("username".equals(cookie.getName())) {
                                        username = cookie.getValue();
                                    }
                                }
                            }
                        %>
                        <div id="hidden">
                            验证码：<input type="text" name="image">
                            <img src="VerifyCodeServlet">
                            <input type="button" value="看不清? 换一张." id="btn1">
                            <br>
                            <font color="red">${requestScope.imageMess}</font>
                        </div>
                        <br>
                        <button type="submit">确定</button>
                    </form>
                </div>
                <hr>

                <div class="SmallBox3">
                    <a href="login_founder?founderId=${founderId}">返回到个人信息页面</a>
                </div>

            </div>
        </div>
    </body>

    <script type="text/javascript">
           function changeVerifyCode(img) {
                img.src = "../Kaptcha?" + Math.floor(Math.random() * 100);
           };
           document.getElementById("btn1").onclick = function () {
                // 获取img元素
                // 为了让浏览器发送请求到servlet, 所以一定要改变src
                document.getElementsByTagName("img")[0].src = "VerifyCodeServlet?time="+new Date().getTime();
           };
    </script>

</html>
