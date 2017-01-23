package ru.deloved


class ProcessPaymentRequestJob {
	def billingService

	static triggers = {
		simple repeatInterval: 15000l, // execute job once in 10 seconds
				startDelay: 50000
	}

	def execute() {
		billingService.processPaymentRequests()
	}
}
