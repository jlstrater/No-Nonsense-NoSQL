<%@ page import="org.gr8ladies.Cart" %>
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
                <li><g:link class="cart" controller="cart" action="show">Cart( ${cartSize} )</g:link></li>
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
                    <th>Vendor</th>
                    <th>Preview</th>
                    <th>Quantity -- Price</th>
                    <th></th>
                </tr>
                <g:each var="product" in="${productList}">
                    <g:form controller="cart" action="add">
                        <tr>
                            <td><g:link action="show" id="${product.id}">Show</g:link></td>
                            <td>${product.name}</td>
                            <td>${product?.chapter}</td>
                            <td><a href="${product.vendor.url + product.vendorUrlPath}" target="_blank">${product.vendor.name}</a></td>
                            <td><g:if test="${product.imageUrl}"><a href="${assetPath(src:product.imageUrl)}" target="_blank">
                                <img src=${assetPath(src: "${product.imageUrl}")} width="100px" height="75px"/></a></g:if></td>
                            <td><g:select from="${product.priceQuantityRelations}"
                                          optionValue="displayName" name="priceQuantityRelation.id" optionKey="id" noSelection="['':'']"></g:select></td>
                            <g:hiddenField name="product.id" value="${product.id}"/>
                            <td><g:submitButton name="cart" value="Add to Cart"/></td>
                        </tr>
                    </g:form>
                </g:each>
            </table>
            <div class="pagination">
                <g:paginate total="${productCount ?: 0}" />
            </div>
        </div>
    </body>
</html>