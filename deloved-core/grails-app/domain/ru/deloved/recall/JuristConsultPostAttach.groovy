package ru.deloved.recall

import ru.deloved.Account
import ru.deloved.Attachment

class JuristConsultPostAttach {

    JuristConsult juristConsult
    Account account
    Attachment attachment

    static belongsTo = [juristConsultPost: JuristConsultPost]

    static constraints = {
        account nullable: true
        juristConsultPost nullable: true
    }
}
