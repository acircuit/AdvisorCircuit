
        $(document).ready(function () {
            $("div.holder").jPages({
                containerID: "itemContainer",
                perPage: 5,
                keyBrowse: true,
                scrollBrowse: true,
                animation: "bounceInUp",
                callback: function (pages,
        items) {
                    $("#legend1").html("Page " + pages.current + " of " + pages.count);
                    $("#legend2").html("Elements "+items.range.start + " - " + items.range.end + " of " + items.count);
                }
            });
            $("button").click(function () {
                /* get given page */
                var page = parseInt($("input").val());
                /* jump to that page */
                $("div.holder").jPages(page);
            });

            $("select#Itemsperpage").change(function () {
                /* get new no of items per page */
                var newPerPage = parseInt($(this).val());
                /* destroy jPages and initiate plugin again */
                $("div.holder").jPages("destroy").jPages({
                    containerID: "itemContainer",
                    perPage: newPerPage,
                    keyBrowse: true,
                    scrollBrowse: true,
                    callback: function (pages,
        items) {
                        $("#legend1").html("Page " + pages.current + " of " + pages.count);
                        $("#legend2").html("Elements "+items.range.start + " - " + items.range.end + " of " + items.count);
                    }
                });
            });
            $("select#Animation").change(function () {
                /* get new css animation */
                var newAnimation = $(this).val();
                /* destroy jPages and initiate plugin again */
                $("div.holder").jPages("destroy").jPages({
                    containerID: "itemContainer",
                    animation: newAnimation,
                    keyBrowse: true,
                    scrollBrowse: true,
                    callback: function (pages,
        items) {
                        $("#legend1").html("Page " + pages.current + " of " + pages.count);
                        $("#legend2").html("Elements "+items.range.start + " - " + items.range.end + " of " + items.count);
                    }
                });
            });
        });
    
    