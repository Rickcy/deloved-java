package ru.deloved

class AccountDealRating {

	Account account
	Deal deal
	int dealSuccess=0
	int dealFail=0
	int enable = 0
	int badReview = 0
	int badDispute = 0
	int badClaim = 0

	static constraints = {
		account nullable: false
		deal nullable: true
	}


	@Override
	public String toString() {
		return "AccountDealRating{" +
				"id=" + id +
				", account=" + accountId +
				", deal=" + dealId +
				", S=" + dealSuccess +
				", F=" + dealFail +
				", r=" + badReview +
				", d=" + badDispute +
				", c=" + badClaim +
				'}';
	}
}
