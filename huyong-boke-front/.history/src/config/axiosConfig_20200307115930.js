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
      return config
    }, err=>{
      return Promise.reject(err)
    })
    this.axiosService.interceptors.response.use(response => {
      if (!response.success) {
        Message.error({message: response.data.message})
        return Promise.reject();
      }
      return response
    }, err=>{
      Message.error({message: err})
      return Promise.reject(err)
    })
   }
   static
}
export default AxiosConfig
