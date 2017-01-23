<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="admin">
    <title>Новое обращение к юристу</title>
</head>

<body>

<div id="create-ticket" class="content scaffold-create" role="main">

    <h1>Новое обращение к юристу</h1>

    <g:if test="${freeAccount}">
        <g:render template="/_common/promo"/>
    </g:if>
    <g:else>

        <g:render template="/_common/create" model="[
                'objInstance' : juristConsultInstance,
                'formTemplate': 'new'
        ]"/>

    </g:else>

</div>
</body>
</html>
