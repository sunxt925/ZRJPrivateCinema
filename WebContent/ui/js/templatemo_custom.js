"use strict";

$(document).ready(function($){	
		//$(".pagination").css("transform","scale(0.7)");
	/************** Gallery Hover Effect and mask*********************/

		$(function () {  
			var top= $(this).find(".overlay1").css("top");
           $(".hexagon-in2").hover(function () {  
                $(this).find(".overlay1").css("top","0"); 
				$(this).find(".overlay1").find("span").hide();
				var text = $(this).find("span").text();
				$("#modal-trigger").SmohanPopLayer({Shade: true,Event: 'click',Content: 'mask',Title: text});
			
            }, //mouseenter  
                function () {  
                     $(this).find(".overlay1").css("top",top); 
					 $(this).find(".overlay1").find("span").show();
                });//mouseleave  
        });  
		
	/**************poster center *********************/
               
});


