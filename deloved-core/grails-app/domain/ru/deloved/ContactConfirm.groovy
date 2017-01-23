package ru.deloved

class ContactConfirm {

	Profile profile
	int type
	int status = ConfirmStatus.New.value()
	String contact
	String secret
	Date dateCreated
	Date lastUpdated

	int confirmType

	static constraints = {
		profile(nullable: false)
		type(nullable: false)
		status(nullable: false)
		contact(nullable: false, blank: false)
		secret(nullable: true)
		confirmType(nullable: true)
		dateCreated()
		lastUpdated()
	}

	ContactType type() {
		return ContactType.valueOf(this.type)
	}

	ConfirmStatus status() {
		return ConfirmStatus.valueOf(this.status)
	}

	ConfirmType confirmType() {
		return ConfirmType.valueOf(this.confirmType)
	}

}

enum ConfirmStatus {

	New(10),
	Process(20),
	Confirmed(30),
	Failed(40)

	private final int value

	ConfirmStatus(int value) {
		this.value = value
	}

	static public ConfirmStatus valueOf(int code) {
		ConfirmStatus.values().grep { it.value == code }[0] ?: null
	}

	public int value() { return value }
}

enum ContactType {

	Email(10),
	CellPhoneNumber(20)

	private final int value

	ContactType(int value) {
		this.value = value
	}

	static public ContactType valueOf(int code) {
		ContactType.values().grep { it.value == code }[0] ?: null
	}

	public int value() { return value }
}

enum ConfirmType {
	ContactUpdate(10),
	PasswordRecover(20)

	private final int value

	ConfirmType(int value) {
		this.value = value
	}

	static public ConfirmType valueOf(int code) {
		ConfirmType.values().grep { it.value == code }[0] ?: null
	}

	public int value() { return value }
}
