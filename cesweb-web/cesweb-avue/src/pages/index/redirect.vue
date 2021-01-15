<script>
  export default {
    beforeCreate() {
      this.$router.replace({ path: this.$route.query.nextPath })
    },
    render: function(h) {
      return h()
    }
  }
</script>
