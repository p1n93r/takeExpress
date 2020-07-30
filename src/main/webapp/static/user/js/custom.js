$(window).on("load", function() {
        $("#preloader").delay(350).fadeOut("slow");
        $(".header-inner").mCustomScrollbar();
        $(".portfolio-filter").on("click", "li",
            function() {
                var e = $(this).attr("data-filter");
                a.isotope({
                    filter: e
                })
            });
        $(".portfolio-filter").each(function(g, f) {
            var e = $(f);
            e.on("click", "li",
                function() {
                    e.find(".current").removeClass("current");
                    $(this).addClass("current")
                })
        });
        var a = $(".portfolio-wrapper");
        a.imagesLoaded(function() {
            $(".portfolio-wrapper").isotope({
                itemSelector: '[class*="col-"]',
                percentPosition: true,
                masonry: {
                    columnWidth: '[class*="col-"]'
                }
            })
        });
        var b = 1;
        var d = $(".portfolio-pagination").find("li a:last").text();
        a.infinitescroll({
                itemSelector: ".grid-item",
                nextSelector: ".portfolio-pagination li a",
                navSelector: ".portfolio-pagination",
                extraScrollPx: 0,
                bufferPx: 0,
                maxPage: 6,
                loading: {
                    finishedMsg: "No more works",
                    msgText: "",
                    speed: "slow",
                    selector: ".load-more",
                },
            },
            function(f) {
                var e = $(f);
                e.imagesLoaded(function() {
                    e.animate({
                        opacity: 1
                    });
                    a.isotope("appended", e)
                });
                b++;
                if (b == d) {
                    $(".load-more").remove()
                }
            });
        a.infinitescroll("unbind");
        $(".load-more .btn").on("click",
            function() {
                a.infinitescroll("retrieve");
                $(".load-more .btn i").css("display", "inline-block");
                $(".load-more .btn i").addClass("fa-spin");
                $(document).ajaxStop(function() {
                    setTimeout(function() {
                            $(".load-more .btn i").hide()
                        },
                        1000)
                });
                return false
            });
        $(".portfolio-filter-mobile").on("change",
            function() {
                var e = this.value;
                e = c[e] || e;
                a.isotope({
                    filter: e
                })
            });
        var c = {
            numberGreaterThan50: function() {
                var e = $(this).find(".number").text();
                return parseInt(e, 10) > 50
            },
            ium: function() {
                var e = $(this).find(".name").text();
                return e.match(/ium$/)
            }
        }
});


$(document).on("ready", function() {
        $(".testimonials-wrapper").slick({
            dots: true,
            arrows: false,
            slidesToShow: 2,
            slidesToScroll: 2,
            responsive: [{
                breakpoint: 768,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1,
                    dots: true,
                    arrows: false,
                }
            }]
        });
        $(".clients-wrapper").slick({
            dots: false,
            arrows: false,
            slidesToShow: 4,
            slidesToScroll: 4,
            responsive: [{
                breakpoint: 768,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 3,
                    dots: false,
                    arrows: false,
                }
            },
                {
                    breakpoint: 425,
                    settings: {
                        slidesToShow: 1,
                        slidesToScroll: 1,
                        dots: false,
                        arrows: false,
                    }
                }]
        })
});


$(function() {
    $(".menu-icon").on("click",
        function() {
            $("header.left").toggleClass("open");
            $(".mobile-header, main.content").toggleClass("push")
        });
    $("main.content, header.left button.close").on("click",
        function() {
            $("header.left").removeClass("open");
            $(".mobile-header, main.content").removeClass("push")
        });
    $(".count").counterUp({
        delay: 10,
        time: 2000
    });
    if ($(".skill-item").length > 0) {
        var f = new Waypoint({
            element: document.getElementsByClassName("skill-item"),
            handler: function(g) {
                $(".progress-bar").each(function() {
                    var h = $(this).attr("aria-valuenow") + "%";
                    $(this).animate({
                            width: h
                        },
                        {
                            easing: "linear"
                        })
                });
                this.destroy()
            },
            offset: "50%"
        })
    }
    //右侧页面滑动过度动画
    $('.vertical-menu li a[href*="#"]:not([href="#"])').on("click", function(h) {
        var g = $(this);
        $("html, body").stop().animate({
                scrollTop: $(g.attr("href")).offset().top-48
            },
            800, "easeInOutQuad");
        //禁用超链接的默认连接
        h.preventDefault();
    });


    $("body").scrollspy({
        target: ".scrollspy",
        offset: 50
    });


    var a = document.getElementsByClassName("background");
    for (var b = 0; b < a.length; b++) {
        var e = a[b].getAttribute("data-image-src");
        a[b].style.backgroundImage = "url('" + e + "')"
    }
    var c = document.getElementsByClassName("spacer");
    for (var b = 0; b < c.length; b++) {
        var d = c[b].getAttribute("data-height");
        c[b].style.height = "" + d + "px"
    }
    $(window).scroll(function() {
        if ($(this).scrollTop() >= 250) {
            $("#return-to-top").fadeIn(200)
        } else {
            $("#return-to-top").fadeOut(200)
        }
    });
    $("#return-to-top").on("click",
        function() {
            $("body,html").animate({
                    scrollTop: 0
                },
                400)
        })
});