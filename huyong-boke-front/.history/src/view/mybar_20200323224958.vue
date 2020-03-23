<template>
  <div>
    <div class="nav-container">
      <div class="nav-left">
        <div>
          <img src="../files/bar.png" class="nav-img" style="width: 30%;margin-left: 3%">
        </div>
        <div class="nav-middle">
          <div class="nav-text">
          <div class="nav-text-content">
              <span class="menu-text">发现</span>
          </div>
          <div class="nav-text-content">
              <span class="menu-text">关注</span>
          </div>
          <div class="nav-text-content">
            <el-row>
              <el-col class="menu-text">
                <el-dropdown>
                  <span class="el-dropdown-link">
                    <a >消息</a>
                  </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item icon="el-icon-check">
                      <a>私信</a>
                    </el-dropdown-item>
                    <el-dropdown-item icon="el-icon-check">
                      <a>与我相关</a>
                    </el-dropdown-item>
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
      </div>
      <div class="nav-right">
        <div class="right-content">
          <el-button type="primary" icon="el-icon-edit" circle @click="go('/customer/write')" ></el-button>
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
                    <el-avatar :size="50">
                      <img :src="baseUrl + user.picture"/>
                    </el-avatar>
                  </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item icon="el-icon-circle-plus">
                      <span @click='loginout'>退出</span>
                    </el-dropdown-item>
                    <el-dropdown-item icon="el-icon-user">
                      <a @click="go('own')">个人中心</a>
                    </el-dropdown-item>
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
            <el-form-item prop="account">
              <el-input placeholder="用户名或邮箱" type="text" v-model="ruleForm.account" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input placeholder="密码" type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitLoginForm('ruleForm')">登录</el-button>
              <el-button @click="logindialog = false">取 消</el-button>
            </el-form-item>
          </el-form>
        </el-dialog>
      </div>
      <div>
        <el-dialog :visible.sync="registerdialog" center title="注册" width="30%">
          <el-form  :model="ruleForm" status-icon :rules="rules" ref="ruleForm"  class="demo-ruleForm">
            <el-form-item prop="userName">
              <el-input placeholder="用户名" type="text" v-model="ruleForm.userName" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item prop="email">
              <el-input placeholder="邮箱" type="email" v-model="ruleForm.email" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item prop="code" v-if="(/^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/).test(ruleForm.email.trim()) != ''">
              <div style="display:flex;justify-content:space-between">
                <div>
                  <el-input placeholder="验证码" type="text" v-model="ruleForm.code" autocomplete="off"></el-input>
                </div>
                <div>
                  <span @click="getcode('ruleForm.code')" v-if="!sendcode">获取验证码</span>
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
                <el-button type="primary" @click="submitRegisterForm('ruleForm')">注册</el-button>
                <el-button @click="registerdialog = false">取 消</el-button>
              </el-form-item>
          </el-form>
        </el-dialog>
      </div>
  </div>
