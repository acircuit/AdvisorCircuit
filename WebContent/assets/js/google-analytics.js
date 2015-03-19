var _gaq = _gaq || [];
         _gaq.push(['_setAccount', 'UA-58914838-1']);
         _gaq.push(['_trackPageview']);
         (function() {
           var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
           ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
           var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
         })();
         
   $('.profile-intro .details1 div p').on('click',function(){
	   
	   var label=$(this).parent().parent().find('h2').text();
		 
	   _gaq.push(['_trackEvent', 'Adisor Detail', 'BookASession', label]);
	   
   });   
   