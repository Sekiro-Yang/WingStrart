<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>所有项目管理</title>
        <style type="text/css">
            body{
                background-color: royalblue;
            }
            .BigBox{
                background-color: white;
                border-radius: 10px;
                width: 900px;
                height:auto !important;
                height:360px;
                min-height:360px;
                margin: 0 auto;
            }
            .SmallBox{
                margin: 0 auto;
                width:90%;
                height:auto !important;
                height:360px;
                min-height:360px;
                margin-top: 100px;
            }
            .SmallBox1{
                margin: 0 auto;
                width: 150px;
                border: 1px solid white;
            }

            .SmallBox3{
                margin: 0 auto;
                width:750px;
            }

            .SmallBox4{
                width:100%;
                text-align:center;
                height:50px;
                line-height:50px;
            }
        </style>
    </head>
    <body>
        <div class="BigBox">
            <div class="SmallBox">
                <div class="SmallBox1">
                    <h2>项目管理页面</h2>
                </div>
                <hr>

                <div class="SmallBox3">
                    <table name="tname" id="tid_judge" border="1" cellpadding="10" cellspacing="2">
                        <tbody id="body_judge"></tbody>
                            <tr>
                                <th colspan="5">项目信息</th>
                            </tr>
                            <tr bgcolor="white">
                                <th>项目名称</th>
                                <th>项目Id</th>
                                <th>状况</th>
                                <th>总轮数</th>
                                <th>项目操作</th>
                            </tr>
                            <c:forEach items="${proList}" var="item">
                                <c:if test="${founderId == item.founderId}">
                                    <tr>
                                        <td>${item.projectName}</td>
                                        <td>${item.projectId}</td>
                                        <td>
                                            <c:if test="${item.projectRight != 0}">
                                                第${item.projectSheave}轮
                                            </c:if>
                                            <c:if test="${item.projectRight == 0}">
                                                失效
                                            </c:if>
                                        </td>
                                        <td>${item.endSheave}轮</td>
                                        <td>
                                            <a href="ManSinProServlet?projectId=${item.projectId}">管理</a>--
                                            <a href="ShowServlet?projectId=${item.projectId}">展示</a>--
                                            <a href="UpdProjectServlet?projectId=${item.projectId}">修改</a>--
                                            <a href="NextSheaveServlet?projectId=${item.projectId}">下一轮</a>--
                                            <a href="EndProjectServlet?projectId=${item.projectId}">结束/恢复</a>--
                                            <a href="DelProjectServlet?projectId=${item.projectId}">删除</a>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                            <tr>
                                <td colspan="5" style="text-align: left"><a href="create_project?founderId=${founderId}&founderName=${founderName}">新增项目</a></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <hr>
                <div class="SmallBox4">
                    <a href="login_founder?founderId=${founderId}">返回到个人信息页面</a>
                </div>
            </div>
        </div>
    </body>
</html>