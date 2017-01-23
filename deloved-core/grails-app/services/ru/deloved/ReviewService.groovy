package ru.deloved

import grails.transaction.Transactional
import org.joda.time.DateTime

@Transactional
class ReviewService {

	def springSecurityService
	def profileService
	def accountService

	def isReviewExist(Deal deal) {
		User user = springSecurityService.getCurrentUser()
		def existReview = Review.executeQuery("""
				select review from Review review
				where review.deal = :deal
				and review.author = :author
		""", [deal: deal, author: user.profile])
		if (existReview) {
			return true
		} else {
			return false
		}
	}

	def isOldDeal(Deal deal) {
		ArrayList<DealPost> statusPosts = DealPost.executeQuery("""
			select post from DealPost as post
			where post.deal = :deal
			and post.status in (:statusList)
		""", [deal: deal, statusList: [DealStatus.Confirmed.value(), DealStatus.Failed.value()]], [sort: 'dateCreated', order: 'desc'])

		if (statusPosts) {
			if (statusPosts.get(0).dateCreated < DateTime.now().minusDays(30).toDate()) {
				return true
			} else {
				return false
			}
		} else {
			return false
		}
	}

	def canEdit(Review review) {
		User user = springSecurityService.getCurrentUser()
		Boolean canEdit = false
		switch (user.role.authority) {
			case 'ROLE_ADMIN':
				canEdit = true
				break
			case 'ROLE_MANAGER':
				if (review.finished == false && profileService.findAllCitiesByProfile(user.profile).contains(review.from.city)) {
					canEdit = true
					break
				}
			case 'ROLE_ACCOUNT':
				if (review.finished == false && review.author == user.profile) {
					canEdit = true
					break
				}
		}
		return canEdit
 	}
}
