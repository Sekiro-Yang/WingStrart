<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑项目信息</title>
    <style type="text/css">
        body{
            background-color: royalblue;
        }
        .BigBox{
            background-color: white;
            border-radius: 10px;
            width: 350px;
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
        .SmallBox2{
            margin: 0 auto;
            width:150px;
            height:50px;
            line-height:50px;
        }
    </style>
</head>
<body>
    <div class="BigBox">
        <div class="SmallBox">
            <div class="SmallBox1">
                <form action="UpdProjectServlet?projectId=${project.projectId}" method="post" style="align-items: center">
                    <table border="1">
                        <tr>
                            <th colspan=2>项目信息编辑</th>
                        </tr>
                        <tr>
                            <td>项目Id</td>
                            <td>${project.projectId}</td>
                        </tr>
                        <tr>
                            <td>项目名称</td>
                            <td><input type="text" name="projectName" value="${project.projectName}"/></td>
                        </tr>
                    </table>
                    <input type="submit" value="确定"/>
                </form>
            </div>
            <hr>

            <div class="SmallBox2">
                <a href="ManAllProServlet?founderId=${project.founderId}&founderName=${project.founderName}">返回到项目管理页面</a>
            </div>
        </div>
    </div>
</body>
</html>