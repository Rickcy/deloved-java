package ru.deloved

class DealPostAttach {

	Deal deal
	Account account
	Attachment attachment

	static belongsTo = [dealPost: DealPost]

	static constraints = {
		dealPost nullable: true
	}
}
