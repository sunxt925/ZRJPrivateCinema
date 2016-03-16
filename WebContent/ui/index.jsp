<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="cn.edu.cqu.csp.dao.movies.Movies"%>
<%@ page import="cn.edu.cqu.csp.src.MovieUI"%>
<%@ page import="cn.edu.cqu.csp.dao.messages.Messages"%>
<%@ page import="cn.edu.cqu.csp.dao.messages.MessagesDAO"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
	<head>
		<meta charset="utf-8">
		<title>自然卷私人影院</title>
		<meta http-equiv="pragma" content="no-cache"  />
		<meta http-equiv="content-type" content="no-cache, must-revalidate" />
		<meta name="description" content="">
		<meta name="author" content="Ilya-Dorman" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<!--<link href="css/font-awesome.min.css" rel="stylesheet">-->
		<link rel="stylesheet" href="css/smohan.pop&share.css">
		<link rel="stylesheet" href="css/structure.css">
		<link rel="stylesheet" href="css/main.css">
		<link rel="stylesheet" href="css/mask.css">
		<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
		
		

		<script  src="js/jquery-1.6.js"></script>
		<!--<script src="js/smohan.pop&share.js"></script> >-->
		<script src="js/marquee.js"></script>
		<script src="js/tms-0.3.js"></script>
		<script  src="js/backgroundPosition.js"></script>
		<!--<script src="js/atooltip.jquery.js"></script>-->
		<!--<script  src="js/script.js"></script>-->
		<script  src="js/jquery.easing.1.3.js"></script>
		<script  src="js/tms_presets.js"></script>
		<script src="js/zrjui.js"></script> 
		<script src="js/html5media.js"></script> 
	</head>
	<body onkeydown="keysearch()">
	 <%

 		MovieUI movieUI = new MovieUI();
		
		List  movies = movieUI.getMoviesList("0","9",0);
		
		MessagesDAO messagesDAO = new MessagesDAO();
		
		Messages message = messagesDAO.findShow();
		
	%>

		<span class="secondKey" style="display:none"></span>
		<div class="top-ribbon"></div>
		<!--<div class="home"><a >自然卷<strong>私人影院</strong></a></div>-->
		 <!--nav start-->
 
			<div class="slider">
				<ul class="items">
					<li>
						<div class="banner">
						</div>
					</li>
				</ul>
				<ul class="pagination">
					<li id="banner1">
                      <a id="a0" href="#">
                         <img  src="images/left/icon1.png"/>
                         <span id="0" >新片速递</span>
                       </a>
                    </li>
					<li id="banner2"><a id="a1" href="#"><img  src="images/left/icon2.png"/><span id="1" >本周热门</span></a></li>
					<li id="banner3"><a id="a2" href="#"><img  src="images/left/icon3.png"/><span id="2" >本院推荐</span></a></li>
					<li id="banner4"><a id="a3" href="#"><img  src="images/left/icon4.png"/><span id="3" >精彩预告</span></a></li>
					<li id="banner5"><a id="a4" href="#"><img  src="images/left/icon5.png"/><span id="4" >L i v e</span></a></li>
					<li id="banner6"><a id="a5" href="#"><img  src="images/left/icon6.png"/><span id="5" >全部标签</span></a></li>
					<li id="banner7"><a id="a6" href="#"><img  src="images/left/icon7.png"/><span id="6" >全部电影</span></a></li>
				</ul>
			</div>

 <!--nav end-->
		
		<div class="main-container">
		<div class="main">
		<section class="gallery-hex">
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2"  style="background-image: url(<%=((Movies)movies.get(0)).getPhotopath()%>);">
							<img class="backborder" src="images/backborder.png"   style="width:100%;"/>
								<div  class="overlay1">
									<a id="modal-trigger" class="modal-trigger1" ><img src="images/play.png"   style="width:45px;margin-top:48%;"/></a>
									<span><%=((Movies)movies.get(0)).getMoviename()%></span>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->

				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2" style="background-image: url(<%=((Movies)movies.get(1)).getPhotopath()%>);">
							<img class="backborder" src="images/backborder.png"   style="width:100%;"/>
								<div  class="overlay1">
									<a id="modal-trigger" class="modal-trigger1"><img src="images/play.png"   style="width:45px;margin-top:48%;"/></a>
									<span><%=((Movies)movies.get(1)).getMoviename()%></span>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->

				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2" style="background-image: url(<%=((Movies)movies.get(2)).getPhotopath()%>);">
							<img class="backborder" src="images/backborder.png"   style="width:100%;"/>
								<div  class="overlay1">
									<a id="modal-trigger" class="modal-trigger1"><img src="images/play.png"   style="width:45px;margin-top:48%;"/></a>
									<span><%=((Movies)movies.get(2)).getMoviename()%></span>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->

				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2" style="background-image: url(<%=((Movies)movies.get(3)).getPhotopath()%>);">
							<img class="backborder" src="images/backborder.png"   style="width:100%;"/>
								<div  class="overlay1">
									<a id="modal-trigger" class="modal-trigger1"><img src="images/play.png"   style="width:45px;margin-top:48%;"/></a>
									<span><%=((Movies)movies.get(3)).getMoviename()%></span>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->

				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2" style="background-image: url(<%=((Movies)movies.get(4)).getPhotopath()%>);">
							<img class="backborder" src="images/backborder.png"   style="width:100%;"/>
								<div  class="overlay1">
									<a id="modal-trigger" class="modal-trigger1"><img src="images/play.png"   style="width:45px;margin-top:48%;"/></a>
									<span><%=((Movies)movies.get(4)).getMoviename()%></span>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->

				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2" style="background-image: url(<%=((Movies)movies.get(5)).getPhotopath()%>);">
							<img class="backborder" src="images/backborder.png"   style="width:100%;"/>
								<div  class="overlay1">
									<a id="modal-trigger" class="modal-trigger1"><img src="images/play.png"   style="width:45px;margin-top:48%;"/></a>
									<span><%=((Movies)movies.get(5)).getMoviename()%></span>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->

				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2" style="background-image: url(<%=((Movies)movies.get(6)).getPhotopath()%>);">
							<img class="backborder" src="images/backborder.png"   style="width:100%;"/>
								<div  class="overlay1">
									<a  id="modal-trigger" class="modal-trigger1"><img src="images/play.png"   style="width:45px;margin-top:48%;"/></a>
									<span><%=((Movies)movies.get(6)).getMoviename()%></span>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->

				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2" style="background-image: url(<%=((Movies)movies.get(7)).getPhotopath()%>);">
							<img class="backborder" src="images/backborder.png"   style="width:100%;"/>
								<div  class="overlay1">
									<a id="modal-trigger" class="modal-trigger1"><img src="images/play.png"   style="width:45px;margin-top:48%;"/></a>
									<span><%=((Movies)movies.get(7)).getMoviename()%></span>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->

				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2" style="background-image: url(<%=((Movies)movies.get(8)).getPhotopath()%>);">
							<img class="backborder" src="images/backborder.png"   style="width:100%;"/>
								<div  class="overlay1">
									<a id="modal-trigger" class="modal-trigger1"><img src="images/play.png"   style="width:45px;margin-top:48%;"/></a>
									<span><%=((Movies)movies.get(8)).getMoviename()%></span>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				
		</section><!--works-->
		</div>
		
		<!-----  all-live div ---->
		<div class="all-live" style="display:none;">
			<section class="gallery-hex">
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2" style="background-image: url(<%=((Movies)movies.get(8)).getPhotopath()%>);">
								<img class="backborder" src="images/backborder.png"   style="width:100%;"/>
								<div  class="overlay1">
									<a id="modal-trigger" class="modal-trigger1"></a>
									<span><%=((Movies)movies.get(8)).getMoviename()%></span>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2" style="background-image: url(<%=((Movies)movies.get(8)).getPhotopath()%>);">
								<img class="backborder" src="images/backborder.png"   style="width:100%;"/>
								<div  class="overlay1">
									<a id="modal-trigger" class="modal-trigger1"></a>
									<span><%=((Movies)movies.get(8)).getMoviename()%></span>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2" style="background-image: url(<%=((Movies)movies.get(8)).getPhotopath()%>);">
								<img class="backborder" src="images/backborder.png"   style="width:100%;"/>
								<div  class="overlay1">
									<a id="modal-trigger" class="modal-trigger1"></a>
									<span><%=((Movies)movies.get(8)).getMoviename()%></span>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2" style="background-image: url(<%=((Movies)movies.get(8)).getPhotopath()%>);">
								<img class="backborder" src="images/backborder.png"   style="width:100%;"/>
								<div  class="overlay1">
									<a id="modal-trigger" class="modal-trigger1"></a>
									<span><%=((Movies)movies.get(8)).getMoviename()%></span>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2" style="background-image: url(<%=((Movies)movies.get(8)).getPhotopath()%>);">
								<img class="backborder" src="images/backborder.png"   style="width:100%;"/>
								<div  class="overlay1">
									<a id="modal-trigger" class="modal-trigger1"></a>
									<span><%=((Movies)movies.get(8)).getMoviename()%></span>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2" style="background-image: url(<%=((Movies)movies.get(8)).getPhotopath()%>);">
								<img class="backborder" src="images/backborder.png"   style="width:100%;"/>
								<div  class="overlay1">
									<a id="modal-trigger" class="modal-trigger1"></a>
									<span><%=((Movies)movies.get(8)).getMoviename()%></span>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2" style="background-image: url(<%=((Movies)movies.get(8)).getPhotopath()%>);">
								<img class="backborder" src="images/backborder.png"   style="width:100%;"/>
								<div  class="overlay1">
									<a id="modal-trigger" class="modal-trigger1"></a>
									<span><%=((Movies)movies.get(8)).getMoviename()%></span>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2" style="background-image: url(<%=((Movies)movies.get(8)).getPhotopath()%>);">
								<img class="backborder" src="images/backborder.png"   style="width:100%;"/>
								<div  class="overlay1">
									<a id="modal-trigger" class="modal-trigger1"></a>
									<span><%=((Movies)movies.get(8)).getMoviename()%></span>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2" style="background-image: url(<%=((Movies)movies.get(8)).getPhotopath()%>);">
								<img class="backborder" src="images/backborder.png"   style="width:100%;"/>
								<div  class="overlay1">
									<a id="modal-trigger" class="modal-trigger1"></a>
									<span><%=((Movies)movies.get(8)).getMoviename()%></span>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2" style="background-image: url(<%=((Movies)movies.get(8)).getPhotopath()%>);">
								<img class="backborder" src="images/backborder.png"   style="width:100%;"/>
								<div  class="overlay1">
									<a id="modal-trigger" class="modal-trigger1"></a>
									<span><%=((Movies)movies.get(8)).getMoviename()%></span>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2" style="background-image: url(<%=((Movies)movies.get(8)).getPhotopath()%>);">
								<img class="backborder" src="images/backborder.png"   style="width:100%;"/>
								<div  class="overlay1">
									<a id="modal-trigger" class="modal-trigger1"></a>
									<span><%=((Movies)movies.get(8)).getMoviename()%></span>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
			</section>
		</div> <!-- all-live div end --->
		<!--all-live div ---->	
		
		
		<!-----  all-tags div ---->
		<div class="all-tags" style="display:none;">
			<section class="gallery-hex">
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2">
								<div  class="overlay2">
									<div class="tag">									
										<span  class="first-word">科</span>
										<span class="following-words">幻</span>
									</div>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2">
								<div  class="overlay2">
									<div class="tag">									
										<span  class="first-word">科</span>
										<span class="following-words">幻</span>
									</div>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2">
								<div  class="overlay2">
									<div class="tag">									
										<span  class="first-word">科</span>
										<span class="following-words">幻</span>
									</div>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2">
								<div  class="overlay2">
									<div class="tag">									
										<span  class="first-word">科</span>
										<span class="following-words">幻</span>
									</div>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2">
								<div  class="overlay2">
									<div class="tag">									
										<span  class="first-word">科</span>
										<span class="following-words">幻</span>
									</div>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2">
								<div  class="overlay2">
									<div class="tag">									
										<span  class="first-word">科</span>
										<span class="following-words">幻</span>
									</div>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2">
								<div  class="overlay2">
									<div class="tag">									
										<span  class="first-word">科</span>
										<span class="following-words">幻</span>
									</div>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2">
								<div  class="overlay2">
									<div class="tag">									
										<span  class="first-word">科</span>
										<span class="following-words">幻</span>
									</div>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2">
								<div  class="overlay2">
									<div class="tag">									
										<span  class="first-word">科</span>
										<span class="following-words">幻</span>
									</div>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2">
								<div  class="overlay2">
									<div class="tag">									
										<span  class="first-word">科</span>
										<span class="following-words">幻</span>
									</div>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2">
								<div  class="overlay2">
									<div class="tag">									
										<span  class="first-word">科</span>
										<span class="following-words">幻</span>
									</div>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
			</section>
		</div> <!-- all-tags div end --->
		
		<!--right nav-->

    	
	</div> 
		
	</div>  <!--中间主体部分-->
	<!--right nav start-->
			<div class="right-nav">
		<section class="gallery-hex">
			<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2">
								<div  class="overlay2">
									
									<span id="pagename" >新片速递</span>
									<br>
									<span id="page_count" ><%=movieUI.getNextPage()%></span>
									
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div id="previouspage"  class="hexagon-in2">
								<div  class="overlay2">
									<a href="#">
									<span>上一页</span>
									</a>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div id="nextpage"  class="hexagon-in2">
								<div  class="overlay2">
									<a href="#">
									<span>下一页</span>
									</a>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div id="search" class="hexagon-in2">
								<div  class="overlay2">
									<a href="#">
									<span>搜索</span>
									</a>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
		</section>
	</div> <!--right nav end-->
		<div class="search-open" style="">
            <div class="input-append">
            
                <input type="text" class="span3" placeholder="Search" />
				
                 <button type="submit" class="btn-u">Go<tton>            
            </div> 
        </div>
	<!--footer start-->
	<div class="footer">
	<div class="footer-content-center">
	<%=message.getContent()%>
	</div>
	</div><!--footer end-->
		<div id="mask" style="display:none;">
		<div class="detail">
			<div class="poster">
				<div class="imgBox">
					<!--<img src="images/gallery/1.jpg"/> 
					<video class="video" poster="images/gallery/1.jpg" width="100%" height="100%" controls autoload>
						<source src="http://192.168.0.123:8080/video/2014.mp4" media="only screen and (min-device-width: 568px)"></source>
						 
					</video>-->
					<img class="moviesphoto" src=""   style="width:100%;height:100%"/>
					<a class="moviesplay" href="#"  alt="点击播放电影">
					<img class="moviesphoto1" src="images/play.png"   style="width:75px;opacity:0.5;top:45%;"/>
					</a>
					
				</div>
				
			</div>
			<div class="info">
				<div class="tags"> <!--the tags of the movie start-->
					<section class="gallery-hex">
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2">
								<div  class="overlay2">
									<div class="attr-value">
									<span class="doubanscore">8.3</span>
									</div>
									<div class="attr-key">
									<span>豆瓣</span>
									</div>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->

				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2">
								<div  class="overlay2">
									<div class="attr-value">
									<span class="country">加拿大</span>
									</div>
									<div class="attr-key">
									<span>国家地区</span>
									</div>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2">
								<div  class="overlay2">
									<div class="attr-value">
									<span class="year">2009</span>
									</div>
									<div class="attr-key">
									<span>上映时间</span>
									</div>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2">
								<div  class="overlay2">
									<div class="attr-value">
									<span class="director">2009</span>
									</div>
									<div class="attr-key">
									<span>导演</span>
									</div>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2">
								<div  class="overlay2">
									<div class="attr-value">
									<span class="duration">2009</span>
									</div>
									<div class="attr-key">
									<span>片长</span>
									</div>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2">
								<div  class="overlay2">
									<div class="tag">									
										<span  id="tag0first" class="first-word0">爱</span>
										<span id="tag0second" class="following-words0">情</span>
									</div>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2">
								<div  class="overlay2">
									<div class="tag">									
										<span id="tag1first" class="first-word1">奇</span>
										<span id="tag1second" class="following-words1">幻</span>
									</div>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
				<div class="wrapper">
					<div class="hexagon">
						<div class="hexagon-in1">
							<div class="hexagon-in2">
								<div  class="overlay2">
									<div class="tag">									
										<span id="tag2first" class="first-word2">全</span>
										<span id="tag2second" class="following-words2">部</span>
									</div>
								</div>
							</div>
						</div>
					</div><!--hexagon-->
				</div><!--wrapper-->
		</section><!--works-->
		
			</div>
			<div class="descript">
		<p class="moviedescription">一个小男孩，父母是魔法师，被巫师界中邪恶的力量所杀害，唯独他一个人存活了下来，他就是哈利波特，一岁的他被姨妈家收养，在姨夫家饱受欺凌，度过十年及其痛苦的日子。从出生来，从来没有人为他过过生日，但是在他十一岁生日那天，一切都发生了变化，信使猫头鹰带来了一封神秘的信：邀请哈利去一个永远难忘的、不可思议的地方--霍格沃茨魔法学校。</p>
		</div>
				</div>
		</div>
	</div>

	<script>

	$(window).load(function(){
	$('.slider')._TMS({
		preset:'zabor',
		easing:'easeOutQuad',
		duration:0,
		pagination:true,
		banners:true,
		waitBannerAnimation:false,

	})
	$('.pagination li').hover(function(){
		if (!$(this).hasClass('current')) {
			$(this).find('a').stop().animate({backgroundPosition:'0 0'},600,'easeOutExpo', function(){$(this).parent().css({backgroundPosition:'0 0'})});
		}
	},function(){
		if (!$(this).hasClass('current')) {
			$(this).css({backgroundPosition:'0 0'}).find('a').stop().animate({backgroundPosition:'-250px 0'},600,'easeOutExpo');
		}
	})
})
</script>
</body>
</html>

