package ru.deloved

class OrgForm {
	String code
	String name

	static constraints = {
		code blank: false, unique: true
		name blank: false
	}
}
