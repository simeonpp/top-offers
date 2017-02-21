var loginRoutes = require('./login.js');
var productsRoutes = require('./products.js');
var ordersRoutes = require('./orders.js');

module.exports = {
    login: loginRoutes,
    products: productsRoutes,
    orders: ordersRoutes
}
