package ru.deloved

import org.apache.commons.lang.builder.HashCodeBuilder

class AccountProfile implements Serializable {
	Account account
	Profile profile

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (account) builder.append(accountId)
		if (profile) builder.append(profileId)
		builder.toHashCode()
	}

	@Override
	boolean equals(Object other) {
		if (!(other instanceof AccountProfile)) {
			return false
		}
		other.accountId == accountId && other.profileId == profileId
	}

	static constraints = {
		account nullable: false
		profile nullable: false
	}
	static mapping = {
		version false
		id composite: ['account', 'profile']
	}
}
