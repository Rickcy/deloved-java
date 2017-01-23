package ru.deloved

class ClaimPost {

	Claim claim
	Date dateCreated
	Account account
	Profile person
	String post
	Integer status

	static hasMany = [attachments: ClaimPostAttach]

	static constraints = {
		claim nullable: false
		account nullable: true
		person nullable: false
		post nullable: true, maxSize: 1500
		status nullable: true
	}

	ClaimStatus status() {
		this.status ? ClaimStatus.valueOf(this.status.intValue()) : null
	}
}
