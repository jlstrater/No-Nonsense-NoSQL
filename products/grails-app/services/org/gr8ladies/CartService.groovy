package org.gr8ladies

import grails.transaction.Transactional

@Transactional
class CartService {

    def redisService

    def getCart(sessionId) {
        Cart cart = new Cart(userSession: sessionId)
        def items = redisService.smembers("cart:$sessionId")
        items.each {
            def (productId, quantity) = it.tokenize(':')
            Product product = Product.get(productId)
            PriceQuantityRelation priceQuantityRelation = product.priceQuantityRelations.find {it.quantity == quantity.toInteger()}
            if(priceQuantityRelation)
                cart.addToCartItems(cart: cart, priceQuantityRelation: priceQuantityRelation, product: product)
    }
        return cart
    }

    def deleteCart(sessionId) {
        String key = "cart:$sessionId"
        redisService.del(key)
    }

    def addToCart(sessionId, priceQuantityRelationId) {
        String key = "cart:$sessionId"
        if(priceQuantityRelationId) {
            redisService.sadd(key, priceQuantityRelationId)
            redisService.expire(key, 1800)
        }
    }

    def getCartSize(sessionId) {
        def cart = getCart(sessionId)
        cart.cartItems?.size() ?: 0
    }

    BigDecimal getTotalPrice(sessionId) {
        BigDecimal sum = getCart(sessionId)?.cartItems?.sum { item ->
            item.priceQuantityRelation.unitPrice * item.priceQuantityRelation.quantity
        }
        sum ? sum.setScale(2) : 0.00
    }

}