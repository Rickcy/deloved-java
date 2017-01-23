package ru.deloved

import org.grails.plugins.excelimport.AbstractExcelImporter
import org.grails.plugins.excelimport.ExcelImportService

import static org.grails.plugins.excelimport.ExpectedPropertyType.*

/**
 * Created by Андрейка on 22.05.2015.
 */
class ItemExcelImporter extends AbstractExcelImporter {

	static Map CONFIG_ITEM_COLUMN_MAP = [
			sheet    : 'Report',
			startRow : 1,
			columnMap: [
					'A': 'id',
					'B': 'name',
					'C': 'price',
					'D': 'description',
					'E': 'availability',
					'F': 'category'
			],
			configMap: [
					'id'          : [expectedType: IntType, defaultValue: 0],
					'availability': [expectedType: IntType, defaultValue: 0],
					'price'       : [expectedType: DoubleType, defaultValue: 0.0],
					'name'        : [expectedType: StringType],
					'description' : [expectedType: StringType, defaultValue: ''],
					'category'    : [expectedType: StringType]
			]
	]

	ItemExcelImporter(InputStream is) {
		this.read(is);
	}

	def getExcelImportService() {
		ExcelImportService.getService()
	}

	List<Map> getItems() {
		List itemList = excelImportService.columns(workbook, CONFIG_ITEM_COLUMN_MAP)
	}
}
