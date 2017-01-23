package ru.deloved.recall

import ru.deloved.Profile

class Suggestion {


	String title
	String content
	SuggestionCategory category
	Date datePublished
	Profile author
//	static belongsTo = Profile
//	static hasMany = [newProfiles: Profile]

	static constraints = {
		title nullable: false, maxSize: 100
		content nullable: false, maxSize: 1000
		category nullable: true
		author nullable: true
		datePublished nullable: true


	}

	def beforeInsert() {
		datePublished = new Date()
	}

}
