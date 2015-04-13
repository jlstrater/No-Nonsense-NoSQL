package org.gr8ladies

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PriceQuantityRelationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PriceQuantityRelation.list(params), model:[priceQuantityRelationCount: PriceQuantityRelation.count()]
    }

    def show(PriceQuantityRelation priceQuantityRelation) {
        respond priceQuantityRelation
    }

    def create() {
        respond new PriceQuantityRelation(params)
    }

    @Transactional
    def save(PriceQuantityRelation priceQuantityRelation) {
        if (priceQuantityRelation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (priceQuantityRelation.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond priceQuantityRelation.errors, view:'create'
            return
        }

        priceQuantityRelation.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'priceQuantityRelation.label', default: 'PriceQuantityRelation'), priceQuantityRelation.id])
                redirect priceQuantityRelation
            }
            '*' { respond priceQuantityRelation, [status: CREATED] }
        }
    }

    def edit(PriceQuantityRelation priceQuantityRelation) {
        respond priceQuantityRelation
    }

    @Transactional
    def update(PriceQuantityRelation priceQuantityRelation) {
        if (priceQuantityRelation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (priceQuantityRelation.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond priceQuantityRelation.errors, view:'edit'
            return
        }

        priceQuantityRelation.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'priceQuantityRelation.label', default: 'PriceQuantityRelation'), priceQuantityRelation.id])
                redirect priceQuantityRelation
            }
            '*'{ respond priceQuantityRelation, [status: OK] }
        }
    }

    @Transactional
    def delete(id) {
        PriceQuantityRelation priceQuantityRelation = PriceQuantityRelation.get(id)
        Product product = priceQuantityRelation.product
        if (priceQuantityRelation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        priceQuantityRelation.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'priceQuantityRelation.label', default: 'PriceQuantityRelation'), priceQuantityRelation.id])
                redirect action:"index", method:"GET"
            }
            respond controller: "product", action: "show", id:product.id
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'priceQuantityRelation.label', default: 'PriceQuantityRelation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}