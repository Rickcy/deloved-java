package ru.deloved
import org.codehaus.groovy.grails.plugins.DomainClassGrailsPlugin

/**
 * Created by artwolf on 03.11.2015.
 * Существенно снижает рассход памяти, связано с тем, что перед .save() делается .validate() объекта, и сохраняет все ошибки в error объекта request
 * Если запроса нет (как в случае обработки данных через quartz, то данные сохраняются в DomainClassGrailsPlugin.PROPERTY_INSTANCE_MAP
 * И не удаляются, ссылки на объекты в памяти остаются и с течением времени память тупо заканчивается.
 * Ирония в том, что я чищу память тем же, что и засоряет её xD
 */

class CleaningPropInstanceMapJob {

	def concurrent = false
	static triggers = {
		simple repeatInterval: 3600000,//43200001, // execute job once in 1 hours 1*60*60+1000
				startDelay: 10000
	}

	def execute() {
		DomainClassGrailsPlugin.PROPERTY_INSTANCE_MAP.get().clear()
	}
}
