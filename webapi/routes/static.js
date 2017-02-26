var applicationConfig = require('../config.js').application;

var imageWithFilenameUrl = applicationConfig.imagesUrl + '{filename}';

module.exports = {
    images: function(server, handler) {
        server.route({
            method: 'GET',
            path: imageWithFilenameUrl,
            handler: handler
        });
    }
}
