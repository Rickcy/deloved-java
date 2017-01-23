package ru.deloved.recall

import ru.deloved.Account
import ru.deloved.Profile

class TicketPost {

	Ticket ticket
	Date dateCreated
	Account account
	Profile person
	String post
	Integer status

	static hasMany = [attachments: TicketPostAttach]

	static constraints = {
		ticket nullable: false
		account nullable: true
		person nullable: true
		post nullable: true, maxSize: 1500
		status nullable: true
	}

	TicketStatus status() {
		this.status ? TicketStatus.valueOf(this.status.intValue()) : null
	}
}
