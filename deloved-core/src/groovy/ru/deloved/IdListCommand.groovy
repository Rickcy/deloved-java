package ru.deloved

import org.codehaus.groovy.grails.validation.Validateable

/**
 * Created with IntelliJ IDEA.
 * User: Андрейка
 * Date: 07.11.13
 * Time: 15:05
 */
@Validateable
class IdListCommand {
	Set<Long> id;

	public List<Long> getList() {
		def list = []
		id.each {
			list.add(Long.valueOf(it))
		}
		return list
	}

	@Override
	public String toString() {
		return id.toString();
	}
}
