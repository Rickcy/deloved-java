package ru.deloved

import org.apache.commons.lang.builder.HashCodeBuilder

class AccountCategory implements Serializable {
	Account account
	Category category

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (account) builder.append(accountId)
		if (category) builder.append(categoryId)
		builder.toHashCode()
	}

	@Override
	boolean equals(Object other) {
		if (!(other instanceof AccountCategory)) {
			return false
		}
		other.accountId == accountId && other.categoryId == categoryId
	}

	static constraints = {
		account nullable: false
		category nullable: false
	}
	static mapping = {
		version false
		id composite: ['account', 'category']
	}
}
