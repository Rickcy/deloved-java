package ru.deloved.billing

import ru.deloved.Profile

class DocumentInvoice {

	Date dateCreated
	Date lastUpdated
	Profile profile
	Keeper keeper
	TariffPrice tariffPrice
	int status = 0
	BigDecimal value
	Operation operation

	static constraints = {
		dateCreated()
		lastUpdated()
		profile(nullable: false)
		keeper(nullable: false)
		tariffPrice(nullable: false)
		status(validator: { val, obj -> InvoiceStatus.valueOf(val) != null })
		value(nullable: false)
		operation(nullable: true)
	}
}

enum InvoiceStatus {
	New(0),
	Process(10),
	Success(20),
	Failed(30)


	private final int value

	InvoiceStatus(int value) {
		this.value = value
	}

	static public InvoiceStatus valueOf(int code) {
		InvoiceStatus.values().grep { it.value == code }[0] ?: null
	}

	public int value() { return value }

}