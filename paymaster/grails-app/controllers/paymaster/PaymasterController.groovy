package paymaster

import grails.converters.JSON
import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import ru.deloved.billing.PaymentRequest
import ru.deloved.billing.RequestStatus

@Transactional
class PaymasterController {

    //static allowedMethods = ["POST"]
    String uri = new DefaultGrailsApplication().config.mailBaseURL=="http://localhost:8080/deloved-app"?new DefaultGrailsApplication().config.mailBaseURL:"http://deloved.ru"
    def PaymasterService

    def index() {
        response.sendError(404)
        return response
    }

    def successes() {
        /* if (request.method != 'POST') {
             log.debug("Wrong request method!")
             response.sendError(404)
             return
         } */

        // log.debug("Payment success" + params)

        flash.message = "Платеж успешно проведен. Ожидайте обработки системой."
        flash.status = 'success'
        redirect(base: uri,controller: 'billing', action: 'account')
    }

    def fail() {
        /* if (request.method != 'POST') {
             log.debug("Wrong request method!")
             response.sendError(404)
             return
         } */

        // log.debug("Payment fail" + params)

        flash.message = "Ошибка при проведении платежа. Попробуйте еще раз, если проблема повторится - сообщите нам"
        flash.status = 'success'
        redirect(base: uri,controller: 'billing', action: 'account')

    }

    //TODO Обработчик платежа подтверждения проведения платежа (ОЧЕНЬ ВАЖНЫЙ!!!)
    def paymentNotification() {

        /* 1. Проверям метод запроса */
        if (request.method != 'POST') {
            log.debug("Wrong request method!")
            response.sendError(404)
            return
        }

        /* 2. Проверям секретный ключ */
        if (!PaymasterService.checkSecretSign(params)) {
            log.debug("Wrong secret sign!")
            response.sendError(404)
            return
        }

        /* 3. Проверяем соответсвие данные */
        if (!PaymasterService.checkParams(params)) {
            response.sendError(404)
            return
        }


        PaymentRequest paymentRequest = PaymentRequest.findById(params.LMI_PAYMENT_NO)


        if (paymentRequest.status == RequestStatus.New.value()) {
            paymentRequest.status = RequestStatus.Executed.value()
            paymentRequest.save(flush: true)
            //response.status = 200
            response.sendError(200)
            return
        } else {
            log.debug("paymentNotification. Request already exist")
            response.sendError(200)
            return
        }

    }


    //TODO Обработчик предзапроса платежный системы
    def invoiceConfirmation() {

        /* 1. Проверяем на метод запроса. Только POST. */
        if (request.method != 'POST') {
            log.debug("Bad request method!")
            response.sendError(404)
            return
        }

        /* 2. Проверяем на флаг предварительного запроса. */
        if (params.LMI_PREREQUEST != '1') {
            log.debug("Not prerequest!")
            response.sendError(404)
            return
        }

        /* 3. Проверяем соответсвие данные */
        if (!PaymasterService.checkParams(params)) {
            log.debug("Bad params!")
            response.sendError(404)
            return
        }

        /* 4. Проверям не находится ли нужный запрос уже в обработке */
        if (PaymentRequest.findById(params.LMI_PAYMENT_NO).status != RequestStatus.New.value()) {
            log.debug("Request already exist")
            response.sendError(404)
            return
        }

        /* 5. Если дошли до этого момента, то все отлично. Отвечаем "ДА" серверу ПС. */
        log.debug("Success prerequest. All right!")
        render "YES"
        return

    }

    def checkRequestStatus(PaymentRequest request) {
        log.debug("ping")
        def response =  PaymasterService.checkRequestStatus(request)

        render response as JSON
    }
}
