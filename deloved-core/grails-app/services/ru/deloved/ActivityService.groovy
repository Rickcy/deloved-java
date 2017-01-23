package ru.deloved

import grails.transaction.Transactional
import groovy.time.TimeCategory
import ru.deloved.recall.JuristConsult
import ru.deloved.recall.JuristConsultLastVisit
import ru.deloved.recall.Suggestion
import ru.deloved.recall.Ticket
import ru.deloved.recall.TicketLastVisit

@Transactional
class ActivityService {

	/**
	 * Список новых сделок для УЧАСТНИКА
	 * @param profile
	 * @return List Deal
	 */
	def getNewDeals(Profile profile) {
		Deal.executeQuery("""
			select d
			  from Deal as d,
				   AccountProfile as ap
			 where d.dateCreated>:date
			   and ap.profile=:profile
			   and (d.account=ap.account or d.partner=ap.account)
			""", [
				profile: profile,
				date   : profile.dateActivity ?: profile.user.dateCreated
		])
	}

	/**
	 * Кол-во новых постов по сделкам, посещенным не ранее месяца назад
	 * @param profile
	 * @return List of Map [item:,cnt:]
	 */
	def getDealNewPostCount(Profile profile) {
		use(TimeCategory) {
			DealLastVisit.executeQuery("""
				  select new map(dp.deal as item, count(dp) as cnt, max(dp.dateCreated) as dateCreated)
					from DealLastVisit as lv,
						 DealPost as dp
				   where lv.profile=:profile
					 and lv.visited > :date
					 and dp.deal=lv.deal
					 and dp.dateCreated>lv.visited
					 and dp.post is not null
				group by dp.deal
				""", [
					profile: profile,
					date   : new Date() - 1.month
			])
		}
	}

	/**
	 * Кол-во новых статусов по сделкам, посещенным не ранее месяца назад
	 * @return List of Map [item:,cnt:]
	 * @return
	 */
	def getDealNewStatusCount(Profile profile) {
		use(TimeCategory) {
			DealLastVisit.executeQuery("""
				  select new map(dp.deal as item, count(dp) as cnt, max(dp.dateCreated) as dateCreated)
					from DealLastVisit as lv,
						 DealPost as dp
				   where lv.profile=:profile
					 and lv.visited > :date
					 and dp.deal=lv.deal
					 and dp.dateCreated>lv.visited
					 and dp.status is not null
				group by dp.deal
				""", [
					profile: profile,
					date   : new Date() - 1.month
			])
		}
	}

	/**
	 * Список новых споров для УЧАСТНИКА или МЕДИАТОРА
	 * @param profile
	 * @return
	 */
	def getNewDisputes(Profile profile) {
		def results = []
		if (profile.user.role.authority == 'ROLE_ACCOUNT') {
			results = Dispute.executeQuery("""
				select d
				  from Dispute as d,
				       AccountProfile as ap
				 where d.dateCreated>:date
				   and ap.profile=:profile
				   and d.partner=ap.account
			""", [
					date   : profile.dateActivity ?: profile.user.dateCreated,
					profile: profile
			])
		} else if (profile.user.role.authority == 'ROLE_MEDIATOR') {
			def regFilter = []
			ProfileRegion.findAllByProfile(profile).each {
				if (it.region.level.type.code == 'CITY') {
					regFilter.add(it.region)
				} else {
					regFilter.addAll(Region.findAllByParent(it.region))
				}
			}
			results = Dispute.executeQuery("""
				select d
				  from Dispute as d
				 where d.dateCreated>:date
				   and d.status=0
				   and (
				   	   d.account.city in (:reglist)
				   	or d.partner.city in (:reglist) )
			""", [
					date   : profile.dateActivity ?: profile.user.dateCreated,
					reglist: regFilter
			])

		}else if (profile.user.role.authority == 'ROLE_ADMIN') {
			results = Dispute.executeQuery("""
				select t from Dispute as t where t.dateCreated>:date
			""",[date: profile.dateActivity ?: profile.user.dateCreated]
			)
		}
		return results
	}

	/**
	 * Кол-во новых постов по спорам, посещенным не ранее месяца назад
	 * @param profile
	 * @return List of Map [item:,cnt:]
	 */
	def getDisputeNewPostCount(Profile profile) {
		use(TimeCategory) {
			DisputeLastVisit.executeQuery("""
				  select new map(dp.dispute as item, count(dp) as cnt, max(dp.dateCreated) as dateCreated)
					from DisputeLastVisit as lv,
						 DisputePost as dp
				   where lv.profile=:profile
					 and lv.visited > :date
					 and dp.dispute=lv.dispute
					 and dp.dateCreated>lv.visited
					 and dp.post is not null
				group by dp.dispute
				""", [
					profile: profile,
					date   : new Date() - 1.month
			])
		}
	}

