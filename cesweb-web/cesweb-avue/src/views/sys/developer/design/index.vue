<template>
  <div class="webapp">
    <basic-container>
      <avue-form-design :options="option" @submit="handleSubmit"></avue-form-design>
    </basic-container>
  </div>
</template>

<script>
import AvueUeditor from "avue-plugin-ueditor"
import GlobalRequest from '@/api/crud'
import { validateNull } from "@/utils/validate"

export default {
  comments: {
    AvueUeditor,
  },
  data() {
    return {
      option: {
        column: [],
      },
    };
  },
  created() {
    this.getFormInfo();
  },
  methods: {
    handleSubmit(json) {
      let params = this.$route.query;
      if (validateNull(params)) {
        return false;
      }
      let result = JSON.stringify(json);
      let formInfo = result,
          tableName = params.tableName,
          dsId = params.dsName;

      GlobalRequest.server('/develope/form').post(Object.assign({formInfo, tableName, dsId})).then((response) => {
        this.$message.success("生成并保存成功");
      });
    },
    getFormInfo() {
      let params = this.$route.query;
      if (validateNull(params)) {
        return false;
      }

      GlobalRequest.server('/develope/form/info').fetch({tableName: params.tableName, dsName: params.dsName}).then((response) => {
        if (!validateNull(response.data.data)) {
          this.option = JSON.parse(response.data.data);
        }
      });
    },
  },
};
</script>
<style lang="scss">
.webapp {
  background-color: #fff;
  position: relative;
  width: 100%;
  height: 100%;

  .form-designer {
    height: 800px;
    overflow-y: scroll;
  }

  .form-designer .widget-config-container .el-tabs__header {
    position: relative;
  }
}
</style>
