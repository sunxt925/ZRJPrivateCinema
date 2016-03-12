
var allInfo;
var alli = 0;
$(document).ready(

//newPage("新片速递");
function(){
	//App.init();
	//alert("sb");


	newPage("新片速递");
	$("#nextpage").click(function(){
	$(".main").hide();
	nextPage();
  });
  
	$("#previouspage").click(function(){
	$(".main").hide();
	previousPage();
  });
  
  /*$("ul.pagination li").click(function(){
	var i = $(".pagination li").index($(this)+1);
	alert(i);
	//newPage(clickPage);
  });*/
  
  $("#a0").click(function(){
	var clickPage = $("#0").text();
	
	$(".all-live").hide();
	$(".main").hide();
	newPage(clickPage);
  });
  
  $("#a1").click(function(){
	var clickPage = $("#1").text();
	
	$(".all-live").hide();
	$(".main").hide();
	newPage(clickPage);
  });
  
  $("#a2").click(function(){
	var clickPage = $("#2").text();
	
	$(".all-live").hide();
	$(".main").hide();
	newPage(clickPage);
  });
  
  $("#a3").click(function(){
	var clickPage = $("#3").text();
	
	$(".all-live").hide();
	$(".main").hide();
	newPage(clickPage);
  });
  
  $("#a4").click(function(){
	var clickPage = $("#4").text();
	
	$(".all-live").hide();
	$(".main").hide();
	newPage(clickPage);
  });
  
  $("#a5").click(function(){
	var clickPage = $("#5").text();
	$(".main").hide();
	$(".all-live").hide();
	
	newPage(clickPage);
  });
  
  $("#a6").click(function(){
	var clickPage = $("#6").text();
	
	$(".all-live").hide();
	$(".main").hide();
	newPage(clickPage);
  });
  
  
  $(".btn-u").click(function(){
	var keyword = $(".span3").val();
	$(".span3").val("");
	
	$(".all-live").hide();
	$(".main").show();		 
	searchMovie(keyword);
  });
  
  
  $(".all-live").find(".hexagon-in2").click(function(){
	if($("#pagename").text()=="L i v e")
	{
		//alert(allInfo.lives[0].liveaddress);
		var tag = $(this).find("span").text();
		var i = 0;
		for(i=0; i<11;i++)
		{
			if(tag==allInfo.lives[i].livename)
				window.open(allInfo.lives[i].liveaddress,"newwindow","height=500,width=500,toolbar=no,menubar=no");
		}
	}
	else
	{
		
		$(".all-live").hide();
		$(".main").hide();		
		var tag = $(this).find("span").text();
		searchTagMovie(tag);
	}
	
	});

	/*$(".tags").find(".hexagon-in2").click(function(){
	alert("s");
	if($("#pagename").text()=="L i v e")
	{
		//alert(allInfo.lives[0].liveaddress);
		var tag = $(this).find("span").text();
		var i = 0;
		for(i=0; i<11;i++)
		{
			if(tag==allInfo.lives[i].livename)
				window.open(allInfo.lives[i].liveaddress,"newwindow","height=500,width=500,toolbar=no,menubar=no");
		}
	}
	else
	{
		
		$(".all-live").hide();
		$(".main").hide();		
		var tag = $(this).find("span").text();
		searchTagMovie(tag);
	}
	
	});*/	
 
	var top= $(this).find(".overlay1").css("top");
  $(".hexagon-in2").hover(function () {  
        $(this).find(".overlay1").css("top","0"); 
		$(this).find(".overlay1").find("span").hide();
		var text = $(this).find("span").text();
		var pagename = $("#pagename").text();
		//alert(text);
		if(text != null && text!=""&&pagename!="L i v e"&&pagename!="全部标签")
		{
			setMovieInfo(text);
			//alert(text);
			$("#modal-trigger").SmohanPopLayer({Shade: true,Event: 'click',Content:'mask',Title: text});
		}
		else
		{
			$("#modal-trigger").SmohanPopLayer({Shade: false,Event: '',Content:'',Title: ''});
		}
		}, //mouseenter  
        function () {  
			$(".Smohan_Layer_Shade").remove();
            $(this).find(".overlay1").css("top",top); 
			$(this).find(".overlay1").find("span").show();
        });//mouseleave  
         
		
});

