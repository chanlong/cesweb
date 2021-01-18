/**
 * 通用 CRUD 请求
 */
import request from '@/router/router-axios'

const GlobalRequest = function(url) {
  this.serverUrl = url
}

GlobalRequest.server = function(url) {
  var gr = new GlobalRequest(url)

  /** 新增 */
  gr.create = function (data) {
    return request({
      url: this.serverUrl,
      method: 'post',
      data: data
    })
  }

  /** 修改 */
  gr.update = function (data) {
    return request({
      url: this.serverUrl,
      method: 'put',
      data: data
    })
  }

  /** 删除 */
  gr.delete = function (id) {
    return request({
      url: `${this.serverUrl}/${id}`,
      method: 'delete'
    })
  }

  /** 详情 */
  gr.fetchObj = function (id) {
    return request({
      url: `${this.serverUrl}/${id}`,
      method: 'get'
    })
  }

  /** post */
  gr.post = function (varible) {
    var url = `${this.serverUrl}`
    var option = {
      url: url,
      method: 'post'
    }
    if (varible && varible instanceof Object) {
      option['data'] = varible
    } else if (varible) {
      option['url'] = `${url}/${varible || ''}`
    }
    return request(option)
  }
  
  /** 提交后下载压缩包 */
  gr.postDownload = function (data) {
    return request({
      url: `${this.serverUrl}`,
      method: 'post',
      responseType: 'arraybuffer',
      data: data
    }).then((response) => { // 处理返回的文件流
      const blob = new Blob([response.data], {type: 'application/zip'})
      const filename = data.tableName + '.zip'
      const link = document.createElement('a')
      link.href = URL.createObjectURL(blob)
      link.download = filename
      document.body.appendChild(link)
      link.click()
      window.setTimeout(function () {
        URL.revokeObjectURL(blob)
        document.body.removeChild(link)
      }, 0)
    })
  }

  /** put */
  gr.put = function (varible) {
    var url = `${this.serverUrl}`
    var option = {
      url: url,
      method: 'put'
    }
    if (varible && varible instanceof Object) {
      option['data'] = varible
    } else if (varible && varible instanceof Array) {
      varible.forEach((item, i) => { option['url'] += `/${item}` })
    } else if (varible) {
      option['url'] += `/${varible || ''}`
    }
    return request(option)
  }

  /** 查询 */
  gr.fetch = function (query) {
    return request({
      url: `${this.serverUrl}`,
      method: 'get',
      params: query
    })
  }

  /** 查询（所有） */
  gr.fetchAll = function (query) {
    return request({
      url: `${this.serverUrl}/all`,
      method: 'get',
      params: query
    })
  }

  /** 查询（所有） */
  gr.fetchList = function (query) {
    return request({
      url: `${this.serverUrl}/list`,
      method: 'get',
      params: query
    })
  }

  /** 查询（分页） */
  gr.fetchPage = function (query) {
    return request({
      url: `${this.serverUrl}/page`,
      method: 'get',
      params: query
    })
  }

  /** 查询（树） */
  gr.fetchTree = function (query) {
    return request({
      url: `${this.serverUrl}/tree`,
      method: 'get',
      params: query
    })
  }

  /** 查询（Path Varible） */
  gr.fetchPath = function (varible) {
    return request({
      url: `${this.serverUrl}/${varible || ''}`,
      method: 'get'
    })
  }

  /** 同步查询 */
  gr.fetchSync = async function () {
    return await request({
      url: `${this.serverUrl}`,
      method: 'get'
    }).then(response => response.data)
  }
  
  return gr
}

export default GlobalRequest
