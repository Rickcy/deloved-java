Здравствуйте, ${contactConfirm.contact}! Вы зарегистрировались на межотраслевом бизнес-портале "Деловед".<br/>

<br/>
Чтобы активировать свой профиль пройдите
<g:link url="[base:baseURL, plugin: 'deloved-front', controller: 'signup', action: 'activate', params: [code: contactConfirm.secret]]" target="_blank"> по этой ссылке.</g:link><br/>
<br/>
Или введите код активации
<g:link url="[base:baseURL, plugin: 'deloved-front', controller: 'signup', action: 'activate']" target="_blank"> на этой странице.</g:link>
Код активации: <b>${contactConfirm.secret}</b>





