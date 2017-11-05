
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"  %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>借贷管理后台</title>

    <link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>

    <style type="text/css">
        a{
            text-decoration: none;
        }

    </style>

</head>

<body>
<div id="main_layout" class="easyui-layout" fit=true>

    <div data-options="region:'north'" style="height:50px">
        <h1 align="center">借贷管理后台</h1>
    </div>

    <div data-options="region:'west',split:true,collapsible:false,iconCls:'icon-ok'" title="系统菜单" style="width:180px;">
        <div class="easyui-accordion" fit=true>
            <div title="系统管理" data-options="iconCls:'icon-ok'">
                <p align="center"  data-options="iconCls:'icon-ok'">
                    <img  src="js/easyui/themes/icons/man.png">
                    <a class="creat_tab"  href="user/userList" style="color:black ">用户列表</a>
                </p>

                <p align="center"  data-options="iconCls:'icon-ok'">
                    <img  src="js/easyui/themes/icons/man.png">
                    <a class="creat_tab" href="" style="color:black ">借贷列表</a>
                </p>
            </div>


        </div>

    </div>

    <div data-options="region:'center',iconCls:'icon-ok'">
        <div id="tab_panel" class="easyui-tabs" data-options="fit:true">
            <div data-options="title:'首页'">
                <p align="center" style="color: blue;font-size: 30px;"> 欢迎进入我的系统</p>
            </div>
        </div>
    </div>

    <div data-options="region:'east',split:true" title="栏目" style="width:180px;"></div>

    <div data-options="region:'south',split:true" style="height:80px;text-align: center;font-size:20px; "><%= new Date().toLocaleString() %></div>
</div>
<script type="text/javascript">
    $("#main_layout").on("click",".creat_tab",function(ev){
        ev.preventDefault(); //阻止超链接默认行为
        var title=$(this).text();
        var url=$(this).attr("href");
        addpanel(title,url);


    })

    function addpanel(title,url){
        var tit =$("#tab_panel").tabs("getTab",title);
        if(tit){
            $("#tab_panel").tabs('select',title);
            title.panel('refresh', url);

        }else{
            $("#tab_panel").tabs('add',{
                title:title,
                closable:true,
                href: url,
            });
        }
    }
</script>
</body>
</html>
