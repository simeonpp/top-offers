'use strict';

var applicationConfig = require('../config.js').application;
var connection = require('../dataProvider').getConnection();

module.exports = {
    images: function(request, reply) {
        let filename = request.params.filename; 
        reply.file(`${applicationConfig.uploadsFolder}${filename}`);
    }
};