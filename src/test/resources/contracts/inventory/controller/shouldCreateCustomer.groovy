org.springframework.cloud.contract.spec.Contract.make {
    description 'Should create a new customer'
    request {
        method 'POST'
        url '/customers'
        body(
            name: $(consumer('John Doe'), producer('John Doe')),
            
        )
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        body(
            id: $(producer(anyNumber())),
            name: fromRequest().body('name'),
        )
        headers {
            contentType(applicationJson())
        }
    }
}
