var loginRoutes = require('./login.js');
var productsRoutes = require('./products.js');
var ordersRoutes = require('./orders.js');
var profileRoutes = require('./profile.js');

module.exports = {
    login: loginRoutes,
    products: productsRoutes,
    orders: ordersRoutes,
    profile: profileRoutes
};
