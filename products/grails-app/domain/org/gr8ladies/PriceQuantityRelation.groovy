package org.gr8ladies

class PriceQuantityRelation {

    BigDecimal price
    Integer quantity
    BigDecimal unitPrice

    static constraints = {
        quantity min: 1
        price min: 0.00, scale: 2
        unitPrice min: 0.00, scale: 4
    }

    String getDisplayName() {
        quantity + ' for $' + price + '($' + unitPrice + ' each)'
    }
}