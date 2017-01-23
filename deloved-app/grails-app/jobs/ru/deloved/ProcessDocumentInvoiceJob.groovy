package ru.deloved


class ProcessDocumentInvoiceJob {
	def billingService

	static triggers = {
		simple repeatInterval: 15000l, // execute job once in 10 seconds
				startDelay: 100000
	}

	def execute() {
		// execute job
//		log.debug("execute processing DocumentInvoice job")
		billingService.processInvoiceDocs()
	}
}
