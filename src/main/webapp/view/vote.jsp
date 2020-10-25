<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>vote.jsp</title>
        <style type="text/css">
            body{
                background-color: royalblue;
            }
            .BigBox{
                background-color: white;
                border-radius: 10px;
                width: 400px;
                height:auto !important;
                height:520px;
                min-height:520px;
                margin: 0 auto;
                margin-top: 100px;
            }
            .SmallBox{
                margin: 0 auto;
                width:90%;
                height:auto !important;
                height:520px;
                min-height:520px;
            }
            .SmallBox1{
                margin: 0 auto;
                width:100%;
                text-align:center;
                padding-top:5px;
            }
            .SmallBox2{
                margin: 0 auto;
                width:50%;
            }
            .SmallBox3{
                margin: 0 auto;
                width:50%;
                margin-top: 10px;
            }
            .SmallBox5{
                margin: 0 auto;
                width:100%;
                margin-top: 10px;
                text-align:center;
                padding-bottom:10px;
            }
        </style>
    </head>
<body>
    <div class="BigBox">
        <div class="SmallBox">
            <div class="SmallBox1">
                <h3>评委投票界面</h3>
            </div>
            <hr>

            <div class="SmallBox2">
                <table border="1">
                    <tr>
                        <th colspan="2">项目信息</th>
                    </tr>
                    <tr>
                        <td>项目Id：</td>
                        <td>${project.projectId}</td>
                    </tr>
                    <tr>
                        <td>项目名称：</td>
                        <td>${project.projectName}</td>
                    </tr>
                </table>
            </div>
            <hr>


            <div class="SmallBox3">
                <table border="1">
                    <tr>
                        <th colspan="2">评委信息</th>
                    </tr>
                    <tr>
                        <td>评委用户名</td>
                        <td>${judge.judgeName}</td>
                    </tr>
                    <tr>
                        <td>评委账号</td>
                        <td>${judge.judgeAccountNum}</td>
                    </tr>
                    <tr>
                        <td>您的权限</td>
                        <td>
                            <c:if test="${judge.judgeRight==1}">
                                可以投票
                            </c:if>
                            <c:if test="${judge.judgeRight==0}">
                                不可投票
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>您的剩余票数</td>
                        <td>${judge.judgeVotesNum}</td>
                    </tr>
                </table>
            </div>
            <hr>


            <div class="SmallBox4">
                <table name="nameCompetitor" id="idCompetitor" border="1" cellpadding="10" cellspacing="2">
                    <tbody id="body_judge">
                        <tr bgcolor="white">
                            <th>选手姓名</th>
                            <th>选手账号</th>
                            <th>票数</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${comList}" var="item">
                            <c:if test="${project.projectId == item.projectId}">
                                <tr>
                                  <td>${item.competitorName}</td>
                                  <td>${item.competitorAccountNum}</td>
                                  <td>${item.competitorVotesNum}</td>
                                  <td>
                                      <a href="VoteServlet?projectId=${project.projectId}&competitorName=${item.competitorName}&competitorAccountNum=${item.competitorAccountNum}&competitorVotesNum=${item.competitorVotesNum}&judgeAccountNum=${judge.judgeAccountNum}">投票</a>--
                                      <a href="QuitVoteServlet?projectId=${project.projectId}&competitorName=${item.competitorName}&competitorAccountNum=${item.competitorAccountNum}&competitorVotesNum=${item.competitorVotesNum}&judgeVotesNum=${judge.judgeVotesNum}&judgeAccountNum=${judge.judgeAccountNum}">弃票</a>
                                  </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <hr>

            <div class="SmallBox5">
                <button onclick="window.location.href='view/login_judge.jsp'">返回</button>
            </div>

        </div>
    </div>

</body>
</html>
