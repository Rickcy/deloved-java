package ru.deloved

import grails.transaction.Transactional
import org.joda.time.DateTime
import ru.deloved.billing.*
import utils.numbers.NumberToWordsRussian

@Transactional
class BillingService {
	def regionService

	Keeper createProfileKeeper(Profile profile, SystemCurrency currency) {
		String number = currency.code + sprintf("%07d", profile.id)
		return new Keeper(profile: profile, number: number, currency: currency).save(flush: true)
	}

	Keeper getProfileKeeper(Profile profile, SystemCurrency currency) {
		Keeper k = Keeper.findByProfileAndCurrency(profile, currency)
		if (k == null) {
			k = createProfileKeeper(profile, currency)
		}
		return k;
	}

	Keeper getDefaultKeeper(Profile profile) {
		def countryDefaults = regionService.getCountryDefaults(profile.city)
		return getProfileKeeper(profile, countryDefaults.currency)
	}

	boolean hasKeeper(Profile profile, SystemCurrency currency) {
		return Keeper.countByProfileAndCurrency(profile, currency) > 0
	}

	boolean hasDefaultKeeper(Profile profile) {
		def countryDefaults = regionService.getCountryDefaults(profile.city)
		return hasKeeper(profile, countryDefaults.currency)
	}

	//Перевод средств на счет
	def processIncomeDocs() {
		DocumentIncome.findAllByStatus(IncomeStatus.New.value(), [max: 10, sort: "id", order: "asc", lock: true]).each { DocumentIncome d ->
			log.debug("PROCESS new income document:" + d)
			d.status = IncomeStatus.Process.value()
			d.save(flush: true)
		}
		DocumentIncome.findAllByStatus(IncomeStatus.Process.value(), [max: 10, sort: "id", order: "asc", lock: true]).each { DocumentIncome d ->
			log.debug("PROCESS income document:" + d)
			if (d.value > 0) {
				def op = new Operation(profile: d.profile, keeper: d.keeper, type: DocumentType.Income.value(), value: d.value, document: d.id).save()
				if (op) {
					d.status = IncomeStatus.Success.value()
					d.operation = op
				} else {
					d.status = IncomeStatus.Failed.value()
				}
			} else {
				d.status = IncomeStatus.Failed.value()
			}
			d.save(flush: true)
			/*
			Сразу после поступления средств на счет - списываем их, создавая документ на списание. Поиск тарифа по его стоимости кривой, но что делать.
			 */
			def tp = TariffPrice.findByPrice(d.value)
			def di = new DocumentInvoice(profile: d.profile, keeper: d.keeper, tariffPrice: tp, value: tp.price).save(flush: true)
			log.debug("PROCESS income $d.id result:" + d.status())
		}

	}

	public getNextChargeTill(Profile profile, TariffPrice tp) {
		Date d = profile.chargeTill
		if (d == null) {
			d = new Date()
		}
		DateTime dt = new DateTime(d)
		if (dt.isBeforeNow()) {
			dt = new DateTime()
		}
		log.debug("FROM: " + dt)
		log.debug("TARIFF: " + tp)
		DateTime till = dt.plusDays(tp.days).plusWeeks(tp.weeks).plusMonths(tp.months).plusYears(tp.years)
		log.debug("TILL: " + till)
		return till.toDate()
	}

	//Списание средств со счета
	def processInvoiceDocs() {
		DocumentInvoice.executeQuery("""
			select d
			  from DocumentInvoice as d,
				   Keeper as k
			 where k.profile=d.profile
			   and k = d.keeper
			   and k.balance >= d.value
			   and d.status = :status
			""",
				[status: InvoiceStatus.New.value()],
				[max: 10, lock: true]
		).each { DocumentInvoice d ->
			log.debug("PROCESS new invoice document:" + d)
			d.status = InvoiceStatus.Process.value()
			d.save()
			log.debug("PROCESS new invoice $d.id result:" + d.status)
		}

		DocumentInvoice.executeQuery("""
			select d
			  from DocumentInvoice as d,
				   Keeper as k
			 where k.profile=d.profile
			   and k = d.keeper
			   and k.balance >= d.value
			   and d.status = :status
			""",
				[status: InvoiceStatus.Process.value()],
				[max: 10, lock: true]
		).each { DocumentInvoice d ->
			log.debug("PROCESS invoice document:" + d)
			def op = new Operation(profile: d.profile, keeper: d.keeper, type: DocumentType.Invoice.value(), value: -d.value, document: d.id).save()
			if (op) {
				log.debug("operation:" + op)
				def p = d.profile
				p.chargeStatus = 1
				p.chargeTill = getNextChargeTill(p, d.tariffPrice)
				p.save()
				d.status = InvoiceStatus.Success.value()
				d.operation = op
			} else {
				d.status = InvoiceStatus.Failed.value()
			}
			d.save(flush: true)
			log.debug("PROCESS invoice $d.id result:" + d.status)
		}
	}

	def processPaymentRequests() {
		def paymentRequests = PaymentRequest.executeQuery("""
			select d from PaymentRequest as d
			where d.status = :status
		""", [status: RequestStatus.Executed.value()],[max: 10, lock: true])

		if (paymentRequests) {
			for  (int i = 0; i < paymentRequests.size(); i++) {
				PaymentRequest pr = paymentRequests[i];
				def income = new DocumentIncome(profile: pr.profile, keeper: pr.keeper, value: pr.value, request: pr).save();
				if (income) {
					pr.status = RequestStatus.Success.value();
				} else {
					pr.status = RequestStatus.Failed.value();
				}
				pr.save(flush: true);
			}
		}
	}

	String rublesToWords(BigDecimal bigDecimal) {
		String rubles=' рублей '
		String penny=' копеек'

		String value = bigDecimal.toString()

		int dotIndex = value.indexOf('.')

		if (dotIndex == -1) {
			return 'error'
		}

		String lastCharRub = value.charAt(dotIndex-1)

		if (lastCharRub == '1') {
			rubles=' рубль '
		}

		if (['2', '3', '4'].contains(lastCharRub)) {
			rubles=' рубля '
		}

		String lastCharPenny = value.charAt(dotIndex+2)

		if (lastCharPenny == '1') {
			penny=' копейка'
		}

		if (['2', '3', '4'].contains(lastCharPenny)) {
			penny=' копейки'
		}

		NumberToWordsRussian toWordsRussian = new NumberToWordsRussian()

		Character[] charArray = toWordsRussian.toWords(bigDecimal).toCharArray()

		charArray[0] = charArray[0].toUpperCase()

		String result = new String(charArray) + rubles + value.charAt(dotIndex+1) + value.charAt(dotIndex+2) + penny

		return result
	}

}
