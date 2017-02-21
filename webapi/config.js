const application = {
    host: 'localhost',
    port: '8000',
    env: 'prod', // [dev, prod],
    apiUrl: '/api/'
}

const db = {
    host: 'localhost',
    database: 'my_orders',
    user: 'root',
    password: '123',
    port: application.env == 'dev' ? '4040' : '3306' // 3306
}

module.exports = {
    application,
    db
};
