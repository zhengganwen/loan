<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"  %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<table id="med_tg" class="easyui-datagrid" title="用户列表"  fit=true
	   data-options="
			singleSelect:false,
			collapsible:true,
			toolbar:'#med_toolBar',
			pagination:true,
			pageSize:5,
			pageNumber:1,
			pageList: [5,10,15,20],
			url:'#',
			method:'get'">
	<thead>
	<tr>
		<th data-options="field:'ck',width:60,checkbox:true" align="center"></th>
		<th data-options="field:'userId',width:80" align="center">编号</th>
		<th data-options="field:'username',width:100" align="center">用户名</th>
		<th data-options="field:'userphone',width:100" align="center">用户手机号</th>
		<th data-options="field:'userphone',width:100" align="center">用户手机号</th>
		<th data-options="field:'userphone',width:100" align="center">用户手机号</th>
		<th data-options="field:'userphone',width:100" align="center">用户手机号</th>
		<th data-options="field:'userphone',width:100" align="center">用户手机号</th>
	</thead>
</table>
<div id="med_toolBar">
	<div>
		<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="medAdd()">添加新药</a>
		<a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="medDel()">删除药物</a>
	</div>
	<div id="med_win" class="easyui-window"  data-options="iconCls:'icon-save',closed:true" style="width:500px;height:200px;padding:10px;">
	</div>

</div>
	<script type="text/javascript">
        function medAdd(){
            var title = "添加新药";
            var url = "medicine/medicineEdit.jsp";

            viewWin(title,url);

        }

        function medUpdate(){
            var row = $('#med_tg').datagrid('getSelected');
            if(row){
                alert(row.medid);
                var url = "";
                var title = "更新用户";
                viewWin(title,url);

            }
            else{
                alert("请选择一行");
            }



        }

        function medDel(){
            var medids="";
            var row = $('#med_tg').datagrid('getSelections');
            for(var i=0;i<row.length-1;i++){
                medids+=row[i].medid+","  ;
            }
            medids+=row[row.length-1].medid

            $.ajax({
                url:"medicineAction!delmedicine.action",
                type:"POST",
                data:{"rows":medids},    //如果值固定  可以
                dataType: "text",
                success:function(data){
                    data = eval('(' + data + ')');
                    $('#med_win').window('close');
                    $('#med_tg').datagrid('reload');
                }
            })
        }



        function viewWin(title,url){

            $('#med_win').window({
                width:400,
                height:200,
                title:title,
                modal:true,
                closed:false,
                href:url
            });
        }

        function medSearchForm(){

            var queryParam={};
            var queryArr = $("#med_searchform").serializeArray();
            $.each(queryArr,function(i,el){
                if(el.value){
                    alert(i+"-----------------------"+el.name +"----"+el.value);
                    queryParam[el.name] = el.value;
                }
            })
            $('#med_tg').datagrid('load',queryParam);
        }

	</script>
