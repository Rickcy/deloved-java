package ru.deloved

import org.joda.time.DateTime
import ru.deloved.recall.JuristConsult
import ru.deloved.recall.Suggestion
import ru.deloved.recall.Ticket

class Profile {

	def userService

	String fio
	String email
	String cellPhone
	String dayOfWork
	Region city
	Attachment avatar
	Attachment avatarThumb
	Date dateCreated
	Date dateActivity

	int chargeStatus=1;
	Date chargeTill =DateTime.now().plusDays(31).toDate()

	static belongsTo = [user: User]
	static hasMany = [
			//newSuggestion:Suggestion,
			newAccount:Account,
			newItem : Item,
			newDeals   : Deal,
			newDisputes: Dispute,
			newClaims  : Claim,
			newReviews : Review,
			newJuristConsults: JuristConsult,
			newTickets: Ticket
	]
	static mappedBy = [
			//newSuggestion: 'newProfiles',
			newAccount: 'newProfiles',
			newItem: 	'newProfiles',
			newDeals   : 'newProfiles',
			newDisputes: 'newProfiles',
			newClaims  : 'newProfiles',
			newReviews : 'newProfiles',
			newJuristConsults: 'newProfiles',
			newTickets: 'newProfiles'
	]

	static constraints = {
		user(nullable: false)
		fio(nullable: true, size: 5..50, maxSize: 50)
		email(nullable: true, size: 5..50, maxSize: 50, email: true)
		cellPhone(nullable: true, maxSize: 20)
		dayOfWork(nullable: true,maxSize: 20)
		city(nullable: true,
				validator: { val, obj ->
					if (obj.userService.isAccountOrSpecialist(obj.user)) {
						return val != null
					}
					return true
				}
		)
		dateActivity(nullable: true)
		avatar(nullable: true)
		avatarThumb(nullable: true)
		chargeTill nullable: true, validator: { val, obj ->
			if (obj.chargeStatus > 0) {
				return val != null
			} else {
				return true;
			}
		}

	}

	static mapping = {
		user(fetch: 'join')
		avatar(fetch: 'join')
		avatarThumb(fetch: 'join')
		newDeals(lazy: true)
		newDisputes(lazy: true)
		newClaims(lazy: true)
		newReviews(lazy: true)
		newJuristConsults(lazy: true)
		newItem(lazy:true)
		newAccount(lazy: true)
//		newSuggestion(lazy: true)
	}

//	def beforeUpdate() {
//		log.debug("Profile.beforeUpdate()")
//		log.debug("avatar:"+avatar)
//	}

	@Override
	public java.lang.String toString() {
		return "Profile{" +
				"id=" + id +
				", user=" + userId +
				", fio='" + fio + '\'' +
				", email='" + email + '\'' +
				", cellPhone='" + cellPhone + '\'' +
				", city=" + cityId +
				", dateActivity=" + dateActivity +
				", dayOfWork=" + dayOfWork +
				", version=" + version +
				'}';
	}
}
