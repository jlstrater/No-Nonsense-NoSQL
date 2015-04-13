package org.gr8ladies

import org.bson.types.ObjectId

class Cart {

    ObjectId id
    String userSession

    static hasMany = [cartItems: CartItem]

    static embedded = ['cartItems']

    static constraints = {
    }

    Integer getSize() {
        this.cartItems?.size() ?: 0
    }
}