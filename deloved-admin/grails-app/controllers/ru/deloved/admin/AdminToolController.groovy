package ru.deloved.admin

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import pl.touk.excel.export.WebXlsxExporter
import ru.deloved.Account
import ru.deloved.Item

@Transactional(readOnly = true)
@Secured(["hasAnyRole('ROLE_ADMIN') and isFullyAuthenticated()"])
class AdminToolController {

	def index() {
	}

	def emailexport() {
		def headers = [
				'Id',
				'Name',
				'Email',
				'City',
				'DateCreated',
		]
		def withProperties = [
				'id',
				'name',
				'email',
				'city.name',
				'dateCreated'
		]

		List<Item> products = Account.executeQuery("""
			select a
			from Account as a
			where a not in (select ap.account from AccountProfile ap)
			""")


		def filename = "emails_" + new Date().format('yyyy-MM-dd_hh-mm-ss') + ".xlsx";
		response.setHeader("Content-disposition", "attachment; filename=$filename;")
		response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")

		new WebXlsxExporter().with {
//				setResponseHeaders(response, '')
			fillHeader(headers)
			add(products, withProperties)
			save(response.outputStream)
		}

	}
}
