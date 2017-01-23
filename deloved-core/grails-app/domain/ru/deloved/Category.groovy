package ru.deloved

class Category {
	String name
	CategoryType type
	Category parent

	void setParent(Category parent) {
		this.parent = parent
		if (this.parent) {
			this.type = parent.type
		}
	}

	static mappedBy = [parent: "none"]

	static mapping = {
		type column: 'categoryType_Id'
		type(fetch: 'join')
	}
	static constraints = {
		parent nullable: true
	}

	def beforeInsert() {
		if (parent) {
			type = parent.type
		}
	}

	@Override
	public java.lang.String toString() {
		return "Category{" +
				"id=" + id +
				", name='" + name + '\'' +
				", type=" + type +
				", parent=" + parentId +
				"} ";
	}
}
