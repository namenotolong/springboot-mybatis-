import axios from 'axios';
import 'element-ui/lib/theme-chalk/index.css';
import ElementUI from 'element-ui';
const axiosService = axios.create({
  baseURL: 'http://localhost:8081'
})
axiosService.interceptors.request.use(config => {
  console.log('进入request拦截器')
  return config
}, err=>{
  console.log(err)
  return Promise.reject(err)
})
axiosService.interceptors.response.use(config => {
  console.log('进入response拦截器')
  return config
}, err=>{
  this.$message({
    message: '验证码已发送到了邮箱中，请查收',
    type: 'success'
  });
  return Promise.reject(err)
})
export default axiosService
