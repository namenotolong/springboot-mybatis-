<template>
  <div>
    <div class="title">
      <div class="title-one">
        <el-button class="choose-menu-one" type="success" @click="goView('index')"><li class="el-icon-caret-left"></li>返回首页</el-button>
      </div>
      <div>
        <el-input v-model="input" placeholder="请输入标题" maxlength="100" class="title-input" max></el-input>
      </div>
      <div>
        <span class="compute-number">{{input.length}}/100</span>
      </div>
      <div>
        <el-button type="" @click="publishArticle()">保存草稿</el-button>
      </div>
      <div>
        <el-button type="danger" @click="publishArticle()">发布</el-button>
      </div>
      <div class="circular--landscape">
        <img v-if="user.picture" :src="baseUrl + user.picture" >
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
import {baseURL} from '../../utils/utils'
export default {
  name: "write",
  data() {
    return {
        input: "",
        content: '',
        editorOption: {},
        baseUrl: '',
        user: {}
    }
  },
  methods: {
    //获取当前登录用户
    getUser() {
      let token = getStore("token")
      if(token) {
        this.$axios.get("/user/getUser").then(response => {
          this.user = response.data.data;
        })
      }
    },
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
    this.getUser();
    this.baseUrl = baseURL
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
    flex: 1 7 1 1 auto
  }
  .title-one {
    margin-right: 3%;
  }
  a{
    text-decoration-line: none;
  }
  .circular--landscape{
    display:inline-block;
    position:relative;
    width:50px;
    height:50px;
    overflow:hidden;
    border-radius:50%;
}

.circular--landscape img{
    width:auto;
    height:100%;
}
</style>
