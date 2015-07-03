$(document).ready(function(){
	$(document).on('click','.ac-dismiss', function(event){
		var $acAlert = $(this).parent('.ac-alert');
		if($acAlert) $acAlert.remove();
	});
	
	$(document).on('click','.ac-resend', function(event){
		//Placeholder for resend confirmation email code
	});
});