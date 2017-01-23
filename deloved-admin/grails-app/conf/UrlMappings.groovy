class UrlMappings {


   	static mappings = {

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

		"404"(view: "/notfound")
        "500"(view:'/error')


	}
}
