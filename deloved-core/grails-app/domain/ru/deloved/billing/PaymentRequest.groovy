package ru.deloved.billing

import ru.deloved.Profile

class PaymentRequest {

	Date dateCreated
	Date lastUpdated
	Profile profile
	Keeper keeper
	int status = 0
	BigDecimal value
	PaymentMethod method

	static constraints = {
		dateCreated()
		lastUpdated()
		profile(nullable: false)
		keeper(nullable: false)
		status(validator: { val, obj -> RequestStatus.valueOf(val) != null })
		value(nullable: false)
		method(nullable: false)
	}

	RequestStatus status() {
		return RequestStatus.valueOf(this.status)
	}

}

enum RequestStatus {
	New(0),
	Process(5),
	Executed(10),
	Success(20),
	Failed(30)


	private final int value

	RequestStatus(int value) {
		this.value = value
	}

	static public RequestStatus valueOf(int code) {
		RequestStatus.values().grep { it.value == code }[0] ?: null
	}

	public int value() { return value }

}