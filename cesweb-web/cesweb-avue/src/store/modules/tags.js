import website from '@/config/website'
import { diff } from '@/utils/util'
import { setStore, getStore } from '@/utils/store'

const isFirstPage = website.isFirstPage
const tagHome = website.fistPage
const tagObj = {
  meta: {}, // 额外参数
  group: [], // 分组
  label: '', // 标题名称
  value: '', // 标题的路径
  query: '', // 标题的参数
  params: '' // 标题的路径参数
}

// 处理首个标签
function setFistTag (list) {
  if (list.length === 1) {
    list[0].close = false
  } else {
    list.forEach(ele => {
      if (ele.value === tagHome.value && isFirstPage === false) {
        ele.close = false
      } else {
        ele.close = true
      }
    })
  }
}

export default {
  state: {
    tag: getStore({ name: 'tag' }) || tagObj,
    tagList: getStore({ name: 'tagList' }) || [],
    tagHome: tagHome
  },
  actions: {},
  mutations: {
    ADD_TAG: (state, action) => {
      state.tag = action
      setStore({ name: 'tag', content: state.tag })
      if (state.tagList.some(ele => diff(ele, action))) return
      state.tagList.push(action)
      setFistTag(state.tagList)
      setStore({ name: 'tagList', content: state.tagList })
    },
    DEL_TAG: (state, action) => {
      state.tagList = state.tagList.filter(item => { return !diff(item, action) })
      setFistTag(state.tagList)
      setStore({ name: 'tagList', content: state.tagList })
    },
    DEL_ALL_TAG: (state) => {
      state.tagList = [state.tagHome]
      setStore({ name: 'tagList', content: state.tagList })
    },
    DEL_TAG_OTHER: (state) => {
      state.tagList = state.tagList.filter(item => {
        if (item.value === state.tag.value) {
          return true
        } else if (!website.isFirstPage && item.value === website.fistPage.value) {
          return true
        }
      })
      setFistTag(state.tagList)
      setStore({ name: 'tagList', content: state.tagList })
    },
    SET_TAG_LIST: (state, tagList) => {
      state.tagList = tagList
      setStore({ name: 'tagList', content: state.tagList })
    }
  }
}
