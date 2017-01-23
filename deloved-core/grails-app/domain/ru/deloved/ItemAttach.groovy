package ru.deloved

class ItemAttach {

	Profile owner
	Attachment image
	Attachment imageThumb
	Date dateCreated
	static belongsTo = [item: Item]

	static constraints = {
		item(nullable: true)
	}
}
