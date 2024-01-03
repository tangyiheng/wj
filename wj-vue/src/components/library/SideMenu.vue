<template>
    <!-- 1.触发 <el-menu> 组件的 @select 事件，执行 handleSelect 方法 -->
    <el-menu class="categories" default-active="0" @select="handleSelect" active-text-color="red">
        <el-menu-item index="0">
            <i class="el-icon-menu"></i>
            <span slot="title">全部</span>
        </el-menu-item>
        <el-menu-item index="1">
            <i class="el-icon-menu"></i>
            <span slot="title">文学</span>
        </el-menu-item>
        <el-menu-item index="2">
            <i class="el-icon-menu"></i>
            <span slot="title">流行</span>
        </el-menu-item>
        <el-menu-item index="3">
            <i class="el-icon-menu"></i>
            <span slot="title">文化</span>
        </el-menu-item>
        <el-menu-item index="4">
            <i class="el-icon-menu"></i>
            <span slot="title">生活</span>
        </el-menu-item>
        <el-menu-item index="5">
            <i class="el-icon-menu"></i>
            <span slot="title">经管</span>
        </el-menu-item>
        <el-menu-item index="6">
            <i class="el-icon-menu"></i>
            <span slot="title">科技</span>
        </el-menu-item>
    </el-menu>
</template>
  
<script>
export default {
    name: 'SideMenu',
    data() {
        return {
            cid: ''
        }
    },
    methods: {
        // 实现了点击分类引发查询事件
        // 2.handleSelect 方法触发 indexSelect 事件，并把 key，即 <el-menu-item> 标签的 index 属性的值赋给 data 中定义的属性，即分类码。
        handleSelect(key, keyPath) {
            this.cid = key
            // emit，即触发，在子组件中使用 $emit 方法，即可触发在父组件中定义的事件。
            this.$emit('indexSelect')
        }
    }
}
</script>
  
<style scoped>
.categories {
    position: fixed;
    margin-left: 50%;
    left: -600px;
    top: 100px;
    width: 150px;
}
</style>
  
  