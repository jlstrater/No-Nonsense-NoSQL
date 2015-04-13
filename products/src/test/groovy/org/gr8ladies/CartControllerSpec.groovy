package org.gr8ladies



import grails.test.mixin.*
import spock.lang.*

@TestFor(CartController)
@Mock(Cart)
class CartControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.cartList
            model.cartCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.cart!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def cart = new Cart()
            cart.validate()
            controller.save(cart)

        then:"The create view is rendered again with the correct model"
            model.cart!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            cart = new Cart(params)

            controller.save(cart)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/cart/show/1'
            controller.flash.message != null
            Cart.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def cart = new Cart(params)
            controller.show(cart)

        then:"A model is populated containing the domain instance"
            model.cart == cart
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def cart = new Cart(params)
            controller.edit(cart)

        then:"A model is populated containing the domain instance"
            model.cart == cart
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/cart/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def cart = new Cart()
            cart.validate()
            controller.update(cart)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.cart == cart

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            cart = new Cart(params).save(flush: true)
            controller.update(cart)

        then:"A redirect is issued to the show action"
            cart != null
            response.redirectedUrl == "/cart/show/$cart.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/cart/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def cart = new Cart(params).save(flush: true)

        then:"It exists"
            Cart.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(cart)

        then:"The instance is deleted"
            Cart.count() == 0
            response.redirectedUrl == '/cart/index'
            flash.message != null
    }
}