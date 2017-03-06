const application = {
    host: '192.168.0.103',
    port: '8000',
    env: 'prod', // [dev, prod],
    apiUrl: '/api/',
    filesUrl: '/static/',
    imagesUrl: '/images/',
    staticFolder: './public/static/',
    uploadsFolder: './public/uploads/',
};

const db = {
    host: 'localhost',
    database: 'my_orders',
    user: 'root',
    password: '123',
    port: application.env === 'dev' ? '4040' : '3306' // 3306
};

module.exports = {
    application,
    db
};
