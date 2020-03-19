import axios from 'axios';
const axiosService = axios.create({
  baseURL: 'http://localhost:8081'
})
axiosService.interceptors.request.use(config => {
  console.log('进入拦截器')
  return config
}, err)
export default axiosService
