// 基础路径 注意发布之前要先修改这里
const apiUrl = 'http://gateway.chanlong.online:9999'
const baseUrl = '/'
const CompressionWebpackPlugin = require('compression-webpack-plugin')
const productionGzipExtensions = ['js', 'css']
module.exports = {
  publicPath: baseUrl,
  lintOnSave: true,
  productionSourceMap: false,
  chainWebpack: (config) => {
    const entry = config.entry('app')
    entry.add('babel-polyfill').end()
    entry.add('classlist-polyfill').end()
    //entry.add('@/mocks').end()
  },
  // eslint-disable-next-line
  configureWebpack: (config) => {
    if (process.env.NODE_ENV === 'production') {
      // 仅在生产环境下启用该配置
      return {
        plugins: [
          new CompressionWebpackPlugin({
            filename: '[path].gz[query]',
            algorithm: 'gzip',
            test: new RegExp('\\.(' + productionGzipExtensions.join('|') + ')$'),
            threshold: 1024, // 只有大小大于该值的资源会被处理,当前配置为对于超过1k的数据进行处理，不足1k的可能会越压缩越大
            minRatio: 0.99, // 只有压缩率小于这个值的资源才会被处理
            deleteOriginalAssets: true // 删除原文件
          })
        ]
      }
    }
  },
  transpileDependencies: [
    'vue-echarts',
    'resize-detector'
  ],
  // 配置转发代理
  devServer: {
    hot: true,      // 开启热更新
    open: true,     // 默认打开浏览器
    overlay: true,  // 开启报错提示显示在浏览器遮罩层
    proxy: {
        '/api': {
          target: apiUrl,
          changeOrigin: true,
          pathRewrite: {
            '^/api': ''
          }
        }
    }
  }
}
