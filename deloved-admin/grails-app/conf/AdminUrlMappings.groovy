import ru.deloved.CategoryType

class AdminUrlMappings {

	static mappings = {

		group "/admin", {

			"/user/$action?/$id?"(controller: "user")

			"/billing/$action?/$id?"(controller: "billing")
			"/paymentRequest/$action?/$id?"(controller: "paymentRequest")

			"/account/$action?/$id?"(controller: "account")
			"/account/logo/$id/$name?"(controller: "account", action: "logo")

			"/category/$action?/$id?"(controller: "category")

			"/region/$action?/$id?"(controller: "region")

			"/currency/$action?/$id?"(controller: "systemCurrency")
			"/tarif/$action?/$id?"(controller: "tariffPrice")

			"/measure/$action?/$id?"(controller: "measure")
			"/content/$action?/$id?"(controller: "content")

			"/docs/$action?/$id?"(controller: "document")
			"/docs/download/$id/$name?"(controller: "document", action: "download")
			"/docs/deleteatt/$id/$name?"(controller: "document", action: "deleteatt")

			"/profile/$action?/$id?"(controller: "profile")
			"/profile/avatar/$id/$name?"(controller: "profile", action: "avatar")

			"/deal/$action?/$id?"(controller: "deal")
			"/deal/thumb/$id/$name?"(controller: "deal", action: "thumb")
			"/deal/download/$id/$name?"(controller: "deal", action: "download")
			"/deal/deleteatt/$id/$name?"(controller: "deal", action: "deleteatt")

		//	"/deal/posts/$id?(.${format})?"(controller: 'deal', action: 'posts', id: $id, format: $format)

			"/review/" (controller: "review", action: "index")
			"/review/$action?/$id?"(controller: "review")
			"/review/thumb/$id/$name?"(controller: "review", action: "thumb")
			"/review/download/$id/$name?"(controller: "review", action: "download")
			"/review/deleteatt/$id/$name?"(controller: "review", action: "deleteatt")

			"/dispute/$action?/$id?"(controller: "dispute")
			"/dispute/thumb/$id/$name?"(controller: "dispute", action: "thumb")
			"/dispute/download/$id/$name?"(controller: "dispute", action: "download")
			"/dispute/deleteatt/$id/$name?"(controller: "dispute", action: "deleteatt")

			"/claim/$action?/$id?"(controller: "claim")
			"/claim/thumb/$id/$name?"(controller: "claim", action: "thumb")
			"/claim/download/$id/$name?"(controller: "claim", action: "download")
			"/claim/deleteatt/$id/$name?"(controller: "claim", action: "deleteatt")

			"/consult/$action?/$id?"(controller: "consult")
			"/consult/thumb/$id/$name?"(controller: "consult", action: "thumb")
			"/consult/download/$id/$name?"(controller: "consult", action: "download")
			"/consult/deleteatt/$id/$name?"(controller: "consult", action: "deleteatt")

			"/recover/$action?"(controller: "recover")

			"/admintool/$action?"(controller: "adminTool")

			"/item/$action?/$id?"(controller: "item")

			"/adcontent/$action?/$id?"(controller: "adcontent")
			"/adcontent/download/$id/$name?"(controller: "adcontent", action: "download")

			name "GOOD": "/goods/$action?"(controller: "item") {
				categoryTypeCode = 'GOOD'
			}

			name "SERVICE": "/services/$action?"(controller: "item") {
				categoryTypeCode = 'SERVICE'
			}

			"/"(controller: "panel")
			"404"(view: '/notfound')
			"500"(view: '/error')

		}

	}
}
