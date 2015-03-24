package org.gr8ladies

class Product {

    String name
    String vendorUrl
    String chapter
    String imageUrl

    static hasMany = [priceQuantityRelations: PriceQuantityRelation]

    static constraints = {
        chapter nullable: true
        vendorUrl url: true
        imageUrl nullable: true
    }
}