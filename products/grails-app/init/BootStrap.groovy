import org.gr8ladies.Product
import org.gr8ladies.PriceQuantityRelation
import org.gr8ladies.Vendor

import javax.servlet.ServletContext

class BootStrap {
    def init = { ServletContext ctx ->
        environments {
            development {
                if (!Product.count()) {
                    Vendor vendor1 = new Vendor(name: 'Zazzle', url: 'http://zazzle.com/')
                    PriceQuantityRelation priceQuantityRelation1 = new PriceQuantityRelation(price: 4.95, unitPrice: 0.2475, quantity: 20)
                    PriceQuantityRelation priceQuantityRelation2 = new PriceQuantityRelation(price: 9.40, unitPrice: 0.235, quantity: 40)

                    Product product1 = new Product(name: 'Gr8Ladies International Stickers',
                            vendor: vendor1,
                            imageUrl: "ProductImages/Gr8LadiesStickerSheetInternational.jpg",
                            vendorUrlPath: 'gr8ladies_international_stickers_november_logo-217827169581818597',
                            minQuantity: 20,
                            priceQuantityRelations: [priceQuantityRelation1, priceQuantityRelation2]

                    ).save(failOnError: true)

                    Product product2 = new Product(name: 'Gr8Ladies Minneapolis Stickers',
                            vendor: vendor1,
                            chapter: 'Minneapolis',
                            imageUrl: "ProductImages/Gr8LadiesStickerSheetMinneapolis.jpg",
                            vendorUrlPath: 'gr8ladies_msp_stickers_november_logo-217065416894495516',
                            minQuantity: 20,
                            priceQuantityRelations: [ new PriceQuantityRelation(price: 4.95, unitPrice: 0.2475, quantity: 20),
                                    new PriceQuantityRelation(price: 9.40, unitPrice: 0.235, quantity: 40)]
                    ).save(failOnError: true)


                    Vendor vendor2 = new Vendor(name: 'Design-A-Shirt', url: 'http://www.designashirt.com/')

                    Product product3 = new Product(name: 'Gr8Ladies T-shirts',
                            vendor: vendor2,
                            vendorUrlPath: 'gr8ladies-t-shirt/729796/preview',
                            minQuantity: 1,
                            priceQuantityRelations: [new PriceQuantityRelation(price: 24.78, unitPrice: 24.78, quantity: 1),
                                    new PriceQuantityRelation(price: 41.90, unitPrice: 20.95, quantity: 2),
                                    new PriceQuantityRelation(price: 58.14, unitPrice: 19.38,quantity: 3)]
                    ).save(failOnError: true)

                }
            }
        }

        def destroy = {
            environments {
                development {
                }
            }
        }
    }
}
