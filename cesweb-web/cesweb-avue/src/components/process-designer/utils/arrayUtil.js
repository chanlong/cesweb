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
      return array.split(',').filter(item => item === obj[key]).length > 0
    } else if (array) {
      return array.filter(item => (item instanceof Object ? item[key] === obj[key] : item === obj[key])).length > 0
    } else {
      return false
    }
  },

  getRemove(array1, array2) {
    return array2.filter(val => !array1.includes(val)).pop()
  },
}
