layui.define(['table', 'jquery', 'element'], function (exports) {
    "use strict";

    var MOD_NAME = 'xxbFrame',
        $ = layui.jquery,
		element = layui.element;
		
    var xxbFrame = function (opt) {
        this.option = opt;
    };

    xxbFrame.prototype.render = function (opt) {
        //默认配置值
		var option = {
			elem:opt.elem,
			url:opt.url,
			title:opt.title,
			width:opt.width,
			height:opt.height,
			done:opt.done ? opt.done: function(){ console.log("菜单渲染成功");}
		}
		
		
	    createFrameHTML(option);
	   
	    $("#"+option.elem).width(option.width);
	    $("#"+option.elem).height(option.height);
		
		return new xxbFrame(option);
    } 
	
	xxbFrame.prototype.changePage = function(url,title,loading){
		
		if(loading){
			var loading = $("#"+this.option.elem).find(".xxb-frame-loading");
			
			loading.css({display:'block'});
		}
		
		$("#"+this.option.elem+" iframe").attr("src",url);
	 
	    $("#"+this.option.elem+" .title").html(title);
	
	     if(loading){
	     	var loading = $("#"+this.option.elem).find(".xxb-frame-loading");
	     	
			setTimeout(function(){
				
				loading.css({display:'none'});
			
			},800)
	     	
	     }
	
	}
	
	xxbFrame.prototype.refresh = function (time) {
		
		
		// 刷 新 指 定 的 选 项 卡
		if(time!=false){
			
			var loading = $("#"+this.option.elem).find(".xxb-frame-loading");
			
			loading.css({display:'block'});
			
			if(time!=0){
				
				setTimeout(function(){
					loading.css({display:'none'});
				},time)
			}
			
		}
		
		$("#"+this.option.elem).find("iframe")[0].contentWindow.location.reload(true);
	}
	
	function createFrameHTML(option){
		
		 var title = "<div class='xxb-frame'><div class='xxb-frame-title'><div class='dot'></div><div class='title'>"+option.title+"</div></div>"
		 
		 var iframe = "<iframe class='xxb-frame-content' style='width:100%;height:100%;'  scrolling='auto' frameborder='0' src='"+option.url+"' ></iframe>"
	
	     var loading = '<div class="xxb-frame-loading">'+
			       '<div class="ball-loader">'+
				      '<span></span><span></span><span></span><span></span>'+
			       '</div>'+
		        '</div></div>'
	
	     $("#"+option.elem).html(title+iframe+loading);	
	}
	
	exports(MOD_NAME,new xxbFrame());
})