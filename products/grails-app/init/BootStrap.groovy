import org.gr8ladies.Product
import org.gr8ladies.PriceQuantityRelation

class BootStrap {

    def init = { servletContext ->

        if (!Product.count()) {
            Product product1 = new Product(name: 'Gr8Ladies International Stickers',
                    vendorUrl: 'http://www.zazzle.com/gr8ladies_international_stickers_november_logo-217827169581818597',
                    imageUrl: "ProductImages/Gr8LadiesStickerSheetInternational.jpg"
            ).save(failOnError: true)

            new PriceQuantityRelation(price: 4.95, quantity: 20, product: product1).save(failOnError: true)
            new PriceQuantityRelation(price: 4.70, quantity: 40, product: product1).save(failOnError: true)

            Product product2 = new Product(name: 'Gr8Ladies Minneapolis Stickers',
                    vendorUrl: 'http://www.zazzle.com/gr8ladies_msp_stickers_november_logo-217065416894495516',
                    chapter: 'Minneapolis',
                    imageUrl: "ProductImages/Gr8LadiesStickerSheetMinneapolis.jpg"
            ).save(failOnError: true)

            new PriceQuantityRelation(price: 4.95, quantity: 20, product: product2).save(failOnError: true)
            new PriceQuantityRelation(price: 4.70, quantity: 40, product: product2).save(failOnError: true)

            Product product3 = new Product(name: 'Gr8Ladies T-shirts',
                    vendorUrl: 'http://www.designashirt.com/gr8ladies-t-shirt/729796/preview',
                    price: 24.78,
                    quantity: 1
            ).save(failOnError: true)

            new PriceQuantityRelation(price: 24.78, quantity: 1, product: product3).save(failOnError: true)
            new PriceQuantityRelation(price: 20.95, quantity: 2, product: product3).save(failOnError: true)
            new PriceQuantityRelation(price: 19.38, quantity: 3, product: product3).save(failOnError: true)

        }

        def destroy = {
        }
    }
}
