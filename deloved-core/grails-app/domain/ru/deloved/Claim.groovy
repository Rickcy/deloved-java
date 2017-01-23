package ru.deloved

class Claim {
	/**
	 * Истец
	 */
	Account account
	/**
	 * Ответчик
	 */
	Account partner
	Profile judge
	Deal deal
	Date dateCreated
	int status
	Account failedBy


	static belongsTo = Profile
	static hasMany = [newProfiles: Profile]

	static constraints = {
		deal(nullable: false)
		judge(nullable: true)
		failedBy(nullable: true)
	}

	ClaimStatus status() {
		return ClaimStatus.valueOf(this.status)
	}

}

enum ClaimStatus {
	New(0),
	Return(5),
	Processing(10),
	Resolve(20),
	ResolveWS(25),
	Failed(30)


	private final int value

	ClaimStatus(int value) {
		this.value = value
	}

	static public ClaimStatus valueOf(int code) {
		ClaimStatus.values().grep { it.value == code }[0] ?: null
	}

	public int value() { return value }

}