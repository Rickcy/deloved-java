package ru.deloved


class ProcessDocumentIncomeJob {
	def billingService

	static triggers = {
		simple repeatInterval: 15000l, // execute job once in 10 seconds
				startDelay: 150000
	}

	def execute() {
		// execute job
//		log.debug("execute processing DocumentIncome job")
		billingService.processIncomeDocs()
	}
}
