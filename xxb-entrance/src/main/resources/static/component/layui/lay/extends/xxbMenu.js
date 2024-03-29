layui.define(['table', 'jquery', 'element'], function (exports) {
    "use strict";

    var MOD_NAME = 'xxbMenu',
        $ = layui.jquery,
		element = layui.element;
		
    var xxbMenu = function (opt) {
        this.option = opt;
    };

    xxbMenu.prototype.render = function (opt) {
        //默认配置值
		var option = {
			elem:opt.elem,
			async:opt.async ? opt.async : false,
			parseData:opt.parseData,
			url:opt.url,
			defaultOpen:opt.defaultOpen,
			control:opt.control,
			defaultMenu:opt.defaultMenu,
			accordion:opt.accordion,
			height:opt.height,
			theme:opt.theme,
			data:opt.data ? opt.data:[],
			done:opt.done ? opt.done: function(){ console.log("菜单渲染成功");}
		}
		
		
		// 根 据 请 求 方 式 获 取 数 据
		if(option.async){
			option.data =  getData(option.url);
		    if(option.parseData!==false){
				option.data = option.parseData(option.data);
		    }
		}
		
		if(option.control!=false){
			
			createMenuAndControl(option);
			
		}else{
			createMenu(option);
		}
	
		
	    // 渲 染 菜 单 结 束 并 完 成
		element.init();
		
		downShow(option);
		
		
		
		option.done();
		return new xxbMenu(option);
    } 

	
	/** 监听事件赋值 */
	xxbMenu.prototype.click = function(clickEvent){
		
		var _this = this;
		$("#"+_this.option.elem+" .site-demo-active").parent().click(function(){
			var dom = $(this).children(".site-demo-active");
			var data  = {menuId:dom.attr("menu-id"),menuTitle:dom.attr("menu-title"),menuIcon:dom.attr("menu-icon"),menuUrl:dom.attr("menu-url")};	
		    clickEvent(dom,data);
		});
		
	}
	
	
	xxbMenu.prototype.skin = function(skin){
		
		var menu = $(".xxb-nav-tree[lay-filter='"+this.option.elem+"']").parent();
	
	    menu.removeClass("dark-theme");
		menu.removeClass("light-theme");
		
		menu.addClass(skin);
	}
	
	
	xxbMenu.prototype.selectItem = function(xxbId){
		
		if(this.option.control != false){
			
			$("#"+this.option.elem+" a[menu-id='"+xxbId+"']").parents(".layui-side-scroll ").find("ul").css({display:"none"});
			
			$("#"+this.option.elem+" a[menu-id='"+xxbId+"']").parents(".layui-side-scroll ").find(".layui-this").removeClass("layui-this");
			
			$("#"+this.option.elem+" a[menu-id='"+xxbId+"']").parents("ul").css({display:"block"});
			
			var controlId = $("#"+this.option.elem+" a[menu-id='"+xxbId+"']").parents("ul").attr("xxb-id");
			
			$("#"+this.option.control).find(".layui-this").removeClass("layui-this");
			
			$("#"+this.option.control).find("[xxb-id='"+controlId+"']").addClass("layui-this");
		}
		
		
		// 根 据 菜 单 Id 选 中 菜 单 项 目
		
		
		$("#"+this.option.elem+" a[menu-id='"+xxbId+"']").parents(".xxb-nav-tree").find(".layui-nav-itemed").removeClass("layui-nav-itemed");
		
		$("#"+this.option.elem+" a[menu-id='"+xxbId+"']").parents(".xxb-nav-tree").find(".layui-this").removeClass("layui-this");
		
		// 通 过 动 画 打 开 当 前 菜 单 项 layui-nav-child
		
		if(!$("#"+this.option.elem).is(".xxb-nav-mini")){
		
		      $("#"+this.option.elem+" a[menu-id='"+xxbId+"']").parents(".layui-nav-item").addClass("layui-nav-itemed");
			  
		      $("#"+this.option.elem+" a[menu-id='"+xxbId+"']").parents("dd").addClass("layui-nav-itemed");
		
		}
		
		$("#"+this.option.elem+" a[menu-id='"+xxbId+"']").parent().addClass("layui-this");
		
		
	}
	
	
	var activeMenus;
	
	xxbMenu.prototype.collaspe = function(time){
		
		var elem = this.option.elem;
		
		var config = this.option;
		
		if($("#"+this.option.elem).is(".xxb-nav-mini")){
		    
			$.each(activeMenus,function(i,item){
				$("#"+elem+" a[menu-id='" + $(this).attr("menu-id") + "']").parent().addClass("layui-nav-itemed");
			})
		  
		    $("#"+this.option.elem).removeClass("xxb-nav-mini");
		
		    $("#"+this.option.elem).animate({
					width: "220px"
				},150);
		
		    isHoverMenu(false,config);
			
		}else{
			
			activeMenus = $("#"+this.option.elem).find(".layui-nav-itemed>a");
			$("#"+this.option.elem).find(".layui-nav-itemed").removeClass("layui-nav-itemed");
			
			$("#"+this.option.elem).addClass("xxb-nav-mini");
		  
		    $("#"+this.option.elem).animate({
					width: "60px"
				},400);
			isHoverMenu(true,config);
		
		}
	}
	
    /** 同 步 请 求 获 取 数 据 */
    function getData(url){
		
		$.ajaxSettings.async = false;
		var data = null;
		
		$.get(url, function(result) {
			data = result;
		});
		
		$.ajaxSettings.async = true;
		return data;
	}
	
	
	function createMenu(option){
	
	
		// 声 明 头 部
		var menuHtml = '<ul lay-filter="'+option.elem+'" class="layui-nav arrow   xxb-menu layui-nav-tree xxb-nav-tree">'
		// 开 启 同 步 操 作
		    $.each(option.data, function(i, item) {
		        // 创 建 每 一 个 菜 单 项
				
				
				var content = '<li class="layui-nav-item" >';
				
				if(i==option.defaultOpen && option.defaultOpen!=false){
					content = '<li class="layui-nav-item layui-nav-itemed" >';
				}
				
				// 判 断 菜 单 类 型 0 是 不可跳转的目录 1 是 可 点 击 跳 转 的 菜 单
				if (item.type == 0) {
					// 创 建 目 录 结 构
					content += '<a  href="javascript:;" menu-type="'+item.type+'" menu-id="'+item.id+'" href="javascript:;"><i class="layui-icon ' + item.icon + '"></i><span>' + item.title +
						'</span></a>';
				} else if (item.type == 1) {
					// 创 建 菜 单 结 构
					content += '<a class="site-demo-active" menu-type="'+item.type+'" menu-url="' + item.href + '" menu-id="' + item.id +
						'" menu-title="' + item.title + '" href="javascript:;" href="javascript:;"><i class="layui-icon ' + item.icon +
						'"></i><span>' + item.title + '</span></a>';
				}
				// 调 用 递 归 方 法 加 载 无 限 层 级 的 子 菜 单 
				content += loadchild(item);
				// 结 束 一 个 根 菜 单 项
				content += '</li>';
				menuHtml += content;
		
			});
			// 结 束 菜 单 结 构 的 初 始 化
			menuHtml += "</ul>";
		    // 将 菜 单 拼 接 到 初 始 化 容 器 中

	    $("#"+option.elem).html(menuHtml);
		
	}
	
	
	function createMenuAndControl(option){
	
	    var control = '<ul class="layui-nav xxb-nav-control">';
		// 声 明 头 部
		var menu = '<div class="layui-side-scroll '+option.theme+'">'
		// 开 启 同 步 操 作
		
		var index = 0;
		   
		$.each(option.data, function(i, item) {
			
			var menuItem = '';
			
			var controlItem = '';
			
			if(index === option.defaultMenu){
	              
				
				controlItem  = '<li xxb-id="'+item.id+'" class="layui-this layui-nav-item"><a href="#">'+item.title+'</a></li>';
				  			
				menuItem = '<ul  xxb-id="'+item.id+'" lay-filter="'+option.elem+'" class="layui-nav arrow layui-nav-tree xxb-nav-tree">';
				   
			}else{
				
				
				controlItem = '<li xxb-id="'+item.id+'" class="layui-nav-item"><a href="#">'+item.title+'</a></li>';
							
				menuItem = '<ul style="display:none" xxb-id="'+item.id+'" lay-filter="'+option.elem+'" class="layui-nav arrow layui-nav-tree xxb-nav-tree">';
				
			}
			
			index ++;
			
	
			
			$.each(item.children,function(i,note){
				
				// 创 建 每 一 个 菜 单 项
				var content = '<li class="layui-nav-item" >';
				// 判 断 菜 单 类 型 0 是 不可跳转的目录 1 是 可 点 击 跳 转 的 菜 单
				if (note.type == 0) {
					// 创 建 目 录 结 构
					content += '<a  href="javascript:;" menu-type="'+note.type+'" menu-id="'+note.id+'" href="javascript:;"><i class="layui-icon ' + note.icon + '"></i><span>' + note.title +
						'</span></a>';
				} else if (note.type == 1) {
					// 创 建 菜 单 结 构
					content += '<a class="site-demo-active" menu-type="'+note.type+'" menu-url="' + note.href + '" menu-id="' + note.id +
						'" menu-title="' + note.title + '" href="javascript:;" href="javascript:;"><i class="layui-icon ' + note.icon +
						'"></i><span>' + note.title + '</span></a>';
				}
				// 调 用 递 归 方 法 加 载 无 限 层 级 的 子 菜 单 
				content += loadchild(note);
				// 结 束 一 个 根 菜 单 项
				content += '</li>';
				
				menuItem += content;
				
			})
			
			
			menu += menuItem + '</ul>';
			
			control += controlItem;
			
		})	
		
		
		$("#"+option.control).html(control);
		
		$("#"+option.elem).html(menu);
		
		$("#"+option.control+" .xxb-nav-control").on("click","li",function(){
			
			$("#"+option.elem).find(".xxb-nav-tree").css({display:'none'});
			
			$("#"+option.elem).find(".xxb-nav-tree[xxb-id='"+$(this).attr("xxb-id")+"']").css({display:'block'});
			
		})
			
		
			
	
		
	}
	
    /** 加载子菜单 (递归)*/
	function loadchild(obj) {
		
		// 判 单 是 否 是 菜 单， 如 果 是 菜 单 直 接 返 回
		if(obj.type==1){
			return "";
		}
		
		// 创 建 子 菜 单 结 构
		var content = '<dl class="layui-nav-child">';
		
		// 如 果 嵌 套 不 等 于 空 
		if (obj.children != null && obj.children.length > 0) {
			// 遍 历 子 项 目
			$.each(obj.children, function(i, note) {
				// 创 建 子 项 结 构
				content += '<dd>';
	            // 判 断 子 项 类 型
				if (note.type == 0) {
					// 创 建 目 录 结 构
	                content += '<a  href="javascript:;" menu-type="'+note.type+'" menu-id="'+note.id+'"><i class="layui-icon ' + note.icon + '"></i><span>' + note.title + '</span></a>';
				} else if (note.type == 1) {
					// 创 建 菜 单 结 构
					content += '<a class="site-demo-active" menu-type="'+note.type+'" menu-url="' + note.href + '" menu-id="' + note.id +'" menu-title="' + note.title + '" menu-icon="' + note.icon + '" href="javascript:;"><i class="layui-icon ' + note.icon +'"></i><span>' + note.title + '</span></a>';
				}
				// 加 载 子 项 目 录
				content += loadchild(note);
				// 结 束 当 前 子 菜 单
				content += '</dd>';
			});
	        // 封 装
			
		}else{
			content += '<div class="toast"><i class="layui-icon layui-icon-more"></i></div>';
		
		   /* 暂 无 数 据&nbsp;&nbsp;&nbsp;&nbsp; */
		}
		
		content += '</dl>';
		return content;
	}
	
	
	
	function downShow(option){
		
		 $("body #"+option.elem).on("click","a[menu-type='0']",function(){
			
			if(!$("#"+option.elem).is(".xxb-nav-mini")){
				
			var superEle = $(this).parent();
			var ele = $(this).next('.layui-nav-child');
			var height = ele.height();
			if($(this).parent().is(".layui-nav-itemed")){

				if(option.accordion){
				// 手 风 琴 实 现
				$(this).parent().parent().find(".layui-nav-itemed").removeClass("layui-nav-itemed");
				$(this).parent().addClass("layui-nav-itemed");			
				
				}
				
				ele.height(0);
				
				ele.animate({
					height: height + "px"
				},200,function() {
					ele.css({
						height: "auto"
					});
				}); 
			
			}else{
			 	
				$(this).parent().addClass("layui-nav-itemed");
				
				ele.animate({
					height: "0px"
				},200,function() {
					ele.css({
						height: "auto"
					});
					$(this).parent().removeClass("layui-nav-itemed");
				}); 
						
			}
			}
		})
		 
		
	}
	
	/** 二 级 悬 浮 菜 单*/
	function isHoverMenu(b,option) {
			if (b) {
				$("#"+option.elem+".xxb-nav-mini .layui-nav-item,#"+option.elem+".xxb-nav-mini dd").hover(function() {
					
					$(this).children(".layui-nav-child").addClass("layui-nav-hover");
					
					var top = $(this).offset().top+5;
					
					
					if (!$(this).is(".layui-nav-item")) {
					    
						var left = $(this).offset().left + 132;
						
						$(this).children(".layui-nav-child").offset({left:left});
				
					}else{
						var left = $(this).offset().left + 62 ;
						
						$(this).children(".layui-nav-child").offset({left:left});
					 
					}
					$(this).children(".layui-nav-child").offset({top:top});
		           
				
				   
			   }, function() {
				   
					$(this).children(".layui-nav-child").removeClass("layui-nav-hover");
					
					//判断当前是dd,还是 layui-nav-item
				
					$(this).children(".layui-nav-child").css({left: '0px'});
					
					$(this).children(".layui-nav-child").css({top: '0px'});
				})
			} else {
				$("#"+option.elem+" .layui-nav-item").off('mouseenter').unbind('mouseleave');
				$("#"+option.elem+" dd").off('mouseenter').unbind('mouseleave');
			}
		}
	
	    exports(MOD_NAME,new xxbMenu());
})