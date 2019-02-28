<%--
  Created by IntelliJ IDEA.
  User: chengsilong
  Date: 2018/12/5
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>班级信息管理</title>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        var url;

        /*查询*/
        function searchGrade() {
            $('#dg').datagrid('load', {
                gradeName: $('#s_gradeName').val()

            });

        }

        function resetgrade() {
            $("#s_gradeName").val("");
            searchGrade();
        }

        /*删除班级*/
        function deleteGrade() {
            var selectedRows = $("#dg").datagrid('getSelections');/*获取选择项的集合*/
            if (selectedRows.length == 0) {
                $.messager.alert("系统提示", "请选择要删除的数据！");
                return;
            }
            var strIds = [];/*存储选中班级的ID*/
            for (var i = 0; i < selectedRows.length; i++) {
                strIds.push(selectedRows[i].id);/*获取ID存入数组*/
            }
            var ids = strIds.join(",");/*数组中的所有元素放入一个字符串*/
            $.messager.confirm("系统提示", "您确认要删掉这<font color=red>" + selectedRows.length + "</font>条数据吗？", function (r) {
                if (r) {
                    $.post("gradeDelete", {delIds: ids}, function (result) {
                        if (result.success) {
                            $.messager.alert("系统提示", "您已成功删除<font color=red>" + result.delNums + "</font>条数据！");
                            $("#dg").datagrid("reload");
                        } else {
                            $.messager.alert('系统提示', '<font color=red>' + selectedRows[result.errorIndex].gradeName + '</font>' + result.errorMsg);
                        }
                    }, "json");
                }
            });
        }

        /*添加班级*/
        function openGradeAddDialog() {
            $("#dlg").dialog("open").dialog("setTitle", "添加班级信息");
            url = "gradeSave";
        }

        /*保存*/
        function saveGrade() {
            $("#fm").form("submit", {
                url: url,
                onSubmit: function () {
                    return $(this).form("validate");//验证是否填写班级名
                },
                success: function (result) {
                    if (result.errorMsg) {
                        $.messager.alert("系统提示", result.errorMsg)
                    } else {
                        $.messager.alert("系统提示", "保存成功");
                        resetValue();
                        $("#dlg").dialog("close");
                        $("#dg").datagrid("reload");
                    }
                }
            });
        }

        /*关闭*/
        function closeGradeDialog() {
            $("#dlg").dialog("close");
            resetValue();
        }

        function resetValue() {
            $("#gradeName").val("");
            $("#gradeDesc").val("");
        }

        /*修改*/
        function openGradeModifyDialog() {
            var selectedRows = $("#dg").datagrid('getSelections');/*获取中的集合*/
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据！");
                return;
            }
            var row = selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle", "编辑班级信息");
            $("#fm").form("load", row);/*将值填进去*/
            url = "gradeSave?id=" + row.id;/**/
        }

    </script>
</head>
<body style="margin: 5px;">
<table id="dg" title="班级信息" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true" url="gradeList" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>
        <th field="id" width="50">编号</th>
        <th field="gradeName" width="100">班级名</th>
        <th field="gradeDesc" width="250">班级描述</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        <a href="javascript:openGradeAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openGradeModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteGrade()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div>&nbsp;班级名称：&nbsp;<input type="text" name="s_gradeName" id="s_gradeName"/><a href="javascript:searchGrade()"
                                                                                     class="easyui-linkbutton"
                                                                                     iconCls="icon-search" plain="true">搜索</a>
        &nbsp; <a href="javascript:resetgrade()" class="easyui-linkbutton" iconCls="icon-reload" plain="true">重置</a>
    </div>

    <div id="dlg" class="easyui-dialog" style="width: 350px;height: 240px;padding: 10px 20px;"
         closed="true" buttons="#dlg-buttons">
        <form id="fm" method="post">
            <table>
                <tr>
                    <td>班级名称：</td>
                    <td><input type="text" name="gradeName" id="gradeName" class="easyui-validatebox" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td valign="top">班级描述：</td>
                    <td><textarea rows="7" cols="23" name="gradeDesc" id="gradeDesc"></textarea></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:saveGrade()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
        <a href="javascript:closeGradeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
    </div>

</div>
</body>
</html>
