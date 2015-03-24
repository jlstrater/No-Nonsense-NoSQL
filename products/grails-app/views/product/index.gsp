<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-product" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-product" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <tr>
                    <th></th>
                    <th>Name</th>
                    <th>Chapter</th>
                    <th>Link</th>
                    <th>Preview Image</th>
                </tr>
                <g:each var="product" in="${productList}">
                    <tr>
                        <td><g:link action="show" id="${product.id}">Show</g:link></td>
                        <td>${product.name}</td>
                        <td>${product?.chapter}</td>
                        <td><a href="${product?.vendorUrl}" target="_blank">${product.vendorUrl}</a></td>
                        <td><g:if test="${product.imageUrl}"><img src=${assetPath(src: "${product.imageUrl}")} width="50px" height="50px"/></g:if></td>
                    </tr>
                </g:each>
            </table>
            <div class="pagination">
                <g:paginate total="${productCount ?: 0}" />
            </div>
        </div>
    </body>
</html>