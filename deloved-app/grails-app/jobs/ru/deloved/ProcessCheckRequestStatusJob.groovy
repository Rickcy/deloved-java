package ru.deloved

import paymaster.PaymasterService
import ru.deloved.billing.PaymentMethod
import ru.deloved.billing.PaymentRequest
import ru.deloved.billing.RequestStatus


class ProcessCheckRequestStatusJob {

    def paymasterService
    def concurrent = false

    static triggers = {
      simple repeatInterval: 30000l,
              startDelay: 400000 // execute job once in 5 seconds
    }


    def execute() {
        PaymentRequest.executeQuery("""
            select req
            from PaymentRequest as req
            where req.status = :status
            and req.method != :method
            """, [status: RequestStatus.New.value(), method: PaymentMethod.findByCode("INCOME_MANUAL")],
            [max: 10, lock: true]
            ).each {PaymentRequest req ->

                req.lock()
                def status = paymasterService.checkRequestStatus(req)

                log.debug(status)

                if (status?.ErrorCode == -13) {
                    log.debug('before: ' + req.status)
                    req.status = RequestStatus.Failed.value()
                    log.debug('after: ' + req.status)
                }

                if (status?.Payment?.State == 'CANCELLED') {
                    req.status = RequestStatus.Failed.value()
                }

                req.save(flush: true)
                log.debug("FINISH Checking Request Status. REQUEST: " + req + ". Result: " + req.status())
            }
    }

}
