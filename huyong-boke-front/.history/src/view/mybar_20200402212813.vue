<template>
  <div>
    <div class="nav-container">
      <div class="nav-left nav-text">
        <div>
          <svg @click="go('/')" style="width: 30%;margin-left: 3%" t="1585206233475" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3799" width="200" height="200"><path d="M555.541333 117.994667l312.874667 224.565333A117.333333 117.333333 0 0 1 917.333333 437.866667V800c0 64.8-52.533333 117.333333-117.333333 117.333333H640V746.666667c0-70.688-57.312-128-128-128s-128 57.312-128 128v170.666666H224c-64.8 0-117.333333-52.533333-117.333333-117.333333V437.877333a117.333333 117.333333 0 0 1 48.917333-95.317333l312.874667-224.565333a74.666667 74.666667 0 0 1 87.082666 0z" p-id="3800" fill="#515151"></path></svg>
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
      <div class="nav-right">
        <div class="right-content" style="width: 10%">
          <a @click="go('/customer/write')" ><img src="../files/bar.png" class="nav-img" style="width: 80%;"></a>
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
                    <el-avatar :size="40">
                      <img :src="baseUrl + user.picture"/>
                    </el-avatar>
                  </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item icon="el-icon-circle-plus">
                      <span @click='loginout'>退出</span>
                    </el-dropdown-item>
                    <el-dropdown-item icon="el-icon-user">
                      <a @click="goOwn">个人中心</a>
                    </el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
              </el-col>
            </el-row>
        </div>
        <div class="right-content" v-if="sign" @click="go('/message/advice')">
          <el-badge :value="unReadCount == 0 ? null : unReadCount" class="item" type="danger">
             <svg style="width: 40px;height: 40px" t="1585490986377" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4026" width="200" height="200"><path d="M703.68768 793.8304a189.952 189.952 0 0 0-379.83744 0h379.83744z" fill="#2c2c2c" p-id="4027"></path><path d="M875.17184 663.35232a49.152 49.152 0 0 1-30.2336-40.89344V449.024c0-164.1216-148.54144-272.96256-212.38272-307.11808-3.5072-20.36736-12.66176-51.14368-36.55168-74.84416A110.80704 110.80704 0 0 0 515.93728 35.84h-2.76992a110.81728 110.81728 0 0 0-80.07168 31.232c-23.88992 23.70048-33.04448 54.48192-36.55168 74.84928-63.8208 34.16064-212.36736 143.0016-212.36736 307.10272v172.86144a49.1008 49.1008 0 0 1-30.23872 41.472c-58.48064 26.624-64.3328 76.99456-50.30912 115.2768 6.84032 18.67264 22.3232 36.89984 127.54432 36.89984h89.41568C326.144 911.60576 410.38848 988.16 513.16736 988.16s187.02848-76.55424 192.58368-172.63104h92.19072c105.216 0 120.704-18.2272 127.5392-36.89984 14.03904-38.27712 8.17664-88.66816-50.30912-115.2768z m-361.984 259.00032c-66.29376 0-120.80128-47.07328-126.3872-106.82368h252.77952c-5.6064 59.7504-60.11392 106.82368-126.4128 106.82368z m284.7744-172.63104h-566.784a396.71808 396.71808 0 0 1-68.12672-4.608c-0.512-9.60512 5.12-15.9232 18.2528-21.90336a114.58048 114.58048 0 0 0 68.81792-100.76672V449.024c0-145.54112 160.128-243.06176 189.5936-254.976l20.61312-8.10496v-22.14912c0.13312-6.36928 2.7904-62.1312 52.89472-62.1312H515.49184c50.31936-0.33792 53.15072 55.73632 53.28384 61.92128v22.18496l20.61312 8.31488c29.4656 11.88864 189.59872 109.4144 189.59872 254.976v173.41952a114.58048 114.58048 0 0 0 68.8128 100.76672c13.14304 5.98016 18.75456 12.288 18.2528 21.90336a396.72832 396.72832 0 0 1-68.11136 4.57216z" fill="#2c2c2c" p-id="4028"></path></svg>
          </el-badge>
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
      //是否展示我的关注的文章
      showFollow: false,
      //未读消息数量
      unReadCount: 0
    }
  },
  mounted() {
    this.getUser();
    this.baseUrl = baseURL
  },
  methods : {
    //获取未读消息数量
    getUnReadCount() {
      if(this.user.id) {
        this.$axios("/event/unReadCount").then(response => {
          this.unReadCount = response.data.data
        })
      }
    },
    //前往个人主页
    goOwn() {
      this.$router.push({path: `/own/ownHome/${this.user.id}`})
    },
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
          //获取未读消息数量
          this.getUnReadCount();
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
        this.$router.push({path: `/customer/search/${value}/2`})
        let url = this.$route.path;
        if(url.startsWith("/customer/search")) {
          alert(111)
          this.$parent.getData();
        }
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
              setStore("token", response.data.data.token);
              history.go(0)
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
    },
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
  flex: 4 1 auto;
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
