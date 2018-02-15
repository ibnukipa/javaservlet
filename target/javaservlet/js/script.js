$(document).ready(function () {
    var _window = $(window);
    var headerMenu = $('.header.middle');
    var mainContainer = $('body .ui.text.container.kipa');

    mainContainer.css('margin-top', $(".top.fixed.menu.kipa").height() + 14);
    _window.scroll(function() {
        toogleMenu($(this));
    });

    function toogleMenu(window) {
        if(window.scrollTop() > 100) {
            if(headerMenu.hasClass('huge')) {
                headerMenu.removeClass('huge');
                headerMenu.addClass('medium');
            }
        } else {
            if(headerMenu.hasClass('medium')) {
                headerMenu.removeClass('medium');
                headerMenu.addClass('huge');
            }
        }
    }

    $(".message .close")
        .on("click", function() {
            $(this)
                .closest(".message")
                .transition("fade")
            ;
        })
    ;

    $(".dropdown").dropdown({
        direction: "downward"
    });
});