import axios from 'axios';
import {Message} from 'element-ui'
class AxiosConfig{
   constructor() {
    this.axiosService = axios.create({
      baseURL: 'http://localhost:8081'
    })
    axiosService.interceptors.request.use(config => {
      return config
    }, err=>{
      console.log(err)
      return Promise.reject(err)
    })
    axiosService.interceptors.response.use(response => {
      if (!response.success) {
        Message.error({message: response.data.message})
        return response;
      }
      return response
    }, err=>{
      Message.error({message: err})
      return Promise.reject(err)
    })
   }
}
const axiosConfig = new AxiosConfig()
export default axiosConfig.axiosService
