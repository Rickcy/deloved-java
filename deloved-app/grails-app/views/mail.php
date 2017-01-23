<?php

$name = htmlspecialchars($_POST["name"]);
$email = htmlspecialchars($_POST["email"]);
$tel = htmlspecialchars($_POST["tel"]);
$message = htmlspecialchars($_POST["message"]);
$bezspama = htmlspecialchars($_POST["bezspama"]);
$website = htmlspecialchars($_POST["website"]);

$address = "deloved.info@gmail.com";
$sub = " Deloved.ru";
 

$mes = "Сообщение с сайта Deloved.ru.\n
Имя отправителя: $name
Электронный адрес отправителя: $email
Телефон отправителя: $tel
Тема вопроса: $website
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