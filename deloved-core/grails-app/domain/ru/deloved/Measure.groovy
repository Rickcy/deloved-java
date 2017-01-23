package ru.deloved

class Measure {

	String name
	String fullname
	CategoryType type

	static constraints = {
		name nullable: false
		fullname nullable: true
		type nullable: false
	}

	static mapping = {
		type(fetch: 'join')
	}

	@Override
	public String toString() {
		return name + (fullname ? ' (' + fullname + ')' : '')
	}
}
