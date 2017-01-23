package ru.deloved

class Role {

	String authority

	static mapping = {
		cache true
		version false
	}

	static constraints = {
		authority blank: false, unique: true
	}


	@Override
	public java.lang.String toString() {
		return authority;
	}
}
