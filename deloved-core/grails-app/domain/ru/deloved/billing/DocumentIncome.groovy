package ru.deloved.billing

import ru.deloved.Profile

class DocumentIncome {

	Date dateCreated
	Date lastUpdated
	Profile profile
	Keeper keeper
	int status = 0
	BigDecimal value
	Operation operation
	PaymentRequest request

	static constraints = {
		dateCreated()
		lastUpdated()
		profile(nullable: false)
		keeper(nullable: false)
		status(validator: { val, obj -> IncomeStatus.valueOf(val) != null })
		value(nullable: false)
		operation(nullable: true)
		request(nullable: false)
	}

	IncomeStatus status() {
		return IncomeStatus.valueOf(this.status)
	}

}

enum IncomeStatus {
	New(0),
	Process(10),
	Success(20),
	Failed(30)


	private final int value

	IncomeStatus(int value) {
		this.value = value
	}

	static public IncomeStatus valueOf(int code) {
		IncomeStatus.values().grep { it.value == code }[0] ?: null
	}

	public int value() { return value }

}