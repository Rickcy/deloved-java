package ru.deloved

class ClaimPostAttach {

	Claim claim
	Account account
	Profile person
	Attachment attachment

	static belongsTo = [claimPost: ClaimPost]

	static constraints = {
		claimPost nullable: true
		account nullable: true
	}
}
