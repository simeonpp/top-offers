'use strict';
var loginHandler = require('./login.js');
var productsHandlers = require('./products.js');
var ordersHandlers = require('./orders.js');
var profileHandlers = require('./profile.js');

module.exports = {
    login: loginHandler,
    products: productsHandlers,
    orders: ordersHandlers,
    profile: profileHandlers
};
