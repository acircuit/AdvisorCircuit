// ul li pagination for photogallary

(function ($) {

    $.fn.Pagination = function (options) {

        var defaults = {
            PagingArea: 'PageList',
            noOfPage: 0,
            curObj: this,
            ParentID: $(this).attr("id"),
            curPage: 1
        };

        var options = $.extend(defaults, options);
        var strHtml = '';
        $("li", this).each(function () {
            options.noOfPage++;

            if (options.noOfPage == 1) {
                $(this).show();
            }
            else {
                $(this).hide();
            }

            $(this).attr('id', options.ParentID + '_li_' + options.noOfPage);
            $("#" + options.PagingArea).append('<a id="Page_' + options.noOfPage + '" href=\"javascript:void(0);\">' + options.noOfPage + '</a>');
            $("#Page_" + options.noOfPage).click(function () {
                $("#Page_" + options.curPage).removeClass("activePageLink");
                $("#" + options.ParentID + " li:nth-child(" + options.curPage + ")").hide();
                options.curPage = $(this).text()
                $("#Page_" + options.curPage).addClass("activePageLink");
                $("#" + options.ParentID + " li:nth-child(" + options.curPage + ")").fadeIn("slow");

            });
        });
    }

    
    $.fn.Pagination2 = function (options) {

        var defaults = {
            PagingArea: 'PageList2',
            noOfPage: 0,
            curObj: this,
            ParentID: $(this).attr("id"),
            curPage: 1
        };

        var options = $.extend(defaults, options);
        var strHtml = '';
        $("li", this).each(function () {
            options.noOfPage++;

            if (options.noOfPage == 1) {
                $(this).show();
            }
            else {
                $(this).hide();
            }

            $(this).attr('id', options.ParentID + '_li_' + options.noOfPage);
            $("#" + options.PagingArea).append('<a id="Page_' + options.noOfPage + '" href=\"javascript:void(0);\">' + options.noOfPage + '</a>');
            $("#Page_" + options.noOfPage).click(function () {
                $("#Page_" + options.curPage).removeClass("activePageLink");
                $("#" + options.ParentID + " li:nth-child(" + options.curPage + ")").hide();
                options.curPage = $(this).text()
                $("#Page_" + options.curPage).addClass("activePageLink");
                $("#" + options.ParentID + " li:nth-child(" + options.curPage + ")").fadeIn("slow");

            });
        });
    }

})(jQuery);