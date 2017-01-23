package ru.deloved

class Document {

	String name
	String description
	Attachment attachment

	static belongsTo = [category: DocumentCategory]

	static constraints = {
		name(blank: false)
		description(nullable: true, maxSize: 1500)
		attachment(nullable: false)
		category(nullable: true)
	}
}
