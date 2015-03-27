package org.gr8ladies

class Product {

    String name
    Vendor vendor
    String chapter
    String imageUrl
    String vendorUrlPath
    SortedSet priceQuantityRelations
    Integer minQuantity

    static hasMany = [priceQuantityRelations: PriceQuantityRelation]

    static constraints = {
        chapter nullable: true
        imageUrl nullable: true
        minQuantity min: 1
    }

    static mapping = {
        priceQuantityRelations sort: 'quantity'
    }
}