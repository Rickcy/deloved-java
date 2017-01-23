package ru.deloved

class AccountStat {

	Account account
	int totalViewAccount = 0
	int totalViewGoods = 0
	int totalViewServices = 0
	int monthViewAccount = 0
	int monthViewGoods = 0
	int monthViewServices = 0

	static constraints = {
		account nullable: false
	}

	@Override
	public String toString() {
		return "AccountStat{" +
				"account=" + accountId +
				", totalViewAccount=" + totalViewAccount +
				", totalViewGoods=" + totalViewGoods +
				", totalViewServices=" + totalViewServices +
				", monthViewAccount=" + monthViewAccount +
				", monthViewGoods=" + monthViewGoods +
				", monthViewServices=" + monthViewServices +
				'}';
	}
}
