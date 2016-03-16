 /*
弹出插件 AND 分享插件
autho：smohan
http://www.smohan.net
*/

//这是弹出层，IE9以下无法圆角
;
(function($) {
    $.fn.SmohanPopLayer = function(options) {
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
                $('#layer_' + options.Content).animate({opacity: 'show'}, "fast", function() {
					$('.Smohan_Layer_Shade').remove();
					var ht = '<div class="Smohan_Layer_Shade"></div>';
					$('body').prepend(ht);
					$('.Smohan_Layer_Shade').show();
                });
            });
        }
        $('.Smohan_Layer_box .close').click(function(e) {
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
						alert("ss");
					}
					else
					{
						alert(totalstring);
					}
					
				}
        });
    };
})(jQuery);

//分享	

