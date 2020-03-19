import axios from 'axios';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
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
    ElementUI.$message({
      message: '验证码已发送到了邮箱中，请查收',
      type: 'success'
    });
  return Promise.reject(err)
})
export default axiosService