	/**
	 * Кол-во новых статусов по спорам, посещенным не ранее месяца назад
	 * @return List of Map [item:,cnt:]
	 * @return
	 */
	def getDisputeNewStatusCount(Profile profile) {
		use(TimeCategory) {
			DisputeLastVisit.executeQuery("""
				  select new map(dp.dispute as item, count(dp) as cnt, max(dp.dateCreated) as dateCreated)
					from DisputeLastVisit as lv,
						 DisputePost as dp
				   where lv.profile=:profile
					 and lv.visited > :date
					 and dp.dispute=lv.dispute
					 and dp.dateCreated>lv.visited
					 and dp.status is not null
				group by dp.dispute
				""", [
					profile: profile,
					date   : new Date() - 1.month
			])
		}
	}

	/**
	 * Список новых исков для УЧАСТНИКА или СУДЬИ
	 * @param profile
	 * @return List of Claim
	 */
	def getNewClaims(Profile profile) {
		def results = []
		if (profile.user.role.authority == 'ROLE_ACCOUNT') {
			results = Claim.executeQuery(
					"select d from Claim as d, AccountProfile as ap where d.dateCreated>:date and ap.profile=:profile and d.partner=ap.account",
					[date: profile.dateActivity ?: profile.user.dateCreated, profile: profile]
			)
		} else if (profile.user.role.authority == 'ROLE_JUDGE') {
			def regFilter = []
			ProfileRegion.findAllByProfile(profile.user.profile).each {
				if (it.region.level.type.code == 'CITY') {
					regFilter.add(it.region)
				} else {
					regFilter.addAll(Region.findAllByParent(it.region))
				}
			}
			results = Claim.executeQuery(
					"select d from Claim as d where d.dateCreated>:date and d.status=0 and (d.account.city in (:reglist) or d.partner.city in (:reglist))",
					[date: profile.dateActivity ?: profile.user.dateCreated, reglist: regFilter]
			)

		}else if (profile.user.role.authority == 'ROLE_ADMIN') {
			results = Claim.executeQuery("""
				select t from Claim as t where t.dateCreated>:date
			""",[date: profile.dateActivity ?: profile.user.dateCreated]
			)
		}
		return results

	}
	/**
	 * Кол-во новых постов по спорам, посещенным не ранее месяца назад
	 * @param profile
	 * @return List of Map [item:,cnt:]
	 */
	def getClaimNewPostCount(Profile profile) {
		use(TimeCategory) {
			ClaimLastVisit.executeQuery("""
				  select new map(dp.claim as item, count(dp) as cnt, max(dp.dateCreated) as dateCreated)
					from ClaimLastVisit as lv,
						 ClaimPost as dp
				   where lv.profile=:profile
					 and lv.visited > :date
					 and dp.claim=lv.claim
					 and dp.dateCreated>lv.visited
					 and dp.post is not null
				group by dp.claim
				""", [
					profile: profile,
					date   : new Date() - 1.month
			])
		}
	}

	/**
	 * Кол-во новых статусов по спорам, посещенным не ранее месяца назад
	 * @return List of Map [item:,cnt:]
	 * @return
	 */
	def getClaimNewStatusCount(Profile profile) {
		use(TimeCategory) {
			ClaimLastVisit.executeQuery("""
				  select new map(dp.claim as item, count(dp) as cnt, max(dp.dateCreated) as dateCreated)
					from ClaimLastVisit as lv,
						 ClaimPost as dp
				   where lv.profile=:profile
					 and lv.visited > :date
					 and dp.claim=lv.claim
					 and dp.dateCreated>lv.visited
					 and dp.status is not null
				group by dp.claim
				""", [
					profile: profile,
					date   : new Date() - 1.month
			])
		}
	}



	/**
	 * Список новых консультаций для ЮРИСТА
	 * @param profile
	 * @return
	 */
	def getNewConsults(Profile profile) {
		def results = []
		if (profile.user.role.authority == 'ROLE_JURIST') {
			def regFilter = []
			ProfileRegion.findAllByProfile(profile).each {
				if (it.region.level.type.code == 'CITY') {
					regFilter.add(it.region)
				} else {
					regFilter.addAll(Region.findAllByParent(it.region))
				}
			}
			results = JuristConsult.executeQuery(
					"select d from JuristConsult as d where d.dateCreated>:date and d.account.city in (:reglist) ",
					[date: profile.dateActivity ?: profile.user.dateCreated, reglist: regFilter]

			)
		}
		else if (profile.user.role.authority == 'ROLE_ADMIN') {
			results = JuristConsult.executeQuery("""
				select t from JuristConsult as t where t.dateCreated>:date
			""",[date: profile.dateActivity ?: profile.user.dateCreated]
			)
		}
		return results
	}

