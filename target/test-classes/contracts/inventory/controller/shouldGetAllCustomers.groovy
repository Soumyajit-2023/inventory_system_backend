// org.springframework.cloud.contract.spec.Contract.make {
//     description("Should return all customers (empty or non-empty list)")
//     request {
//         method 'GET'
//         url '/customers'
//     }
//     response {
//         status 200
//         body(
//             $(consumer(optional([
//                 $(consumer([
//                     id: anyNumber(),
//                     name: anyNonBlankString()
//                 ])),
//                 $(consumer([
//                     id: anyNumber(),
//                     name: anyNonBlankString()
//                 ]))
//             ])), producer([
//                 [id: 1L, name: 'Alice'],
//                 [id: 2L, name: 'Bob']
//             ]))
//         )
//         headers {
//             contentType(applicationJson())
//         }
//     }
// }
