package ru.deloved

import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * Регионы профиля.
 *
 * ROLE_MANAGER - область действия менеджера. Назначается Админом
 * Менеджер имеет доступ ко всем специалистам и участником из городов своей области действия.
 *
 * ROLE_MEDIATOR, ROLE_JURIST, ROLE_JUDGE - область действия специалиста. Назначается Админом или Менеджером
 * Специалисты имеют доступ к соответствующим материалам(сделки, споры и т.д.) учасников из городов своей области действия.
 */
class ProfileRegion implements Serializable {

	Profile profile
	Region region

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (profile) builder.append(profileId)
		if (region) builder.append(regionId)
		builder.toHashCode()
	}

	@Override
	boolean equals(Object other) {
		if (!(other instanceof ProfileRegion)) {
			return false
		}
		other.profileId == profileId && other.regionId == regionId
	}

	static constraints = {
		profile nullable: false
		region nullable: false
	}
	static mapping = {
		version false
		id composite: ['profile', 'region']
	}
}
