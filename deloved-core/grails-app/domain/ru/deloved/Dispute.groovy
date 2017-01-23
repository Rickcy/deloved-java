package ru.deloved

class Dispute {
	/**
	 * Заказчик
	 */
	Account account
	/**
	 * Подрядчик
	 */
	Account partner
	Profile mediator
	Deal deal
	Date dateCreated
	int status
	Account failedBy

	static belongsTo = Profile
	static hasMany = [newProfiles: Profile]

	static constraints = {

		deal(nullable: false)
		mediator(nullable: true)
		failedBy(nullable: true)
	}

	DisputeStatus status() {
		return DisputeStatus.valueOf(this.status)
	}

}

enum DisputeStatus {
	New(0),
	Processing(10),

	ResolveWS(25),
	Failed(30)


	private final int value

	DisputeStatus(int value) {
		this.value = value
	}

	static public DisputeStatus valueOf(int code) {
		DisputeStatus.values().grep { it.value == code }[0] ?: null
	}

	public int value() { return value }

}