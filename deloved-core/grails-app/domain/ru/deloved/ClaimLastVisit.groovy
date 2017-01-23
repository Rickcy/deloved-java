package ru.deloved

import org.apache.commons.lang.builder.HashCodeBuilder

class ClaimLastVisit implements Serializable {
	Profile profile
	Claim claim
	Date visited

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (profile) builder.append(profileId)
		if (claim) builder.append(claim)
		builder.toHashCode()
	}

	@Override
	boolean equals(Object other) {
		if (!(other instanceof ClaimLastVisit)) {
			return false
		}
		other.profileId == profileId && other.claimId == claimId
	}

	static constraints = {
		profile nullable: false
		claim nullable: false
		visited nullable: false
	}
	static mapping = {
		version false
		id composite: ['profile', 'claim']
	}
}
