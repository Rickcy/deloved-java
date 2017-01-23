package ru.deloved.billing

import ru.deloved.Profile
import ru.deloved.SystemCurrency

class Keeper {

	Profile profile
	String number
	BigDecimal balance = BigDecimal.ZERO
	SystemCurrency currency

	static constraints = {
		profile nullable: false
		number blank: false, unique: true
		balance nullable: false
		currency nullable: false
	}
}
