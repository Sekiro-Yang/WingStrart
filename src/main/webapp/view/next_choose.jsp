<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>选择评委页面</title>
    <style type="text/css">
        body{
            background-color: royalblue;
        }
        .BigBox{
            background-color: white;
            border-radius: 10px;
            width: 600px;
            height:auto !important;
            height:300px;
            min-height:300px;
            margin: 0 auto;
            margin-top: 160px;
        }
        .SmallBox{
            margin: 0 auto;
            margin-top: 50px;
            width: 520px;
            height:auto !important;
            height:300px;
            min-height:300px;
        }
        .SmallBox1{
            margin: 0 auto;
            padding-top:5px;
            text-align:center;
        }
        .SmallBox2{
            margin: 0 auto;
            width:90%
        }
        .SmallBox3{
            margin: 0 auto;
            width:100%;
            text-align:center;
            height:80px;
        }
    </style>
</head>
<body>
    <div class="BigBox">
        <div class="SmallBox">

            <div class="SmallBox1">
                <h2>请选择下一轮的评委</h2>
            </div>
            <hr>

            <div class="SmallBox2">
                <table name="nameJudge" id="idJudge" border="1" cellpadding="10" cellspacing="2">
                    <tbody id="body_judge">
                        <tr bgcolor="white">
                            <th>评委姓名</th>
                            <th>评委账号</th>
                            <th>账号密码</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${judList}" var="item">
                            <c:if test="${project.projectId == item.projectId}">
                                <tr>
                                  <td>${item.judgeName}</td>
                                  <td>${item.judgeAccountNum}</td>
                                  <td>${item.judgePassword}</td>
                                  <td>
                                      <c:if test="${item.judgeRight==1}">
                                        已选
                                      </c:if>
                                      <c:if test="${item.judgeRight==0}">
                                        未选
                                      </c:if>
                                  </td>
                                  <td>
                                      <a href="ChoJudgeServlet_nextSheave?judgeAccountNum=${item.judgeAccountNum}&projectId=${project.projectId}">选择/取消选择</a>
                                  </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <hr>

            <div class="SmallBox3">
                <a href="NextSheave2Servlet?founderId=${project.founderId}&founderName=${project.founderName}&projectId=${project.projectId}">进入下一轮</a><br><br>
                <a href="ManAllProServlet?founderId=${project.founderId}&founderName=${project.founderName}"> 返回到项目管理页面</a>
            </div>
        </div>
    </div>

</body>
</html>