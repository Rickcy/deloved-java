package ru.deloved.recall

import ru.deloved.Account
import ru.deloved.Profile


class Ticket {

	Account account
	Profile support
	Date dateCreated
	int status

	static belongsTo = Profile
	static hasMany = [newProfiles: Profile]

	static constraints = {
		account nullable: true
		support nullable: true
	}

	TicketStatus status() {
		return TicketStatus.valueOf(this.status)
	}
	def position() {
		return TicketStatus.getPosition(this.status)
	}

}

enum TicketStatus {
	New(0),
	Processing(10),
	Closed(20)


	private final int value

	TicketStatus(int value) {
		this.value = value
	}

	static public TicketStatus valueOf(int code) {
		TicketStatus.values().grep { it.value == code }[0] ?: null
	}
	static public ArrayList<TicketStatus> FullStatus() {
		return [TicketStatus.Processing, TicketStatus.Closed]
	}

	public int position() {
		TicketStatus.getPosition(this.value)
	}
	static public int getPosition(int code){
		switch (code) {
			case 0: return 10
			case 10: return 50
			case 20: return 100
		}
		return 100
		}
	public int value() { return value }

}