import axios from 'axios';
import {Message} from 'element-ui'
class AxiosConfig{
   constructor() {
    this.axiosService = axios.create({
      baseURL: 'http://localhost:8081'
    })
   }
}
export default AxiosConfig