	/**
	 * Кол-во новых постов по консультациям, посещенным не ранее месяца назад
	 * @param profile
	 * @return List of Map [item:,cnt:]
	 */
	def getConsultNewPostCount(Profile profile) {
		use(TimeCategory) {
			JuristConsultLastVisit.executeQuery("""
			   select new map(dp.juristConsult as item, count(dp) as cnt, max(dp.dateCreated) as dateCreated)
				 from JuristConsultLastVisit as lv,
				      JuristConsultPost as dp
				where lv.profile=:profile
				  and lv.visited > :date
				  and dp.juristConsult=lv.juristConsult
				  and dp.dateCreated>lv.visited
				  and dp.post is not null
				group by dp.juristConsult
				""", [
					profile: profile,
					date   : new Date() - 1.month
			])
		}
	}

	/**
	 * Кол-во новых статусов по консультациям, посещенным не ранее месяца назад
	 * @param profile
	 * @return List of Map [item:,cnt:]
	 */
	def getConsultNewStatusCount(Profile profile) {
		use(TimeCategory) {
			JuristConsultLastVisit.executeQuery("""
			   select new map(dp.juristConsult as item, count(dp) as cnt, max(dp.dateCreated) as dateCreated)
				 from JuristConsultLastVisit as lv,
				      JuristConsultPost as dp
				where lv.profile=:profile
				  and lv.visited > :date
				  and dp.juristConsult=lv.juristConsult
				  and dp.dateCreated>lv.visited
				  and dp.status is not null
				group by dp.juristConsult
				""", [
					profile: profile,
					date   : new Date() - 1.month
			])
		}
	}


	/**
	 * Список новых тикетов для для специалиста тех.поддержки
	 * @param profile
	 * @return
	 */

