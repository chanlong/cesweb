import Vue from 'vue'
import VueI18n from 'vue-i18n'
import Avue from '@smallwei/avue'
import elementEnLocale from 'element-ui/lib/locale/lang/en'
import elementZhLocale from 'element-ui/lib/locale/lang/zh-CN'
import enLocale from './en'
import zhLocale from './zh'
import { getStore } from '@/utils/store'

Vue.use(VueI18n)
const messages = {
  en: {
    ...enLocale,
    ...elementEnLocale,
    ...Avue.locale.en,
  },
  zh: {
    ...zhLocale,
    ...elementZhLocale,
    ...Avue.locale.zh,
  }
}

const i18n = new VueI18n({
  locale: getStore({ name: 'language' }) || 'zh',
  messages
})

export default i18n
