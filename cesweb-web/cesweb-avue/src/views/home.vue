<template>
  <div>
    
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'home',
  computed: {
    ...mapGetters(['website'])
  }
}
</script>

<style scoped="scoped" lang="scss">
  .home {
    &__header {
      padding: 25px 40px;
      border-bottom: 1px solid #e8e8e8;
      background-color: #fff;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    &__info {
      display: flex;
      align-items: center;
      &-img {
        border-radius: 72px;
        display: block;
        width: 72px;
        height: 72px;
        img {
          width: 100%;
          height: 100%;
          display: block;
        }
      }
      &-content {
        position: relative;
        margin-left: 24px;
        color: rgba(0, 0, 0, 0.45);
        line-height: 22px;
      }
      &-title {
        font-size: 20px;
        line-height: 28px;
        font-weight: 500;
        color: rgba(0, 0, 0, 0.85);
        margin-bottom: 12px;
      }
      &-subtitle {
        position: relative;
        font-size: 14px;
        color: rgba(0, 0, 0, 0.45);
        line-height: 22px;
      }
    }
    &__extra {
      &-item {
        position: relative;
        padding: 0 32px;
        display: inline-block;
        &:last-child {
          &::after {
            display: none;
          }
        }
        &:after {
          background-color: #e8e8e8;
          position: absolute;
          top: 30px;
          right: 0;
          width: 1px;
          height: 40px;
          content: "";
        }
      }
      &-title {
        color: rgba(0, 0, 0, 0.45);
        font-size: 14px;
        line-height: 22px;
        margin-bottom: 4px;
      }
      &-subtitle {
        color: rgba(0, 0, 0, 0.85);
        font-size: 30px;
        line-height: 38px;
        margin: 0;
        span {
          color: rgba(0, 0, 0, 0.45);
          font-size: 20px;
        }
      }
    }
  }
</style>
