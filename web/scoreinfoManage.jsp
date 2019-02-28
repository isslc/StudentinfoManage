<%--
  Created by IntelliJ IDEA.
  User: chengsilong
  Date: 2018/12/8
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生成绩表单</title>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        var url = "";

        function searchScore() {
            $('#dg').datagrid('load', {

                stuId: $('#s_stuId').combobox("getValue"),
                couid: $('#s_couid').combobox("getValue"),
                scscoure: $('#s_scscoure').val(),
                scscoure1: $('#s_scscoure1').val(),
            });
        }

        function resetscore() {
            $("#s_stuId").combobox("setValue", "");
            $("#s_couid").combobox("setValue", "");
            $("#s_scscoure").val("");
            $("#s_scscoure1").val("");
            searchScore();

        }

        /*删除班级*/
        function deleteScore() {
            var selectedRows = $("#dg").datagrid('getSelections');/*获取选择项的集合*/
            if (selectedRows.length == 0) {
                $.messager.alert("系统提示", "请选择要删除的数据！");
                return;
            }
            var strIds = [];/*存储选中班级的ID*/
            for (var i = 0; i < selectedRows.length; i++) {
                strIds.push(selectedRows[i].scid);/*获取ID存入数组*/
            }
            var ids = strIds.join(",");/*数组中的所有元素放入一个字符串*/
            $.messager.confirm("系统提示", "您确认要删掉这<font color=red>" + selectedRows.length + "</font>条数据吗？", function (r) {
                if (r) {
                    $.post("scoreDelete", {delIds: ids}, function (result) {
                        if (result.success) {
                            $.messager.alert("系统提示", "您已成功删除<font color=red>" + result.delNums + "</font>条数据！");
                            $("#dg").datagrid("reload");//重新加载文档
                        } else {
                            $.messager.alert('系统提示', result.errorMsg);
                        }
                    }, "json");
                }
            });
        }

        /**
         * 添加 studentSave
         */
        function openScoreAddDialog() {
            $("#dlg").dialog("open").dialog("setTitle", "添加学生信息");
            url = "scoreSave";
        }

        /*保存*/
        function saveScore() {
            $("#fm").form("submit", {
                url: url,
                onSubmit: function () {
                    if ($('#stuId').combobox("getValue") == "") {
                        $.messager.alert("系统提示", "请选择姓名");
                        return false;
                    }
                    if ($('#couid').combobox("getValue") == "") {
                        $.messager.alert("系统提示", "请选择所属课程");
                        return false;
                    }
                    return $(this).form("validate");
                },
                success: function (result) {
                    if (result.errorMsg) {
                        $.messager.alert("系统提示", result.errorMsg);
                        return;
                    } else {
                        $.messager.alert("系统提示", "保存成功");
                        resetValue();
                        $("#dlg").dialog("close");
                        $("#dg").datagrid("reload");
                    }
                }
            });
        }

        function resetValue() {
            $("#stuId").combobox("setValue", "");
            $("#couid").combobox("setValue", "");
            $("#scscore").val("");
        }

        function closeScoreDialog() {
            $("#dlg").dialog("close");
            resetValue();
        }

        function openScoreModifyDialog() {
            var selectedRows = $("#dg").datagrid('getSelections');
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据！");
                return;
            }
            var row = selectedRows[0];

            $("#dlg").dialog("open").dialog("setTitle", "编辑学生信息");
            $("#fm").form("load", row);
            url = "scoreSave?scid=" + row.scid;
        }


    </script>
</head>
<body style="margin: 5px">
<table id="dg" title="课程信息" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true" url="scoreList" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>
        <th field="scid" width="50" align="center">成绩编号</th>

        <th field="scstuid" width="100" align="center" hidden="true">学生ID</th>
        <th field="stuName" width="100" align="center">学生名称</th>

        <th field="sccouid" width="100" align="center" hidden="true">课程ID</th>
        <th field="couname" width="100" align="center">课程名称</th>

        <th field="scscore" width="150" align="center">成绩</th>
    </tr>
    </thead>
</table>

<div id="tb" style="margin-bottom: 5px">
    <div>
        <a href="javascript:openScoreAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openScoreModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteScore()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>


    <div>
        &nbsp;学生姓名：&nbsp;<input class="easyui-combobox" id="s_stuId" name="s_stuId" size="10"
                                data-options="panelHeight:'auto',editable:false,valueField:'stuId',textField:'stuName',url:'comboList'"/>
        &nbsp;课程名称：&nbsp;<input class="easyui-combobox" id="s_couid" name="s_couid" size="10"
                                data-options="panelHeight:'auto',editable:false,valueField:'couid',textField:'couname',url:'courseList'"/>
        &nbsp;成绩大于：&nbsp;<input type="text" name="s_stuNo" id="s_scscoure" size="10"/>
        &nbsp;成绩小于：&nbsp;<input type="text" name="s_stuNo" id="s_scscoure1" size="10"/>
        <a href="javascript:searchScore()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
        &nbsp; <a href="javascript:resetscore()" class="easyui-linkbutton" iconCls="icon-reload" plain="true">重置</a>

    </div>


</div>
<div id="dlg" class="easyui-dialog" style="width: 570px;height: 350px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="5px;">

            <tr>
                <td>学生姓名：</td>
                <td><input class="easyui-combobox" id="stuId" name="stuId"
                           data-options="panelHeight:'auto',editable:false,valueField:'stuId',textField:'stuName',url:'comboList'"/>
                </td>
                <td></td>

            </tr>
            <tr>
                <td>课程名称：</td>
                <td><input class="easyui-combobox" id="couid" name="couid"
                           data-options="panelHeight:'auto',editable:false,valueField:'couid',textField:'couname',url:'courseList'"/>
                </td>
                <td></td>

            <tr>
                <td>学生成绩：</td>
                <td><input type="text" name="scscore" id="scscore" class="easyui-validatebox" required="true"/></td>

            </tr>

        </table>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:saveScore()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeScoreDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>
