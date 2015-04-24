// ul li pagination for photogallary

(function ($) {
	var currobj = this;

    $.fn.Pagination = function (options) {
    	 var defaults = {
 	            PagingArea: 'PageList',
 	            noOfPage: 0,
 	            curObj: this,
 	            ParentID: $(this).attr("id"),
 	            curPage: 1
 	        };
        var options = $.extend(defaults, options);
        var defaults1 = {
 	            PagingArea: 'PageList2',
 	            noOfPage: 0,
 	            curObj: this,
 	            ParentID: $(this).attr("id"),
 	            curPage: 1
 	        };
        var strHtml = '';
        $("li", this).each(function () {
            options.noOfPage++;
            defaults1.noOfPage++;
            if (options.noOfPage == 1) {
                $(this).show();
            }
            else {
                $(this).hide();
            }

            $(this).attr('id', options.ParentID + '_li_' + options.noOfPage);
            $(this).attr('id', defaults1.ParentID + '_li_' + options.noOfPage);
            $("#" + options.PagingArea).append('<a id="Page_' + options.noOfPage + '" href=\"javascript:void(0);\">' + options.noOfPage + '</a>');
            $("#" + defaults1.PagingArea).append('<a id="Page1_' + defaults1.noOfPage + '" href=\"javascript:void(0);\">' + defaults1.noOfPage + '</a>');
            $("#Page_" + options.noOfPage).click(function () {
                $("#Page_" + options.curPage).removeClass("activePageLink");
                $("#Page1_" + defaults1.curPage).removeClass("activePageLink");
                $("#" + options.ParentID + " li:nth-child(" + options.curPage + ")").hide();
                $("#" + defaults1.ParentID + " li:nth-child(" + defaults1.curPage + ")").hide();
                options.curPage = $(this).text()
                defaults1.curPage = $(this).text()
                $("#Page_" + options.curPage).addClass("activePageLink");
                $("#Page1_" + options.curPage).addClass("activePageLink");
                $("#" + options.ParentID + " li:nth-child(" + options.curPage + ")").fadeIn("slow");

            });
            $("#Page1_" + defaults1.noOfPage).click(function () {
                $("#Page1_" + defaults1.curPage).removeClass("activePageLink");
                $("#Page_" + options.curPage).removeClass("activePageLink");
                $("#" + defaults1.ParentID + " li:nth-child(" + defaults1.curPage + ")").hide();
                $("#" + options.ParentID + " li:nth-child(" + options.curPage + ")").hide();
                defaults1.curPage = $(this).text()
                options.curPage = $(this).text()
                $("#Page1_" + defaults1.curPage).addClass("activePageLink");
                $("#Page_" + defaults1.curPage).addClass("activePageLink");
                $("#" + defaults1.ParentID + " li:nth-child(" + defaults1.curPage + ")").fadeIn("slow");

            });
        });  
    }


})(jQuery);