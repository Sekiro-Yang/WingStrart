<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>展示投票页面</title>
        <style type="text/css">
            body{
                background-color: royalblue;
            }
            .BigBox{
                background-color: white;
                border-radius: 10px;
                width: 400px;
                height:auto !important;
                height:360px;
                min-height:360px;
                margin: 0 auto;
                margin-top: 150px;
            }
            .SmallBox{
                margin: 0 auto;
                width: 90%;
                height:auto !important;
                height:360px;
                min-height:360px;
            }
            .SmallBox1{
                width: 100%;
                text-align:center;
                padding-top:5px;
            }
            .SmallBox2{
                margin: 0 auto;
                width:200px
            }
            .SmallBox3{
                margin: 0 auto;
                width:240px;
            }
            .SmallBox4{
                margin: 0 auto;
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
                <h3>展示投票页面</h3>
            </div>
            <hr>

            <div class="SmallBox2">
                <table>
                    <tr>
                        <th colspan="2">项目信息</th>
                    </tr>
                    <tr>
                        <td>项目Id</td>
                        <td>${projectId}</td>
                    </tr>
                    <tr>
                        <td>项目名称</td>
                        <td>${projectName}</td>
                    </tr>
                    <tr>
                        <td>项目轮数</td>
                        <td>第${projectSheave}轮</td>
                    </tr>
                </table>
            </div>
            <hr>

            <div class="SmallBox3">
                <table name="nameCompetitor" id="idCompetitor" border="1" cellpadding="10" cellspacing="2">
                    <tbody id="body_judge">
                        <tr bgcolor="white">
                            <th>选手姓名</th>
                            <th>选手账号</th>
                            <th>票数</th>
                        </tr>
                        <c:forEach items="${comList}" var="item">
                            <c:if test="${projectId == item.projectId}">
                                <tr>
                                  <td>${item.competitorName}</td>
                                  <td>${item.competitorAccountNum}</td>
                                  <td>${item.competitorVotesNum}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <hr>

            <div class="SmallBox4">
                <a href="ManAllProServlet?founderId=${founderId}&founderName=${founderName}">返回到项目管理页面</a>
            </div>
        </div>
    </div>
</body>
</html>
