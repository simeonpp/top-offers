'use strict';
var connection = require('../dataProvider').getConnection();
var helpers = require('../helpers.js');

module.exports = {
    getAllMy: function(request, reply) {
        console.log('featching my orders...');
        let headers = helpers.parseRequestHeader(request);

        let query = `SELECT o.id, o.quantity, o.singlePrice, o.totalPrice, o.dateOrdered, o.status, p.title as productTitle, p.imageIdentifier as productImageIdentifier` +
                        `, p.description as productDescription, u.firstName as productSellerFirstName, u.lastName as productSellerLastName, u.address as productSellerAddress,` +
                        `u.phone as productSellerPhone, u.username as productSellerUsername ` +
                    `FROM orders o ` +
                    `INNER JOIN products p ON p.id = o.productId ` +
                    `INNER JOIN users u on u.id = p.sellerId `;

        if (headers.role === 'seller') {
            query += `WHERE p.sellerId = ${headers.id}`;
        } else {
            query += `WHERE o.buyerId = ${headers.id}`
        }

        connection.query(query)
            .then(function(dataResult) {
                reply(dataResult);
            });
    },
    getById: function(request, reply) {
        console.log('get order by id...');
        let headers = helpers.parseRequestHeader(request);
        let orderId = request.params.orderId;

        let query = `SELECT o.id, o.quantity, o.singlePrice, o.totalPrice, o.dateOrdered, o.status, p.title as productTitle, p.imageIdentifier as productImageIdentifier` +
                        `, p.description as productDescription, u.firstName as productSellerFirstName, u.lastName as productSellerLastName, u.address as productSellerAddress,` +
                        `u.phone as productSellerPhone, u.username as productSellerUsername ` +
                    `FROM orders o ` +
                    `INNER JOIN products p ON p.id = o.productId ` +
                    `INNER JOIN users u on u.id = p.sellerId `;

        if (headers.role === 'seller') {
            console.log('as seller');
            query += `WHERE p.sellerId = ${headers.id} AND o.id = ${orderId}`;
        } else {
            console.log('as buyer');
            query += `WHERE o.buyerId = ${headers.id} AND o.id = ${orderId}`
        }

        connection.query(query)
            .then(function(dataResult) {
                if (dataResult.length === 1) {
                    reply(dataResult[0]);
                } else {
                    reply({});
                }
            })
    },
    create: function(request, reply) {
        console.log('creating new order');
        let headers = helpers.parseRequestHeader(request);
        let productId = request.payload.productId;
        let quantity = request.payload.quantity;

        if (headers.role === 'buyer') {
            let productQuery = 'SELECT * FROM products WHERE id = ${productId}';
            connection.query(productQuery)
                .then(function(dataResult) {
                    if (dataResult.length > 0) {
                        let product = dataResult[0];
                        let orderQuery = 'INSERT INTO orders (productId, buyerId, quantity, singlePrice, totalPrice) ' +
                                          'VALUES (${product.id}, ${headers.id}, ${quantity}, ${product.price}, ${product.price * quantity});';
                        return connection.query(orderQuery);
                    } else {
                        reply({
                            error: {
                                type: 'order.productNotFound',
                                message: 'Product not found'
                            }
                        });
                        return;
                    }
                })
                .then(function(orderQuery) {
                    reply({
                        success: true
                    });
                });
        } else {
            reply({
                error: {
                    type: 'order.noPermissions',
                    message: 'You have no permissions to make an order'
                }
            });
        }
    }
};
