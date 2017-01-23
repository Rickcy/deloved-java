package ru.deloved.admin

import grails.plugin.springsecurity.annotation.Secured
import grails.util.Environment
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import ru.deloved.AccountProfile
import ru.deloved.BigDecimalConverter
import ru.deloved.User
import ru.deloved.billing.*

import static org.springframework.http.HttpStatus.NOT_FOUND

@Secured(["hasAnyRole('ROLE_ACCOUNT') and isFullyAuthenticated()"])
class BillingController {
	def springSecurityService
	def billingService


	String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"

	static allowedMethods = [account: ["GET", "POST"], invoice: "POST", income: "POST"]

	/*def addMethods() {

		//User user = springSecurityService.getCurrentUser()
		//	if (user.role.authority != 'ROLE_ADMIN') {
		//	return
		//}

		PaymentSystem system = PaymentSystem.findByName("PayMaster")
		log.debug(system)
		if (!system) {
			system = new PaymentSystem(name: 'PayMaster')
			system.save(flush: true)
			log.debug(system)
		}

		Map methods = ['Альфа-Банк': 'AlfaBank',
					   'Банк Русский Стандарт': 'RSB',
					   'WebMoney': 'WebMoney',
					   'Контакт': 'Contact',
					   'Евросеть': 'EuroSet',
					   'ПромСвязьБанк': 'PSB',
					   'Связной': 'Svyaznoy',
					   'Банковская карта': 'BankCard',
					   'Сбербанк Онлайн': 'SberbankOnline',
					   'Почта России': 'RussianPost',
					   'PayMaster': '0']

		log.debug(methods)

		methods.each {
			log.debug(it.key + ' ' + it.value)
			if (!PaymentMethod.findByNameAndCode(it.key, it.value)) {
				log.debug('Start create new PaymentMethod: ' + it.key + ' ' + it.value)
				PaymentMethod method = new PaymentMethod(name: it.key,
						code: it.value,
						income: true,
						outcome: false,
						enabled: true,
						currency: null,
						PaymentSystem: system)
				method.save(flush: true)
				if (method) {
					log.debug('PaymentMethod ' + method + ' successfully created!')
				}
			}
		}

		render 'Система оплаты и методы оплаты успешно созданы'
		return

	}*/

	def index() {
		User user = springSecurityService.getCurrentUser()
		boolean hasKeeper = billingService.hasDefaultKeeper(user.profile)

		if(!hasKeeper) {
			def keeper = billingService.getDefaultKeeper(user.profile)
			redirect(base: uri,controller: 'billing', action: 'account')
			return
		}

		def paymentRequests = PaymentRequest.findAllByProfile(user.profile,[
				sort: params.sort ?: 'dateCreated',
				order: params.order ?: 'desc',
				max: 10,
				offset: params.offset ?: '0'
		])

		log.debug("billingController.paymentRequests:" + paymentRequests)

		if (request.xhr) {
			render(template: 'historyGrid', model: [
					requestInstanceList: paymentRequests,
					requestInstanceTotal: PaymentRequest.countByProfile(user.profile),
					params: params])
			return
		}

		Keeper keeper = null
		def tariffs = []
		//def invoicesWait
		//def invoicesNeed
		def methods = null

		keeper = billingService.getDefaultKeeper(user.profile)
		tariffs = TariffPrice.findAllByCurrency(keeper.currency, [sort: 'price'])

		/*invoicesWait = DocumentInvoice.countByProfileAndKeeperAndStatus(user.profile, keeper, InvoiceStatus.New.value())
		log.debug("billingController.account.DocumentInvoices: " + DocumentInvoice.findAllByProfileAndStatus(user.profile, InvoiceStatus.New.value()) + "\n")

		invoicesNeed = DocumentInvoice.executeQuery("""
						select sum(d.value)-k.balance
						 from DocumentInvoice as d,
						       Keeper as k
						 where d.profile = :profile
						   and d.keeper = :keeper
						   and d.status = :status
						   and k = d.keeper
						group by k
						""", [profile: user.profile, keeper: keeper, status: InvoiceStatus.New.value()])[0] */

		methods = PaymentMethod.createCriteria().list() {
			eq("enabled", true)
			eq("income", true)
			or {
				isNull("currency")
				eq("currency", keeper.currency)
			}
		}

		/*log.debug("invoicesWait:" + invoicesWait)
		log.debug("invoicesNeed:" + invoicesNeed)*/


		def basePrice = TariffPrice.findByCurrencyAndMonths(keeper.currency, 1)

		render view: 'index', model: [
				profile        : user.profile,
				keeper         : keeper,
				tariffs        : tariffs,
				methods        : methods,
				basePrice: basePrice,
				requestInstanceList: paymentRequests,
				requestInstanceTotal: PaymentRequest.countByProfile(user.profile)
		]
	}

/*
	def addfunds() {
		User user = springSecurityService.getCurrentUser()
		boolean hasKeeper = billingService.hasDefaultKeeper(user.profile)


		def tariffs
		def methods
		def keeper
		def currency

		if (hasKeeper) {


			keeper = billingService.getDefaultKeeper(user.profile)
			tariffs = TariffPrice.findAllByCurrency(keeper.currency, [sort: 'price'])
			methods = PaymentMethod.createCriteria().list() {

			eq("enabled", true)
			eq("income", true)
			or {
				isNull("currency")
				eq("currency", keeper.currency)
				}
			}

				render view: "_addfunds", model:[
				tariffs: tariffs,
				methods: methods,
				profile: user.profile,
				keeper: keeper

			]
		} else redirect(action: 'index')

	}
*/

