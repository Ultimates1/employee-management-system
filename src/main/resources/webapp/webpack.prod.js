const merge = require('webpack-merge');
const common = require('./webpack.config.js');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');
const OptimizeCSSAssetsPlugin = require('optimize-css-assets-webpack-plugin');

module.exports = merge(common, {
	mode: 'production',
	optimization: {
		minimizer: [
			new UglifyJsPlugin({
				cache: true,
				parallel: true,
				sourceMap: false // set to true if you want JS source maps
			}),
			new OptimizeCSSAssetsPlugin({})
		]
	},
	plugins: [
	]
});
