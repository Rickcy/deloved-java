upload {
	deal.directory = 'D:\\idea\\deloved\\upload\\deal'
	avatar.directory = 'D:\\idea\\deloved\\upload\\avatar'
	logo.directory = 'D:\\idea\\deloved\\upload\\logo'
	item.directory = 'D:\\idea\\deloved\\upload\\item'
	review.directory = 'D:\\idea\\deloved\\upload\\review'
	dispute.directory = 'D:\\idea\\deloved\\upload\\dispute'
	claim.directory = 'D:\\idea\\deloved\\upload\\claim'
	adcontent.directory = 'D:\\idea\\deloved\\upload\\adcontent'
	consult.directory = 'D:\\idea\\deloved\\upload\\consult'
	docs.directory = 'D:\\idea\\deloved\\upload\\docs'
}

grails {
	mail {
		host = "smtp.gmail.com"
		username = "user@gmail.com"
		password = "password"
		port = 465
		props = ["mail.smtp.auth"                  : "true",
				 "mail.smtp.socketFactory.port"    : "465",
				 "mail.smtp.socketFactory.class"   : "javax.net.ssl.SSLSocketFactory",
				 "mail.smtp.socketFactory.fallback": "false"]

	}
}

grails.mail.default.from = grails.mail.username
//grails.mail.overrideAddress = "testuser@gmail.com"

//grails.serverURL = "localhost:8080"
//grails.app.context="/deloved-app"
mailBaseURL = 'http://localhost:8080/deloved-app'


//ratingText = [
//		25 : 'TOO_BAD',
//		50 : 'BAD',
//		75 : 'NEUTRAL',
//		90 : 'NORMAL',
//		98 : 'GOOD',
//		101: 'BEST'
//]

organization{
	name ="ООО Портальные системы"
	address = "г.Москва, пер. Свестильников, 35"
	phone="+7-495-123-4567"
	inn="123456789012"
	kpp="123456789"
	bank{
		name="ОТДЕЛЕНИЕ N8644 СБЕРБАНКА РОССИИ"
		bik="040173604"
		korr="30101810200000000604"
		account="40717810000000000123"
	}
}
