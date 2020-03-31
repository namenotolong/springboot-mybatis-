<template>
  <div>
    <div class="title">
      <div class="title-one">
          <svg @click="goView('/')" style="width: 40px;height:40px;margin-left: 3%" t="1585206233475" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3799" width="200" height="200"><path d="M555.541333 117.994667l312.874667 224.565333A117.333333 117.333333 0 0 1 917.333333 437.866667V800c0 64.8-52.533333 117.333333-117.333333 117.333333H640V746.666667c0-70.688-57.312-128-128-128s-128 57.312-128 128v170.666666H224c-64.8 0-117.333333-52.533333-117.333333-117.333333V437.877333a117.333333 117.333333 0 0 1 48.917333-95.317333l312.874667-224.565333a74.666667 74.666667 0 0 1 87.082666 0z" p-id="3800" fill="#515151"></path></svg>
      </div>
      <div class="title textinput">
        <div class="title-input">
          <el-input v-model="article.title" placeholder="请输入标题" maxlength="100" class="" max></el-input>
        </div>
        <div>
          <span class="compute-number">{{article.title.length}}/100</span>
        </div>
      </div>
      <div class="title">
        <div>
          <el-button type="" @click="publishArticle(0)">保存草稿</el-button>
        </div>
        <div>
         <el-button type="danger" @click="publishArticle(1)">发布</el-button>
       </div>
       <div>
         <el-row>
            <el-col>
              <el-dropdown>
                <span class="el-dropdown-link">
                  <el-avatar :size="50">
                    <img :src="baseUrl + user.picture"/>
                  </el-avatar>
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
    <div class="down">
      <div style="width: 10%">
        <div>
          <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
            <el-submenu index="2">
              <template slot="title"><span style="color: black">选择分类</span></template>
              <span v-for="item in kinds" :key="item.id">
                <el-menu-item :index="item.kindName" v-if="item.children.length == 0">
                  <span class="black">{{item.kindName}}</span>
                </el-menu-item>
                <el-submenu :index="item.kindName" v-else>
                  <template slot="title"><span class="black">{{item.kindName}}</span></template>
                   <el-menu-item :index="kind.kindName" v-for="kind in item.children" :key="kind.id" @click="handleEdit(kind)">
                     <span :class="{black : kind.status == 0,red : kind.status == 1}">
                       {{kind.kindName}}
                     </span>
                  </el-menu-item>
                </el-submenu>
              </span>
            </el-submenu>
          </el-menu>
        </div>
        <el-tag
          v-for="tag in article.kinds"
          :key="tag.kindName"
          @close="handleEdit(tag)"
          closable>
          {{tag.kindName}}
        </el-tag>
      </div>
      <div>
        <quill-editor ref="myTextEditor"
          style="height:1000px"
          :options="quillOption"
          v-model="article.content"
          @blur="onEditorBlur($event)"
          @focus="onEditorFocus($event)"
          @change="onEditorChange($event)">
        </quill-editor>
      </div>
    </div>
  </div>
</template>
<script>
import {baseURL,getStore,removeStore} from '../../utils/utils'
import quillConfig from '../../config/quill-config'
export default {
  name: "write",
  data() {
    return {
        props: { multiple: true,value:"kindName",label:"kindName",expandTrigger:'hover' },
        activeIndex: '1',
        activeIndex2: '1',
        editorOption: {},
        baseUrl: '',
        user: {},
        quillOption: quillConfig,
        kinds: [],
        article: {'title' : '','content' : '', 'kinds' : []}
    }
  },
  methods: {
    //获取当前编辑文章内容
    getEditArticle() {
      if(this.$route.params.id){
        this.$axios.get("/article/detail", {
          params : {
            id : this.$route.params.id
          }
        }).then(response => {
          this.article = response.data.data
          //初始化kinds选中情况
          this.kinds.forEach(element => {
            console.log(element.status)
            element.status = 1
          });
        })
      }
    },
    handleSelect(key, keyPath) {
     },
    //获取所有分类
    getKinds() {
      this.$axios.get("/kind/getKinds").then(response => {
        this.kinds = response.data.data
        this.getEditArticle();
      })
    },
    //点击分类
    handleEdit(child) {
      if(child) {
        let flag = child.status;
        if(flag == 0) {
          this.article.kinds.push(child)
          child.status = 1;
        } else{
          console.log(child.id)
          for(let i = 0; i < this.article.kinds.length; i++) {
            if(this.article.kinds[i].id == child.id) {
              this.article.kinds.splice(i, 1)
              this.article.kinds = this.article.kinds
            }
          }
          child.status = 0;
        }
      }
    },
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
    //发表文章
    publishArticle(type){
      if(this.article.title.trim() === '') {
        this.$message.error("请输入标题");
        return;
      }
      this.$confirm('客观~确定吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'danger'
      }).then(() => {
        var kindIds = '';
        for (const iterator of this.article.kinds) {
          kindIds += iterator.id + ","
        }
        kindIds = kindIds.substring(0, kindIds.length - 1)
        this.article.type = type
        this.article.kindIds = kindIds
        this.article.ops = 0;
        this.$axios.post("/article/publishOrModify", this.article).then(response => {
          this.$message.success("发布成功！")
        })
      }).catch(() => {

      })
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
    window.onbeforeunload = e => {
      return false
    };
    this.getUser();
    this.baseUrl = baseURL
    this.getKinds();
  },
  beforeRouteLeave (to, from, next) {
    next()
  },
  beforeDestroy() {
  }
}
</script>
<style scoped>
.down{
  display: flex;
  flex-direction: row;
}
.el-menu-demo .el-menu-item{
    border-bottom-color:#ffffff!important;
    font-size: 16px;
}
 .el-dropdown {
    vertical-align: top;
  }
  .el-dropdown + .el-dropdown {
    margin-left: 15px;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
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
.red{
  color: red;
}
.black{
  color: black
}
</style>
