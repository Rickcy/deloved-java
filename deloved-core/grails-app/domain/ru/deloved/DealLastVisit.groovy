package ru.deloved

import org.apache.commons.lang.builder.HashCodeBuilder

class DealLastVisit implements Serializable {
	Profile profile
	Deal deal
	Date visited

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (profile) builder.append(profileId)
		if (deal) builder.append(deal)
		builder.toHashCode()
	}

	@Override
	boolean equals(Object other) {
		if (!(other instanceof DealLastVisit)) {
			return false
		}
		other.profileId == profileId && other.dealId == dealId
	}

	static constraints = {
		profile nullable: false
		deal nullable: false
		visited nullable: false
	}
	static mapping = {
		version false
		id composite: ['profile', 'deal']
	}
}
