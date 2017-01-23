package ru.deloved

import grails.plugin.cache.Cacheable
import org.grails.databinding.BindUsing
import org.grails.databinding.DataBindingSource
import org.hibernate.annotations.Cache
import org.springframework.web.servlet.support.RequestContextUtils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.ParsePosition

class Item {

	Account account
	Category category
	CategoryType categoryType
	String name
	String kind
	BigDecimal price

	SystemCurrency currency
	Measure measure
	int measureValue = 1
	int availability
	String description
	Date dateCreated

	ItemAttach photo



	Boolean showMain = false
	static belongsTo = Profile
	static hasMany = [photos: ItemAttach,
					  newProfiles: Profile]

	static constraints = {
		account nullable: false
		category nullable: false
		categoryType nullable: false
		name nullable: false
		kind blank: true
		price nullable: false
		currency nullable: false
		measure nullable: false
		description blank: false, maxSize: 1500
		photo(nullable: true)
		showMain(nullable: true)
	}

	static mapping = {
		photos(sort: 'id', order: 'asc')
	}
}
