<%--
  Created by IntelliJ IDEA.
  User: chengsilong
  Date: 2018/12/5
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生管理系统登录</title>

</head>
<body style="margin:0;padding:0;">


<div class="addBg" style="width: 400px;
						 height: 400px;
						 position: absolute;
						 background-color: rgba(222,225,230,0.3);
						 border-radius: 400px;
						 -webkit-border-radius: 400px;
						  position:fixed;
                        top:50%;
                        margin-top:-200px;
                        left:50%;
                        margin-left:-150px;
						  z-index:1000000;box-shadow:0px 0px 40px white;">

    <form action="login" method="post">
        <div align="center" style="padding-top: 45px;">
            <p style="font-size:40px;color:white;">学生管理系统
                <br>
                <font style="font-size:13px;">STUDENT MANAGEMENT SYSTEM</font></p>
            <input type="text" value="${userName }" name="userName" id="userName" placeholder=" 用户名"
                   style="width:200px;height:28px;border-radius:5px;border:0px;padding-left:10px;"/>
            <br>
            <br>
            <input type="password" value="${password }" name="password" id="password" placeholder=" 密码"
                   style="width:200px;height:28px;border-radius:5px;border:0px;padding-left:10px;"/>
            <br>
            <br>
            <input type="submit" value="立 即 登 录" onmouseover="this.style.backgroundColor='rgb(1,70,109)';"
                   onmouseout="this.style.backgroundColor='rgb(0,118,209)';"
                   style="border:0px;width:200px;height:33px;margin-left:-5px;font-family:黑体;font-size:16px;background:rgb(0,118,209);color:white;border-radius:5px;">

        </div>
        <div align="center" style="padding-top: 20px;">
            <tr height="10">
                <td width="40%"></td>
                <td colspan="2">

                    <font color="red">${error }</font>
                </td>
            </tr>
        </div>

    </form>

</div>
<div style="height:100%;width: 100%;
    position: absolute;
	overflow:hidden;
	background:-webkit-linear-gradient(top,#0076d1,#5db8ff);
	background:-moz-linear-gradient(top,#0076d1,#5db8ff);
	background:-o-linear-gradient(top,#0076d1,#5db8ff);
	background:-ms-linear-gradient(top,#0076d1,#5db8ff)">
    <canvas id="waves" class="waves"></canvas>
</div>


<script src="js/vendors.js"></script>
<script src="js/index.js"></script>


</body>
</html>

