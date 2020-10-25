<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="cn">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>创建投票项目_选手信息页面</title>
    <style type="text/css">
        body{
            background-color: royalblue;
        }
        .BigBox{
            background-color: white;
            border-radius: 10px;
            width: 400px;
            height:300px;
            margin: 0 auto;
            margin-top: 150px;
        }
        .SmallBox{
            margin: 0 auto;
            width:90%;
            height:300px;
        }
        .SmallBox1{
            margin: 0 auto;
            width: 300px;
            border: 1px solid white;
        }
        .SmallBox2{
            margin: 0 auto;
            width:200px;
        }
        .SmallBox3{
            margin: 0 auto;
            width:50px;
            height:30px;
            line-height:30px;
        }
    </style>
</head>
<body>
    <div class="BigBox">
        <div class="SmallBox">
            <div class="SmallBox1">
                <h2>创建投票项目_选手信息页面</h2>
            </div>
            <hr>
            <div class="SmallBox2">
                <form action="/Maven_Webapp_Test/create_competitor?competitorAccountNum=${competitorAccountNum}&projectId=${project.projectId}" method="post">
                    <table>
                        <tr>
                            <th colspan="2">创建选手</th>
                        </tr>
                        <tr>
                            <td>选手账号：</td>
                            <td>${competitorAccountNum}</td>
                        </tr>
                        <tr>
                            <td>选手姓名：</td>
                            <td><input type="text" name="competitorName" id="competitorName" size="10"></td>
                        </tr>
                        <tr>
                            <td>选手密码：</td>
                            <td><input type="text" name="competitorPassword" id="competitorPassword" size="10"></td>
                        </tr>
                    </table>
                    <button type="submit">确认</button>
                <form>
            </div>
            <hr>
            <div class="SmallBox3">
                <a onclick="window.location.href = 'ManAllProServlet?founderId=${project.founderId}&founderName=${project.founderName}'">完成</a>
            </div>
        </div>
    </div>
</body>
</html>