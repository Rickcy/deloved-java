package ru.deloved

class RegionLevel {
	RegionType type
	RegionLevel parent
	int level

	static mappedBy = [parent: "none"]

	static mapping = {
		version(false)
		type(fetch: 'join')
		autoTimestamp(false)
	}

	static constraints = {
		type nullable: false
		parent nullable: true
		level()
	}
}
