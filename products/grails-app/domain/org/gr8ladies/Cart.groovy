package org.gr8ladies

class Cart {
    String userSession

    static hasMany = [cartItems: CartItem]

    static constraints = {
    }

    Integer getSize() {
        this.cartItems?.size() ?: 0
    }
}