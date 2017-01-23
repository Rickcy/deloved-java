package ru.deloved

class DocumentCategory {

	String name

	static hasMany = [documents: Document]

	static constraints = {
		name()
	}
}
