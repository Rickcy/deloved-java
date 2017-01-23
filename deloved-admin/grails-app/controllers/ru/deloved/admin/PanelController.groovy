package ru.deloved.admin

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.time.TimeCategory
import ru.deloved.*
import ru.deloved.recall.Ticket

@Transactional(readOnly = true)
@Secured(["isFullyAuthenticated()"])
class PanelController {
	def springSecurityService
	def accountService
	def activityService


	def getAccount(){
		User user = springSecurityService.getCurrentUser()
		def lenta = []
		def newAcc=[]
		def newDeal=[]
		def newDisp =[]
		def newClaim =[]
		def newReview =[]
		def newJurist =[]
		def newTicket =[]
		def newItem =[]
		def newDealPost=[]
		def newDealStatusPost=[]
		def newClaimPost=[]
		def newClaimStatusPost=[]
		def newDisputePost=[]
		def newDisputeStatusPost=[]
		def newJuristPost=[]
		def newJuristStatusPost=[]
		def newTicketPost=[]
		def newTicketStatusPost=[]
		newAcc.addAll(user.profile.newAccount)
		newDisp.addAll(user.profile.newDisputes)
		newClaim.addAll(user.profile.newClaims)
		newReview.addAll(user.profile.newReviews)
		newJurist.addAll(user.profile.newJuristConsults)
		newTicket.addAll(user.profile.newTickets)
		newItem.addAll(user.profile.newItem)
		newDeal.addAll(user.profile.newDeals)


		use(TimeCategory) {

			activityService.getNewReviews(user.profile).each {
				newReview.add([dateCreated: it.dateCreated, class: [simpleName: 'newReview']]) }

			activityService.getNewClaims(user.profile).each {
				newClaim.add([dateCreated: it.dateCreated, class: [simpleName: 'newClaim']]) }

			activityService.getNewConsults(user.profile).each {
				newJurist.add([dateCreated: it.dateCreated, class: [simpleName: 'newJurist']]) }

			activityService.getNewDeals(user.profile).each {
				newDeal.add([dateCreated: it.dateCreated, class: [simpleName: 'newDeal']]) }
//
 				activityService.getNewDisputes(user.profile).each {
					newDisp.add([dateCreated: it.dateCreated, class: [simpleName: 'newDisp']]) }

 				activityService.getNewTickets(user.profile).each {
				newTicket.add([dateCreated: it.dateCreated,  class: [simpleName: 'newTicket']]) }
//
//
			activityService.getNewAccount(user.profile).each {
				newAcc.add([dateCreated: it.dateCreated, class: [simpleName: 'newAcc']]) }
//
			activityService.getNewItem(user.profile).each {
				newItem.add([dateCreated: it.dateCreated,  class: [simpleName: 'NewItem']]) }

			activityService.getDealNewPostCount(user.profile).each {
				newDealPost.add([dateCreated: it.dateCreated,  count: it.cnt, class: [simpleName: 'DealNewPost']]) }

			activityService.getDealNewStatusCount(user.profile).each {
				newDealStatusPost.add([dateCreated: it.dateCreated,  count: it.cnt, class: [simpleName: 'DealNewStatus']])
			}

			activityService.getDisputeNewPostCount(user.profile).each {
				newDisputePost.add([dateCreated: it.dateCreated,  count: it.cnt, class: [simpleName: 'DisputeNewPost']])
			}
			activityService.getDisputeNewStatusCount(user.profile).each {
				newDisputeStatusPost.add([dateCreated: it.dateCreated,  count: it.cnt, class: [simpleName: 'DisputeNewStatus']])
			}

			activityService.getClaimNewPostCount(user.profile).each {
				newClaimPost.add([dateCreated: it.dateCreated,  count: it.cnt, class: [simpleName: 'ClaimNewPost']]) }

			activityService.getClaimNewStatusCount(user.profile).each {
				newClaimStatusPost.add([dateCreated: it.dateCreated,  count: it.cnt, class: [simpleName: 'ClaimNewStatus']])
			}

			activityService.getConsultNewPostCount(user.profile).each {
				newJuristPost.add([dateCreated: it.dateCreated,  count: it.cnt, class: [simpleName: 'ConsultNewPost']])
			}
			activityService.getConsultNewStatusCount(user.profile).each {
				newJuristStatusPost.add([dateCreated: it.dateCreated,  count: it.cnt, class: [simpleName: 'ConsultNewStatus']])
			}
			activityService.getTicketNewPostCount(user.profile).each {
				newTicketPost.add([dateCreated: it.dateCreated,  count: it.cnt, class: [simpleName: 'TicketNewPost']])
			}
			activityService.getTicketNewStatusCount(user.profile).each {
				newTicketStatusPost.add([dateCreated: it.dateCreated,  count: it.cnt, class: [simpleName: 'TicketNewStatus']])
			}


		}
		lenta = [newAcc:newAcc,newDisp:newDisp,newClaim:newClaim,newReview:newReview,newJurist:newJurist,newTicket:newTicket,newItem:newItem,newDeal:newDeal,newDealStatusPost:newDealStatusPost,newDealPost:newDealPost,newClaimPost:newClaimPost,newClaimStatusPost:newClaimStatusPost,newDisputePost:newDisputePost,newDisputeStatusPost:newDisputeStatusPost,newTicketPost:newTicketPost,newTicketStatusPost:newTicketStatusPost,newJuristPost:newJuristPost,newJuristStatusPost:newJuristStatusPost]
		render lenta as JSON
	}

