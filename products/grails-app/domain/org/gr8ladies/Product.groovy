package org.gr8ladies

import org.bson.types.ObjectId

class Product {
    ObjectId id
    String name
    Vendor vendor
    String chapter
    String imageUrl
    String vendorUrlPath
    Integer minQuantity

    static embedded = ['vendor', 'priceQuantityRelations']

    static hasMany = [priceQuantityRelations: PriceQuantityRelation]

    static constraints = {
        chapter nullable: true
        imageUrl nullable: true
        minQuantity min: 1
    }
}