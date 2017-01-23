package ru.deloved.billing

class PaymentSystem {

	String name

	static hasMany = [methods: PaymentMethod]

	static constraints = {
		name(blank: false)
	}
}
