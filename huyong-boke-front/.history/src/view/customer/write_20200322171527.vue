<template>
  <div>
    <div class="title">
      <div class="title-one">
        <el-button class="choose-menu-one" type="success" @click="goView('index')"><li class="el-icon-caret-left"></li>返回首页</el-button>
      </div>
      <div class="title textinput">
        <div class="title-input">
          <el-input v-model="input" placeholder="请输入标题" maxlength="100" class="" max></el-input>
        </div>
        <div>
          <span class="compute-number">{{input.length}}/100</span>
        </div>
      </div>
      <div class="title">
        <div>
          <el-button type="" @click="publishArticle()">保存草稿</el-button>
        </div>
        <div>
         <el-button type="danger" @click="publishArticle()">发布</el-button>
       </div>
       <div>
         <el-row>
            <el-col>
              <el-dropdown>
                <span class="el-dropdown-link">
                  <div class="circular--landscape">
                    <img v-if="user.picture" :src="baseUrl + user.picture" >
                  </div>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item icon="el-icon-circle-plus">
                    <span @click='loginout'>退出</span>
                  </el-dropdown-item>
                  <el-dropdown-item icon="el-icon-user">
                    <a @click="goView('own')">个人中心</a>
                  </el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </el-col>
          </el-row>
       </div>
      </div>
    </div>

    <div>
      <quill-editor
        v-model="content"
        style="border: 0"
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
import {baseURL,getStore,removeStore} from '../../utils/utils'
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

      },
      //注销
    loginout() {
      this.$confirm('确定退出吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'danger'
        }).then(() => {
          removeStore('token')
          this.$message({
            type: 'success',
            message: '退出成功!'
          });
          this.goView("/");
        }).catch(() => {
        });
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
.textinput{
  width: 80%;
}
.title-input{
  width: 95%;
}
  .title{
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
  }
  .title div {
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
