'use strict';

var applicationConfig = require('../config.js').application;
var productsUrl = applicationConfig.apiUrl + 'products';
var productUrl = applicationConfig.apiUrl + 'product';
var productWithIdUrl = applicationConfig.apiUrl + 'product/{productId}';

module.exports = {
    getAll: function(server, handler) {
        server.route({
            method: 'GET',
            path: productsUrl,
            handler: handler 
        });
    },
    getById: function(server, handler) {
        server.route({
            method: 'GET',
            path: productWithIdUrl,
            handler: handler 
        });
    },
    create: function(server, handler) {
        server.route({
            method: 'POST',
            path: productUrl,
            handler: handler 
        });
    },
    delete: function(server, handler) {
        server.route({
            method: 'DELETE',
            path: productWithIdUrl,
            handler: handler 
        });
    },
    edit: function(server, handler) {
        server.route({
            method: 'PUT',
            path: productWithIdUrl,
            handler: handler 
        });
    }
};
