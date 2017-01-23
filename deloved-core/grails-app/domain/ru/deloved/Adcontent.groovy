package ru.deloved

class Adcontent {

    String name
    String description
    Account account
    int type
    boolean approved
    Attachment file
    Attachment fileThumb
    String url
    Date dateCreated

    static constraints = {
        name blank: false
        description nullable: true, maxSize: 1500
        account nullable: false
        file nullable: true
        fileThumb nullable: true
        url nullable: true, maxSize: 10000
    }

    AdcontentType type() {
        return AdcontentType.valueOf(this.type)
    }
}

enum AdcontentType {

    Image(10),
    Pdf(20),
    Audio(30),
    Video(40),
    Doc(50)

    private final int value

    AdcontentType(int value) {
        this.value = value
    }

    static public AdcontentType valueOf(int code) {
        AdcontentType.values().grep { it.value == code }[0] ?: null
    }

    public int value() { return value }
}