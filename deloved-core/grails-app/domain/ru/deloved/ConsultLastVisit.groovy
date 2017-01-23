package ru.deloved

import org.apache.commons.lang.builder.HashCodeBuilder

class ConsultLastVisit implements Serializable {
	Profile profile
	Consult consult
	Date visited

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (profile) builder.append(profileId)
		if (consult) builder.append(consult)
		builder.toHashCode()
	}

	@Override
	boolean equals(Object other) {
		if (!(other instanceof ConsultLastVisit)) {
			return false
		}
		other.profileId == profileId && other.consultId == consultId
	}

	static constraints = {
		profile nullable: false
		consult nullable: false
		visited nullable: false
	}
	static mapping = {
		version false
		id composite: ['profile', 'consult']
	}
}
