<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<title>自然卷私人影院后台管理</title>
	<link id="easyuiTheme" rel="stylesheet" href="cms/themes/sunny/easyui.css" type="text/css"></link>
	<link rel="stylesheet" type="text/css" href="cms/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="cms/main/portal.css">
    <link rel="stylesheet" type="text/css" href="cms/main/common.css">
    <link rel="stylesheet" type="text/css" href="cms/main/css/style.css">
	<script type="text/javascript" src="cms/jquery-1.8.0.min.js"></script>
	
	<script type="text/javascript" src="cms/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="cms/jquery.portal.js"></script>
	<script type="text/javascript" src="cms/jquery.cookie.js"></script>
    <script type="text/javascript" src="cms/main/jeasyui.common.new.js"></script>
    <script type="text/javascript" src="cms/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
</head>

<body class="easyui-layout">

<noscript>
<div style="position:absolute; z-index:100000; height:246px;top:0px;left:0px; width:100%; background:white; text-align:center;">
<img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
</div>
</noscript>

	<div data-options="region:'north',border:false" style="height:60px;background:#fff;padding:0px">
    	<div class="site_title" >自然卷私人影院后台管理系统</div>
        <div id="sessionInfoDiv" style="position: absolute;right: 5px;top:10px;">
        	<%
        		String username = (String)request.getAttribute("username");
        		out.println("[<strong>"+username+"</strong>]，欢迎你！");
        	 %>            
        </div>
        <div style="position: absolute; right: 0px; bottom: 0px; ">
            <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_pfMenu',iconCls:'icon-ok'">更换皮肤</a> 
           
        </div>
        
        <div id="layout_north_pfMenu" style="width: 120px; display: none;">
            <div onclick="changeTheme('default');">default</div>
            <div onclick="changeTheme('gray');">gray</div>
            <div onclick="changeTheme('metro');">metro</div>
            <div onclick="changeTheme('cupertino');">cupertino</div>
            <div onclick="changeTheme('dark-hive');">dark-hive</div>
            <div onclick="changeTheme('pepper-grinder');">pepper-grinder</div>
            <div onclick="changeTheme('sunny');">sunny</div>
        </div>
        
        <div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
            <div onclick="userInfoFun();">个人信息</div>
            <div onclick="userInfoFun();">退出登录</div>
        </div>
        
        <div id="layout_north_zxMenu" style="width: 100px; display: none;">
            <div onclick="logoutFun();">锁定窗口</div>
            <div class="menu-sep"></div>
            <div onclick="logoutFun();">重新登录</div>
            <div onclick="logoutFun(true);">退出系统</div>
        </div>
    </div>
    
	<div data-options="region:'west',split:true,title:'导航菜单'" style="width:200px;">
        <div class="easyui-accordion sider" data-options="fit:true,border:false">
            <!--//左侧菜单导航-->
        	
    		
    
           
            <div title="电影信息管理" data-options="iconCls:'icon-mini-add'" style="padding:10px;">
        		<ul class="easyui-tree" data-options="animate:true">
        			<li><a href="javascript:viod(0);" cmshref="cms/main/movies/moviesManage.html" type="nav_head" rel="">电影管理</a></li>
        			<li><a href="javascript:viod(0);" cmshref="cms/main/trailers/trailersManage.html" type="nav_head" rel="">预告片管理</a></li>
        			<li><a href="javascript:viod(0);" cmshref="cms/main/lives/livesManage.html" type="nav_head" rel="">live管理</a></li>
        			<li><a href="javascript:viod(0);" cmshref="cms/main/tags/tagsManage.html" type="nav_head" rel="">标签管理</a></li>
        			<li><a href="javascript:viod(0);" cmshref="cms/main/messages/messagesManage.html" type="nav_head" rel="">提示信息管理</a></li>
        			<li><a href="javascript:viod(0);" cmshref="SenderAction!show.action" type="nav_head" rel="">上报频率管理</a></li>
            	</ul>
            </div><!--//左侧菜单导航-->
            <div title="站点管理" data-options="iconCls:'icon-mini-add'" style="padding:10px;">
        		<ul class="easyui-tree" data-options="animate:true">
        			<li><a href="javascript:viod(0);" cmshref="cms/main/sites/sitesManage.html" type="nav_head" rel="">站点管理</a></li>
            	</ul>
            </div><!--//左侧菜单导航-->
             <div title="用户管理" data-options="iconCls:'icon-mini-add'" style="padding:10px;">
        		<ul class="easyui-tree" data-options="animate:true">
        			<li><a href="javascript:viod(0);" cmshref="cms/main/users/userManage.html" type="nav_head" rel="">用户管理</a></li>
            	</ul>
            </div><!--//左侧菜单导航-->
        </div><!--accordion-->
    </div><!--west-->
    
	<div data-options="region:'south',border:false" style="height:50px;background:#fff;padding:10px;">
        <div id="footer">
            Copyright &copy; 2015 by 自然卷私人影院.<br>
            All Rights Reserved<br>
        </div>
    </div>
    
	<!--//主体内容部分-->
    <div data-options="region:'center'" class="indexcenter" title="欢迎使用自然私人影院后台管理系统">
        <div id="tabs_index" class="easyui-tabs"  fit="true" border="false"  >
            <div title="首页" style="overflow:hidden; " data-options="href:'cms/main/portal.html'">
            </div>
        </div>
    </div><!--center-->
    <!--//主体内容部分-->
    
    <div id="dialog_cms" data-options="iconCls:'icon-save'">
    </div>
    
</body>
</html>
