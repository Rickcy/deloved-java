import groovy.time.TimeCategory
import org.springframework.transaction.annotation.Transactional
import ru.deloved.*
import ru.deloved.recall.*

@Transactional
class ActivityFilters {
	def activityService

	def filters = {
		adminModuleCounterCollect(controller: '(account|adcontent|category|panel|item|measure|profile|region|user|deal|dispute|claim|review|juristConsult|ticket)', action: '(index|me|show|thread|edit)') {
			before = {
				def userName = applicationContext.getBean("springSecurityService").authentication.name
				log.debug("springSecurityService user:" + userName)
				log.debug "adminModuleCounterCollect request " + "'$request.servletPath'/'$request.forwardURI', " + "from $request.remoteHost ($request.remoteAddr) " + " at ${new Date()}, Ajax: $request.xhr, controller: $controllerName, " + "action: $actionName, params: ${new TreeMap(params)}"
				if (!request.xhr) {
					User user = User.findByUsername(userName)
					def userDealNewPosts = [:]
					def userDealNewStatuses = [:]
					def userDisputeNewPosts = [:]
					def userDisputeNewStatuses = [:]
					def userClaimNewPosts = [:]
					def userClaimNewStatuses = [:]
					def userConsultNewPosts = [:]

					def userConsultNewStatuses = [:]
					def userTicketNewPosts = [:]
					def userTicketNewStatuses = [:]
					if (user?.isEnabled()) {
						// новые сделки
						if (user.role.authority in['ROLE_ACCOUNT','ROLE_ADMIN']) {
							activityService.getNewDeals(user.profile).each { user.profile.addToNewDeals(it) }
							// новые ответы в сделках
						}
						if (user.role.authority =='ROLE_ACCOUNT') {
							use(TimeCategory) {
								activityService.getDealNewPostCount(user.profile).each { userDealNewPosts[it.item] = it.cnt }
								activityService.getDealNewStatusCount(user.profile).each { userDealNewStatuses[it.item] = it.cnt }
							}
						}

						// новые споры
						if (user.role.authority in ['ROLE_MEDIATOR','ROLE_ADMIN']) {
							activityService.getNewDisputes(user.profile).each { user.profile.addToNewDisputes(it) }
						}
						if (user.role.authority in ['ROLE_ACCOUNT', 'ROLE_MEDIATOR']) {
							// новые ответы в спорах
							use(TimeCategory) {
								activityService.getDisputeNewPostCount(user.profile).each { userDisputeNewPosts[it.item] = it.cnt }
								activityService.getDisputeNewStatusCount(user.profile).each { userDisputeNewStatuses[it.item] = it.cnt }
							}
						}
						// новые иски
						if (user.role.authority in ['ROLE_JUDGE','ROLE_ADMIN']) {
							activityService.getNewClaims(user.profile).each { user.profile.addToNewClaims(it) }
						}
						if (user.role.authority in ['ROLE_ACCOUNT', 'ROLE_JUDGE']) {
							// новые ответы в исках
							use(TimeCategory) {
								activityService.getClaimNewPostCount(user.profile).each { userClaimNewPosts[it.item] = it.cnt }
								activityService.getClaimNewStatusCount(user.profile).each { userClaimNewStatuses[it.item] = it.cnt }
							}
						}
						// новые отзывы
						if (user.role.authority in ['ROLE_ACCOUNT', 'ROLE_ADMIN', 'ROLE_MANAGER']) {
							activityService.getNewReviews(user.profile).each { user.profile.addToNewReviews(it) }
						}
						// новые товары и услуги
						if (user.role.authority in ['ROLE_MANAGER','ROLE_ADMIN']) {
							activityService.getNewItem(user.profile).each { user.profile.addToNewItem(it) }
						}
						//Новые приедложения или замечания
//						if (user.role.authority in ['ROLE_MANAGER','ROLE_ADMIN']) {
//							activityService.getNewSuggestion(user.profile).each { user.profile.addToNewSuggestion(it) }
//						}
						//Новые преприятия
						if (user.role.authority in ['ROLE_MANAGER','ROLE_ADMIN']) {
							activityService.getNewAccount(user.profile).each { user.profile.addToNewAccount(it) }
						}
						// новые консультации
						if (user.role.authority in ['ROLE_JURIST','ROLE_ADMIN']) {
							activityService.getNewConsults(user.profile).each { user.profile.addToNewJuristConsults(it) }
						}
						// новые ответы в консультации
						if (user.role.authority in ['ROLE_ACCOUNT', 'ROLE_JURIST','ROLE_ADMIN']) {
							use(TimeCategory) {
								activityService.getConsultNewPostCount(user.profile).each { userConsultNewPosts[it.item] = it.cnt }
								activityService.getConsultNewStatusCount(user.profile).each { userConsultNewStatuses[it.item] = it.cnt }
							}
						}
						/*TODO
						ввести роль
						 */
						//новые тикеты
						if (user.role.authority in ['ROLE_MANAGER','ROLE_ADMIN']) {
							activityService.getNewTickets(user.profile).each {user.profile.addToNewTickets(it)}
						}
						//новые ответы на тикеты
						if (user.role.authority in ['ROLE_ACCOUNT', 'ROLE_MANAGER']) {
							use(TimeCategory) {
								activityService.getTicketNewPostCount(user.profile).each { userTicketNewPosts[it.item] = it.cnt }
								activityService.getTicketNewStatusCount(user.profile).each { userTicketNewStatuses[it.item] = it.cnt }
							}
						}

						if (actionName == "index") {
							if(controllerName == "front"){
								request.userNewObjects = user.profile.newDisputes
								request.userNewObjectPosts = userDisputeNewPosts
								request.userNewObjectStatuses = userDisputeNewStatuses
							}

							if (controllerName == "deal") {

								request.userNewObjects = user.profile.newDeals
								request.userNewObjectPosts = userDealNewPosts
								request.userNewObjectStatuses = userDealNewStatuses
							} else if (controllerName == "dispute") {

								request.userNewObjects = user.profile.newDisputes
								request.userNewObjectPosts = userDisputeNewPosts
								request.userNewObjectStatuses = userDisputeNewStatuses
							} else if (controllerName == "claim") {

								request.userNewObjects = user.profile.newClaims
								request.userNewObjectPosts = userClaimNewPosts
								request.userNewObjectStatuses = userClaimNewStatuses
							} else if (controllerName == "review") {

								request.userNewObjects = user.profile.newReviews
							}
							else if (controllerName == "account") {

								request.userNewObjects = user.profile.newAccount
							}

							else if (controllerName == "item") {

								request.userNewObjects = user.profile.newItem
							}

							else if (controllerName == "juristConsult") {

								request.userNewObjects = user.profile.newJuristConsults
								request.userNewObjectPosts = userConsultNewPosts
								request.userNewObjectStatuses = userConsultNewStatuses
							}
							else if (controllerName == "ticket") {

								request.userNewObjects = user.profile.newTickets
								request.userNewObjectPosts = userTicketNewPosts
								request.userNewObjectStatuses = userTicketNewStatuses
							}

						}
						else if (actionName in ['index']) {
							def id = params.id
							if (id) {
								if (controllerName == "review") {
									Suggestion suggestion = Suggestion.get(id)
									if (suggestion) {
										user.profile.newSuggestion.remove(suggestion)

									}
								}
							}
						}
						else if (actionName in ['thread', 'show', 'edit']) {
							def id = params.id
							if (id) {
								if (controllerName == "item") {
									Item item = Item.get(id)
									if (item) {
										user.profile.newItem.remove(item)

									}
								}
								else if (controllerName == "account") {
									Account account = Account.get(id)
									if (account) {
										user.profile.newAccount.remove(account)

									}
								}



								if (controllerName == "deal") {
									Deal deal = Deal.get(id)
									if (deal) {
										user.profile.newDeals.remove(deal)
										def lv = DealLastVisit.findOrCreateByProfileAndDeal(user.profile, deal)
										lv.visited = new Date()
										lv.save(flush: true)
										userDealNewPosts.remove(userDealNewPosts.find { it.key.id == deal.id }?.key)
										userDealNewStatuses.remove(userDealNewStatuses.find { it.key.id == deal.id }?.key)
									}
								} else if (controllerName == "dispute") {
									Dispute dispute = Dispute.get(id)
									if (dispute) {
										user.profile.newDisputes.remove(dispute)
										def lv = DisputeLastVisit.findOrCreateByProfileAndDispute(user.profile, dispute)
										lv.visited = new Date()
										lv.save(flush: true)
										userDisputeNewPosts.remove(userDisputeNewPosts.find { it.key.id == dispute.id }?.key)
										userDisputeNewStatuses.remove(userDisputeNewStatuses.find { it.key.id == dispute.id }?.key)
									}
								} else if (controllerName == "claim") {
									Claim claim = Claim.get(id)
									if (claim) {
										user.profile.newClaims.remove(claim)
										def lv = ClaimLastVisit.findOrCreateByProfileAndClaim(user.profile, claim)
										lv.visited = new Date()
										lv.save(flush: true)
										userClaimNewPosts.remove(userClaimNewPosts.find { it.key.id == claim.id }?.key)
										userClaimNewStatuses.remove(userClaimNewStatuses.find { it.key.id == claim.id }?.key)
									}
								} else if (controllerName == "review") {
									Review review = Review.get(id)
									if (review) {
										user.profile.newReviews.remove(review)
									}
								} else if (controllerName == "juristConsult") {
									JuristConsult juristConsult = JuristConsult.get(id)
									if (juristConsult) {
										user.profile.newJuristConsults.remove(juristConsult)
										def lv = JuristConsultLastVisit.findOrCreateByProfileAndJuristConsult(user.profile, juristConsult)
										lv.visited = new Date()
										lv.save(flush: true)
										userConsultNewPosts.remove(userConsultNewPosts.find { it.key.id == juristConsult.id }?.key)
										userConsultNewStatuses.remove(userConsultNewStatuses.find { it.key.id == juristConsult.id }?.key)
									}
								} else if (controllerName == "ticket") {
									Ticket ticket = Ticket.get(id)
									if (ticket) {
										user.profile.newTickets.remove(ticket)
										def lv = TicketLastVisit.findOrCreateByProfileAndTicket(user.profile, ticket)
										lv.visited = new Date()
										lv.save(flush: true)
										userTicketNewPosts.remove(userTicketNewPosts.find { it.key.id == ticket.id }?.key)
										userTicketNewStatuses.remove(userTicketNewStatuses.find { it.key.id == ticket.id }?.key)
									}
								}
							}
						}
					}
					log.debug("user.profile.dateActivity:" + user.profile.dateActivity)
					user.profile.dateActivity = new Date()
					log.debug("user.profile.dateActivity:" + user.profile.dateActivity)
					request.userNewObjectsCount = [
							deals   : new HashSet(user.profile.newDeals + userDealNewPosts.keySet() + userDealNewStatuses.keySet()).size(),
							disputes: new HashSet(user.profile.newDisputes + userDisputeNewPosts.keySet() + userDisputeNewStatuses.keySet()).size(),
							claims  : new HashSet(user.profile.newClaims + userClaimNewPosts.keySet() + userClaimNewStatuses.keySet()).size(),
							reviews : user.profile.newReviews.size(),
							consults: new HashSet(user.profile.newJuristConsults + userConsultNewPosts.keySet() + userConsultNewStatuses.keySet()).size(),
							tickets: new HashSet(user.profile.newTickets + userTicketNewPosts.keySet() + userTicketNewStatuses.keySet()).size(),
							item:user.profile.newItem.size(),
							account:user.profile.newAccount.size(),
							//suggestion:user.profile.newSuggestion.size(),
					]
					log.debug(request.userNewObjectsCount)
					user.profile.save(flush: true)
				}
				return true
			}
		}
	}
}
