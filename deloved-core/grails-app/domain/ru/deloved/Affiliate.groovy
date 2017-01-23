package ru.deloved

class Affiliate {
	String phone
	String email
	Region city
	String address

	static mapping = {
		version(false)
		autoTimestamp(false)
	}

	static constraints = {
		phone nullable: true
		email nullable: true, email: true
		city nullable: true
		address blank: false
	}

	@Override
	public String toString() {
		return "Affiliate{" +
				"id=" + id +
				", phone='" + phone + '\'' +
				", email='" + email + '\'' +
				", city=" + cityId +
				", address='" + address + '\'' +
				"} ";
	}
}
