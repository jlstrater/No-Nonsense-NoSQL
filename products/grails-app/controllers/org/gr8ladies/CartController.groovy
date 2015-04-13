package org.gr8ladies

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CartController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Cart.list(params), model:[cartCount: Cart.count()]
    }

    def show() {
        respond Cart.findByUserSession(session.id) ?: new Cart(userSession: session.id).save(failOnError: true)
    }

    def create() {
        respond new Cart(params)
    }

    def add(){
        Cart cart = Cart.findByUserSession(session.id) ?: new Cart(userSession: session.id).save(failOnError: true)
        Product product = Product.get(params.product.id)
        if (! product) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.product.id])
            redirect controller: "product", action: "index"
        }

        PriceQuantityRelation priceQuantityRelation = product.priceQuantityRelations.find { it.quantity == params.quantity.toInteger()}

        if(!priceQuantityRelation) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cart.label', default: 'Cart'), params.id])
            redirect action: "index", method: "GET"
        }
        CartItem cartItem = new CartItem(cart: cart, priceQuantityRelation: priceQuantityRelation, product: product)
        cart.addToCartItems(cartItem)
        cart.save(failOnError: true)
        redirect controller: "product", action: "index"
    }

    @Transactional
    def save(Cart cart) {
        if (cart == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (cart.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond cart.errors, view:'create'
            return
        }

        cart.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cart.label', default: 'Cart'), cart.id])
                redirect cart
            }
            '*' { respond cart, [status: CREATED] }
        }
    }

    def edit(Cart cart) {
        respond cart
    }

    @Transactional
    def update(Cart cart) {
        if (cart == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (cart.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond cart.errors, view:'edit'
            return
        }

        cart.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'cart.label', default: 'Cart'), cart.id])
                redirect cart
            }
            '*'{ respond cart, [status: OK] }
        }
    }

    @Transactional
    def delete(Cart cart) {

        if (cart == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        cart.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cart.label', default: 'Cart'), cart.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cart.label', default: 'Cart'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}