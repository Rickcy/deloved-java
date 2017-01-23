package ru.deloved.recall

import ru.deloved.Account
import ru.deloved.Profile

class JuristConsultPost {

    JuristConsult juristConsult
    Date dateCreated
    Account account
    Profile person
    String post
    Integer status

    static hasMany = [attachments: JuristConsultPostAttach]

    static constraints = {
        juristConsult nullable: false
        account nullable: true
        person nullable: true
        post nullable: true, maxSize: 1500
        status nullable: true
    }

    JuristConsultStatus status() {
        this.status ? JuristConsultStatus.valueOf(this.status.intValue()) : null
    }
}
