<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑选手信息</title>
    <style type="text/css">
        body{
            background-color: royalblue;
        }
        .BigBox{
            background-color: white;
            border-radius: 10px;
            width: 300px;
            height: 230px;
            margin: 0 auto;
            margin-top: 190px;
        }
        .SmallBox{
            margin: 0 auto;
            margin-top: 50px;
            height: 230px;
        }
        .SmallBox1{
            margin: 0 auto;
            width:250px;
            padding-top:30px;
        }
    </style>
</head>
<body>
    <div class="BigBox">
        <div class="SmallBox">
            <div class="SmallBox1">
                <form action="UpdCompetitorServlet?competitorAccountNum=${competitor.competitorAccountNum}" method="post" style="align-items: center">
                    <table border="1" >
                        <tr>
                            <td colspan="2"><h3>编辑选手信息</h3></td>
                        </tr>
                        <tr>
                            <td>选手账号</td>
                            <td><span>${competitor.competitorAccountNum}</span></td>
                        </tr>
                        <tr>
                            <td>选手姓名</td>
                                <td><input type="text" name="competitorName" value="${competitor.competitorName}" /></td>
                        </tr>
                        <tr>
                            <td>账号密码</td>
                            <td><input type="text" name="competitorPassword" value="${competitor.competitorPassword}" /></td>
                        </tr>
                    </table>
                    <br>
                    <input type="submit" value="确定"/>
                </form>
            </div>
        </div>
    </div>
</body>
</html>