const webpack = require('webpack')
const ExtractTextPlugin = require('extract-text-webpack-plugin')

var environment = {
    'process.env':{
        NODE_ENV : JSON.stringify('production'),
        SERVICE_URL : JSON.stringify('https://mymoneyapp.herokuapp.com')
    }
}

module.exports = {
    entry: './src/index.jsx',
    output: {
        path: '../resources/public',
        filename: './appbundle.min.js'
    },
    devServer: {
        port: 8081,
        contentBase: '../resources/public',
    },
    resolve: {
        extensions: ['', '.js', '.jsx'],
        alias: {
            modules: __dirname + '/node_modules',
            jquery: 'modules/admin-lte/plugins/jQuery/jquery-2.2.3.min.js',
            bootstrap: 'modules/admin-lte/bootstrap/js/bootstrap.js'
        }
    },
    plugins: [ 
        new webpack.ProvidePlugin({
            $: 'jquery',
            jQuery: 'jquery',
            'window.jQuery': 'jquery'
        })
        ,new ExtractTextPlugin('app.css')
        ,new webpack.optimize.UglifyJsPlugin()
        ,new webpack.DefinePlugin(environment)
    ],
    module: {
        loaders: [{
            test: /.js[x]?$/,
            loader: 'babel-loader',
            exclude: /node_modules/,
            query: {
                presets: ['es2015', 'react'],
                plugins: ['transform-object-rest-spread']
            }
        }, {
            test: /\.css$/,
            loader: ExtractTextPlugin.extract('style-loader', 'css-loader')
        }, {
            test: /\.woff|.woff2|.ttf|.eot|.svg|.png|.jpg*.*$/,
            loader: 'file'
        }]
    }
}