function PageFade(r)
{
	PageLoad(r);
	if($("#pagename").text() == "L i v e")
		$(".all-live").fadeIn("2000");
	else if($("#pagename").text() == "全部标签")
		$(".all-live").fadeIn("2000");
	else 
		$(".main").fadeIn("2000");
}
function PageLoad(r)
{
	//$(".main").fadeOut("fast");
	//$(".main section div").css("background-image","url("+r.movies[0].photopath+")");
	var i=0;
	
	if(r.flag == "tags")
	{
		for(i=0;i<11;i++)
		{
			
			$(".all-live section div:eq(" + i*5 +")").find(".hexagon-in2").css("background-image","url("+r.tags[i].imageaddress+")");
			if(r.tags[i].tagname==null)
			{
				$(".all-live section div:eq(" + i*5 +")").find("span").text("");
			}	
			else
			{
				$(".all-live section div:eq(" + i*5 +")").find("span").text(r.tags[i].tagname);
			}
				
		}
		
	}
	
	if(r.flag == "lives")
	{
		for(i=0;i<11;i++)
		{
			$(".all-live section div:eq(" + i*5 +")").find(".hexagon-in2").css("background-image","url("+r.lives[i].imageaddress+")");
			if(r.lives[i].livename==null)
			{
				$(".all-live section div:eq(" + i*5 +")").find("span").text("");
			}	
			else
			{
				$(".all-live section div:eq(" + i*5 +")").find("span").text(r.lives[i].livename);
			}
				
		}
	}	
	
	/*if(r.flag == "trailers")
	{
		for(i=0;i<9;i++)
		{
			$(".main section div:eq(" + i*5 +")").find(".hexagon-in2").css("background-image","url("+r.trailers[i].photopath+")");
			if(r.movies[i].moviename==null)
				$(".main section div:eq(" + i*5 +")").find("span").text("");
			else
				$(".main section div:eq(" + i*5 +")").find("span").text(r.trailers[i].moviename);
		}
	}*/	
	
	if(r.flag == "movies" || r.flag == "trailers")
	{
		for(i=0;i<9;i++)
		{
			$(".main section div:eq(" + i*5 +")").find(".hexagon-in2").css("background-image","url("+r.movies[i].photopath+")");
			if(r.movies[i].moviename==null)
				$(".main section div:eq(" + i*5 +")").find("span").text("");
			else
				$(".main section div:eq(" + i*5 +")").find("span").text(r.movies[i].moviename);
		}
	}
	
	
	$("#page_count").text(r.nextpage);
	//$(".main").fadeOut("slow");
	
}

function nextPage()
{
	var name = $("#pagename").text();
	var nextpage = $("#page_count").text();
	var key = $("input").val();
	var secondkey = $(".secondKey").text();
	$.ajax({
		url:'MoviePageAction!NextPage.action',
		data:
		{	
			pagename:name,
			pagenum:nextpage,
			keyword:key,
			secondkey:secondkey
		},
		cache: false,
        dataType: 'JSON',
        success: function(r) 
		{
			if (r.success) 
			{
				allInfo = r;
				PageFade(r);
			}
			else
			{
				$.messager.show({
					msg: r.msg,
                    title: '提示'
                });
			}
		},
	});
}

