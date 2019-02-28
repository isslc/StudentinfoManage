<%--
  Created by IntelliJ IDEA.
  User: chengsilong
  Date: 2018/12/5
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息管理系统主界面</title>
    <%
        //权限验证
        if (session.getAttribute("currentUser") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
    %>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="css/xlcd.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">


        //下拉菜单不遮住
        $(function () {
            $(document.body).layout('panel', 'north').parent().add('html,body').css('overflow', 'inherit')
        })
        $(function () {
            var treeDate = [{
                text: "学生管理 ▼",
                children: [{
                    text: "班级信息管理",
                    attributes: {
                        url: "gradeInfoManage.jsp"
                    }
                }, {
                    text: "学生信息管理",
                    attributes: {
                        url: "studentinfoManage.jsp"
                    }

                }, {
                    text: "课程信息管理",
                    attributes: {
                        url: "courseinfoManage.jsp"
                    }

                }, {
                    text: "成绩信息管理",
                    attributes: {
                        url: "scoreinfoManage.jsp"
                    }

                }]
            }];
            //实例化树菜单
            $("#tree").tree({
                data: treeDate,
                lines: true,
                onClick: function (node) {
                    if (node.attributes) {
                        openTab(node.text, node.attributes.url);
                    }
                }
            });

            //新增Tab
            function openTab(text, url) {
                //判断是否被打开 打开就选中
                if ($("#tabs").tabs('exists', text)) {
                    $("#tabs").tabs('select', text);
                } else {
                    var content = "<iframe frameborder='0' scrolling='auto' style='width: 100%;height: 100%' src=" + url + "></iframe>"
                    $("#tabs").tabs('add', {
                        title: text,
                        closable: true,
                        content: content
                    });
                }
            }
        });
    </script>

</head>
<body class="easyui-layout">
<div region="north" style="overflow-x: hidden;overflow-y: hidden;">
    <div style="position: absolute;margin-left: 20px;margin-top: -10px;"><img
            src="${pageContext.request.contextPath}/images/tbbbo.png" style="width: 32%;height: 15%;"></div>
    <span style="font-size:50px;color:white;position: absolute;margin-left:130px;margin-top:12px;">学生管理系统</span>
    <div align="left" style="width: 80%;float: left"><img src="${pageContext.request.contextPath}/images/dhl.jpg"
                                                          style="width:130%;height:100px;"></div>

    <a href="" style="position: absolute;margin-left:7.7%;margin-top:9px;">
        <img src="images/wh.png" style="width:18px;height:18px;">
    </a>
    <a href="http://www.java1234.com"
       style="color:white;text-decoration:none;position: absolute;margin-left:9%;margin-top:10px;">帮助</a>
    <a href="" style="color:white;text-decoration:none;position: absolute;margin-left:11%;margin-top:10px;">|</a>
    <a href="http://www.java1234.com"
       style="color:white;text-decoration:none;position: absolute;margin-left:11.7%;margin-top:10px;">关于</a>
    <a href="" style="color:white;text-decoration:none;position: absolute;margin-left:13.6%;margin-top:10px;">|</a>
    <a href="index.jsp" style="color:white;text-decoration:none;position: absolute;margin-left:14.3%;margin-top:10px;">退出</a>
    <div style="width:120px;
						 height: 20px;
						 background-color: rgba(1,70,109,0.8);
						 border-radius: 20px;
						 -webkit-border-radius: 10px;
						  position: absolute;
						  margin-left:88%;
						  margin-top:60px;"></div>
    <div style="margin-top: 61px;margin-left:89%;position: absolute;"><img src="images/tx.png"
                                                                           style="width:16px;height:16px;"></div>
    <div class="select">
            <span><a href="javascript:void(0);">
            <font color="white">${currentUser.userName }</font>
            </a></span>
        <div class="drop">
            <ul>
                <li>
                    <a href="javascript:void(0);">当前账户</a>
                </li>
                <li>
                    <a href="index.jsp">退出账号</a>
                </li>
            </ul>
        </div>
    </div>


</div>
<div region="center">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
        <div title="首页">
            <div>
                <%--
                                <span style="position: absolute;color:white;font-size: 50px;margin-left: 32%;margin-top: 15%">学生管理系统</span>
                --%>
            </div>
            <div align="center"><img src="${pageContext.request.contextPath}/images/hysy.jpg"
                                     style="width:100%;height: 100%"></div>
        </div>
    </div>
</div>
<div region="west" style="width: 150px; " title="导航菜单" split="true">
    <ul id="tree"></ul>
</div>
<div region="south" style="height: 25px; background-color: #0069a2" align="center">版权所有<a href="https://longzzz.cn">www.longzzz.com</a></div>
</body>
</html>
