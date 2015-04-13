<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'cart.label', default: 'Cart')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-cart" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-cart" class="content scaffold-show" role="main">
            <h1><g:message code="total.price" args="['$' + totalPrice]"/></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <tr>
                    <th></th>
                    <th>Preview</th>
                    <th>Name</th>
                    <th>Unit Price</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th></th>
                </tr>
                <g:each var="item" in="${cart.cartItems}">
                    <g:form controller="cart" action="remove">
                        <tr>
                            <td><g:link controller="product" action="show" id="${item.product.id}">Show</g:link></td>
                            <td><g:if test="${item.product.imageUrl}">
                                <a href="${assetPath(src: item.product.imageUrl)}" target="_blank">
                                <img src="${assetPath(src: item.product.imageUrl)}" width="30px" height="25px"/></a></g:if></td>
                            <td>${item.product.name}</td>
                            <td>${item.priceQuantityRelation.unitPrice}</td>
                            <td>${item.priceQuantityRelation.quantity}</td>
                            <td>${item.priceQuantityRelation.price}</td>
                            <g:hiddenField name="cartItem" value="${item.id}"/>
                            <td><g:submitButton name="cart" value="Remove From Cart"/></td>
                        </tr>
                    </g:form>
                </g:each>
            </table>
            <g:form resource="${cart}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${cart}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>