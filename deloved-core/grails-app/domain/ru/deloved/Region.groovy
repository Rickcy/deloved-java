package ru.deloved

class Region {
	String name
	String fullName
	Region parent
	RegionLevel level

	void setParent(Region parent) {
		this.parent = parent
		setLevelByParent()
	}

	private void setLevelByParent() {
		if (this.parent && this.level == null) {
			this.level = RegionLevel.findByParent(this.parent.level)
		}
	}


	static mappedBy = [parent: "none"]

	static mapping = {
		version(false)
		level(fetch: 'join')
		autoTimestamp(false)
	}

	static constraints = {
		name blank: false
		fullName nullable: true
		parent nullable: true
		level nullable: false
	}

	@Override
	public java.lang.String toString() {
		return "Region{" +
				"id=" + id +
				", name='" + name + '\'' +
				", fullName='" + fullName + '\'' +
				", parent=" + parentId +
				", level=" + levelId +
				"} ";
	}
}
