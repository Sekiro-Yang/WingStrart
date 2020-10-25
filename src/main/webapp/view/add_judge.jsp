<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加评委界面</title>
    <style type="text/css">
        body{
            background-color: royalblue;
        }
        .BigBox{
            background-color: white;
            border-radius: 10px;
            width: 300px;
            height: 240px;
            margin: 0 auto;
            margin-top: 190px;
        }
        .SmallBox{
            margin: 0 auto;
            padding-top: 10px;
            width: 300px;
            height: 240px;
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
                <form action="AddJudgeServlet?projectId=${projectId}&projectName=${projectName}&judgeAccountNum=${judgeAccountNum}" method="post">
                    <table border="1" >
                        <tr>
                            <td colspan="2"><h3>新增评委</h3></td>
                        </tr>
                        <tr>
                            <td>评委账号：</td>
                            <td><span>${judgeAccountNum}</span></td>
                        </tr>
                        <tr>
                            <td>评委姓名：</td>
                            <td><input type="text" name="judgeName"/></td>
                        </tr>
                        <tr>
                            <td>账号密码：</td>
                            <td><input type="text" name="judgePassword"/></td>
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