package ru.deloved

class SystemCurrency {

	String name
	String code
	String digit3

	static constraints = {
		name(blank: false)
		code(blank: false, minSize: 3, maxSize: 3, matches: "[A-Z]{3}")
		digit3(blank: false, minSize: 3, maxSize: 3, matches: "[0-9]{3}")
	}

	public String getSymbol(Locale locale) {
		return (Currency.getInstance(code)?.getSymbol(locale)) ?: code;
	}
}
