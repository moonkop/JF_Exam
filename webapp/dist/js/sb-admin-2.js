$(document).ready(function () {
    $('#side-menu').metisMenu();
    $(".side-visible-line").on("click",function()
    {
        Side.collapse()
    })
});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function () {
    $(window).bind("load resize", function () {
        var topOffset = 50;
        var width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        // if (width < 768)
        // {
        //     $('div.navbar-collapse').addClass('collapse');
        //     topOffset = 100; // 2-row-menu
        // } else
        // {
        //     $('div.navbar-collapse').removeClass('collapse');
        // }

        var height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        if (height < 1)
        {
            height = 1;
        }
        if (height > topOffset)
        {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    });

    var url = window.location;
    // var element = $('ul.nav a').filter(function() {
    //     return this.href == url;
    // }).addClass('active').parent().parent().addClass('in').parent();
    var element = $('ul.nav a').filter(function () {
        return this.href == url;
    }).addClass('active').parent();

    while (true)
    {
        if (element.is('li'))
        {
            element = element.parent().addClass('in').parent();
        } else
        {
            break;
        }
    }
});

var Side = {

    _ps: $('.app-side'),
    _body: $('body'),
    _ele: $(".app-side"),
    responsive: function responsive() {
        $(window).width() < 0 ? Side._ele.removeClass('app-side-mini app-side-opened').addClass('app-side-closed') : Side._ele.addClass('app-side-opened').removeClass('app-side-closed');

        if (Side._ele.hasClass('page-fixed') & !Side._ele.hasClass('app-side-expand-on-hover'))
        {
            Side._ele.removeClass('app-side-mini');
        }
    },

    collapse: function collapse(element) {
        $(element).on('click', function (event) {
            event.preventDefault();
            Side._ele.toggleClass('app-side-opened app-side-closed');
            Side._stopMetisMenu();
        });
    },

    mini: function mini(element) {
        $(element).on('click', function (event) {
            event.preventDefault();
            Side._ele.toggleClass('app-side-mini');
            Side._stopMetisMenu();
        });
    },

    _stopMetisMenu: function _stopMetisMenu() {
        $('.side-nav').find('li').removeClass('active');
        $('.side-nav').find('a').attr('aria-expanded', false);
        $('.side-nav').find('ul.collapse').removeClass('in').attr('aria-expanded', false);
    }

};

$(document).on("chl.side", function () {
    Side.responsive();
    // var _cb;
    // $(window).on('resize', function () {
    //   _cb = setTimeout(Side.responsive, 100);
    // });

    $("[data-side]").each(function () {
        Side[$(this).attr("data-side")](this);
    });
}).trigger("chl.side");