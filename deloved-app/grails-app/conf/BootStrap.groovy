import grails.util.Environment
import ru.deloved.*
import ru.deloved.billing.PaymentMethod
import ru.deloved.billing.PaymentSystem
import ru.deloved.billing.TariffPrice

class BootStrap {
	def grailsApplication
	def regionService
	def categoryService
	def billingService


	def createMeasures(Category categoryRoot) {
		log.trace("Create Measures type " + categoryRoot.type.code)
		def filePath = "resources/measure_${categoryRoot.type.code}.csv"
		def text = grailsApplication.getParentContext().getResource("classpath:$filePath").getInputStream().getText('UTF-8')
		text.splitEachLine('\t') { fields ->
			new Measure(name: fields[0], fullname: fields[1], type: categoryRoot.type).save(flush: true)
		}
		log.trace("Create Measures done.")

	}

	def createCategories(Category categoryRoot) {
		log.trace("Create Categories type " + categoryRoot.type.code)
		def filePath = "resources/cat_${categoryRoot.type.code}.csv"
		def text = grailsApplication.getParentContext().getResource("classpath:$filePath").getInputStream().getText('UTF-8')
		int cur = 0;
		Category[] levels = new ArrayList[20]
		levels[0] = categoryRoot
		text.splitEachLine('\t') { fields ->
			int s = -1
			for (int i = 0; i < fields.size(); i++) {
				if (!fields[i].isEmpty()) {
					s = i
					break
				}
			}
			if (s != -1) {
				if (s < cur) {
					for (i in s + 1..cur) {
						levels[i] = null
					}
				}
				if (levels[s] != null) {
					Category cat = new Category(name: fields[s], parent: levels[s]).save(flush: true)
					levels[s + 1] = cat
					cur = s + 1;
				}
			}
		}
		log.trace("Create Categories done")
	}

	def createOrgForms() {
		log.trace("Create OrgForms")
		def filePath = "resources/org_forms.csv"
		def text = grailsApplication.getParentContext().getResource("classpath:$filePath").getInputStream().getText('UTF-8')
		text.splitEachLine('\t') { fields ->
			new OrgForm(code: fields[0], name: fields[1]).save(flush: true)
		}
		log.trace("Create OrgForms done.")
	}

	def createRussiaRegions() {
		log.trace("Create regions")
		def typeCountry = new RegionType(code: 'COUNTRY').save(flush: true)
		def typeRegion = new RegionType(code: 'REGION').save(flush: true)
		def typeCity = new RegionType(code: 'CITY').save(flush: true)

		def levelRussia1 = new RegionLevel(level: 1, type: typeCountry, parent: null).save(flush: true)
		def levelRussia2 = new RegionLevel(level: 2, type: typeRegion, parent: levelRussia1).save(flush: true)
		def levelRussia3 = new RegionLevel(level: 3, type: typeCity, parent: levelRussia2).save(flush: true)

		def regionRussia = new Region(name: 'Россия', fullName: 'Российская Федерация', parent: null, level: levelRussia1).save(flush: true)

		def russiaDefaults = new CountryDefaults(country: regionRussia, currency: SystemCurrency.findByCode('RUB')).save(flush: true)

		def fileRegionsPath = "resources/regions_ru.csv"
		def fileCitiesPath = "resources/cities_ru.csv"
		def textRegion = grailsApplication.getParentContext().getResource("classpath:$fileRegionsPath").getInputStream().getText('UTF-8')

		textRegion.splitEachLine('\t') { fields ->
			def reg = new Region(name: fields[0], fullName: fields[1], parent: regionRussia)
			reg.save(flush: true)
			if (reg.hasErrors() || reg.save(flush: true) == null) {
				log.error("Could not import Region  ${reg.errors}")
			}
		}

		def textCity = grailsApplication.getParentContext().getResource("classpath:$fileCitiesPath").getInputStream().getText('UTF-8')

		textCity.splitEachLine('\t') { fields ->
			def p = getParent(fields[1])
			def reg = new Region(name: fields[0], parent: p)
			reg.save(flush: true)
			if (reg.hasErrors() || reg.save(flush: true) == null) {
				log.error("Could not import Region  ${reg.errors}")
			}
		}
		log.trace("Create regions done.")
	}

