<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>create_project.jsp</title>
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
            width: 100%;
            text-align:center;
            padding-top:5px;
        }
        .SmallBox3{
            margin: 0 auto;
            width:280px;
        }
        .SmallBox4{
            width:100%;
            height:50px;
            line-height:50px;
            text-align:center;
        }
    </style>
</head>
<body>
    <div class="BigBox">

        <div class="SmallBox">
            <div class="SmallBox1">
                <h2>创建投票项目_项目信息页面</h2>
            </div>
            <hr>


            <div class="SmallBox3">
                <form action="/Maven_Webapp_Test/create_project?projectId=${projectId}&founderId=${founderId}&founderName=${founderName}" method="post">
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
                            <td><input type="text" name="projectName"></td>
                        </tr>
                        <tr>
                            <td>比赛轮数：</td>
                            <td><input type="text" name="endSheave" size="10"></td>
                        </tr>
                    </table>
                    <button type="submit">确认</button>
                </form>
            </div>
            <hr>


            <div class="SmallBox4">
                <a href="login_founder?founderId=${founderId}">返回到个人信息页面</a>
            </div>
        </div>
    </div>
</body>
</html>