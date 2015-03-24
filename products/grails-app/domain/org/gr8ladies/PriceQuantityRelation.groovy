package org.gr8ladies

class PriceQuantityRelation {

    BigDecimal price
    Integer quantity

    static belongsTo = [product: Product]

    static constraints = {
        quantity min: 1
        price min: 0.00, scale: 2
    }
}
