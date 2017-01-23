package ru.deloved

import org.apache.commons.lang.StringUtils

class StringUtilsTagLib {
	static defaultEncodeAs = 'html'
	static encodeAsForTags = [truncate: 'text']

	/**
	 * Return maximum @max@ chars from left of @value@ or the whole @value@ if it is shorter than @max@ characters.
	 * Parameter @words@ of @true@ specify do not truncate in middle of the word
	 *
	 * @attr max REQUIRED Макс. длина для обрезки
	 * @attr value Исходная строка
	 * @attr words Обрезать по словам. Default false
	 * @attr tail Хвост(многоточие). Default '…'
	 */
	def truncate = { attrs, body ->

		if (attrs.max) {
			int max = Integer.parseInt(attrs.max as String)
			String s = StringUtils.trim(attrs.value ? (attrs.value as String) : (body() as String))
			String end = null
			if (s.length() > max) {
				if (attrs.words) {
					if (StringUtils.trim(StringUtils.substring(s, 0, max + 1)).length() < StringUtils.substring(s, 0, max + 1).length()) {
						end = StringUtils.trim(StringUtils.substring(s, max))
						s = StringUtils.trim(StringUtils.substring(s, 0, max))
					} else {
						int pos = StringUtils.lastIndexOfAny(StringUtils.substring(s, 0, max), [" ", "\\t", "\\n", "\\r\\n"] as String[])
						if (pos == -1) {
							pos = max
						}
						end = StringUtils.substring(s, pos)
						s = StringUtils.substring(s, 0, pos)
					}
				} else {
					end = StringUtils.substring(s, max)
					s = StringUtils.substring(s, 0, max)
				}
				if (attrs.tail != null) {
					s = s + attrs.tail
				} else {
					s = s + '…'
				}
			}
			out << s
			if (attrs.value && end != null && attrs.varTail != null) {
				def var = attrs.varTail
				out << body((var): end)
			}
		}
	}

	/**
	 * Возвращает текстовое представление рейтинга в соответствии с настройками
	 *
	 * @attr value REQUIRED Величина рейтинга в процентах
	 */
	def rating = { attrs, body ->
		if (attrs.value != null) {
			Integer value = attrs.value as Integer
			def res = null
			if (value != null) {
				Map ratingText = grailsApplication.config.ratingText
				if (ratingText == null || ratingText.isEmpty()) {
					ratingText = [

							0 : 'D-',
							20 : 'D',
							30 : 'C',
							40 : 'CC',
							50 : 'CCC',
							60 : 'B',
							70 : 'BB',
							80 : 'BBB',
							90 : 'A',
							99 : 'AA',
							101: 'AAA'
					]
				}
				def m = ratingText.findAll { value < it.key }.min { a, b -> a.key <=> b.key }
				if (m) {
					res = g.message(code: 'account.rating.text.' + m.value, default: m.value)
				}
			}
			if (res) {
				out << res
			} else {
				out << g.message(code: 'account.rating.text.INVALID', default: 'INVALID')
			}
		} else {
			out << g.message(code: 'account.rating.text.no_raiting', default: 'no rating')
		}
	}
}
