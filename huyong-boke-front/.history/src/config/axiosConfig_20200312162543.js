import axios from 'axios';
import {Message} from 'element-ui'
class AxiosConfig{
   constructor() {
    this.axiosService = axios.create({
      baseURL: 'http://localhost:8081'
    })
    this.addInterceptor()
    this.axiosService.getRequest = (url) => this.axiosService.get(url)
   }
   addInterceptor() {
    this.axiosService.interceptors.request.use(config => {
      config.headers.authorization = 'hello'
      let authorization = localStorage.getItem("authorization")
      if(authorization) {
        console.log(authorization)
      }
      console.log(a)
      return config
    }, err=>{
      return Promise.reject(err)
    })
    this.axiosService.interceptors.response.use(response => {
      if (!response.data.success) {
        if(response.data.code === 333) {
          let a = localStorage.getItem('authorization')
          console.log(a)
          console("身份认证失败，移除token缓存，重新登录")
        }
        Message.error({message: response.data.message})
        return Promise.reject(response.data.message);
      }
      return response
    }, err=>{
      Message.error({message: err})
      return Promise.reject(err)
    })
   }
}
export default AxiosConfig
