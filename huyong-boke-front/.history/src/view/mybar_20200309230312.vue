<template>
  <div>
    <div class="nav-container">
      <div class="nav-left">
      <el-image :src="barimg" class="nav-img"></el-image>
      </div>
    <div class="nav-middle">
      <div class="nav-text">
        <div class="">
          <div class="nav-text-content">
            <a href="#">
              <span class="menu-text">
              发现
              </span>
            </a>
          </div>
        </div>
        <div class="nav-text-content">
          <a href="#">
            <span class="menu-text">
            关注
            </span>
          </a>
        </div>
        <div class="nav-text-content">
          <el-row>
            <el-col class="menu-text">
              <el-dropdown>
                <span class="el-dropdown-link">
                  消息
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item icon="el-icon-plus">黄金糕</el-dropdown-item>
                  <el-dropdown-item icon="el-icon-circle-plus">狮子头</el-dropdown-item>
                  <el-dropdown-item icon="el-icon-circle-plus-outline">螺蛳粉</el-dropdown-item>
                  <el-dropdown-item icon="el-icon-check">双皮奶</el-dropdown-item>
                  <el-dropdown-item icon="el-icon-circle-check">蚵仔煎</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </el-col>
          </el-row>
        </div>
        <div class="">
             <el-input
            placeholder="请输入搜索内容" type="text"
            prefix-icon="el-icon-search"
            clearable
            v-model="searchContent"
            @focus="searchInputFocus = true"
            @change="searchInputSubmitMethod"
            @blur="searchInputFocus = false"
            v-bind:class="{focus : searchInputFocus}"
            >
          </el-input>
        </div>
      </div>
      <div class="nav-blank">
      </div>
    </div>
    <div class="nav-right">
       <div class="right-content">
         <el-button type="primary" icon="el-icon-edit" circle></el-button>
       </div>
       <div class="right-content" v-if="!sign">
         <el-button circle type="warning" @click="logindialog = true">登录</el-button>
       </div>
        <div class="right-content" v-if="!sign">
         <a href="#">
            <span class="menu-text" @click="registerdialog = true">
            注册
            </span>
          </a>
       </div>
       <div class="right-content" v-if="sign">
         <el-row>
            <el-col>
              <el-dropdown>
                <span class="el-dropdown-link">
                  <img src="../img/2.png" class="icon-img">
                    <i class="el-icon-caret-bottom" />
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item icon="el-icon-plus">黄金糕</el-dropdown-item>
                  <el-dropdown-item icon="el-icon-circle-plus">狮子头</el-dropdown-item>
                  <el-dropdown-item icon="el-icon-circle-plus-outline">螺蛳粉</el-dropdown-item>
                  <el-dropdown-item icon="el-icon-check">双皮奶</el-dropdown-item>
                  <el-dropdown-item icon="el-icon-circle-check">蚵仔煎</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </el-col>
          </el-row>
       </div>
    </div>
    </div>
    <div>
      <el-dialog :visible.sync="logindialog" center title="登录" width="30%">
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm"  class="demo-ruleForm">
          <el-form-item prop="email">
            <el-input placeholder="邮箱" type="email" v-model="ruleForm.email" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input placeholder="密码" type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
            <el-button @click="logindialog = false">取 消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>
    <div>
      <el-dialog :visible.sync="registerdialog" center title="注册" width="30%">
        <el-form  :model="ruleForm" status-icon :rules="rules" ref="ruleForm"  class="demo-ruleForm">
          <el-form-item prop="email">
            <el-input placeholder="邮箱" type="email" v-model="ruleForm.email" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item prop="code">
            <div style="display:flex;justify-content:space-between">
              <div>
                <el-input placeholder="验证码" type="text" v-model="ruleForm.validateCode" autocomplete="off"></el-input>
              </div>
              <div>
                <span @click="getcode" v-if="!sendcode">获取验证码</span>
                <span v-if="sendcode">{{lastNumber}}s</span>
              </div>
           </div>
          </el-form-item>
          <el-form-item prop="password">
            <el-input placeholder="密码" type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item prop="repass">
            <el-input placeholder="确认密码" type="password" v-model="ruleForm.repass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
              <el-button @click="registerdialog = false">取 消</el-button>
            </el-form-item>
        </el-form>
      </el-dialog>
    </div>
    <div>
      <el-button @click="testaxios">test</el-button>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    var validateEmail = (rule, value, callback) => {
      let reg = /^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/
        if (value === '') {
          callback(new Error('请输入邮箱'));
        } else if (!reg.test(value)) {
          callback(new Error('邮箱格式不正确'));
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    var validateCode = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入验证码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    var validateRePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请确认密码'));
      } else if(value !== this.ruleForm.password){
        callback(new Error('两次输入密码不一致'));
      } else{
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    return {
      //搜索内容
      searchContent : '',
      //邮箱倒计时
      lastNumber : 60,
      //搜索框是否聚焦
      searchInputFocus : false,
      //是否是登录状态
      sign : false,
      //导航图片地址
      barimg : "https://cdn2.jianshu.io/assets/web/nav-logo-4c7bbafe27adc892f3046e6978459bac.png",
      //登录弹出框是否出现
      logindialog: false,
      //注册弹出框
      registerdialog: false,
      //校验
      ruleForm: {
          //邮箱
          email: '',
          //密码
          password: '',
          //验证码
          code : '',
          //重复确认密码
          repass : ''
        },
      //表单校验规则
      rules: {
          email: [
            { validator: validateEmail, trigger: 'blur' }
          ],
          password: [
            { validator: validatePass, trigger: 'blur' }
          ],
          code: [
            { validator: validateCode, trigger: 'blur' }
          ],
          repass : [
            { validator: validateRePass, trigger : 'blur'}
          ]
        },
      //是否可以获取验证码验证码
      sendcode: false
    }
  },
  mounted() {
    alert(111)
    var webSocket;
    if (window.WebSocket)
    {
        webSocket = new WebSocket("ws://localhost:8081/websocket");
        //连通之后的回调事件
        webSocket.onopen = function()
        {
            webSocket.send("发送数据");
        };
        //接收后台服务端的消息
        webSocket.onmessage = function (evt)
        {
            /*var received_msg = evt.data;
            var map1 = received_msg.map1;
            var map2 = received_msg.map2;
            console.log(map1);
            console.log(map2);*/
            alert(evt.data);
        };
        //连接关闭的回调事件
        webSocket.onclose = function()
        {
            alert("连接已关闭...");
        };
    }
  },
  methods : {
    testaxios() {
      this.$axios.getRequest("/test").then(response => {
        console.log(response)
        if(response.data.success) {
          console.log(response)
        }
      })
    },
    //提交搜索内容
    searchInputSubmitMethod(value) {
      if (value && value.trim().length > 0) {
        alert(value)
      }
    },
    //提交登录表单
    submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
        }
      });
    },
    //发送验证码
    getcode() {
      this.$message({
          message: '验证码已发送到了邮箱中，请查收',
          type: 'success'
        });
      this.sendcode = true;
      let timer = setInterval(() => {
        if(this.lastNumber > 0) {
        this.lastNumber--
      } else {
        this.lastNumber = 60
        this.sendcode = false
        clearInterval(timer);
      }
      }, 1000);
    }
  }
}
</script>
<style scoped>
.focus{
  width: 120%
}
.nav-text-content :hover{
  color: red;
  height: 100%;
}
.menu-text{
  font-size: 16px;
  color: #606266;
}
.el-dropdown-link{
  font-size: 16px;
}
.menu-text :hover{
  color: red
}
.nav-text{
  display: flex;
  flex: 1 1 auto;
  justify-content: space-around;
  align-items: center
}
.nav-blank{
  flex: 1 1 auto
}
.nav-left{
  flex: 1 1 auto
}
.nav-middle{
  display: flex;
  flex: 4 1 auto
}
.nav-right{
  display: flex;
  flex: 4 1 auto;
  flex-direction: row-reverse;
  align-items: center;
}
.right-content{
  margin-right: 4%
}
a{
  text-decoration: none;
  color: black
}
.nav-container{
  display: flex;
  flex-direction: row;
  height: 80px;
  align-items: center;
   -webkit-box-shadow: #666 0px 0px 10px;
   -moz-box-shadow: #666 0px 0px 10px;
   box-shadow: #666 0px 0px 10px;
   background: white;
}
.nav-img{
  height: 50px;
  width: 100px
}
.icon-img{
  height: 40px;
}
</style>
