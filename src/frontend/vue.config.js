const path = require('path');

module.exports = {
    outputDir: path.resolve(__dirname, "../" + "main/resources/static"),
    devServer : {
        proxy : {
            '/api' :{
                target: 'http://14.32.18.239:8080',
                ws: true,
                changeOrigin: true,
                // pathRewrite: { '^/api' : '' }
            }
        }
    }
}