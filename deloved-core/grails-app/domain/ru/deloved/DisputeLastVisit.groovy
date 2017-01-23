package ru.deloved

import org.apache.commons.lang.builder.HashCodeBuilder

class DisputeLastVisit implements Serializable {
	Profile profile
	Dispute dispute
	Date visited

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (profile) builder.append(profileId)
		if (dispute) builder.append(dispute)
		builder.toHashCode()
	}

	@Override
	boolean equals(Object other) {
		if (!(other instanceof DisputeLastVisit)) {
			return false
		}
		other.profileId == profileId && other.disputeId == disputeId
	}

	static constraints = {
		profile nullable: false
		dispute nullable: false
		visited nullable: false
	}
	static mapping = {
		version false
		id composite: ['profile', 'dispute']
	}
}
