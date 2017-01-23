package ru.deloved

class ConsultPostAttach {

	Consult consult
	Account account
	Attachment attachment

	static belongsTo = [consultPost: ConsultPost]

	static constraints = {
		account nullable: true
		consultPost nullable: true
	}
}
