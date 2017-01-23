package ru.deloved

class RegionType {

	String code

	static constraints = {
		code(blank: false, unique: true)
	}

	static mapping = {
		version(false)
		autoTimestamp(false)
	}
}
