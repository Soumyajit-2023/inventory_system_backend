org.springframework.cloud.contract.spec.Contract.make {
    description("Should create an inventory item")
    request {
        method 'POST'
        url '/inventory'
        body(
            name: 'Widget',
            quantity: 100
        )
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        body(
            id: anyNumber(),
            name: 'Widget',
            quantity: 100
        )
        headers {
            contentType(applicationJson())
        }
    }
}
