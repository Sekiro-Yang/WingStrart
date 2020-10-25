<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑评委信息</title>
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
                <form action="UpdJudgeServlet?judgeAccountNum=${judge.judgeAccountNum}&projectId=${judge.projectId}" method="post" style="align-items: center">
                    <table border="1" >
                        <tr>
                            <td colspan="2"><h3>编辑评委信息</h3></td>
                        </tr>
                        <tr>
                            <td>评委账号</td>
                            <td><span>${judge.judgeAccountNum}</span></td>
                        </tr>
                        <tr>
                            <td>评委姓名</td>
                            <td><input type="text" name="judgeName" value="${judge.judgeName}" /></td>
                        </tr>

                        <tr>
                            <td>账号密码</td>
                            <td><input type="text" name="judgePassword" value="${judge.judgePassword}" /></td>
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