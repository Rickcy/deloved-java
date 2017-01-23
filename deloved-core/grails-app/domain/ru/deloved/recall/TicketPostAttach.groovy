package ru.deloved.recall

import ru.deloved.Account
import ru.deloved.Attachment

class TicketPostAttach {

	Ticket ticket
	Account account
	Attachment attachment

	static belongsTo = [ticketPost: TicketPost]

	static constraints = {
		account nullable: true
		ticketPost nullable: true
	}
}