</template>
<script>
import {clearInput,encrypt,decrypt,setStore,getStore,removeStore,converObjToUrl,baseURL} from '../utils/utils'
export default {
  data() {
    var validateUserName = (rule, value, callback) => {
      let reg = /^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/
        if (value === '') {
          callback(new Error('请输入用户名'));
        } else if (reg.test(value)) {
          callback(new Error('用户名不能是邮箱格式'));
        } else {
          this.checkAccountUnique(value, 1).then(response => {
            if (!response.data.data) {
              callback('该用户名已被注册');
            } else {
              callback();
            }
          })
        }
      };
    var validateAccount = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入邮箱或用户名！'));
        } else {
          callback();
        }
      };

    var validateEmail = (rule, value, callback) => {
      let reg = /^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/
        if (value === '') {
          callback(new Error('请输入邮箱'));
        } else if (!reg.test(value)) {
          callback(new Error('邮箱格式不正确'));
        } else {
          this.checkAccountUnique(value, 0).then(response => {
            if (!response.data.data) {
              callback('该邮箱已被注册');
            } else {
              callback();
            }
          })
        }
      };
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        callback();
      }
    };
    var validateCode = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入验证码'));
      } else {
        callback();
      }
    };
    var validateRePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请确认密码'));
      } else if(value !== this.ruleForm.password){
        callback(new Error('两次输入密码不一致'));
      } else{
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
          //用户名
          userName: '',
          //邮箱
          email: '',
          //密码
          password: '',
          //验证码
          code : '',
          //重复确认密码
          repass : '',
          //登录账号
          account: '',
        },
      //注册表单校验规则
      rules: {
          account: [
            { validator: validateAccount, trigger: 'blur' }
          ],
          userName: [
            { validator: validateUserName, trigger: 'blur' }
          ],
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
      sendcode: false,
      //当前登录用户
      user: {},
      //左侧导航
      leftBars: [],
      //右侧导航
      rightBars: [],
      baseUrl: '',
    }
  },
  mounted() {
    this.getUser();
    this.baseUrl = baseURL
  },
  methods : {
    //路由跳转
    go(view) {
      this.$router.push({path : view})
    },
    //获取左边的导航
    getLeftBars() {
      this.$axios.get('/bar/getLeft').then(response => {
        this.leftBars = response.data.data
      })
    },
    //获取右边的导航
    getRightBars() {
      this.$axios.get('/bar/getRight').then(response => {
        this.rightBars = response.data.data
        //console.log(this.rightBars)
      })
    },
    //验证账号唯一性
    async checkAccountUnique(account,ops) {
      let obj = {}
      obj.account = account;
      obj.ops = ops;
      return await this.$axios.get("/user/checkAccountUnique" + converObjToUrl(obj))
    },
    //获取当前登录用户
    getUser() {
      let token = getStore("token")
      if(token) {
        this.$axios.get("/user/getUser").then(response => {
          this.user = response.data.data;
          if(this.user) {
            this.sign = true
          }
          this.$parent.setUser(this.user)
          this.$parent.tempName = this.user.userName
        })
      }
    },
    //websoocket
    connectWebSocket() {
      var webSocket;
      if (!window.WebSocket)
      {
          webSocket = new WebSocket("ws://localhost:8081/websocket");
          //连通之后的回调事件
          webSocket.onopen = function(){
              webSocket.send("发送数据");
          };
          //接收后台服务端的消息
          webSocket.onmessage = function (evt){
              alert(evt.data);
          };
          //连接关闭的回调事件
          webSocket.onclose = function(){
              alert("连接已关闭...");
          };
      }
    },
    //提交搜索内容
    searchInputSubmitMethod(value) {
      if (value && value.trim().length > 0) {
        alert(value)
      }
    },
    //提交登录表单
    submitLoginForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            //开始登录
            let user = {};
            user.account = this.ruleForm.account;
            user.password = encrypt(this.ruleForm.password);
            this.ruleForm.password = ''
            this.$axios.post("/user/login", user).then(response => {
              this.$message({
                message: '登录成功',
                type: 'success'
              });
              this.logindialog = false;
              this.sign = true;
              setStore("token", response.data.data.token);
              this.user = response.data.data;
            })
          } else {
            return false;
        }
      });

    },
    //提交注册表单
    submitRegisterForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            //开始登录
            let user = {};
            user.email = this.ruleForm.email;
            user.userName = this.ruleForm.userName;
            user.password = encrypt(this.ruleForm.password);
            user.code = this.ruleForm.code
            clearInput(this.ruleForm)
            this.$axios.post("/user/register", user).then(response => {
              this.registerdialog = false;
              this.$message({
                message: '注册成功，请登录',
                type: 'success'
              });
            })
          } else {
            console.log('error submit!!');
            return false;
        }
      });
    },
    //发送验证码
    getcode() {
      if(this.ruleForm.email.trim() === '') {
        this.$message({
            message: '请填写邮箱',
            type: 'error'
        });
        return;
      }
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
      this.$axios.get("/user/send", {
        params : {
          email: this.ruleForm.email
        }
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
        history.go(0)
        }).catch(() => {

        });
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
  display: flex;
}
.nav-left{
  flex: 1 8 auto;
  display: flex;
}
.nav-middle{
  display: flex;
  margin-left: -20%;
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
}
.nav-container{
  display: flex;
  flex-direction: row;
  height: 80px;
  align-items: center;
   -webkit-box-shadow: #666 0px 0px 10px;
   -moz-box-shadow: #666 0px 0px 10px;
   box-shadow: #666 0px 0px 3px;
   background: white;
}
.nav-img{
  height: 50px;
  width: 100px;
}
</style>
