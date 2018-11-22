const path = require('path');

module.exports = {
	entry: ['babel-polyfill', './src/index.js'],
	output: {
		path: path.resolve(__dirname, '..', 'static'),
		filename: 'index.js',
		chunkFilename: '[name].js'
	},
	optimization: {
		namedChunks: true, // Allow for naming chunks (disabled by default in production mode)
		splitChunks: { // Split everything from node modules into vendors chunk
			cacheGroups: {
				vendors: {
					test: /[\\/]node_modules[\\/]/,
					chunks: 'initial',
					name: 'vendors'
				}
			}
		}
	},
	module: {
		rules: [{
			test: /\.js$/,
			exclude: /.*node_modules\/((?!pioneer-(js|scripts)).)*$/,
			use: {
				loader: 'babel-loader',
				options: {
					presets: ['env']
				}
			}
		},
		{
			test: /\.css$/,
			use: [
				'css-loader'
			]
		},
		{
			test: /\.(png|jpg|gif|svg|eot|otf|ttf|woff|woff2)$/,
			use: [
				{
					loader: 'url-loader',
					options: {
						limit: 10000
					}
				}
			]
		}]
	},
	resolve: {
		symlinks: false
	}
};