function previousPage()
{
	var name = $("#pagename").text();
	var previouspage = $("#page_count").text();
	var key = $("input").val();
	var secondkey = $(".secondKey").text();
	$.ajax({
		url:'MoviePageAction!PreviousPage.action',
		data:
		{
			pagename:name,
			pagenum:previouspage,
			keyword:key,
			secondkey:secondkey
		},
		cache: false,
        dataType: 'JSON',
        success: function(r) 
		{
			if (r.success) 
			{
				allInfo = r;
				PageFade(r);
			}
			else
			{
				$.messager.show({
					msg: r.msg,
                    title: '提示'
                });
			}
		},
	});
}

function newPage(clickPage)
{
	//alert(clickPage);
	$(".secondKey").text("new");
	$("#pagename").text(clickPage);
	$.ajax({
		url:'PageAction!newPage.action',
		data:
		{
			page:clickPage
		},
		cache: false,
        dataType: 'JSON',
        success: function(r) 
		{
			if (r.success) 
			{
				allInfo = r;
				PageFade(r);
			}
			else
			{
				$.messager.show({
					msg: r.msg,
                    title: '提示'
                });
			}
		},
	});
}

function searchMovie(keyword)
{
	$(".secondKey").text(keyword);
	$("#pagename").text("搜索");
	$.ajax({
		url:'UISearchMoviesAction!SearchMovies.action',
		data:
		{
			keyword:keyword
		},
		cache: false,
        dataType: 'JSON',
        success: function(r) 
		{
			if (r.success) 
			{
				allInfo = r;
				PageFade(r);
			}
			else
			{
				$.messager.show({
					msg: r.msg,
                    title: '提示'
                });
			}
		},
	});
}

function searchTagMovie(keyword)
{
	$(".secondKey").text("tagsearch");
	$("#pagename").text(keyword);
	$.ajax({
		url:'UISearchMoviesAction!SearchMovies.action',
		data:
		{
			keyword:keyword
		},
		cache: false,
        dataType: 'JSON',
        success: function(r) 
		{
			if (r.success) 
			{
				allInfo = r;
				PageFade(r);
			}
			else
			{
				$.messager.show({
					msg: r.msg,
                    title: '提示'
                });
			}
		},
	});
}


function keysearch()
{
if(event.keyCode ==13)
{
	var keyword = $(".span3").val();
	$(".span3").val("");
	
	$(".main").show();		 
	searchMovie(keyword);
	$(".span3").val();
}

}

function click_a(ad,aflag,address)
{
	window.open(ad,"_top");
	
	$.ajax({
		url:'AddCountAction!AddCount.action',
		data:
		{
			flag:aflag,
			videoaddress:address
		},
		cache: false,
        dataType: 'JSON',
        success: function(r) 
		{
			if (r.success) 
			{
				
			}
			else
			{
				$.messager.show({
					msg: r.msg,
                    title: '提示'
                });
			}
		},
	});
	
}

/** 
* 获取本地IP地址 
*/ 
function getLocalIPAddress() 
{ 
	GetIP();
    function my_callback2(json) {
       alert(json.IP);
   }
   function GetIP(){
         EasyjQuery_Get_IP("my_callback2","full"); // full version
     } 

}

