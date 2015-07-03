// ul li pagination for photogallary

(function ($) {
    var currobj = this;
    
    $.fn.Pagination = function (options) {

        var defaults = {
                PagingArea: 'PageList',
                noOfPage: 0,
                curObj: this,
                ParentID: $(this).attr("id"),
                curPage: 1,
                numberOfVisiblePages : 5
            };
        var options = $.extend(defaults, options);
        var defaults1 = {
                PagingArea: 'PageList2',
                noOfPage: 0,
                curObj: this,
                ParentID: $(this).attr("id"),
                curPage: 1,
                numberOfVisiblePages : 5
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
                options.curPage = parseInt($(this).text());
                defaults1.curPage = parseInt($(this).text());
                $("#Page_" + options.curPage).addClass("activePageLink");
                $("#Page1_" + options.curPage).addClass("activePageLink");
                $("#" + options.ParentID + " li:nth-child(" + options.curPage + ")").fadeIn("slow");
                hidePrevNext();

            });
            $("#Page1_" + defaults1.noOfPage).click(function () {
                $("#Page1_" + defaults1.curPage).removeClass("activePageLink");
                $("#Page_" + options.curPage).removeClass("activePageLink");
                $("#" + defaults1.ParentID + " li:nth-child(" + defaults1.curPage + ")").hide();
                $("#" + options.ParentID + " li:nth-child(" + options.curPage + ")").hide();
                defaults1.curPage = parseInt($(this).text());
                options.curPage = parseInt($(this).text());
                $("#Page1_" + defaults1.curPage).addClass("activePageLink");
                $("#Page_" + defaults1.curPage).addClass("activePageLink");
                $("#" + defaults1.ParentID + " li:nth-child(" + defaults1.curPage + ")").fadeIn("slow");
                hidePrevNext();

            });

        });

        $("#PageList").prepend('<a id="Page_' + 'Previous' + '" href=\"javascript:void(0);\">' + '<span class="glyphicon glyphicon-chevron-left"></span>' + '</a>');
        $("#PageList2").prepend('<a id="Page1_' + 'Previous' + '" href=\"javascript:void(0);\">' + '<span class="glyphicon glyphicon-chevron-left"></span>' + '</a>');
        $("#PageList").append('<a id="Page_' + 'Next' + '" href=\"javascript:void(0);\">' + '<span class="glyphicon glyphicon-chevron-right"></span>' + '</a>');
        $("#PageList2").append('<a id="Page1_' + 'Next' + '" href=\"javascript:void(0);\">' + '<span class="glyphicon glyphicon-chevron-right"></span>' + '</a>');

        $( '[id*=PageList]' ).on('click', '[id*=Previous],[id*=Next]', function(e){
            var $control = $(this),
                $controlId = $control.attr('id'),
                $controlParent = $control.parent('li'),
                typeOfControl = $controlId.split('_')[1].toLowerCase();

                switch(typeOfControl){
                    case "previous":
                        var previousPage = parseInt(options.curPage) - 1;
                        $controlParent.find('[id*=_'+ previousPage +']').trigger('click');
                        break;
                    case "next":
                        var nextPage = parseInt(options.curPage) + 1;
                        $controlParent.find('[id*=_'+ nextPage +']').trigger('click');
                        break;
                    default:
                        break;
                }
                hidePrevNext();
        });

        function hidePrevNext(){
            $( '[id*=PageList] [id*=Page]' ).hide();
            var visibleCounter = options.numberOfVisiblePages,
                hasNext = true,
                $nextPage=$( '[id*=PageList] [id*=_'+ options.curPage +']' ).show();
            if(options.curPage == 1){
                $( '[id*=PageList] [id*=Previous]' ).hide();
            } else {
                $( '[id*=PageList] [id*=Previous]' ).show();
            }
            if(options.curPage == options.noOfPage || options.noOfPage==0){
                $( '[id*=PageList] [id*=Next]' ).hide();
            } else {
                $( '[id*=PageList] [id*=Next]' ).show();
            }
            while( visibleCounter && !!$nextPage ){
                if(hasNext){
                    $nextPage = $nextPage.next().not('[id*="Next"]');    
                } else{
                    $nextPage = $nextPage.prev().not('[id*="Prev"]');    
                }
                if($nextPage.length==0){
                    hasNext = false;
                    $nextPage = $( '[id*=PageList] [id*=_'+ options.curPage +']' ).prev().not('[id*="Previous"]');
                }
                if($nextPage.length==0) break;
                $nextPage.show();
                visibleCounter--;
            }
        };

        hidePrevNext();

    }


})(jQuery);