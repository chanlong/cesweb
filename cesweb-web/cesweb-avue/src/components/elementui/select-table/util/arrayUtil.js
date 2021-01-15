export default {
  get(array, key, filter) {
    return array.filter(item => (item[key] === filter))[0]
  },

  join(array, key, sep = ',') {
    return array.map(item => item[key]).join(sep)
  },

  split(value, sep = ',') {
    return value.split(sep)
  },

  includes(array, key, obj) {
    if (array && typeof(array) === 'string') {
      array = array.split(',')
    }
    return array ? array.filter(item => item ===  obj[key]).length > 0 : false
  },

  getRemove(array1, array2) {
    return array2.filter(val => !array1.includes(val)).pop()
  },
}