	def getNewTickets(Profile profile) {
		def results = []
		if (profile.user.role.authority == 'ROLE_MANAGER') {
			def regFilter = []
			ProfileRegion.findAllByProfile(profile).each {
				if (it.region.level.type.code == 'CITY') {
					regFilter.add(it.region)
				} else {
					regFilter.addAll(Region.findAllByParent(it.region))
				}
			}
			results = Ticket.executeQuery(
					"select t from Ticket as t where t.dateCreated>:date and t.account.city in (:reglist) ",
					[date: profile.dateActivity ?: profile.user.dateCreated, reglist: regFilter]
			)

		}
		else if (profile.user.role.authority == 'ROLE_ADMIN') {
			results = Ticket.executeQuery("""
				select t from Ticket as t where t.dateCreated>:date
			""",[date: profile.dateActivity ?: profile.user.dateCreated]
			)
		}
		return results
	}
	/**
	 * Кол-во новых постов по тикетам, посещенным не ранее месяца назад
	 * @param profile
	 * @return List of Map [item:,cnt:]
	 */
	def getTicketNewPostCount(Profile profile) {
		use(TimeCategory) {
			TicketLastVisit.executeQuery("""
			   select new map(dp.ticket as item, count(dp) as cnt, max(dp.dateCreated) as dateCreated)
				 from TicketLastVisit as lv,
				      TicketPost as dp
				where lv.profile=:profile
				  and lv.visited > :date
				  and dp.ticket=lv.ticket
				  and dp.dateCreated>lv.visited
				  and dp.post is not null
				group by dp.ticket
				""", [
					profile: profile,
					date   : new Date() - 1.month
			])
		}
	}
	/**
	 * Кол-во новых статусов по консультациям, посещенным не ранее месяца назад
	 * @param profile
	 * @return List of Map [item:,cnt:]
	 */
	def getTicketNewStatusCount(Profile profile) {
		use(TimeCategory) {
			TicketLastVisit.executeQuery("""
			   select new map(dp.ticket as item, count(dp) as cnt, max(dp.dateCreated) as dateCreated)
				 from TicketLastVisit as lv,
				      TicketPost as dp
				where lv.profile=:profile
				  and lv.visited > :date
				  and dp.ticket=lv.ticket
				  and dp.dateCreated>lv.visited
				  and dp.status is not null
				group by dp.ticket
				""", [
					profile: profile,
					date   : new Date() - 1.month
			])
		}
	}
/**
 * Список новых отзывов для УЧАСТНИКА, МЕНЕДЖЕРА и АДМИНА
 * @param profile
 * @return List of Review
 */
	def getNewReviews(Profile profile) {
		def results = []
		if (profile.user.role.authority == 'ROLE_ACCOUNT') {
			results = Review.executeQuery("""
				select review from Review as review
				where review.lastUpdated > :date
				and review.author = :profile
				""", [date: profile.dateActivity ?: profile.user.dateCreated, profile: profile]
			)
		} else if (profile.user.role.authority == 'ROLE_MANAGER') {
			def regFilter = []
			ProfileRegion.findAllByProfile(profile).each {
				if (it.region.level.type.code == 'CITY') {
					regFilter.add(it.region)
				} else {
					regFilter.addAll(Region.findAllByParent(it.region))
				}
			}
			results = Review.executeQuery("""
				select review from Review as review
				where ((review.dateCreated > :date) or (review.lastUpdated > :date))
				and review.finished=false
				and ((review.to.city in (:reglist)) or (review.from.city in (:reglist)))
			""", [date: profile.dateActivity ?: profile.user.dateCreated, reglist: regFilter]
			)
		} else if (profile.user.role.authority == 'ROLE_ADMIN') {
			results = Review.executeQuery("""
				select review from Review as review
				where ((review.dateCreated > :date) or (review.lastUpdated > :date))
				and review.finished=false
			""",[date: profile.dateActivity ?: profile.user.dateCreated]
			)
		}
		return results
	}
	//Новые предложения или замечания
//	def getNewSuggestion(Profile profile) {
//		def results = []
//		if (profile.user.role.authority =='ROLE_MANAGER') {
//			def regFilter = []
//			ProfileRegion.findAllByProfile(profile).each {
//				if (it.region.level.type.code == 'CITY') {
//					regFilter.add(it.region)
//				} else {
//					regFilter.addAll(Region.findAllByParent(it.region))
//				}
//			}
//			results = Suggestion.executeQuery("""
//					select suggestion from Suggestion as suggestion
//					 where suggestion.datePublished>:date
//					 and suggestion.author.city in (:reglist) """,
//					[date: profile.dateActivity ?: profile.user.dateCreated, reglist: regFilter]
//			)
//		}
//		else if (profile.user.role.authority == 'ROLE_ADMIN') {
//			results = Suggestion.executeQuery("""
//				select suggestion from Suggestion as suggestion
//				where ((suggestion.datePublished > :date))
//			""",[date: profile.dateActivity ?: profile.user.dateCreated]
//			)
//		}
//		return results
//	}
	//Список новых Зарегистрированных предприятий
	def getNewAccount(Profile profile) {
		def results = []
		if (profile.user.role.authority =='ROLE_MANAGER') {
			def regFilter = []
			ProfileRegion.findAllByProfile(profile).each {
				if (it.region.level.type.code == 'CITY') {
					regFilter.add(it.region)
				} else {
					regFilter.addAll(Region.findAllByParent(it.region))
				}
			}
			results = Account.executeQuery("""
					select account from Account as account
					 where account.dateCreated>:date
					 and account.city in (:reglist) """,
					[date: profile.dateActivity ?: profile.user.dateCreated, reglist: regFilter]
			)
		}
		else if (profile.user.role.authority == 'ROLE_ADMIN') {
			results = Account.executeQuery("""
				select account from Account as account
				where ((account.dateCreated > :date))
			""",[date: profile.dateActivity ?: profile.user.dateCreated]
			)
		}
		return results
	}
  	//Список новых ппродуктов
	def getNewItem(Profile profile) {
		def results = []
		if (profile.user.role.authority =='ROLE_MANAGER') {
			def regFilter = []
			ProfileRegion.findAllByProfile(profile).each {
				if (it.region.level.type.code == 'CITY') {
					regFilter.add(it.region)
				} else {
					regFilter.addAll(Region.findAllByParent(it.region))
				}
			}
			results = Item.executeQuery("""
					select item from Item as item
					 where item.dateCreated>:date
					 and item.account.city in (:reglist) """,
					[date: profile.dateActivity ?: profile.user.dateCreated, reglist: regFilter]
			)
		}
		else if (profile.user.role.authority == 'ROLE_ADMIN') {
			results = Item.executeQuery("""
				select t from Item as t where t.dateCreated>:date

			""",[date: profile.dateActivity ?: profile.user.dateCreated]
			)
		}
		return results
	}


}
