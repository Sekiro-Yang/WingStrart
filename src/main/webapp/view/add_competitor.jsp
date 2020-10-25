<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增选手页面</title>
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
            padding-top: 10px;
            width: 300px;
            height: 230px;
        }
        .SmallBox1{
            margin: 0 auto;
            width:90%;
        }
        .SmallBox2{
            margin: 0 auto;
            width:90%;
            padding-bottom:5px;
        }
    </style>
</head>
<body>
    <div class="BigBox">
        <div class="SmallBox">
            <div class="SmallBox1">
                <form action="AddCompetitorServlet?projectId=${projectId}" method="post">
                    <table border="1" >
                        <tr>
                            <td colspan="2"><h3>新增选手</h3></td>
                        </tr>
                        <tr>
                            <td>选手账号：</td>
                            <td><span>${addComAcc}</span></td>
                        </tr>
                        <tr>
                            <td>选手姓名：</td>
                            <td><input type="text" name="competitorName"/></td>
                        </tr>

                        <tr>
                            <td>账号密码：</td>
                            <td><input type="text" name="competitorPassword"/></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="submit" value="确定">
                                <input type="reset" value="清空">
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
           <div class="SmallBox2">
               <button type="back" onclick="window.location.href = 'ManSinProServlet?projectId=${projectId}'">完成</button>
           </div>
        </div>
    </div>
</body>
</html>