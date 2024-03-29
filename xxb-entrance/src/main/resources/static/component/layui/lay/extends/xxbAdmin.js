layui.define(['table', 'layer', 'jquery', 'element', 'form', 'xxbTab', 'xxbMenu', 'xxbFrame'], function (exports) {
    "use strict";

    var $ = layui.jquery,
        xxbTab = layui.xxbTab,
        xxbMenu = layui.xxbMenu,
        xxbFrame = layui.xxbFrame,
        layer = layui.layer;

    var bodyFrame;
    var sideMenu;
    var bodyTab;

    var xxbAdmin = new function () {
        this.render = function (option) {
            this.menuRender(option);
            this.bodyRender(option);
            this.keepLoad(option);
            this.themeRender(option);
        }

        this.menuRender = function (option) {

            /** 侧 边 菜 单 组 件 初 始 化 */
            sideMenu = xxbMenu.render({
                elem: 'sideMenu', //依赖容器
                async: true, //数据形式
                theme: option.theme,
                height: '100%',
                control: option.control ? 'control' : false, // control
                defaultMenu: 0,
                defaultOpen: 0,   //默认打开菜单
                accordion: true,
                url: option.data, //数据地址
                parseData: function (data) {//请求后是否进行数据解析 函数
                    //
                    var group = {};
                    for (var index in data) {
                        var parentId = data[index].parentId;
                        if (!group.hasOwnProperty(parentId)) {
                            group[parentId] = [];
                        }
                        group[parentId].push(data[index]);
                    }
                    data.forEach(function (menu) {
                        var id = menu.id;
                        if (group.hasOwnProperty(id)) {
                            menu.children = group[id];
                        }
                    });
                    return group["0"];
                }
            })
        }

        this.bodyRender = function (option) {

            if (option.muiltTab) {

                /** 选 项 卡 组 件 初 始 化 */
                bodyTab = xxbTab.render({
                    elem: 'content',
                    roll: true,
                    tool: true,
                    width: '100%',
                    height: '100%',
                    index: 0,
                    tabMax: 30,
                    data: [{
                        id: '11',
                        url: option.index,
                        title: '首页',
                        close: false
                    }]
                });

                // 选 项 卡 切 换 API 文 档
                bodyTab.click(function (id) {
                    bodyTab.positionTab();
                    sideMenu.selectItem(id);
                })


                $("body").on("click", ".refresh", function () {
                    bodyTab.refresh(500);
                })

                sideMenu.click(function (dom, data) {
                    bodyTab.addTabOnly({
                        id: data.menuId,
                        title: data.menuTitle,
                        url: data.menuUrl,
                        icon: data.menuIcon,
                        close: true
                    }, 200);
                })

            } else {

                // 选 项 卡 初 始 API 文 档
                bodyFrame = xxbFrame.render({
                    elem: 'content',
                    title: '首 页',
                    url: option.index,
                    width: '100%',
                    height: '100%'
                });

                $("body").on("click", ".refresh", function () {
                    bodyFrame.refresh(500);
                })

                sideMenu.click(function (dom, data) {
                    bodyFrame.changePage(data.menuUrl, data.menuTitle, true);
                })
            }
        }

        this.keepLoad = function (option) {

            setTimeout(function () {
                $(".preloader").fadeOut();
            }, option.keepLoad)
        }


        this.colorSet = function (color) {

            var style = '';

            // 自 定 义 菜 单 配 色
            style += '.light-theme .xxb-nav-tree .layui-this a:hover,.light-theme .xxb-nav-tree .layui-this,.light-theme .xxb-nav-tree .layui-this a,.xxb-nav-tree .layui-this a,.xxb-nav-tree .layui-this{background-color: ' + color + '!important;}';

            // 自 定 义 标 签 配 色
            style += '.xxb-frame-title .dot,.xxb-tab .layui-this .xxb-tab-active{background-color: ' + color + '!important;}';

            // 自 定 义 顶 部 配 色
            style += '.xxb-admin .layui-header .layui-nav .layui-nav-bar{background-color: ' + color + '!important;}'

            // 自 定 义 加 载 配 色
            style += '.ball-loader>span,.signal-loader>span {background-color: ' + color + '!important;}';

            // 自 定 义 顶 部 配 色 以 及 操 作 标 签 页 的 配 色
            style += '.layui-tab-tool .layui-nav .layui-nav-item .layui-nav-child dd a:hover, .layui-header .layui-nav .layui-nav-item .layui-nav-child dd a:hover{ color:#fff; background-color:' + color + '!important; }';

            // 自 定 义 加 载 配 色
            style += '#preloader{background-color:' + color + '!important;}';

            // 自 定 义 样 式 选 择 边 框 配 色
            style += '.xxbone-color .color-content li.layui-this:after, .xxbone-color .color-content li:hover:after {border: ' + color + ' 2px solid!important;}';

            style += '.layui-form-onswitch {border-color:' + color + '!important;background-color:' + color + '!important;};';


            localStorage.setItem("theme-color", color);

            if ($("iframe").contents().find("#customTheme").length > 0) {

                $("iframe").contents().find("#customTheme").remove();

            }


            var theme = "<style>";
            /*
            theme += '::-webkit-scrollbar{width:0px;height:0px;}'+
            '::-webkit-scrollbar{width:7px;height:4px;}'+
            '::-webkit-scrollbar-track{background: whitesmoke;border-radius:50px;}'+
            '::-webkit-scrollbar-thumb{background:  lightgray;border-radius:50px;}'+
            '::-webkit-scrollbar-thumb:hover{background: lightgray;}'+
            '::-webkit-scrollbar-corner{background: whitesmoke;}'; */


            theme += '</style>';


            $("iframe").contents().find("head").append(theme);

            $("#xxbone-bg-color").html(style);
        }


        this.themeRender = function (option) {


            var color = localStorage.getItem("theme-color");

            var menu = localStorage.getItem("theme-menu");

            this.colorSet(color);

            this.menuSkin(menu);
        }


        this.menuSkin = function (theme) {

            $(".xxb-admin").removeClass("light-theme");
            $(".xxb-admin").removeClass("dark-theme");
            $(".xxb-admin").addClass(theme);

        }
    };

    $("body").on("click", ".message", function () {

        // 获取消息数据, 便利拼接到相应页面


        openMessage("");
    })


    $("body").on("click", ".collaspe", function () {

        sideMenu.collaspe();

        if ($(".xxb-admin").is(".xxb-mini")) {

            $(".xxb-admin").removeClass("xxb-mini");

        } else {

            $(".xxb-admin").addClass("xxb-mini");

        }
    })


    /**
     * 全屏/退出全屏
     */
    $("body").on("click", ".fullScreen", function () {
        if ($(this).hasClass("layui-icon-screen-restore")) {

            screenFun(2).then(function () {
                $(".fullScreen").eq(0).removeClass("layui-icon-screen-restore");
            });

        } else {

            screenFun(1).then(function () {
                $(".fullScreen").eq(0).addClass("layui-icon-screen-restore");
            });

        }
    });


    /**
     * 全屏和退出全屏的方法
     * @param num 1代表全屏 2代表退出全屏
     * @returns {Promise}
     */
    function screenFun(num) {
        num = num || 1;
        num = num * 1;
        var docElm = document.documentElement;

        switch (num) {
            case 1:
                if (docElm.requestFullscreen) {
                    docElm.requestFullscreen();
                } else if (docElm.mozRequestFullScreen) {
                    docElm.mozRequestFullScreen();
                } else if (docElm.webkitRequestFullScreen) {
                    docElm.webkitRequestFullScreen();
                } else if (docElm.msRequestFullscreen) {
                    docElm.msRequestFullscreen();
                }
                break;
            case 2:
                if (document.exitFullscreen) {
                    document.exitFullscreen();
                } else if (document.mozCancelFullScreen) {
                    document.mozCancelFullScreen();
                } else if (document.webkitCancelFullScreen) {
                    document.webkitCancelFullScreen();
                } else if (document.msExitFullscreen) {
                    document.msExitFullscreen();
                }
                break;
        }

        return new Promise(function (res, rej) {
            res("返回值");
        });
    }


    //监听背景色选择
    $('body').on('click', '[data-select-bgcolor]', function () {
        var theme = $(this).attr('data-select-bgcolor');

        $('[data-select-bgcolor]').removeClass("layui-this");

        $(this).addClass("layui-this");

        localStorage.setItem("theme-menu", theme);

        xxbAdmin.menuSkin(theme);
    });

    $('body').on('click', '.select-color-item', function () {

        $(".select-color-item").removeClass("layui-icon")
            .removeClass("layui-icon-ok");

        $(this).addClass("layui-icon").addClass("layui-icon-ok");

        var color = $(".select-color-item.layui-icon-ok").css("background-color");


        xxbAdmin.colorSet(color);
    });


    $("body").on("click", ".setting", function () {

        var menu = localStorage.getItem("theme-menu");

        var bgColorHtml =
            '<li ' + (menu === "dark-theme" ? 'class="layui-this"' : '') + ' data-select-bgcolor="dark-theme">' +
            '<a href="javascript:;" data-skin="skin-blue" style="" class="clearfix full-opacity-hover">' +
            '<div><span style="display:block; width: 20%; float: left; height: 12px; background: #28333E;"></span><span style="display:block; width: 80%; float: left; height: 12px; background: white;"></span></div>' +
            '<div><span style="display:block; width: 20%; float: left; height: 40px; background: #28333E;"></span><span style="display:block; width: 80%; float: left; height: 40px; background: #f4f5f7;"></span></div>' +
            '</a>' +
            '</li>';


        bgColorHtml +=
            '<li ' + (menu === "light-theme" ? 'class="layui-this"' : '') + ' data-select-bgcolor="light-theme">' +
            '<a href="javascript:;" data-skin="skin-blue" style="" class="clearfix full-opacity-hover">' +
            '<div><span style="display:block; width: 20%; float: left; height: 12px; background: white;"></span><span style="display:block; width: 80%; float: left; height: 12px; background: white;"></span></div>' +
            '<div><span style="display:block; width: 20%; float: left; height: 40px; background: white;"></span><span style="display:block; width: 80%; float: left; height: 40px; background: #f4f5f7;"></span></div>' +
            '</a>' +
            '</li>';

        var html =
            '<div class="xxbone-color">\n' +
            '<div class="color-title">整体风格</div>\n' +
            '<div class="color-content">\n' +
            '<ul>\n' + bgColorHtml + '</ul>\n' +
            '</div>\n' +
            '</div>';


        html += "<div class='select-color'>" +
            "<div class='select-color-title'>主题色</div>" +
            "<div class='select-color-content'>" +
            "<span class='select-color-item layui-icon layui-icon-ok' style='background-color:#FF5722;'></span>" +
            "<span class='select-color-item' style='background-color:#5FB878;'></span>" +
            "<span class='select-color-item'  style='background-color:#1E9FFF;'></span>" +
            "<span class='select-color-item' style='background-color:#FFB800;'></span>" +
            "<span class='select-color-item' style='background-color:darkgray;'></span>" +
            "</div></div>";

        html += '<div class="more-menu-list">' +
            '<a class="more-menu-item" href="" target="_blank">' +
            '<i class="layui-icon layui-icon-read" style="font-size: 19px;"></i> 系统文档' +
            '</a>' +
            '<a class="more-menu-item" href="https://lanzous.com/b08r5r7za\n" target="_blank">' +
            '<i class="layui-icon layui-icon-cellphone" style="font-size: 16px;"></i>  安卓App' +
            '</a>' +
            '<a class="more-menu-item" href="mailto:xagu_qc@foxmail.com?subject=学习崩&cc=抄送人邮箱&subject=主题&body=反馈内容" target="_blank">' +
            '<i class="layui-icon layui-icon-email"></i> 联系邮箱' +
            '</a>' +
            '<a class="more-menu-item" href="//shang.qq.com/wpa/qunwpa?idkey=11d77b4b02af940a8273da1203d81ae8fbdb57e996dffc3eb9dea61eaa22e8c8" target="_blank">' +
            '<i class="layui-icon layui-icon-user"></i>QQ交流群' +
            '</a>' +
            '</div>';


        openRight(html);

    })

    function openMessage(html) {
        layer.open({
            type: 1,
            offset: 'r',
            area: ['340px', '100%'],
            title: false,
            shade: 0.1,
            closeBtn: 0,
            shadeClose: false,
            anim: -1,
            skin: 'layer-anim-right',
            move: false,
            content: html,
            success: function (layero, index) {
                $('#layui-layer-shade' + index).click(function () {
                    var $layero = $('#layui-layer' + index);
                    $layero.animate({
                        left: $layero.offset().left + $layero.width()
                    }, 200, function () {
                        layer.close(index);
                    });
                })
            }
        });
    }

    function openRight(html) {
        layer.open({
            type: 1,
            offset: 'r',
            area: ['340px', '100%'],
            title: false,
            shade: 0.1,
            closeBtn: 0,
            shadeClose: false,
            anim: -1,
            skin: 'layer-anim-right',
            move: false,
            content: html,
            success: function (layero, index) {

                //初始化颜色选择器的状态
                $(".select-color-item").removeClass("layui-icon")
                    .removeClass("layui-icon-ok");

                var color = localStorage.getItem("theme-color");

                $(".select-color-item").each(function () {
                    if ($(this).css("background-color") === color) {
                        $(this).addClass("layui-icon").addClass("layui-icon-ok");
                    }
                });

                $('#layui-layer-shade' + index).click(function () {
                    var $layero = $('#layui-layer' + index);
                    $layero.animate({
                        left: $layero.offset().left + $layero.width()
                    }, 200, function () {
                        layer.close(index);
                    });
                });
            }
        });
    }

    exports('xxbAdmin', xxbAdmin);
})