!function(window,angular){"use strict";function $AriaProvider(){function watchExpr(attrName,ariaAttr,negate){return function(scope,elem,attr){var ariaCamelName=attr.$normalize(ariaAttr);config[ariaCamelName]&&!attr[ariaCamelName]&&scope.$watch(attr[attrName],function(boolVal){boolVal=negate?!boolVal:!!boolVal,elem.attr(ariaAttr,boolVal)})}}var config={ariaHidden:!0,ariaChecked:!0,ariaDisabled:!0,ariaRequired:!0,ariaInvalid:!0,ariaMultiline:!0,ariaValue:!0,tabindex:!0,bindKeypress:!0,bindRoleForClick:!0};this.config=function(newConfig){config=angular.extend(config,newConfig)},this.$get=function(){return{config:function(key){return config[key]},$$watchExpr:watchExpr}}}var ngAriaModule=angular.module("ngAria",["ng"]).provider("$aria",$AriaProvider);ngAriaModule.directive("ngShow",["$aria",function($aria){return $aria.$$watchExpr("ngShow","aria-hidden",!0)}]).directive("ngHide",["$aria",function($aria){return $aria.$$watchExpr("ngHide","aria-hidden",!1)}]).directive("ngModel",["$aria",function($aria){function shouldAttachAttr(attr,normalizedAttr,elem){return $aria.config(normalizedAttr)&&!elem.attr(attr)}function shouldAttachRole(role,elem){return!elem.attr("role")&&elem.attr("type")===role&&"INPUT"!==elem[0].nodeName}function getShape(attr,elem){var type=attr.type,role=attr.role;return"checkbox"===(type||role)||"menuitemcheckbox"===role?"checkbox":"radio"===(type||role)||"menuitemradio"===role?"radio":"range"===type||"progressbar"===role||"slider"===role?"range":"textbox"===(type||role)||"TEXTAREA"===elem[0].nodeName?"multiline":""}return{restrict:"A",require:"?ngModel",priority:200,compile:function(elem,attr){var shape=getShape(attr,elem);return{pre:function(scope,elem,attr,ngModel){"checkbox"===shape&&"checkbox"!==attr.type&&(ngModel.$isEmpty=function(value){return value===!1})},post:function(scope,elem,attr,ngModel){function ngAriaWatchModelValue(){return ngModel.$modelValue}function getRadioReaction(){return needsTabIndex?(needsTabIndex=!1,function(){var boolVal=attr.value==ngModel.$viewValue;elem.attr("aria-checked",boolVal),elem.attr("tabindex",0-!boolVal)}):function(){elem.attr("aria-checked",attr.value==ngModel.$viewValue)}}function ngAriaCheckboxReaction(){elem.attr("aria-checked",!ngModel.$isEmpty(ngModel.$viewValue))}var needsTabIndex=shouldAttachAttr("tabindex","tabindex",elem);switch(shape){case"radio":case"checkbox":shouldAttachRole(shape,elem)&&elem.attr("role",shape),shouldAttachAttr("aria-checked","ariaChecked",elem)&&scope.$watch(ngAriaWatchModelValue,"radio"===shape?getRadioReaction():ngAriaCheckboxReaction);break;case"range":if(shouldAttachRole(shape,elem)&&elem.attr("role","slider"),$aria.config("ariaValue")){var needsAriaValuemin=!elem.attr("aria-valuemin")&&(attr.hasOwnProperty("min")||attr.hasOwnProperty("ngMin")),needsAriaValuemax=!elem.attr("aria-valuemax")&&(attr.hasOwnProperty("max")||attr.hasOwnProperty("ngMax")),needsAriaValuenow=!elem.attr("aria-valuenow");needsAriaValuemin&&attr.$observe("min",function(newVal){elem.attr("aria-valuemin",newVal)}),needsAriaValuemax&&attr.$observe("max",function(newVal){elem.attr("aria-valuemax",newVal)}),needsAriaValuenow&&scope.$watch(ngAriaWatchModelValue,function(newVal){elem.attr("aria-valuenow",newVal)})}break;case"multiline":shouldAttachAttr("aria-multiline","ariaMultiline",elem)&&elem.attr("aria-multiline",!0)}needsTabIndex&&elem.attr("tabindex",0),ngModel.$validators.required&&shouldAttachAttr("aria-required","ariaRequired",elem)&&scope.$watch(function(){return ngModel.$error.required},function(newVal){elem.attr("aria-required",!!newVal)}),shouldAttachAttr("aria-invalid","ariaInvalid",elem)&&scope.$watch(function(){return ngModel.$invalid},function(newVal){elem.attr("aria-invalid",!!newVal)})}}}}}]).directive("ngDisabled",["$aria",function($aria){return $aria.$$watchExpr("ngDisabled","aria-disabled")}]).directive("ngMessages",function(){return{restrict:"A",require:"?ngMessages",link:function(scope,elem){elem.attr("aria-live")||elem.attr("aria-live","assertive")}}}).directive("ngClick",["$aria","$parse",function($aria,$parse){return{restrict:"A",compile:function(elem,attr){var fn=$parse(attr.ngClick,null,!0);return function(scope,elem,attr){function isNodeOneOf(elem,nodeTypeArray){return-1!==nodeTypeArray.indexOf(elem[0].nodeName)?!0:void 0}var nodeBlackList=["BUTTON","A","INPUT","TEXTAREA"];!$aria.config("bindRoleForClick")||elem.attr("role")||isNodeOneOf(elem,nodeBlackList)||elem.attr("role","button"),$aria.config("tabindex")&&!elem.attr("tabindex")&&elem.attr("tabindex",0),!$aria.config("bindKeypress")||attr.ngKeypress||isNodeOneOf(elem,nodeBlackList)||elem.on("keypress",function(event){function callback(){fn(scope,{$event:event})}var keyCode=event.which||event.keyCode;(32===keyCode||13===keyCode)&&scope.$apply(callback)})}}}}]).directive("ngDblclick",["$aria",function($aria){return function(scope,elem){$aria.config("tabindex")&&!elem.attr("tabindex")&&elem.attr("tabindex",0)}}])}(window,window.angular);