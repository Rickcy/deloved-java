package ru.deloved

class Account  {
	String name
	String fullName
	String brandName
	OrgForm orgForm
	String regNumber
	String inn
	String kpp
	String legalAddress
	Date regDate
	String phone1
	String phone2
	String phone3
	String fax1
	String fax2
	String webAddress
	String email
	Attachment logo
	Attachment logoThumb
	String description
	String manager
	String workTime
	Region city
	String address
	String keywords
	Collection affiliates
	boolean publicStatus
	boolean verifyStatus
	Integer rating

	Date dateCreated
	Date lastUpdated

	Boolean showMain = false

	def accountService
	static belongsTo = Profile
	static hasMany = [affiliates: Affiliate,
					  newProfiles: Profile]

	static constraints = {
		name blank: false,unique: true
		fullName blank: false
		brandName blank: false
		orgForm nullable: false
		regNumber blank: false, minSize: 13, maxSize: 15, matches: '(\\d{13}|\\d{15})'
		inn minSize: 10, maxSize: 12, matches: '(\\d{10}|\\d{12})', blank: false//, unique: true
//				validator: { val, obj ->
//					if (obj.id)
//						return obj.accountService.isInnAvailable(val, obj.id)
//					else
//						return obj.accountService.isInnAvailable(val)
//				}


		kpp nullable: true, blank: true, minSize: 9, maxSize: 9, matches: '\\d{9}'
		legalAddress blank: false
		regDate nullable: false
		phone1 blank: false
		phone2 nullable: true
		phone3 nullable: true
		fax1 nullable: true
		fax2 nullable: true
		webAddress nullable: true
		email nullable: true
		logo nullable: true
		logoThumb nullable: true
		description nullable: true, maxSize: 1500
		manager blank: false
		workTime blank: false
		city nullable: false
		address nullable: true
		keywords nullable: true, maxSize: 300
		affiliates nullable: true
		rating nullable: true
		showMain(nullable: true)
	}

	/*
	def beforeUpdate(){
	accountService.renameProfileFio(this)
	}


	def beforeInsert(){
		accountService.renameProfileFio(this)
	}
	*/

	static mapping = {
		logo(fetch: 'join')
		logoThumb(fetch: 'join')
		city(fetch: 'join')
		orgForm(fetch: 'join')
	}
}
