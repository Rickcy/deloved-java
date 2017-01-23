package ru.deloved.billing

import ru.deloved.SystemCurrency

class TariffPrice {

	String name
	int days
	int weeks
	int months
	int years
	BigDecimal price
	SystemCurrency currency

	static constraints = {
		name(blank: false)
		days()
		weeks()
		months()
		years()
		price(nullable: false)
		currency(nullable: false)
	}

	@Override
	public String toString() {
		return "TariffPrice{" +
				"id=" + id +
				", name='" + name + '\'' +
				", days=" + days +
				", weeks=" + weeks +
				", months=" + months +
				", years=" + years +
				", price=" + price +
				", currency=" + currency?.code +
				'}';
	}
}
