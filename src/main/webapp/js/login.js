$(function() {
	function refresh(obj) {
        obj.src = "imageServlet?"+Math.random();
    }
	function subLogin() {
		alert("denglu");
	}
    var imgOffsetX, imgOffsetY, loginImgWidth, loginImgHeight, scale;
    //报错蒙板
    var $mask = $('<div class="fs-login-errmask"/>');
    //用户名
    var user_ctrl=$('input.fs-login-username').attr("placeholder", "用户名").attr('title',"用户名");
    //密码
    var psw_ctrl=$('input.fs-login-password').attr("placeholder", "用户密码").attr('title',"用户密码");
    //校验码
    var checkCode_ctrl=$('input.fs-login-checkCode').attr("placeholder", "校验码").attr('title',"校验码");
    
    $('input').focus(function(){
        $(this).parent().addClass('fs-login-input-focus');
        $mask.hide();
    }).blur(function(){
            $(this).parent().removeClass('fs-login-input-focus');
        });
    //是否保持登录状态
//    var $keep = $('span.fs-login-remember').text(FR.i18nText("FS-Generic-Privilege_Keep_Login_State")).click(
//        function(){
//            $(this).toggleClass('fs-login-remember-selected');
//        }
//    );
    //登录按钮
    $('a').text("登录").click(
        function(){
            signIN();
        }
    );
    //绑定回车
    $(document).keydown(function(e){
        if(e.keyCode===13){
            signIN();
        }
    });
    /**
     * 初始化FS的登录背景图片
     */
    var initBackgroundImage = function () {
        var self = this;
        var ran = new Date().getTime() + "" + (Math.random() * 1000);
        loginImgWidth = 1920;
        loginImgHeight = 1080;
        calcBackgroundScale();
        if ($('body').length > 0) {
            var loginImg = $('img.fs-login-img');
//            loginImg.attr("src", url);
            loginImg.css({
                "margin-left": "-" + imgOffsetX + "px",
                "margin-top": "-" + imgOffsetY + "px",
                width: loginImgWidth * scale + "px",
                height: loginImgHeight * scale + "px"
            });

            var offset = $('#fs-login-scalebg').offset();
            var loginScaleBgImg = $('img.fs-login-scalebg-img');
//            loginScaleBgImg.attr("src", url);
            loginScaleBgImg.css({
                "margin-left": "-" + (imgOffsetX + offset.left) + "px",
                "margin-top": "-" + (imgOffsetY + offset.top) + "px",
                width: loginImgWidth * scale + "px",
                height: loginImgHeight * scale + "px"
            });
        }
    };

    var calcBackgroundScale = function () {
        var windowWidth = document.body.clientWidth;
        var windowHeight = document.body.clientHeight;

        if (windowWidth / windowHeight >= loginImgWidth / loginImgHeight) {
            scale = windowWidth / loginImgWidth;
            imgOffsetX = 0;
            imgOffsetY = (loginImgHeight * scale - windowHeight) / 2;
        } else {
            scale = windowHeight / loginImgHeight;
            imgOffsetX = (loginImgWidth * scale - windowWidth) / 2;
            imgOffsetY = 0;
        }
    };

    var showErrorMsg = function($pos, msg){
        $mask.hide().insertAfter($pos).text(msg);
        $mask.click(function(){
            $(this).fadeOut();
            $pos.select();
        }).fadeIn();
    };

    var signIN = function(){
        $mask.hide();
        var user = $('input.fs-login-username').val();
        var pw =  $('input.fs-login-password').val();
        var checkCode= $('input.fs-login-checkCode').val();
        //用户名为空
        if(user==null||user==''){
            showErrorMsg(user_ctrl,"用户名不能为空！");
            return;
        }
        //密码为空
        if(pw==null||pw==''){
            showErrorMsg(psw_ctrl,"密码不能为空!");
            return;
        }
        if(checkCode==null||checkCode==''){
            showErrorMsg(checkCode_ctrl,"校验码不能为空!");
            return;
        }
    	var form = document.getElementsByTagName("form");
        //form[0].userPwd.value=$.md5(form[0].userPwd.value).toUpperCase();
//		form[0].method.value = "login";
		form[0].submit();
    };
    initBackgroundImage();
    
    $(window).resize(function(){
        calcBackgroundScale();
        $('img.fs-login-img').css({
            "margin-left": "-" + imgOffsetX +"px",
            "margin-top": "-" + imgOffsetY +"px",
            width: loginImgWidth * scale + "px",
            height: loginImgHeight * scale + "px"
        });
        var offset = $('#fs-login-scalebg').offset();
        $('img.fs-login-scalebg-img').css({
            "margin-left": "-" + (imgOffsetX + offset.left) + "px",
            "margin-top": "-" + (imgOffsetY + offset.top) + "px",
            width: loginImgWidth * scale + "px",
            height: loginImgHeight * scale + "px"
        });
    });
});