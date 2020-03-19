import axios from 'axios';
import {Message} from 'element-ui'
class AxiosConfig{
   axiosService = axios.create({
    baseURL: 'http://localhost:8081'
  })
}
