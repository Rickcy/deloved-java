!function($){"use strict";$.jstree.defaults.conditionalselect=function(){return!0},$.jstree.plugins.conditionalselect=function(options,parent){this.select_node=function(obj,supress_event,prevent_open){this.settings.conditionalselect.call(this,this.get_node(obj))&&parent.select_node.call(this,obj,supress_event,prevent_open)}}}(jQuery),function($){"use strict";$.jstree.defaults.onlyleafselect={toggle_group:!0},$.jstree.plugins.onlyleafselect=function(options,parent){this.activate_node=function(obj,e){this.is_leaf(this.get_node(obj))?parent.activate_node.call(this,obj,e):this.settings.onlyleafselect.toggle_group&&(this.is_closed(obj)?this.open_node(obj):this.close_node(obj,!1,0))}}}(jQuery);