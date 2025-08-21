// org.springframework.cloud.contract.spec.Contract.make {
//     description("Should return all inventory items")

//     request {
//         method 'GET'
//         url '/inventory'
//     }

//     response {
//         status 200
//         body(
//             $(consumer(optional(
//                 eachLike([
//                     id: $(consumer(anyNumber()), producer(10L)),
//                     name: $(consumer(anyNonBlankString()), producer("Widget")),
//                     quantity: $(consumer(anyNumber()), producer(100))
//                 ])
//             )),
//             producer([
//                 [
//                     id: 10L,
//                     name: 'Widget',
//                     quantity: 100
//                 ],
//                 [
//                     id: 11L,
//                     name: 'Gadget',
//                     quantity: 25
//                 ]
//             ]))
//         )
//         headers {
//             contentType(applicationJson())
//         }
//     }
// }
