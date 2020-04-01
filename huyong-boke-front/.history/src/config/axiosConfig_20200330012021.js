import axios from 'axios';
import {Message} from 'element-ui'
import {removeStore } from '../utils/utils'
import router from '../router';
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
      let authorization = localStorage.getItem("token")
      if(authorization) {
        config.headers.authorization = authorization
      }
      return config
    }, err=>{
      return Promise.reject(err)
    })
    this.axiosService.interceptors.response.use(response => {
      if (!response.data.success) {
        console.log(response)
        alert(response.data.success)
        Message.error({message: response.data.message})
        //身份认证异常
        if(response.data.code === 333) {
          removeStore("token")
          router.push({path : '/'})
        }
        //继续传播
        if(response.data.code === 334) {
          Promise.resolve(response);
        }
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