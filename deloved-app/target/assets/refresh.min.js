function show(){$.ajax({url:"admin",cache:!1,success:function(){$("#asd").html($(".col-lg-3,col-md-4").html())}})}$(document).ready(function(){setInterval("show()",5e3)});
