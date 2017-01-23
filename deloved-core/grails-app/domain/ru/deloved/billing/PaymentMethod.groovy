package ru.deloved.billing

import ru.deloved.SystemCurrency

class PaymentMethod {

	String name
	String code
	boolean income = false
	boolean outcome = false
	boolean enabled = false
	SystemCurrency currency

	static belongsTo = [system: PaymentSystem]

	static constraints = {
		name(blank: false)
		code(blank: false)
		income()
		outcome()
		enabled()
		currency(nullable: true)
	}
}
