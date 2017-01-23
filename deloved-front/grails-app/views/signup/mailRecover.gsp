Здравствуйте!<br/>
<br/>
Вы или кто-то другой запросил смену пароля<br/>
Email: ${contactConfirm.contact} <br/>
Прослейдуйте по этой ссылке для смены пароля: <g:link url="[base:baseURL, plugin: 'deloved-admin', controller: 'recover', action: 'change', params: [code: contactConfirm.secret]]" target="_blank">
	${createLink(base: baseURL, plugin: 'deloved-admin', controller: 'recover', action: 'change', params: [code: contactConfirm.secret])}
</g:link><br/>
или введите слующий код <b>${contactConfirm.secret}</b> <br/>
перейдя по этой ссылке <g:link url="[base:baseURL, plugin: 'deloved-admin', controller: 'recover', action: 'change']" target="_blank">
	${createLink(base: baseURL, plugin: 'deloved-admin', controller: 'recover', action: 'change')}
</g:link> <br/>
Не отвечайте на это письмо, т.к. оно сгенерировано автоматически.


