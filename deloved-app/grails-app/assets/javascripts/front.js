//= require jquery
//= require jquery-ui
//= require bootstrap
//= require select2
//= require gallery
//= require bxslider/jquery.bxslider.min
//= require jstree
//= require img-error
//= require_self
//= require upload
if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}
