<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"  %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<table id="picture_tg" class="easyui-datagrid" title="照片表"  fit=true
       data-options="
			singleSelect:false,
			collapsible:true,
			toolbar:'#ptoolbar',
			url:'picture/findPictureList',
			method:'post'">
    <thead>
    <tr>
        <th data-options="field:'ck',width:60,checkbox:true" align="center"></th>
        <th data-options="field:'id',width:80" align="center">编号</th>
        <th data-options="field:'href',width:120" align="center">链接路径</th>
        <th data-options="field:'path',width:120,formatter:formatImg" align="center">照片</th>
        <th data-options="field:'intime',width:120,formatter:formatterdate" align="center">添加时间</th>
    </thead>
</table>
<div id="ptoolbar">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newPicture()">新增</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deletePicture()">删除</a>
</div>
<div id="dlgPicture" class="easyui-dialog" style="width:450px;height:400px;padding:-10px -20px;"
     closed="true" buttons="#dlgpic-buttons">
    <form id="fm" method="post">
        <table cellspacing="10px;">

            <tr>
                <td>链接路径：</td>
                <td><input name="href" class="easyui-validatebox" style="width: 200px;"></td>
            </tr>
            <tr>
                <td>照片内容：</td>
                <td><img src="" id="credentialfront"  alt="" style="width: 80px;height:80px;"/></td>
                    <input name="path" id="imagePath" style="display: none;" >
            </tr>
            <tr>
                <td>照片：</td>
                <td><input id="credentialfrontInput"  type="file" onchange="change_photo(event)"></td>
            </tr>

        </table>
    </form>
</div>

<div id="dlgpic-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="savePicture()">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgPicture').dialog('close')">关闭</a>
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

    function newPicture(){
        $("#dlgPicture").dialog('open').dialog('setTitle','添加照片');

    }

    function change_photo(e){
       // console.info(e.target.files[0]);//图片文件
        var dom =$("input[id^='credentialfrontInput']")[0];
      //  console.info(dom.value);//这个是文件的路径 C:\fakepath\icon (5).png
       // console.log(e.target.value);//这个也是文件的路径和上面的dom.value是一样的
        var reader = new FileReader();
        reader.onload = (function (file) {
            return function (e) {
               // console.info(this.result); //这个就是base64的数据了
              //  var sss=$("#showImage");
                $("#credentialfront")[0].src=this.result;
                $("#imagePath").val(this.result);
            };
        })(e.target.files[0]);
        reader.readAsDataURL(e.target.files[0]);
    }

    function savePicture(){
        $('#fm').form('submit',{
            url:'picture/insert',
            onSubmit:function(){
                return $(this).form('validate');
            },
            success:function(result){
                $('#dlgPicture').dialog('close');
                $("#picture_tg").datagrid("reload");
            }
        });
    }

    function deletePicture(){
        var row = $('#picture_tg').datagrid('getSelected');
        if(row){
            $.post('picture/delete',{id:row.id},function(result){
                $("#picture_tg").datagrid("reload");

            },'json');
        } else{
            alert("请选择一行");
        }
    }



</script>
