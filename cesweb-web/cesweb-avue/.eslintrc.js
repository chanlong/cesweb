module.exports = {
  root: true,
  parserOptions: {
    parser: 'babel-eslint',
    sourceType: 'module'
  },
  env: {
    browser: true,
    node: true,
    es6: true
  },
  extends: ['plugin:vue/recommended', 'eslint:recommended'],
  rules: {
    'vue/max-attributes-per-line': [0, {}],
    'vue/html-indent': 'off',
    'vue/html-self-closing': [0, {}],
    'vue/html-closing-bracket-newline': [0, {}],
    'vue/html-closing-bracket-spacing': [0, {}],
    'vue/name-property-casing': [0, 'PascalCase'],
    'vue/multiline-html-element-content-newline': 'off',
    'vue/singleline-html-element-content-newline': 'off',
    'vue/no-unused-vars': [0, {}],
    'vue/no-parsing-error': [0, {}],
    'vue/attributes-order': [0, {}],
    'vue/order-in-components': [0, {}],
    'vue/valid-v-bind': [0, {}],
    'vue/mustache-interpolation-spacing': 0,
    'no-eval': 0, //禁止使用eval
    'no-undef': 2, //不能有未定义的变量
    'no-console': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    'no-unused-vars': [2, { 'vars': 'all', 'args': 'none' }] //不能有声明后未被使用的变量或参数
  }
}