function setMovieInfo(moviename)
{
	
	//alert(allInfo.movies[0].moviename);
	var i = 0;
	var j = 0;
	for(i = 0; i<9; i++)
	{
		if(moviename == allInfo.movies[i].moviename)
		{
			//alli = alli+1;
			//alert(moviename);
			$(".doubanscore").text(allInfo.movies[i].doubanscore);
			
			if(allInfo.movies[i].country.length>4)
			{
				$(".country").css("font-size","14px");
				$(".country").text(allInfo.movies[i].country);
			}
			else
			{
				$(".country").css("font-size","25px");
				$(".country").text(allInfo.movies[i].country);
			}
			
			//$(".country").text(allInfo.movies[i].country);
			$(".year").text(allInfo.movies[i].year);
			if(allInfo.movies[i].director.length>4)
			{
				$(".director").css("font-size","14px");
				$(".director").text(allInfo.movies[i].director);
			}
			else
			{
				$(".director").css("font-size","25px");
				$(".director").text(allInfo.movies[i].director);
			}
			
			$(".duration").text(allInfo.movies[i].duration);
			$(".moviedescription").html(allInfo.movies[i].description);
			$(".moviesphoto").attr("src",allInfo.movies[i].photopath);
			//alert()
			$(".moviesplay").attr("href","javascript:click_a('f1://"+allInfo.movies[i].filepath+"','"+allInfo.flag+"','"+allInfo.movies[i].filepath+"');");
			var str=allInfo.movies[i].movietag;
			var tempStr;
			//$(".tag0first").text("haha");
			for(j = 0; j < 3; j++)
			{	
				//str = str.substring(0,str.indexOf("/")-1);
				tempStr=str.substring(0,str.indexOf("/"));
				$(".first-word"+j).text(tempStr.substring(0,1));
				$(".following-words"+j).text(tempStr.substring(1,tempStr.length));
				str = str.substring(str.indexOf("/")+1,str.length);
			}
		}
	}
	
	 $.fn.SmohanPopLayer = function(options) {
		 //alert(options.Content);
        var Config = {Shade: true,Event: "click",Content: "Content",Title: "Smohan.net"};
        var options = $.extend(Config, options);
		var totalstring;
		var string1;
		var string2;
		$('.Smohan_Layer_box').remove();
        var layerhtml = "";
        if (options.Shade == true) {
            layerhtml += '<div class="Smohan_Layer_Shade" style="display:none;"></div>';
        }
        layerhtml += '<div class="Smohan_Layer_box" style="width:' + 20 + 'px;height:' + 20 + 'px;'  +  'px;display:none;" id="layer_' + options.Content + '">';
        layerhtml += '<h3><b class="text">' + options.Title + '</b><a href="javascript:void(0)" class="close"></a></h3>';
        layerhtml += '<div class="layer_content">';
        //layerhtml += '<div class="loading" style="left:' + 20 + 'px;top:' + 20 + 'px;"></div>';
        layerhtml += '<div id="' + options.Content + '" style="display:block;">' + $("#" + options.Content).html() + '</div>';
        layerhtml += '</div>';
        layerhtml += '</div>';
        $('body').prepend(layerhtml);
        if (options.Event == "unload") {
            $('#layer_' + options.Content).animate({opacity: 'show'}, "slow", function() {
            });
        } else {
            $(this).live(options.Event, function(e) {
            	//alert(options);
                $('#layer_' + options.Content).animate({opacity: 'show'}, "fast", function() {
					$('.Smohan_Layer_Shade').remove();
					var ht = '<div class="Smohan_Layer_Shade"></div>';
					$('body').prepend(ht);
					$('.Smohan_Layer_Shade').show();
                });
            });
        }
        $('.Smohan_Layer_box .close').click(function(e) {
        	//alert("这是一个记录点B");
            $('.Smohan_Layer_box').animate({opacity: 'hide',marginTop: '-300px'}, "fast", function() {
				$('.Smohan_Layer_Shade').remove();
               // $('.Smohan_Layer_box').remove();
                //$('.Smohan_Layer_box .loading').show();
            });
        });
		
		$(".tags").find(".hexagon-in2").click(function() {
            $('.Smohan_Layer_box').animate({opacity: 'hide',marginTop: '-300px'}, "fast", function() {
				$('.Smohan_Layer_Shade').remove();
				
            });
			for(j = 0; j < 3; j++)
				{	
					//tempStr=str.substring(0,str.indexOf("/"));
					string1 = $(this).find(".first-word"+j).text();
					string2 = $(this).find(".following-words"+j).text();
					totalstring = string1 + string2;
					if(totalstring == "")
					{
						
					}
					else
					{
						searchTagMovie(totalstring);
					}
					
				}
        });
    };
	
}