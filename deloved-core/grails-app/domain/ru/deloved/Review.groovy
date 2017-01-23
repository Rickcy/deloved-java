package ru.deloved

class Review {

	String content
	String remark
	Integer value
	boolean published
	boolean finished
	Deal deal
	Account from
	Account to
	Profile author
	Date dateCreated
	Date datePublished
	Date lastUpdated

	static belongsTo = Profile
	static hasMany = [
			attachments: ReviewAttach,
			newProfiles: Profile
	]

	static constraints = {
		content blank: false, maxSize: 1500
		value nullable: false
		remark nullable: true, maxSize: 800
		deal nullable: true
		from nullable: false
		to nullable: false
		author nullable: false
		datePublished nullable: true
		lastUpdated nullable: true
	}

	ReviewValue value() {
		return ReviewValue.valueOf(this.value)
	}


	def beforeInsert() {
		datePublished = new Date()
	}

	def beforeUpdate() {
		if (published && isDirty("published")) {
			datePublished = new Date()
		}
	}
}

enum ReviewValue {
	Good(1),
	Neutral(0),
	Bad(-1)

	private final int value

	ReviewValue(int value) {
		this.value = value
	}

	static public ReviewValue valueOf(int code) {
		ReviewValue.values().grep { it.value == code }[0] ?: null
	}

	public int value() { return value }

}
