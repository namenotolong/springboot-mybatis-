<template>
  <div>
     <div class="my-header">
      <el-input v-model="input" placeholder="请输入标题" maxlength="100" class="title-input" >
    </el-input>
      <span class="compute-number">{{input.length}}/100</span>
      <el-button type="danger" style="margin-left: 5%" @click="publishArticle()">发布</el-button>
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
  </div>
</template>
<script>
export default {
  name: "write",
  data() {
    return {
        input: "",
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
    window.onbeforeunload = function (e) {
      if(_this.$route.fullPath =="/customer/write"){
          e = e || window.event;
          // 兼容IE8和Firefox 4之前的版本
          if (e) {
            e.returnValue = '关闭提示';
          }
          // Chrome, Safari, Firefox 4+, Opera 12+ , IE 9+
          return '关闭提示';
      }else{
        window.onbeforeunload =null
      }
    }
  },
  beforeRouteLeave (to, from, next) {
    this.$confirm('是否离开？, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          next()
        }).catch(() => {
          next(false)
    });
    next(false)
  },
  beforeDestroy() {
    window.onbeforeunload = null
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
