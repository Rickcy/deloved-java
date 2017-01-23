package paymaster

import grails.transaction.Transactional
import groovyx.net.http.HTTPBuilder
import groovy.json.JsonSlurper
import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.Method.GET




import org.apache.commons.lang.RandomStringUtils
import ru.deloved.billing.PaymentRequest
import ru.deloved.billing.PaymentSystem

@Transactional
class PaymasterService {

    /* Параметры генерации Nonce */
    //int randomStringLength = 32
    //String charset = (('a'..'z') + ('A'..'Z') + ('0'..'9')).join()

    /*
    Параметры авторизации и аунтификации в платежной системе для запроса статуса платежа.
    Все запросы выполняются от имени пользователей созданных со способом входа "Автоматический доступ".
    Пользователям должна быть назначена роль Операционист (получение статуса платежа и списка платежей)
    или Бухгалтер (Операционист + возврат платежа + список возвратов).
    */
    String login = 'deloved.info@gmail.com'
    String password = 'Delo2016ved'
    String site = 'https://paymaster.ru'
    String path = '/partners/rest/getPaymentByInvoiceID'
    String merchantId = 'a2498ef4-9f7c-4bc0-ad34-edacc30ffc6b'

    String getSecretSign(Map params) {
        String s = params.LMI_MERCHANT_ID + ';' +
                params.LMI_PAYMENT_NO + ';' +
                params.LMI_SYS_PAYMENT_ID + ';' +
                params.LMI_SYS_PAYMENT_DATE + ';' +
                params.LMI_PAYMENT_AMOUNT + ';' +
                params.LMI_CURRENCY + ';' +
                params.LMI_PAID_AMOUNT + ';' +
                params.LMI_PAID_CURRENCY + ';' +
                params.LMI_PAYMENT_SYSTEM + ';' +
                (params.LMI_SIM_MODE ?: '') + ';' + 'dunkleosteus'
        return s.encodeAsMD5Bytes().encodeAsBase64()
    }

    Boolean checkSecretSign(Map params) {
        return params.LMI_HASH == getSecretSign(params)
    }

    Boolean checkParams(Map params) {
        PaymentRequest paymentRequest = PaymentRequest.findById(params.LMI_PAYMENT_NO)
        if (!paymentRequest) {
            log.debug('Wrong payment request!')
            return false
        }
        if (params.LMI_PAYMENT_AMOUNT != paymentRequest.value.toString()) {
            log.debug('Wrong value!')
            return false
        }
        if (params.LMI_CURRENCY != paymentRequest.keeper.currency.code) {
            log.debug('Wrong currency!')
            return false
        }
        if (params.LMI_MERCHANT_ID != merchantId) {
            log.debug('Wrong merchant ID!')
            return false
        }
        return true
    }

    def checkRequestStatus(PaymentRequest request) {
        /*
	    Хешируемые параметры: login;password;nonce;invoiceID;siteAlias.
	    Значения полей записываются в одну строчку через точку с запятой,
	    затем от полученной UTF8-строки считается SHA1-хеш, который затем кодируется base64.
	     */

        if (!request) {
            return false
        }

        log.debug('Check Request Status for PaymentRequest: ' + request + ". Request ID:" + request.id + ". Request ID to string: " + request.id.toString())
        String nonce = UUID.randomUUID().toString()
        String hash = (login + ';' + password + ';' + nonce + ';' + request?.id + ';' + merchantId).encodeAsSHA1Bytes().encodeAsBase64()
        Map query = [login: login, nonce: nonce, invoiceid: request?.id, siteAlias: merchantId, hash: hash]

        def http = new HTTPBuilder()

        http.request(site, GET, JSON) { req ->
            uri.path = path
            uri.query = query
            headers.'User-Agent' = "grails web-app (centos)"
            headers.Accept = 'application/json'
            response.success = { resp, reader ->
                if (resp.statusLine.statusCode != 200) {
                    return false
                }
                if (reader.ErrorCode != 0) {
                    /*
			        Коды ошибок.
			    	0 Без ошибок
                   -1 Неизвестная ошибка. Сбой в системе PayMaster. Если ошибка повторяется, обратитесь в техподдержку.
                   -2 Сетевая ошибка. Сбой в системе PayMaster. Если ошибка повторяется, обратитесь в техподдержку.
                   -6 Нет доступа. Неверно указан логин, или у данного логина нет прав на запрошенную информацию.
                   -7 Неверная подпись запроса. Неверно сформирован хеш запроса.
                   -13 Платеж не найден по номеру счета
                   -14 Повторный запрос с тем же nonce
                   -18 Неверное значение суммы (в случае возвратов имеется в виду значение amount)
                    */
                    return reader
                }

                /*
                Параметры ответа
                State - состояние платежа. Допустимые значения:
                    INITIATED: платеж начат,
                    PROCESSING: платеж проводится
                    COMPLETE: платеж завершен успешно
                    CANCELLED: платеж завершен неуспешно
                Amount - сумма платежа
                CurrencyCode - 3-буквенный ISO код валюты
                IsTestPayment - признак тестового платежа (платеж совершен в тестовом режиме)
                LastUpdate (LastUpdateTime для JSON) - время последнего обновления статуса. Для завершенных платежей - время завершения платежа.
                PaymentAmount - сумма оплаты
                PaymentCurrencyCode - валюта оплаты
                PaymentID - идентификатор платежа
                PaymentSystemID - идентификатор платежной системы
                Purpose - примечание к платежу
                SiteID - идентификатор сайта - получателя платежа
                SiteInvoiceID - номер счета (параметр LMI_PAYMENT_NO)
                UserIdentifier - идентификатор пользователя в платежной системе
                UserPhoneNumber - номер телефона плательщика.
                 */
                return reader

            }
            response.'404' = {
                return false
            }
        }
    }
}