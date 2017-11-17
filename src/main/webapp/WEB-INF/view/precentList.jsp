<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"  %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<table id="precet_tg" class="easyui-datagrid" title="利率表"  fit=true
       data-options="
			singleSelect:false,
			collapsible:true,
			toolbar:'#precenttoolbar',
			pagination:true,
			pageSize:5,
			pageNumber:1,
			pageList: [5,10,15,20],
			url:'precent/findPreByPage',
			method:'post'">
    <thead>
    <tr>
        <th data-options="field:'ck',width:60,checkbox:true" align="center"></th>
        <th data-options="field:'id',width:80" align="center">编号</th>
        <th data-options="field:'setdate',width:120" align="center">借款期限</th>
        <th data-options="field:'percent',width:120" align="center">利息</th>
        <th data-options="field:'intime',width:120,formatter:formatterdate" align="center">添加时间</th>
    </thead>
</table>
<div id="precenttoolbar">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newPercent()">添加</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editPercent()">修改</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deletePercent()">删除</a>
</div>
<div id="dlg" class="easyui-dialog" style="width:450px;height:300px;padding:-10px -20px;"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="10px;">
            <tr>
                <td>借款天数：</td>
                <td><input name="setdate" class="easyui-validatebox" style="width: 200px;"></td>
            </tr>
            <tr>
                <td>借款利率：</td>
                <td><input name="percent" class="easyui-validatebox"  style="width: 200px;"></td>
            </tr>

        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="savePrecent()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">关闭</a>
</div>

<div id="dlgupdate" class="easyui-dialog" style="width:450px;height:300px;padding:-10px -20px;"
     closed="true" buttons="#dlgupdate-buttons">
    <form id="fmupdate" method="post">
        <table cellspacing="10px;">
            <input name="id" class="easyui-validatebox" style="width: 200px;display: none;">
            <tr>
                <td>借款天数：</td>
                <td><input name="setdate" class="easyui-validatebox" style="width: 200px;"></td>
            </tr>
            <tr>
                <td>借款利率：</td>
                <td><input name="percent" class="easyui-validatebox"  style="width: 200px;"></td>
            </tr>

        </table>
    </form>
</div>

<div id="dlgupdate-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="updatePrecent()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgupdate').dialog('close')">关闭</a>
</div>




<script type="text/javascript">
    function formatterdate(val, row) {
        var date = new Date(val);
        var year = date.getFullYear().toString();
        var month = (date.getMonth() + 1);
        var day = date.getDate().toString();
        var hour = date.getHours().toString();
        var minutes = date.getMinutes().toString();
        var seconds = date.getSeconds().toString();
        if (month < 10) {
            month = "0" + month;
        }
        if (day < 10) {
            day = "0" + day;
        }
        if (hour < 10) {
            hour = "0" + hour;
        }
        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        if (seconds < 10) {
            seconds = "0" + seconds;
        }
        return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
    }

function newPercent(){
    $('#fm').form('clear');
    $("#dlg").dialog('open').dialog('setTitle','添加支付通道商户信息');
}
    function savePrecent(){
        $('#fm').form('submit',{
            url:'precent/insert',
            onSubmit:function(){
                return $(this).form('validate');
            },
            success:function(result){
                $('#dlg').dialog('close');
                $("#precet_tg").datagrid("reload");
            }
        });
    }
    function editPercent(){
        var row = $('#precet_tg').datagrid('getSelected');
        if(row){
            $("#dlgupdate").dialog('open').dialog('setTitle','编辑利率');
            $('#fmupdate').form('load',row);
        } else{
            alert("请选择一行");
        }
    }

    function updatePrecent(){
        $('#fmupdate').form('submit',{
            url:'precent/update',
            onSubmit:function(){
                return $(this).form('validate');
            },
            success:function(result){
                $('#dlgupdate').dialog('close');
                $("#precet_tg").datagrid("reload");
            }
        });
    }

    function deletePercent(){
        var row = $('#precet_tg').datagrid('getSelected');
        if(row){
            $.post('precent/delete',{id:row.id},function(result){
                $("#precet_tg").datagrid("reload");

            },'json');
        } else{
            alert("请选择一行");
        }
    }



</script>
