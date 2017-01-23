package ru.deloved

class CountryDefaults {

	Region country
	SystemCurrency currency

	static constraints = {
		country(nullable: false, unique: true, validator: { val, obj -> return val.level.type.code == 'COUNTRY' })
		currency(nullable: false)
	}
}
