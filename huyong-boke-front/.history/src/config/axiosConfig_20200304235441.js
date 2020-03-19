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
    ElementUI.Message({
      message: err,
      type: 'error'
    });
  return Promise.reject(err)
})
const get = (url, data= {}, headers) => {
  return new Promise((resolve, reject) => {
    service({
      url,
      data: JSON.stringify(data),
      headers: {
        'Content-Type' : 'application/json'
      }
    })
  })
}
export default axiosService
