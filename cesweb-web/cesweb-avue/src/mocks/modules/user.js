import Mock from 'mockjs'

export default ({ mock }) => {
  if (!mock) return

  // 用户登录
  Mock.mock('/user/login', 'post', { data: new Date().getTime() + '' })

  // 用户退出
  Mock.mock('/user/logout', 'get', { data: true })

  // 刷新token
  Mock.mock('/user/refesh', 'post', { data: new Date().getTime() + '' })

  // 获取用户信息
  Mock.mock('/user/getUserInfo', 'get', {
    data: {
      roles: 'admin',
      userInfo: {
        name: 'avue',
        username: 'admin',
        avatar: 'https://gitee.com/uploads/61/632261_smallweigit.jpg'
      },
      // 权限列表
      permission: [
        'sys_crud_btn_add',
        'sys_crud_btn_export',
        'sys_menu_btn_add',
        'sys_menu_btn_edit',
        'sys_menu_btn_del',
        'sys_role_btn1',
        'sys_role_btn2',
        'sys_role_btn3',
        'sys_role_btn4',
        'sys_role_btn5',
        'sys_role_btn6'
      ]
    }
  })

  // 获取模拟用户数据
  Mock.mock('/user/getTable', 'get', () => {
    const list = []
    for (let i = 0; i < 5; i++) {
      list.push(Mock.mock({
        id: '@increment',
        name: Mock.mock('@cname'),
        username: Mock.mock('@last'),
        type: [0, 2],
        checkbox: [0, 1],
        'number|0-100': 0,
        datetime: 1532932422071,
        'sex|0-1': 0,
        moreselect: [0, 1],
        grade: 0,
        address: Mock.mock('@cparagraph(1, 3)'),
        check: [1, 3, 4]
      }))
    }
    return {
      data: {
        total: 20,
        pageSize: 10,
        tableData: list
      }
    }
  })
}
