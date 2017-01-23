package ru.deloved

class DealPost {

	Deal deal
	Date dateCreated
	Account account
	Profile person
	String post
	Integer status
	Dispute dispute
	Claim claim

	static hasMany = [attachments: DealPostAttach]

	static constraints = {
		deal nullable: false
		account nullable: true
		person nullable: false
		post nullable: true, maxSize: 1500
		status nullable: true
		dispute nullable: true
		claim nullable: true
	}

	DealStatus status() {
		this.status != null ? DealStatus.valueOf(this.status.intValue()) : null
	}

	static mapping = {

		person lazy: false
		attachments lazy: false
	}


}
