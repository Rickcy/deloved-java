package ru.deloved

class ReviewAttach {

	Account account
	Attachment attachment

	static belongsTo = [review: Review]

	static constraints = {
	}
}
