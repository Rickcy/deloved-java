+function($){"use strict";function Plugin(option){return this.each(function(){var $this=$(this),data=$this.data("bs.button"),options="object"==typeof option&&option;data||$this.data("bs.button",data=new Button(this,options)),"toggle"==option?data.toggle():option&&data.setState(option)})}var Button=function(element,options){this.$element=$(element),this.options=$.extend({},Button.DEFAULTS,options),this.isLoading=!1};Button.VERSION="3.2.0",Button.DEFAULTS={loadingText:"loading..."},Button.prototype.setState=function(state){var d="disabled",$el=this.$element,val=$el.is("input")?"val":"html",data=$el.data();state+="Text",null==data.resetText&&$el.data("resetText",$el[val]()),$el[val](null==data[state]?this.options[state]:data[state]),setTimeout($.proxy(function(){"loadingText"==state?(this.isLoading=!0,$el.addClass(d).attr(d,d)):this.isLoading&&(this.isLoading=!1,$el.removeClass(d).removeAttr(d))},this),0)},Button.prototype.toggle=function(){var changed=!0,$parent=this.$element.closest('[data-toggle="buttons"]');if($parent.length){var $input=this.$element.find("input");"radio"==$input.prop("type")&&($input.prop("checked")&&this.$element.hasClass("active")?changed=!1:$parent.find(".active").removeClass("active")),changed&&$input.prop("checked",!this.$element.hasClass("active")).trigger("change")}changed&&this.$element.toggleClass("active")};var old=$.fn.button;$.fn.button=Plugin,$.fn.button.Constructor=Button,$.fn.button.noConflict=function(){return $.fn.button=old,this},$(document).on("click.bs.button.data-api",'[data-toggle^="button"]',function(e){var $btn=$(e.target);$btn.hasClass("btn")||($btn=$btn.closest(".btn")),Plugin.call($btn,"toggle"),e.preventDefault()})}(jQuery);