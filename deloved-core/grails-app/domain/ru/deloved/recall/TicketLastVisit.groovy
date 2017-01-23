package ru.deloved.recall

import org.apache.commons.lang.builder.HashCodeBuilder
import ru.deloved.Profile

class TicketLastVisit implements Serializable {
	Profile profile
	Ticket ticket
	Date visited

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (profile) builder.append(profileId)
		if (ticket) builder.append(ticket)
		builder.toHashCode()
	}

	@Override
	boolean equals(Object other) {
		if (!(other instanceof TicketLastVisit)) {
			return false
		}
		other.profileId == profileId && other.ticketId == ticketId
	}

	static constraints = {
		profile nullable: false
		ticket nullable: false
		visited nullable: false
	}
	static mapping = {
		version false
		id composite: ['profile', 'ticket']
	}
}
