package org.gr8ladies

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CartController {

    def cartService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Cart.list(params), model:[cartCount: Cart.count()]
    }

    def show() {
        respond cartService.getCart(session.id), model: [totalPrice: cartService.getTotalPrice(session.id)]
    }

    def create() {
        respond new Cart(params)
    }

    def add(){
        cartService.addToCart(session.id, params.priceQuantityRelation.id)
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

        cartService.deleteCart(session.id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cart.label', default: 'Cart'), session.id])
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