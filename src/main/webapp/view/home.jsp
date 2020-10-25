<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>个人信息界面</title>
    <style type="text/css">
        body{
            background-color: royalblue;
        }
        .BigBox{
            background-color: white;
            border-radius: 10px;
            width: 400px;
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
        <c:if test="${founder.founderId == null}">
            <script language='javascript'>alert('你还未登录，点击确定跳转到登录界面');window.location.href='view/login_founder.jsp';</script>
        </c:if>

        <c:if test="${founder.founderId != null}">
            <div class="BigBox">
                <div class="SmallBox">

                    <div class="SmallBox1">
                        <h2>个人信息界面</h2>
                    </div>
                    <hr>

                    <div class="SmallBox2">
                        <table border="1px solid black">
                            <tr>
                                <td>账号：</td>
                                <td>${founder.founderId}</td>
                            </tr>
                            <tr>
                                <td>用户名：</td>
                                <td>${founder.founderName}</td>
                            </tr>
                            <tr>
                                <td>qq邮箱：</td>
                                <td>${founder.email}</td>
                            </tr>
                            <tr>
                                <td>性别：</td>
                                <td>${founder.sex}</td>
                            </tr>
                            <tr>
                                <td>年龄：</td>
                                <td>${founder.age}</td>
                            </tr>
                        </table>
                    </div>
                    <hr>

                    <div class="SmallBox3">
                        <button onclick="window.location.href = 'ManAllProServlet?founderId=${founder.founderId}'">管理项目</button>
                        <button onclick="window.location.href = 'create_project?founderId=${founder.founderId}'">创建项目</button>
                        <button onclick="window.location.href = 'change?founderId=${founder.founderId}'">修改信息</button>
                        <button onclick="window.location.href = 'quit'">退出登录</button>
                    </div>
                </div>
            </div>
        </c:if>
    </body>
</html>
