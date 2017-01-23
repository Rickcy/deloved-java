package ru.deloved

class Content {
	String news
	String name
	String time
	String code
	boolean enabled = true
	String content
	String content2
	String title
	String description
	String keywords
	static constraints = {
		news(nullable: true)
		title(nullable: true)
		description(nullable: true)
		keywords(nullable: true)
		name(nullable: false)
		code(nullable: false)
		time(nullable: true)
		enabled()
		content(nullable: true, maxSize: 5000)
		content2(nullable: true, maxSize: 5000)
	}
}