	def createCurrencies() {
		log.trace("Create currencies")
		new SystemCurrency(name: "Рубль", code: "RUB", digit3: "643").save(flush: true)
		new SystemCurrency(name: "Доллар", code: "USD", digit3: "840").save(flush: true)
		new SystemCurrency(name: "Евро", code: "EUR", digit3: "978").save(flush: true)
	}

	def createTariffs() {
		log.trace("Create tariffs")
		def rub = SystemCurrency.findByCode('RUB')
		def usd = SystemCurrency.findByCode('USD')
		new TariffPrice(name: "1 месяц", months: 1, price: 500, currency: rub).save(flush: true)
		new TariffPrice(name: "3 месяца", months: 3, price: 1500, currency: rub).save(flush: true)
		new TariffPrice(name: "6 месяцев", months: 6, price: 3000, currency: rub).save(flush: true)
		new TariffPrice(name: "12 месяцев", months: 12, price: 6000, currency: rub).save(flush: true)
		new TariffPrice(name: "1 месяц", months: 1, price: 10, currency: usd).save(flush: true)
		new TariffPrice(name: "3 месяца", months: 3, price: 30, currency: usd).save(flush: true)
		new TariffPrice(name: "6 месяцев", months: 6, price: 60, currency: usd).save(flush: true)
		new TariffPrice(name: "12 месяцев", months: 12, price: 120, currency: usd).save(flush: true)
	}

	def createPaymentSystem() {
		log.trace("Create internal payment system")
		def ps = new PaymentSystem(name: "Служебная").save(flush: true)
		new PaymentMethod(name: "Оплата по счету", code: "INCOME_MANUAL", income: true, outcome: false, enabled: true, currency: null, system: ps).save(flush: true)


		def paymaster = new PaymentSystem(name: "PayMaster").save(flush: true)
		new PaymentMethod(name: "Оплата через PayMaster", code: "0", income: true, outcome: false, enabled: true, currency: null, system: paymaster).save(flush: true)
	}

	def getParent(String name) {
		Region.withCriteria(uniqueResult: true) {
			or {
				ilike('name', name.replaceAll('[-—]', '%'))
				ilike('fullName', name.replaceAll('[-—]', '%'))
			}
			eq 'level', RegionLevel.findByType(RegionType.findByCode('REGION'))
		}
	}

