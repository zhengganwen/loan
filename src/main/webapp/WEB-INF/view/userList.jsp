<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"  %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<table id="user_tg" class="easyui-datagrid" title="用户列表"  fit=true
	   data-options="
			singleSelect:false,
			collapsible:true,
			toolbar:'#user_toolBar',
			pagination:true,
			pageSize:5,
			pageNumber:1,
			pageList: [5,10,15,20],
			url:'user/findUserByPage',
			method:'post'">
	<thead>
	<tr>
		<th data-options="field:'ck',width:60,checkbox:true" align="center"></th>
		<th data-options="field:'id',width:80" align="center">编号</th>
		<th data-options="field:'username',width:100" align="center">用户名</th>
		<th data-options="field:'userphone',width:100" align="center">用户手机号</th>
		<th data-options="field:'usercardnumber',width:100" align="center">用户身份证号码</th>
		<th data-options="field:'frontpicture',width:100,formatter:formatImg" align="center">身份证正面</th>
		<th data-options="field:'backpicture',width:100,formatter:formatImg" align="center">身份证反面</th>
		<th data-options="field:'truepicture',width:100,formatter:formatImg" align="center">自拍照片</th>
		<th data-options="field:'state',width:100" align="center">状态</th>
		<th data-options="field:'intime',width:100,formatter:formatterdate" align="center" >添加时间</th>
	</thead>
</table>
<div id="user_toolBar">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit"  onclick="userUpdate()">更新</a>
	<div id="med_win" >
		手机号:<input class="easyui-textbox" style="width:100px" name="userphone">&nbsp;&nbsp;
		姓名:<input class="easyui-textbox" style="width:100px" name="username">&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearchUser()">查询</a>
	</div>
</div>
<div id="dlgUser" class="easyui-dialog" style="width:450px;height:200px;padding:-10px -20px;"
	 closed="true" buttons="#dlg-buttons">
	<form id="fm" method="post">
		<table cellspacing="10px;">
			<input id="userid" name="id" class="easyui-validatebox" style="width: 200px;display: none;">
			<tr>
				<td>状态：</td>
				<td>
					<select id="states" name="state" style="width: 180px;">
						<option value="1">用户已注册</option>
						<option value="2">用户已实名认证</option>
						<option value="3">用户已实名认证通过</option>
						<option value="4">用户已实名认证失败</option>
					</select>
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="dlg-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgUser').dialog('close')">关闭</a>
</div>
	<script type="text/javascript">
		function formatImg(val,row){
		    return "<img style='width:80px;height:80px;' src="+val+">";
		}


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

        function doSearchUser(){
            $('#user_tg').datagrid('load',{
                userphone:$("input[name='userphone']").val(),
                username:$("input[name='username']").val(),
            });
        }

        function userUpdate(){
            var row = $('#user_tg').datagrid('getSelected');
            if(row){
                $("#dlgUser").dialog('open').dialog('setTitle','编辑用户信息');
                if(row.state=="用户已注册"){
                  row.state=1;
                }else if(row.state=="用户已实名认证"){
                    row.state=2;
                }else if(row.state=="用户已实名认证通过"){
                    row.state=3;
                }else{
                    row.state=4;
                }
                $("#userid").val(row.id);
                $("#states").find("option[value = '"+row.state+"']").attr("selected","selected");
            } else{
                alert("请选择一行");
            }
        }

        function saveUser(){
            $('#fm').form('submit',{
                url:'user/updateState',
                onSubmit:function(){
                    return $(this).form('validate');
                },
                success:function(result){
                        $('#dlgUser').dialog('close');
                        $("#user_tg").datagrid("reload");

                }
            });
		}

	</script>
