package ru.deloved

class FrontTagLib {
	static defaultEncodeAs = [taglib: 'text']
	//static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

	def staticContent = { attrs, body ->
		if (attrs.code) {
			def content = Content.findByCodeAndEnabled(attrs.code, true)
			if (content) {
				out << content.content
			}
		}
	}
	def newsContent = { attrs, body ->
		if (attrs.code) {
			def content = Content.findByCodeAndEnabled(attrs.code, true)
			if (content) {
				out << '<div class="new" style="padding-left:0;">'
				out << '<h5><b>'+content.time+'</b></h5>'
				out << '<h4>'+content.name+'</h4>'
				out << '<b>'+content.content+'</b>'
				out << '</div><hr>'
			}
		}
	}
	def DescKeyContent = { attrs, body ->
		if (attrs.code) {
			def content = Content.findByCodeAndEnabled(attrs.code, true)
			if (content) {
				out << '<title>'+content.title+'</title>'
				out << '<meta name="keywords"'
				out << ' content="'+content.keywords
				out << '">'
				out << '<meta name="description"'
				out << ' content="'+content.description
				out << '">'
			}
		}
	}
	def titleContent ={ attrs, body ->
		if (attrs.code) {
			def content = Content.findByCodeAndEnabled(attrs.code, true)
			if (content) {
				out << content.title

			}
		}
	}
	def ContContent = { attrs, body ->
		if (attrs.code) {
			def content = Content.findByCodeAndEnabled(attrs.code, true)
			if (content) {
				out << '<div>'
				out << '<h1 class="ContContent">'+content.name+'</h1>'
				out << '<p class="ContContentP">'
				out << content.content
				out << '</p>'
				out << '</div>'
			}
		}
	}
	def ContContentColumn1 = { attrs, body ->
		if (attrs.code) {
			def content = Content.findByCodeAndEnabled(attrs.code, true)
			if (content) {

				out << content.content

			}
		}
	}
	def ContContentColumn2 = { attrs, body ->
		if (attrs.code) {
			def content = Content.findByCodeAndEnabled(attrs.code, true)
			if (content) {



				out << content.content2


			}
		}
	}
}
