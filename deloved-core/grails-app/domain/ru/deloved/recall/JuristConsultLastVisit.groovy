package ru.deloved.recall

import org.apache.commons.lang.builder.HashCodeBuilder
import ru.deloved.Profile

class JuristConsultLastVisit implements Serializable {
    Profile profile
    JuristConsult juristConsult
    Date visited

    @Override
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (profile) builder.append(profileId)
        if (juristConsult) builder.append(juristConsult)
        builder.toHashCode()
    }

    @Override
    boolean equals(Object other) {
        if (!(other instanceof JuristConsultLastVisit)) {
            return false
        }
        other.profileId == profileId && other.juristConsultId == juristConsultId
    }

    static constraints = {
        profile nullable: false
        juristConsult nullable: false
        visited nullable: false
    }
    static mapping = {
        version false
        id composite: ['profile', 'juristConsult']
    }
}
