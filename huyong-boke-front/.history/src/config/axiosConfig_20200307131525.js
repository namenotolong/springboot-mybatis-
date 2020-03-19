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
      console.log(config)
      for (const key in config) {
        if (config.hasOwnProperty(key)) {
          const element = config[key];
          console.log(key + '->' + element)
          if(key === 'headers') {
            console.log(element)
          }
        }
      }
      return config
    }, err=>{
      return Promise.reject(err)
    })
    this.axiosService.interceptors.response.use(response => {
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