	def init = { servletContext ->
		int userCount = User.count()
		if (userCount == 0) {
			log.trace("Create roles")
			def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
			def managerRole = new Role(authority: 'ROLE_MANAGER').save(flush: true)
			def accountRole = new Role(authority: 'ROLE_ACCOUNT').save(flush: true)
			def mediatorRole = new Role(authority: 'ROLE_MEDIATOR').save(flush: true)
			def judgeRole = new Role(authority: 'ROLE_JUDGE').save(flush: true)
			def juristRole = new Role(authority: 'ROLE_JURIST').save(flush: true)
			def noneRole = new Role(authority: 'ROLE_NONE').save(flush: true)
			log.trace("Create ADMIN user")
			def adminUser = new User(username: 'admin', enabled: true, activated: true, password: '11112222', profile: new Profile(), role: adminRole).save(flush: true)

			createCurrencies()
			createRussiaRegions()
			createOrgForms()
			createTariffs()
			createPaymentSystem()


			log.trace("Create category type GOOD and root element")
			def typeGood = new CategoryType(code: 'GOOD').save(flush: true)
			def catGoods = new Category(name: "Категории товаров", type: typeGood, parent: null).save(flush: true)
			createCategories(catGoods)

			log.trace("Create category type SERVICE and root element")
			def typeService = new CategoryType(code: 'SERVICE').save(flush: true)
			def catServices = new Category(name: "Категории услуг", type: typeService, parent: null).save(flush: true)
			createCategories(catServices)

			log.trace("Create measure list")
			createMeasures(catGoods)
			createMeasures(catServices)
		}
		log.trace("======== INIT Regions list =========")
		regionService.init()
		log.trace("======== FINISH Regions list =========")
		log.trace("======== INIT Categories list =========")
		categoryService.init()
		log.trace("======== FINISH Categories list =========")


		Environment.executeForCurrentEnvironment {
			development {
				if (userCount == 0) {

					def cityList = Region.findAllByLevel(RegionLevel.findByType(RegionType.findByCode('CITY')))

					log.trace("Create test users")
					def managerRole = Role.findByAuthority('ROLE_MANAGER')
					def accountRole = Role.findByAuthority('ROLE_ACCOUNT')
					def mediatorRole = Role.findByAuthority('ROLE_MEDIATOR')
					def judgeRole = Role.findByAuthority('ROLE_JUDGE')
					def juristRole = Role.findByAuthority('ROLE_JURIST')
					def noneRole = Role.findByAuthority('ROLE_NONE')
					def rlist = [managerRole, accountRole, mediatorRole, judgeRole, juristRole, noneRole]
					def nlist = ['manager', 'account', 'mediator', 'judge', 'jurist', 'none']
					for (int i = 0; i < rlist.size(); i++) {
						for (y in 1..3) {

							User u = new User(username: nlist[i] + y,
									enabled: true,
									activated: true,
									password: '11112222',
									profile: new Profile(fio: nlist[i].capitalize() + ' ' + y, city: (rlist[i] in [accountRole, mediatorRole, judgeRole, juristRole]) ? cityList[y * 10] : null),
									role: rlist[i]
							)
							if (!u.save(flush: true)) {
								log.error(u.getErrors())
							}
						}
					}

					def man1 = User.findByUsername('manager1')
					def man1pr = Profile.findByUser(man1)
					new ProfileRegion(profile: man1pr, region: Region.findByName('Алтайский край')).save(flush: true)


					Calendar c = Calendar.getInstance();
					c.add(Calendar.WEEK_OF_YEAR, 1);
					log.trace("Create test account")
					def acc1 = new Account(
							name: "ООО Название",
							fullName: "Общество с ограниченной ответственностью Название",
							brandName: "The Name",
							chargeStatus: 1, chargeTill: c.getTime(),
							publicStatus: 1,
							city: Region.findByName('Барнаул'),
							inn: "123456789012",
							kpp: "111333345",
							legalAddress: "ул. Улица, д.10",
							manager: "Директор",
							orgForm: OrgForm.findByCode('ООО'),
							phone1: "+7-913-213-1122",
							regDate: new Date(),
							regNumber: "1232234234233",
							email: "mail1@mail.ru",
							workTime: "9:00 - 21:00"
					)
					acc1.save(flush: true)

					log.trace("Create test account 2")
					def acc2 = new Account(
							name: "ООО Название 2",
							fullName: "Общество с ограниченной ответственностью Название 2",
							brandName: "The Name Two",
							chargeStatus: 0,
							publicStatus: 1,
							city: Region.findByName('Барнаул'),
							inn: "123456789013",
							kpp: "111333345",
							legalAddress: "ул. Улица, д.12",
							manager: "Директор 2",
							orgForm: OrgForm.findByCode('ООО'),
							phone1: "+7-913-213-1133",
							regDate: new Date(),
							regNumber: "1232234234234",
							email: "mail2@mail.ru",
							workTime: "9:00 - 21:00"
					)
					acc2.save(flush: true)

					log.trace("Create test account 3")
					def acc3 = new Account(
							name: "ООО Название 3",
							fullName: "Общество с ограниченной ответственностью Название 3",
							brandName: "The Name Three",
							chargeStatus: 0,
							publicStatus: 1,
							city: Region.findByName('Барнаул'),
							inn: "123456789014",
							kpp: "111333345",
							legalAddress: "ул. Улица, д.14",
							manager: "Директор 3",
							orgForm: OrgForm.findByCode('ООО'),
							phone1: "+7-913-213-1144",
							regDate: new Date(),
							regNumber: "1232234234234",
							email: "mail3@mail.ru",
							workTime: "9:00 - 21:00"
					)
					acc3.save(flush: true)

					new AccountProfile(account: acc1, profile: User.findByUsername('account1').profile).save(flush: true)
					new AccountProfile(account: acc2, profile: User.findByUsername('account2').profile).save(flush: true)
					new AccountProfile(account: acc3, profile: User.findByUsername('account3').profile).save(flush: true)

					def catAuto = Category.findByName("Авто")
					new AccountCategory(account: acc1, category: catAuto).save(flush: true)
					new AccountCategory(account: acc2, category: catAuto).save(flush: true)
					new AccountCategory(account: acc3, category: catAuto).save(flush: true)

					log.trace("Create test account done")


					new Content(name: 'Test content', code: 'test_content', content: '<b>Test</b> content!').save(flush: true)
				}
			}
		}
	}
	def destroy = {
	}
}
