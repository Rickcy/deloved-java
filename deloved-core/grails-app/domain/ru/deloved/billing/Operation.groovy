package ru.deloved.billing

import ru.deloved.Profile

class Operation {

	Date dateCreated
	Profile profile
	Keeper keeper
	int type
	BigDecimal initBalance
	BigDecimal value
	BigDecimal resultBalance
	long document


	static constraints = {
		dateCreated()
		profile(nullable: false)
		keeper(nullable: false)
		type(validator: { val, obj -> DocumentType.valueOf(val) != null })
		initBalance(nullable: true)
		value(nullable: false)
		resultBalance(nullable: true)
		document()
	}

	DocumentType type() {
		this.type ? DocumentType.valueOf(this.type.intValue()) : null
	}

	def beforeInsert() {
		def k = Keeper.lock(keeperId)
		initBalance = k.balance.add(BigDecimal.ZERO)
		k.balance = k.balance.add(value)
		resultBalance = k.balance.add(BigDecimal.ZERO)
		k.save()
	}
}


enum DocumentType {
	Income(10),
	Invoice(20),
	Outcome(30),
	Storno(40)


	private final int value

	DocumentType(int value) {
		this.value = value
	}

	static public DocumentType valueOf(int code) {
		DocumentType.values().grep { it.value == code }[0] ?: null
	}

	public int value() { return value }

}
