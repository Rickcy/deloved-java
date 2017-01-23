package ru.deloved

import org.grails.databinding.converters.ValueConverter
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.servlet.support.RequestContextUtils

import javax.servlet.http.HttpServletRequest
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.ParsePosition

/**
 * Created by Андрейка on 08.04.2015.
 */
class BigDecimalConverter implements ValueConverter {


	@Override
	boolean canConvert(value) {
		value instanceof String
	}

	@Override
	Object convert(Object value) {
		String amount = value
		println "amount: " + amount
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		DecimalFormat nf = (DecimalFormat) NumberFormat.getInstance(RequestContextUtils.getLocale(request));
		nf.setParseBigDecimal(true);

		BigDecimal bd = (BigDecimal) nf.parse(amount, new ParsePosition(0));
		println "bd:" + bd
		return bd
	}

	@Override
	Class<?> getTargetType() {
		return java.math.BigDecimal
	}
}
