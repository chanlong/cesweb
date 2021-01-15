<template>
  <basic-container>
    <activiti-bpmn :id="id" :process-key="key"
                            :process-name="name"
                            :process-category="category"
                            @before="getBpmnXml"/>
  </basic-container>
</template>

<script>
  import GlobalRequest from '@/api/crud'
  import activitiBpmn from '@/components/process-designer/editor'

  export default {
    data() {
      return {
        id: '',
        key: '',
        name: '',
        category: '',
      }
    },
    components: {
      activitiBpmn
    },
    created() {
      this.id = this.$route.params.id
      this.key = this.$route.query.key
      this.name = this.$route.query.name
      this.category = this.$route.query.category
    },
    methods: {
      async getBpmnXml() {
        const that = this
        GlobalRequest.server(`/act/model/${this.id}/bpmnxml`).fetch().then(result => {
          that.$event.$emit('getBpmnXml', result.data.data)
        }).catch(e => {
          that.$event.$emit('getBpmnXml')
        })
      }
    }
  }
</script>
