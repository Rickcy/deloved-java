package ru.deloved

class DisputePost {

	Dispute dispute
	Date dateCreated
	Account account
	Profile person
	String post
	Integer status

	static hasMany = [attachments: DisputePostAttach]

	static constraints = {
		dispute nullable: false
		account nullable: true
		person nullable: false
		post nullable: true, maxSize: 1500
		status nullable: true
	}

	DisputeStatus status() {
		this.status ? DisputeStatus.valueOf(this.status.intValue()) : null
	}
}
