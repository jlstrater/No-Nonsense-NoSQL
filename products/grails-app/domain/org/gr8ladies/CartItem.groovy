package org.gr8ladies

class CartItem {

    PriceQuantityRelation priceQuantityRelation

    static belongsTo = [cart: Cart]

    static constraints = {
    }
}