	def postRedirect(PaymentRequest request) {

		User user = springSecurityService.getCurrentUser()
		boolean hasKeeper = billingService.hasDefaultKeeper(user.profile)

		if(!hasKeeper) {
			return
		}
		//PaymentRequest request = PaymentRequest.findById(params.id)

		log.debug('billingController.postRedirect.params: ' + params)
		Boolean isDevelopment = false

		TariffPrice tariff = TariffPrice.findById(params.tariff)

		if (Environment.current == Environment.DEVELOPMENT) {
			isDevelopment = true
		}

		render (view: 'postRedirect', model: [
				url: "https://paymaster.ru/Payment/Init",
				merchant_id: "a2498ef4-9f7c-4bc0-ad34-edacc30ffc6b",
				paymentRequest: request,
				tariff: tariff,
				isDevelopment: isDevelopment
		])
	}

/*	def history() {

		log.debug(params)

		User user = springSecurityService.getCurrentUser()
		boolean hasKeeper = billingService.hasDefaultKeeper(user.profile)

		if(!hasKeeper) {
			return
		}

		log.debug('billingController.history.params: ' + params)
		def requests = PaymentRequest.findAllByProfile(user.profile,[
				sort: params.sort ?: 'dateCreated',
				order: params.order ?: 'desc',
				max: 10,
				offset: params.offset ?: '0'
		])

		if (request.xhr) {
			render(template: 'historyGrid', model: [requestInstanceList: requests, requestInstanceTotal: PaymentRequest.countByProfile(user.profile), params: params])
		}

		else {
			render(view: 'history', model: [requestInstanceList: requests, requestInstanceTotal: PaymentRequest.countByProfile(user.profile)])
		}

	}


	def subscription() {
		User user = springSecurityService.getCurrentUser()
		boolean hasKeeper = billingService.hasDefaultKeeper(user.profile)
		def tariffs
		if (hasKeeper) {
			def keeper = billingService.getDefaultKeeper(user.profile)
			tariffs = TariffPrice.findAllByCurrency(keeper.currency, [sort: 'price'])

			def basePrice = TariffPrice.findByCurrencyAndMonths(keeper.currency, 1)

			render (view: 'subscription', model: [tariffs: tariffs, basePrice: basePrice.price])
		}
	}
*/


