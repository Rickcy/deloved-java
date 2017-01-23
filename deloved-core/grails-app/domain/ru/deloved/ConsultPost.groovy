package ru.deloved

class ConsultPost {

	Consult consult
	Date dateCreated
	Account account
	Profile person
	String post
	Integer status

	static hasMany = [attachments: ConsultPostAttach]

	static constraints = {
		consult nullable: false
		account nullable: true
		person nullable: false
		post nullable: true, maxSize: 1500
		status nullable: true
	}

	ConsultStatus status() {
		this.status ? ConsultStatus.valueOf(this.status.intValue()) : null
	}
}
