     var client = new ZeroClipboard( $("#click-to-copy"), {
              moviePath: "http://www.paulund.co.uk/playground/demo/zeroclipboard-demo/zeroclipboard/ZeroClipboard.swf",
              debug: false
            } );

            client.on( "load", function(client)
            {
                $('#flash-loaded').fadeIn();

                client.on( "complete", function(client, args) {
                    client.setText( "Set text copied." );
                    $('#click-to-copy-text').fadeIn();
                } );
            } );
        
    
    });