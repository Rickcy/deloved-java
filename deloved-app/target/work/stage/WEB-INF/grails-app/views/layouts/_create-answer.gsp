

<sec:ifNotLoggedIn>

        <span>
            <a id="createSuggestion" href="javascript:void(0)">Связаться с нами</a>
        </span>
        <script>
            $(document).ready(function(){
                $("#createSuggestion").click(function(){
                    $("#suggestionModal").modal("show");
                });


            });
            function sendMail(){

            }
        </script>

        <div id="suggestionModal" class="modal fade" style="z-index: 1000000">

            <div class="modal-dialog" align="center">

                <div class="modal-content" style="width: 90%">

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" style="text-align: left">Мы рады вашим обращениям!</h4>
                    </div>


                    <div class="modal-body">





























                        <form class="contact_form" action="http://main-ip.ru/mail.php" method="post"><input type="hidden" name="zagol_soob" value="" />
                            <p>
                                <h5 >Имя:</h5>
                                <input class="form-control" style="width: 60%;margin-left: 6%" type="text"  name="name" placeholder="Введите ваше имя" required />
                            </p>
                            <p>
                                <h5>Email:</h5>
                                <input class="form-control" style="width: 60%;margin-left: 6%" type="text" name="e_mail" placeholder="Введите электронный адрес" required />

                            </p>

                            <p>
                                <h5>Тема вопроса</h5>
                                <select class="form-control" style="width: 60%;margin-left: 6%" type="text" name="pole_1a" placeholder="Выбирете тему" required>
                                    <option></option>
                                    <option>Вопросы при регистрации</option>
                                    <option>Реклама</option>
                                    <option>Сотрудничество</option>
                                    <option>Другое</option>
                                </select>
                            <input type="hidden" name="pole_1b" value="Тема" />
                            </p>
                            <p>
                                <h5>Текст сообщения:</h5>
                                <textarea name="text" class="form-control" cols="40" rows="6" required ></textarea>
                            </p>
                            <div style="overflow:auto; width:100%;">
                                <div style="float:left; padding:10px 0px 10px 20px;">Число на картинке<br />
                                    <input name="captcha" type="text" style="width:220px; height:20px; background:#FFFFFF; border:1px solid #CCCCCC; color:#191970;" maxlength="12" />
                                </div>
                                <div style="float:right; padding:10px 20px 10px 0px;">
                                    <a href="http://main-ip.ru/"><img border="0" src="http://main-ip.ru/captcha.php" /></a>
                                </div>
                            </div>
                            <p>
                                <button class="btn btn-lg btn-primary btn-block" style="width: 50%"  type="submit">Отправить сообщение</button>
                            </p>

                        </form><!-- 12:49, 02.03.2016 -->



                    </div>



                </div>
            </div>
        </div>

</sec:ifNotLoggedIn>

<sec:ifLoggedIn>
    <g:link controller="ticket" action="create">Связаться с нами</g:link>
</sec:ifLoggedIn>