<template>
  <div>
     <div class="my-header">
      <el-input v-model="input" placeholder="请输入标题" maxlength="100" class="title-input" >
    </el-input>
      <span class="compute-number">{{input.length}}/100</span>
      <el-button type="danger" style="margin-left: 5%" @click="publishArticle()">发布</el-button>
      <el-input
        type="input"
        :rows="2"
        placeholder="请输入简介"
        v-model="textarea"
        maxlength="100"
        class="title-input"
      >
      </el-input>
      <span class="compute-number">{{textarea.length}}/100</span>
    </div>
    <div class="choose-menu">
      <el-button class="choose-menu-one" type="success" @click="goView('index')"><li class="el-icon-caret-left"></li>返回首页</el-button>
      <el-select v-model="value" placeholder="请选择发布类别" class="choose-menu-one">
        <el-option
          v-for="item in options"
          :key="item.id"
          :label="item.name"
          :value="item.name">
        </el-option>
      </el-select>
      <el-radio v-model="radio" label="0">公开</el-radio>
      <el-radio v-model="radio" label="1">私密</el-radio>
    </div>
    <div class="content1">
      <quill-editor
        v-model="content"
        ref="myQuillEditor"
        :options="editorOption"
        @blur="onEditorBlur($event)" @focus="onEditorFocus($event)"
        @change="onEditorChange($event)">
      </quill-editor>
    </div>
    <div class="content2" v-html="content">
    </div>
  </div>
</template>
<script>
export default {
  name: "write",
  data() {
    return {
        radio: '0',
        input: "",
        textarea: "",
        content_text: "",
        content: `<br><br>`,
        editorOption: {},
        options: null,
        value: ''
    }
  },
  methods: {
      goView(view){
        this.$router.push({path: '/' + view})
      },
      onEditorReady(editor) { // 准备编辑器
      },
      onEditorBlur(){}, // 失去焦点事件
      onEditorFocus(){}, // 获得焦点事件
      onEditorChange(){}, // 内容改变事件
      publishArticle(){

      }
  },
  computed: {
      editor() {
        return this.$refs.myQuillEditor.quill;
      },
  },
  mounted() {
    // this.$axios.get('http://zhsdsb.top:8888/getCatalogs')
    //     .then(response => {
    //       this.options = response.data
    //     })
  },
  components:{
  },
  beforeRouteLeave (to, from, next) {
    // 导航离开该组件的对应路由时调用
    // 可以访问组件实例 `this`
    // 该导航可以通过 next(false) 来取消。
    const answer = window.confirm('Do you really want to leave? you have unsaved changes!')
    if (answer) {
        next()
    } else {
    // 不支持传递回调(因为this实例已经创建可以获取到，所以没必要)
        next(false)
    }
  },
  beforeDestroy() {
    this.$confirm('确认离开吗？将不会保存内容, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      return false;
  }
}
</script>
<style scoped>
.choose-menu-one:hover{
    color: black;
  }
  .choose-menu-one{
    margin-right: 5%;
  }
  .content1{
    width: 50%;
    float: left;
  }
  .content2{
    width: 50%;
    float: left;
  }
  a{
    text-decoration-line: none;
  }
  .title-input{
    display: inline-block;width: 80%;
    padding: 8px;font-size: 18px;line-height: 24px;
    background-color: #fff;background-image: none;border: 0;border-radius: 4px;
  }
  .compute-number{
    line-height: 40px;
    font-size: 20px;
    margin-right: 8px;margin-top: 0.5%
  }
</style>
