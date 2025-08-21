// org.springframework.cloud.contract.spec.Contract.make {
//     description("Should place an order")
//     request {
//         method 'POST'
//         url '/orders'
//         body(
//             customerId: 1L,
//             itemId: 10L,
//             quantity: 2
//         )
//         headers {
//             contentType(applicationJson())
//         }
//     }
//     response {
//         status 200
//         body(
//             id: 100L,
//             customer: [
//                 id: 1L,
//                 name: 'Alice'
//             ],
//             item: [
//                 id: 10L,
//                 name: 'Widget',
//                 quantity: 98
//             ],
//             quantity: 2,
//             status: 'PLACED'
//         )
//         headers {
//             contentType(applicationJson())
//         }
//     }
// }
