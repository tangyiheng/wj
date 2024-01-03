<template>
    <div>
        <el-row style="height: 840px;">
            <!-- 添加搜索框 -->
            <search-bar @onSearch="searchResult" ref="searchBar"></search-bar>
            <!-- 利用 data 和 template 里相应元素的双向绑定，实现页面的动态渲染 -->
            <!-- 前端利用 currentPage 和 pagesize 来控制分页 -->
            <el-tooltip effect="dark" placement="right"
                v-for="item in books.slice((currentPage - 1) * pagesize, currentPage * pagesize)" :key="item.id">
                <p slot="content" style="font-size: 14px;margin-bottom: 6px;">{{ item.title }}</p>
                <p slot="content" style="font-size: 13px;margin-bottom: 6px">
                    <span>{{ item.author }}</span> /
                    <span>{{ item.date }}</span> /
                    <span>{{ item.press }}</span>
                </p>
                <p slot="content" style="width: 300px" class="abstract">{{ item.abs }}</p>
                <el-card style="width: 135px;margin-bottom: 20px;height: 233px;float: left;margin-right: 15px" class="book"
                    bodyStyle="padding:10px" shadow="hover">
                    <div class="cover" @click="editBook(item)">
                        <img :src="item.cover" alt="封面">
                    </div>
                    <div class="info">
                        <div class="title">
                            <a href="">{{ item.title }}</a>
                        </div>
                        <i class="el-icon-delete" @click="deleteBook(item.id)"></i>
                    </div>
                    <div class="author">{{ item.author }}</div>
                </el-card>
            </el-tooltip>
            <!-- 添加增加、删除按钮 -->
            <edit-form @onSubmit="loadBooks()" ref="edit"></edit-form>
        </el-row>
        <el-row>
            <!-- 完善分页功能 -->
            <el-pagination @current-change="handleCurrentChange" :current-page="currentPage" :page-size="pagesize"
                :total="books.length">
            </el-pagination>
        </el-row>
    </div>
</template>
  
<script>

// 编辑表单子组件
import EditForm from './EditForm'
// 搜索框子组件
import SearchBar from './SearchBar'

export default {
    name: 'Books',
    components: { EditForm, SearchBar },
    // 利用 data 和 template 里相应元素的双向绑定，实现页面的动态渲染
    data() {
        return {
            books: [],
            currentPage: 1,
            pagesize: 17
        }
    },
    // Vue 的 钩子函数 —— mounted
    // 在打开页面时就自动触发相应代码发送请求并渲染页面
    mounted: function () {
        this.loadBooks()
    },
    // 构造增、删、改、查对应的请求
    methods: {
        // 查询书籍
        loadBooks() {
            var _this = this
            // 利用 axios 发送了一个 get 请求，在接受到后端返回的成功代码后把 data 里的数据替换为后端返回的数据
            this.$axios.get('/books').then(resp => {
                if (resp && resp.status === 200) {
                    _this.books = resp.data
                }
            })
        },
        // 处理分页
        handleCurrentChange: function (currentPage) {
            this.currentPage = currentPage
            console.log(this.currentPage)
        },
        // 通过发送一个包含搜索关键词的请求到服务器的 /search 路径，并处理返回的响应，从而实现了书籍的搜索功能。
        searchResult() {
            var _this = this
            this.$axios
                .get('/search?keywords=' + this.$refs.searchBar.keywords, {
                }).then(resp => {
                    if (resp && resp.status === 200) {
                        _this.books = resp.data
                    }
                })
        },
        // 删除书籍
        deleteBook(id) {
            this.$confirm('此操作将永久删除该书籍, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                // post 请求的构造方式，post 不能像 get 请求那样直接把参数写在 url 里，而需要以键值对的方式传递
                this.$axios
                    .post('/delete', { id: id }).then(resp => {
                        if (resp && resp.status === 200) {
                            this.loadBooks()
                        }
                    })
            }
            ).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                })
            })
            // alert(id)
        },
        // 弹出修改表单并渲染数据
        editBook(item) {
            this.$refs.edit.dialogFormVisible = true
            this.$refs.edit.form = {
                id: item.id,
                cover: item.cover,
                title: item.title,
                author: item.author,
                date: item.date,
                press: item.press,
                abs: item.abs,
                category: {
                    id: item.category.id.toString(),
                    name: item.category.name
                }
            }
        }
    }
}
</script>
<style scoped>
.cover {
    width: 115px;
    height: 172px;
    margin-bottom: 7px;
    overflow: hidden;
    cursor: pointer;
}

img {
    width: 115px;
    height: 172px;
    /*margin: 0 auto;*/
}

.title {
    font-size: 14px;
    text-align: left;
}

.author {
    color: #333;
    width: 102px;
    font-size: 13px;
    margin-bottom: 6px;
    text-align: left;
}

.abstract {
    display: block;
    line-height: 17px;
}

.el-icon-delete {
    cursor: pointer;
    float: right;
}

.switch {
    display: flex;
    position: absolute;
    left: 780px;
    top: 25px;
}

a {
    text-decoration: none;
}

a:link,
a:visited,
a:focus {
    color: #3377aa;
}
</style>
  
  