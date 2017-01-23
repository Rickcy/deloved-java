class FrontUrlMappings {

	static mappings = {

		"/company/$id?"(controller: "company", action: "index")
		"/companies/"(controller: "companies", action: "index")

		"/goods/$action?"(controller: "goods") {
			categoryTypeCode = 'GOOD'
		}
		"/goods/search"(controller: "goods", action: "search") {
			categoryTypeCode = 'GOOD'
		}
		"/goods/item/$id"(controller: "goods", action: 'item')
		"/services/$action?"(controller: "services") {
			categoryTypeCode = 'SERVICE'
		}
		"/services/item/$id"(controller: "services", action: 'item')

		"/signup/$action?"(controller: "signup")

		"/files/$action/$id/$name?"(controller: "files")

		"/"(controller: "front")
		"500"(view: '/error')
	}
}
