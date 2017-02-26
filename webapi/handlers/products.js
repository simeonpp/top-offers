'use strict';
var fs = require('fs');
var multiparty = require('multiparty');
const uuidV1 = require('uuid/v1');

var applicationConfig = require('../config.js').application;
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

        var imageIdentifier = uuidV1();
        var form = new multiparty.Form();
        form.parse(request.payload, function(error, fields, files) {
            if (error) {
                return reply(error);
            } else {
                fs.readFile(files.image[0].path, function(readFilerError, data) {
                    var originalFilename = files.image[0].originalFilename;
                    var imageExtension = originalFilename.split('.').pop();
                    var imageNameWithExtension = `${imageIdentifier}.${imageExtension}`;

                    // checkFileExist();
                    fs.writeFile(`${applicationConfig.uploadsFolder}${imageNameWithExtension}`, data, function(writeFileError) {
                        if (writeFileError) {
                            return reply(writeFileError);
                        } else {
                            var imageToUpload = fs.readFileSync(files.image[0].path);
                            var uploadedImage = fs.writeFileSync(applicationConfig.uploadsFolde + imageIdentifier)
                            // Image upload finishe

                            // SQL query
                            let headers = helpers.parseRequestHeader(request);
                            let title = fields.title[0];
                            let price = fields.price[0];
                            let quantity = fields.quantity[0];
                            let description = fields.description[0];

                            let query = 'INSERT INTO products (sellerId, title, price, quantity, imageIdentifier, description) ' +
                                        `VALUES (${headers.id}, '${title}', ${price}, ${quantity}, '${imageNameWithExtension}', '${description}');`;
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
                        }
                    });
                });
            }
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
        console.log('editing existing products...');
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

var upload = function(files, reply) {
    fs.readFile(files.file[0].path, function(err, data) {
        // checkFileExist();
        fs.writeFile(applicationConfig.uploadsFolder + files.file[0].originalFilename, data, function(error) {
            if (error) {
                return reply(err);
            } else {
                console.log('file uploaded');
                return reply({
                    success: true,
                    message: ''
                });
            }
        });
    });
};
