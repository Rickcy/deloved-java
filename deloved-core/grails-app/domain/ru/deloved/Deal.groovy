package ru.deloved

class Deal {

	/**
	 * Заказчик
	 */
	Account account
	/**
	 * Подрядчик
	 */
	Account partner
	Date dateCreated
	int status
	Account failedBy

	static belongsTo = Profile
	static hasMany = [newProfiles: Profile]

	static constraints = {
		account nullable: false
		partner nullable: false
		failedBy nullable: true
	}

	DealStatus status() {
		return DealStatus.valueOf(this.status)
	}

	def position() {
		return DealStatus.getPosition(this.status)
	}



}

enum DealStatus {
	/* Статусы сделки при заключении договора */
	Proposed(100),                      //Сделка преложена
	Discass(101),                       //Обсуждение условий сделки
	SellerSign(102),                    //Договор подписан продавцом
	BuyerSign(103),                     //Договор подписан покупателем
	SignUP(104),                        //Договор подписан

	/* Статусы сделки без предоплаты */
	WaitNonPaidedExecute(200),          //Ожидание исполнения обязательств по сделке
	NonPaidedExecute(201),              //Обязательства по сделке исполнены
    FullPostPaid(202),                  //Оплата внесена
	//FullPostPaidConfirm(204),           //Полная оплата подтверждена
			WaitNonPaidedExecutee(200),
	/* Статусы сделки с полной предоплатой */
	FullPrePaid(300),                   //Предоплата внесена
	FullPrePaidConfirm(301),            //Полная предоплата подтвержденна
	WaitPaidedExecute(302),             //Ожидание исполнения обязательств по сделке
	PaidedExecute(304),                 //Обязательбства по сделке исполнены

	/* Статусы сделки с частичной предоплатой */
	HalfPrePaid(400),                   //Частичная предоплата внесена
	HalfPrePaidConfirm(401),            //Частичная предоплата подтвержденна
	WaitHalfPaidedExecute(402),         //Ожидание исполнения обязательств по сделке
	HalfPaidedExecute(403),             //Обязательства по сделке исполнены
	HalfPostPaid(404),                  //Полная предоплата внесена
	//HalfPostPaidConfirm(405),           //Полная оплата подтвержденна

	/* Служебные статусы */
	Confirmed(500),                     //Успешная сделка
	Rejected(501),                      //Сделка отвергнута
	Failed(502),                        //Обязательство по сделке не исполнено
	Suspended(503)                      //Сделка приостановлена


	private final int value

	DealStatus(int value) {
		this.value = value
	}

	static public DealStatus valueOf(int code) {
		DealStatus.values().grep { it.value == code }[0] ?: null
	}

	static public ArrayList<DealStatus> halfPaidStatuses() {
		return [DealStatus.HalfPrePaid, DealStatus.HalfPrePaidConfirm, DealStatus.WaitHalfPaidedExecute, DealStatus.HalfPaidedExecute, DealStatus.HalfPostPaid]
	}

	static public ArrayList<DealStatus> fullPaidStatuses() {
		return [DealStatus.FullPrePaid, DealStatus.FullPrePaidConfirm, DealStatus.WaitPaidedExecute,DealStatus.PaidedExecute]
	}

	static public ArrayList<DealStatus> nonPaidStatuses() {
		return [DealStatus.WaitNonPaidedExecute, DealStatus.NonPaidedExecute, DealStatus.FullPostPaid]
	}
	static public ArrayList<DealStatus> DEAL(){
		return [DealStatus.Discass, DealStatus.SignUP,DealStatus.WaitNonPaidedExecutee,DealStatus.FullPostPaid,
		DealStatus.FullPrePaid,DealStatus.HalfPostPaid,DealStatus.Confirmed,DealStatus.Suspended,DealStatus.Rejected]
	}


	public int position() {
		DealStatus.getPosition(this.value)
	}

	static public int getPosition(int code){
		switch (code) {
			case 100: return 7
			case 101: return 14
			case 102: return 21
			case 103: return 21
			case 104: return 28 //x

			case 200: return 46
			case 201: return 64//x
			case 202: return 82

			case 300: return 46 //x
			case 301: return 64
			case 302: return 82 //x
			case 304: return 95 //x

			case 400: return 40
			case 401: return 52 //x
			case 402: return 64
			case 403: return 76 //x
			case 404: return 88

			case 500:return 100
			case 501:return 100
			case 502:return 100
			case 503:return 100
			default: return 100
		}
		return 100
	}

	public int value() { return value }
}
