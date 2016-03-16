<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="cn.edu.cqu.csp.dao.movies.Movies"%>
<%@ page import="cn.edu.cqu.csp.dao.movies.MoviesDAO"%>
<%@ page import="cn.edu.cqu.csp.dao.trailers.Trailers"%>
<%@ page import="cn.edu.cqu.csp.dao.trailers.TrailersDAO"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String videoaddress = request.getParameter("address");
String flag = request.getParameter("flag");

if(flag.equals("trailers"))
{
	TrailersDAO moviesDAO = new TrailersDAO();
	Trailers movies = new Trailers();
	movies = (Trailers)((moviesDAO.findByFilepath(videoaddress)).get(0));
	movies.setCount(movies.getCount()+1);
	moviesDAO.update(movies);
	System.out.println(flag);
}
else
{
	MoviesDAO moviesDAO = new MoviesDAO();
	Movies movies = new Movies();
	movies = (Movies)((moviesDAO.findByFilepath(videoaddress)).get(0));
	movies.setCount(movies.getCount()+1);
	moviesDAO.update(movies);
	System.out.println(flag);
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<htmL>
<head>
	<script  src="js/jquery-1.6.js"></script>
	<script src="js/html5media.js"></script>
</head>
<script>


$(document).ready(function(){
var video = $('#video').get(0);
video.play();
video.webkitRequestFullScreen();//默认全屏播放
video.volume = 0.5;  //初始化声音为50%；
var aTime = 20; // 定义一次快进快退的时间为20s;
$(document).keydown(function(event){ 
if(event.keyCode == 37){ 
		 //左方向键，快退 定义每次快进快退时间为 +- 3秒
		 
		 video.currentTime = video.currentTime - aTime;
		}else if (event.keyCode == 39){ 
		//右方向键 快进
		video.currentTime = video.currentTime + aTime;
		}else if (event.keyCode == 32){ 
			if(video.paused) {
			  video.play();
		   }
		   else {
			  video.pause();
		   }
		}else if (event.keyCode == 38){
		//方向键up
		video.volume = video.volume + 0.1 ;
		}else if (event.keyCode == 40){ 
		//方向键down
		video.volume = video.volume - 0.1 ;
		}	
		else if (event.keyCode == 46){ 
		//ESC关闭电源播放
		window.close();
		}	
});

});
</script>
<body style="margin:0;padding:0;background-color:#000;" >

<video id="video" width="100%" height="100%" style="margin:0;padding:0;background-color:#000;" controls autoplay >
						<source src=<%=videoaddress%>></source>	
						<track src="test.vtt">		
	</video>

<!--
<object classid="clsid:67DABFBF-D0AB-41fa-9C46-CC0F21721616" width="100%" height="100%" codebase="http://go.divx.com/plugin/DivXBrowserPlugin.cab">
  <param name="custommode" value="none" />
  <param name="previewImage" value="" />
  <param name="autoPlay" value="true" />
  <param name="src" value=<%=videoaddress%> />
<embed type="video/divx" src=<%=videoaddress%> custommode="none" width="100%" height="100%" autoPlay="true" previewImage="" pluginspage="http://go.divx.com/plugin/download/">
</embed>

</object>
-->						

<!--<script type="text/javascript" language="JavaScript" src="js/s_code.js"></script>-->

</body>
</html>
