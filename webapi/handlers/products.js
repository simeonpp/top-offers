'use strict';
var connection = require('../dataProvider').getConnection();
var helpers = require('../helpers.js');

module.exports = {
    getAll: function(request, reply) {
        console.log('featching products...');
        let headers = helpers.parseRequestHeader(request);

        let query = 'SELECT p.id, p.title, p.price, p.quantity, p.imageIdentifier, p.description, p.dateAdded, u.username as sellerUsername, u.firstName as sellerFirstName, u.lastName as sellerLastName ' +
                    'FROM products p ' +
                    'INNER JOIN users u ON u.id = p.sellerId ';
        if (headers.role === 'seller') {
            query += `WHERE p.sellerId = ${headers.id}`;
        }

        connection.query(query)
            .then(function(dataResult) {
                reply(dataResult);
            });
    },
    getById: function(request, reply) {
        console.log('get product by id...');
        let headers = helpers.parseRequestHeader(request);
        let productId = request.params.productId;

        let query = 'SELECT p.id, p.title, p.price, p.quantity, p.imageIdentifier, p.description, p.dateAdded, u.username as sellerUsername, u.firstName as sellerFirstName, u.lastName as sellerLastName ' +
                    'FROM products p ' +
                    'INNER JOIN users u ON u.id = p.sellerId ';
        if (headers.role === 'seller') {
            query += `WHERE p.id = ${productId} AND p.sellerId = ${headers.id}`;
        } else {
            query += `WHERE p.id = ${productId}`;
        }

        connection.query(query)
            .then(function(dataResult) {
                if (dataResult.length === 1) {
                    reply(dataResult[0]);
                } else {
                    reply({});
                }
            });
    },
    create: function(request, reply) {
        console.log('creating new product');
        let headers = helpers.parseRequestHeader(request);
        let title = request.payload.title;
        let price = request.payload.price;
        let quantity = request.payload.quantity;
        let imageIdentifier = null;
        let description = request.payload.description;

        let query = 'INSERT INTO products (sellerId, title, price, quantity, imageIdentifier, description) ' +
                    `VALUES (${headers.id}, '${title}', ${price}, ${quantity}, '${imageIdentifier}', '${description}');`;
        connection.query(query)
            .then(function(dataResult) {
                reply({
                    success: true,
                    title,
                    price,
                    quantity,
                    imageIdentifier,
                    description
                });
            });
    },
    delete: function(request, reply) {
        console.log('deleting existing product...');
        let headers = helpers.parseRequestHeader(request);
        let productId = request.params.productId;

        let query = `DELETE FROM products WHERE id = ${productId} AND sellerId = ${headers.id}`;
        connection.query(query)
            .then(function(dataResult) {
                if (dataResult && dataResult.affectedRows === 1) {
                    reply({
                        id: productId
                    });
                } else {
                    reply({
                        error: {
                            type: 'product.productNotFound',
                            message: 'Product was not found'
                        }
                    });
                }
            });
    },
    edit: function(request, reply) {
        console.log('editing new products');
        let headers = helpers.parseRequestHeader(request);
        let productId = request.params.productId;

        let title = request.payload.title;
        let price = request.payload.price;
        let quantity = request.payload.quantity;
        let imageIdentifier = null;
        let description = request.payload.description;

        let query = 'UPDATE products ' +
                    `SET title='${title}', price = ${price}, quantity = ${quantity}, imageIdentifier = '${imageIdentifier}', description = '${description}' ` +
                    `WHERE id = ${productId} AND sellerId = ${headers.id}`;
        connection.query(query)
            .then(function(dataResult) {
                if (dataResult && dataResult.affectedRows === 1) {
                    reply({ 
                        id: productId,
                        title,
                        price,
                        quantity,
                        imageIdentifier,
                        description
                    });
                } else {
                    reply({
                        error: {
                            type: 'product.productNotFound',
                            message: 'Product was not found'
                        }
                    });
                }
            });
    }
};
