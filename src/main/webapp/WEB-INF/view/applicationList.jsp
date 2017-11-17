<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"  %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<table id="application_tg" class="easyui-datagrid" title="用户申请表"  fit=true
       data-options="
			singleSelect:false,
			collapsible:true,
			toolbar:'#application_toolBar',
			pagination:true,
			pageSize:5,
			pageNumber:1,
			pageList: [5,10,15,20],
			url:'application/findApplicationByPage',
			method:'post'">
    <thead>
    <tr>
        <th data-options="field:'ck',width:60,checkbox:true" align="center"></th>
        <th data-options="field:'id',width:80" align="center">编号</th>
        <th data-options="field:'username',width:80,formatter:formateUsername" align="center">借款姓名</th>
        <th data-options="field:'userphone',width:80,formatter:formateUserphone" align="center">借款人手机号</th>
        <th data-options="field:'money',width:80" align="center">借款金额</th>
        <th data-options="field:'getdate',width:80" align="center">借款期限</th>
        <th data-options="field:'userfor',width:80" align="center">借款用途</th>
        <th data-options="field:'returnmoney',width:80" align="center">应还金额</th>
        <th data-options="field:'state',width:80" align="center">状态</th>
    </thead>
</table>
<div id="application_toolBar">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit"  onclick="applicationUpdate()">更新</a>
    <div id="med_win" >
        手机号:<input class="easyui-textbox" style="width:100px" name="userphone">&nbsp;&nbsp;
        姓名:<input class="easyui-textbox" style="width:100px" name="username">&nbsp;&nbsp;
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearchApplication()">查询</a>
    </div>
</div>
<div id="dlgApplication" class="easyui-dialog" style="width:450px;height:200px;padding:-10px -20px;"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="10px;">
            <input id="applicationid" name="id" class="easyui-validatebox" style="width: 200px;display: none;">
            <tr>
                <td>状态：</td>
                <td>
                    <select id="states" name="state" style="width: 150px;">
                        <option value="1">已申请</option>
                        <option value="2">已借款</option>
                        <option value="3">已还款</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveApplication()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgApplication').dialog('close')">关闭</a>
</div>
<script>
    function formateUserphone(value,row,index){
        if(row.user){
            return  row.user.userphone;
        }
    }
    function formateUsername(value,row,index){
        if(row.user){
            return  row.user.username;
        }
    }

    function doSearchApplication(){
        $('#application_tg').datagrid('load',{
            userphone:$("input[name='userphone']").val(),
            username:$("input[name='username']").val(),
        });
    }

    function applicationUpdate(){
        var row = $('#application_tg').datagrid('getSelected');
        if(row){
            $("#dlgApplication").dialog('open').dialog('setTitle','编辑借款申请');
            if(row.state=="已申请"){
                row.state=1;
            }else if(row.state=="已借款"){
                row.state=2;
            }else if(row.state=="已还款"){
                row.state=3;
            }
            $("#applicationid").val(row.id);
            $("#states").find("option[value = '"+row.state+"']").attr("selected","selected");

        } else{
            alert("请选择一行");
        }
    }

    function saveApplication(){
        $('#fm').form('submit',{
            url:'application/update',
            onSubmit:function(){
                return $(this).form('validate');
            },
            success:function(result){
                $('#dlgApplication').dialog('close');
                $("#application_tg").datagrid("reload");

            }
        });
    }

</script>