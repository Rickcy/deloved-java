package ru.deloved

import ru.deloved.Account
import ru.deloved.Profile

class Consult {

	Account account
	Profile jurist
	Date dateCreated
	int status

	int consultcat = 0
	Deal deal
	Account defendant

	static belongsTo = Profile
	static hasMany = [newProfiles: Profile]

	static constraints = {
		account nullable: true
		jurist nullable: true
		consultcat nullable: true
		deal nullable: true
		defendant nullable: true
	}

	ConsultStatus status() {
		return ConsultStatus.valueOf(this.status)
	}

	ConsultCategory category(){
		return  ConsultCategory.valueOf(this.consultcat)
	}
}

enum ConsultCategory {
	None(0),
	Claim(10),
	Dispute(20)

	private final int value

	ConsultCategory(int value) {
		this.value = value
	}

	static public ConsultCategory valueOf(int code) {
		ConsultCategory.values().grep { it.value == code }[0] ?: null
	}

	public int value() { return value }

}

enum ConsultStatus {
	New(0),
	Processing(10),
	Closed(20)


	private final int value

	ConsultStatus(int value) {
		this.value = value
	}

	static public ConsultStatus valueOf(int code) {
		ConsultStatus.values().grep { it.value == code }[0] ?: null
	}

	public int value() { return value }

}