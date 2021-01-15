import Mock from 'mockjs'

const Random = Mock.Random
const tables = [{
  id: Random.guid(),
  tableName: 'T_USER',
  tableComment: '用户信息表',
  tableCollation: 'utf8mb4_general_ci',
  engine: '',
  createTime: Random.datetime()
}]

export default ({ mock }) => {
  if (!mock) return

  Mock.mock('/gent/tables', 'get', () => {
    return {
      data: {
        rows: tables,
        total: 20,
        pageSize: 10
      }
    }
  })
}
