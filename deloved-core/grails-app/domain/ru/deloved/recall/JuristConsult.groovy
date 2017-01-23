package ru.deloved.recall

import ru.deloved.Account
import ru.deloved.Profile


class JuristConsult {

    Account account
    Profile jurist
    Date dateCreated
    int status

    static belongsTo = Profile
    static hasMany = [newProfiles: Profile]

    static constraints = {
        account nullable: true
        jurist nullable: true
    }

    JuristConsultStatus status() {
        return JuristConsultStatus.valueOf(this.status)
    }
    def position() {
        return JuristConsultStatus.getPosition(this.status)
    }

}

enum JuristConsultStatus {
    New(0),
    Processing(10),
    Closed(20)


    private final int value

    JuristConsultStatus(int value) {
        this.value = value
    }

    static public JuristConsultStatus valueOf(int code) {
        JuristConsultStatus.values().grep { it.value == code }[0] ?: null
    }
    static public ArrayList<JuristConsultStatus> FullStatus() {
        return [JuristConsultStatus.Processing, JuristConsultStatus.Closed]
    }

    public int position() {
        JuristConsultStatus.getPosition(this.value)
    }
    static public int getPosition(int code){
        switch (code) {
            case 0: return 10
            case 10: return 50
            case 20: return 100
        }
        return 100
    }
    public int value() { return value }

}