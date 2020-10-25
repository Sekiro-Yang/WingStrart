<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>manager_sinPro.jsp</title>
    <style type="text/css">
        body{
            background-color: royalblue;
        }
        .BigBox{
            background-color: white;
            border-radius: 10px;
            width: 750px;
            height:auto !important;
            height:500px;
            min-height:500px;
            margin: 0 auto;
        }
        .SmallBox{
            margin: 0 auto;
            width:90%;
            height:auto !important;
            height:500px;
            min-height:500px;
            margin-top: 50px;
        }
        .SmallBox1{
            margin: 0 auto;
            width: 100%;
            text-align:center;
            border: 1px solid white;
        }
        .SmallBox2{
            margin: 0 auto;
            width:170px;
            margin-bottom:10px;
            margin-top:10px;
        }
        .SmallBox3{
            margin: 0 auto;
            width:200px;
            margin-bottom:10px;
            margin-top:10px;
        }

        .SmallBox4{
            margin: 0 auto;
            width:560px;
            height:auto !important;
            height:150px;
            min-height:150px;
            line-height:100px;
        }
        .SmallBox5{
            margin: 0 auto;
            width:490px;
            height:auto !important;
            height:150px;
            min-height:150px;
            line-height:100px;
        }
        .SmallBox6{
            margin: 0 auto;
            width:150px;
            height:80px;
            line-height:80px;
        }
    </style>
</head>
<body>
    <div class="BigBox">
        <div class="SmallBox">


            <div class="SmallBox1">
                <h2>单个项目管理页面</h2>
            </div>
            <hr>


            <div class="SmallBox2">
                <table>
                    <tr>
                        <th colspan="2">创建者信息</th>
                    </tr>
                    <tr>
                        <td>创建者Id：</td>
                        <td>${founderId}</td>
                    </tr>
                    <tr>
                        <td>创建者姓名：</td>
                        <td>${founderName}</td>
                    </tr>

                </table>
            </div>
            <hr>


            <div class="SmallBox3">
                <table>
                    <tr>
                        <th colspan="2">项目信息</th>
                    </tr>
                    <tr>
                        <td>项目Id：</td>
                        <td>${projectId}</td>
                    </tr>
                    <tr>
                        <td>项目名称：</td>
                        <td>${projectName}</td>
                    </tr>
                    <tr>
                        <td>当前轮数：</td>
                        <td>${projectSheave}</td>
                    </tr>
                    <tr>
                        <td>最终轮数：</td>
                        <td>${endSheave}</td>
                    </tr>
                </table>
            </div>
            <hr>


            <div class="SmallBox4">
                <table name="nameJudge" id="idJudge" border="1" cellpadding="10" cellspacing="2">
                    <tbody id="body_judge">
                        <tr>
                            <th colspan="5">评委信息</th>
                        </tr>
                        <tr bgcolor="white">
                            <th>评委姓名</th>
                            <th>评委账号</th>
                            <th>账号密码</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${judList}" var="item">
                            <c:if test="${projectId == item.projectId}">
                                <tr>
                                  <td>${item.judgeName}</td>
                                  <td>${item.judgeAccountNum}</td>
                                  <td>${item.judgePassword}</td>
                                  <td>
                                    <c:if test="${item.judgeRight == 1}">
                                      已选
                                    </c:if>
                                    <c:if test="${item.judgeRight == 0}">
                                      未选
                                    </c:if>
                                  </td>
                                  <td>
                                    <a href="DelJudgeServlet?judgeAccountNum=${item.judgeAccountNum}">删除</a>--
                                    <a href="UpdJudgeServlet?judgeAccountNum=${item.judgeAccountNum}">编辑</a>--
                                    <a href="ChoJudgeServlet?judgeAccountNum=${item.judgeAccountNum}&projectId=${projectId}">选择/取消选择</a>
                                  </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        <tr>
                            <td colspan="5" style="text-align: left"><a href="AddJudgeServlet?projectId=${projectId}&projectName=${projectName}">新增评委</a> </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <hr>


            <div class="SmallBox5">
                <table name="nameCompetitor" id="idCompetitor" border="1" cellpadding="10" cellspacing="2">
                    <tbody id="body_judge">
                        <tr>
                            <th colspan="5">选手信息</th>
                        </tr>
                        <tr bgcolor="white">
                            <th>选手姓名</th>
                            <th>选手账号</th>
                            <th>账号密码</th>
                            <th>票数</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${comList}" var="item">
                            <c:if test="${projectId == item.projectId}">
                                <tr>
                                  <td>${item.competitorName}</td>
                                  <td>${item.competitorAccountNum}</td>
                                  <td>${item.competitorPassword}</td>
                                  <td>${item.competitorVotesNum}</td>
                                  <td>
                                      <a href="DelCompetitorServlet?competitorAccountNum=${item.competitorAccountNum}">删除</a>--
                                      <a href="UpdCompetitorServlet?competitorAccountNum=${item.competitorAccountNum}">编辑</a>
                                  </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        <tr>
                            <td colspan="5" style="text-align: left"><a href="AddCompetitorServlet?projectId=${projectId}&projectName=${projectName}">新增选手</a> </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <hr>


            <div class="SmallBox6">
                <a href="ManAllProServlet?founderId=${founderId}&founderName=${founderName}">返回到项目管理页面</a>
            </div>


        </div>
    </div>
</body>
</html>