package ru.deloved


//товар или услуга, параметр для Category
class CategoryType {
	String code

	static constraints = {
		code(blank: false, unique: true)
	}

	static mapping = {
		version(false)
		autoTimestamp(false)
	}
}