	def index() {
		User user = springSecurityService.getCurrentUser()





		def lenta = []

		lenta.addAll(user.profile.newDeals)
		lenta.addAll(user.profile.newDisputes)
		lenta.addAll(user.profile.newClaims)
		lenta.addAll(user.profile.newReviews)
		lenta.addAll(user.profile.newJuristConsults)
		lenta.addAll(user.profile.newTickets)
		lenta.addAll(user.profile.newAccount)
		lenta.addAll(user.profile.newItem)

		use(TimeCategory) {
//			activityService.getNewSuggestion(user.profile).each {
//				lenta.add([dateCreated: it.datePublished, item: it.item, count: it.cnt, class: [simpleName: 'NewSuggestion']]) }


			activityService.getNewAccount(user.profile).each {
				lenta.add([dateCreated: it.dateCreated, item: it.item, count: it.cnt, class: [simpleName: 'NewAccount']]) }

			activityService.getNewItem(user.profile).each {
				lenta.add([dateCreated: it.dateCreated, item: it.item, count: it.cnt, class: [simpleName: 'NewItem']]) }

			activityService.getDealNewPostCount(user.profile).each { lenta.add([dateCreated: it.dateCreated, item: it.item, count: it.cnt, class: [simpleName: 'DealNewPost']]) }

			activityService.getDealNewStatusCount(user.profile).each {
				lenta.add([dateCreated: it.dateCreated, item: it.item, count: it.cnt, class: [simpleName: 'DealNewStatus']])
			}

			activityService.getDisputeNewPostCount(user.profile).each {
				lenta.add([dateCreated: it.dateCreated, item: it.item, count: it.cnt, class: [simpleName: 'DisputeNewPost']])
			}
			activityService.getDisputeNewStatusCount(user.profile).each {
				lenta.add([dateCreated: it.dateCreated, item: it.item, count: it.cnt, class: [simpleName: 'DisputeNewStatus']])
			}

			activityService.getClaimNewPostCount(user.profile).each { lenta.add([dateCreated: it.dateCreated, item: it.item, count: it.cnt, class: [simpleName: 'ClaimNewPost']]) }

			activityService.getClaimNewStatusCount(user.profile).each {
				lenta.add([dateCreated: it.dateCreated, item: it.item, count: it.cnt, class: [simpleName: 'ClaimNewStatus']])
			}

			activityService.getConsultNewPostCount(user.profile).each {
				lenta.add([dateCreated: it.dateCreated, item: it.item, count: it.cnt, class: [simpleName: 'ConsultNewPost']])
			}
			activityService.getConsultNewStatusCount(user.profile).each {
				lenta.add([dateCreated: it.dateCreated, item: it.item, count: it.cnt, class: [simpleName: 'ConsultNewStatus']])
			}
			activityService.getTicketNewPostCount(user.profile).each {
				lenta.add([dateCreated: it.dateCreated, item: it.item, count: it.cnt, class: [simpleName: 'TicketNewPost']])
			}
			activityService.getTicketNewStatusCount(user.profile).each {
				lenta.add([dateCreated: it.dateCreated, item: it.item, count: it.cnt, class: [simpleName: 'TicketNewStatus']])
			}

		}


		lenta = lenta.sort { a, b -> a.dateCreated <=> b.dateCreated }
		lenta = lenta.reverse()

		List myAccounts = accountService.getMyAccounts()
		Account defaultAccount = null;
		def defaultAccountStat = null
		if (!myAccounts.isEmpty()) {
			defaultAccount = myAccounts.get(0)

			int reviewsPositiveCount = Review.countByToAndValueGreaterThanAndPublishedAndFinished(defaultAccount, 0, true, true)
			int reviewsNegativeCount = Review.countByToAndValueLessThanAndPublishedAndFinished(defaultAccount, 0, true, true)

			int goodsCount = Item.countByAccountAndCategoryType(defaultAccount, CategoryType.findByCode('GOOD'))
			int servicesCount = Item.countByAccountAndCategoryType(defaultAccount, CategoryType.findByCode('SERVICE'))

			int dealsFinishedCount = Deal.createCriteria().get {
				inList('status', [DealStatus.Confirmed.value(), DealStatus.Failed.value()])
				or {
					eq('account', defaultAccount)
					eq('partner', defaultAccount)
				}
				projections {
					count()
				}
			}
			int dealsInProcessCount = Deal.createCriteria().get {
				not {
					inList('status', [DealStatus.Confirmed.value(), DealStatus.Failed.value()])
				}
				or {
					eq('account', defaultAccount)
					eq('partner', defaultAccount)
				}
				projections {
					count()
				}
			}
			int disputesInProcessCount = Dispute.createCriteria().get {
				inList('status', [DisputeStatus.New.value(), DisputeStatus.Processing.value()])
				or {
					eq('account', defaultAccount)
					eq('partner', defaultAccount)
				}
				projections {
					count()
				}
			}
			int disputesFailed = Dispute.countByFailedBy(defaultAccount)
			int claimsInProcessCount = Claim.createCriteria().get {
				inList('status', [ClaimStatus.New.value(), ClaimStatus.Processing.value()])
				or {
					eq('account', defaultAccount)
					eq('partner', defaultAccount)
				}
				projections {
					count()
				}
			}
			int claimsFailed = Claim.countByFailedBy(defaultAccount)


			def stat = AccountStat.findOrCreateByAccount(defaultAccount)


			defaultAccountStat = [
					reviewsPositiveCount  : reviewsPositiveCount,
					reviewsNegativeCount  : reviewsNegativeCount,
					goodsCount            : goodsCount,
					servicesCount         : servicesCount,
					dealsFinishedCount    : dealsFinishedCount,
					dealsInProcessCount   : dealsInProcessCount,
					disputesInProcessCount: disputesInProcessCount,
					disputesFailed        : disputesFailed,
					claimsInProcessCount  : claimsInProcessCount,
					claimsFailed          : claimsFailed,

					viewStat              : stat
			]


		}

		render view: "index", model: [
				profile           : user.profile,
				lenta             : lenta,
				accounts          : myAccounts,
				defaultAccount    : defaultAccount,
				defaultAccountStat: defaultAccountStat
		]
	}
}
