<?php

$name = htmlspecialchars($_POST["name"]);
$email = htmlspecialchars($_POST["e_mail"]);
$tema = htmlspecialchars($_POST["pole_1"]);
$message = htmlspecialchars($_POST["message"]);
$bezspama = htmlspecialchars($_POST["bezspama"]);


$address = "deloved.info@gmail.com";
$sub = " Сообщение с Deloved;
 

$mes = "Сообщение с сайта Deloved\n
Имя отправителя: $name
Электронный адрес отправителя: $email
Тема Вопроса: $tema
Текст сообщения:
$message";
 
 
if (empty($bezspama))
{

$from  = "From: $name <$email> \r\n Reply-To: $email \r\n"; 
if (mail($address, $sub, $mes, $from)) {
	header('Refresh: 5; URL=http://biznessystem.ru');
	echo 'Письмо отправлено, через 5 секунд вы вернетесь на сайт Deloved.ru';}
else {
	header('Refresh: 5; URL=http://biznessystem.ru');
	echo 'Письмо не отправлено, через 5 секунд вы вернетесь на страницу Deloved.ru';}
}
exit;
php?>