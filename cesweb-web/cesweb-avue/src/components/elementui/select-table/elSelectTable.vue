<template>
  <div>
    <el-select v-model="selected" multiple clearable filterable allow-create default-first-option @focus="focus" @blur="blur">
      <template slot="empty">
        <el-card :body-style="{ padding: '0px' }">
          <el-container>
            <el-header>
              <div style="display: flex; padding: 20px 0 0; align-items: center;">
                <el-form :model="queryform" inline>
                  <template v-for="(item, index) in columns">
                    <el-form-item :label="item.label" :key="index" v-if="item.search">
                      <template v-if="item.type === 'treeselect'">
                        <treeselect v-model="queryform[item.prop]"
                                    :auto-load-root-options="false"
                                    :options="item.options"
                                    :normalizer="normalizer"
                                    :load-options="item.onLoad"
                                    placeholder="选择部门"
                                    no-options-text="暂无数据" style="max-width: 200px;"/>
                      </template>

                      <template v-else>
                        <el-input v-model="queryform[item.prop]" clearable />
                      </template>
                    </el-form-item>
                  </template>

                  <el-form-item>
                    <el-button type="primary">查询</el-button>
                    <el-button>重置</el-button>
                  </el-form-item>
                </el-form>
              </div>
            </el-header>

            <el-main>
              <el-table :data="rows" @row-click="handleRowClick">
                <el-table-column align="center" width="60">
                  <template slot="header" slot-scope="scope"></template>
                  <template slot-scope="scope">
                    <el-tag v-if="isSelected(scope.row)" color="transparent"><i class="el-icon-check"></i></el-tag>
                  </template>
                </el-table-column>

                <template v-for="(item, index) in columns">
                  <el-table-column v-if="!item.hidden" :key="index"
                                                       :align="item.align ? item.align : 'center'"
                                                       :header-align="item.headerAlign ? item.headerAlign : 'center'"
                                                       :label="item.label"
                                                       :prop="item.prop" />
                </template>
              </el-table>
              <div style="display: flex; justify-content: flex-end; padding: 5px 0; background: linear-gradient(to bottom, white 0%, #fafafa 50%, whitesmoke 100%);">
                <el-pagination layout="total, sizes, prev, pager, next, jumper"
                               :total="crudPage.total"
                               :page-size="crudPage.pageSize"
                               :current-page="crudPage.currentPage"
                               @size-change="sizeChange"
                               @current-change="currentChange">
                </el-pagination>
              </div>
            </el-main>
          </el-container>
        </el-card>
      </template>
    </el-select>
  </div>

</template>

<script>
  import { pageOption } from '@/const/global/option'
  import arrayUtil from './util/arrayUtil'
  import Treeselect from "@riophae/vue-treeselect"
  import "@riophae/vue-treeselect/dist/vue-treeselect.css"

  export default {
    name: 'ElSelectTable',
    components: {
      Treeselect
    },
    props: {
      rows: {
        type: Array,
        default: () => {
          return []
        }
      },
      value: {
        type: String,
        default: ''
      },
      columns: {
        type: Array,
        default: () => {
          return []
        }
      }
    },
    data() {
      return {
        isShow: false,
        selected: [],
        crudPage: pageOption,
        queryform: {},
        deptOptions: null
      }
    },
    computed: {

    },
    watch: {
      rows: {
        deep: true,
        immediate: true,
        handler(newVal, oldVal) {
          if (newVal && newVal.length && this.value) {
            this.value.split(',').forEach(item => {
              let user = arrayUtil.get(this.rows, 'userId', item)
              if (user && !this.selected.includes(user.name)) {
                this.selected.push(user.name)
              }
            })
          }
        }
      },
      value(newVal, oldVal) {
        if (this.rows && this.rows.length) {
          newVal.split(',').forEach(item => {
            let user = arrayUtil.get(this.rows, 'userId', item)
            if (user && !this.selected.includes(user.name)) {
              this.selected.push(user.name)
            }
          })
        }
      },
      selected(newVal, oldVal) {
        let remove = arrayUtil.getRemove(newVal, oldVal)
        if (remove) this.$emit('onRemove', remove)
      }
    },
    created() {
      this.init();
    },
    mounted() {
      this.$emit('onLoad', this.crudPage)
    },
    methods: {
      init() {
        this.columns.forEach((n, i) => this.queryform[n.prop] = null)
      },
      blur(event) {

      },
      focus(event) {
        this.isShow = true
      },
      sizeChange() {

      },
      isSelected(row) {
        return arrayUtil.includes(this.value, 'userId', row)
      },
      currentChange() {

      },
      handleRowClick(row, column, event) {
        this.$emit('onSelect', row)
      },
      /** 转换数据结构 */
      normalizer(node) {
        // 去除叶子节点的children属性
        if (node.children && !node.children.length) {
          delete node.children
        }
        return {
          id: node.id,
          label: node.name,
          children: node.children
        }
      }
    }
  }
</script>

<style lang="scss">
  .vue-treeselect__x {
    margin-right: 5px;
  }
</style>
