package org.gr8ladies

class CartItem {

    PriceQuantityRelation priceQuantityRelation
    Product product

    static belongsTo = [cart: Cart]

    static embedded = ['priceQuantityRelation']

    static constraints = {
    }
}