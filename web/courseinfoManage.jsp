<%--
  Created by IntelliJ IDEA.
  User: chengsilong
  Date: 2018/12/8
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>课程信息管理</title>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        var url;

        /*查询*/
        function searchCourse() {
            $('#dg').datagrid('load', {
                couname: $('#c_couname').val(),
                counteacher: $('#c_counteacher').val()
            });

        }

        function resetcourse() {
            $("#c_couname").val("");
            $("#c_counteacher").val("");
            searchCourse();
        }

        /*删除课程*/
        function deleteCourse() {
            var selectedRows = $("#dg").datagrid('getSelections');/*获取选择项的集合*/
            if (selectedRows.length == 0) {
                $.messager.alert("系统提示", "请选择要删除的数据！");
                return;
            }
            var strIds = [];/*存储选中课程的ID*/
            for (var i = 0; i < selectedRows.length; i++) {
                strIds.push(selectedRows[i].couid);/*获取ID存入数组*/
            }
            var ids = strIds.join(",");/*数组中的所有元素放入一个字符串*/
            $.messager.confirm("系统提示", "您确认要删掉这<font color=red>" + selectedRows.length + "</font>条数据吗？", function (r) {
                if (r) {
                    $.post("courseDelete", {delIds: ids}, function (result) {
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

        /*添加课程*/
        function openCourseAddDialog() {
            $("#dlg").dialog("open").dialog("setTitle", "添加课程信息");
            url = "courseSave1";
        }

        /*保存*/
        function saveCourse() {
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
        function closeCourseDialog() {
            $("#dlg").dialog("close");
            resetValue();
        }

        function resetValue() {
            $("#couname").val("");
            $("#counteacher").val("");
            $("#coudesc").val("");
        }

        /*修改*/
        function openCourseModifyDialog() {
            var selectedRows = $("#dg").datagrid('getSelections');/*获取中的集合*/
            if (selectedRows.length != 1) {
                $.messager.alert("系统提示", "请选择一条要编辑的数据！");
                return;
            }
            var row = selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle", "编辑课程信息");
            $("#fm").form("load", row);/*将值填进去*/
            url = "courseSave1?id=" + row.couid;/**/
        }

    </script>
</head>
<body style="margin: 5px">
<table id="dg" title="课程信息" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true" url="courseSave" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>
        <th field="couid" width="50" align="center">课程编号</th>
        <th field="couname" width="100" align="center">课程名称</th>
        <th field="counteacher" width="100" align="center">任课老师</th>
        <th field="coudesc" width="100" align="center">注释信息</th>

    </tr>
    </thead>
</table>

<div id="tb" style="margin-bottom: 5px">
    <div>
        <a href="javascript:openCourseAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openCourseModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteCourse()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>

    <div>&nbsp;课程名称：&nbsp;<input type="text" name="c_couname" id="c_couname"/>
        &nbsp;任课老师：&nbsp;<input type="text" name="c_counteacher" id="c_counteacher"/>
        <a href="javascript:searchCourse()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
        &nbsp; <a href="javascript:resetcourse()" class="easyui-linkbutton" iconCls="icon-reload" plain="true">重置</a>

    </div>

    <div id="dlg" class="easyui-dialog" style="width:380px;height: 260px;padding: 10px 20px"
         closed="true" buttons="#dlg-buttons">
        <form id="fm" method="post">
            <table>
                <tr>
                    <td>课程名称：</td>
                    <td><input type="text" name="couname" id="couname" class="easyui-validatebox" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td>任课老师：</td>
                    <td><input type="text" name="counteacher" id="counteacher" class="easyui-validatebox"
                               required="true"/>
                    </td>
                </tr>
                <tr>
                    <td valign="top">课程描述：</td>
                    <td><textarea rows="7" cols="23" name="coudesc" id="coudesc"></textarea></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:saveCourse()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
        <a href="javascript:closeCourseDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
    </div>
</div>


</body>


</html>
