package org.gr8ladies

class Vendor {

    String name
    String url

    static constraints = {
        url(url: true)
    }
}