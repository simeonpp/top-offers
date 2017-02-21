'use strict';
var config = require('./config');
var dataProvider = require('./dataProvider');
var routes = require('./routes');

var mysql = require('promise-mysql');
const Hapi = require('hapi');

// Init DataProvider connection
dataProvider.init
    .then(function(dataProviderConnection) {
        var handlers = require('./handlers');
        // Init server
        const server = new Hapi.Server();
        server.connection({ port: config.application.port, host: config.application.host });

        // Register routes
        routes.login(server, handlers.login);
            // products
        routes.products.getAll(server, handlers.products.getAll);
        routes.products.getById(server, handlers.products.getById);
        routes.products.create(server, handlers.products.create);
        routes.products.delete(server, handlers.products.delete);
        routes.products.edit(server, handlers.products.edit);
            // orders
        routes.orders.getAllMy(server, handlers.orders.getAllMy);
        routes.orders.getById(server, handlers.orders.getById);
        routes.orders.create(server, handlers.orders.create);

        // Start server
        server.start((err) => {
            if (err) {
                throw err;
            }
            console.log(`Server running at: ${server.info.uri}`);
        });
    });
