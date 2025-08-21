// org.springframework.cloud.contract.spec.Contract.make {
//     description("Should return all orders for a customer")
//     request {
//         method 'GET'
//         url '/orders/1'
//     }
//     response {
//         status 200
//         body([
//             [
//                 id: 100L,
//                 customer: [
//                     id: 1L,
//                     name: 'Alice'
//                 ],
//                 item: [
//                     id: 10L,
//                     name: 'Widget',
//                     quantity: 98
//                 ],
//                 quantity: 2,
//                 status: 'PLACED'
//             ],
//             [
//                 id: 101L,
//                 customer: [
//                     id: 1L,
//                     name: 'Alice'
//                 ],
//                 item: [
//                     id: 11L,
//                     name: 'Gadget',
//                     quantity: 23
//                 ],
//                 quantity: 1,
//                 status: 'PLACED'
//             ]
//         ])
//         headers {
//             contentType(applicationJson())
//         }
//     }
// }
