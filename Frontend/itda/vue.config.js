const fs = require('fs')
module.exports = {
    devServer: {
      https: true,
      https: {
          key: fs.readFileSync('./cert/private.key'),
          cert: fs.readFileSync('./cert/private.crt'),
          ca: fs.readFileSync('./cert/rootCA.pem')
      }
    },
  }