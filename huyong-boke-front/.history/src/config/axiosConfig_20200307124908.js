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
      let a = config
      let b = config.headers
      let c = config.url
      console.log(a)
      console.log(b)
      console.log(c)
      config.headers.put("token", "hello")
      console.log(config)
      return config
    }, err=>{
      return Promise.reject(err)
    })
    this.axiosService.interceptors.response.use(response => {
      console.log(response)
      if (!response.data.success) {
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
