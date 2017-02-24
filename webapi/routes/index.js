var loginRoutes = require('./login.js');
var registerRoutes = require('./register');
var productsRoutes = require('./products.js');
var ordersRoutes = require('./orders.js');
var profileRoutes = require('./profile.js');

module.exports = {
    login: loginRoutes,
    register: registerRoutes,
    products: productsRoutes,
    orders: ordersRoutes,
    profile: profileRoutes
};
