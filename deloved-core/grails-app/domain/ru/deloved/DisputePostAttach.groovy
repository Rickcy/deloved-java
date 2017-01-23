package ru.deloved

class DisputePostAttach {

	Dispute dispute
	Account account
	Profile person
	Attachment attachment

	static belongsTo = [disputePost: DisputePost]

	static constraints = {
		disputePost nullable: true
		account nullable: true
	}
}
