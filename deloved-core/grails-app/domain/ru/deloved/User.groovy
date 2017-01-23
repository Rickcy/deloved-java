package ru.deloved

class User {

	def userService

	String username
	String password
	boolean enabled
	Date dateCreated
	Role role
	Role roleRequest
	def accountService

	static hasOne = [profile: Profile]

	static constraints = {
		username(blank: false, unique: true)
		password(blank: false)
		enabled()
		dateCreated()
		profile(nullable: false, unique: true)
		role(nullable: false)
		roleRequest(nullable: true)
	}

	static mapping = {
		table("users")
		profile(fetch: 'join')
		role(fetch: 'join')
//        password column: '`password`'
	}

	Set<Role> getAuthorities() {
		[role]
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		log.debug("User.beforeUpdate()")
		if (isDirty('password')) {
			log.debug("User.beforeUpdate() dirty password")
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = userService.encodePassword(password)
	}


	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", enabled=" + enabled +
				", dateCreated=" + dateCreated +
				", version=" + version +
				", profile=" + profileId +
				", role=" + roleId +
				'}';
	}
}
