<template>
    <el-container>
        <el-aside style="width: 200px;margin-top: 20px">
            <switch></switch>
            <!-- SideMenu 组件在 LibraryIndex 组件中作为一个 子组件 存在，是 LibraryIndex 组件的一部分。
                在它的标签中，我们用 ref 属性设置了一个引用名。 -->
            <!-- @indexSelect 为 listByCategory() 方法设置了触发事件 -->
            <SideMenu @indexSelect="listByCategory" ref="sideMenu"></SideMenu>
        </el-aside>
        <el-main>
            <books class="books-area" ref="booksArea"></books>
        </el-main>
    </el-container>
</template>
  
<script>
import SideMenu from './SideMenu'
import Books from './Books'

export default {
    name: 'AppLibrary',
    components: { Books, SideMenu },
    methods: {
        // 实现按分类查询
        // 3.父组件收到指令，执行事件对应的方法，即 listByCategory 方法
        listByCategory() {
            var _this = this
            // 通过 _this.refs.sideMenu 来引用侧面导航栏的实例，并获取它的 data 
            var cid = this.$refs.sideMenu.cid
            var url = 'categories/' + cid + '/books'
            // 点击左侧导航栏，向后端发送一个带有参数的 get 请求，然后同样是修改 data 里的数据以实现动态渲染
            // 4.发送请求，后端执行查询代码，返回数据，再通过 refs 修改 Books组件的 data 以动态渲染页面。
            this.$axios.get(url).then(resp => {
                if (resp && resp.status === 200) {
                    _this.$refs.booksArea.books = resp.data
                }
            })
        }
    }
}
</script>
  
<style scoped>
.books-area {
    width: 990px;
    margin-left: auto;
    margin-right: auto;
}
</style>
  
  