package ru.deloved.recall

class SuggestionCategory {



	String name

	static constraints = {
		name unique: true
	}
}
