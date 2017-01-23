package ru.deloved.front

class ArticleController {
    def news(){
        render view: 'news',model: []
    }
    def deal_online(){
        render view: 'deal_online',model: []
    }
    def rating_system(){
        render view: 'rating_system',model: []
    }
    def judge_service(){
        render view: 'judge_service',model: []
    }
    def jurist_service(){
        render view: 'jurist_service',model: []
    }
    def mediation_service(){
        render view: 'mediation_service',model: []
    }

    def ticket(){
        render view: 'ticket',model: []
    }


}