	def invoice() {
		User user = springSecurityService.getCurrentUser()
		boolean hasKeeper = billingService.hasDefaultKeeper(user.profile)
		def keeper = billingService.getDefaultKeeper(user.profile)

		def tariffPrice = TariffPrice.findById(params.tariffId)
		log.debug('TariffPrice: ' + tariffPrice  + ', КeeperBalance' + keeper.balance)

		if (!tariffPrice) {
			flash.message = "Невернные данные"
			redirect(base: uri,controller: 'billing', action: 'subscription')
			return
		}

		if (keeper.balance < tariffPrice.price) {
			flash.message = "Недостаточно средств. У вас ${keeper.balance}, необходимо ${tariffPrice.price}, не хватает ${tariffPrice.price-keeper.balance}"
			redirect(base: uri,controller: 'billing', action: 'subscription')
			return
		}

		if (user.role.authority == "ROLE_ACCOUNT" && hasKeeper) {

			log.debug("tariff:" + tariffPrice)

			def di = new DocumentInvoice(profile: user.profile, keeper: keeper, tariffPrice: tariffPrice, value: tariffPrice.price).save(flush: true)

			if (di) {
				flash.message = "Принято к исполнению"
			} else {
				flash.message = "Ошибка активации/продления подписки"
			}


			redirect(base: uri,controller: 'billing', action: 'account')
			return

		} else {
			flash.message = "Неверенные данные"
			redirect(base: uri,controller: 'billing', action: 'subscription')
			return
		}

		flash.message = "Неверные данные"
		redirect(base: uri,controller: 'billing', action: 'subscription')
		return
	}

	def income(PaymentMethod method) {
		log.debug('params: ' + params)
		User user = springSecurityService.getCurrentUser()
		boolean hasKeeper = billingService.hasDefaultKeeper(user.profile)


		if (hasKeeper) {
			PaymentRequest r

			Keeper keeper = billingService.getDefaultKeeper(user.profile)

			TariffPrice tp = TariffPrice.findById(params.tariff)
			log.debug('billingController.income.tp: ' + tp)

			BigDecimalConverter bdc = new BigDecimalConverter()
			BigDecimal amount = bdc.canConvert(params.amount) ? bdc.convert(params.amount) : null
			log.debug("amount:" + amount)
			log.debug("method:" + method)
			log.debug("method.id" + method.id)

			/**
			 * Проверяем на пустые значения сумму или метод оплаты, если пустые возвращем обратно на форму с сообщением о неудаче
			 */
			log.debug('PaymentMethod: '+ method.code + '  '+ method.system)

			if (amount == null || amount == 0) {
				flash.message = "Выберите тариф"
				redirect(base: uri,controller: 'billing', action: 'account')
				return
			}

			if (method.id == null) {
				flash.message = "Укажите способ оплаты"
				redirect(base: uri,controller: 'billing', action: 'account')
				return
			}

			/**
			 * Если и сумма и метод оплаты корректны, то создаем запрос о платеже
			 */

			r = new PaymentRequest(profile: user.profile, keeper: keeper, method: method, value: amount).save(flush: true);

			/**
			 * Если запрос не прошел сохранение в базе данных, то возвращем на форму с сообщением о неудаче
			 */

			if (!r) {
				flash.message = "Невернные данные";
				redirect(base: uri,controller: 'billing', action: 'account')
				return
			}

			/**
			 * Если запрос сохранен успешно, то делаем проверку на выбарнный метод оплаты, если это оплата по счету, то
			 * возвращем на страницу управления балансом и сообщением об успешной заявке.
			 */
			if (method.code == 'INCOME_MANUAL') {
				flash.message = "Заявка создана, оплатите и ожидайте подтверждения администрацией портала";
				redirect(base: uri,controller: 'billing', action: 'bill', id: r.id)
				return
			}

			/**
			 * Иначе же перенаправляем на страничку с автосабмитом формы, которая перенаправит на сайт платежной системы
			 */

			redirect(base: uri,controller: 'billing', action: 'postRedirect', id: r.id, params: [tariff: tp.id])

			return

		}

	}

	def bill(PaymentRequest paymentRequest) {
		if (paymentRequest?.id == null || paymentRequest.method.code != 'INCOME_MANUAL') {
			render view: "/notfound", status: NOT_FOUND, model: [message: message(code: 'default.not.found.message', args: [message(code: 'paymentRequest.label', default: 'Payment request'), params.id])]
			return
		}


		def accountProfile = AccountProfile.findByProfile(paymentRequest.profile)

		String propis = billingService.rublesToWords(paymentRequest.value)

		render view: 'bill', model: [
				paymentRequestInstance: paymentRequest,
				org                   : new DefaultGrailsApplication().config.organization,
				propis                : propis,
				account               : accountProfile.account
		]

	}
}


