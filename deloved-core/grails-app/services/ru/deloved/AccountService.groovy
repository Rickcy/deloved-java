package ru.deloved

import grails.gorm.DetachedCriteria
import grails.transaction.Transactional
import groovy.sql.Sql


@Transactional
class AccountService {
	def springSecurityService


	def renameProfileFio(Account account) {
		Profile profile = AccountProfile.findByAccount(account)?.profile
		log.debug('accountService.renameProfileFio: ' + profile)
		if (profile) {
			profile.lock()
			profile.fio = account?.manager
			profile.save(flush: true)
			return
		}
		return
	}

	void deactivateAccount(String inn) {

		def account = Account.findByInn(inn)

		if (!account) {
			return
		}

		def accountProfile = AccountProfile.findByAccount(account)

		if (accountProfile?.profile == null) {
			account.lock()
			account.inn = 1111111111
			account.showMain = false
			account.verifyStatus = false
			account.publicStatus = false
			account.save(flush: true)
			return
		}

		return

	}

	boolean isInnAvailable(String inn) {
		log.debug "Check for new INN '$inn'"
		def c = new DetachedCriteria(Account).build {
			eq 'inn', inn
		}
		log.debug "count:" + c.count()
		return c.count() == 0
	}

	boolean isInnAvailable(String inn, long id) {
		log.debug "Check for new INN '$inn' with id=$id"
		return Account.findByInnAndIdNotEqual(inn, id) == null
	}

	boolean isMyAccount(Account account) {
		User user = springSecurityService.getCurrentUser()
		if (user.role.authority in ['ROLE_ACCOUNT','ROLE_JUDGE','ROLE_MEDIATOR','ROLE_JURIST']) {
			return AccountProfile.countByProfileAndAccount(user.profile, account) > 0
		}
		return false
	}

	List<Account> getMyAccounts() {
		User user = springSecurityService.getCurrentUser()
		def list = []
		if (user.role.authority in ['ROLE_ACCOUNT','ROLE_JUDGE','ROLE_MEDIATOR','ROLE_JURIST']) {
			AccountProfile.findAllByProfile(user.profile).each {
				list.add(it.account)
			}
		}
		return list
	}


	void updateAccoutRating(Account account) {



		/*def list = Deal.executeQuery("""

		select d.account
		from Deal d
		where d.account = ?


		""", [account])

		/*int uniqueCountrAgents

		List<Long> countrAgents

		Deal.findAllByAccountOrPartner(account, account).each {
		/*	if (it.account == account) {
				countrAgents.add(it.partner.id)
			} else {
				countrAgents.add(it.account.id)
			} */

		//	(it.account == account) ? countrAgents.add(it.partner.id) : countrAgents.add(it.account.id)

		//}

		/*Deal.findAllByPartner(account).each {
			countrAgents.add(it.account.id)
		} */

		//uniqueCountrAgents = countrAgents.unique().size()


		def c = AccountDealRating.createCriteria()
		def res = c.list {
			projections {
				sqlProjection 'sum(enable) as total, sum(bad_Review) as badReview, sum(bad_Dispute) as badDispute, sum(bad_Claim) as badClaim', ['total', 'badReview', 'badDispute', 'badClaim'], [INTEGER, INTEGER, INTEGER, INTEGER]
			}
			eq('account', account)
			eq('enable', 1)
		}
		int total = 0
		int badReview = 0
		int badDispute = 0
		int badClaim = 0
		res.each {
			log.debug("it:" + it)
			total = it[0] ?: 0
			badReview = it[1] ?: 0
			badDispute = it[2] ?: 0
			badClaim = it[3] ?: 0
		}
		log.debug("total:" + total)
		log.debug("badReview:" + badReview)
		log.debug("badDispute:" + badDispute)
		log.debug("badClaim:" + badClaim)
		int rating = 0
		if (total > 0) {
			rating = (total - (badReview * 0.1) - (badDispute * 0.2) - (badClaim * 0.7)) * 100 / total
			account.rating = rating

		} else {
			account.rating = null
		}
		account.save(flush: true)
	}

	void updateStatViewAccount(Account account) {
		def stat = AccountStat.findOrCreateByAccount(account)
		stat.totalViewAccount++
		stat.monthViewAccount++
		stat.save()
	}

	void updateStatViewGoods(Account account) {
		def stat = AccountStat.findOrCreateByAccount(account)
		stat.totalViewGoods++
		stat.monthViewGoods++
		stat.save()
	}

	void updateStatViewServices(Account account) {
		def stat = AccountStat.findOrCreateByAccount(account)
		stat.totalViewServices++
		stat.monthViewServices++
		stat.save()
	}

	void resetMonthStat() {
		log.info('Reset month account statistics')
		AccountStat.executeUpdate("update AccountStat s set s.monthViewAccount=0, s.monthViewGoods=0, s.monthViewServices=0")
	}



}
