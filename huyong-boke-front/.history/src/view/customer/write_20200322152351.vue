<template>
  <div>
    <div class="title">
      <div>
        <el-button class="choose-menu-one" type="success" @click="goView('index')"><li class="el-icon-caret-left"></li>返回首页</el-button>
      </div>
      <div>
        <el-input v-model="input" placeholder="请输入标题" maxlength="100" class="title-input"></el-input>
      </div>
      <div>
        <span class="compute-number">{{input.length}}/100</span>
      </div>
      <div>
        <el-button type="danger" style="margin-left: 5%" @click="publishArticle()">发布</el-button>
      </div>
    </div>

    <div>
      <quill-editor
        v-model="content"
        ref="myQuillEditor"
        :options="editorOption"
        @blur="onEditorBlur($event)" @focus="onEditorFocus($event)"
        @change="onEditorChange($event)">
      </quill-editor>
    </div>
    <div>
      {{content}}
    </div>
  </div>
</template>
<script>
export default {
  name: "write",
  data() {
    return {
        input: "",
        content: '',
        editorOption: {},
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
      }
  },
  mounted() {
//禁止页面刷新
    // window.onbeforeunload = e => {
    //   return returnValue
    // };
  },
  beforeRouteLeave (to, from, next) {
    next()
  },
  beforeDestroy() {
  }
}
</script>
<style scoped>
  .title{
    display: flex;
    flex-direction: row;
    align-items: center;
    align-content: space-between;
  }
  a{
    text-decoration-line: none;
  }
</style>
